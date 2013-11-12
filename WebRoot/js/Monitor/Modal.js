;$(function(window,undefined){
	$("#myModal").data("res",false);
	$("#myModal").find(".return").bind("click",function(){
		$('#myModal').modal('hide');
		$("#myModal").data("res",false);
	});
	$("#myModal").find(".save").bind("click",function(){
		$(".process").button("loading");
		$.ajax({
			url: "http://127.0.0.1:8080/struts2_demo/Save.action",
         	type: "POST",
         	data: {url:"http://192.168.0.197:8888/icinga/cgi-bin/status.cgi?jsonoutput"},
         	success:function(data){
         		$.globalMessenger().post({
  										message: "Your operation has succeeded",
  										type: 'info',
  										showCloseButton: true,
				});
         		$(".process").button("reset");
         	},
		});
		$('#myModal').modal('hide');
		$("#myModal").data("res",true);
	});
});