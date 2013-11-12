package com.test.json.action;

import java.io.IOException;

import com.opensymphony.xwork2.ActionSupport;
import com.test.json.dao.saveMonitor;

public class SaveAction extends ActionSupport{
	private String url;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String execute() {
		try {
			saveMonitor.test(url);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
	}
	

}
