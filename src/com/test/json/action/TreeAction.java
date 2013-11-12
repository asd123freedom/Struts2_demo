package com.test.json.action;

import java.io.File;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class TreeAction extends ActionSupport {
	private Object[] childrenNodes;

	public Object[] getChildrenNodes() {
		return childrenNodes;
	}

	public void setChildrenNodes(Object[] childrenNodes) {
		this.childrenNodes = childrenNodes;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	private String id;
	private String name;
	private String level;

	public String execute() {
		if (id != "" && id != null) {
			System.out.println("id=" + id);
		} else {
			System.out.println("第一次加载"+id);
		}
		if (name != "" && level != null) {
			System.out.println("name=" + name);
		} else {
			System.out.println("第一次加载"+name);
		}
		if (level != "" && level != null) {
			System.out.println("level=" + level);
		} else {
			System.out.println("第一次加载"+level);
		}
		if (id==null) {
			JSONArray ja1 = new JSONArray();
			JSONObject js1 = new JSONObject();
			js1.put("id", "1");
			js1.put("name", "F1");
			js1.put("isParent", "true");
			ja1.add(js1);
			JSONObject js2 = new JSONObject();
			js2.put("id", "2");
			js2.put("name", "F2");
			ja1.add(js2);
			childrenNodes = ja1.toArray();
		}
		else{
			JSONArray ja1 = new JSONArray();
			JSONObject js1 = new JSONObject();
			js1.put("id",1);
			js1.put("name", id+"_"+level);
			js1.put("isParent", "true");
			ja1.add(js1);
			childrenNodes = ja1.toArray();
		}
		return "success";
	}

}
