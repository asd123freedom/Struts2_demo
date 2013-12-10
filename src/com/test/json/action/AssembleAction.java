package com.test.json.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.opensymphony.xwork2.ActionContext;
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
		HashMap hm=(HashMap) ActionContext.getContext().getSession().get("Blocklist");
		System.out.println(hm.size());
		File file=new File("F:\\newFile.java");
		PrintWriter pw=null;
		try {
			pw=new PrintWriter(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject jo=JSONObject.fromObject(info);
		JSONArray ja=JSONArray.fromObject(jo.get("info"));
		String left=jo.getString("left");
		System.out.println(ja.size());
		StringBuffer sb=new StringBuffer("");
		for(int i=0;i<ja.size();i++){
			int type=(Integer)ja.getJSONObject(i).get("type");
			if(type==1){
				String temp=(String) hm.get(ja.getJSONObject(i).get("MD5"));
				sb.append(temp);
			}else{
				sb.append((String)ja.getJSONObject(i).get("content"));
			}
		}
		sb.append(left);
		pw.write(sb.toString());
		pw.close();
		String filename1="E:/TestGBK.java";
		String filename2="F:/newFile.java";
		try {
			BufferedReader br1=new BufferedReader(new InputStreamReader(new FileInputStream(filename1)));
			BufferedReader br2=new BufferedReader(new InputStreamReader(new FileInputStream(filename2)));
			while(true){
				String line1=br1.readLine();
				String line2=br2.readLine();
				if(line1==null && line2==null){
					break;
				}
				//System.out.println(line1.equals(line2));
			}
			br1.close();
			br2.close();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			System.out.println("结果是"+getMD5Checksum(filename1).equals(getMD5Checksum(filename2)));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	public static byte[] createChecksum(String filename) throws Exception {
	       InputStream fis =  new FileInputStream(filename);

	       byte[] buffer = new byte[1024];
	       MessageDigest complete = MessageDigest.getInstance("MD5");
	       int numRead;

	       do {
	           numRead = fis.read(buffer);
	           if (numRead > 0) {
	               complete.update(buffer, 0, numRead);
	           }
	       } while (numRead != -1);

	       fis.close();
	       return complete.digest();
	}
	public static String getMD5Checksum(String filename) throws Exception {
	       byte[] b = createChecksum(filename);
	       String result = "";

	       for (int i=0; i < b.length; i++) {
	           result += Integer.toString( ( b[i] & 0xff ) + 0x100, 16).substring( 1 );
	       }
	       return result;
	}
	public static void main(String[] args) {
		
	}
}
