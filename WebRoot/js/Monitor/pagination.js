;$(function(window,undefined){
	  var pages=function(data){
		  var $strip=$(".pagination");
		  for(var i = 1; i < data.length; i++){			  
			var $li=$(".pagination").find("li.num").clone();
			$li.find("a").text(i+1+"").end().appendTo($strip);
			
		  }
	  }
	
});