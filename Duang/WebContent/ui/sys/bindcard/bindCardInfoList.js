var tableObj;
var editRow = undefined; //定义全局变量：当前编辑的行
$(function(){
	//隐藏显示查询条件区域
	$('#bindCardInfoList_open_close').on("click",function(){
		$('#bindCardInfoList_conditon').toggle(80);
		setTimeout(domresize,100);//条件隐藏，改变表格高度
	});
	//表格初始化
	tableObj = $("#bindCardInfoList_table").datagrid({
		height:$("#bindCardInfoList_body").height()-$('#bindCardInfoList_search_area').height()-5,
		width:$("#bindCardInfoList_body").width(),
		loadMsg : "正在加载，请稍后...",
		url:"bindcard!queryAllBindedMember.do",  
		singleSelect:true,  
		nowrap:true,
		rownumbers:true,
		pagination:true,
		fitColumns:true,
		pageSize:50,
		pageList:[50,80,100,150,200],
		sortOrder:'desc',
		toolbar:'',
		columns:[[
				{field:'id',hidden:true},
				{field:'memberInfoId',hidden:true},
				{field:'name',title:'名称',halign:"center", align:"center",width:100},
		        {field:'phone',title:'开户手机号',halign:"center", align:"center",width:150},  
		        {field:'idcard',title:'身份证号',halign:"center", align:"center",width:200}, 
		        
				{field:'bankNo',title:'银行卡号',halign:"center", align:"center",width:200},
				{field:'openBank',title:'开户行',halign:"center", align:"center",width:150},
				{field:'type',title:'类型',halign:"center", align:"center",width:100,
					formatter: function(value,row,index){
						if(value==1){
							return "储蓄卡";
						}else if(value ==2){
							return "借记卡";
						}else{
							return "信用卡";
						}
					}
				},
				
				{field:'photoImg1',title:'银行卡正面照',halign:"center", align:"center",width:200},
				{field:'photoImg2',title:'银行卡后面照',halign:"center", align:"center",width:200 },
				{field:'optTime',title:'绑定日期',halign:"center", align:"center",width:120,
					formatter: function(value,row,index){
					   if(value==0){
						   return "-&nbsp;&nbsp;-";
					   }else{ 
						   return new Date(value).format("yyyy-MM-dd hh:mm:ss");
					   }
					}
				}
		]],
		onClickCell: function(index,field,value){
			tableObj.datagrid("selectRow",index);
			var selectRowt = tableObj.datagrid("getSelected");
			if(field=='photoImg1'){
				if(value==null){
					layer.msg("还没有上传银行卡前照",{time:2000});
					return;
				}
				indexLayer = layer.open({
					type: 2,
					title: '银行卡前照',
					shadeClose: true,
					shade: 0.8,
					area: ['80%', '80%'],
					maxmin:true,
					content: "bindcard!showBindCardImage.do?id="+selectRowt.id+"&type=1" //1代表上传身份证前照
				});  
			}else if(field=='photoImg2'){
				if(value==null){
					layer.msg("还没有上传银行卡后照",{time:2000});
					return;
				}
				indexLayer = layer.open({
					type: 2,
					title: '银行卡后照',
					shadeClose: true,
					shade: 0.8,
					area: ['80%', '80%'],
					maxmin:true,
					content: "bindcard!showBindCardImage.do?id="+selectRowt.id+"&type=2" //1代表上传身份证前照
				});  
			}
		},

	});
	$('#querybindCardInfoListForm').form({    
	    url:"bindcard!queryBandCardByParameter.do",    
	    onSubmit: function(){    
	        
	    },    
	    success:function(data){ 
	    	data = JSON.parse(data);
	    	if(data.result==false){
	    		layer.msg(data.msg,{time:2000});
	    	}
	    	tableObj.datagrid('loadData', {
	    		"rows":data.rows,
	    		"total":data.total,
	    	}); 
	    }    
	});
});

var selectedRow = null;
function isSelectedRow(){
	selectedRow = tableObj.datagrid("getSelected");
	if(selectedRow==null){
		return false;
	}
	return true;
}

//监听窗口大小变化
window.onresize = function(){
	setTimeout(domresize,300);
};
//改变表格宽高
function domresize(){
	$('#bindCardInfoList_table').datagrid('resize',{  
		height:$("#bindCardInfoList_body").height()-$('#bindCardInfoList_search_area').height()-5,
		width:$("#bindCardInfoList_body").width()
	});
}
