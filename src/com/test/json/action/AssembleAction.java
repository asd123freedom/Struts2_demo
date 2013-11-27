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
	public Object[]  array;
	public String execute() {
		System.out.println(info);
		if(array!=null){
			for(int i=0;i<array.length;i++){
				Object o=array[i];
				if(o instanceof Long){
					System.out.println("Long");
				}else if(o instanceof String){
					System.out.println("String");
				}
			}
			return "success";
		}else{
			System.out.println("失败");
			return "success";
		}
	}
	public JSONObject getJson() {
		return json;
	}
	public void setJson(JSONObject json) {
		this.json = json;
	}
	public Object[] getArray() {
		return array;
	}
	public void setArray(Object[] array) {
		this.array = array;
	}

}
