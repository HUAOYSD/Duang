
/**
 * 初始化
 */
$(function() {
	loadPowerList("privilege_queryPoweTreeList.action");
	//loadPowerList("ui/sysPower.jsp");
});
/**
 * 加载权限列表
 * @memberOf {TypeName} 
 * @return {TypeName} 
 */
function loadPowerList(url){
	$('#powerList').treegrid({
			width : "auto",
			height : "auto",
			nowrap : true,
			autoRowHeight : false,
			striped : true,
			collapsible : true,
			url :url,
			fit : true,
			border : false,
			idField:'powerId',
			treeField:'powerName',
			sortName : '',
			remoteSort : false,
			rownumbers : true,
			rownumbers : true,
			fitColumns : true,
			columns : [ [
					{
						field : "powerName",
						title : "权限名称",
						width : $(this).width() * 0.1,
						align : "left"
					},
					{
						field : "optTime",
						title : "操作时间",
						width : $(this).width() * 0.1,
						align : "center",
						formatter: function(value,row,index){
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
		           				html += "<a href='javascript:;' onclick = 'javascript:deletePower(" +"&quot;"+ rowData.powerId +"&quot"+");'"+
		           						"style=\"color:rgb(51,102,153);text-decoration:none;\" " +"onmouseover=\"javascript:this.style.color='rgb(255,102,0)';\""+
					           	  		"onmouseout=\"javascript:this.style.color='rgb(51,102,153)';\">[删除]</a>&nbsp;&nbsp;&nbsp;";
		           				//html += "<a href='javascript:;' onclick = 'javascript:openUserView(" + rowData.memberId + ");'><font >查看</font></a>&nbsp;&nbsp;&nbsp;";
		           				html += "<a href='javascript:;' onclick = 'javascript:editPowerView(" +"&quot;"+ rowData.powerId +"&quot"+");'" +
		           						"style=\"color:rgb(51,102,153);text-decoration:none;\" " +"onmouseover=\"javascript:this.style.color='rgb(255,102,0)';\""+
					           	  		"onmouseout=\"javascript:this.style.color='rgb(51,102,153)';\">[编辑]</a>&nbsp;&nbsp;&nbsp;";
		                      	return html;  
		                }
					} 
					] ],
		});
	}

/**
 * 打开添加权限窗口
 */
function openAddPowerView() {	
	$("#addPowerView").dialog({
		cache: false,
		width:400,
		height:248,
		title:"添加权限",
		modal:true,
		//href:'user/addMember.jsp'
		href:'privilege_openDialog.action?path=addPowerView'
	})
	$("#addPowerView").dialog('open');
}


/**
 * 编辑权限
 * @param {Object} powerId
 */
function editPowerView(powerId) {
	//alert(powerId);
	$("#editPowerView").dialog({
	cache: false,
		width:400,
		height:248,
		title:"编辑权限",
		modal:true,
		//href:'user/addMember.jsp'
		href:'privilege_openDialog.action?powerId='+powerId +"&path=editPowerView"
	})
	$("#editPowerView").dialog('open');
}

/**
 * 保存权限
 */
 function addPower() {
	 var name = $.trim($("#powerName").val());
	 
	 $("#PowerAddForm").form('submit',{
		 	onSubmit: function() {
		 		var con = true;
		 		$.ajax({
		 			 url:"privilege_checkPowerName.action",
		 			 dataType:'json',
		 			 async: false,
		 			 data:"name="+name,
		 			 success:function(data) {
		 				 if(!data.success) {
		 					 $.messager.alert('错误','权限名称已经存在！','error');
		 					 con = false;
		 				 }
		 			 }
		 		 });
		 		return con;
		 	},
			success : function(data) {
				var result = eval('(' + data + ')');
				if(result.success){
					// 成功
					$.messager.alert('成功','添加成功','info');
				}else{
					// 失败
					$.messager.alert('错误','添加失败','error');
				}
				$('#addPowerView').window('close');
				loadPowerList("privilege_queryPoweTreeList.action");
			}
		});
 }
 
 /**
  * 更新权限
  */
  function editPower() {
	 $("#PowerEditForm").form('submit',{
			success : function(data) {
				var result = eval('(' + data + ')');
				if(result.success){
					// 成功
					$.messager.alert('成功','更新成功','info');
				}else{
					// 失败
					$.messager.alert('错误','更新失败','error');
				}
				$('#editPowerView').window('close');
				loadPowerList("privilege_queryPoweTreeList.action");
			}
		});
 }
  
  /**
   * 删除权限
   * @param {Object} powerId
   */
  function deletePower(powerId) {
	  $.messager.confirm('确认','您确认想要删除记录吗？',function(r){    
	    if (r){    
	        $.ajax({
	        	type:"post",
	        	url:"privilege_deletePower.action?powerId="+powerId,
	        	//data:"",
	        	success:function(msg) {
	        		var result = eval('('+msg+')');
	        		if(result) {
	        			$.messager.alert("消息","删除成功","info");
	        		} else {
	        			$.messager.alert("消息","删除失败","info");
	        		}
	        		loadPowerList("privilege_queryPoweTreeList.action");
	        	}
	        });   
	    }    
	});
  }