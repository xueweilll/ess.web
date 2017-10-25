var tabs;

Ext.require([ '*' ]);
var ploy, oper, menuStore;
Ext
		.onReady(function() {
			//启动悬浮提示(在你验证非法时、和现实提示语句等)
			Ext.QuickTips.init();

			// NOTE: This is an example showing simple state management. During
			// development,
			// it is generally best to disable state management as
			// dynamically-generated
			// ids
			// can change across page loads, leading to unpredictable results.
			// The
			// developer
			// should ensure that stable state ids are set for stateful
			// components in
			// real apps.
			Ext.state.Manager.setProvider(Ext.create('Ext.state.CookieProvider'));
			/*
			 * var menu0,menu1,menu2,menu3,menu4,menu5;
			 * 
			 * Ext.Ajax.request({ url: 'getMenu.html',
			 * //fields:['menu0','menu1','menu2','menu3','menu4','menu5'],
			 * success: function(response){ //alert(response.responseText); var
			 * obj = Ext.decode(response.responseText); //alert(obj);
			 * menu0=obj.menu0; Ext.Msg.alert('Status', response.responseText);
			 * menu1=obj.menu1; menu2=obj.menu2; menu3=obj.menu3;
			 * menu4=obj.menu4; menu5=obj.menu5; } });
			 */
			//启动教研
			//ModelWindow();
			//定义Menu类
			Ext.define('Menu', {
				//alias:"Person", 此处可为Menu类定义别名
				//此处为Menu类继承Ext.data.Model类
				extend : 'Ext.data.Model',
				//次为参数
				fields : [ {
					name : 'id',
					type : 'int'
				}, {
					name : 'url',
					type : 'string'
				} ]
			//此处为验证  实例化menu后，可通过menu.validate（） 来验证
//				, validations: [
//				                {type: 'presence',  field: 'age'},
//				                {type: 'length',    field: 'name',     min: 2},
//				                {type: 'inclusion', field: 'gender',   list: ['Male', 'Female']},
//				                {type: 'exclusion', field: 'username', list: ['Admin', 'Operator']},
//				                {type: 'format',    field: 'username', matcher: /([a-z]+)[0-9]{2,3}/}
//				            ]
			});
			//实例化Ext.data.Store类
			menuStore = Ext.create('Ext.data.Store', {
				model : 'Menu',
				proxy : {
					type : 'ajax',
					method : 'POST',
					url : 'menuTree.html',
					asyn : false,
					reader : {
						type : 'json',
						root : 'listMenu',
						successProperty : 'success'
					}
				},
				autoLoad : true
			});
			var viewport = Ext
					.create(
							'Ext.Viewport',
							{
								id : 'border-example',
								//布局為邊界
								layout : 'border',
								items : [
										// create instance immediately
										Ext
												.create(
														'Ext.Component', //此组件类似渲染一个DIV
														{
															region :'north',
															height :100, // give
																			// north
																			// and
																			// south
																			// regions
																			// a
															// height
															collapsed : true,
															margins : '0 0 5 0',
															autoEl : {
																html : '<img src="image/top.jpg" style="width:100%;height:100px;position: absolute;"/><img src="image/logo.png" style="width:570px;height:76px;top:12px;position: absolute;"/>'
															}
														}),
										{
											// lazily created panel
											// (xtype:'panel' is default)
											region : 'south',
											contentEl : 'south',
											split : false,
											border : false,
											height : 25,
											minSize : 100,
											maxSize : 200,
											collapsible : false,
											collapsed : false,
											title : '<div style="text-align:right;padding-right:30px;">copyright©2015&nbsp;&nbsp;&nbsp;<a href="http://www.jsmjzl.com/"  target="_blank"> 江苏明鹿物联技术股份有限公司</a>版权所有</div>',
											margins : '0 0 0 0'
										},
										
										{
											region : 'west',
											//stateId : 'navigation-panel',
											id : 'west-panel', // see
																// Ext.getCmp()
																// below
											title : '功能菜单',
											split : true,
											width : 140,
											minWidth : 160,
											maxWidth : 400,
											collapsible : true,
											animCollapse : true,
											margins : '-5 0 0 0',
											layout : 'accordion',
											/*
											 * items :
											 * [Ext.create('Ext.tree.Panel', {
											 * width : 200, height : 450, store :
											 * menuStore, lines : false,
											 * rootVisible : false, border :
											 * false })]
											 */
											bodyStyle:'background-color:#10588d;',
											items : [
													{
														contentEl : 'west',
														title : '首页',
														html : "<div id='menu0'></div>",
														iconCls : 'menu0', // see
																			// the
																			// HEAD
																			// section
																			// for
													// style used
															bodyStyle:'background-color:#2181b5;'
													},
													{
														title : '权限管理',
														html : "<div id='menu1'></div>",
														bodyStyle:'background-color:#2181b5;',
														iconCls : 'menu1' // see
																			// the
																			// HEAD
																			// section
																			// for
													// style used
													},
													{
														title : '系统设置',
														html : "<div id='menu2'></div>",
														bodyStyle:'background-color:#2181b5;',
														iconCls : 'menu2'
													},
													{
														title : '远程监控',
														html : "<div id='menu3'></div>",
														bodyStyle:'background-color:#2181b5;',
														iconCls : 'menu3'
													},
													{
														title : '数据分析',
														html : "<div id='menu4'></div>",
														bodyStyle:'background-color:#2181b5;',
														iconCls : 'menu4'
													},
													{
														title : '系统信息',
														html : "<div id='menu5'></div>",
														bodyStyle:'background-color:#2181b5;',
														iconCls : 'menu5'
													} ]

										},
										// in this instance the TabPanel is not
										// wrapped by another panel
										// since no title is needed, this Panel
										// is added directly
										// as a Container
										tabs = Ext
												.create(
														'Ext.tab.Panel',
														{
															region : 'center', // a
																				// center
																				// region
																				// is
																				// ALWAYS
															// required for
															// border layout
															deferredRender : false,
															activeTab : 1, // first
																			// tab
																			// initially
																			// active
															items : [
															/*
															 * { contentEl :
															 * 'center1', id :
															 * 'desktop', title :
															 * '桌面', // autoLoad :
															 * 'page.html',//自动加载面板体的默认连接
															 * html : '<iframe
															 * src="page.html"
															 * id="desktop_iframe"
															 * noresize="noresize"
															 * width="100%"
															 * height="100%"
															 * frameborder="no"
															 * border="0"
															 * marginwidth="0"
															 * marginheight="0"
															 * scrolling="no"
															 * allowtransparency="yes"></iframe>',
															 * bodyStyle :
															 * 'background-color:#ffffff', //
															 * 设置面板体的颜色 //
															 * closable: true,
															 * autoScroll : true },
															 */{
																contentEl : 'center2',
																id : 'map',
																title : '总览',
																
																// autoLoad :
																// 'map.html',//自动加载面板体的默认连接
																html : '<iframe src="home.html" id="map_iframe" noresize="noresize" width="100%" height="100%" frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="no" allowtransparency="yes"></iframe>',
																bodyStyle : 'background-color:#ffffff', // 设置面板体的颜色
																autoScroll : true
															} ]
														}) ]
							});

			/*
			 * // get a reference to the HTML element with id "hideit" and add a
			 * click listener to it Ext.get("hideit").on('click', function(){ //
			 * get a reference to the Panel that was created with id =
			 * 'west-panel' var w = Ext.getCmp('west-panel'); // expand or
			 * collapse that Panel based on its collapsed property state
			 * w.collapsed ? w.expand() : w.collapse(); });
			 */

			var store = Ext
					.create(
							'Ext.data.TreeStore',
							{
								root : {
									expanded : true,
									children : [
									// { text : "<a
									// href='javascript:void(0)'onclick=\"menulink('1-1')\">桌面</a>",
									// leaf : true },
									{
										text : "<a href='javascript:void(0)'onclick=\"menulink('1-2')\">总览</a>",
										icon : 'image/map.png',
										leaf : true
									} ]
								}
							});

			Ext.create('Ext.tree.Panel', {
				width : 200,
				height : 150,
				store : store,
				rootVisible : false,
				renderTo : 'menu0',
				border : false
			});

			store = Ext
					.create(
							'Ext.data.TreeStore',
							{
								root : {
									expanded : true,
									children : [
											{
												text : "<a href='javascript:void(0)' onclick=\"menulink('2-1')\">用户分组</a>",
												icon : 'image/limit.png',
												leaf : true
											},
											{
												text : "<a href='javascript:void(0)' onclick=\"menulink('2-2')\">用户管理</a>",
												icon : 'image/user.png',
												leaf : true
											} ]
								}
							});

			Ext.create('Ext.tree.Panel', {
				width : 200,
				height : 150,
				store : store,
				rootVisible : false,
				renderTo : 'menu1',
				border : false
			});

			store = Ext
					.create(
							'Ext.data.TreeStore',
							{
								root : {
									expanded : true,
									children : [
											{
												text : "<a href='javascript:void(0)' onclick=\"menulink('3-1')\">区域管理</a>",
												icon : 'image/area.png',
												leaf : true
											},
											{
												text : "<a href='javascript:void(0)' onclick=\"menulink('3-2')\">站点管理</a>",
												icon : 'image/site.png',
												leaf : true
											},
											{
												text : "<a href='javascript:void(0)' onclick=\"menulink('3-3')\">报警管理</a>",
												icon : 'image/site.png',
												leaf : true
											} /*
												 * , { text : "<a
												 * href='javascript:void(0)'
												 * onclick=\"menulink('3-3')\">设备管理</a>",
												 * leaf : true }, { text : "<a
												 * href='javascript:void(0)'
												 * onclick=\"menulink('3-4')\">分组管理</a>",
												 * leaf : true }, { text : "<a
												 * href='javascript:void(0)'
												 * onclick=\"menulink('3-5')\">参数设置</a>",
												 * leaf : true }
												 */]
								}
							});

			Ext.create('Ext.tree.Panel', {
				width : 200,
				height : 150,
				store : store,
				rootVisible : false,
				renderTo : 'menu2',
				border : false
			});

			store = Ext
					.create(
							'Ext.data.TreeStore',
							{
								root : {
									expanded : true,
									children : [ {
										text : "<a href='javascript:void(0)' onclick=\"menulink('4-1')\">集中监控</a>",
										icon : 'image/allmt.png',
										leaf : true
									},{
										text : "<a href='javascript:void(0)' onclick=\"menulink('4-2')\">集中报警</a>",
										icon : 'image/allmt.png',
										leaf : true
									}]
								}
							});

			Ext.create('Ext.tree.Panel', {
				width : 200,
				height : 150,
				store : store,
				rootVisible : false,
				renderTo : 'menu3',
				border : false
			});
			
			
			

			store = Ext
					.create(
							'Ext.data.TreeStore',
							{
								root : {
									expanded : true,
									children : [
											{
												text : "<a href='javascript:void(0)' onclick=\"menulink('5-3')\">历史报警</a>",
												icon : 'image/history.png',
												leaf : true
											},    
											{
												text : "<a href='javascript:void(0)' onclick=\"menulink('5-1')\">历史数据</a>",
												icon : 'image/history.png',
												leaf : true
											},
											{
												text : "<a href='javascript:void(0)' onclick=\"menulink('5-2')\">系统日志</a>",
												icon : 'image/log.png',
												leaf : true
											} ]
								}
							});

			Ext.create('Ext.tree.Panel', {
				width : 200,
				height : 150,
				store : store,
				rootVisible : false,
				renderTo : 'menu4',
				border : false
			});
		});

function addTab(id, title, url) {
	var tab = tabs.child('#' + id);
	if (tab == null) {
		// alert("aa");
		tabs
				.add(
						{
							closable : true,
							// html : 'Tab Body ',
							id : id,
							// iconCls: 'tabs',
							// autoLoad : url,
							html : '<iframe id='
									+ (id + '_iframe')
									+ ' src='
									+ url
									+ ' noresize="noresize" width="100%" height="100%" frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="no" allowtransparency="yes"></iframe>',
							// alert(html),
							title : title,
							autoScroll : true
						}).show();
	} else {
		// alert("bb");
		tab.show();
		tabs.setActiveTab(tab);
	}
}

function menulink(id) {
	var id, url, hz = ".html";
	switch (id) {
	/*
	 * case "1-1" : title = "桌面"; id = "desktop"; url = ""; break;
	 */
	case "1-2":
		title = "总览";
		id = "map";
		url = "home";
		break;
	case "2-1":
		title = "用户分组";
		id = "user";
		url = "user";
		break;
	case "2-2":
		title = "用户管理";
		id = "limit";
		url = "limit";
		break;
	case "3-1":
		title = "区域管理";
		id = "area";
		url = "station";
		break;
	case "3-2":
		title = "站点管理";
		id = "site";
		url = "dtu";
		break;
	case "3-3":
		title = "报警管理";
		id = "eqt";
		url = "lmu";
		break;
	case "3-4":
		title = "分组管理";
		id = "group";
		url = "group";
		break;
	case "3-5":
		title = "参数设置";
		id = "set";
		url = "set";
		break;
	case "4-1":
		title = "集中监控";
		id = "allmt";
		url = "possess";
		break;
	case "4-2":
		title = "集中报警";
		id = "police";
		url = "police";
		break;
	case "5-1":
		title = "历史数据";
		id = "history";
		url = "history";
		break;
	case "5-2":
		title = "系统日志";
		id = "log";
		url = "record";
		break;
	case "5-3":
		title = "历史报警";
		id = "historyPolice";
		url = "historyPolice";
		break;	
	}
	/*
	 * lmustore.on('load', function() { lmustore.each(function(record) {
	 * alert(record.get('code')); }); });
	 */
	// alert(url);
	var result = false;
	menuStore.each(function(record) {
		// alert(record.get('url'));
		if (url == record.get('url')) {
			url = url + hz;
			addTab(id, title, url);
			result = true;
			return false;
		}
	});
	if (!result) {
		Ext.Msg.alert('警告', '您无权限访问此页面，如需访问请通知管理员修改权限！');
	}
}
//就此打住
var modelWindow, Id;
function BindModel(id) {
	Ext.getCmp('status').setValue("编辑");
	Id = id;
	oper = 1;
	ploy.each(function(record) {
		// alert(record.get('id'));
		if (record.get('id') == id) {
			Ext.getCmp('ployname').setValue(record.get('modelName'));
			Ext.getCmp('hr0').setValue(record.get('preTime'));
			Ext.getCmp('hr4').setValue(record.get('speed'));
			Ext.getCmp('hr5').setValue(record.get('t1'));
			Ext.getCmp('hr6').setValue(record.get('v1'));
			Ext.getCmp('hr7').setValue(record.get('t2'));
			Ext.getCmp('hr8').setValue(record.get('v2'));
			Ext.getCmp('hr9').setValue(record.get('t3'));
			Ext.getCmp('hr10').setValue(record.get('v3'));
			Ext.getCmp('hr11').setValue(record.get('t4'));
			Ext.getCmp('hr12').setValue(record.get('v4'));
			Ext.getCmp('hr13').setValue(record.get('t5'));
			Ext.getCmp('hr14').setValue(record.get('v5'));
			Ext.getCmp('hr15').setValue(record.get('t6'));
			Ext.getCmp('hr16').setValue(record.get('v6'));
			Ext.getCmp('hr17').setValue(record.get('t7'));
			Ext.getCmp('hr18').setValue(record.get('v7'));
			Ext.getCmp('hr19').setValue(record.get('t8'));
			Ext.getCmp('hr20').setValue(record.get('v8'));
			return false;
		}
	});

	modelWindow.show();
}

function DelModel(id) {
	Id = id;
	oper = 2;
	ModelEdit();
}

/*function ModelEdit() {
	var model = Ext.create('Model', {
		id : Id,
		modelName : Ext.getCmp('ployname').getValue(),
		preTime : Ext.getCmp('hr0').getValue(),
		speed : Ext.getCmp('hr4').getValue(),
		t1 : Ext.getCmp('hr5').getValue(),
		v1 : Ext.getCmp('hr6').getValue(),
		t2 : Ext.getCmp('hr7').getValue(),
		v2 : Ext.getCmp('hr8').getValue(),
		t3 : Ext.getCmp('hr9').getValue(),
		v3 : Ext.getCmp('hr10').getValue(),
		t4 : Ext.getCmp('hr11').getValue(),
		v4 : Ext.getCmp('hr12').getValue(),
		t5 : Ext.getCmp('hr13').getValue(),
		v5 : Ext.getCmp('hr14').getValue(),
		t6 : Ext.getCmp('hr15').getValue(),
		v6 : Ext.getCmp('hr16').getValue(),
		t7 : Ext.getCmp('hr17').getValue(),
		v7 : Ext.getCmp('hr18').getValue(),
		t8 : Ext.getCmp('hr19').getValue(),
		v8 : Ext.getCmp('hr20').getValue()
	});
	Ext.Ajax.request({
		url : 'modelEdit.html',
		params : {
			model : Ext.encode(model.data),
			oper : oper
		},
		success : function(response) {
			var text = response.responseText;
			var result = Ext.decode(text).result;
			switch (oper) {
			case 0:
				if (result) {
					ploy.reload();
				} else {
					Ext.Msg.alert("调压策略添加失败！");
				}
				break;
			case 1:
				if (result) {
					ploy.reload();
				} else {
					Ext.Msg.alert("调压策略更新失败！");
				}
				break;
			case 2:
				if (result) {
					ploy.reload();
				} else {
					Ext.Msg.alert("调压策略删除失败！");
				}
				break;
			}
		}
	});
}

function ModelClear() {
	Id = 0;
	Ext.getCmp('status').setValue("新建");
	Ext.getCmp('ployname').setValue("");
	Ext.getCmp('hr0').setValue("");
	Ext.getCmp('hr4').setValue("");
	Ext.getCmp('hr5').setValue("");
	Ext.getCmp('hr6').setValue("");
	Ext.getCmp('hr7').setValue("");
	Ext.getCmp('hr8').setValue("");
	Ext.getCmp('hr9').setValue("");
	Ext.getCmp('hr10').setValue("");
	Ext.getCmp('hr11').setValue("");
	Ext.getCmp('hr12').setValue("");
	Ext.getCmp('hr13').setValue("");
	Ext.getCmp('hr14').setValue("");
	Ext.getCmp('hr15').setValue("");
	Ext.getCmp('hr16').setValue("");
	Ext.getCmp('hr17').setValue("");
	Ext.getCmp('hr18').setValue("");
	Ext.getCmp('hr19').setValue("");
	Ext.getCmp('hr20').setValue("");
}

function ModelWindow() {
	modelWindow = Ext
			.create(
					'Ext.window.Window',
					{
						width : 460,
						height : 420,
						closeAction : 'hide',
						overflow : true,
						title : '调压曲线',
						layout : 'fit',
						border : false,
						resizable : false,
						modal : true,
						items : [ {
							xtype : 'form',
							id : 'contral',
							width : 450,
							height : 390,
							layout : {
								type : 'table',
								columns : 2
							},
							items : [
									{
										xtype : 'textfield',
										id : 'status',
										margin : '20 10 0 20',
										labelWidth : 60,
										fieldLabel : '当前状态'
									},
									{
										xtype : 'textfield',
										id : 'ployname',
										margin : '20 20 0 0',
										labelWidth : 60,
										fieldLabel : '调压策略',
										allowBlank : false,
										blankText : '调压策略不能为空'
									},
									{
										xtype : 'textfield',
										id : 'hr0',
										margin : '10 10 0 20',
										regex : /(^([2-9])$)|(^([1-9][0-9])$)|(^([0-1][0-7][0-9])$)|(^([0-1][0-8][0])$)/,
										regexText : '预热时间必须大于2小于等于180分钟',
										labelWidth : 60,
										fieldLabel : '预热时间',
										allowBlank : false,
										blankText : '预热时间不能为空'
									},
									{
										xtype : 'textfield',
										id : 'hr4',
										margin : '10 20 0 0',
										regex : /(^([1-9])$)|(^([1][0-9])$)|(^([2][0])$)/,
										regexText : '调压速度必须大于1小于等于20',
										labelWidth : 60,
										fieldLabel : '调压速度',
										allowBlank : false,
										blankText : '调压速度不能为空'
									},
									{
										xtype : 'textfield',
										id : 'hr5',
										margin : '10 10 0 20',
										labelWidth : 60,
										regex : /(^([0-1][0-9]|[2][0-3])([0-5][0-9])$)|(^([0-1][0-9]|[2][0-4])([0][0])$)/,
										regexText : '调压时间格式不正确，例如：2230代表22:30，2400代表关闭该时间点调压',
										fieldLabel : '时间1'
									},
									{
										xtype : 'textfield',
										id : 'hr6',
										margin : '10 20 0 0',
										labelWidth : 60,
										regex : /(^([1][8-9][0-9]$))|(^([2][0-9][0-9]$))|((^([3][0-1][0-9]$))|(^([3][0-2][0]$)))/,
										regexText : '预设电压只能为180到320',
										fieldLabel : '电压1'
									},
									{
										xtype : 'textfield',
										id : 'hr7',
										margin : '10 10 0 20',
										labelWidth : 60,
										regex : /(^([0-1][0-9]|[2][0-3])([0-5][0-9])$)|(^([0-1][0-9]|[2][0-4])([0][0])$)/,
										regexText : '调压时间格式不正确，例如：2230代表22:30，2400代表关闭该时间点调压',
										fieldLabel : '时间2'
									},
									{
										xtype : 'textfield',
										id : 'hr8',
										margin : '10 20 0 0',
										labelWidth : 60,
										regex : /(^([1][8-9][0-9]$))|(^([2][0-9][0-9]$))|((^([3][0-1][0-9]$))|(^([3][0-2][0]$)))/,
										regexText : '预设电压只能为180到320',
										fieldLabel : '电压2'
									},
									{
										xtype : 'textfield',
										id : 'hr9',
										margin : '10 10 0 20',
										labelWidth : 60,
										regex : /(^([0-1][0-9]|[2][0-3])([0-5][0-9])$)|(^([0-1][0-9]|[2][0-4])([0][0])$)/,
										regexText : '调压时间格式不正确，例如：2230代表22:30，2400代表关闭该时间点调压',
										fieldLabel : '时间3'
									},
									{
										xtype : 'textfield',
										id : 'hr10',
										margin : '10 20 0 0',
										labelWidth : 60,
										regex : /(^([1][8-9][0-9]$))|(^([2][0-9][0-9]$))|((^([3][0-1][0-9]$))|(^([3][0-2][0]$)))/,
										regexText : '预设电压只能为180到320',
										fieldLabel : '电压3'
									},
									{
										xtype : 'textfield',
										id : 'hr11',
										margin : '10 10 0 20',
										labelWidth : 60,
										regex : /(^([0-1][0-9]|[2][0-3])([0-5][0-9])$)|(^([0-1][0-9]|[2][0-4])([0][0])$)/,
										regexText : '调压时间格式不正确，例如：2230代表22:30，2400代表关闭该时间点调压',
										fieldLabel : '时间4'
									},
									{
										xtype : 'textfield',
										id : 'hr12',
										margin : '10 20 0 0',
										labelWidth : 60,
										regex : /(^([1][8-9][0-9]$))|(^([2][0-9][0-9]$))|((^([3][0-1][0-9]$))|(^([3][0-2][0]$)))/,
										regexText : '预设电压只能为180到320',
										fieldLabel : '电压4'
									},
									{
										xtype : 'textfield',
										id : 'hr13',
										margin : '10 10 0 20',
										labelWidth : 60,
										regex : /(^([0-1][0-9]|[2][0-3])([0-5][0-9])$)|(^([0-1][0-9]|[2][0-4])([0][0])$)/,
										regexText : '调压时间格式不正确，例如：2230代表22:30，2400代表关闭该时间点调压',
										fieldLabel : '时间5'
									},
									{
										xtype : 'textfield',
										id : 'hr14',
										margin : '10 20 0 0',
										labelWidth : 60,
										regex : /(^([1][8-9][0-9]$))|(^([2][0-9][0-9]$))|((^([3][0-1][0-9]$))|(^([3][0-2][0]$)))/,
										regexText : '预设电压只能为180到320',
										fieldLabel : '电压5'
									},
									{
										xtype : 'textfield',
										id : 'hr15',
										margin : '10 10 0 20',
										labelWidth : 60,
										regex : /(^([0-1][0-9]|[2][0-3])([0-5][0-9])$)|(^([0-1][0-9]|[2][0-4])([0][0])$)/,
										regexText : '调压时间格式不正确，例如：2230代表22:30，2400代表关闭该时间点调压',
										fieldLabel : '时间6'
									},
									{
										xtype : 'textfield',
										id : 'hr16',
										margin : '10 20 0 0',
										labelWidth : 60,
										regex : /(^([1][8-9][0-9]$))|(^([2][0-9][0-9]$))|((^([3][0-1][0-9]$))|(^([3][0-2][0]$)))/,
										regexText : '预设电压只能为180到320',
										fieldLabel : '电压6'
									},
									{
										xtype : 'textfield',
										id : 'hr17',
										margin : '10 10 0 20',
										labelWidth : 60,
										regex : /(^([0-1][0-9]|[2][0-3])([0-5][0-9])$)|(^([0-1][0-9]|[2][0-4])([0][0])$)/,
										regexText : '调压时间格式不正确，例如：2230代表22:30，2400代表关闭该时间点调压',
										fieldLabel : '时间7'
									},
									{
										xtype : 'textfield',
										id : 'hr18',
										margin : '10 20 0 0',
										labelWidth : 60,
										regex : /(^([1][8-9][0-9]$))|(^([2][0-9][0-9]$))|((^([3][0-1][0-9]$))|(^([3][0-2][0]$)))/,
										regexText : '预设电压只能为180到320',
										fieldLabel : '电压7'
									},
									{
										xtype : 'textfield',
										id : 'hr19',
										margin : '10 10 0 20',
										labelWidth : 60,
										regex : /(^([0-1][0-9]|[2][0-3])([0-5][0-9])$)|(^([0-1][0-9]|[2][0-4])([0][0])$)/,
										regexText : '调压时间格式不正确，例如：2230代表22:30，2400代表关闭该时间点调压',
										fieldLabel : '时间8'
									},
									{
										xtype : 'textfield',
										id : 'hr20',
										margin : '10 20 0 0',
										labelWidth : 60,
										regex : /(^([1][8-9][0-9]$))|(^([2][0-9][0-9]$))|((^([3][0-1][0-9]$))|(^([3][0-2][0]$)))/,
										regexText : '预设电压只能为180到320',
										fieldLabel : '电压8'
									},
									{
										xtype : 'button',
										text : '确定',
										width : 40,
										margin : '10 10 20 180',
										handler : function() {
											if (!Ext.getCmp('contral')
													.isValid()) {
												return false;
											}
											ModelEdit();
										}
									}, {
										xtype : 'button',
										text : '取消',
										width : 40,
										margin : '10 0 20 0',
										handler : function() {
											modelWindow.hide();
										}
									} ]
						} ]
					});
}*/

/*
 * function allComResize() { }
 */