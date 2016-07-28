/**
 * 初始化
 */
$(function() {
	loadUserList("sysuser!queryUserList.do");
});


/**
 * 加载用户列表
 * @memberOf {TypeName} 
 * @return {TypeName} 
 */
function loadUserList(url){
	$('#sysUserList').datagrid({
			width : "auto",
			height : "auto",
			nowrap : true,
			autoRowHeight : false,
			striped : true,
			fit : true,
			border : false,
			collapsible : true,
			method:'get',
			url :url,
			sortName : "sysUserName",
			sortOrder : "desc",
			remoteSort : false,
			idField : "sysUserId",
			pageSize : 15,
			pageList : [10,15,20,30],
			rownumbers : true,
			fitColumns : true,
			pagination : true,
			rownumbers : true,
			columns : [ [
					{
						field : "sysUserName",
						title : "用户名",
						width : $(this).width() * 0.1,
						align : "center"
					},
					{
						field : "roleName",
						title : "所属角色",
						width : $(this).width() * 0.1,
						align : "center"
					},
					{
						field : "phone",
						title : "手机号码",
						width : $(this).width() * 0.1,
						align : "center"
					},
					{
						field : "email",
						title : "邮箱",
						width : $(this).width() * 0.1,
						align : "center"
					},
					{
						field : "sysUserId",
						title : "用户Id",
						hidden : true, 
						width : $(this).width() * 0.1,
						align : "center"
					},
					{
						field : "updateDate",
						title : "更新时间",
						width : $(this).width() * 0.1,
						align : "center",
						formatter: function(value,row,index){
									  if(value==0)
										   return "-&nbsp;&nbsp;-";
									   else	   
										   return new Date(value).format("yyyy-MM-dd hh:mm:ss");
								   }
					},
					{
						field : 'operate',
						title : '操作',
						width : $(this).width() * 0.1,
						align : 'center',
						rowspan :2,
		           	  	formatter:function(value,rowData,index){
		           	  			var html="";
		           				html += "<a href='javascript:;' onclick = 'javascript:deleteSysUser(" +"&quot;"+ rowData.sysUserId +"&quot"+");'"+
		           						"style=\"color:rgb(51,102,153);text-decoration:none;\" " +"onmouseover=\"javascript:this.style.color='rgb(255,102,0)';\""+
					           	  		"onmouseout=\"javascript:this.style.color='rgb(51,102,153)';\">[删除]</a>&nbsp;&nbsp;&nbsp;";
		           				html += "<a href='javascript:;' onclick = 'javascript:editSysUserView(" +"&quot;"+ rowData.sysUserId +"&quot"+");'" +
		           						"style=\"color:rgb(51,102,153);text-decoration:none;\" " +"onmouseover=\"javascript:this.style.color='rgb(255,102,0)';\""+
					           	  		"onmouseout=\"javascript:this.style.color='rgb(51,102,153)';\">[编辑]</a>&nbsp;&nbsp;&nbsp;";
		           				html += "<a href='javascript:;' onclick = 'javascript:openResetPwdDialog(" +"&quot;"+ rowData.sysUserId +"&quot"+");'" +
           								"style=\"color:rgb(51,102,153);text-decoration:none;\" " +"onmouseover=\"javascript:this.style.color='rgb(255,102,0)';\""+
           								"onmouseout=\"javascript:this.style.color='rgb(51,102,153)';\">[重置密码]</a>&nbsp;&nbsp;&nbsp;";
		           				return html;  
		                }
					} 
					] ]
			
		});
}


/**
 * 弹出添加用户窗口
  */
function openAddSysUserView() {
	$('#addSysUserView').dialog({
		cache: false,
		width:450,
		height:435,
		title:"添加用户",
		modal:true,
		href:'sysuser!openDialog.do?path=addSysUserView'
	});
	$('#addSysUserView').dialog('open');
}
 

/**
 * 打开重置密码页面
 * @param {Object} userId
 */
function openResetPwdDialog(sysUserId) {
	$('#editPasswordView').dialog({
		cache: false,
		width:430,
		height:200,
		title:"重置密码",
		modal:true,
		href:'sysuser!openDialog.do?sysUserId='+sysUserId+'&path=editPassword'
	});	
	$('#editPasswordView').dialog('open');
};


/**
 * 打开用户详细信息页面
 * @param {Object} userId
 */
function editSysUserView(sysUserId) {
	$('#editSysUserView').dialog({
		cache: false,
		width:450,
		height:410,
		title:"编辑用户信息",
		modal:true,
		href:'sysuser!openDialog.do?sysUserId='+sysUserId+'&path=editSysUserView'
	});	
	$('#editSysUserView').dialog('open');
};


/**
 * 删除用户
 * @param {Object} userId
 */
function deleteSysUser(sysUserId) {
	$.messager.confirm('确认','您确认想要删除记录吗？',function(r){    
	    if (r){    
	        $.ajax({
	        	type:"post",
	        	url:"sysuser!deleteUser.do?sysUserId="+sysUserId,
	        	success:function(msg) {
	        		var result = eval('('+msg+')');
	        		if(result) {
	        			$.messager.alert("消息","删除成功","info");
	        		} else {
	        			$.messager.alert("消息","删除失败","info");
	        		}
	        		loadUserList("sysuser!queryUserList.do");;
	        	}
	        });   
	    }    
	});
}




