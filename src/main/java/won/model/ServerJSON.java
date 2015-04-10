package won.model;

import org.json.JSONObject;

public class ServerJSON {
	
	private JSONObject server;
	private DC dc;
	private String host;
	private String name;

	public JSONObject getServer() {
		return server;
	}

	public void setServer(JSONObject server) {
		this.server = server;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public DC getDc() {
		return dc;
	}

	public void setDc(DC dc) {
		this.dc = dc;
	}
}
