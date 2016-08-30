<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/home.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/common.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/left.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/js/easyui/themes/gray/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath( )%>/js/easyui/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/js/easyui/themes/IconExtension.css" />
<script type="text/javascript" src="<%=request.getContextPath() %>/js/easyui/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/layer/layer.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/home.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/extends.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/json.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/util.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/yxuiUtil.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/js/rightKey/css/normalize.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/js/rightKey/css/styles.css">
<html>
	<body>
		<menu class="right-menu">
			<li class="menu-item" onclick="refresh()">
				<button type="button" class="menu-btn" >
					<span class="menu-text">刷新本页</span>
				</button>
			</li>
			<li class="menu-separator"></li>
			<li class="menu-item" onclick="closeCurrentPage()">
				<button type="button" class="menu-btn">
					<span class="menu-text">关闭本页标签</span>
				</button>
			</li>
			<li class="menu-item" onclick="closeOtherPage()">
				<button type="button" class="menu-btn">
					<span class="menu-text">关闭其他标签</span>
				</button>
			</li>
			<li class="menu-item" onclick="closeLeftPage()">
				<button type="button" class="menu-btn">
					<span class="menu-text">关闭左侧标签</span>
				</button>
			</li>
			<li class="menu-item" onclick="closeRightPage()">
				<button type="button" class="menu-btn">
					<span class="menu-text">关闭右侧标签</span>
				</button>
			</li>
			<li class="menu-item" onclick="closeAllPage()">
				<button type="button" class="menu-btn">
					<span class="menu-text">关闭全部标签</span>
				</button>
			</li>
		</menu>
		<script type="text/javascript">
			var menu = document.querySelector('.right-menu');
			function showMenu(x, y) {
				menu.style.left = x + 'px';
				menu.style.top = y + 'px';
				menu.classList.add('show-menu');
			}
			function hideMenu() {
				menu.classList.remove('show-menu');
			}
			function onContextMenu(e) {
				var className = e.srcElement.className;
				var tabs = className.substring(0,5);
				if(tabs=="tabs-"){
					e.preventDefault();
					showMenu(e.pageX, e.pageY);
				}
			}
			function onMouseDown(e) {
				hideMenu();
				document.removeEventListener('mousedown', onMouseDown);
			}
		
			$(document).click(function(event) {  
				menu.classList.remove('show-menu'); 
				document.removeEventListener('mousedown', onMouseDown);
			});
			
			document.addEventListener('contextmenu', onContextMenu, false);
		</script>
	</body>
</html>
		