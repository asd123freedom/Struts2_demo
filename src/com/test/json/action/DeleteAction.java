package com.test.json.action;

import java.io.File;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class DeleteAction extends ActionSupport {
	private String fileName;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String execute() {
		String path=ServletActionContext.getServletContext().getRealPath("/transfer"+fileName);
		System.out.println(path);
		File file=new File(path);
		if(file.exists()){
			System.out.println(file.delete());
		}
		return "success";
	}
	
}
