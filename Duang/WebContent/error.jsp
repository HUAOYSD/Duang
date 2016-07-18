<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<h1> 
   娃娃你错老
   <br/><br/>
   错误代码：${param.code}
   错误消息：<%=request.getAttribute("msg") %>
</h1>