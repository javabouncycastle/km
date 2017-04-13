<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>新增用户</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  <script type="text/javascript">
  
  </script>
  <body>
    <form action="user/insert.do" method="post">
       <input type="hidden" id="id" name="name"/>
       <table>
           <tr>
              <td>
                  	姓名：
              </td>
              <td>
                  <input id="name" name="name"/>
              </td>
           </tr>
           <tr>
              <td>
                                               年龄：
              </td>
              <td>
                  <input id="age" name="age"/>
              </td>
           </tr>
       </table>
       <input type="submit" value="save"/>
    </form>
  </body>
</html>
