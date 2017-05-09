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
  <script type="text/javascript">
   var validator;
  	$(function(){
  		validator=$("#updateForm").validate({
			rules:{
				name:{
					required: true
				},
				algorithmOid:{
					required:true
				},
				algorithmName:{
					required:true
				},
				keysize:{
					required:true
				}
			},
			errorClass: "help-inline",
			errorElement: "span",
			highlight:function(element, errorClass, validClass) {
				$(element).parents('.control-group').addClass('error');
			},
			unhighlight: function(element, errorClass, validClass) {
				$(element).parents('.control-group').removeClass('error');
				$(element).parents('.control-group').addClass('success');
			}
		});
  	});
 	function  insert(){
		if(validator.form()){
			$.ajax({  
                 type : "POST",  //提交方式  
                 url : "algorithm/insert.do",//路径  
                 data : $("#insertForm").serialize(),//数据，这里使用的是Json格式进行传输  
                 success : function(result) {//返回数据根据结果进行相应的处理  
                     if ( result.success ) { 
                     	parent.$("#insertKeypair").dialog("close");//parent 关闭父页面的dialog 
                         parent.alert("id为"+result.id+"更新成功");
                     } else { 
                        alert("保存失败！"); 
                     }  
                 }  
             });  
		}
 		}
  </script>
  
  <body>
  		<div class="container-fluid">
				<div class="row-fluid">
					<div class="span12">
						<div class="widget-box">
							<div class="widget-content nopadding">
								<form action="algorithm/insert.do" method="post" class="form-horizontal"  name="basic_validate" id="insertForm" novalidate="novalidate">
									<div class="control-group">
										<label class="control-label">别名</label>
										<div class="controls">
											<input type="text" name="name"/>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">算法OID</label>
										<div class="controls">
											<input type="text" name="algorithmOid"/>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">算法英文缩写</label>
										<div class="controls">
											<input type="text" name="algorithmName"/>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">密钥长度</label>
										<div class="controls">
											<input type="text" name="keysize"/>
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
											<select name="isValid">
												<option value="1">是</option>
												<option value="0">否</option>
											</select>
										</div>
									</div>
									<div class="form-actions">
									<button type="submit" value="Validate" class="btn btn-primary">保存</button>
									</div>
								</form>
							</div>
						</div>						
					</div>
				</div>
			</div>
  </body>
</html>
