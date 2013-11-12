package com.test.json.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;
import com.test.json.bean.UserInfo;

public class JsonStruts2Action extends ActionSupport{
	private String name;
	private UserInfo userInfo;
	private List<UserInfo> userInfosList;     
    private Map<String,UserInfo> userInfosMap;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	public List<UserInfo> getUserInfosList() {
		return userInfosList;
	}
	public void setUserInfosList(List<UserInfo> userInfosList) {
		this.userInfosList = userInfosList;
	}
	public Map<String, UserInfo> getUserInfosMap() {
		return userInfosMap;
	}
	public void setUserInfosMap(Map<String, UserInfo> userInfosMap) {
		this.userInfosMap = userInfosMap;
	}
    public String returnMessage(){ 

        this.name = "zidane"; 

        return "message"; 

    }
    public String gainUserInfo(){ 
        System.out.println("name"+userInfo.getName()); 
        System.out.println("password"+userInfo.getPassword()); 
        return "userInfo"; 

    } 
    public String returnUserInfo(){ 

        userInfo = new UserInfo();
        userInfo.setName("James");
        userInfo.setPassword("abc");
        return "userInfo"; 

    }
    public String returnList(){
    	this.userInfosList=new ArrayList<UserInfo>();
    	UserInfo u1=new UserInfo();
    	UserInfo u2=new UserInfo();
    	UserInfo u3=new UserInfo();
    	u1.setName("Dirk");
    	u2.setName("Kobe");
    	u3.setName("Yao");
    	this.userInfosList.add(u1);
    	this.userInfosList.add(u2);
    	this.userInfosList.add(u3);
    	return "list";
    }
    public String returnMap(){
    	userInfosMap = new HashMap<String,UserInfo>(); 
    	UserInfo u1=new UserInfo();
    	UserInfo u2=new UserInfo();
    	UserInfo u3=new UserInfo();
    	u1.setName("Dirk");
    	u2.setName("Kobe");
    	u3.setName("Yao");
    	userInfosMap.put(u1.getName(),u1);
    	userInfosMap.put(u2.getName(),u2);
    	userInfosMap.put(u3.getName(),u3);
    	return "map";
    }
}
