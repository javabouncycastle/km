<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'keypairInsert.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<%@include file="/common/commonLeft.jsp" %>

  </head>
  
  <script type="text/javascript">
   $(function(){
	   $("#updateKeypair").dialog({
		   autoOpen: false,
		   width: 600,
		   buttons: {
			   "Ok": function () {
		           $(this).dialog("close");
			   },
			   "Cancel": function () {
				   $(this).dialog("close");
			   }
		   },
		   show: {
		       effect: "fade",
			   duration: 500
		   },
		   hide: {
			   effect: "fade",
			   duration: 500
		   } 
	   });
   });
   function showIframe(){
	   $("#iframe2").attr("src","forward.do?page=succ");
   }
   function showDialog(){
	   $("#updateKeypair").dialog("open");
   }
</script>
  
  <body>
  		<div id="content">
  			<div class="widget-box">
							<div class="widget-title">
								<span class="icon">
									<input type="checkbox" id="title-checkbox" name="title-checkbox" />
								</span>
								<h5>全选</h5>
								<button  onclick="showDialog()"  class="btn btn-inverse">更新密钥算法</button>
								<button onclick="testDialog()" class="btn btn-inverse">删除密钥算法</button>
							</div>
							<div class="widget-content nopadding">
								<table class="table table-bordered table-striped table-hover with-check">
									<thead>
										<tr>
											<th><i class="icon-resize-vertical"></i></th>
											<th>id</th>
											<th>别名</th>
											<th>算法OID</th>
											<th>算法英文缩写</th>
											<th>密钥长度</th>
											<th>备注</th>
 											<th>是否有效</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${keypairAlgorithms}" var="keypairAlgorithm" varStatus="count">
											<tr>
												<tr onclick="ckeck()">
												    <td><input type="checkbox" /></td>
												    <td>${keypairAlgorithm.id}</td>
													<td>${keypairAlgorithm.name}</td>
													<td>${keypairAlgorithm.algorithmOid}</td>
													<td>${keypairAlgorithm.algorithmName}</td>
													<td>${keypairAlgorithm.keysize}</td>
													<td>${keypairAlgorithm.notes}</td>
		 											<td>${keypairAlgorithm.isValid=='0'?'否':'是'}</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>							
							</div>
						</div>
  		</div>
  		
  		<!-- 弹出窗口 ,修改密钥算法内容-->
  		<div id="updateKeypair" title="更新密钥算法">
			<div class="modal-header">
				 <iframe id="dialogIframe" src="forward.do?page=succ"></iframe>
			</div>
		</div>
  		
  </body>
</html>
