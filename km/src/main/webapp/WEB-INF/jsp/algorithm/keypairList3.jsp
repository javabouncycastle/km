<%@ page contentType="text/html;charset=utf8" pageEncoding="utf8" %>
<!DOCTYPE html>
<%@ include file="../left.jsp" %>
<body>

        <div class="templatemo-content">
          <ol class="breadcrumb">
            <li><a href="<%=request.getContextPath()%>/main">主页面</a></li>
            <li class="active">密钥算法容</li>
            <li><a href="../../sign-in.html">Sign In Form</a></li>
             <li><a href="javascript:add()">增加密钥算法</a></li>
          </ol>
        <div class="row">
            <div class="col-md-12">
            	 <c:if test="${success != null && success != ''}">               
	                   <div class="alert alert-success alert-dismissible" role="alert">
	                      <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
	                      <strong>操作成功！</strong> 
	                    </div>
	            </c:if> 
              <div class="table-responsive">
                <h4 class="margin-bottom-15">密钥算法列表　<a href="javascript:searchByCondition()"  class="btn btn-link">删除</a></h4>
                <table class="table table-striped table-hover table-bordered">
                  <thead>
                    <tr bgcolor="CFCFCF">
                      <th width="8%">主键</th>
                      <th width="10%">别名</th>
                      <th width="10%">算法OID</th>
                      <th width="10%">算法英文缩写</th>
                      <th width="10%">密钥长度</th>
                      <th width="8%">是否有效</th>
                      <th width="10%">备注</th>
                      <th width="16%">操作</th>
                    </tr>
                  </thead>
                    <tbody id="id_tbody_upd_list"> 
                    
                    <c:forEach var="row" items="${frSysCodes}">
                    <tr id="upd_list_row_id_${row.id}" >
                    <td><a href="javascript:
                    edit('${row.id}','${row.color}','${row.displayOrder}','${row.isDisplay}','${row.isValid}','${row.notes}','${row.paraCode}','${row.paraValue}','${row.paraType.id}')" 
                     class="btn btn-link">${row.id}</a></td>
                    <td>${row.color}</td>
                    <td>${row.displayOrder}</td>
                    <td>${row.paraValue}</td>
                    <td>
                    	<c:if test="${row.isDisplay=='1'}">是</c:if>
                    	<c:if test="${row.isDisplay=='0'}">否</c:if>
					</td>
                    <td>
                    	<c:if test="${row.isValid=='1'}">是</c:if>
                    	<c:if test="${row.isValid=='0'}">否</c:if>
					</td>
                    <td>${row.notes}</td>
                    <td>${row.paraCode}</td>
                    <td>
	                   	<c:forEach var="frs" items="${frSysCodeTypes}">
	                   		<c:if test="${frs.id==row.paraType.id}">
	                   			${frs.paraTypeDesc}
	                   		</c:if>
	                   	</c:forEach>
                    </td>
                    <td> <a href="javascript:remove('${row.id}')"  class="btn btn-link">删除</a>
					</td>      		
                    </tr>
                </c:forEach>
                    
                </table>
            	 共找到${totalCount}条记录 ,当前显示1到${totalCount}条.
            </div>
          </div>
        </div>
       </div>
       <%@ include file="../footer.jsp" %>
   </body>     
 <script type="text/javascript">


function remove(id){
   if (confirm("您确实要刪除该记录吗？")){
     self.location.replace("remove?&id="+id);
   }
}   
function suspend(id){
     self.location.replace("suspend?&id="+id);
}

function activate(id){
     self.location.replace("activate?&id="+id);
}

//修改的初始化页面            
function edit(id,fontColor,displayOrder,isDisplay,isValid,notes,paraCode,paraValue,paraType){
	 		$("#sys_code_id").html("");
	 		$("#sys_code_id").append(id);	
			$("#dictInfo input[name='id']").val(id);
			$("#dictInfo input[name='color']").val(fontColor);	
			$("#dictInfo input[name='displayOrder']").val(displayOrder);	
			$("#dictInfo #isDisplay").val(isDisplay);	
			$("#dictInfo #isValid").val(isValid);	
			$("#dictInfo input[name='notes']").val(notes);	
			$("#dictInfo input[name='paraCode']").val(paraCode);	
			$("#dictInfo input[name='paraValue']").val(paraValue);	
			$("#dictInfo").modal('show');
			$("#dictInfo #paratypeInfo").val(paraType);	

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
 
<c:if test="${message != null && message != ''}">     
<script type="text/javascript">
 	$("#modal_insert").modal('show');
</script>
</c:if> 
 
      
