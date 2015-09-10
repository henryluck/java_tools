package bean;

public class Config {
	private String server;
	private int server_port = 8989;
	private String password;
	private String method;
	private String remarks;
	public String getServer() {
		return server;
	}
	public void setServer(String server) {
		this.server = server;
	}
	public int getServer_port() {
		return server_port;
	}
	public void setServer_port(int server_port) {
		this.server_port = server_port;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
