package won.task;


import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.json.JSONObject;

import won.model.CLI;
import won.model.DC;
import won.model.Datasource;
import won.model.Server;
import won.repository.DCRepository;
import won.repository.DatasourceRepository;
import won.util.CommonUtils;
import won.util.HTTPUtil;
import won.util.JSONUtil;


/**
 * @author Rodrigo Ramalho
 *         hodrigohamalho@gmail.com.
 */
@Singleton
public class MetricsCollector {
	
	@Inject
	private Logger log;
	
	@Resource
    TimerService timerService;
	
	@Timeout
    public void programmaticTimeout(Timer timer) {
		log.info("Programmatic timeout occurred.");
    }
	
	@Inject
	private DCRepository dcRepository;
	@Inject
	private DatasourceRepository datasourceRepository;
	
	@Inject
	private Event<Server> serverEvent;
	
	@Schedule(dayOfWeek = "*", hour = "*", minute = "*/5", second = "*", year = "*", persistent = false)
    public void collect(){
    	servers();
    }
	
	public void datasourceMetrics(@Observes Server server){
		System.out.println("SERVER: "+server.toString());
	}
	
	
	public List<Server> servers(){
		List<DC> dcs = dcRepository.activeDCS();
		List<Server> servers = new ArrayList<Server>();
		
		for (DC dc : dcs) {
			try {
				String rootLevel = HTTPUtil.retrieveJSONFromDC(dc, new CLI("", false, false));
				List<String> hostNames = JSONUtil.keysFromObject(new JSONObject(rootLevel), "host");

				if (CommonUtils.isNotEmpty(hostNames)) {

					// for each host retrieve host information.
					for (String hostName : hostNames) {
						String url = "/host/" + hostName;
						String hostInfo = HTTPUtil.retrieveJSONFromDC(dc, new CLI(url, false, false));
						List<String> serverNames = JSONUtil.keysFromObject(new JSONObject(hostInfo), "server");
						
						// For each server 
						for (String serverName : serverNames) {
							String serverURL = "/host/" + hostName + "/server/"+serverName;
							String serverInfo = HTTPUtil.retrieveJSONFromDC(dc, new CLI(serverURL, true, true));
							JSONObject serverJSON = new JSONObject(serverInfo);
							
							Server server = new Server();
							server.setDc(dc);
							server.setHost(hostName);
							server.setName(serverJSON.get("name").toString());
							JSONObject datasourcesJSON = serverJSON.getJSONObject("subsystem").getJSONObject("datasources");
							List<String> dsNames = JSONUtil.keysFromObject(datasourcesJSON, "data-source");
							for (String dsName : dsNames) {
								JSONObject dsJSON = datasourcesJSON.getJSONObject("data-source").getJSONObject(dsName);
								Datasource datasource = datasourceRepository.findByNameAndHost(dsName, hostName);
								datasource.setName(dsName);
								datasource.setDriverName(dsJSON.getString("driver-name"));
								datasource.setConnectionUrl(dsJSON.getString("connection-url"));
								datasource.setMinPoolSize(dsJSON.getInt("min-pool-size"));
								datasource.setMaxPoolSize(dsJSON.getInt("max-pool-size"));
								
								JSONObject pool = dsJSON.getJSONObject("statistics").getJSONObject("pool");
								
								datasource.setAvailableCount(pool.getLong("AvailableCount"));
								datasource.setAverageBlockingTime(pool.getLong("AverageBlockingTime"));
								datasource.setAverageCreationTime(pool.getLong("AverageCreationTime"));
								datasource.setActiveCount(pool.getLong("ActiveCount"));
								datasource.setCreatedCount(pool.getLong("CreatedCount"));
								datasource.setDestroyedCount(pool.getLong("DestroyedCount"));
								datasource.setInUseCount(pool.getLong("InUseCount"));
								datasource.setMaxCreationTime(pool.getLong("MaxCreationTime"));
								datasource.setMaxUsedCount(pool.getLong("MaxUsedCount"));
								datasource.setMaxWaitCount(pool.getLong("MaxWaitCount"));
								datasource.setMaxWaitTime(pool.getLong("MaxWaitTime"));
								datasource.setTimedOut(pool.getLong("TimedOut"));
								datasource.setTotalBlockingTime(pool.getLong("TotalBlockingTime"));
								datasource.setTotalCreationTime(pool.getLong("TotalCreationTime"));
								System.out.println(datasource);
							}
							
							serverEvent.fire(server);
							servers.add(server);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return servers;
	}
}
