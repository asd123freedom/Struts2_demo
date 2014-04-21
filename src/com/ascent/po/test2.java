package com.ascent.po;

import java.util.Scanner;

public class test2 {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n;
		double result = 1000000;
		int index=0;
		n=sc.nextInt();
		for(int i = 0;i<n;i++){
			int fir=0; 
			int sec;
			double tmp=0;
			char opt;
			fir=sc.nextInt();
			opt=sc.next().charAt(0);
			sec=sc.nextInt();
			if(opt == '*') {
				tmp = fir * sec;
			} else if(opt == '+') {
				tmp = fir + sec;
			} else if(opt == '-') {
				tmp = fir - sec;
			} else if(opt == '/') {
				tmp = (double)fir / (double)sec;
			}
			if(Math.abs(tmp - 9) < Math.abs(result - 9)) {
				result = tmp;
				index = i + 1;
			}
		}
		System.out.println(index);
	}

}
/*
int main (){
	int n;
	double result = 1000000;
	int index;
	cin>>n;
	for(int i = 0;i<n;i++){
		int fir, sec;
		double tmp;
		char opt;
		cin>>fir>>opt>>sec;
		if(opt == '*') {
			tmp = fir * sec;
		} else if(opt == '+') {
			tmp = fir + sec;
		} else if(opt == '-') {
			tmp = fir - sec;
		} else if(opt == '/') {
			tmp = (double)fir / (double)sec;
		}
		if(fabs(tmp - 9) < fabs(result - 9)) {
			result = tmp;
			index = i + 1;
		}
	}
	cout<<index<<endl;
	system("pause");
}


 */
