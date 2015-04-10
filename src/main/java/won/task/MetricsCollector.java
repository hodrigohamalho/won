package won.task;


import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Singleton;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.json.JSONObject;

import won.model.CLI;
import won.model.DC;
import won.model.Datasource;
import won.model.DatasourceMetrics;
import won.model.Server;
import won.model.ServerJSON;
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
	
	@Inject
	private DCRepository dcRepository;
	@Inject
	private DatasourceRepository datasourceRepository;
	
	@Inject
	private Event<ServerJSON> serverEvent;
	
//	@Schedule(dayOfWeek = "*", hour = "*", minute = "*/5", second = "*", year = "*", persistent = false)
    public void collect(){
    	servers();
    }
	
	public void datasourceMetrics(@Observes ServerJSON serverJSON){
		Server server = new Server();
		server.setDc(serverJSON.getDc());
		server.setHost(serverJSON.getHost());
		server.setName(serverJSON.getName());
		
		JSONObject datasourcesJSON = serverJSON.getServer().getJSONObject("subsystem").getJSONObject("datasources");
		List<String> dsNames = JSONUtil.keysFromObject(datasourcesJSON, "data-source");
		for (String dsName : dsNames) {
			JSONObject dsJSON = datasourcesJSON.getJSONObject("data-source").getJSONObject(dsName);
			Datasource datasource = datasourceRepository.findByNameAndHost(dsName, serverJSON.getHost());
			DatasourceMetrics metric = new DatasourceMetrics();
			datasource.setName(dsName);
			metric.setDriverName(dsJSON.getString("driver-name"));
			metric.setConnectionUrl(dsJSON.getString("connection-url"));
			metric.setMinPoolSize(dsJSON.getInt("min-pool-size"));
			metric.setMaxPoolSize(dsJSON.getInt("max-pool-size"));
			
			JSONObject pool = dsJSON.getJSONObject("statistics").getJSONObject("pool");
			
			metric.setAvailableCount(pool.getLong("AvailableCount"));
			metric.setAverageBlockingTime(pool.getLong("AverageBlockingTime"));
			metric.setAverageCreationTime(pool.getLong("AverageCreationTime"));
			metric.setActiveCount(pool.getLong("ActiveCount"));
			metric.setCreatedCount(pool.getLong("CreatedCount"));
			metric.setDestroyedCount(pool.getLong("DestroyedCount"));
			metric.setInUseCount(pool.getLong("InUseCount"));
			metric.setMaxCreationTime(pool.getLong("MaxCreationTime"));
			metric.setMaxUsedCount(pool.getLong("MaxUsedCount"));
			metric.setMaxWaitCount(pool.getLong("MaxWaitCount"));
			metric.setMaxWaitTime(pool.getLong("MaxWaitTime"));
			metric.setTimedOut(pool.getLong("TimedOut"));
			metric.setTotalBlockingTime(pool.getLong("TotalBlockingTime"));
			metric.setTotalCreationTime(pool.getLong("TotalCreationTime"));
			System.out.println(datasource);
		}
	}
	
	/**
	 * This method trigger a ServerJSON event with all information about a running server
	 */
	public void servers(){
		List<DC> dcs = dcRepository.activeDCS();
		
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
							
							ServerJSON serverJson = new ServerJSON();
							serverJson.setServer(new JSONObject(serverInfo));
							serverJson.setName(serverName);
							serverJson.setHost(hostName);
							serverJson.setDc(dc);
							
							serverEvent.fire(serverJson);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
