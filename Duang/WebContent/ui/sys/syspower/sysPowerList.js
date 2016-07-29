/**
 * 初始化
 */
$(function() {
	loadPowerList("syspower!queryPoweTreeList.do");
});


/**
 * 加载权限列表
 * @memberOf {TypeName} 
 * @return {TypeName} 
 */
function loadPowerList(url){
	$('#powerlist').treegrid({
			autoRowHeight : false,
			striped : true,
			collapsible : true,
			border : false,
			idField:'powerId',
			treeField:'powerName',
			remoteSort : false,
			fitColumns : true,
			height:$("#body_powerlist").height()-5,
			width:$("#body_powerlist").width(),
			loadMsg : "正在加载，请稍后...",
			url :url,
			singleSelect:true, 
			nowrap:true,
			rownumbers:true,
			toolbar:'#tt_toolbar_powerlist',
			columns : [ [
					{
						field : "powerName",
						title : "权限名称",
						width : $(this).width() * 0.1,
						align : "left"
					},
					{
						field : "url",
						title : "访问路径",
						width : $(this).width() * 0.1,
						align : "left"
					},
					{
						field : "optTime",
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
						field : "powerId",
						title : "权限id",
						hidden: true,
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
		           				html += "<a href='javascript:;' onclick = 'javascript:deletePower(" +"&quot;"+ rowData.powerId +"&quot,"+rowData.isnotdel+");'"+
		           						"style=\"color:rgb(51,102,153);text-decoration:none;\" " +"onmouseover=\"javascript:this.style.color='rgb(255,102,0)';\""+
					           	  		"onmouseout=\"javascript:this.style.color='rgb(51,102,153)';\">[删除]</a>&nbsp;&nbsp;&nbsp;";
		           				//html += "<a href='javascript:;' onclick = 'javascript:openUserView(" + rowData.memberId + ");'><font >查看</font></a>&nbsp;&nbsp;&nbsp;";
		           				html += "<a href='javascript:;' onclick = 'javascript:editPowerView(" +"&quot;"+ rowData.powerId +"&quot,"+rowData.isnotdel+");'" +
		           						"style=\"color:rgb(51,102,153);text-decoration:none;\" " +"onmouseover=\"javascript:this.style.color='rgb(255,102,0)';\""+
					           	  		"onmouseout=\"javascript:this.style.color='rgb(51,102,153)';\">[编辑]</a>&nbsp;&nbsp;&nbsp;";
		                      	return html;  
		                }
					} 
					] ],
		});
}


/**
 * 刷新数据
 */
function reloadDataGrid(){
	loadPowerList("syspower!queryPoweTreeList.do"); 
}


/**
 * 打开添加权限窗口
 */
$("#add_btn_powerlist").on("click",function(){
	var indexLayer = layer.open({
		type: 2,
		title: '添加权限',
		shadeClose: true,
		shade: 0.8,
		area: ['460px', '438px'],
		content: 'syspower!openDialog.do?path=addPowerView'
	});  
});


/**
 * 打开编辑权限窗口
 * @param {Object} powerId
 */
function editPowerView(powerId, isnotdel) {
	if(isnotdel){
		$.messager.alert("提示","顶级权限禁止操作","info");
	}else{
		var indexLayer = layer.open({
			type: 2,
			title: '编辑产品',
			shadeClose: true,
			shade: 0.8,
			area: ['460px', '438px'],
			content: 'syspower!openDialog.do?powerId='+powerId+"&path=editPowerView"
		}); 
	}
}
 

/**
 * 删除权限
 * @param {Object} powerId
 */
function deletePower(powerId, isnotdel) {
	if(isnotdel){
		$.messager.alert("提示","顶级权限禁止操作","info");
	}else{
	  $.messager.confirm('确认','您确认想要删除记录吗？',function(r){    
		 if (r){    
		    $.ajax({
		    	type:"post",
		    	url:"syspower!deletePower.do?powerId="+powerId,
		    	success:function(msg) {
		    		var result = eval('('+msg+')');
		    		if(result.success) {
		    			loadPowerList("syspower!queryPoweTreeList.do");
		    		} else {
		    			$.messager.alert("消息","删除失败","info");
		    		}
		        }
		     });   
		   }    
	   });
	}
}