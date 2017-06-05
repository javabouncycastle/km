<%@ page contentType="text/html;charset=utf8" pageEncoding="utf8" %>
<%@ taglib prefix="c" uri="/WEB-INF/c-1_0-rt.tld"%>
<%@ include file="../left.jsp" %>
<%@ include file="../footer.jsp" %>
<body>

        <div class="templatemo-content">
	          <ol class="breadcrumb">
	            <li><a href="<%=request.getContextPath()%>/main">主页面</a></li>
	            <li class="active">密钥任务</li>
	            <li><a href="../../sign-in.html">Sign In Form</a></li>
	             <li><a href="javascript:add()">增加密钥任务</a></li>
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
                <h4 class="margin-bottom-15">密钥任务列表</h4>
                <table class="table table-striped table-hover table-bordered">
		                  <thead>
		                    <tr bgcolor="CFCFCF">
		                      <th width="5%">主键</th>
		                      <th width="8%">别名</th>
		                      <th width="8%">算法</th>
		                      <th width="8%">生成数量</th>
		                      <th width="8%">已生成数量</th>
		                      <th width="10%">任务状态</th>
		                      <th width="10%">任务开始时间</th>
		                      <th width="10%">执行开始时间</th>
		                      <th width="10%">执行结束时间</th>
		                      <th width="10%">任务结果</th>
		                      <th width="10%">任务说明</th>
		                      <th width="10%">操作</th>
		                    </tr>
		                  </thead>
                    <tbody id="id_tbody_upd_list"> 
                    <c:forEach var="row" items="${kpgTasks}">
                    	<!--  修改密钥任务-->
	                    <tr id="upd_list_row_id_${row.id}" >
		                     <td>
		                    	<a href="javascript:edit('${row.id}','${row.name}','${row.keypairAlgorithm}','${row.kpgKeyAmount}','${row.dbCommitBufsize}','${row.taskStatus}',
		                    	'${row.taskStartTime}','${row.exeTaskStartTime}, ${row.exeTaskEndTime}','${row.taskExeResult}','${row.taskNotes }')" class="btn btn-link">${row.id}</a>
		                    </td>
		                    <td>${row.name}</td>
		                    <td>${row.keypairAlgorithm.id}</td>
		                    <td>${row.kpgKeyAmount}</td>
		                    <td>${row.dbCommitBufsize}</td>
		                    <td>${row.taskStatus.id}</td>
		                    <td>${row.taskStartTime}</td>
		                    <td>${row.exeTaskStartTime}</td>
		                    <td>${row.exeTaskEndTime}</td>
		                    <td>${row.taskExeResult}</td>
		                    <td>${row.taskNotes}</td>
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
       
       
        <!-- 弹出窗口 新增密钥任务内容-->
 	<div class="modal fade" id="modal_insert" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
           <div class="modal-content">
            	<div class="modal-header">
             	  <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
              	  <h4 class="modal-title" >新增密钥任务</h4>
                </div>
          		<form action="insert.do" method="post">
            		<div class="modal-header">
            			<div class="form">
            				<table id="infoTable"  class="table table-striped table-hover table-bordered">
				            	 <c:if test="${messageInsert != null && messageInsert != ''}">               
				                    <div class="alert alert-danger alert-dismissible" role="alert">
				                      <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span>
				                      <span class="sr-only">Close</span></button>
				                      <strong>${messageInsert}</strong> 
				                    </div>  
					            </c:if> 

				                <div class="row"> 
				                  <div class="col-md-6 margin-bottom-15">
				                    <label for="paraValue" class="control-label">别名 </label>
				                    <input type="text" class="form-control" id="name" name="name" value="${kpgTask.name}" required="required"/>                 
				                  </div>
				                  <div class="col-md-6 margin-bottom-15">
				                    <label for="notes" class="control-label">密钥算法</label>
				                    <select class="form-control margin-bottom-15" name="kpgAlg.id" id="paratypeInfo" required="required">
				                    	<option value="">--请选择--</option>
				                    	<c:forEach var="kpgAlg" items="${keypairAlgorithms}">
				                    		<option value="${kpgAlg.id}">${kpgAlg.name}</option>
				                    	</c:forEach>
				                    </select>
				                  </div>
				                </div>
				                
				                <div class="row">
				                  <div class="col-md-6 margin-bottom-15">
				                    <label for="paraCode" class="control-label">生成数量 </label>
				                    <input type="text" class="form-control" id="kpgKeyAmount" name="kpgKeyAmount" value="${kpgTask.kpgKeyAmount}" required="required" />                 
				                  </div>
				                  <%-- <div class="col-md-6 margin-bottom-15">
				                    <label for="color" class="control-label">任务状态</label>
				                    <select class="form-control margin-bottom-15" name="sc.id" id="paratypeInfo" required="required">
				                    	<option value="">--请选择--</option>
				                    	<c:forEach var="sc" items="${sysCodes}">
				                    		<option value="${sc.id}">${sc.paraCode}</option>
				                    	</c:forEach>
				                    </select>                 
				                  </div> --%>
				                </div>	
                  
				               <%--  <div class="row">		            
				                  <div class="col-md-6 margin-bottom-15">
				                    <label for="color" class="control-label">任务开始时间</label>
				                    <input type="date" class="form-control" id="taskStartTime" onfocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" class="MyWdate" name="taskStartTime" value="${kpgTask.taskStartTime}" required="required" />                 
				                  </div>
				                 </div> --%>
				                  
				                <div class="row">
				                  <div class="col-md-12 margin-bottom-15">
				                    <label for="notes" class="control-label">备注 </label>
				                    <textarea class="form-control" rows="3" id="notes" name="notes">${kpgTask.taskNotes}</textarea>
				                  </div>
				                </div>	
            				</table>
            			</div>          
			            <div class="modal-footer">
			            	<button type="submit" class="btn btn-primary">保存</button>
			             <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
			            </div>
          			</div>
         		</form>
       		 </div>
     	 </div>     
      </div>
       
      <!-- 弹出窗口 ,修改密钥任务内容-->
 	<div class="modal fade" id="modal_update" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
              <h4 class="modal-title" >密钥任务修改</h4>
            </div>
       
			<form action="update.do" method="post" >
           		 <div class="modal-header">
            		<div class="form">
            			<table id="id_cert_detail"  class="table table-striped table-hover table-bordered">
				             <c:if test="${updateSuccess != null && message != ''}">               
					               <div class="alert alert-danger alert-dismissible" role="alert">
						                <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span>
							                 <span class="sr-only">Close</span></button>
							                 <strong>${message}</strong> 
					               </div>  
					           </c:if> 
				                <div class="row">
				                  <div class="col-md-6 margin-bottom-15">
				                    <label for="id" class="control-label">主键</label>
				                    <input type="number" min="0" class="form-control" name="id" id="id" readonly="true"  required="required">                  
				                  </div>
                  
				                  <div class="col-md-6 margin-bottom-15">
				                    <label for="paraValue" class="control-label">别名</label>
				                    <input type="text" class="form-control" id="name" name="name" required="required"/>                 
				                  </div>
				                </div>
				                
				                <div class="row">
				                  <div class="col-md-6 margin-bottom-15">
				                    <label for="notes" class="control-label">算法</label>
				                    <input type="text" class="form-control" id="keypairAlgorithm" name="keypairAlgorithm" required="required"/>     
				                  </div>
				                  <div class="col-md-6 margin-bottom-15">
				                    <label for="paraCode" class="control-label">生成数量</label>
				                    <input type="text" class="form-control" id="kpgKeyAmount" name="kpgKeyAmount" required="required" />                 
				                  </div>
				                </div>	
                  
				                <div class="row">
				                  <div class="col-md-6 margin-bottom-15">
				                    <label for="color" class="control-label">已生成数量</label>
				                    <input type="text" class="form-control" id="dbCommitBufsize" name="dbCommitBufsize" required="required" />                 
				                  </div>					            
				                  <div class="col-md-6 margin-bottom-15">
				                    <label for="color" class="control-label">任务状态</label>
				                    <select class="form-control margin-bottom-15" name="sc.id" id="paratypeInfo" required="required">
				                    	<option value="">--请选择--</option>
				                    	<c:forEach var="sc" items="${sysCodes}">
				                    		<option value="${sc.id}">${sc.paraCode}</option>
				                    	</c:forEach>
				                    </select>                
				                  </div>
				                </div>	
								<div class="row">
				                  <div class="col-md-6 margin-bottom-15">
				                    <label for="notes" class="control-label">任务开始时间</label>
				                    <input type="text" class="form-control" id="taskStartTime" name="taskStartTime" required="required"/>     
				                  </div>
				                  <div class="col-md-6 margin-bottom-15">
				                    <label for="paraCode" class="control-label">执行开始时间</label>
				                    <input type="text" class="form-control" id="exeTaskStartTime" name="exeTaskStartTime" required="required" />                 
				                  </div>
				                </div>	 
				                <div class="row">
				                  <div class="col-md-6 margin-bottom-15">
				                    <label for="notes" class="control-label">执行结束时间</label>
				                    <input type="text" class="form-control" id="exeTaskEndTime" name="exeTaskEndTime" required="required"/>     
				                  </div>
				                  <div class="col-md-6 margin-bottom-15">
				                    <label for="paraCode" class="control-label">任务结果</label>
				                    <input type="text" class="form-control" id="taskExeResult" name="taskExeResult" required="required" />                 
				                  </div>
				                </div>
				                <div class="row">
				                  <div class="col-md-12 margin-bottom-15">
				                    <label for="notes" class="control-label">任务说明 </label>
				                    <textarea class="form-control" rows="3" id="notes" name="notes">${kpgTask.taskNotes}</textarea>
				                  </div>
				                </div>	         				
				          </table>
            			</div>          
			            <div class="modal-footer">
			            	<button type="submit" class="btn btn-primary">保存</button>
			             <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
			            </div>
          			</div>
         		</form>
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
function edit(id,name,keypairAlgorithm,kpgKeyAmount,dbCommitBufsize,taskStatus,taskStartTime,exeTaskStartTime,exeTaskEndTime,taskExeResult,taskNotes){
			$("#modal_update input[name='id']").val(id);
			$("#modal_update input[name='name']").val(name);	
			$("#modal_update select[name='keypairAlgorithm.id']").val(keypairAlgorithm);	
			$("#modal_update input[name='kpgKeyAmount']").val(kpgKeyAmount);
		 	$("#modal_update input[name='dbCommitBufsize']").val(dbCommitBufsize);
			$("#modal_update select[name='taskStatus.id']").val(taskStatus.id);	
			$("#modal_update input[name='taskStartTime']").val(taskStartTime);
			$("#modal_update input[name='exeTaskStartTime']").val(exeTaskStartTime);
			$("#modal_update input[name='exeTaskEndTime']").val(exeTaskEndTime);
			$("#modal_update input[name='taskExeResult']").val(taskExeResult);	
			$("#modal_update [id='taskNotes']").val(taskNotes);
			$("#modal_update").modal('show');
}
function add(){
	$("#modal_insert").modal('show');
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