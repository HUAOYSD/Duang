/**
 * 初始化
 */
$(function() {
	loadRoleList("sysrole!queryRolePageList.do");
});


/**
 * 加载会员列表
 * @memberOf {TypeName} 
 * @return {TypeName} 
 */
function loadRoleList(url){
	$('#RoleList').datagrid({
			width : "auto",
			height : "auto",
			nowrap : true,
			autoRowHeight : false,
			striped : true,
			collapsible : true,
			method:'get',
			fit :true,
			border : false,
			url :url,
			sortName : "sysRoleName",
			sortOrder : "desc",
			remoteSort : false,
			idField : "sysRoleId",
			pageSize : 15,
			pageList : [10,15,20,30],
			rownumbers : true,
			fitColumns : true,
			pagination : true,
			rownumbers : true,
			rowStyler: function(index,row){
				if ((index % 2) != 0){
					return 'background-color:rgb(212,233,255);color:#000000;font-weight:normal;';
				}
			},
			columns : [ [
					{
						field : "sysRoleName",
						title : "角色名称",
						width : $(this).width() * 0.1,
						align : "center"
					},
					{
						field : "sysRoleDesc",
						title : "角色描述",
						width : $(this).width() * 0.1,
						align : "center"
					},
					{
						field : "optDate",
						title : "操作时间",
						width : $(this).width() * 0.1,
						align : "center",
						formatter: function(value,row,index){
									   return new Date(value).format("yyyy-MM-dd hh:mm:ss");
									}
					},
					{
						field : "sysRoleId",
						title : "角色ID",
						hidden : true,
						width : $(this).width() * 0.1,
						align : "center"
					},
					{
						field : 'operate',
						title : '操作',
						width : $(this).width() * 0.1,
						align : 'center',
						rowspan :2,
		           	  	formatter:function(value,rowData,index){
		           	  			var html="";
		           	  			//if(rowData.sysRoleId!='admin') {
		           				html += "<a href='javascript:;' onclick = 'javascript:updateRoleView(" +"&quot;"+ rowData.sysRoleId +"&quot"+");' " +
		           						"style=\"color:rgb(51,102,153);text-decoration:none;\" " +"onmouseover=\"javascript:this.style.color='rgb(255,102,0)';\""+
					           	  		"onmouseout=\"javascript:this.style.color='rgb(51,102,153)';\">[编辑]</a>&nbsp;&nbsp;&nbsp;";	
		           				html += "<a href='javascript:;' onclick = 'javascript:deleteRole(" +"&quot;"+ rowData.sysRoleId +"&quot"+");'"+
		           						"style=\"color:rgb(51,102,153);text-decoration:none;\" " +"onmouseover=\"javascript:this.style.color='rgb(255,102,0)';\""+
					           	  		"onmouseout=\"javascript:this.style.color='rgb(51,102,153)';\">[删除]</a>&nbsp;&nbsp;&nbsp;";
		           				//html += "<a href='javascript:;' onclick = 'javascript:openUserView(" + rowData.memberId + ");'><font >查看</font></a>&nbsp;&nbsp;&nbsp;";
		           				html += "<a href='javascript:;' onclick = 'javascript:openAllotPowerView(" +"&quot;"+ rowData.sysRoleId +"&quot"+");' " +
		           						"style=\"color:rgb(51,102,153);text-decoration:none;\" " +"onmouseover=\"javascript:this.style.color='rgb(255,102,0)';\""+
					           	  		"onmouseout=\"javascript:this.style.color='rgb(51,102,153)';\">[分配权限]</a>&nbsp;&nbsp;&nbsp;";	
		           	  		//	}
		                      	return html;  
		                }
					} 
					] ]
		});
}
	

/**
 * 打开添加角色页面
 * @param {Object} userId
 */		 
function openAddRoleView(memberId) {
		$('#addRoleView').dialog({
			cache: false,
			width:450,
			height:400,
			title:"添加角色",
			modal:true,
			href:'sysrole!openDialog.do?path=addRoleView'
		});	
		$('#addRoleView').dialog('open');
};


/**
 * 打开更改角色页面
 * @param {Object} memberId
 */
function updateRoleView(sysRoleId) {
	$('#editRoleView').dialog({
			cache: false,
			width:450,
			height:260,
			title:"修改角色",
			modal:true,
			href:'sysrole!openDialog.do?path=editRoleView&sysRoleId='+sysRoleId
	});
	$('#editRoleView').dialog('open');
}


/**
 * 删除权限
 * @param {Object} powerId
 */
function deleteRole(sysRoleId) {
	  $.messager.confirm('确认','您确认想要删除记录吗？',function(r){    
	    if (r){    
	        $.ajax({
	        	type:"post",
	        	url:"sysrole!deleteRole.do?",
	        	data:{"sysRoleId": sysRoleId},
	        	success:function(msg) {
	        		var result = eval('('+msg+')');
	        		if(result) {
	        			$.messager.alert("消息","删除成功","info");
	        		} else {
	        			$.messager.alert("消息","删除失败","info");
	        		}
	        		loadRoleList("sysrole!queryRolePageList.do");
	        	}
	        });   
	    }    
	});
}


/** 
 * 打开分配权限页面
 * @param {Object} sysRoleId
 */
function openAllotPowerView(sysRoleId) {
	$('#powerToRoleView').dialog({
			cache: false,
			width:450,
			height:600,
			title:"分配权限",
			modal:true,
			href:'sysrole!openDialog.do?path=powerToRoleView&sysRoleId='+sysRoleId
	});
	$('#powerToRoleView').dialog('open');
}
 




