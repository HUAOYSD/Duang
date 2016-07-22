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
$(function(){
	//systemTime();
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
		$('#tabs').tabs('select', title);
	}
}