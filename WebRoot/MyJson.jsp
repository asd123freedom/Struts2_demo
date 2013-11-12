<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'MyJson.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  <script type="text/javascript" src="js/jquery-1.9.1.js"></script>
  <script type="text/javascript">
  $(document).ready(function(){
  	 $("#getMessage").click(function(){ 
        $.getJSON("jsontest!returnMessage.action",function(data){ 

            //通过.操作符可以从data.message中获得Action中message的值 
			
            $("#message").html("<font color='red'>"+data.name+"</font>"); 

        }); 
    }); 	
    //为获取UserInfo对象按钮添加鼠标单击事件 

    $("input#getUserInfo").click(function(){ 
        $.getJSON("jsontest!returnUserInfo.action",function(data){ 

            //清空显示层中的数据 

            $("#message").html(""); 

            //为显示层添加获取到的数据 

            //获取对象的数据用data.userInfo.属性 

            $("#message").append("<div><font color='red'>用户名："+data.userInfo.name+"</font></div>") 
                         .append("<div><font color='red'>密码："+data.userInfo.password+"</font></div>") 

        }); 

    }); 

    //为获取List对象按钮添加鼠标单击事件 

    $("#getList").click(function(){ 
		alert("asd");
        $.getJSON("jsontest!returnList.action",function(data){ 

            //清空显示层中的数据 

            $("#message").html(""); 

            //使用jQuery中的each(data,function(){});函数 

            //从data.userInfosList获取UserInfo对象放入value之中 

            $.each(data.userInfosList,function(i,value){ 

                $("#message").append("<div>第"+(i+1)+"个用户：</div>") 

                         .append("<div><font color='red'>用户名："+value.name+"</font></div>") 

                         .append("<div><font color='red'>密码："+value.password+"</font></div>"); 

            }); 

        }); 

    }); 

    //为获取Map对象按钮添加鼠标单击事件 

    $("#getMap").click(function(){ 

        $.getJSON("jsontest!returnMap.action",function(data){ 

            //清空显示层中的数据 

            $("#message").html(""); 

            //使用jQuery中的each(data,function(){});函数 

            //从data.userInfosList获取UserInfo对象放入value之中 

            //key值为Map的键值 

            $.each(data.userInfosMap,function(key,value){ 

                $("#message")
                         .append("<div><font color='red'>用户名："+value.name+"</font></div>") 

                         .append("<div><font color='red'>密码："+value.password+"</font></div>"); 

            }); 
        }); 
    }); 
     $("#regRe").click(function(){ 

        //把表单的数据进行序列化 
        var params = $("form").serialize(); 
		console.log(params);
		params={'userInfo.name':'chujin','userInfo.password':'aass'};
        //使用jQuery中的$.ajax({});Ajax方法 

        $.ajax({ 

            url:"jsontest!gainUserInfo.action", 

            type:"POST", 

            data:params, 

            dataType:"json", 

            success:function(data){
            console.log(data); 

                //清空显示层中的数据 

            $("#message").html(""); 

            //为显示层添加获取到的数据 

            //获取对象的数据用data.userInfo.属性 

            $("#message").append("<div><font color='red'>用户名："+data.userInfo.name+"</font></div>") 

                         .append("<div><font color='red'>密码："+data.userInfo.password+"</font></div>") 

            } 

        }); 

    }); 

});
  </script>
  <body>
    <input id="getMessage" type="button" value="获取单个值"/>     
    <input id="getUserInfo" type="button" value="获取UserInfo对象"/>   
    <input id="getList" type="button" value="获取List对象"/>   
    <input id="getMap" type="button" value="获取Map对象"/>   
    <br> 
    <br> 
    <br>
    <div id="message"></div> 
    <form>
    <table>
    <tr> 
       <td><div>用户名：</div></td><td><input name="userInfo.name" type="text" style="float: left"/></td>
       </tr>
       <tr> 
       <td><div>密&nbsp;&nbsp;码：</div></td><td><input name="userInfo.password" type="text" style="float: left"/></td>
       </tr>
       </table> 
        <input id="regRe" type="button" value="注册"/> 
    </form>
    <s:debug/> 
  </body>
</html>
