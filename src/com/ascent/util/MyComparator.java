package com.ascent.util;

import java.util.Comparator;
import java.util.Date;

public class MyComparator implements Comparator{

	@Override
	public int compare(Object o1, Object o2) {
		// TODO Auto-generated method stub
		return o1.toString().length()-o2.toString().length();
	}
	public static void main(String[] args) {
		Date now=new Date();
	}

}
