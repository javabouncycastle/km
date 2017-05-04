<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>确信身份认证系统</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%@include file="/common/common.jsp" %>

 					 </head>
  
  						<body>
							<div class="widget-box">
								<form action="algorithm/update.do" method="post" class="form-horizontal"  onsubmit="window.opener=null;window.close();">
									<div class="control-group">
										<label class="control-label">id</label>
										<div class="controls">
											<input type="text" name="id"  value="${keypairAlgorithm.id}"/>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">别名</label>
										<div class="controls">
											<input type="text" name="name"  value="${keypairAlgorithm.name}"/>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">算法OID</label>
										<div class="controls">
											<input type="text" name="algorithmOid" value="${keypairAlgorithm.algorithmOid}"/>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">算法英文缩写</label>
										<div class="controls">
											<input type="text" name="algorithmName" value="${keypairAlgorithm.algorithmName}"/>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">密钥长度</label>
										<div class="controls">
											<input type="text" name="keysize" value="${keypairAlgorithm.keysize}"/>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">备注</label>
										<div class="controls">
											<textarea name="notes"></textarea>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">是否有效</label>
										<div class="controls">
										<div class="control-group">
											<select name="isValid">
												<option value="1">是</option>
												<option value="0">否</option>
											</select>
										</div>
										</div>
									</div>
									
									<div class="form-actions" >
                                        <input type="submit" value="保存" class="btn btn-primary">
                                    </div>
								</form>
							</div>
 						 </body>
					</html>
