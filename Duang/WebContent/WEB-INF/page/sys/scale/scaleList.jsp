<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="UTF-8"%>
<% String path = request.getContextPath();%>
<%@ include file="/page/inc/inc.jsp"%>
<body class="easyui-layout">
	<div class="body" id="body_scale" region="center" style="border:0px;" >	
		  <!-- 查询条件区域 -->
		  <div id="scale_search_area" class="search_area query_conds_area">
		    <div class="conditon" id="scale_conditon">
				<form id="scaleQueryForm" method="post">
					  <table border="0">
						<tr height="35px">
						  	<td class="showRight"><span class="investPro-search">标名称：</span></td>
						  	<td><input name="name"/></td>
						 	<td class="showRight"><span class="investPro-search">产品名称：</span></td>
						  	<td><input name="productName"/></td>
						  	<td class="showRight"><span class="investPro-search">新品：</span></td>
						  	<td><select style="width: 140px;" class="easyui-combobox" name="productNew" data-options="panelHeight:'auto'">   
								    <option value="null" selected="selected">--请选择--</option>   
								    <option value="0">否</option>   
					    			<option value="1">是</option>
								</select> 
						  	</td>
						  	<td class="showRight"><span class="investPro-search">推荐：</span></td>
						  	<td><select style="width: 140px;" class="easyui-combobox" name="productTj" data-options="panelHeight:'auto'">   
								    <option value="null" selected="selected">--请选择--</option>   
								    <option value="0">否</option>   
					    			<option value="1">是</option>
								</select> 
						  	</td>
						</tr>
						<tr height="35px">
						  	<td class="showRight"><span class="investPro-search">可用理财券：</span></td>
						  	<td><select style="width: 140px;" class="easyui-combobox" name="useTicket" data-options="panelHeight:'auto'">   
								    <option value="null" selected="selected">--请选择--</option>   
								    <option value="0">不可以</option>   
					    			<option value="1">可以</option>
								</select> 
						  	</td>
						  	<td class="showRight"><span class="investPro-search">可转让：</span></td>
						  	<td><select style="width: 140px;" class="easyui-combobox" name="transfer" data-options="panelHeight:'auto'">   
								    <option value="null" selected="selected">--请选择--</option>   
								    <option value="0">不可以</option>   
					    			<option value="1">可以</option>
								</select> 
						  	</td>
						  	<td class="showRight"><span class="investPro-search">转让标：</span></td>
						  	<td><select style="width: 140px;" class="easyui-combobox" name="isTurn" data-options="panelHeight:'auto'">   
								    <option value="null" selected="selected">--请选择--</option>   
								    <option value="0">不是</option>   
					    			<option value="1">是</option>
								</select> 
						  	</td>
						  	<td class="showRight"><span class="investPro-search">状态：</span></td>
						  	<td><select style="width: 140px;" class="easyui-combobox" name="status" data-options="panelHeight:'auto'">   
								    <option value="null" selected="selected">--请选择--</option>   
								    <option value="0">新建标</option>   
								    <option value="1">可投入</option>   
					    			<option value="2">还有机会</option>
					    			<option value="3">已完成</option>
								</select> 
						  	</td>
						</tr>
						<tr height="35px">
							<td class="showRight"><span class="investPro-search">标签：</span></td>
						  	<td><input name="tags"/></td>
						  	<td class="showRight"><span class="investPro-search">开标时间：</span></td>
						  	<td><input name="beginDate1" type="text" class="easyui-datebox"></input></td>
						  	<td>&nbsp;&nbsp;——&nbsp;&nbsp;</td>
						  	<td><input name="beginDate2" type="text" class="easyui-datebox"></input></td>
						  	<td colspan="2">&nbsp;&nbsp;
								<a id="scaleQueryForm_Btn" class="easyui-linkbutton my-search-button" iconCls="icon-2012092109942" plain="true">查询</a>&nbsp;&nbsp;
						  		<a onclick="javascript:$('#scaleQueryForm').form('reset');" class="easyui-linkbutton my-search-button" iconCls="icon-reset" plain="true">重置</a>
						  	</td>
						</tr>
					  </table>
				</form>
		    </div>
		    <span class="openOrClose" id="scale_open_close">&nbsp;&nbsp;</span>
		  </div>
		  <!-- 数据表格区域 -->
		  <table id="scale" style="table-layout:fixed;" ></table>
	 	  <!-- 表格顶部工具按钮 -->
		  <div id="tt_toolbar_scale">
		      <a id="add_btn_scale" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
		      <a id="edit_btn_scale" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a> 
		      <a id="info_btn_scale" class="easyui-linkbutton" iconCls="icon-information" plain="true">详情</a> 
		      <a id="allot_btn_scale" class="easyui-linkbutton" iconCls="icon-wrench_orange" plain="true">借贷分配</a> 
		      <a id="match_btn_scale" class="easyui-linkbutton" iconCls="icon-zoom" plain="true">查询借贷匹配</a> 
	  	  </div>
	</div>
	<script type="text/javascript" src="<%=path %>/ui/sys/scale/scale.js"></script>
</body>