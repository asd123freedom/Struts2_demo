package com.test.json.action;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.opensymphony.xwork2.ActionSupport;

public class AssembleAction extends ActionSupport{
	public String info;
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public JSONObject json;
	public String  array;
	public String execute() {
		System.out.println(info);
		if(json==null){
			System.out.println("asd");
			return "success";
		}
		JSONArray ja=json.getJSONArray("info");
		System.out.println(ja.size());
		return "success";
	}
	public JSONObject getJson() {
		return json;
	}
	public void setJson(JSONObject json) {
		this.json = json;
	}
	public String getArray() {
		return array;
	}
	public void setArray(String array) {
		this.array = array;
	}

}
