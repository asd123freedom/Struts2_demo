package com.ascent.po;

import java.io.*;
import java.util.*;

public class test {
	public static int[] a = new int[301];
	public static int test2(int n,int len){
		for(int i=0;i<n;i++)
		{
			for(int j=i+len;j<n;j++)
			{
				int flag= 1;
				for(int k=0;k<len;k++)
				{
					if(a[i+k]!=a[j+k])
					{
						flag = 0;
						break;
					}
				}
				if(flag==1)return 1;
			}
		}
		return 0;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(new BufferedInputStream(System.in));
		int n = sc.nextInt(); 
		for(int i=0;i<n;i++){
			a[i]=sc.nextInt();
		}
		int l=0,h=n/2;
		while(l<=h){
			int mid = (l+h)/2;
			int flag = test2(n,mid);
			if(flag==1) l=mid+1;
			else h=mid-1;
		}
		System.out.println(h);
	}

}
