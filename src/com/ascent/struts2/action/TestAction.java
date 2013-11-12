package com.ascent.struts2.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;

public class TestAction implements Action{
	private String user;
	private String pass;
	private InputStream inputStream;
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public InputStream getResult() {
		return inputStream;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 5154262851467114730L;

	public String execute() throws Exception {
		// TODO Auto-generated method stub
//		HttpServletRequest request=ServletActionContext.getRequest();
//		String name=(String)request.getAttribute("name");
//		System.out.println(name);
//		HttpServletResponse response=ServletActionContext.getResponse();
		if(user.equals("freedom") && pass.equals("freedom")){
			inputStream = new ByteArrayInputStream("Congratulations!".getBytes("utf-8"));
		}
		else{
			inputStream = new ByteArrayInputStream("Sorry".getBytes("utf-8"));
		}
		return SUCCESS;
	}

//	public void setInputStream(InputStream inputStream) {
//		this.inputStream = inputStream;
//	}
//
//	public InputStream getInputStream() {
//		return inputStream;
//	}
	

}
