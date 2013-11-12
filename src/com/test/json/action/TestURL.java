package com.test.json.action;

import java.io.BufferedReader;
import java.io.File;

import java.io.IOException;

import java.io.InputStream;

import java.io.InputStreamReader;

import java.net.Authenticator;

import java.net.HttpURLConnection;

import java.net.PasswordAuthentication;

import java.net.URL;

import java.net.URLConnection;

import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class TestURL {

	// 一个public方法，返回字符串，错误则返回"error open url"

	public static String getContent(String strUrl) {
		try {
			URL url = new URL(strUrl);
			BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
			String s = "";
			StringBuffer sb = new StringBuffer("");
			while ((s = br.readLine()) != null) {
				sb.append(s + "\r");
			}
			br.close();
			return sb.toString();
		} catch (Exception e) {
			return "error open url:" + strUrl;
		}
	}

	public static void initProxy(final String username,final String password) {
		Authenticator.setDefault(new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username,
				new String(password).toCharArray());
			}
		});
	}

	public static String content(String url) throws IOException {
//		Document doc = null;
//		try {
//			doc = new SAXReader().read(new File("/cfg.xml"));
//		} catch (DocumentException e) {
//			e.printStackTrace();
//		}
//		String url = "";
		String username = "";
		String password = "";
//		Element root = doc.getRootElement();
//		List list = root.elements();
//		for (Iterator its = list.iterator(); its.hasNext();) {
//			Element chileEle = (Element) its.next();
//			if (chileEle.getName().equals("url"))
//				url = (String) chileEle.getData();
//			if (chileEle.getName().equals("username"))
//				username = (String) chileEle.getData();
//			if (chileEle.getName().equals("password"))
//				password = (String) chileEle.getData();
//			System.out.println(chileEle.getName());
//		}
//		url = "http://192.168.0.197:8888/icinga/cgi-bin/status.cgi?jsonoutput";
		username = "icingaadmin";
		password = "123456";
		String curLine = "";
		String content = "";
		URL server = new URL(url);
		initProxy(username, password);
		HttpURLConnection connection = (HttpURLConnection) server.openConnection();
		connection.connect();
		InputStream is = connection.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		while ((curLine = reader.readLine()) != null) {
			content = content + curLine + "\r\n";
		}
		is.close();
		return content;
	}
	public static void main(String[] args) throws IOException {
		TestURL.content("http://192.168.0.197:8888/icinga/cgi-bin/status.cgi?jsonoutput");
	}

}
