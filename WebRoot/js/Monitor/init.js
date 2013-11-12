;$(function(window,undefined){
	  		$(".mybtn").bind('click',function(){			
  			//$.globalMessenger().post("Your request has succeded!");
  				$('#myModal').modal({
  					keyboard: false,
  					backdrop: true,
				});
  			});
	  		$(".setup").bind('click',function(){			
  			//$.globalMessenger().post("Your request has succeded!");
  				$('#setCode').modal({
  					keyboard: true,
  					backdrop: true,
				});
  				$('#setCode .modal-body').load("setup.html");
  			});
	  		//view Modules
	  		var view=(function(){
	  			var status_show=function(data){
	  				$(".status").each(function(index,e){
	  					$(e).data("count",0);
						//var str=$(e).text();
						//str=str.replace(/\d+/,$(e).data("count"));
						//$(e).text(str);
					});	
	  				for(var i=0;i<data.length;i++){
	  					if(data[i].status.toLocaleLowerCase()=='ok'){
							var temp=$(".ok").data("count");
							//temp++;
							$(".ok").data("count",++temp);
						}else if(data[i].status.toLocaleLowerCase()=='warning'){
							var temp=$(".warning").data("count");
							temp++;
							$(".warning").data("count",temp);
						}else if(data[i].status.toLocaleLowerCase()=='critical'){
							var temp=$(".critical").data("count");
							temp++;
							$(".critical").data("count",temp);
						}else{
							var temp=$(".other").data("count");
							temp++;
							$(".other").data("count",temp);
						}			
	  				}
	  				$(".status").each(function(index,e){
						var str=$(e).text();
						str=str.replace(/\d+/,$(e).data("count"));
						$(e).text(str);
					});	
	  			};
	  			var table_show = function (data){
	  				//alert("调用");
	  				//console.log(data);
					$table=$('.table');
					$table.find('tr.temple ~ *').remove();
					var $host_name=$table.data("host_name");
					//console.log($host_name);
					var index=0;
					for (var i = 0; i < data.length; i++) {
						var $line=$table.find('tr.temple').clone();
						//console.log(data[i].host_name);
						if(data[i].host_name!=$host_name){
							continue;
						}else{
							index++;
						}
						$line.removeClass('hide temple')
					 		.find('.id').text(index+"").end()
					 		.find('.name').text(data[i].service_description).end()
					 		.find('.status_info').text(data[i].status).end()
					 		.find('.time').text(data[i].last_check).end()
					 		.find('.desc').text(data[i].status_information).end().appendTo($table);
						var height=$line.css("height");
//						if(data[i].status.toLocaleLowerCase()=='ok'){
//							var temp=$(".ok").data("count");
//							//temp++;
//							$(".ok").data("count",++temp);
//						}else if(data[i].status.toLocaleLowerCase()=='warning'){
//							var temp=$(".warning").data("count");
//							temp++;
//							$(".warning").data("count",temp);
//						}else if(data[i].status.toLocaleLowerCase()=='critical'){
//							var temp=$(".critical").data("count");
//							temp++;
//							$(".critical").data("count",temp);
//						}else{
//							var temp=$(".other").data("count");
//							temp++;
//							$(".other").data("count",temp);
//						}			
					}
					var margin=$(".pagination").css("margin-top");
					margin=margin.match(/\d+/);
					//margin=margin+height*(10-data.length);
					margin=margin[0];
					height=height.match(/\d+/);
					height=height[0];
					height=parseInt(height)*(10-data.length);
					//console.log(height);
					margin=parseInt(margin)+height;
//					console.log(margin);
					//$(".pagination").css("margin-top",margin+"px");
//					for(var i=data.length;i<10;i++){
//						var $line=$table.find('tr.temple').clone();
//						$line.removeClass('hide temple')
//					 		.find('.id').text("1").end()
//					 		.find('.name').text("1").end()
//					 		.find('.status_info').text("1").end()
//					 		.find('.time').text("1").end()
//					 		.find('.desc').text("1").end().appendTo($table);
//						var color=$line.find('.id').css("color");
//						console.log($line.find('.id').css("color"));
//					}
//					$(".status").each(function(index,e){
//						var str=$(e).text();
//						str=str.replace(/\d+/,$(e).data("count"));
//						$(e).text(str);
//					});				
				};
				var pages=function(data){
						$(".pagination").find("li.num").remove();
						//console.log(data);
		  				var $strip=$(".pagination");
		  				$table=$('.table');
		  				var $host_name=$table.data("host_name");
		  				for(var i=0;i<data.length;i++){
		  					if(data[i].host_name!=$host_name){
								continue;
								alert("");
							}
		  				}
		  				var n=parseInt(data.length/11);
		  				//console.log(n);		  				
		  				for(var i = 0; i <= n; i++){		
		  					if(i!=0){
		  						var $li=$(".pagination").find("li.first").clone();
								$li.removeClass("temple").addClass("num");
								$li.find("a").text(i+1+"").end().insertBefore($(".ra"));
		  					}
		  				}
		  				var $li=$(".pagination").find("li:not('li.next')");
		  						$li.bind("click",function(){
		  							//if($(".table").find(".error"))
		  							$(this).siblings().removeClass("active");
		  							$(this).addClass("active");
									var $data=$(this).data("data");
									table_show($data);
									var $e=$(".table").data("status");
									if($e!=null){
										$e.trigger("click");
									}
						});
		  				for(var i=0;i<=n;i++){
		  					var array=[];
		  					for(var j=i*10;j<i*10+10 && j<data.length;j++){
		  						array.push(data[j]);
		  					}
		  					var li=$(".pagination").find("li:not('li.next')")[i];
		  					//console.log(array);
		  					$(li).data("data",array);
		  				}
	  			};
　　　　			var _nav = function(){
　　　　					$.ajax({
						url: "http://127.0.0.1:8080/struts2_demo/Monitor.action",
         				type: "POST",
         				data: {url:"http://192.168.0.197:8888/icinga/cgi-bin/status.cgi?style=hostdetail&jsonoutput"},
         				success:function(data){
           					var M_data=eval("("+data.data+")");
           					//console.log(M_data);
           					$("body").data("data",M_data);
           					$mynav=$(".mynav");
           					$mynav.find("li.temple ~ *").remove();
           					data=M_data.status.host_status;
           					//console.log(data);
           					for(var i=0;i<data.length;i++){           						
           						$li=$(".mynav").find("li.temple").clone();
           						var $i=$("li.temple").find("i").clone();
           						$i.addClass("icon-chevron-right my");
           						$li.removeClass("hide temple").find("a").text(data[i].host_name);
           						//$li.find("a").attr("data-toggle","tooltip").attr("data-original-title","click for more infotmation");
           						$i.appendTo($li.find("a"));
           						//$li.data("original-title","Default tooltip");
           						//$li.find("a").tooltip();
           						
           						$li.bind("click",function(){
	  								$(this).siblings().removeClass('active');
  									//$(".mynav").find("li").removeClass("active");
  									$(this).siblings().find("i").removeClass("icon-white");
  									$(this).addClass("active");
  									$(this).find("i").addClass("icon-white");  			
  									$(".table").data("host_name",$(this).find("a").text());
  									var data=$(this).data("data");
  									//console.log(data);
  									//show(data);
  									//$(".refresh").trigger('click');
  									status_show(data);
  									_table(data);
  									var li=$(".pagination").find("li:not('li.next')")[0];
  									
  									$(li).trigger("click");
           						});
           						$li.insertBefore("li.temple");
           					}
           					$(".refresh").trigger('click');
           					//var temp=$(".mynav").find("li")[0];
           					//$(temp).trigger("click");
           					//$mynav.find("li.temple").remove();
         				}, 
				});};
　　　　			var _table = function(data){　　　　				           					
           					$(".status").each(function(index,e){
								$(e).data("count",0);
							});
           					//var M_data=$(".mynav").find("li.active").data("data");
           					var M_data=data;
           					pages(M_data);
           					var temp=$(".pagination").find("li.num")[0];
           					$(temp).trigger("click");
           					var info=$(".critical").data("count")+"";
           					var num=info.match(/\d+/);
           					$._messengerDefaults = {
								extraClasses: 'messenger-fixed messenger-theme-future messenger-on-bottom messenger-on-right'
							};
           					if(num>0){
           						var msg;
           						if(num==1){
           							msg="A service is in critical situation."
           						}
           						else{
           							msg="Some services are in critical situation."
           						}
           						//$.globalMessenger().post({
  								//		message: msg,
  								//		type: 'error',
  								//		showCloseButton: true,
								//});
           						var a=function(){
           							$.globalMessenger().post({
  										message: msg,
  										type: 'error',
  										showCloseButton: true,
									});
           						};
           						var t=setTimeout(a,2000);
           					}　　　　				
//　　　　				$.ajax({
//						url: "http://127.0.0.1:8080/struts2_demo/Monitor.action",
//         				type: "POST",
//         				data: {url:"http://192.168.0.197:8888/icinga/cgi-bin/status.cgi?jsonoutput"},
//         				success:
//         					function(data){
//           					var M_data=eval("("+data.data+")");
//           					$(".status").each(function(index,e){
//								$(e).data("count",0);
//							});
//           					//show(M_data.status.service_status);
//           					//pages(M_data.status.service_status);
//           					var temp=$(".pagination").find("li.num")[0];
//           					$(temp).trigger("click");
//           					var info=$(".critical").data("count")+"";
//           					var num=info.match(/\d+/);
//           					$._messengerDefaults = {
//								extraClasses: 'messenger-fixed messenger-theme-future messenger-on-bottom messenger-on-right'
//							};
//           					if(num>0){
//           						var msg;
//           						if(num==1){
//           							msg="A service is in critical situation."
//           						}
//           						else{
//           							msg="Some services are in critical situation."
//           						}
//           						//$.globalMessenger().post({
//  								//		message: msg,
//  								//		type: 'error',
//  								//		showCloseButton: true,
//								//});
//           						var a=function(){
//           							$.globalMessenger().post({
//  										message: msg,
//  										type: 'error',
//  										showCloseButton: true,
//									});
//           						};
//           						var t=setTimeout(a,2000);
//           					}
//           					$(".refresh").button("reset");
//         				}, 
//				});
　　　　			};

　　　　			return {
　　　　　　			_nav : _nav,
　　　　　　			_table : _table,
　　　　			};	  			
	  		})();
	  		$("body").data("view",view);
	  		$(".mynav").find("li").bind('click',function(){
	  			//alert("");
	  			//$(this).siblings().removeClass('active');
  				//$(".mynav").find("li").removeClass("active");
  				//$(".mynav").find("i").removeClass("icon-white");
  				//$(this).addClass("active");
  				//$(this).find("i").addClass("icon-white");  			
  				//$table.data("host_name",$(this).find("a").text());
  			});
});