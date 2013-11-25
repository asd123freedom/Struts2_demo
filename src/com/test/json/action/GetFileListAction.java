package com.test.json.action;

import java.io.File;
import java.util.ArrayList;

import com.opensymphony.xwork2.ActionSupport;

public class GetFileListAction extends ActionSupport{
	private String path;
	private String[] folders;
	private ArrayList files=new ArrayList<String>();
	public String[] getFolders() {
		return folders;
	}
	public void setFolders(String[] folders) {
		this.folders = folders;
	}
	public ArrayList<String> getFiles() {
		return files;
	}
	public void setFiles(ArrayList<String> files) {
		this.files = files;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String execute() {
		File file=new File(path);
		getAllFiles(file);
		return "success";
	}
	public void getAllFiles(File file){
		File[] arr=file.listFiles();
		for(int i=0;i<arr.length;i++){
			if(arr[i].isHidden()){
				System.out.println(arr[i].getName());
				continue;
			}
			if(arr[i].isFile() && arr[i].getName().endsWith(".xml")){
				files.add(arr[i].getName());
				System.out.println(arr[i].getName());
			}else if(arr[i].isDirectory()){
				getAllFiles(arr[i]);
			}
		}
		return;
	}
	public static void main(String[] args) {
		GetFileListAction g=new GetFileListAction();
		String path="D:\\Workspaces\\branch\\HiServiceCRM\\src";
		g.setPath(path);
		g.execute();
	}
}
