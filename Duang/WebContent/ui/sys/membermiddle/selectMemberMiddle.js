/**
 * 初始化
 */
$(function() {
	loadmemberMiddle1("memberMiddle!queryNameIdCard.do");
});

/**
 * 改变表格宽高
 */
function domresize1(){
	$('#select_memberMiddle').datagrid('resize',{  
		height:$("#body_selectMemberMiddle").height()-$('#memberMiddle_search_area').height()-100,
		width:$("#body_selectMemberMiddle").width()-50
	});
}

/**
 * 监听窗口大小变化
 */
window.onresize = function(){
	setTimeout(domresize1,300);
};


/**
 * 加载列表
 * @memberOf {TypeName} 
 * @return {TypeName} 
 */
function loadmemberMiddle1(url, dataObj){
	$('#select_memberMiddle').datagrid({
			height:$("#body_selectMemberMiddle").height()-$('#memberMiddle_search_area').height()-100,
			width:$("#body_selectMemberMiddle").width()-50,
			loadMsg : "正在加载，请稍后...",
			singleSelect:true, 
			nowrap:true,
			rownumbers:true,
			sortOrder:'desc',
			method:'post',
			autoRowHeight : false,
			border : false,
			url :url,
			queryParams : dataObj,
			fitColumns : true,
			singleSelect : false,
			ctrlSelect:true,
			remoteSort : false,
			columns : [ [
			             {field:'id',checkbox:true},
			             {field : "userName", title : "姓名", width : 200, align : "center" },
			             {field : "idcard", title : "证件号", width : 300, align : "center"},
			             {field : "sum", title : "金额", width : 300, align : "center", 
			            	 editor:{  
				                 type:'numberbox',  
				                 options:{  
				                     missingMessage:'输入分配的放款金额'
				                 }  
				             } 

			             }
			] ],
			onLoadSuccess:function(data){
				for(var i=0;i<data.rows.length;i++){
					$('#select_memberMiddle').datagrid('beginEdit', i);
				}
			},
			onDblClickCell: function(index,field,value){
			}

		});
}
/**
 * 刷新数据
 */
function reloadDataGrid(){
	loadmemberMiddle("memberMiddle!queryNameIdCard.do");
}

/**
 * 打开借贷记录详情
 */
$("#btn_select_memberMiddle").on("click",function(){
	var selectedRows = $('#select_memberMiddle').datagrid('getSelections');
	if(selectedRows==null || selectedRows.length==0){
		layer.msg("请选择一个居间人",{time:1500});
		return;
	}
	var userNames='';
	var idcards='';
	var sums='';
	for(var i=0;i<selectedRows.length;i++){
		var selectRowIndex = $('#select_memberMiddle').datagrid('getRowIndex',selectedRows[i]);
		$('#select_memberMiddle').datagrid('endEdit',selectRowIndex);
		
		if(selectedRows[i].sum==0){
			layer.msg("请填写选择行的金额！",{icon:2});
			$('#select_memberMiddle').datagrid('beginEdit',selectRowIndex);
			return;
		}
		userNames+=selectedRows[i].userName;
		idcards+=selectedRows[i].idcard;
		sums+=selectedRows[i].sum;
		if(i<selectedRows.length-1){
			userNames+=",";
			idcards+=",";
			sums+=",";
		}
	}
	$.ajax({
		   type: "POST",
		   url: "scale!loanFullScaleToUser.do",
		   data: "scaleId="+scaleId+"&userNames"+userNames+"&idcards="+idcards+"&sums="+sums,
		   success: function(msg){
			    $.messager.progress("close");	
				var result = eval('(' + msg + ')');
				if(result.errorMemberInfo.length>0){
					layer.msg(errorMemberInfo+"放款失败!",{icon:2});
				}else{
					layer.msg("放款成功!",{icon:1});
				}
		   }
	});
	
});