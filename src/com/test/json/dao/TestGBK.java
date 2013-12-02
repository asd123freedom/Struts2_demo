package com.test.json.dao;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import com.sun.imageio.plugins.common.InputStreamAdapter;


public class TestGBK {
	public static void main(String[] args) {
		//File file=new File("E:\\Ubuntu_Tools\\Check_Tomcat.java");
		File file=new File("F:\\gbk.txt");
		File file2=new File("F:\\utf.txt");
		try {
			BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(file),"gbk"));
			BufferedReader br2=new BufferedReader(new InputStreamReader(new FileInputStream(file2),"utf-8"));
			String line=br.readLine();
			String line2=br2.readLine();
			byte[] b=line.getBytes();
			for(int i=0;i<b.length;i++){
				int a=b[i] & 0xff;
				System.out.println(a+" "+(char)a);
			}
			//System.out.println(line);
			//System.out.println(line2);
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	 public static String getTxtEncode(FileInputStream in) throws IOException{
		  
		  		String dc  = Charset.defaultCharset().name();
		  		System.out.println(dc);
		        UnicodeInputStream uin = new UnicodeInputStream(in,dc);
		        System.out.println(uin.getEncoding());
		        if("UTF-8".equals(uin.getEncoding())){
		         uin.close();
		         return "UTF-8";
		        }
		        //uin.close();
		        
		        byte[] head = new byte[3];  
		        in.read(head);    
		        String code = "GBK";  
		        if (head[0] == -1 && head[1] == -2 )  
		            code = "UTF-16";  
		        if (head[0] == -2 && head[1] == -1 )  
		            code = "Unicode";
		        //��BOM
		        if(head[0]==-17 && head[1]==-69 && head[2] ==-65)  
		            code = "UTF-8";  
		        if("Unicode".equals(code)){
		         code = "UTF-16";
		        }
		        return code;
		 }

}
