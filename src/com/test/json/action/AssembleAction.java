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
	public Object json;
	public String  array;
	public String execute() {
		JSONObject jo=JSONObject.fromObject(info);
		JSONArray ja=JSONArray.fromObject(jo.get("info"));
		System.out.println(ja.size());
		return "success";
	}
	public Object getJson() {
		return json;
	}
	public void setJson(Object json) {
		this.json = json;
	}
	public String getArray() {
		return array;
	}
	public void setArray(String array) {
		this.array = array;
	}

}
