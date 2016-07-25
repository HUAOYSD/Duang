
/**
 * 初始化
 */
$(function() {
	loadUserList("sysUser_queryUserList.action");
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
			//url:'/back/goods/product_queryProductType.action',
			//url:'/zdy_shop/member.json',
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
		           				//html += "<a href='javascript:;' onclick = 'javascript:openUserView(" + rowData.memberId + ");'><font >查看</font></a>&nbsp;&nbsp;&nbsp;";
		           				html += "<a href='javascript:;' onclick = 'javascript:editSysUserView(" +"&quot;"+ rowData.sysUserId +"&quot"+");'" +
		           						"style=\"color:rgb(51,102,153);text-decoration:none;\" " +"onmouseover=\"javascript:this.style.color='rgb(255,102,0)';\""+
					           	  		"onmouseout=\"javascript:this.style.color='rgb(51,102,153)';\">[编辑]</a>&nbsp;&nbsp;&nbsp;";
		                      	return html;  
		                }
					} 
					] ]
			
		});
	}
	

/**
 * 打开用户详细信息页面
 * @param {Object} userId
 */
function editSysUserView(sysUserId) {
		$('#editSysUserView').dialog({
			cache: false,
			width:450,
			height:200,
			title:"编辑用户信息",
			modal:true,
			//href:'user/memberView.jsp?memberId='+memberId 
			href:'sysUser_openDialog.action?sysUserId='+sysUserId+'&path=editSysUserView'
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
	        	url:"sysUser_deleteUser.action?sysUserId="+sysUserId,
	        	//data:"",
	        	success:function(msg) {
	        		var result = eval('('+msg+')');
	        		if(result) {
	        			$.messager.alert("消息","删除成功","info");
	        		} else {
	        			$.messager.alert("消息","删除失败","info");
	        		}
	        		loadUserList("sysUser_queryUserList.action");;
	        	}
	        });   
	    }    
	});
//	loadMemberList("member_queryMemberList.action");
}

/**
 * 弹出添加用户窗口
  */
function openAddSysUserView() {
		$('#addSysUserView').dialog({
			cache: false,
			width:450,
			height:300,
			title:"添加用户",
			modal:true,
			//href:'user/addMember.jsp'
			href:'sysUser_openDialog.action?path=addSysUserView'
		});
		$('#addSysUserView').dialog('open');
		
}
 


