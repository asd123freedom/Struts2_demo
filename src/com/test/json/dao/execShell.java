package com.test.json.dao;

import java.io.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class execShell {
	public execShell() {
		runTime = Runtime.getRuntime();
		if (runTime == null) {
			System.err.println("Create runtime false!");
			System.exit(1);
		}
	}

	private Process pro = null;
	private Runtime runTime = null;

	public static void main(String[] args) {
		execShell es = new execShell();
		Scanner sc = new Scanner(System.in);
		String command = sc.nextLine();
		try {
			es.pro = es.runTime.exec(command);
			BufferedReader input = new BufferedReader(new InputStreamReader(
					es.pro.getInputStream()));// 这个输入流是获取shell输出的
			BufferedReader input2 = new BufferedReader(new InputStreamReader(
					es.pro.getErrorStream()));// 这个输入流是获取shell错误输出的
			PrintWriter output = new PrintWriter(new OutputStreamWriter(es.pro
					.getOutputStream()));// 这个输出流主要是对Process进行输入控制用的
			String line;
			char[] arr=new char[100];
			while ((input2.read(arr,0,100)) != -1) {
				for(int i=0;i<arr.length;i++){
					System.out.print(arr[i]);
				}
				System.out.println();
				//System.out.println(line);
//				if (-1 != line.indexOf("remove")) {// 提示输入为错误输出，则执行输入操作
//					output.print("n\r\n");// \r\n 不可少，否则相当于没有Enter操作
//					output.flush();// 输入完成之后一定要flush。否则一直处在等待输入的地方
//				}
			}
			while ((line = input.readLine()) != null) {
				System.out.println(line);
			}
			input.close();
			input2.close();
			output.close(); 
			es.pro.destroy(); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
