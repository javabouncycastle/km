<%@ page contentType="text/html;charset=utf8" pageEncoding="utf8" %>
<%@ taglib prefix="c" uri="/WEB-INF/c-1_0-rt.tld"%>
<!DOCTYPE html>
<%@ include file="../left.jsp" %>
<%@ include file="../footer.jsp" %>
<body>

        <div class="templatemo-content">
	          <ol class="breadcrumb">
	            <li><a href="<%=request.getContextPath()%>/main">主页面</a></li>
	            <li class="active">密钥管理</li>
	            <li><a href="../../sign-in.html">Sign In Form</a></li>
	             <li><a href="javascript:searchByCondition()"> 查询</a></li>
	          </ol>
        <div class="row">
            <div class="col-md-12">
            	 <c:if test="${success != null && success != ''}">               
	                   <div class="alert alert-success alert-dismissible" role="alert">
	                      <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
	                      <strong>${msg}</strong> 
	                    </div>
	             </c:if> 
              <div class="table-responsive">
                <h4 class="margin-bottom-15">密钥列表</h4>
                <!-- 查询条件div -->
                 <div class="row" id="searchCondition" style="display:none">
		            <div class="col-md-12">
		              <form id="templatemo-preferences-form" action="searchByCondition.do" method="post" >
		                <div class="row">
	                	  <div class="col-md-6 margin-bottom-15">
	                  	     <label for="id" class="control-label">主键标识</label>
			                 <input type="number" min="0" class="form-control" name="id" id="id"/>     
		                  </div>
		                   <div class="col-md-6 margin-bottom-15">
			                    <label for="name" class="control-label">别名 </label>
			                    <input type="text" class="form-control" id="name" name="name" />                 
		                  </div>
		                </div>
		                  <div class="row">
		                  <div class="col-md-6 margin-bottom-15">
			                    <label for="algorithmOid" class="control-label">算法OID </label>
			                    <input type="text" class="form-control" id="algorithmOid" name="algorithmOid" />                 
		                  </div>
		                  <div class="col-md-6 margin-bottom-15">
		                  	<label for="keysize" class="control-label">密钥长度 </label>
			                    <input type="text" class="form-control" id="keysize" name="keysize" />           
		                  </div>
		                </div>
		                <div class="row templatemo-form-buttons">
			                <div class="col-md-12">
			                  <button type="submit" class="btn btn-primary">查询</button>
			               	 </div>
		            	 </div>
		               </form>
		             </div>
		          </div>
                <table class="table table-striped table-hover table-bordered">
		                  <thead>
		                    <tr bgcolor="CFCFCF">
		                      <th width="8%">主键</th>
		                      <th width="10%">公钥</th>
		                      <th width="10%">私钥</th>
		                      <th width="10%">算法+长度</th>
		                      <th width="10%">任务</th>
		                      <th width="8%">生成时间</th>
		                      <th width="16%">操作</th>
		                    </tr>
		                  </thead>
                    <tbody id="id_tbody_upd_list"> 
                    <c:forEach var="row" items="${keyPairStandbys}">
                    	<!--  修改密钥算法-->
	                    <tr id="upd_list_row_id_${row.id}" >
		                    <td>
		                    	<a href="javascript:edit('${row.id}','${row.pubKey}','${row.priKey}','${row.keyPairAlgorithm}','${row.kpgTask}','${row.genTime}')" class="btn btn-link">${row.id}</a>
		                    </td>
		                    <td>${row.pubKey}</td>
		                    <td>${row.priKey}</td>
		                    <td>${row.keyPairAlgorithm}</td>
		                    <td>${row.kpgTask}</td>
		                    <td>${row.genTime}</td>
		                    <td> <a href="javascript:remove('${row.id}')"  class="btn btn-link">删除</a>
							</td>      		
	                    </tr>
                	</c:forEach>
               </table>
            </div>
          </div>
        </div>
       </div>
   </body>     
 <script type="text/javascript">
function remove(id){
   if (confirm("您确实要刪除该记录吗？")){
     self.location.replace("remove.do?&id="+id);
   }
}   
function suspend(id){
     self.location.replace("suspend.do?&id="+id);
}
function activate(id){
     self.location.replace("activate.do?&id="+id);
}
//修改的初始化页面            
function edit(id,name,algorithmOid,algorithmName,keysize,notes,isValid){
			$("#modal_update input[name='id']").val(id);
			$("#modal_update input[name='name']").val(name);	
			$("#modal_update input[name='algorithmOid']").val(algorithmOid);	
			$("#modal_update input[name='algorithmName']").val(algorithmName);
			$("#modal_update input[name='keysize']").val(keysize);	
			$("#modal_update [id='notes']").val(notes);
			$("#modal_update input[name='isValid']").val(isValid);
			$("#modal_update").modal('show');
}
function add(){
	$("#modal_insert").modal('show');
}
function searchByCondition(){
	if($("#searchCondition").is(":hidden")){
		$("#searchCondition").show();
	}else{
		$("#searchCondition").hide();
	}
}
</script>

<c:if test="${success != null && success != ''}">     
<script type="text/javascript">
 document.getElementById("upd_list_row_id_${success}").className="success";
</script>
</c:if> 
 
<c:if test="${messageInsert != null && messageInsert != ''}">     
	<script type="text/javascript">
	 	$("#modal_insert").modal('show');
	</script>
</c:if> 