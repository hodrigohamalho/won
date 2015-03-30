package won.util;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import won.model.CLI;
import won.model.DC;


/**
 * @author Rodrigo Ramalho
 *         hodrigohamalho@gmail.com.
 */
public class HTTPUtil {

    // TODO it must be dynamic
    public final static String REALM = "ManagementRealm";

    // TODO ok, ok. This method name is not the best thing to see. lol
    public static String retrieveJSONFromDC(DC dc, CLI cli) throws IOException {
        String result = null;
        String url = generateJBossURL(dc, cli).toString();

        DefaultHttpClient client = configureHttpClient();
        authenticateClient(dc,client);

        InputStream inputStream = null;
        try{
            HttpGet request = new HttpGet(url);
            HttpResponse response = client.execute(request);
            // Check if response server is valid
            StatusLine status = response.getStatusLine();

            if (status.getStatusCode() != 200)
                throw new IOException("Invalid response from server: " + status.toString());

            HttpEntity entity = response.getEntity(); // Pull content Stream from server
            inputStream = entity.getContent();

            result = responseContent(inputStream);
//                json = new JSONObject(result);

//                if (json.has("outcome") && json.get("outcome") == "failed")
//                    throw new OutComeFailed(json.get("failure-description").toString());
//            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (inputStream != null)
                inputStream.close();
        }
        
        return result;
    }

    private static String responseContent(InputStream inputStream) throws IOException {
        ByteArrayOutputStream content = new ByteArrayOutputStream();
        int readBytes = 0;

        // Read response into a buffered stream
        byte[] sBuffer = new byte[512];
        while ((readBytes = inputStream.read(sBuffer)) != -1) {
          content.write(sBuffer, 0, readBytes);
        }

        return new String(content.toByteArray());
    }

    private static void authenticateClient(DC dc, DefaultHttpClient client) {
        UsernamePasswordCredentials upc = new UsernamePasswordCredentials(dc.getUsername(), dc.getPassword());
        AuthScope as = new AuthScope(dc.getHost(), dc.getPort(), HTTPUtil.REALM);

        client.getCredentialsProvider().setCredentials(as, upc);
    }

    private static DefaultHttpClient configureHttpClient(){
        int timeout = 10; //seconds
        DefaultHttpClient client = new DefaultHttpClient();
        HttpParams params = client.getParams();
        HttpConnectionParams.setConnectionTimeout(params, timeout * 1000); // http.connection.timeout
        HttpConnectionParams.setSoTimeout(params, timeout * 1000); // http.socket.timeout


        return client;
    }

    private static StringBuilder generateJBossURL(DC dc, CLI cli) {
        StringBuilder url = new StringBuilder("http://"+dc.getHost()+":"+dc.getPort()+"/"+"management"+ cli.getUrl());
        url.append("?recursive=").
            append(cli.isRecursive()).
            append("&include-runtime=").
            append(cli.isRuntime());
        return url;
    }

}
