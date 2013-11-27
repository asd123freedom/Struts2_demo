;$(function(){
	var Rsync={};
	Rsync=(function(){
		var block=function(adler32,MD5){
			this.type=1;
			this.adler32=adler32;
			this.MD5=MD5;
		}
		var commit=function(){
			this.type=0;
			this.content="";
			this.start=0;
			this.end=0;
		}
		var send_blocks=function(str,size,list){
				var list=list;
				var size=size;
				if(size==0 || size==null){
					size=20;
				}
				var b="itaohuiamsoman";
				if(str!=null && str!=""){
					b=str;
				}
				//console.log('left:'+left);
				var checksum=0;
				var start=0;
				var end=0;
				var list2=[];
				for(var i=0;i<b.length-size;i++){
					var str="";
					for(var j=0;j<size;j++){
						str+=b[i+j];
					}
					//str=str+(b[i])+(b[i+1])+(b[i+2])+(b[i+3]);
					if(i==0){
						checksum=adler32(str);
					}else{
						checksum=adler32_rolling_checksum(checksum,str.length,b[i-1],b[i+size-1]);
					}
					if(contain(list,checksum)){
						console.log("find:"+str);
						end=i;
						console.log("need to send:"+b.substring(start,end));
						var add=b.substring(start,end);
						var a=null;
						if(add!=""){
							a=new commit();
							a.content=add;
							a.start=start;
							a.end=end-1;
						}
						var b=new block();
						b.adler32=checksum;
						console.log(add);
						var find=checksum;
						if(a!=null){
							list2.push(a);
						}
						list2.push(b);
						start=i+size;
						left2=b.substr(start);
					}
				}
				console.log(left2);
				console.log(list2);
				return {'info':list2,'left':Left2};
		};
		var test=function(){
				var line="taohuiissoman";
				var b="itaohuiamsoman";
				var list=[];
				var hm={};
				for(var i=0;i<Math.floor(line.length/4);i++){
					var temp=line.substring(i*4,i*4+4);
					//console.log("slice"+i+":"+temp);
					var l=adler32(temp);
					console.log(l);
					var list2=[];
					if(hm[l]!=null){
						hm[l].push(temp);
					}else{
						hm.l=[];
						hm.l.push(temp);
					}
					list.push(l);
				}
				var left=line.substr(Math.floor(line.length/4)*4);
				//console.log('left:'+left);
				var checksum=0;
				var start=0;
				var end=0;
				var list2=[];
				for(var i=0;i<b.length-3;i++){
					var str="";
					str=str+(b[i])+(b[i+1])+(b[i+2])+(b[i+3]);
					if(i==0){
						checksum=adler32(str);
					}else{
						checksum=adler32_rolling_checksum(checksum,str.length,b[i-1],b[i+3]);
					}
					if(contain(list,checksum)){
						console.log("find:"+str);
						end=i;
						console.log("need to send:"+b.substring(start,end));
						var add=b.substring(start,end);
						list2.push(add);
						list2.push(checksum);
						start=i+4;
						left2=b.substr(start);
					}
				}
				console.log(left2);
				//console.log(list2);
				//return {'info':list2,'left':Left2};
				return list2;
			};
		var contain=function (arr,num){
						//console.log(arr);
						//console.log(num);
						var flag=false;
						for(var i=0;i<arr.length;i++){
							if(arr[i]===num){
								flag=true;
								break;
							}
						}
						return flag;
					};
		var adler32=function (data){
						var MOD_ADLER=65521;
						var a=0;
						var b=0;
						for(var index=0;index < data.length;++index){
							a=(a + data.charCodeAt(index)) % MOD_ADLER;
							//console.log((data.charCodeAt(index)));
							b=(b + a) % MOD_ADLER;
						}
						return (b << 16) | a;
					};
		var adler32_rolling_checksum=function (sum,len,c1,c2){
						var MOD_ADLER=65521;
						var s1=0;
						var s2=0;
						c1=c1.charCodeAt(0);
						c2=c2.charCodeAt(0);
						s1=(sum & 0xffff);
						s2=(sum >> 16);
						s1-=(c1-c2);
						s2-=(len*c1-s1);
						s1=s1%MOD_ADLER;
						s2=s2%MOD_ADLER;
						return (s1 & 0xffff) + ((s2 & 0xffff) << 16);
					};
		return {'test':test,'adler32':adler32,'rolling_checksum':adler32_rolling_checksum};
	})();
	$(".navbar").data("test",Rsync);
});