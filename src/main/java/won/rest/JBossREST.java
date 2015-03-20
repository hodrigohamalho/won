package won.rest;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import won.model.CLI;
import won.model.DC;
import won.repository.DCRepository;
import won.util.CommonUtils;
import won.util.HTTPUtil;

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
    
    @GET
    @Path("/resume")
    public String resumeJbossInfo(){
        List<DC> dcs = dcRepository.activeDCS();
        JSONObject dcsJson = new JSONObject();

        // for each DC retrieve DC data
        for (DC dc : dcs) {
            try {
                JSONObject resume = new JSONObject();
                JSONObject hostsJson = new JSONObject();

                JSONObject rootLevel = HTTPUtil.retrieveJSONFromDC(dc, new CLI("", false, false));
                List<String> hostNames = hostsNames(rootLevel);

                if (CommonUtils.isNotEmpty(hostNames)) {

                    // for each host retrieve host information.
                    for (String hostName : hostNames) {
                        JSONObject hostLevel = HTTPUtil.retrieveJSONFromDC(dc, new CLI("/host/" + hostName, true, true));
                        hostsJson.put(hostName, hostLevel);
                    }

                    resume.put("hosts", hostsJson);
                }

                dcsJson.put(rootLevel.get("name").toString(), resume);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return dcsJson.toString();
    }

    private List<String> hostsNames(JSONObject rootLevel) throws JSONException {
        return jsonArrayToList(rootLevel.getJSONObject("host").names());
    }

    private JSONObject wrapJson(JSONObject json, String name) throws JSONException {
        JSONObject wrap = new JSONObject();
        wrap.put(name, json);

        return wrap;
    }


    private List<String> jsonArrayToList(JSONArray array){
        List<String> list = new ArrayList<String>();

        for (int i=0; i<array.length(); i ++){
            try {
                list.add(array.get(i).toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return list;
    }
}
