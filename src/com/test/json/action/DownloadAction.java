package com.test.json.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class DownloadAction extends ActionSupport{
	 private String fileName;
     private String filePath;
     public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileName() {
		return fileName;
	}
	 public void setFileName(String fileName) {
         this.fileName = fileName;
	 }
	public InputStream getInputStream() throws FileNotFoundException {
			filePath=ServletActionContext.getRequest().getParameter("filePath");
			System.out.println("filename="+fileName);
			System.out.println("filepath="+filePath);
			String fileName1="";
			try {
				fileName1= URLDecoder.decode(this.fileName, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String path=ServletActionContext.getServletContext().getRealPath("/transfer"+filePath+fileName1);
			System.out.println(path);
			System.out.println((new File(path)).exists());
//            return ServletActionContext.getServletContext().getResourceAsStream("/transfer/" + fileName1);
			return new FileInputStream(path);
     }
    
     public String execute(){
             return "success";
     }


}
