<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	//String id=request.getParameter("id");
	//out.println("接收到:"+id);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title></title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript" src="js/video/webVideoCtrl.js"></script>
<script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
<link href="css/demo.css" rel="stylesheet" rev="stylesheet"
	type="text/css" />
</head>

<body>
	<div id="divPlugin" class="plugin"></div>
	<script type="text/javascript">
		// 初始化插件
		var id = "";
		var szIP = "192.168.1.67";
		var szUsername = "admin";
		var szPassword = "asdf1234";
		var szPort = "80";
		var channel = 1;
		var result;
		// 全局保存当前选中窗口
		var g_iWndIndex = 0; //可以不用设置这个变量，有窗口参数的接口中，不用传值，开发包会默认使用当前选择窗口
		$(function() {
			if (-1 == WebVideoCtrl.I_CheckPluginInstall()) {
				/* $.messager.confirm('确认对话框', '您还未安装过插件,请点击确定下载！', function(r) {
					if (r) {
						window.open("js/video/WebComponents.exe");
					}
				});  */
				alert("您还未安装过插件,请点击确定下载");
				window.open("js/video/WebComponents.exe");
				return;
			}

			// 初始化插件参数及插入插件
			WebVideoCtrl.I_InitPlugin(790, 330, {
				iWndowType : 1,
				cbSelWnd : function(xmlDoc) {
					g_iWndIndex = $(xmlDoc).find("SelectWnd").eq(0).text();
				}
			});
			WebVideoCtrl.I_InsertOBJECTPlugin("divPlugin");
			WebVideoCtrl.I_ChangeWndNum(1);
			id = '<%=(String) request.getParameter("id")%>';

			//clickLogin();
			//setTimeout("clickStartRealPlay()",1000); 
			$.ajax({
				type : 'POST',
				url : "vedioDtu.html",
				data : {
					"dtuId" : id
				},
				success : function(msg) {
					//var data = eval(msg);
					//console.info(data);
					var object= msg.map;
					console.info(object.ip);
					szIP= object.ip;
					szUsername= object.username;
					szPassword= object.password;
					szPort= object.port;
					channel= object.path;
					clickLogin();
					setTimeout("clickStartRealPlay()",100); 
				}
			});

		});

		// 登录
		function clickLogin() {
			if ("" == szIP || "" == szPort) {
				return;
			}

			var iRet = WebVideoCtrl.I_Login(szIP, 1, szPort, szUsername,
					szPassword, {
						success : function(xmlDoc) {
							result = 1;
						},
						error : function() {
							result = 2;
						}
					});

			if (-1 == iRet) {
				result = 3;
			}
			return result;
		}

		// 开始预览
		function clickStartRealPlay() {
			var oWndInfo = WebVideoCtrl.I_GetWindowStatus(0), szInfo = "";
			var channel = channel;

			if ("" == szIP) {
				return;
			}

			if (oWndInfo != null) {// 已经在播放了，先停止
				WebVideoCtrl.I_Stop();
			}

			var iRet = WebVideoCtrl.I_StartRealPlay(szIP, {
				iWndIndex : 0,
				iStreamType : 1,
				iChannelID : channel,
				bZeroChannel : false
			});
		}

		function getQueryString(name) {
			//var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
			var r = window.location.search.substr(1);
			console.info(r);
			if (r != null)

				return unescape(r[2]);
			return null;
		}
	</script>
</body>
</html>
