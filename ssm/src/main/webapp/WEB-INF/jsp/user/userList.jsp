<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'userList.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

  </head>
  <script type="text/javascript">
   var basePath='<%=basePath%>';
    function deleteUser(id){
       var url=basePath+"user/remove.do?id="+id;
       window.location.href=url;
    }
  </script>
  <body>
     <div align="center">
     	<form action="" method="post" id="form">
     	</form>
        	<table>
				<tr>
					<td>
						操作：
					</td>
					<td>
						ID：
					</td>
					<td>
						姓名：
					</td>
					<td>
						年龄：
					</td>
				</tr>
				<c:forEach items="${Users}" var="user">
					<tr>
						<td>
							<a href="javascript:deleteUser(${user.id })">删除</a>
						</td>
						<td>
						   ${user.id }
						</td>
						<td>
						   ${user.name}
						</td>
						<td>
						   ${user.age }
						</td>
					</tr>
				</c:forEach>
        	</table>
     </div>
  </body>
</html>
