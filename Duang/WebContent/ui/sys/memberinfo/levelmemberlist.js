/**
 * 初始化
 */
$(function() {
	$('#memberinfo_open_close').on("click",function(){
		$('#memberinfo_conditon').toggle(80);
		setTimeout(domresize,100);//条件隐藏，改变表格高度
	});	
	loadmemberinfo("memberinfo!queryLevelMebmer.do");
});


/**
 * 改变表格宽高
 */
function domresize(){
	$('#memberinfo').datagrid('resize',{  
		height:$("#body_memberinfo").height()-$('#memberinfo_search_area').height()-8,
		width:$("#body_memberinfo").width()
	});
}


/**
 * 监听窗口大小变化
 */
window.onresize = function(){
	setTimeout(domresize,300);
};


/**
 * 加载列表
 * @memberOf {TypeName} 
 * @return {TypeName} 
 */
function loadmemberinfo(url, dataObj){
	$('#memberinfo').datagrid({
			height:$("#body_memberinfo").height()-$('#memberinfo_search_area').height()-8,
			width:$("#body_memberinfo").width(),
			loadMsg : "正在加载，请稍后...",
			singleSelect:true, 
			nowrap:true,
			rownumbers:true,
			pagination:true,
			pageSize:50,
			pageList:[50,100,150,200,250],
			sortOrder:'desc',
			method:'post',
			autoRowHeight : false,
			border : false,
			url :url,
			queryParams : dataObj,
			fitColumns : true,
			remoteSort : false,
			rowStyler: function(index,row){},
			columns : [ [
			             {field : "firtname", title : "客户姓名", width : $(this).width() * 0.1, align : "center" },
			             {field : "secondname", title : "推荐人", width : $(this).width() * 0.1, align : "center" },
			             {field : "thirdname", title : "二级推荐人", width : $(this).width() * 0.1, align : "center" },
			] ]
		});
}


/**
 * 查询
 */
$("#memberinfoQueryForm_Btn").on("click", function(){
	var dataStr = $('#memberinfoQueryForm').serialize();
	var urlStr = "{";
	$.each(dataStr.split("&"), function(i,n){
		var args = n.split("="); 
		if(args.length == 2){												
			if(args[1].isNotNull()){
				urlStr += "'"+args[0]+"':'"+args[1]+"',";
			}
		}
	});
	urlStr += "'submitTime':'"+(new Date().getTime())+"'}";
	var data = eval('('+urlStr+')');
	loadmemberinfo("memberinfo!queryLevelMebmer.do", data);
});

