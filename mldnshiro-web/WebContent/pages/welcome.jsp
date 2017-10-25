<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%
	request.setCharacterEncoding("UTF-8");
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
	String login_url = basePath + "login.action" ;
%>
<html>
<head>
<base href="<%=basePath%>" />
<title>WEB开发</title>
</head>
<body> 
<h1>用户ID：<shiro:principal/></h1>
<shiro:hasPermission name="news:add">
	<h1>具有news:add的处理权限</h1>
</shiro:hasPermission> 
<shiro:hasRole name="member">
	<h1>用户具有member的操作角色！</h1>
</shiro:hasRole> 

<h1>大爷来玩吧，强子陪你玩一晚上。你懂的。</h1>
</body>
</html>