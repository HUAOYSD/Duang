/**
 * 初始化
 */
$(function() {
	$('#edit_btn_rolelist').linkbutton('disable');
	$('#del_btn_rolelist').linkbutton('disable');
	loadRoleList("sysrole!queryRolePageList.do");
});


/**
 * 加载会员列表
 * @memberOf {TypeName} 
 * @return {TypeName} 
 */
function loadRoleList(url){
	$('#rolelist').datagrid({
			height:$("#body_rolelist").height()-5,
			width:$("#body_rolelist").width(),
			loadMsg : "正在加载，请稍后...",
			singleSelect:true, 
			nowrap:true,
			rownumbers:true,
			pagination:true,
			pageSize:50,
			pageList:[50,100,150,200,250],
			sortOrder:'desc',
			toolbar:'#tt_toolbar_rolelist',
			method:'post',
			autoRowHeight : false,
			border : false,
			url :url,
			sortName : "sysRoleName",
			fitColumns : true,
			sortOrder : "desc",
			remoteSort : false,
			idField : "sysRoleId",
			rowStyler: function(index,row){
//				if ((index % 2) != 0){
//					return 'background-color:rgb(212,233,255);color:#000000;font-weight:normal;';
//				}
			},
			columns : [ [
			        {field:'sysRoleId',checkbox:true},
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
		           	  			//if(rowData.sysRoleId!='admin') {
		           				//html += "<a href='javascript:;' onclick = 'javascript:updateRoleView(" +"&quot;"+ rowData.sysRoleId +"&quot"+");' " +
		           				//		"style=\"color:rgb(51,102,153);text-decoration:none;\" " +"onmouseover=\"javascript:this.style.color='rgb(255,102,0)';\""+
					           	//  		"onmouseout=\"javascript:this.style.color='rgb(51,102,153)';\">[编辑]</a>&nbsp;&nbsp;&nbsp;";	
		           				//html += "<a href='javascript:;' onclick = 'javascript:deleteRole(" +"&quot;"+ rowData.sysRoleId +"&quot"+");'"+
		           				//		"style=\"color:rgb(51,102,153);text-decoration:none;\" " +"onmouseover=\"javascript:this.style.color='rgb(255,102,0)';\""+
					           	//  		"onmouseout=\"javascript:this.style.color='rgb(51,102,153)';\">[删除]</a>&nbsp;&nbsp;&nbsp;";
		           				//html += "<a href='javascript:;' onclick = 'javascript:openUserView(" + rowData.memberId + ");'><font >查看</font></a>&nbsp;&nbsp;&nbsp;";
		           				html += "<a href='javascript:;' onclick = 'javascript:openAllotPowerView(" +"&quot;"+ rowData.sysRoleId +"&quot"+");' " +
		           						"style=\"color:rgb(51,102,153);text-decoration:none;\" " +"onmouseover=\"javascript:this.style.color='rgb(255,102,0)';\""+
					           	  		"onmouseout=\"javascript:this.style.color='rgb(51,102,153)';\">[分配角色]</a>&nbsp;&nbsp;&nbsp;";	
		           				//}
		                      	return html;  
		                }
					} 
			] ],
			onSelect:function(rowIndex, rowData){
				$('#edit_btn_rolelist').linkbutton('enable');
				$('#del_btn_rolelist').linkbutton('enable');
			},
			onUnselect:function(rowIndex, rowData){
				$('#edit_btn_rolelist').linkbutton('disable');
				$('#del_btn_rolelist').linkbutton('disable');
			},
		});
}
	
/**
 * 刷新数据
 */
function reloadDataGrid(){
	loadRoleList("sysrole!queryRolePageList.do");
}

/**
 * 打开添加角色页面
 */
$("#add_btn_rolelist").on("click",function(){
	var indexLayer = layer.open({
		type: 2,
		title: '添加角色',
		shadeClose: true,
		shade: 0.8,
		area: ['460px', '458px'],
		content: 'sysrole!openDialog.do?path=addRoleView'
	});  
});

/**
 * 打开更改角色页面
 * @param {Object} memberId
 */
$("#edit_btn_rolelist").on("click",function(){
	var selectedRow = $("#rolelist").datagrid('getSelected');
	if(selectedRow==null){
		layer.msg("请选择一个角色",{time:1500});
		return;
	}
	var indexLayer = layer.open({
		type: 2,
		title: '编辑角色',
		shadeClose: true,
		shade: 0.8,
		area: ['460px', '280px'],
		content: 'sysrole!openDialog.do?path=editRoleView&sysRoleId='+selectedRow.sysRoleId
	}); 
});


/**
 * 删除角色
 * @param {Object} powerId
 */
$("#del_btn_rolelist").on('click',function(){
	var selectedRow = $("#rolelist").datagrid('getSelected'); 
	if(selectedRow==null){
		layer.msg("请选择一个角色",{time:1500});
		return;
	}
	$.messager.confirm('确认','您确认想要删除记录吗？',function(r){    
	    if (r){    
	    	$.messager.progress();
	    	$.ajax({
	         	type:"post",
	         	url:"sysrole!deleteRole.do?",
	         	data:{"sysRoleId": selectedRow.sysRoleId},
	         	success:function(msg) {
	         		var result = eval('('+msg+')');
	         		if(result.success) {
	         			loadRoleList("sysrole!queryRolePageList.do");
	         		} else {
	         			if(result.msg.isNotNull()){
	         				layer.msg(result.msg,{time:1500});
	         			}else{
	         				layer.msg("删除失败",{time:1500});
	         			}
	         		}
	         	}
	        }); 
	    	$.messager.progress("close");
	    }    
	});
});


/** 
 * 打开分配权限页面
 * @param {Object} sysRoleId
 */
function openAllotPowerView(sysRoleId) {
	var indexLayer = layer.open({
		type: 2,
		title: '分配权限',
		shadeClose: true,
		shade: 0.8,
		area: ['460px', '485px'],
		content: 'sysrole!openDialog.do?path=powerToRoleView&sysRoleId='+sysRoleId
	}); 
}
