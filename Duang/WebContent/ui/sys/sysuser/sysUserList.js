/**
 * 初始化
 */
$(function() {
	$('#edit_btn_userlist').linkbutton('disable');
	$('#del_btn_userlist').linkbutton('disable');
	loadUserList("sysuser!queryUserList.do");
});


/**
 * 加载用户列表
 * @memberOf {TypeName} 
 * @return {TypeName} 
 */
function loadUserList(url){
	$('#userlist').datagrid({
			height:$("#body_userlist").height()-5,
			width:$("#body_userlist").width(),
			loadMsg : "正在加载，请稍后...",
			singleSelect:true, 
			nowrap:true,
			rownumbers:true,
			pagination:true,
			pageSize:30,
			sortOrder:'desc',
			toolbar:'#tt_toolbar_userlist',
			method:'post',
			autoRowHeight : false,
			border : false,
			url :url,
			//sortName : "sysUserName",
			//sortOrder : "desc",
			fitColumns : true,
			remoteSort : false,
			//idField : "sysUserId",
			rowStyler: function(index,row){
				//			if ((index % 2) != 0){
				//				return 'background-color:rgb(212,233,255);color:#000000;font-weight:normal;';
				//			}
			},
			showPageList: true,
			pageList:[50,100,150,200,250],
			columns : [ [
			        {field:'sysUserId',checkbox:true},
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
					//					{
					//						field : "phone",
					//						title : "手机号码",
					//						width : $(this).width() * 0.1,
					//						align : "center"
					//					},
					//					{
					//						field : "email",
					//						title : "邮箱",
					//						width : $(this).width() * 0.1,
					//						align : "center"
					//					},
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
		           				//html += "<a href='javascript:;' onclick = 'javascript:deleteSysUser(" +"&quot;"+ rowData.sysUserId +"&quot,"+rowData.isnotdel+");'"+
		           				//		"style=\"color:rgb(51,102,153);text-decoration:none;\" " +"onmouseover=\"javascript:this.style.color='rgb(255,102,0)';\""+
					           	//  		"onmouseout=\"javascript:this.style.color='rgb(51,102,153)';\">[删除]</a>&nbsp;&nbsp;&nbsp;";
		           				//html += "<a href='javascript:;' onclick = 'javascript:editSysUserView(" +"&quot;"+ rowData.sysUserId +"&quot"+");'" +
		           				//		"style=\"color:rgb(51,102,153);text-decoration:none;\" " +"onmouseover=\"javascript:this.style.color='rgb(255,102,0)';\""+
					           	//  		"onmouseout=\"javascript:this.style.color='rgb(51,102,153)';\">[编辑]</a>&nbsp;&nbsp;&nbsp;";
		           				html += "<a href='javascript:;' onclick = 'javascript:openResetPwdDialog(" +"&quot;"+ rowData.sysUserId +"&quot"+");'" +
           								"style=\"color:rgb(51,102,153);text-decoration:none;\" " +"onmouseover=\"javascript:this.style.color='rgb(255,102,0)';\""+
           								"onmouseout=\"javascript:this.style.color='rgb(51,102,153)';\">[重置密码]</a>&nbsp;&nbsp;&nbsp;";
		           				return html;  
		                }
					} 
			] ],
			onSelect:function(rowIndex, rowData){
				$('#edit_btn_userlist').linkbutton('enable');
				$('#del_btn_userlist').linkbutton('enable');
			},
			onUnselect:function(rowIndex, rowData){
				$('#edit_btn_userlist').linkbutton('disable');
				$('#del_btn_userlist').linkbutton('disable');
			},
			onLoadSuccess: function(data){
				$('#edit_btn_userlist').linkbutton('disable');
				$('#del_btn_userlist').linkbutton('disable');
			}
		});
}


/**
 * 刷新数据
 */
function reloadDataGrid(){
	loadUserList("sysuser!queryUserList.do");
}

/**
 * 打开用户添加信息页面
 * @param {Object} userId
 */
$("#add_btn_userlist").on("click",function(){
	layer.open({
		type: 2,
		title: '添加用户',
		shadeClose: true,
		shade: 0.8,
		area: ['470px', '388px'],
		content: 'sysuser!openDialog.do?path=addSysUserView'
	});  
});


/**
 * 打开用户详细信息页面
 * @param {Object} userId
 */
$("#edit_btn_userlist").on("click",function(){
	var selectedRow = $("#userlist").datagrid('getSelected');
	if(selectedRow==null){
		layer.msg("请选择一个角色",{time:1000});
		return;
	}
	layer.open({
		type: 2,
		title: '编辑用户信息',
		shadeClose: true,
		shade: 0.8,
		area: ['470px', '325px'],
		content: 'sysuser!openDialog.do?sysUserId='+selectedRow.sysUserId+'&path=editSysUserView'
	}); 
});


/**
 * 打开重置密码页面
 * @param {Object} userId
 */
function openResetPwdDialog(sysUserId) {
	layer.open({
		type: 2,
		title: '重置密码',
		shadeClose: true,
		shade: 0.8,
		area: ['470px', '205px'],
		content: 'sysuser!openDialog.do?sysUserId='+sysUserId+'&path=editPassword'
	});
};


/**
 * 删除用户
 * @param {Object} powerId
 */
$("#del_btn_userlist").on('click',function(){
	var selectedRow = $("#userlist").datagrid('getSelected');
	if(selectedRow==null){
		layer.msg("请选择一个角色",{time:1500});
		return;
	}
	if(selectedRow.isnotdel){
		layer.msg("超级用户禁止操作",{time:1500});
	}else{
		$.messager.confirm('确认','您确认想要删除记录吗？',function(r){    
		    if (r){    
		        $.ajax({
		        	type:"post",
		        	url:"sysuser!deleteUser.do?sysUserId="+selectedRow.sysUserId,
		        	success:function(msg) {
		        		var result = eval('('+msg+')');
		        		if(result.success) {
		        			loadUserList("sysuser!queryUserList.do");;
		        			layer.msg("删除成功",{time:1500});
		        		} else {
		        			layer.msg("删除失败",{time:1500});
		        		}
		        	}
		        });   
		    }    
		});
	}
});

