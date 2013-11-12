package com.test.json.action;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.opensymphony.xwork2.ActionSupport;

public class GraphAction extends ActionSupport {
	private String info;

	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String returnInfo(){
		try{
			JSONObject js=new JSONObject();
			js.put("label","湖人");
			js.put("data",11);
			JSONObject js1=new JSONObject();
			js1.put("label","凯尔特人");
			js1.put("data",15);
			JSONObject js2=new JSONObject();
			js2.put("label","马刺");
			js2.put("data",5);
			JSONArray ja=new JSONArray();
			ja.add(js);
			ja.add(js1);
			ja.add(js2);
			this.info=ja.toString();
			
		}catch(Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return "info";
	}

}
