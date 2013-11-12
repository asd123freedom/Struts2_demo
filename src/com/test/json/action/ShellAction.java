package com.test.json.action;

import com.opensymphony.xwork2.ActionSupport;
import com.test.json.dao.saveMonitor;

public class ShellAction extends ActionSupport{
	private String command;
	private String info;
	public String execute() {
		
		return "success";
	}

}
