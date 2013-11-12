package com.test.json.Model;

import java.util.Date;

public class MonitorData {
	private long id;
	private String hostname;
	private String server_description;
	private String status;
	private long last_check;
	private Boolean active_checks_enabled;
	private Boolean passive_checks_enabled;
	private String status_information;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getHostname() {
		return hostname;
	}
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	public String getServer_description() {
		return server_description;
	}
	public void setServer_description(String serverDescription) {
		server_description = serverDescription;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public long getLast_check() {
		return last_check;
	}
	public void setLast_check(long lastCheck) {
		last_check = lastCheck;
	}
	public Boolean getActive_checks_enabled() {
		return active_checks_enabled;
	}
	public void setActive_checks_enabled(Boolean activeChecksEnabled) {
		active_checks_enabled = activeChecksEnabled;
	}
	public Boolean getPassive_checks_enabled() {
		return passive_checks_enabled;
	}
	public void setPassive_checks_enabled(Boolean passiveChecksEnabled) {
		passive_checks_enabled = passiveChecksEnabled;
	}
	public String getStatus_information() {
		return status_information;
	}
	public void setStatus_information(String statusInformation) {
		status_information = statusInformation;
	}


}
