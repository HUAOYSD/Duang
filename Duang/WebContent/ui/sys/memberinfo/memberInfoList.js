var tableObj;
var editRow = undefined; //定义全局变量：当前编辑的行
$(function(){
	//隐藏显示查询条件区域
	$('#memberInfoList_open_close').on("click",function(){
		$('#memberInfoList_conditon').toggle(80);
		setTimeout(domresize,100);//条件隐藏，改变表格高度
	});	
	//表格初始化
	tableObj = $("#memberInfoList_table").datagrid({
		height:$("#memberInfoList_body").height()-$('#memberInfoList_search_area').height()-5,
		width:$("#memberInfoList_body").width(),
		idField:'id',
		loadMsg : "正在加载，请稍后...",
		url:"memberinfo!queryAllMember.do",  
		singleSelect:true,  
		nowrap:true,
		rownumbers:true,
		pagination:true,
		fitColumns:false,
		pageSize:50,
		pageList:[50,80,100,150,200],
		sortOrder:'desc',
		toolbar:'#memberInfoList_table_toolbar',
		frozenColumns:[[  
						{field:'id',checkbox:true,halign:"center", align:"center"},
						{field:'loginName',title:'登录名',width:150,halign:"center", align:"center"},
						{field:'realName',title:'真实姓名',width:100,halign:"center", align:"center",editor:'center'},
		            ]],
		columns:[[
		          {title:'基本信息',colspan:16},
		          {title:'理财信息',colspan:7},
		          {title:'借贷信息',colspan:4}
		       ],[
		        {field:'investMember_id',title:'理财id',hidden:true}, 
		        {field:'loanMember_id',title:'借贷id',hidden:true},  
		        
				{field:'nickname',title:'昵称',width:100,halign:"center", align:"center"},
				{field:'isFreeze',title:'状态',width:100,halign:"center", align:"center",
					formatter:function(value,row,index){
						if(value==1){
							return "已冻结";
						}else{
							return "已解冻";
						}
					}
				},
				{field:'idCard',title:'身份证号',width:200,halign:"center", align:"center"},
				{field:'phone',title:'手机号',width:150,halign:"center", align:"center" },
				{field:'email',title:'邮箱',width:150,halign:"center", align:"center" },
				{field:'sex',title:'性别',width:100,halign:"center", align:"center",
					formatter: function(value,row,index){
						if(value==1){
							return "男";
						}else if(value == 0){
							return "女";
						}else{
							return "保密";
						}
					}
				},
				{field:'age',title:'年龄',width:100,halign:"center", align:"center" },
				{field:'type',title:'用户类型',width:100,halign:"center", align:"center",
					formatter: function(value,row,index){
						if(value==0){
							return "个体用户";
						}else if(value==1){
							return "企业用户";
						}
					}
				},
				{field:'isEliteAccount',title:'是否为金账户',width:100,halign:"center", align:"center",
					formatter: function(value,row,index){
						if(value==1){
							return "金账户";
						}else{
							return "非金账户";
						}
					}
				},
				{field:'userImg',title:'头像',width:200,halign:"center", align:"center" },
				{field:'idCardImg1',title:'身份证前照',width:200,halign:"center", align:"center"},
				{field:'idCardImg2',title:'身份证后照',width:200,halign:"center", align:"center" },
				{field:'price',title:'财力值',width:100,halign:"center", align:"center" },
				{field:'level',title:'等级',width:100,halign:"center", align:"center" },
				{field:'createTime',title:'开户日期',width:200,halign:"center", align:"center",
					formatter: function(value,row,index){
					   if(value==0){
						   return "-&nbsp;&nbsp;-";
					   }else{ 
						   return new Date(value).format("yyyy-MM-dd hh:mm:ss");
					   }
					}
				},
				{field:'miDescribe',title:'描述',width:200,halign:"center", align:"center" },
				
				//理财信息
				{field:'balance',title:'余额',width:100,halign:"center", align:"center" },
				{field:'investing',title:'投资中金额',width:100,halign:"center", align:"center"},
				{field:'totalIncome',title:'总收益',width:100,halign:"center", align:"center" },
				{field:'totalMoney',title:'总资产',width:100,halign:"center", align:"center" },
				{field:'useableScore',title:'可用积分',width:100,halign:"center", align:"center" },
				{field:'isContract',title:'契约用户',width:100,halign:"center", align:"center",
					formatter: function(value,row,index){
						if(value==1){
							return "是";
						}else{
							return "否";
						}
					}
				},
				{field:'registerStyle',title:'注册方式',width:100,halign:"center", align:"center",
					formatter: function(value,row,index){
						if(value==1){
							return "线下";
						}else if(value==2){
							return "Android";
						}else if(value==3){
							return "IOS";
						}else{
							return "平台系统";
						}
					}
				},
				//借贷用户信息
				{field:'lendMoney',title:'总借款',width:100,halign:"center", align:"center" },
				{field:'backMoney',title:'总还款',width:100,halign:"center", align:"center"},
				{field:'residueMoney',title:'剩余应还',width:100,halign:"center", align:"center" },
				{field:'expectMoney',title:'总逾期',width:100,halign:"center", align:"center" }
		]],
		onClickCell: function(index,field,value){
			tableObj.datagrid("selectRow",index);
			var selectRowt = tableObj.datagrid("getSelected");
			if(field=='idCardImg1'){
				if(value==null){
					layer.msg("还没有上传图片",{time:2000});
					return;
				}
				indexLayer = layer.open({
					type: 2,
					title: '身份证前照',
					shadeClose: true,
					shade: 0.8,
					area: ['80%', '80%'],
					maxmin:true,
					content: "memberinfo!showMemberInfoImage.do?id="+selectRowt.id+"&type=1" //1代表上传身份证前照
				});  
			}else if(field=='idCardImg2'){
				if(value==null){
					layer.msg("还没有上传图片",{time:2000});
					return;
				}
				indexLayer = layer.open({
					type: 2,
					title: '身份证后照',
					shadeClose: true,
					shade: 0.8,
					area: ['80%', '80%'],
					maxmin:true,
					content: "memberinfo!showMemberInfoImage.do?id="+selectRowt.id+"&type=2" //1代表上传身份证前照
				});  
			}
		}

	});
	$('#queryMemberInfoListForm').form({    
	    url:"memberinfo!queryMemberInfoByParameter.do",    
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


//上传身份证前照
$("#memberInfoList-upload-idcard1-btn").on('click',function(){
	//判断是否选择
	if(!isSelectedRow()){
		return;
	}
	indexLayer = layer.open({
		type: 2,
		title: '上传身份证前照',
		shadeClose: true,
		shade: 0.8,
		area: ['80%', '80%'],
		maxmin:true,
		content: "memberinfo!gotoUploadImg.do?id="+selectedRow.id+"&type=1" //1代表上传身份证前照
	});
});
//上传身份证后照
$("#memberInfoList-upload-idcard2-btn").on('click',function(){
	//判断是否选择
	if(!isSelectedRow()){
		return;
	}
	indexLayer = layer.open({
		type: 2,
		title: '上传身份证后照',
		shadeClose: true,
		shade: 0.8,
		maxmin:true,
		area: ['80%', '80%'],
		content: "memberinfo!gotoUploadImg.do?id="+selectedRow.id+"&type=2" //2代表上传身份证前照
	});
});

//取消选择
$("#memberInfoList-unselected-btn").on('click',function(){
	tableObj.datagrid("unselectAll");
});

//查询理财用户
$("#memberInfoList-sereach-investMember-btn").on('click',function(){
	$.ajax({
		   type: "POST",
		   url: "investmember!queryAllInvestMember.do",
		   data: "",
		   success: function(data){
			 data = JSON.parse(data);
		     if(data.result==true){
		    	 //隐藏借贷用户信息
		    	 hideLoanMemberColume();
		    	 showInvestMemberColume();
		    	 tableObj.datagrid('loadData', {
			    		"rows":data.rows,
			    		"total":data.total,
			      });
		     }else{
		    	 layer.msg(data.msg, {time: 1000});
		     }
		   }
	});
});

//查询借贷用户
$("#memberInfoList-sereach-loanMember-btn").on('click',function(){
	$.ajax({
		   type: "POST",
		   url: "loanmember!queryAllLoanMember.do",
		   data: "",
		   success: function(data){
			 data = JSON.parse(data);
		     if(data.result==true){
		    	 //隐藏理财用户信息
		    	 hideInvestMemberColume();
		    	 showLoanMemberColume();
		    	 tableObj.datagrid('loadData', {
			    		"rows":data.rows,
			    		"total":data.total,
			      });
		     }else{
		    	 layer.msg(data.msg, {time: 1000});
		     }
		   }
	});
});

//查询全部用户
$("#memberInfoList-sereach-memberInfo-btn").on('click',function(){
	$.ajax({
		   type: "POST",
		   url: "memberinfo!queryAllMember.do",
		   data: "",
		   success: function(data){
			 data = JSON.parse(data);
		     if(data.result==true){
		    	 //隐藏理财用户信息
		    	 showInvestMemberColume();
		    	 showLoanMemberColume();
		    	 tableObj.datagrid('loadData', {
			    		"rows":data.rows,
			    		"total":data.total,
			      });
		     }else{
		    	 layer.msg(data.msg, {time: 1000});
		     }
		   }
	});
});

function hideInvestMemberColume(){
	 hideColumn('#memberInfoList_table','balance');
	 hideColumn('#memberInfoList_table','investing');
	 hideColumn('#memberInfoList_table','totalIncome');
	 hideColumn('#memberInfoList_table','totalMoney');
	 hideColumn('#memberInfoList_table','useableScore');
	 hideColumn('#memberInfoList_table','isContract');
	 hideColumn('#memberInfoList_table','registerStyle');
	 $(".datagrid-header-row td[colspan='7']").hide();
}

function showInvestMemberColume(){
	showColumn('#memberInfoList_table','balance');
	showColumn('#memberInfoList_table','investing');
	showColumn('#memberInfoList_table','totalIncome');
	showColumn('#memberInfoList_table','totalMoney');
	showColumn('#memberInfoList_table','useableScore');
	showColumn('#memberInfoList_table','isContract');
	showColumn('#memberInfoList_table','registerStyle');
	$(".datagrid-header-row td[colspan='7']").show();
}

function hideLoanMemberColume(){
	 hideColumn('#memberInfoList_table','lendMoney');
	 hideColumn('#memberInfoList_table','backMoney');
	 hideColumn('#memberInfoList_table','residueMoney');
	 hideColumn('#memberInfoList_table','expectMoney');
	 $(".datagrid-header-row td[colspan='4']").hide();
}

function showLoanMemberColume(){
	showColumn('#memberInfoList_table','lendMoney');
	showColumn('#memberInfoList_table','backMoney');
	showColumn('#memberInfoList_table','residueMoney');
	showColumn('#memberInfoList_table','expectMoney');
	$(".datagrid-header-row td[colspan='4']").show();
}

function isSelectedRow(){
	selectedRow = tableObj.datagrid("getSelected");
	if(selectedRow==null){
		layer.msg("请选择用户！",{time:1000});
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
	$('#memberInfoList_table').datagrid('resize',{  
		height:$("#memberInfoList_body").height()-$('#memberInfoList_search_area').height()-5,
		width:$("#memberInfoList_body").width()
	});
}

function reloadDataGrid(){
    $("#invest_member_table").datagrid('reload');  
}

var selectedRow = null;
//冻结操作
$("#memberInfoList-freeze-btn").on('click',function(){
	//判断是否选择
	if(!isSelectedRow()){
		return;
	}
	$.ajax({
	   type: "POST",
	   url: "memberinfo!freezeMemberInfo.do",
	   data: "isFreeze=1&id="+selectedRow.memberInfoId,
	   success: function(data){
		   data = JSON.parse(data);
		   layer.msg(data.msg,{time:1000});
		   var selectRowIndex = $('#invest_member_table').datagrid('getRowIndex',selectedRow);
		   $('#invest_member_table').datagrid('updateRow',{
				index: selectRowIndex,
				row: {
					isFreeze: 1 //冻结
				}
			});
	   }
	});
});

//解冻操作
$("#memberInfoList-unfreeze-btn").on('click',function(){
	//判断是否选择
	if(!isSelectedRow()){
		return;
	}
	$.ajax({
		   type: "POST",
		   url: "memberinfo!freezeMemberInfo.do",
		   data: "isFreeze=0&id="+selectedRow.memberInfoId,
		   success: function(data){
			   data = JSON.parse(data);
			   layer.msg(data.msg,{time:1000});
			   var selectRowIndex = $('#invest_member_table').datagrid('getRowIndex',selectedRow);
			   $('#invest_member_table').datagrid('updateRow',{
					index: selectRowIndex,
					row: {
						isFreeze: 0 //解冻
					}
				});
		   }
		});
});

//添加操作
$("#memberInfoList-add-btn").on('click',function(){
	indexLayer = layer.open({
		type: 2,
		title: '添加理财用户',
		shadeClose: true,
		shade: 0.8,
		area: ['450px', '97%'],
		content: 'investmember!addInvestMember.do'
	});  
});
//修改
$("#memberInfoList-update-btn").on('click',function(){
	//判断是否选择
	if(!isSelectedRow()){
		return;
	}
	indexLayer = layer.open({
		type: 2,
		title: '修改产品',
		shadeClose: true,
		shade: 0.8,
		area: ['450px', '97%'],
		content: "investmember!eidtInvestMember.do?id="+selectedRow.id
	}); 
});

//删除
$("#memberInfoList-delete-btn").on('click',function(){
	//判断是否选择
	if(!isSelectedRow()){
		return;
	}
    layer.confirm('您确定要删除 '+selectedRow.realName+' 吗？', {
    	  title :'警告',
		  icon: 7,
		  btn: ['确定','取消'] //按钮
		}, function(){ //确定
			$.messager.progress('close');	// 如果提交成功则隐藏进度条
			$.ajax({
				   type: "POST",
				   url: "memberinfo!deleteMemberInfo.do",
				   data: "id="+selectedRow.memberInfoId,
				   success: function(data){
					 data = JSON.parse(data);
				     if(data.result==true){
				    	 var selectedRowIndex = $("#invest_member_table").datagrid('getRowIndex',selectedRow);
				    	 $("#invest_member_table").datagrid('deleteRow',selectedRowIndex);
				    	 layer.closeAll();
				     }
				    layer.msg(data.msg, {time: 1000});
				   }
				});
		}, function(){//取消
		  return;
	});
});

/**
 * 弹窗提示
 * @param msg 信息
 * @param time 时间
 */
function layerMsg(msg,time){
	 layer.msg(msg, {time: time});
}
	

/**
 * 扩展---隐藏列，解决隐藏复合表格中隐藏时发生错位问题
 */
var hideColumn = function(target,field){
	var t =  $(target);
	var opts = t.datagrid("options");
	var frozenColumns = opts.frozenColumns||(opts.frozenColumns = [[]]),columns = opts.columns||(opts.columns = [[]]);
	//var multiRowHeader=(columns.length>1&&columns[1].length>0)||(frozenColumns.length>1&&frozenColumns[1].length>0);
	var multiRowHeader = opts.multiRowHeader;
	if(!opts.hiddenColumns || !opts.hiddenColumns.length){
		opts.hiddenColumns = [];
	}
	if(!multiRowHeader){
		var dataGridPanel = t.datagrid("getPanel");
		dataGridPanel.find("td[field=\"" + field + "\"]").hide();
		t.datagrid("getColumnOption", field).hidden = true;
		t.datagrid("fitColumns");
	}else{
		var parentColumnInfo = getParentColumns(target,field);
		var frozen = parentColumnInfo.frozen;
		var columnOption = parentColumnInfo.columnOption;
		var parentColumnOptions = parentColumnInfo.parentColumnOptions;
		var cols = !!frozen?frozenColumns:columns;
		arrayUtil.each(parentColumnOptions,function(i,v){
			var colspan = parseInt(objUtil.getVal(cols[v.currentCoordinate.level][v.currentCoordinate.index],'colspan'));
			if(!isNaN(colspan) && colspan > 1){
				cols[v.currentCoordinate.level][v.currentCoordinate.index].colspan --;
			}else{
				arrayUtil.removeAt(cols[v.currentCoordinate.level],v.currentCoordinate.index,1);
			}
		});
		//arrayUtil.removeAt(cols[columnOption.coordinate.level],columnOption.coordinate.index,1);
		arrayUtil.removeAt(cols[columnOption.currentCoordinate.level],columnOption.currentCoordinate.index,1);
		t.datagrid('refactorView',opts,false);
		t.datagrid("fitColumns");
		
		opts.hiddenColumns.push(parentColumnInfo);
	}//if end
};

/**
 * 扩展---显示列，解决显示复合表格中隐藏时发生错位问题
 */
var showColumn = function(target,field){
	var t =  $(target);
	var opts = t.datagrid("options");
	var frozenColumns = opts.frozenColumns||(opts.frozenColumns = [[]]),columns = opts.columns||(opts.columns = [[]]);
	//var multiRowHeader=(columns.length>1&&columns[1].length>0)||(frozenColumns.length>1&&frozenColumns[1].length>0);
	var multiRowHeader = opts.multiRowHeader;
	if(!multiRowHeader){
		var dataGridPanel = t.datagrid("getPanel");
		dataGridPanel.find("td[field=\"" + field + "\"]").show();
		t.datagrid("getColumnOption", field).hidden = false;
		t.datagrid("fitColumns");
	}else{
		if(!opts.hiddenColumns || !opts.hiddenColumns.length){
			opts.hiddenColumns = [];
		}
		var index=-1,columnInfo = {};
		index = arrayUtil.indexOf(opts.hiddenColumns,field,function(info,field){
			return info.field == field;
		});
		if(index !== -1){
			var info = opts.hiddenColumns[index];
			var frozen = info.frozen,field = info.field;
			var columnOption = info.columnOption,parentColumnOptions = info.parentColumnOptions;
			
			//opts = insertColumns(columnOption,parentColumnOptions,opts,frozen);
			var arr = [columnOption].concat(parentColumnOptions);
			var colIndex = 0,length = arr.length,targetColumns = !!frozen?opts.frozenColumns:opts.columns;
			for(;colIndex < length;colIndex++){
				var columnOption = arr[colIndex];
				var insertCoordinate = getInsertCoordinate(targetColumns,columnOption);
				var insertLevel = insertCoordinate.insertLevel;
				var insertIndex = insertCoordinate.insertIndex;
				
				var tempColumnOption = targetColumns[insertLevel][insertIndex];
				var tempCoordinate = !!tempColumnOption&&tempColumnOption.coordinate || {
					level:0,
					index:0,
					preIndex:-1
				};
				
				if(!tempColumnOption){
					if(colIndex > 0){
						columnOption.colspan = 1;// isNaN(columnOption.colspan)?1:columnOption.colspan+1;
					}
					arrayUtil.insert(targetColumns[insertLevel],insertIndex,columnOption);
				}else if(tempCoordinate.index != columnOption.coordinate.index){
					if(colIndex > 0){
						columnOption.colspan = 1;//isNaN(columnOption.colspan)?1:columnOption.colspan+1;
					}
					arrayUtil.insert(targetColumns[insertLevel],insertIndex,columnOption);
				}else{
					if(colIndex > 0){
						tempColumnOption.colspan = isNaN(tempColumnOption.colspan)?1:tempColumnOption.colspan+1;
					}
				}
			}
			t.datagrid('refactorView',opts,false);
			t.datagrid("fitColumns");
			arrayUtil.removeAt(opts.hiddenColumns,index,1);
		}
		
	}// if end
	
};