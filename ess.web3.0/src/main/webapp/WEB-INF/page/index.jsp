<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>养猪场养殖综合监控系统</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="extjs/resources/css/ext-all.css" rel="stylesheet"
	rev="stylesheet" type="text/css" />
<link href="css/style.css" rel="stylesheet" rev="stylesheet"
	type="text/css" />
<script type="text/javascript" src="extjs/ext-all.js"></script>
<script type="text/javascript" src="js/model/model.js"></script>
<script type="text/javascript" src="js/index.js"></script>
<style>
#west-panel{background-color:#10588d;     border-color:red;}
#west-panel div{background: transparent;color:#FFF;}
.x-tree-node-text a{color:#FFF;}
#west-panel td{background-color:#2181b5;}
.x-panel-default {
    border-color: #10588d;
    padding: 0;
}
#panel-1016-body{background-color:#2181b5;}
.x-accordion-hd .x-tool-img {background-color: transparent;
}
.x-tab-bar-default-top{background-color: transparent;
}
#tabbar-1014{background-image: -webkit-linear-gradient(top,#2181b5,#2181b5); }
#tabpanel-1013{margin-top: -4px;    margin-left: -4px;}
#panel-1010_header{background-color: transparent;}
</style>

  </head>
  
  <body>
    <!-- use class="x-hide-display" to prevent a brief flicker of the content -->
	<div id="west" class="x-hide-display"></div>
	<div id="center2" class="x-hide-display"></div>
	<div id="center1" class="x-hide-display"></div>
	<div id="props-panel" class="x-hide-display"
		style="width: 200px; height: 200px; overflow: hidden;"></div>
	<div id="south" class="x-hide-display">
		<p>south - generally for informational stuff, also could be for
			status bar</p>
	</div>
  </body>
</html>
