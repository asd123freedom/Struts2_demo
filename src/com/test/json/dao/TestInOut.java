package com.test.json.dao;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestInOut {
	public static void main(String[] args) {
		String test="猜";
		byte[] b=test.getBytes();
		System.out.println(b.length);
		System.out.println(b[0] & 0xff);
		System.out.println(b[1] & 0xff);
	}

}
