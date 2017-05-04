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
  		function  update(){
  			if(validator.form()){
  				$.ajax({  
                    type : "POST",  //提交方式  
                    url : "algorithm/update.do",//路径  
                    data : $("#updateForm").serialize(),//数据，这里使用的是Json格式进行传输  
                    success : function(result) {//返回数据根据结果进行相应的处理  
                        if ( result.success ) { 
                        	parent.$("#updateKeypair").dialog("close");//parent 关闭父页面的dialog 
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
	<div class="widget-box">
		<form action="algorithm/update.do" id="updateForm" method="post" class="form-horizontal"  novalidate="novalidate">
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
               <input type="button" value="保存" onclick="update()" class="btn btn-primary">
            </div>
			</form>
		</div>
		 </body>
</html>
