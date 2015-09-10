package bean;

import java.util.ArrayList;
import java.util.List;

public class GUIConfig {
	public List<Config> configs  = new ArrayList<Config>();
	private String strategy = null;
	private int index = 0;
	private boolean global = false;
	private boolean enabled = true;
	private boolean shareOverLan = false;
	private boolean isDefault = false;
	private int localPort = 1080;
	private String pacUrl = null;
	private boolean useOnlinePac = false;
	private boolean availabilityStatistics = false;
	
	public void addConfig(Config c){
		configs.add(c);
	}
	public List<Config> getConfigs() {
		return configs;
	}
	public void setConfigs(List<Config> configs) {
		this.configs = configs;
	}
	public String getStrategy() {
		return strategy;
	}
	public void setStrategy(String strategy) {
		this.strategy = strategy;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public boolean isGlobal() {
		return global;
	}
	public void setGlobal(boolean global) {
		this.global = global;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public boolean isShareOverLan() {
		return shareOverLan;
	}
	public void setShareOverLan(boolean shareOverLan) {
		this.shareOverLan = shareOverLan;
	}
	public boolean isDefault() {
		return isDefault;
	}
	public void setDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}
	public int getLocalPort() {
		return localPort;
	}
	public void setLocalPort(int localPort) {
		this.localPort = localPort;
	}
	public String getPacUrl() {
		return pacUrl;
	}
	public void setPacUrl(String pacUrl) {
		this.pacUrl = pacUrl;
	}
	public boolean isUseOnlinePac() {
		return useOnlinePac;
	}
	public void setUseOnlinePac(boolean useOnlinePac) {
		this.useOnlinePac = useOnlinePac;
	}
	public boolean isAvailabilityStatistics() {
		return availabilityStatistics;
	}
	public void setAvailabilityStatistics(boolean availabilityStatistics) {
		this.availabilityStatistics = availabilityStatistics;
	}
	
}
