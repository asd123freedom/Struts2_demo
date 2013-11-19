package com.test.json.dao;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestInOut {
	public static void main(String[] args) {
		String name="Freedom is my name because of Gundam";
		BufferedReader br=new BufferedReader(new InputStreamReader(new ByteArrayInputStream( name.getBytes() )));
		char[] arr=new char[5];
		try {
			while(br.read(arr,0,2)!=-1){
				for(int i=0;i<arr.length;i++){
					if(arr[i]==0){
						continue;
					}
					System.out.print(arr[i]);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
