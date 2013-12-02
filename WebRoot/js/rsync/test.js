		var test=function(){
				var line="taohuiissoman";
				//var b="itaohuiamsoman";
				var b="taohuiissoman";
				var list=[];
				var list_md5=[];
				var hm={};
				for(var i=0;i<Math.floor(line.length/4);i++){
					var temp=line.substring(i*4,i*4+4);
					//console.log("slice"+i+":"+temp);
					var l=adler32(temp);
					var md5_value=md5(temp);
					console.log(l);
					var list2=[];
					if(hm[l]!=null){
						hm[l].push(temp);
					}else{
						hm.l=[];
						hm.l.push(temp);
					}
					list.push(l);
					list_md5.push(md5_value);
				}
				var left=line.substr(Math.floor(line.length/4)*4);
				console.log(list_md5);
				//console.log('left:'+left);
				var checksum=0;
				var start=0;
				var end=0;
				var list2=[];
				var left2="";
				for(var i=0;i<b.length-3;i++){
					var str="";
					str=str+(b[i])+(b[i+1])+(b[i+2])+(b[i+3]);
					if(i==0){
						checksum=adler32(str);
					}else{
						checksum=adler32_rolling_checksum(checksum,str.length,b[i-1],b[i+3]);
					}
					var value_md5=md5(str);
					if(contain(list,list_md5,checksum,value_md5)){
						console.log("find:"+str);
						end=i;
						if(end!=start){
							console.log(end);
							console.log(start)
							console.log("need to send:"+b.substring(start,end));
							var add=b.substring(start,end);
							list2.push(add);
						}
						list2.push(checksum);
						start=i+4;
						left2=b.substr(start);
					}
				}
				console.log(b.length)
				console.log(left2);
				//console.log(list2);
				//return {'info':list2,'left':Left2};
				return list2;
			};