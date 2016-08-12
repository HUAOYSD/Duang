/**
 * 初始化
 */
$(function() {
	$('#customerManager_open_close').on("click",function(){
		$('#customerManager_conditon').toggle(80);
		setTimeout(domresize,100);//条件隐藏，改变表格高度
	});	
	loadcustomerManager("customermanager!queryCustomerManagerByPage.do");
});


/**
 * 改变表格宽高
 */
function domresize(){
	$('#customerManager').datagrid('resize',{  
		height:$("#body_customerManager").height()-$('#customerManager_search_area').height()-$('#tt_toolbar_customerManager_footer').height()-38,
		width:$("#body_customerManager").width()
	});
}


/**
 * 监听窗口大小变化
 */
window.onresize = function(){
	setTimeout(domresize,300);
};


/**
 * 加载列表
 * @memberOf {TypeName} 
 * @return {TypeName} 
 */
function loadcustomerManager(url, dataObj){
	$('#customerManager').datagrid({
			height:$("#body_customerManager").height()-$('#customerManager_search_area').height()-$('#tt_toolbar_customerManager_footer').height()-38,
			width:$("#body_customerManager").width(),
			loadMsg : "正在加载，请稍后...",
			singleSelect:true, 
			nowrap:true,
			rownumbers:true,
			pagination:true,
			pageSize:50,
			pageList:[50,100,150,200,250],
			sortOrder:'desc',
			method:'post',
			autoRowHeight : false,
			border : false,
			url :url,
			queryParams : dataObj,
			fitColumns : true,
			remoteSort : false,
			rowStyler: function(index,row){},
			columns : [ [
			        {field:'id',checkbox:false},
					{field : "name", title : "姓名", width : $(this).width() * 0.1, align : "center" },
					{field : "sysUserName", title : "ID", width : $(this).width() * 0.1, align : "center" },
					{field : "workNumber", title : "工号", width : $(this).width() * 0.1, align : "center" },
					{field : "sex", title : "性别", width : $(this).width() * 0.1, align : "center" },
					{field : "phone", title : "手机号", width : $(this).width() * 0.1, align : "center" },
					{field : "email", title : "邮箱", width : $(this).width() * 0.1, align : "center" },
					{field : "idcard", title : "身份证", width : $(this).width() * 0.1, align : "center" },
					{field : "createtime", title : "创建时间", width : $(this).width() * 0.1, align : "center",
					 formatter: function(value,row,index){
						  if(value==0)
							   return "-&nbsp;&nbsp;-";
						  else	   
							   return new Date(value).format("yyyy-MM-dd hh:mm:ss");
					 }
					} 
			] ]
		});
}


/**
 * 查询
 */
$("#customerManagerQueryForm_Btn").on("click", function(){
	var name = $("#customerManagerSearch_name").val();
	var sysUserId = $("#customerManagerSearch_sysUserId").val();
	var workNumber = $("#customerManagerSearch_workNumber").val();
	var phone = $("#customerManagerSearch_phone").val();
	var email = $("#customerManagerSearch_email").val();
	var idcard = $("#customerManagerSearch_idcard").val();
	var sex = $("#customerManagerSearch_sex").combobox("getValue");
	var isDelete = $("#customerManagerSearch_isDelete").combobox("getValue");

	var data = new Object();
	if(name.isNotNull()){
		data.name = name;
	}
	if(sysUserId.isNotNull()){
		data.sysUserName = sysUserId;
	}
	if(workNumber.isNotNull()){
		data.workNumber = workNumber;
	}
	if(phone.isNotNull()){
		data.phone = phone;
	}
	if(email.isNotNull()){
		data.email = email;
	}
	if(idcard.isNotNull()){
		data.idcard = idcard;
	}
	if(sex.isNotNull()){
		data.sex = sex;
	}
	if(isDelete.isNotNull()){
		data.isDelete = isDelete;
	}
	loadcustomerManager("customermanager!queryCustomerManagerByPage.do", data);
});


/**
 * 分配客户经理
 */
$("#chose_customerManager").on("click",function(){
	var selectedRow = $("#customerManager").datagrid('getSelected');
	if(selectedRow==null){
		layer.msg("请选择一条记录",{time:1500});
		return;
	}
	$.messager.confirm('确认','您确认分配为该客户经理吗？',function(r){    
	    if (r){    
	    	var id = window.parent.getRecordId();
	    	$.messager.progress();
	    	$.ajax({
	         	type:"post",
	         	url:"loanlist!updateCustomerManager.do?",
	         	data:{"customerManager.id":selectedRow.id, "id":id},
	         	success:function(msg) {
	         		var result = eval('('+msg+')');
	         		if(result.success) {
	         			window.parent.reloadDataGrid();
	         			parent.layer.closeAll();
	         		} else {
	         			layer.msg("分配客户经理失败",{time:1500});
	         		}
	         	}
	        }); 
	    	$.messager.progress("close");
	    }    
	});
});
