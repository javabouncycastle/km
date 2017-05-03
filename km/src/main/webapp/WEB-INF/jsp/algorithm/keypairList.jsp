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
  //修改密钥算法
   $(function(){
	   $("#updateKeypair").dialog({
		   autoOpen: false,
		   modal:true,
		   height:600,
		   width:1000,
		   buttons: {
			   "确定": function () {
		           $(this).dialog("close");
			   },
			   "取消": function () {
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
	   $("#iframe2").attr("src","forward.do?page=algorithm/keypairUpdate");
   }
   function updateDialog(){
        var ids=[];//先生成一个数组，放选中的id
       	//jquery选择器， $(".keyPairCheckbox:checked") 
       	//意思是获取所有class 是keyPairCheckbox 并且选中的checkbox
       	$(".keypairAlgCheckbox:checked").each(function(){//循环所有的选中的checkbox，把id放进数组
       		ids.push(this.value);//把id放进数组
       	});
       	if(ids.length==0){//如果没有勾选
       		alert("请至少选择一条");
       		return false;
       	}
       	if(ids.length>1){//如果勾选了多条
       		alert("只能选择一条信息修改");
       		return false;
       	}
        var url="algorithm/findById.do?id="+ids[0];//拼装后台请求路径，给iframe的src赋值
        $("#dialogIframe").attr("src",url);//给iframe赋值路径，跳转到更新的页面
        $("#updateKeypair").dialog("open");
	    
   }
   //新增密钥算法
    $(function(){
	   $("#insertKeypair").dialog({
		   autoOpen: false,
		   modal:true,
		   height:600,
		   width:1000,
		   buttons: {
			   "确定": function () {
		           $(this).dialog("close");
			   },
			   "取消": function () {
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
	   $("#iframe2").attr("src","forward.do?page=algorithm/keypairInsert");
   }
   function insertDialog(){
        var ids=[];//先生成一个数组，放选中的id
       	//jquery选择器， $(".keyPairCheckbox:checked") 
       	//意思是获取所有class 是keyPairCheckbox 并且选中的checkbox
       	$(".keypairAlgCheckbox:checked").each(function(){//循环所有的选中的checkbox，把id放进数组
       		ids.push(this.value);//把id放进数组
       	});
       	if(ids.length==0){//如果没有勾选
       		alert("请至少选择一条");
       		return false;
       	}
        var url="algorithm/findById.do?id="+ids[0];//拼装后台请求路径，给iframe的src赋值
        $("#dialogIframe").attr("src",url);//给iframe赋值路径，跳转到更新的页面
        $("#insertKeypair").dialog("open");
	    
   }
</script> 
  
  <body>
  		<div id="content">
  			<div class="widget-box">
							<div class="widget-title">
								<span class="icon">
									<input type="checkbox" id="title-checkbox" name="title-checkbox" />
								</span>
								<button onclick="" class="btn btn-inverse">全选</button>
								<button onclick="insertDialog()" class="btn btn-inverse">增加密钥算法</button>
								<button onclick="updateDialog()" class="btn btn-inverse">更新密钥算法</button>
								<button onclick="deleteDialog()" class="btn btn-inverse">删除密钥算法</button>
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
												<tr onclick="check()">
												    <td><input type="checkbox" class="keypairAlgCheckbox" value="${keypairAlgorithm.id}"/></td>
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
  		<div id="updateKeypair" style="overflow:hidden" title="更新密钥算法" > <!-- 不要滚动条 -->
			<iframe id="dialogIframe" style="width:100%;height:100%" src=""></iframe>
		</div>
		
		<!-- 弹出窗口 ,修改密钥算法内容-->
		<div id="insertKeypair" title="增加密钥算法">
			<iframe id="dialogIframe2" style="width:100%;height:100%" src="forward.do?page=algorithm/keypairInsert"></iframe>
		</div>
		
  </body>
</html>
