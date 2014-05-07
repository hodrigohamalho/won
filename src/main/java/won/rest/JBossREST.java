package won.rest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import won.model.CLI;
import won.model.DC;
import won.util.CommonUtils;
import won.util.HTTPUtil;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

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
    public String resumeJbossInfo(){
        List<DC> dcs = activeDCS();
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
