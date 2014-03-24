package won.util;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.GetMethod;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


/**
 *
 * @author rodrigoramalho
 *
 */
public class HTTPUtil {

    public static String getJSONfromUrl(String urlPath, Boolean recursive, Boolean runtime) throws Exception {

        Properties properties = new Properties();
        String json = "";

        properties.load(Thread.currentThread().getContextClassLoader().getResource("configuration.properties").openStream());
        String host = properties.get("host").toString();
        Integer port = Integer.valueOf(properties.get("port").toString());
        String username = properties.get("username").toString();
        String password = properties.get("password").toString();
        String realm = properties.get("realm").toString();

        StringBuilder url = new StringBuilder("http://"+host+":"+port+"/"+"management"+urlPath);
        url.append("?recursive=").
            append(recursive.toString()).
            append("&include-runtime=").
            append(runtime.toString());

        HttpClient client = new HttpClient();
        GetMethod getMethod = new GetMethod(url.toString());

        UsernamePasswordCredentials upc = new UsernamePasswordCredentials(username, password);
        AuthScope as = new AuthScope(host, port, realm);
        client.getState().setCredentials(as, upc);

        int status = client.executeMethod(getMethod);

        if (status == 200){
            String responseBody = getMethod.getResponseBodyAsString();
            getMethod.releaseConnection();

            json = responseBody;
        }else if (status != 500){
            throw new Exception("HTTP code returned on trying to connect with jboss domain controller: "+status);
        }

        return json;
    }


    public static Map<String, String> propertiesToMap(String path){
        Map<String, String> exportedProperties = new HashMap<String, String>();

        try {
            Properties props = new HTTPUtil().loadProperties(path);
            Enumeration<String> propertyNames = (Enumeration<String>) props.propertyNames();
            while (propertyNames.hasMoreElements()){
                String p = propertyNames.nextElement();
                exportedProperties.put(p, props.get(p).toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return exportedProperties;
    }


    private Properties loadProperties(String path) throws IOException {
        Properties properties = new Properties();
        properties.load(Thread.currentThread().getContextClassLoader().getResource(path).openStream());

        return properties;
    }

}
