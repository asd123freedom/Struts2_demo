<%@ page language="java" import="java.util.*" pageEncoding="utf-8" contentType="text/html"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>登陆页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="script/jquery-1.3.2.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
				$("#loginBn").click(function(event){
					
	 			 $.post("Test",$("#loginForm").serializeArray(),
	 			 	function(data){
	   				 $("#show").text(data);  				 	
	  				},"html");
			});
		});		
		</script>
  </head>
  
  <body>
   <h3>用户登录</h3>
   <!--  
   		<table>
   		<tr align="center">
   			<td align="right">用户名:<input type="text" name="username"/></td>
   		</tr>
   		<tr align="center">
   		    <td align="right">密码:<input type="password" name="password"/></td>
   		</tr>
   		<tr align="center">
   		<td colspan="2">
   		<input type="reset" value="重置"/>
   		</td>
   		</tr>
   		</table>
   		<div id="test">Test</div>
   		<div id="test01">Let's go</div>
   <input type="button" value="提交" id="b01"/>
   -->
   <s:form id="loginForm">
   	<s:textfield name="user" label="用户名" />
   	<s:textfield name="pass" label="密码" />
   		<tr><td colspan="2">
   			<input id="loginBn" type="button" value="提交">
   		</td></tr>
	</s:form>
	<div id="show""></div>
  </body>
</html>
