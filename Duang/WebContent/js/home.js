//系统时间显示
//setInterval("document.getElementById('nowTime').innerHTML=new Date().toLocaleString()+' 星期'+'日一二三四五六'.charAt(new Date().getDay());",1000);

//获取系统时间，将时间以指定格式显示到页面。  
/*function systemTime()  
{  
    //获取系统时间。  
    var dateTime=new Date();  
    document.getElementById("nowTime").innerHTML=dateTime.toLocaleString()+' 星期'+'日一二三四五六'.charAt(new Date().getDay());  
    //每隔1000ms执行方法systemTime()。  
    setTimeout("systemTime()",1000);  
}  */
var $parent = self.parent.$;
var selectedTab = null;
$(function(){
	//隐藏显示查询条件区域
	$('#openOrClose').on("click",function(){
		$('#conditon').toggle(80);
		setTimeout(domresize,100);//条件隐藏，改变表格高度
	});	
});

$(function() {
	//中间部分tab
	$('#tabs').tabs({  
		border:false,
		fit:true
	});
	
	$('.index_panel').panel({  
	  width:300,  
	  height:200,  
	  closable:true,
	  minimizable:true,
	  title: 'My Panel'
	});
	
});

//添加一个选项卡面板 
function addTab(title, url,icon){
	if(!$('#tabs').tabs('exists', title)){
		$('#tabs').tabs('add',{  
			title:title,
			border:false,
			iconCls:icon,
			content:'<iframe src="'+url+'" frameBorder="0" border="0" scrolling="no" style="width: 100%; height: 100%;"/>',  
			closable:true
		});
	} else {
		selectedTab = $('#tabs').tabs('select', title);
	}
}

/**
 * 关闭右键弹窗，并且移除监听
 */
function closeRightMenu(){
	menu.classList.remove('show-menu');
	document.removeEventListener('mousedown', onMouseDown);
}

/**
 * 刷新本页
 */
function refresh(){
	var currTab =  $('#tabs').tabs('getSelected'); //获得当前tab
	var url = $(currTab.panel('options').content).attr('src');
	$('#tabs').tabs('update', {
	    tab : currTab,
	    options : {
	    	content:'<iframe src="'+url+'" frameBorder="0" border="0" scrolling="no" style="width: 100%; height: 100%;"/>' 
	    }
	});
	closeRightMenu();
}

/**
 * 关闭当前页
 */
function closeCurrentPage(){
	var currentTab =  $('#tabs').tabs('getSelected'); //获得当前tab
	var currentTabIndex = $('#tabs').tabs('getTabIndex',currentTab);
	$('#tabs').tabs('close',currentTabIndex);
	closeRightMenu();
}

/**
 * 关闭其他标签
 */
function closeOtherPage(){
	//获得当前tab
	var currentTab =  $('#tabs').tabs('getSelected'); 
	//当前tab的标题
	var currentTabTitle = currentTab.panel('options').title;
	//遍历所有打开的tab
	$(".tabs li").each(function(index, obj) {
        //关闭除了当前tab  
        var tabTitle = $(".tabs-closable", this).text();
        if(tabTitle != currentTabTitle){
        	$('#tabs').tabs('close', tabTitle);
        }
    }); 
	closeRightMenu();
}

/**
 * 关闭左边的标签
 */
function closeLeftPage(){
	//获得当前tab
	var currentTab =  $('#tabs').tabs('getSelected'); 
	//当前tab的标题
	var currentTabIndex = $('#tabs').tabs('getTabIndex',currentTab);
	$(".tabs li").each(function(index, obj) {
		if(index < currentTabIndex){
	        //关闭除了当前tab  
	        var tabTitle = $(".tabs-closable", this).text();
	        $('#tabs').tabs('close', tabTitle);
		}
    }); 
	closeRightMenu();
}

/**
 * 关闭右边的标签
 */
function closeRightPage(){
	//获得当前tab
	var currentTab =  $('#tabs').tabs('getSelected'); 
	//当前tab的标题
	var currentTabIndex = $('#tabs').tabs('getTabIndex',currentTab);
	$(".tabs li").each(function(index, obj) {
		if(index > currentTabIndex){
	        //关闭除了当前tab  
	        var tabTitle = $(".tabs-closable", this).text();
	        $('#tabs').tabs('close', tabTitle);
		}
    }); 
	closeRightMenu();
}

/**
 * 关闭全部的标签
 */
function closeAllPage(){
	$(".tabs li").each(function(index, obj) {
        //关闭除了当前tab  
        var tabTitle = $(".tabs-closable", this).text();
        $('#tabs').tabs('close', tabTitle);
    }); 
	closeRightMenu();
}

