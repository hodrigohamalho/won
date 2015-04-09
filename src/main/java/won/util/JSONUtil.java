package won.util;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Rodrigo Ramalho
 *         hodrigohamalho@gmail.com.
 */
public class JSONUtil {

	public static List<String> keysFromObject(JSONObject rootLevel, String object) throws JSONException {
		try{
			return jsonArrayToList(rootLevel.getJSONObject(object).names());
		}catch(Exception e){
			return new ArrayList<String>();
		}
	}

    public static List<String> serverGroupNames(JSONObject rootLevel) throws JSONException {
		return jsonArrayToList(rootLevel.getJSONObject("host").names());
	}

    public static JSONObject wrapJson(JSONObject json, String name) throws JSONException {
		JSONObject wrap = new JSONObject();
		wrap.put(name, json);

		return wrap;
	}
	
    public static List<String> jsonArrayToList(JSONArray array){
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
