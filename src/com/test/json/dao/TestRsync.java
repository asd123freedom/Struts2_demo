package com.test.json.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestRsync {
	public static void main(String[] args) {
		String test="Wikipedia";
		String line="taohuiissoman";
		String line2="itaohuiamsoman";
		ArrayList<Long> list=new ArrayList<Long>();
		Map hm=new HashMap<Long, ArrayList<String>>();
		for(int i=0;i<line.length()/4;i++){
			String temp=line.substring(i*4,i*4+4);
			System.out.println(temp);
			long l=adler32(temp.getBytes());
			System.out.println(l);
			ArrayList<String> list2=null;
			if(hm.containsKey(l)){
				list2=(ArrayList<String>) hm.get(l);
			}else{
				list2=new ArrayList<String>();
			}
			list2.add(temp);
			hm.put(l, list2);
			list.add(l);
		}
		String left=line.substring((line.length()/4)*4);
		System.out.println(left);
		byte[] b=line2.getBytes();
		long checksum=0;
		int start=0;
		int end=0;
		StringBuffer result=new StringBuffer("");
		String left2 = null;
		StringBuffer sb=null;
		List list2=new ArrayList<Object>();
		for(int i=0;i<b.length-3;i++){
			sb=new StringBuffer("");
			sb.append((char)b[i]);
			sb.append((char)b[i+1]);
			sb.append((char)b[i+2]);
			sb.append((char)b[i+3]);
			//System.out.println(sb.toString());
			if(i==0){
				checksum=adler32(sb.toString().getBytes());
			}else{
				checksum=adler32_rolling_checksum(checksum,sb.toString().length(),b[i-1],b[i+3]);
			}
			if(list.contains(checksum)){
				System.out.println("find:"+sb.toString());
				end=i;
				System.out.println("need to send:"+line2.substring(start,end));
				String add=line2.substring(start,end);
				Long find=Long.valueOf(checksum);
				list2.add(add);
				list2.add(find);
				result.append(add+find);
				start=i+4;
				left2=line2.substring(start);
			}
		}
		System.out.println(left2);
		result.append(left2);
		list2.add(left2);
		System.out.println(result.toString());
		assemble(hm,list2);
//		String test1="Wiki";
//		String test2="ikip";
//		System.out.println(adler32(test.getBytes()));
//		long csum=adler32(test1.getBytes());
//		byte c1='W';
//		byte c2='p';
//		long csum3=adler32(test2.getBytes());
//		long csum4=adler32_rolling_checksum(csum,test2.length(),c1,c2);
//		//long csum2=checkSum_Adler32(test2.getBytes(),4);
//		//System.out.println(csum2);
//		System.out.println(csum3);
//		System.out.println(csum4);
	}
	public static void assemble(Map map,List l){
		for(int i=0;i<l.size();i++){
			Object o=l.get(i);
			if(l.get(i) instanceof Long){
				Object o2=(map.get((Long)o));
				if(o2 instanceof ArrayList){
					String temp=(String) ((ArrayList)o2).get(0);
					System.out.println("已存在"+temp);
				}
			}else if(l.get(i) instanceof String){
				String temp=(String)o;
				System.out.println("要添加"+temp);
			}
		}
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
