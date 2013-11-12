package com.ascent.struts2.action;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletResponseAware;

public class LoginAction implements ServletResponseAware{
	private String username;
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public String execute(){ 
		if(this.getUsername()==null){
			System.out.println("ssas");
			return "error";
		}
			
		else {
			System.out.println("dasdsa");
		 	if(this.getUsername().equals("liang")&&this.getPassword().equals("liang")){
			return "success";}
			else
				return "error";
		   
	    }
	}

	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub
		
	}

}
