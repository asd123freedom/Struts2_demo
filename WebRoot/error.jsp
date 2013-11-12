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
    
    <title>My JSP 'error.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    error <br>
    <s:if test="%{false}">
     	<div>will not be executed here 111</div>
    </s:if>
    <s:elseif test="%{true}">
        <div>will not be executed here 222</div>
    </s:elseif>
    <s:else>
    	<div>will not be executed here 333</div>
    </s:else>
    <%
    	request.setAttribute("name","Kobe");
    	request.getRequestDispatcher("/index.jsp").include(request,response);
     %>    
    <%
    	List<String> list=new ArrayList<String>();
    	list.add("Leonnn");
    	list.add("Johnn");
    	list.add("Peter");
    	list.add("Jeffnnnnnn");
    	request.setAttribute("names",list);
     %>
     <h3>Name:</h3>
     <ol> 
        <s:iterator value="#request.names" status="status">
        	<s:if test="#status.odd">
        		<li>奇数行:<s:property/></li>
        	</s:if>	
        	<s:else>
        		<li>偶数行:<s:property/></li>
        	</s:else>
        </s:iterator>
     </ol>
     <s:bean id="mycomparator" name="com.ascent.util.MyComparator"/>
     <table border="1" width="200">
     	<s:sort
     		source="#request.names" comparator="#mycomparator"
     	>
     	<s:iterator status="st">
     	 <tr<s:if test="#st.odd"> style="background-color:#bbbbbb"</s:if>>
     	 	<td><s:property/></td>
     	 </tr>
     	</s:iterator>
     	</s:sort>	      
     </table>
     <%
     	Date now=new Date();
     	pageContext.setAttribute("now",now);
      %>
      <s:date name="#attr.now" format="dd/MM/yyyy" nice="false"/><hr><br>
      <s:date name="#attr.now" format="dd/MM/yyyy" nice="true"/><hr><br>
       <s:date name="#attr.now"  nice="true"/><hr><br>
       <s:date name="#attr.now"  nice="false"/><hr><br>
      <hr>
      <h2>Test for Set</h2>
      <s:bean name="com.ascent.po.User" id="u">
      		<s:param name="username" value="'zhangsan'"/>
      		<s:param name="password" value="'123456'"/>
      </s:bean>
      <s:set value="#u" name="user" scope="request"/>
      <s:property value="#request.user.username"/><br>
       <s:property value="#request.user.password"/>
      <s:actionerror/>
      <s:form action="" name="form1">
      	<s:doubleselect
      			label="请选择您喜欢的图书"
      			name="author" list="{'John','Bill'}"
      			doubleList="top=='John'?{'aaa','bbb','ccc'}:
      			{'www','xxx','yyy'}"
      			doubleName="book"/>
      </s:form>
      <s:optiontransferselect
      		label="Favourite Cartoons Characters"
      		name="leftSideCartoonCharacters"
      		list="{'Popeye','He-man','Spiderman'}"
      		doubleName="rightSideCartoonCharacters"
      		doubleList="{'Superman','Mickey Mouse'}"
      />		 
     <s:debug></s:debug>  	
  </body>
</html>
