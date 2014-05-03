package won.rest;

import org.apache.commons.collections.ListUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import won.model.*;
import won.util.CommonUtils;
import won.util.HTTPUtil;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Rodrigo Ramalho
 *         hodrigohamalho@gmail.com.
 */

@Stateless
public class JBossREST implements IJBossREST{

    @Inject
    private EntityManager em;

    public List<DC> activeDCS(){
        try{
            Query query = em.createQuery("SELECT d FROM DC d where d.active = true");
            return (List<DC>) query.getResultList();
        }catch(NullPointerException nre){
            return new ArrayList<DC>();
        }
    }

    @Override
    public List<DC> teste(){
        List<Group> groups = new ArrayList<Group>();
        List<DC> dcs = activeDCS();

        // for each DC retrieve DC data
        for (DC dc : dcs) {
            try {
                JSONObject rootLevel = HTTPUtil.retrieveJSONFromDC(dc, new CLI("", false, false));

                List<String> hostNames = hostsNames(rootLevel);
                if (CommonUtils.isNotEmpty(hostNames)) {

                    // for each host retrieve host information.
                    for (String hostName : hostNames) {
                        HC hc = new HC();
                        hc.setName(hostName);

                        JSONObject hostLevel = HTTPUtil.retrieveJSONFromDC(dc, new CLI("/host/" + hostName, true, true));

                        JSONObject serverConfig = new JSONObject(hostLevel.get("server-config").toString());
                        List<String> serverNames = jsonArrayToList(serverConfig.names());

                        // for each server retrieve server information
                        for (String serverName : serverNames) {
                            if (CommonUtils.isNotEmpty(serverNames)) {
                                JSONObject serverJson = new JSONObject(serverConfig.get(serverName).toString());
                                Server server = new Server();
                                server.setName(serverJson.get("name").toString());
                                server.setStatus(serverJson.get("status").toString());
                            }
                        }

                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return "oi";
    }

    private List<String> hostsNames(JSONObject rootLevel) throws JSONException {
        return jsonArrayToList(rootLevel.getJSONObject("host").names());;
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
