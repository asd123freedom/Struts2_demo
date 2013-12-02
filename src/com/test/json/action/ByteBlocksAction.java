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

public class ByteBlocksAction extends ActionSupport{
	private String info;
	private ArrayList<Long> l;
	private ArrayList<String> l_md5;
	public ArrayList<String> getL_md5() {
		return l_md5;
	}
	public void setL_md5(ArrayList<String> lMd5) {
		l_md5 = lMd5;
	}
	private String path="F:\\TestGBK.java";
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
		char[] content=sb.toString().toCharArray();
		ArrayList<Long> list=new ArrayList<Long>();
		ArrayList<String> list_md5=new ArrayList<String>(); 
		Map hm=new HashMap<Long, ArrayList<String>>();
		//System.out.println(content);
		for(int i=0;i<content.length-size;i+=size){
			char[] temp=new char[size];
			for(int j=0;j<size;j++){
				//console.log((b[i+j]-0)+b[i+j]);
				temp[j]=content[i+j];
			}
			String str=new String(temp);
			//System.out.println(str);
			//String temp=content.substring(i*size,i*size+size);
			//System.out.println(temp);
			//System.out.println(temp.getBytes().length);
			long l=adler32(temp);
			//System.out.println(l);
			ArrayList<String> list2=null;
			if(hm.containsKey(l)){
				list2=(ArrayList<String>) hm.get(l);
			}else{
				list2=new ArrayList<String>();
			}
			list2.add(str);
			hm.put(l, list2);
			list.add(l);
			list_md5.add(MD5(str));
		}
		this.l=list;
		this.l_md5=list_md5;
		//String left=sb.toString().substring((sb.toString().length()/size)*size);
		//System.out.println(left);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
	}
	public static long adler32(char[] data){
		long a = 0, b = 0;
		int MOD_ADLER = 65521;
	    /* Process each byte of the data in order */
	    for (int index = 0; index < data.length; ++index)
	    {
	        if((int)data[index]> 0x9fff || (int)data[index] < 4e00){
	        	if((int)data[index] > 127){
	        		System.out.println((int)data[index]);
	        	}
	        }
	    	//System.out.println(data[index]);
	    	a = (a + (int)data[index]) % MOD_ADLER;
	        //System.out.println(a);
	        b = (b + a) % MOD_ADLER;
	        //System.out.println(b);
	    }
	    return (b << 16) | a;
	}
	public static long adler32_rolling_checksum(long csum, int len, char c1,  
            char c2) {  
        long s1, s2; 
        int MOD_ADLER = 65521;
        s1 = (csum & 0xffff);  
        s2 = (csum >> 16);
        //System.out.println(s1);
        //System.out.println(s2);
        s1 -= ((int)c1 - (int)c2);  
        s2 -= (len * (int)c1 - s1); 
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
	public static void main(String[] args) {
		new GetBlocksAction().execute();
	}
}

