package com.test.json.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TestRsync {
	public static void main(String[] args) {
		String test="Wikipedia";
		String line="taohuiissoman";
		String line2="itaohuiamsoman";
		ArrayList<Long> list=new ArrayList<Long>();
		Map hm=new HashMap<Long, ArrayList<String>>();
		for(int i=0;i<line.length()/4;i++){
			String temp=line.substring(i*4,i*4+3);
			long l=adler32(temp.getBytes());
			ArrayList<String> list2=null;
			if(hm.containsKey(l)){
				list2=(ArrayList<String>) hm.get(l);
			}else{
				list2=new ArrayList<String>();
			}
			list2=new ArrayList<String>();
			list.add(l);

		}
		String test1="Wiki";
		String test2="ikip";
		System.out.println(adler32(test.getBytes()));
		long csum=adler32(test1.getBytes());
		byte c1='W';
		byte c2='p';
		long csum3=adler32(test2.getBytes());
		long csum4=adler32_rolling_checksum(csum,test2.length(),c1,c2);
		//long csum2=checkSum_Adler32(test2.getBytes(),4);
		//System.out.println(csum2);
		System.out.println(csum3);
		System.out.println(csum4);
	}
	public static long adler32(byte[] data){
		long a = 0, b = 0;
		int MOD_ADLER = 65521;
	    /* Process each byte of the data in order */
	    for (int index = 0; index < data.length; ++index)
	    {
	        a = (a + data[index]) % MOD_ADLER;
	        //System.out.println(a);
	        b = (b + a) % MOD_ADLER;
	        //System.out.println(b);
	    }
	 
	    return (b << 16) | a;
	}
	public static long adler32_rolling_checksum(long csum, int len, byte c1,  
            byte c2) {  
        long s1, s2; 
        int MOD_ADLER = 65521;
        s1 = (csum & 0xffff);  
        s2 = (csum >> 16);
        //System.out.println(s1);
        //System.out.println(s2);
        s1 -= (c1 - c2);  
        s2 -= (len * c1 - s1); 
        s1=s1%MOD_ADLER;
        s2=s2%MOD_ADLER;
        return (s1 & 0xffff) + ((s2 & 0xffff) << 16);  
    }
	public static long checkSum_Adler32(byte[] buf, int len) {  
        int i;  
        long s1, s2;  
        // for (i = 0; i < len ; i++) {  
        // if (buf[i] < 0) {  
        // buf[i] = (byte) -buf[i];  
        // }  
        // }  
        s1 = s2 = 0;  
        for (i = 0; i < (len - 4); i += 4) {  
            s2 += 4 * (s1 + buf[i]) + 3 * buf[i + 1] + 2 * buf[i + 2]  
                    + buf[i + 3];  
            s1 += (buf[i + 0] + buf[i + 1] + buf[i + 2] + buf[i + 3]);  
        }  
        for (; i < len; i++) {  
            s1 += (buf[i]);  
            s2 += s1;  
        }  
        return (s1 & 0xffff) + ((s2 & 0xffff) << 16);  
    }  

}
