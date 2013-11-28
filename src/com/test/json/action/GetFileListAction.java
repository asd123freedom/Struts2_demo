package com.test.json.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class GetFileListAction extends ActionSupport{
	private String path;
	private String[] folders;
	private String start;
	static long max=0;
	static String max1="";
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	private ArrayList<String> files=new ArrayList<String>();
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
		if(path==null || path.length()==0){
			path="D:\\Workspaces\\branch\\HiServiceCRM\\src";
		}
		File file=new File(path);
		if(ActionContext.getContext().getSession().get("list")==null){
			getAllFiles(file);
			ActionContext.getContext().getSession().put("list",this.files);
		}
		ArrayList<String> tmpList=(ArrayList<String>) ActionContext.getContext().getSession().get("list");
		ArrayList<String> returnList=new ArrayList<String>();
		if(start!=null && start.length()!=0){
			for(int i=0;i<tmpList.size();i++){
				if(tmpList.get(i).toLowerCase().startsWith(start.toLowerCase())){
					returnList.add(tmpList.get(i));
				}
			}
			this.files=returnList;
		}
		return "success";
	}
	public void getAllFiles(File file){
		File[] arr=file.listFiles();
		for(int i=0;i<arr.length;i++){
			if(arr[i].isHidden()){
				//System.out.println(arr[i].getName());
				continue;
			}
			if(arr[i].isFile() && arr[i].getName().endsWith(".xml")){
				files.add(arr[i].getName());
				if (arr[i].length()>max){
					max=arr[i].length();
					max1=arr[i].getName();
				}
				//System.out.println(arr[i].getName());
			}else if(arr[i].isDirectory()){
				getAllFiles(arr[i]);
			}
		}
		
		return;
	}
	public static void main(String[] args) {
		String m5="27be1a569ae8205c916f58a4e00111c9";
		System.out.println(m5.length());
//		GetFileListAction g=new GetFileListAction();
//		String path="D:\\Workspaces\\branch\\HiServiceCRM\\src";
//		g.setPath(path);
//		File file=new File(path);
//		g.getAllFiles(file);
//		System.out.println(max);
//		System.out.println(max1);
	}
}
