<%@ page contentType="text/html;charset=utf8" pageEncoding="utf8" %>
<!DOCTYPE html>
<%@ include file="../left.jsp" %>


        <div class="templatemo-content">
          <ol class="breadcrumb">
            <li><a href="<%=request.getContextPath()%>/main">主页面</a></li>
            <li class="active">数据字典内容</li>
            <li><a href="../../sign-in.html">Sign In Form</a></li>
             <li><a href="javascript:add()">增加字典内容</a></li>
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
                <h4 class="margin-bottom-15">数据字典内容列表　<a href="javascript:searchByCondition()"  class="btn btn-link">查询</a></h4>
                <!-- 查询条件div -->
                 <div class="row" id="searchCondition" style="display:none">
		            <div class="col-md-12">
		              <form id="templatemo-preferences-form" action="list" method="post" >
		                <div class="row">
	                	  <div class="col-md-6 margin-bottom-15">
	                  	     <label for="id" class="control-label">主键标识</label>
			                 <input type="number" min="0" class="form-control" name="id" id="id"/>     
		                  </div>
		                 
		             
		                </div>
		                  <div class="row">
		                  <div class="col-md-6 margin-bottom-15">
			                    <label for="paraValue" class="control-label">设定值 </label>
			                    <input type="text" class="form-control" id="paraValue" name="paraValue" />                 
		                  </div>
		                  <div class="col-md-6 margin-bottom-15">
		                  	 <label for="notes" class="control-label">所属类型</label>
		                    <select class="form-control margin-bottom-15" name="paraType" id="paraType"/>
		                    	<option value="">--请选择--</option>
		                    	<c:forEach var="frs" items="${frSysCodeTypes}">
		                    		<option value="${frs.id}">${frs.paraTypeDesc}</option>
		                    	</c:forEach>
		                    </select>   
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
                      <th width="10%">设定值</th>
                      <th width="10%">所属类型</th>
                      <th width="10%">代码</th>
                      <th width="10%">显示颜色</th>
                      <th width="10%">显示顺序</th>
                      <th width="8%">是否显示</th>
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
                     	  <c:if test="${row.isValid==1}">
					      		<a href="javascript:suspend('${row.id}')"  class="btn btn-link">停用</a> 
					      </c:if>
					      <c:if test="${row.isValid==0}">
					            <a href="javascript:activate('${row.id}')"  class="btn btn-link">启用</a>
					      </c:if>  
					</td>      		
                    </tr>
                </c:forEach>
                </table>
            	 共找到${totalCount}条记录 ,当前显示1到${totalCount}条.
            </div>
          </div>
        </div>
<!-- 弹出窗口 ,修改数据字典内容-->
 	<div class="modal fade" id="dictInfo" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
              <h4 class="modal-title" >数据字典修改</h4>
            </div>
       
	<form action="update" method="post" >
            <div class="modal-header">
            	<div class="form">
            		<table id="id_cert_detail"  class="table table-striped table-hover table-bordered">
				          <c:if test="${message != null && message != ''}">               
				               <div class="alert alert-danger alert-dismissible" role="alert">
				                <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span>
				                 <span class="sr-only">Close</span></button>
				                 <strong>${message}</strong> 
				               </div>  
					      </c:if> 

				                <div class="row">
				                  <div class="col-md-6 margin-bottom-15">
				                    <label for="id" class="control-label">主键标识</label>
				                    <input type="number" min="0" class="form-control" name="id" id="id"  required="required">                  
				                  </div>
                  
				                  <div class="col-md-6 margin-bottom-15">
				                    <label for="paraValue" class="control-label">设定值 </label>
				                    <input type="text" class="form-control" id="paraValue" name="paraValue" required="required"/>                 
				                  </div>
				                </div>
				                
				                <div class="row">
				                  <div class="col-md-6 margin-bottom-15">
				                    <label for="notes" class="control-label">所属类型</label>
				                    <select class="form-control margin-bottom-15" name="paraType.id" id="paratypeInfo" required="required">
				                    	<option value="">--请选择--</option>
				                    	<c:forEach var="frs" items="${frSysCodeTypes}">
				                    		<option value="${frs.id}">${frs.paraTypeDesc}</option>
				                    	</c:forEach>
				                    </select>
				                    </div>
				                  <div class="col-md-6 margin-bottom-15">
				                    <label for="paraCode" class="control-label">代码 </label>
				                    <input type="text" class="form-control" id="paraCode" name="paraCode" required="required" />                 
				                  </div>
				                </div>	
                  
				                <div class="row">
				                  <div class="col-md-6 margin-bottom-15">
				                    <label for="color" class="control-label">显示颜色</label>
				                    <input type="text" class="form-control" id="color" name="color" required="required" />                 
				                  </div>					            
				                  <div class="col-md-6 margin-bottom-15">
				                    <label for="displayOrder" class="control-label">显示顺序</label>
				                    <input type="number" class="form-control" id="displayOrder" name="displayOrder" required="required" />                 
				                  </div>
				                </div>	
				                
				                 <div class="row">
				                  <div class="col-md-6 margin-bottom-15">
				                    <label for="isDisplay" class="control-label">是否显示</label>
				                     <select class="form-control margin-bottom-15" name="isDisplay" id="isDisplay"  >
									      <option value="1" >是</option>
									      <option value="0" >否</option>
				                  	   </select>                    
				                  </div>

				                  <div class="col-md-6 margin-bottom-15">
				                    <label for="isValid" class="control-label">是否有效</label>
				                     <select class="form-control margin-bottom-15" name="isValid" id="isValid"  >
									      <option value="1"  >是</option>
									      <option value="0"  >否</option>
				                  	   </select>                 
				                  </div>
				                </div>
				                	
                
				                <div class="row">
				                  <div class="col-md-12 margin-bottom-15">
				                    <label for="notes" class="control-label">备注 </label>
				                    <textarea class="form-control" rows="3" id="notes" name="notes">${frSysParam.notes}</textarea>
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
          </div>
          </form>
          </div>
  
        
 <!-- 弹出窗口 新增数据字典内容-->
 	<div class="modal fade" id="modal_insert" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
           <div class="modal-content">
            	<div class="modal-header">
             	  <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
              	  <h4 class="modal-title" >新增数据字典</h4>
                </div>
          		<form action="insert" method="post">
            		<div class="modal-header">
            			<div class="form">
            				<table id="infoTable"  class="table table-striped table-hover table-bordered">
				            	 <c:if test="${message != null && message != ''}">               
				                    <div class="alert alert-danger alert-dismissible" role="alert">
				                      <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span>
				                      <span class="sr-only">Close</span></button>
				                      <strong>${message}</strong> 
				                    </div>  
					            </c:if> 

				                <div class="row">
				                  <div class="col-md-6 margin-bottom-15">
				                    <label for="id" class="control-label">主键标识</label>
				                    <input type="number" min="0" class="form-control" name="id" id="id"  required="required">                  
				                  </div>
                  
				                  <div class="col-md-6 margin-bottom-15">
				                    <label for="paraValue" class="control-label">设定值 </label>
				                    <input type="text" class="form-control" id="paraValue" name="paraValue" required="required"/>                 
				                  </div>
				                </div>
				                
				                <div class="row">
				                  <div class="col-md-6 margin-bottom-15">
				                    <label for="notes" class="control-label">所属类型</label>
				                    <select class="form-control margin-bottom-15" name="paraType.id" id="paratypeInfo" required="required">
				                    	<option value="">--请选择--</option>
				                    	<c:forEach var="frs" items="${frSysCodeTypes}">
				                    		<option value="${frs.id}">${frs.paraTypeDesc}</option>
				                    	</c:forEach>
				                    </select>
				                    </div>
				                  <div class="col-md-6 margin-bottom-15">
				                    <label for="paraCode" class="control-label">代码 </label>
				                    <input type="text" class="form-control" id="paraCode" name="paraCode" required="required" />                 
				                  </div>
				                </div>	
                  
				                <div class="row">
				                  <div class="col-md-6 margin-bottom-15">
				                    <label for="color" class="control-label">显示颜色</label>
				                    <input type="text" class="form-control" id="color" name="color" required="required" />                 
				                  </div>					            
				                  <div class="col-md-6 margin-bottom-15">
				                    <label for="displayOrder" class="control-label">显示顺序</label>
				                    <input type="number" class="form-control" id="displayOrder" name="displayOrder" required="required" />                 
				                  </div>
				                </div>	
				                
				                 <div class="row">
				                  <div class="col-md-6 margin-bottom-15">
				                    <label for="isDisplay" class="control-label">是否显示</label>
				                     <select class="form-control margin-bottom-15" name="isDisplay" id="isDisplay"  >
									      <option value="1" >是</option>
									      <option value="0" >否</option>
				                  	   </select>                    
				                  </div>

				                  <div class="col-md-6 margin-bottom-15">
				                    <label for="isValid" class="control-label">是否有效</label>
				                     <select class="form-control margin-bottom-15" name="isValid" id="isValid"  >
									      <option value="1"  >是</option>
									      <option value="0"  >否</option>
				                  	   </select>                 
				                  </div>
				                </div>
				                	
                
				                <div class="row">
				                  <div class="col-md-12 margin-bottom-15">
				                    <label for="notes" class="control-label">备注 </label>
				                    <textarea class="form-control" rows="3" id="notes" name="notes">${frSysParam.notes}</textarea>
				                  </div>
				                </div>	
				                                
				                    <!-- <input type="text" class="form-control" id="paratypeInfo" name="paratypeInfo" required="required" /> -->                 
			
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
 
      
<%@ include file="../footer.jsp" %>