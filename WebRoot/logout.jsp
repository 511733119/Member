<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'logout.jsp' starting page</title>
  </head>
  
  <body>
 <%
 	session.removeAttribute("adminName");
 	request.getRequestDispatcher("/login.jsp").forward(request, response);
  %>   
  </body>
</html>
