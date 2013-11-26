;$(function(){
	/*
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
	/*long a = 0, b = 0;
		int MOD_ADLER = 65521;
	    /* Process each byte of the data in order
	    for (int index = 0; index < data.length; ++index)
	    {
	        a = (a + data[index]) % MOD_ADLER;
	        //System.out.println(a);
	        b = (b + a) % MOD_ADLER;
	        //System.out.println(b);
	    }
	 
	    return (b << 16) | a;
    }
	*/
	function checkSum_Adler32(buf,len){
		var MOD_ADLER=65521;
		var a=0;
		var b=0;
		for(var index=0;index < data.length;++index){
			a=(a + buf[indx]) % MOD_ADLER;
			b=(b + a) % MOD_ADLER;
		}
		return (b << 16) | a;
	}
	function alder32_rolling_checksum(sum,len,c1,c2){
		var MOD_ADLER=65521;
		var s1=0;
		var s2=0;
		s1=(sum & 0xffff);
		s2=(sum >> 16);
		s1-=(c1-c2);
		s2-=(len*c1-s1);
		s1=s1%MOD_ADLER;
		s2=s2%MOD_ADLER;
		return (s1 & oxffff) + ((s2 & oxffff) << 16);
	}
});