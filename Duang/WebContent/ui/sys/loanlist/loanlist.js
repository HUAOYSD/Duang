/**
 * 初始化
 */
$(function() {
	$('#edit_btn_loanlist').linkbutton('disable');
	$('#del_btn_loanlist').linkbutton('disable');
	$('#res_btn_loanlist').linkbutton('disable');
	$('#loanlist_open_close').on("click",function(){
		$('#loanlist_conditon').toggle(80);
		setTimeout(domresize,100);//条件隐藏，改变表格高度
	});	
	loadloanlist("loanlist!queryByPage.do");
});


/**
 * 改变表格宽高
 */
function domresize(){
	$('#loanlist').datagrid('resize',{  
		height:$("#body_loanlist").height()-$('#loanlist_search_area').height()-8,
		width:$("#body_loanlist").width()
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
function loadloanlist(url, dataObj){
	$('#loanlist').datagrid({
			height:$("#body_loanlist").height()-$('#loanlist_search_area').height()-8,
			width:$("#body_loanlist").width(),
			loadMsg : "正在加载，请稍后...",
			singleSelect:true, 
			nowrap:true,
			rownumbers:true,
			pagination:true,
			pageSize:50,
			pageList:[50,100,150,200,250],
			sortOrder:'desc',
			toolbar:'#tt_toolbar_loanlist',
			method:'post',
			autoRowHeight : false,
			border : false,
			url :url,
			queryParams : dataObj,
			fitColumns : true,
			remoteSort : false,
			rowStyler: function(index,row){},
			columns : [ [
			        {field:'id',checkbox:true},
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
			] ],
			onSelect:function(rowIndex, rowData){
				$('#edit_btn_loanlist').linkbutton('enable');
				$('#del_btn_loanlist').linkbutton('enable');
				$('#res_btn_loanlist').linkbutton('enable');
			},
			onUnselect:function(rowIndex, rowData){
				$('#edit_btn_loanlist').linkbutton('disable');
				$('#del_btn_loanlist').linkbutton('disable');
				$('#res_btn_loanlist').linkbutton('disable');
			},
			onLoadSuccess: function(data){
				$('#edit_btn_loanlist').linkbutton('disable');
				$('#del_btn_loanlist').linkbutton('disable');
				$('#res_btn_loanlist').linkbutton('disable');
			}
		});
}


/**
 * 查询
 */
$("#loanlistQueryForm_Btn").on("click", function(){
	var name = $("#loanlistSearch_name").val();
	var sysUserId = $("#loanlistSearch_sysUserId").val();
	var workNumber = $("#loanlistSearch_workNumber").val();
	var phone = $("#loanlistSearch_phone").val();
	var email = $("#loanlistSearch_email").val();
	var idcard = $("#loanlistSearch_idcard").val();
	var sex = $("#loanlistSearch_sex").combobox("getValue");
	var isDelete = $("#loanlistSearch_isDelete").combobox("getValue");

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
	loadloanlist("loanlist!queryByPage.do", data);
});


/**
 * 刷新数据
 */
function reloadDataGrid(){
	loadloanlist("loanlist!queryByPage.do");
}


/**
 * 打开添加页面
 */
$("#add_btn_loanlist").on("click",function(){
	layer.open({
		type: 2,
		title: '添加客户经理',
		shadeClose: true,
		shade: 0.8,
		area: ['460px', '530px'],
		content: 'loanlist!openDialog.do?path=add'
	});  
});


/**
 * 打开更改页面
 * @param {Object} memberId
 */
$("#edit_btn_loanlist").on("click",function(){
	var selectedRow = $("#loanlist").datagrid('getSelected');
	if(selectedRow==null){
		layer.msg("请选择一个客户经理",{time:1500});
		return;
	}
	layer.open({
		type: 2,
		title: '编辑客户经理',
		shadeClose: true,
		shade: 0.8,
		area: ['460px', '510px'],
		content: 'loanlist!openDialog.do?path=edit&id='+selectedRow.id
	}); 
});


/**
 * 删除
 * @param {Object} powerId
 */
$("#del_btn_loanlist").on('click',function(){
	var selectedRow = $("#loanlist").datagrid('getSelected'); 
	if(selectedRow==null){
		layer.msg("请选择一个客户经理",{time:1500});
		return;
	}
	$.messager.confirm('确认','您确认想要删除记录吗？',function(r){    
	    if (r){    
	    	$.messager.progress();
	    	$.ajax({
	         	type:"post",
	         	url:"loanlist!deleteloanlist.do?",
	         	data:{"id": selectedRow.id},
	         	success:function(msg) {
	         		var result = eval('('+msg+')');
	         		if(result.success) {
	         			loadloanlist("loanlist!queryByPage.do");
	         		} else {
	         			layer.msg("删除失败",{time:1500});
	         		}
	         	}
	        }); 
	    	$.messager.progress("close");
	    }    
	});
});


/**
 * 还原删除
 */
$("#res_btn_loanlist").on('click',function(){
	var selectedRow = $("#loanlist").datagrid('getSelected'); 
	if(selectedRow==null){
		layer.msg("请选择一个客户经理",{time:1500});
		return;
	}
	$.messager.confirm('确认','您确认想要还原记录吗？',function(r){    
	    if (r){    
	    	$.messager.progress();
	    	$.ajax({
	         	type:"post",
	         	url:"loanlist!restoreloanlist.do?",
	         	data:{"id": selectedRow.id},
	         	success:function(msg) {
	         		var result = eval('('+msg+')');
	         		if(result.success) {
	         			loadloanlist("loanlist!queryByPage.do");
	         		} else {
	         			layer.msg("还原失败",{time:1500});
	         		}
	         	}
	        }); 
	    	$.messager.progress("close");
	    }    
	});
});