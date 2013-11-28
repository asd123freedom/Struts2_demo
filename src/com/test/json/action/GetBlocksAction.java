package com.test.json.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;

public class GetBlocksAction extends ActionSupport{
	private String info;
	private ArrayList<Long> l;
	private ArrayList<String> l_md5;
	public ArrayList<String> getL_md5() {
		return l_md5;
	}
	public void setL_md5(ArrayList<String> lMd5) {
		l_md5 = lMd5;
	}
	private String path="E:\\Ubuntu_Tools\\Check_Tomcat.java";
	public static int size=50;
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public ArrayList<Long> getL() {
		return l;
	}
	public void setL(ArrayList<Long> l) {
		this.l = l;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String execute(){
		File file=new File(path);
		BufferedReader br=null;
		try {
			br=new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		
		StringBuffer sb=new StringBuffer("");
		String line=br.readLine();
		sb.append(line+"\r\n");
		while(true){
			line=br.readLine();
			if(line==null){
				break;
			}
			sb.append(line+"\r\n");
		}
		String content=sb.toString();
		ArrayList<Long> list=new ArrayList<Long>();
		ArrayList<String> list_md5=new ArrayList<String>(); 
		Map hm=new HashMap<Long, ArrayList<String>>();
		//System.out.println(content);
		for(int i=0;i<content.length()/size;i++){
			String temp=content.substring(i*size,i*size+size);
			//System.out.println(temp);
			long l=adler32(temp.getBytes());
			//System.out.println(l);
			ArrayList<String> list2=null;
			if(hm.containsKey(l)){
				list2=(ArrayList<String>) hm.get(l);
			}else{
				list2=new ArrayList<String>();
			}
			list2.add(temp);
			hm.put(l, list2);
			list.add(l);
			list_md5.add(MD5(temp));
		}
		this.l=list;
		this.l_md5=list_md5;
		String left=content.substring((content.length()/size)*size);
		System.out.println(left);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
	}
	public static long adler32(byte[] data){
		long a = 0, b = 0;
		int MOD_ADLER = 65521;
	    /* Process each byte of the data in order */
	    for (int index = 0; index < data.length; ++index)
	    {
	        a = (a + data[index]) % MOD_ADLER;
	        //System.out.println(a);
	        b = (b + a) % MOD_ADLER;
	        //System.out.println(b);
	    }
	    return (b << 16) | a;
	}
	public static long adler32_rolling_checksum(long csum, int len, byte c1,  
            byte c2) {  
        long s1, s2; 
        int MOD_ADLER = 65521;
        s1 = (csum & 0xffff);  
        s2 = (csum >> 16);
        //System.out.println(s1);
        //System.out.println(s2);
        s1 -= (c1 - c2);  
        s2 -= (len * c1 - s1); 
        s1=s1%MOD_ADLER;
        System.out.println(s1);
        s2=s2%MOD_ADLER;
        System.out.println(s2);
        return (s1 & 0xffff) + ((s2 & 0xffff) << 16);  
    }
	public final static String MD5(String s) {
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};       
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
