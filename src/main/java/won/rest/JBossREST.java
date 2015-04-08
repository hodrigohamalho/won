package won.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.json.JSONArray;
import org.json.JSONObject;

import won.model.CLI;
import won.model.DC;
import won.repository.DCRepository;
import won.util.CommonUtils;
import won.util.HTTPUtil;
import won.util.JSONUtil;

/**
 * @author Rodrigo Ramalho
 *         hodrigohamalho@gmail.com.
 */

@Path("/jboss")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class JBossREST {

	@Inject
	private DCRepository dcRepository;

	/**
	 * Return a list of all DCS with they respective servers
	 * @return
	 */
	@GET
	@Path("/servers")
	public String servers(@QueryParam("dc") Integer dcId, @QueryParam("by") String type){
		if (dcId == null)
			throw new IllegalArgumentException("dc parameter is mandatory");

		DC dc = dcRepository.find(dcId);
		if (dc == null)
			throw new IllegalArgumentException("dc not found");
		
		JSONObject serversByGroup = new JSONObject();
		String servers = "";

		// for each DC retrieve DC data
		try {
			JSONObject hostsJson = new JSONObject();

			String rootLevel = HTTPUtil.retrieveJSONFromDC(dc, new CLI("", false, false));
			List<String> hostNames = JSONUtil.keysFromObject(new JSONObject(rootLevel), "host");

			if (CommonUtils.isNotEmpty(hostNames)) {

				// for each host retrieve host information.
				for (String hostName : hostNames) {
					JSONArray array = null;
					String url = "/host/" + hostName + "/server-config/*";
					String jsonString = HTTPUtil.retrieveJSONFromDC(dc, new CLI(url, false, true));

					if (jsonString.startsWith("[") && jsonString.endsWith("]"))
						array = new JSONArray(jsonString);

					for (int i=0; i < array.length(); i++){
						JSONArray addresses = new JSONArray(new JSONObject(array.get(i).toString()).get("address").toString());
						String serverName = new JSONObject(addresses.get(1).toString()).getString("server-config");
						JSONObject serverConfigValue = new JSONObject(new JSONObject(array.get(i).toString()).get("result").toString());
						serverConfigValue.put("name", serverName);
						serverConfigValue.put("host", hostName);
						if ("group".equalsIgnoreCase(type)){
							serversByGroup.accumulate(serverConfigValue.getString("group"),  serverConfigValue);
						}else{
							hostsJson.accumulate(hostName, serverConfigValue);
						}
					}
				}

				if ("group".equalsIgnoreCase(type)){
					servers = serversByGroup.toString();
				}else{
					servers = hostsJson.toString();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return servers;
	}
	
	
	@GET
	@Path("/deploy")
	public String deploymentInfo(@QueryParam("dc") Integer dcId, @QueryParam("host") String host, @QueryParam("server") String server){
		JSONObject deploymentInfo = new JSONObject();
		
		DC dc = dcRepository.find(dcId);
		if (dc == null)
			throw new IllegalArgumentException("dc not found");
		
		JSONArray array = null;
		String url = "/host/"+host+"/server/"+server+"/deployment/*";
		String jsonString = null;
		try {
			jsonString = HTTPUtil.retrieveJSONFromDC(dc, new CLI(url, true, true));
			if (jsonString.startsWith("[") && jsonString.endsWith("]"))
				array = new JSONArray(jsonString);
			
			for (int i=0; i < array.length(); i++){
				JSONObject deployment = new JSONObject(new JSONObject(array.get(i).toString()).get("result").toString());
				deploymentInfo.put(deployment.getString("name").toString(), deployment);
			}
		} catch (Exception e) {
		}

		
		return deploymentInfo.toString();
	}
}
