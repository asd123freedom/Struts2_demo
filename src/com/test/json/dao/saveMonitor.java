package com.test.json.dao;


import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.management.monitor.Monitor;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.test.json.Model.MonitorData;
import com.test.json.action.TestURL;


public class saveMonitor {
	public static void main(String[] args) throws IOException {
		Configuration conf=new Configuration().configure();
		SessionFactory sf=conf.buildSessionFactory();
		Session sess=sf.openSession();
		Transaction tx=sess.beginTransaction();
		String content=TestURL.content("");
		JSONObject js1=JSONObject.fromObject(content);
		JSONObject js2=JSONObject.fromObject(js1.get("status"));
		JSONArray ja1=new JSONArray();
		JSONObject js3=new JSONObject();
		JSONArray ja=JSONArray.fromObject(js2.get("service_status"));
		SimpleDateFormat sp=new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
		for(int i=0;i<ja.size();i++){
			JSONObject temp=JSONObject.fromObject(ja.get(i));
			String host_name=temp.getString("host_name");
			if(host_name.equals("localhost")){
				continue;
			}
			MonitorData md=new MonitorData();
			md.setHostname(temp.getString("host_name"));
			Date date=null;
			try {
				String last_check=temp.getString("last_check");
				if(last_check.equals("N/A")){
					date=new Date();
					date.setTime(0);
				}
				else{
					date = sp.parse(temp.getString("last_check"));
					System.out.println(date);
				}
					
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			md.setLast_check(date.getTime());
			md.setActive_checks_enabled(temp.getBoolean("active_checks_enabled"));
			md.setPassive_checks_enabled(temp.getBoolean("passive_checks_enabled"));
			md.setStatus(temp.getString("status"));
			md.setStatus_information(temp.getString("status_information"));
			md.setServer_description(temp.getString("service_description"));
			sess.save(md);
		}
		tx.commit();
		sess.close();
	}
	public static void test(String url) throws Exception{
		Configuration conf=new Configuration().configure();
		SessionFactory sf=conf.buildSessionFactory();
		Session sess=sf.openSession();
		Transaction tx=sess.beginTransaction();
		String content=TestURL.content(url);
		JSONObject js1=JSONObject.fromObject(content);
		JSONObject js2=JSONObject.fromObject(js1.get("status"));
		
		JSONArray ja=JSONArray.fromObject(js2.get("service_status"));
		SimpleDateFormat sp=new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
		for(int i=0;i<ja.size();i++){
			JSONObject temp=JSONObject.fromObject(ja.get(i));
			String host_name=temp.getString("host_name");
			if(host_name.equals("localhost")){
				continue;
			}
			MonitorData md=new MonitorData();
			md.setHostname(temp.getString("host_name"));
			Date date=null;
			try {
				String last_check=temp.getString("last_check");
				if(last_check.equals("N/A")){
					date=new Date();
					date.setTime(0);
				}
				else{
					date = sp.parse(temp.getString("last_check"));
					System.out.println(date);
				}
					
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			md.setLast_check(date.getTime());
			md.setActive_checks_enabled(temp.getBoolean("active_checks_enabled"));
			md.setPassive_checks_enabled(temp.getBoolean("passive_checks_enabled"));
			md.setStatus(temp.getString("status"));
			md.setStatus_information(temp.getString("status_information"));
			md.setServer_description(temp.getString("service_description"));
			sess.save(md);
		}
		tx.commit();
		sess.close();
	}

}
