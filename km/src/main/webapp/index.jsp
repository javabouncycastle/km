<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html lang="en">
	<head>
		<title>确信信息身份认证系统</title>
		<meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<%@include file="common/common.jsp" %>
	</head>
	<script type="text/javascript">
		function forward(pageName){
			$("#contentIframe").attr("src","forward.do?page="+pageName);
		}
	</script>
	<body>
		
		<div id="header">
			<h1></h1>		
		</div>
		
		<div id="sidebar">
			<ul>
					<li class="submenu">
						<a href="#"><i class="icon icon-th-list"></i> <span>密钥管理</span><span class="label">3</span> </a>
							<ul>
								<li><a onclick="forward('/keyManager/generateKey')">密钥生成</a></li>
								<li><a onclick="forward">密钥查询</a></li>
							</ul>
					<li class="submenu">
						<a href="#"><i class="icon icon-th-list"></i> <span>日志管理</span> <span class="label">3</span></a>
							<ul>
								<li><a href="form-common.html">Common elements</a></li>
								<li><a href="form-validation.html">Validation</a></li>
								<li><a href="form-wizard.html">Wizard</a></li>
							</ul>
					</li>
					<li class="submenu">
						<a href="#"><i class="icon icon-th-list"></i> <span>管理员管理</span> <span class="label">3</span></a>
							<ul>
								<li><a href="form-common.html">Common elements</a></li>
								<li><a href="form-validation.html">Validation</a></li>
								<li><a href="form-wizard.html">Wizard</a></li>
							</ul>
					</li>
		
		</div>
		
		
		<div id="content">
			<div id="content-header">
				<h1>确信信息</h1>
				<div class="btn-group">
				</div>
			</div>
			<div id="breadcrumb">
				<a href="#" title="返回首页" class="tip-bottom"><i class="icon-home"></i> 首页</a>
			</div>
			<iframe id="contentIframe" src=""></iframe>
			<div id="footer" class="span12">
				Copyright © 2016 suresec.net/ All Rights Reserved　山东确信信息产业股份有限公司　版权所有　鲁ICP备13015085号-4 </br>
				电话：400-006-8211 　 地址：济南市高新区舜华路2000号舜泰广场11号楼北区203室
			</div>
				
	  </div>

            
	</body>
</html>
