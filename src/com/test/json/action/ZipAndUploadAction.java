package com.test.json.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class ZipAndUploadAction extends ActionSupport{
	private File grand;
	private String grandFileName;
	private String grandContentType;
	private String savePath;
	private String folderName;
	public String getFolderName() {
		return folderName;
	}

	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}

	public String getSavePath() {
		return ServletActionContext.getServletContext().getRealPath(savePath);
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public File getGrand() {
		return grand;
	}

	public void setGrand(File grand) {
		this.grand = grand;
	}

	public String getGrandFileName() {
		return grandFileName;
	}

	public void setGrandFileName(String grandFileName) {
		this.grandFileName = grandFileName;
	}

	public String getGrandContentType() {
		return grandContentType;
	}

	public void setGrandContentType(String grandContentType) {
		this.grandContentType = grandContentType;
	}
	public void test(){
		System.out.println("新建文件夹");
	}
	public String execute(){
		try {
			String path=getSavePath()+"\\"+folderName;
			File temp=new File(path);
			if(!temp.exists()){
				temp.mkdirs();
			}
			FileOutputStream fos=new FileOutputStream(path+"\\"+getGrandFileName());
			FileInputStream fis;
			fis = new FileInputStream(getGrand());
			
		byte[] buffer=new byte[1024];
		int len=0;
		while((len=fis.read(buffer))>0){
			fos.write(buffer,0,len);
		}
		fos.close();
		fis.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
		
	}


}
