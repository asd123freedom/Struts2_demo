;(function() {
	
	window.jsPlumbDemo = {
			
		init : function() {
			
			jsPlumb.importDefaults({
				Connector : "Straight",
				PaintStyle:{ lineWidth:3, strokeStyle:"#ffa500", "dashstyle":"2 4" },
				Endpoint:[ "Dot", { radius:5 } ],
				ConnectionsDetachable:false,
				EndpointStyle:{ fillStyle:"#000" },
				Anchors:[ "LeftMiddle","RightMiddle"]
			});
			// NOTE here we are just using getSelector so we don't have to rewrite the code for each of the supported libraries.
			// you can just use the approriate selector from the library you're using, if you want to. like $(".shape) on jquery, for example.
			//var shapes = jsPlumb.getSelector(".tool");
			// make everything draggable
			var stateMachineConnector = {				
				connector:"Straight",
				paintStyle:{lineWidth:3,strokeStyle:"#056"},
				hoverPaintStyle:{strokeStyle:"#dbe300"},
				endpoint:"Blank",
				anchor:"Continuous",
				overlays:[ ["PlainArrow", {location:1,width:10, length:12} ],
					[ "Label", { cssClass:"l1 component label",label:"foo", location:0.5 }, {id:"myLabel"} ]
					]
			};
			var shapes=$(".icon");
			var toolBar=$("#test");
			toolBar.data("option",stateMachineConnector);
//			jsPlumb.draggable(shapes,{helper:"clone"});
//			shapes.each(function(index,el){
//				 console.log(el);
//				 $(el).bind("click",function(){
//				 $(this).parent().find(".icon").removeClass("selected");
//				 $(this).addClass("selected");
//				 alert("");
//				 if(come==""){
//					come=$(this).attr("id");
//				 }
//				 else{
//					go=$(this).attr("id");
//					jsPlumb.connect({
//						source:come,
//						target:go,
//					},stateMachineConnector);
//					come="";
//					go="";
//				 }
//			 });
//			});

			
//			jsPlumb.connect({
//				source:"element1",
//				target:"element2",
//			},stateMachineConnector);
//			 $("#element1").bind("click",function(){
//				 if(come==""){
//					come=$(this).attr("id");
//				 }
//				 else{
//					go=$(this).attr("id");
//					jsPlumb.connect({
//						source:come,
//						target:go,
//					},stateMachineConnector);
//					come="";
//					go="";
//				 }
//			 });
//			 
//			 $("#element2").bind("click",function(){
//				 if(come==""){
//					come=$(this).attr("id");
//				 }
//				 else{
//					go=$(this).attr("id");
//					jsPlumb.connect({
//						source:come,
//						target:go,
//					},stateMachineConnector);
//					come="";
//					go="";
//				 }
//			 });
				
			// loop through them and connect each one to each other one.
    }    
  }
  
})();