﻿var store, dtustore, lmustore;
var itemsPerPage = 20, pareId = 0;
var alarmTypeComboBox = [[0, '请选择...'], [1, '过流'], [2, '欠流'],
		[3, '过压'], [4, '欠压'], [5, '过温'], [6, '上管异常'], [7, '下管异常']];
Ext.onReady(function() {
	Ext.create('Ext.container.Viewport', {
		title : 'Fit Layout',
		margin : '0 0 0 0',
		width : '100%',
		height : '100%',
		layout : 'border',
		items : [{
					title : '区域列表',
					width : 200,
					border : false,
					collapsible : true,
					split : true,
					region : 'west',
					html : "<div id='treeview' style='width:100%;height:100%'></div>"
				}, {
					border : false,
					width : '100%',
					height : '100%',
					region : 'center',
					html : "<div id='dataview' style='width:100%;height:100%'></div>"
				}],
		renderTo : Ext.getBody()
	});

	CreateTree();
	CreatePanel();
});

function CreateTree() {
	Ext.define('Marker', {
				extend : 'Ext.data.Model',
				fields : [{
							name : 'id',
							type : 'string'
						}, {
							name : 'text',
							type : 'string'
						}, {
							name : 'objData',
							type : 'Station'
						}]
			});
	store = Ext.create('Ext.data.TreeStore', {
				model : 'Marker',
				proxy : {
					type : 'ajax',
					url : 'stationTree.html'
				},
				reader : {
					type : 'json',
					root : 'children',
					successProperty : 'success'
				},
				clearOnLoad : true,
				root : {
					id : '0',
					text : '所有区域',
					expanded : true
				},
				autoLoad : false

			});
	
	
	lmustore = Ext.create('Ext.data.Store', {
				model : 'Lmu1',
				proxy : {
					type : 'ajax',
					method : 'POST',
					url : 'listLmuLimit.html',
					extraParams : {
						dtuId : 0
					},
					reader : {
						type : 'json',
						root : 'lmuLimitList',
						successProperty : 'success',
						totalProperty : 'totalCount'
					}
				},
				autoLoad : true
			});
	dtustore = Ext.create('Ext.data.Store', {
				model : 'Dtu',
				proxy : {
					type : 'ajax',
					method : 'POST',
					url : 'createDtus.html',

					extraParams : {
						pareId : pareId
					},
					reader : {
						type : 'json',
						root : 'dtuList',
						successProperty : 'success'
					}
				},
				autoLoad : true,
				listeners : {
					load : function(style, records, options) {
						// alert('aa');
						var rs = Ext.create('Dtu', {
									id : 0,
									code : '',
									name : '请选择...',
									address : '',
									IP : '',
									dtuX : 0.0,
									dtuY : 0.0,
									stationId : 0,
									level : 0,
									port : 0,
									isAlarm : false,
									isActive : false,
									isTogether : false,
									eqNum : 0
								});
						dtustore.insert(0, rs);
					}
				}
			});

	Ext.create('Ext.tree.Panel', {
				renderTo : 'treeview',
				border : false,
				width : '100%',
				height : '100%',
				store : store,
				rootVisible : false,
				listeners : {
					itemclick : function(view, record, item, index, e, eOpts) {
						// var pareId = record.get('objData').pareid;
						// var id = record.get('objData').id;
						// var stationname = record.get('objData').stationname;
						
						var pareId = record.get('objData').id;
						var pa = {
							pareId : pareId
						}
						Ext.apply(dtustore.proxy.extraParams, pa);
						dtustore.reload();
						Ext.getCmp('dtu').setValue(0); 
					}
				}
			});
}

function CreatePanel() {
	Ext.create('Ext.panel.Panel', {
		renderTo : 'dataview',
		border : false,
		width : '100%',
		height : '100%',
		layout : 'border',
		// store : store,
		items : [{
					xtype : 'panel',
					title : '查询区域',
					height : 70,
					border : false,
					region : 'north',
					html : "<div id='searchview' style='width:100%;height:100%'></div>"
				}, {
					xtype : 'panel',
					title : '设备信息',
					border : false,
					margin : '0 0 0 0',
					region : 'center',
					html : "<div id='listview' style='width:100%;height:100%'></div>"
				}]
	});
	Ext.create('Ext.panel.Panel', {
				renderTo : 'searchview',
				border : false,
				width : '100%',
				height : '100%',
				// store : store,
				layout : {
					type : 'table',
					columns : 5
				},

				items : [{
							xtype : 'combobox',
							fieldLabel : '选择站点',
							labelWidth : 60,
							id : 'dtu',
							margin : '10 0 0 10',
							valueField : 'id',
							displayField : 'name',
							store : dtustore,
							emptyText : '请选择...',
							selectOnFocus : true/*,
							listeners : {
								select : function(combo, records, options) {
									var pa = {
										dtuId : combo.getValue()
									};
									Ext.apply(lmustore.proxy.extraParams, pa);
									lmustore.reload();
									Ext.getCmp('lmu').setValue(0);
								}
							}*/
						}/*, {
							xtype : 'combobox',
							fieldLabel : '选择设备',
							labelWidth : 60,
							id : 'lmu',
							margin : '10 0 0 10',
							valueField : 'id',
							displayField : 'name',
							store : lmustore,
							emptyText : '请选择...',
							selectOnFocus : true,
							multiSelect : true
						}, {
							margin : '10 0 0 10',
							xtype : 'datefield',
							// anchor : '100%',
							fieldLabel : '查询时间',
							labelWidth : 60,
							name : 'addtime',
							id : 'addtime',
							format : "Y-m-d",
							maxValue : new Date()
						}, {
							xtype : 'combobox',
							fieldLabel : '报警类型',
							labelWidth : 60,
							id : 'alarmType',
							// name : 'alarmType',
							margin : '10 0 0 10',
							valueField : 'id',
							displayField : 'text',
							store : Ext.create('Ext.data.ArrayStore', {
										fields : ['id', 'text'],
										data : alarmTypeComboBox
									}),
							emptyText : '请选择...'
						}*/, {
							xtype : 'button',
							align : 'right',
							text : '查询',
							margin : '10 0 0 30',
							handler : function() {
								selectData();
							}
						}]

			});
	Ext.getCmp('dtu').setValue(0);
	
	Ext.create('Ext.grid.Panel', {
				border : false,
				id : 'grid',
				width : '100%',
				height : '100%',
				store : lmustore,
				renderTo : 'listview',
				columns : [{
					header : "序号",
					width : 80,
					sortable : true,
					flex : 0.5,
					renderer : function(value, cellmeta, record, rowIndex,
							columnIndex, store) {
						return rowIndex + 1;
					}
				}, {
					header : '设备名称',
					dataIndex : 'name',
					flex : 1
				}, {
					header : '设备描述',
					dataIndex : 'address',
					flex : 1
				}, {
					header : '设备编号',
					dataIndex : 'code',
					flex : 1
				}, {
					header : '设备类型',
					dataIndex : 'type',
					renderer:function(value, cellmeta, record, rowIndex, columnIndex, store){
						if(value == "1"){
							return "氨气采集器";
						}else if(value == "2"){
							return "温湿度采集器";
						}else if(value == "3"){
							return "市电采集器";
						}else{
							return "其他设备";
						}
			        },
					flex : 1
				}, {
					header : '操作',
					dataIndex : 'oper',
					flex : 2,
					renderer : function(val, metadata, record, rowIndex, colIndex,
							store) {
						return "&nbsp;&nbsp;<a href='javascript:void(0)' onclick=\"CreateWin('"
								+ record.get('lmulimit').id
								+ "','"
								+ record.get('lmulimit').a1
								+ "','"
								+ record.get('lmulimit').a2
								+ "','"
								+ record.get('lmulimit').w1
								+ "','"
								+ record.get('lmulimit').w2
								+ "','"
								+ record.get('lmulimit').s1
								+ "','"
								+ record.get('lmulimit').s2
								+ "','"
								+ record.get('lmulimit').sd1
								+ "','"
								+ record.get('lmulimit').sd2
								+ "','"
								+ record.get('lmulimit').sd3
								+ "','"
								+ record.get('lmulimit').sd4								
								+ "','"
								+ record.get('lmulimit').sd5
								+ "','"
								+ record.get('lmulimit').sd6
								+ "','"
								+ record.get('lmulimit').sd7
								+ "','"
								+ record.get('lmulimit').sd8
								+ "','"
								+ record.get('lmulimit').arate
								+ "','"
								+ record.get('lmulimit').wrate
								+ "','"
								+ record.get('lmulimit').srate
								+ "','"
								+ record.get('lmulimit').lmuId
								+ "','"
								+ record.get('type')
								+ "',0)\">查看</a>&nbsp;&nbsp;&nbsp;"
								+ "<a href='javascript:void(0)' onclick=\"CreateWin('"
								+ record.get('lmulimit').id
								+ "','"
								+ record.get('lmulimit').a1
								+ "','"
								+ record.get('lmulimit').a2
								+ "','"
								+ record.get('lmulimit').w1
								+ "','"
								+ record.get('lmulimit').w2
								+ "','"
								+ record.get('lmulimit').s1
								+ "','"
								+ record.get('lmulimit').s2
								+ "','"
								+ record.get('lmulimit').sd1
								+ "','"
								+ record.get('lmulimit').sd2
								+ "','"
								+ record.get('lmulimit').sd3
								+ "','"
								+ record.get('lmulimit').sd4								
								+ "','"
								+ record.get('lmulimit').sd5
								+ "','"
								+ record.get('lmulimit').sd6
								+ "','"
								+ record.get('lmulimit').sd7
								+ "','"
								+ record.get('lmulimit').sd8
								+ "','"
								+ record.get('lmulimit').arate
								+ "','"
								+ record.get('lmulimit').wrate
								+ "','"
								+ record.get('lmulimit').srate
								+ "','"
								+ record.get('lmulimit').lmuId
								+ "','"
								+ record.get('type')
								+ "',1)\">编辑</a>&nbsp;&nbsp;&nbsp;"
								
					}
				}],
				dockedItems : [{
							id : 'pagingtoolbar',
							xtype : 'pagingtoolbar',
							store : lmustore, // same store GridPanel is using
							params : {
								start : 'start',
								limit : 'limit'
							},
							dock : 'bottom',
							displayInfo : true
						}]
			});
}

function parseDate(addtime) {
	return addtime.replace("T", " ");
	// return Format(addtime, "yyyy-MM-dd");
}

function selectData() {
	//alert(Ext.getCmp('addtime').getValue());
	var pa = {
		dtuId : Ext.getCmp('dtu').getValue()
		//lmuIds : Ext.getCmp('lmu').getValue(),
		//selectDate : Ext.getCmp('addtime').getValue()
		//alarmType : Ext.getCmp('alarmType').getValue()
	};
	Ext.apply(lmustore.proxy.extraParams, pa);
	lmustore.loadPage(1);
}

var aqFrom,wsForm,sdForm,aqwin,wswin,sdwin;

function CreateWin(id,a1,a2,w1,w2,s1,s2,sd1,sd2,sd3,sd4,sd5,sd6,sd7,sd8,arate,wrate,srate,lmuId,type,status) {
	var html;
	if (status == 1) {
		html = '<div style="text-align:center"><input type="button" value="保存" onclick="save('
				+ status
				+ ','+type+')"/>&nbsp;&nbsp;'
				+ '<input type="button" value="取消" onclick="cancel('+type+')"/></div>'
	}
	
	aqwin = Ext.create('Ext.window.Window', {
		id : 'aqwin',
		title : '氨气报警',
		region : 'center',
		height : 380,
		width : 320,
		layout : 'fit',
		items : aqFrom = new Ext.form.Panel({
					width : 300,
					height : 360,
					closable : false,
					border : false,
					layout : 'anchor',
					defaults : {
						anchor : '100%'
					},
					defaultType : 'textfield',
					items : [{
								id : 'id',
								fieldLabel : '报警ID',
								name : 'id',
								margin : '20 20 0 20',
								value : id,
								hidden : true
							},{
								id : 'lmuId',
								fieldLabel : '设备ID',
								name : 'lmuId',
								margin : '20 20 0 20',
								value : lmuId,
								hidden : true
							}
							, {
								fieldLabel : '当前状态',
								name : 'status',
								id : 'status',
								value : status == 0 ? "查看" : "编辑",
								margin : '20 20 0 20',
								readOnly : true
							}, {
								fieldLabel : '氨气上限',
								name : 'a1',
								id : 'a1',
								margin : '20 20 0 20',
								fieldLabel : '氨气上限(单位ppm)',
								allowBlank : false,
								blankText : '氨气上限不能为空',
								value : a1,
								regex : /^-?\d+$/,
								regexText : '输入整数'
							}, {
								fieldLabel : '氨气下限',
								name : 'a2',
								id : 'a2',
								margin : '20 20 0 20',
								fieldLabel : '氨气下限(单位ppm)',
								allowBlank : false,
								blankText : '氨气下限不能为空',
								value : a2,
								regex : /^-?\d+$/,
								regexText : '输入整数'
							}, {
								fieldLabel : '氨气报警次数',
								name : 'arate',
								id : 'arate',
								margin : '20 20 0 20',
								fieldLabel : '氨气报警次数',
								allowBlank : false,
								blankText : '氨气报警次数',
								value : arate,
								regex : /^-?\d+$/,
								regexText : '输入整数'
							}, {
								xtype : 'panel',
								margin : '30 0 0 0',
								border : false,
								html : html
							}]
				})
	});
	
	
	wswin = Ext.create('Ext.window.Window', {
		id : 'wswin',
		title : '温湿度报警',
		region : 'center',
		height : 380,
		width : 320,
		layout : 'fit',
		items : wsFrom = new Ext.form.Panel({
					width : 300,
					height : 360,
					closable : false,
					border : false,
					layout : 'anchor',
					defaults : {
						anchor : '100%'
					},
					defaultType : 'textfield',
					items : [{
								id : 'id',
								fieldLabel : '报警ID',
								name : 'id',
								margin : '20 20 0 20',
								value : id,
								hidden : true
							},{
								id : 'lmuId',
								fieldLabel : '设备ID',
								name : 'lmuId',
								margin : '20 20 0 20',
								value : lmuId,
								hidden : true
							}, {
								fieldLabel : '当前状态',
								name : 'status',
								id : 'status',
								value : status == 0 ? "查看" : "编辑",
								margin : '20 20 0 20',
								readOnly : true
							}, {
								fieldLabel : '温度上限(单位:℃)',
								name : 'w1',
								id : 'w1',
								margin : '20 20 0 20',
								fieldLabel : '温度上限(单位:℃)',
								allowBlank : false,
								blankText : '温度上限不能为空',
								value : w1,
								regex : /^-?\d+$/,
								regexText : '输入整数'
							}, {
								fieldLabel : '温度下限(单位:℃)',
								name : 'w2',
								id : 'w2',
								margin : '20 20 0 20',
								fieldLabel : '温度下限(单位:℃)',
								allowBlank : false,
								blankText : '温度下限不能为空',
								value : w2,
								regex : /^-?\d+$/,
								regexText : '输入整数'
							}, {
								fieldLabel : '温度报警次数',
								name : 'wrate',
								id : 'wrate',
								margin : '20 20 0 20',
								fieldLabel : '温度报警次数',
								allowBlank : false,
								blankText : '温度报警次数不能为空',
								value : wrate,
								regex : /^-?\d+$/,
								regexText : '输入整数'
							}, {
								fieldLabel : '湿度上限(单位:%)',
								name : 's1',
								id : 's1',
								margin : '20 20 0 20',
								fieldLabel : '湿度上限(单位:%)',
								allowBlank : false,
								blankText : '湿度上限不能为空',
								value : s1,
								regex : /^-?\d+$/,
								regexText : '输入整数'
							}, {
								fieldLabel : '湿度下限(单位:%)',
								name : 's2',
								id : 's2',
								margin : '20 20 0 20',
								fieldLabel : '湿度下限(单位:%)',
								allowBlank : false,
								blankText : '氨气下限不能为空',
								value : s2,
								regex : /^-?\d+$/,
								regexText : '输入整数'
							}, {
								fieldLabel : '湿度报警次数',
								name : 'srate',
								id : 'srate',
								margin : '20 20 0 20',
								fieldLabel : '温度报警次数',
								allowBlank : false,
								blankText : '温度报警次数不能为空',
								value : srate,
								regex : /^-?\d+$/,
								regexText : '输入整数'
							}, {
								xtype : 'panel',
								margin : '30 0 0 0',
								border : false,
								html : html
							}]
				})
	});
	
	
	sdwin = Ext.create('Ext.window.Window', {
		id : 'sdwin',
		title : '市电报警',
		region : 'center',
		height : 500,
		width : 320,
		layout : 'fit',
		items : sdFrom = new Ext.form.Panel({
					width : 300,
					height : 500,
					closable : false,
					border : false,
					layout : 'anchor',
					defaults : {
						anchor : '100%'
					},
					defaultType : 'textfield',
					items : [{
								id : 'id',
								fieldLabel : '报警ID',
								name : 'id',
								margin : '20 20 0 20',
								value : id,
								hidden : true
							},{
								id : 'lmuId',
								fieldLabel : '设备ID',
								name : 'lmuId',
								margin : '20 20 0 20',
								value : lmuId,
								hidden : true
							}, {
								fieldLabel : '当前状态',
								name : 'status',
								id : 'status',
								value : status == 0 ? "查看" : "编辑",
								margin : '20 20 0 20',
								readOnly : true
							}, {
								name : 'sd1',
								id : 'sd1',
								margin : '20 20 0 20',
								fieldLabel : '市电一路',
								xtype: 'radiogroup',
					            items: [
					                {boxLabel: '断开', id:"sd1off", name: 'sd1', inputValue: 0, checked: true},
					                {boxLabel: '常通', id:"sd1on", name: 'sd1', inputValue: 1,}
					            ]
							}, {
								name : 'sd2',
								id : 'sd2',
								margin : '20 20 0 20',
								fieldLabel : '市电二路',
								xtype: 'radiogroup',
								value:sd2,
					            items: [
					                {boxLabel: '断开',id:"sd2off", name: 'sd2', inputValue: 0, checked: true},
					                {boxLabel: '常通',id:"sd2on", name: 'sd2', inputValue: 1,}
					            ]
							}, {
								name : 'sd3',
								id : 'sd3',
								margin : '20 20 0 20',
								fieldLabel : '市电三路',
								 xtype: 'radiogroup',
								 value:sd3,
						            items: [
						                {boxLabel: '断开',id:"sd3off", name: 'sd3', inputValue: 0, checked: true},
						                {boxLabel: '常通',id:"sd3on", name: 'sd3', inputValue: 1,}
						            ]
							}, {
								name : 'sd4',
								id : 'sd4',
								margin : '20 20 0 20',
								fieldLabel : '市电四路',
								 xtype: 'radiogroup',
								 value:sd4,
						            items: [
						                {boxLabel: '断开',id:"sd4off", name: 'sd4', inputValue: 0, checked: true},
						                {boxLabel: '常通',id:"sd4on", name: 'sd4', inputValue: 1,}
						            ]
							}, {
								name : 'sd5',
								id : 'sd5',
								margin : '20 20 0 20',
								fieldLabel : '市电五路',
								xtype: 'radiogroup',
								value:sd5,
					            items: [
					                {boxLabel: '断开',id:"sd5off", name: 'sd5', inputValue: 0, checked: true},
					                {boxLabel: '常通',id:"sd5on", name: 'sd5', inputValue: 1,}
					            ]
							}, {
								name : 'sd6',
								id : 'sd6',
								margin : '20 20 0 20',
								fieldLabel : '市电六路',
								xtype: 'radiogroup',
								value:sd6,
					            items: [
					                {boxLabel: '断开',id:"sd6off", name: 'sd6', inputValue: 0, checked: true},
					                {boxLabel: '常通',id:"sd6on", name: 'sd6', inputValue: 1,}
					            ]
							}, {
								name : 'sd7',
								id : 'sd7',
								margin : '20 20 0 20',
								fieldLabel : '市电七路',
								xtype: 'radiogroup',
								value:sd7,
					            items: [
					                {boxLabel: '断开',id:"sd7off", name: 'sd7', inputValue: 0, checked: true},
					                {boxLabel: '常通',id:"sd7on", name: 'sd7', inputValue: 1,}
					            ]
							}, {
								name : 'sd8',
								id : 'sd8',
								margin : '20 20 0 20',
								fieldLabel : '市电八路',
								xtype: 'radiogroup',
								value:sd8,
					            items: [
					                {boxLabel: '断开',id:"sd8off", name: 'sd8', inputValue: 0, checked: true},
					                {boxLabel: '常通',id:"sd8on", name: 'sd8', inputValue: 1,}
					            ]
							}, {
								xtype : 'panel',
								margin : '30 0 0 0',
								border : false,
								html : html
							}]
				})
	});
	
	if(type == 1){
		aqwin.show();
	}else if(type == 2){
		wswin.show();
	}else if(type == 3){
		if(sd1 == 0){
			Ext.getCmp('sd1off').setValue(true)
			Ext.getCmp('sd1on').setValue(false)
		}else{
			Ext.getCmp('sd1off').setValue(false)
			Ext.getCmp('sd1on').setValue(true)
		}
		
		if(sd2 == 0){
			Ext.getCmp('sd2off').setValue(true)
			Ext.getCmp('sd2on').setValue(false)
		}else{
			Ext.getCmp('sd2off').setValue(false)
			Ext.getCmp('sd2on').setValue(true)
		}
		
		if(sd3 == 0){
			Ext.getCmp('sd3off').setValue(true)
			Ext.getCmp('sd3on').setValue(false)
		}else{
			Ext.getCmp('sd3off').setValue(false)
			Ext.getCmp('sd3on').setValue(true)
		}
		
		if(sd4 == 0){
			Ext.getCmp('sd4off').setValue(true)
			Ext.getCmp('sd4on').setValue(false)
		}else{
			Ext.getCmp('sd4off').setValue(false)
			Ext.getCmp('sd4on').setValue(true)
		}
		
		if(sd5 == 0){
			Ext.getCmp('sd5off').setValue(true)
			Ext.getCmp('sd5on').setValue(false)
		}else{
			Ext.getCmp('sd5off').setValue(false)
			Ext.getCmp('sd5on').setValue(true)
		}
		
		if(sd6 == 0){
			Ext.getCmp('sd6off').setValue(true)
			Ext.getCmp('sd6on').setValue(false)
		}else{
			Ext.getCmp('sd6off').setValue(false)
			Ext.getCmp('sd6on').setValue(true)
		}
		

		if(sd7 == 0){
			Ext.getCmp('sd7off').setValue(true)
			Ext.getCmp('sd7on').setValue(false)
		}else{
			Ext.getCmp('sd7off').setValue(false)
			Ext.getCmp('sd7on').setValue(true)
		}
		
		if(sd8 == 0){
			Ext.getCmp('sd8off').setValue(true)
			Ext.getCmp('sd8on').setValue(false)
		}else{
			Ext.getCmp('sd8off').setValue(false)
			Ext.getCmp('sd8on').setValue(true)
		}
		
		 sdwin.show();
	}
	
	
}

function cancel(type){
	if(type == 1){
		aqwin.close();
	}else if(type == 2){
		wswin.close();
	}else if(type == 3){
		sdwin.close();
	}
}


function save(status,type) {
	if(type == 1){
		if (!aqFrom.isValid()) {
			return false;
		}
	}else if(type == 2){
		if (!wsFrom.isValid()) {
			return false;
		}
	}else if(type == 3){
		if (!sdFrom.isValid()) {
			return false;
		}
	}
	var id = Ext.getCmp("id").getValue();
	var lmuId = Ext.getCmp("lmuId").getValue();
	var params = {id:id,type:type,lmuId:lmuId};
	if(type == 1){
		params.a1 = Ext.getCmp("a1").getValue();
		params.a2 = Ext.getCmp("a2").getValue();
		params.arate = Ext.getCmp("arate").getValue();
	}else if(type == 2){
		params.w1 = Ext.getCmp("w1").getValue();
		params.w2 = Ext.getCmp("w2").getValue(); 
		params.s1 = Ext.getCmp("s1").getValue(); 
		params.s2 = Ext.getCmp("s2").getValue(); 
		params.wrate =  Ext.getCmp("wrate").getValue();
		params.srate =  Ext.getCmp("srate").getValue();
	}else if(type == 3){
		params.sd1 = Ext.getCmp("sd1on").getValue() == true ? 1 : 0; 
		params.sd2 = Ext.getCmp("sd2on").getValue() == true ? 1 : 0; 
		params.sd3 = Ext.getCmp("sd3on").getValue() == true ? 1 : 0; 
		params.sd4 = Ext.getCmp("sd4on").getValue() == true ? 1 : 0;  
		params.sd5 = Ext.getCmp("sd5on").getValue() == true ? 1 : 0;  
		params.sd6 = Ext.getCmp("sd6on").getValue() == true ? 1 : 0; 
		params.sd7 = Ext.getCmp("sd7on").getValue() == true ? 1 : 0;  
		params.sd8 = Ext.getCmp("sd8on").getValue() == true ? 1 : 0;   
	}
	Ext.Ajax.request({
				method : 'POST',
				url : 'updateLmuLimit.html',
				params : params,
				waitMsg : '正在操作,请等待!',
				scope : this,
				success : function(r, o) {
					var pa = {
							dtuId : Ext.getCmp('dtu').getValue()
							//lmuIds : Ext.getCmp('lmu').getValue(),
							//selectDate : Ext.getCmp('addtime').getValue()
							//alarmType : Ext.getCmp('alarmType').getValue()
						};
						Ext.apply(lmustore.proxy.extraParams, pa);
						lmustore.loadPage(1);
				},
				failure : function(r, o) {
					Ext.MessageBox.alert("提示", "保存失败！!");
				}
			});
	if(type == 1){
		aqwin.close();
	}else if(type == 2){
		wswin.close();
	}else if(type ==3){
		sdwin.close();
	}

}