<%@ page pageEncoding="UTF-8"%>
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
<h1>大爷来玩吧，强子陪你玩一晚上。你懂的。</h1>
</body>
</html>