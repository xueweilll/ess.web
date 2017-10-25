﻿var store, dtustore, lmustore, HisIrStore;
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
				},//1214
				autoLoad : true

			});
	lmustore = Ext.create('Ext.data.Store', {
				model : 'Lmu',
				proxy : {
					type : 'ajax',
					method : 'POST',
					url : 'createLmus.html',
					extraParams : {
						dtuId : 0
					},
					reader : {
						type : 'json',
						root : 'arrLmu',
						successProperty : 'success'
					}
				},
				autoLoad : true,
				listeners : {
					load : function(style, records, options) {
						var rs = Ext.create('Lmu', {
							id : 0,
							dtuId : 0,
							name : '请选择...',
							address : '',
							code : 0
								// dtu:null
							});
						lmustore.insert(0, rs);
					}
				}
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
						var pareId = record.get('objData').id;
						var pa = {
							pareId : pareId
						}
						Ext.apply(dtustore.proxy.extraParams, pa);
					    dtustore.reload();
					    Ext.getCmp('dtu').setValue(0);
					    Ext.apply(HisIrStore.proxy.extraParams, pa);
						HisIrStore.reload();
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
					title : '历史数据',
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
							selectOnFocus : true,
							listeners : {
								select : function(combo, records, options) {
									var pa = {
										dtuId : combo.getValue()
									};
									Ext.apply(lmustore.proxy.extraParams, pa);
									lmustore.reload();
									/*Ext.getCmp('lmu').setValue(0);*/
								}
							}
						}, {
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
							fieldLabel : '查询时间',
							labelWidth : 60,
							name : 'addtime',
							id : 'addtime',
							format : "Y-m-d",
							maxValue : new Date()
						}, {
							xtype : 'button',
							align : 'right',
							text : '查询',
							margin : '10 0 0 30',
							handler : function() {
								selectData();
							}
						}]

			});
	HisIrStore = Ext.create('Ext.data.Store', {
				autoLoad : true,
				pageSize : itemsPerPage,
				model : 'HisIrItem',
				proxy : {
					type : 'ajax',
					method : 'POST',
					async : false,
					url : 'histroyData.html',

					extraParams : {
						dtuId : 0,
						lmuId : 0,
						selectDate : '',
						alarmType : 0
					},

					reader : {
						type : 'json',
						root : 'hisIrList',
						totalProperty : 'totalCount'
					}
				}
			});
	Ext.create('Ext.grid.Panel', {
				border : false,
				id : 'grid',
				width : '100%',
				height : '100%',
				store : HisIrStore,
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
					header : '站点名称',
					dataIndex : 'dtuName',
					sortable : true,
					flex : 1
				}, {
					header : '氨气浓度(ppm)',
					dataIndex : 'v1',
					renderer:function(value, cellmeta, record, rowIndex, columnIndex, store){
			            if(value!=''){
			            	return value;
			            }else{
			            	return "-";
			            }
			        },
					flex : 1
				}, {
					header : '空气温度(℃)',
					dataIndex : 'v2',
					renderer:function(value, cellmeta, record, rowIndex, columnIndex, store){
			            if(value!=''){
			            	return value;
			            }else{
			            	return "-";
			            }
			        },
					flex : 1
				}, {
					header : '空气湿度(%)',
					dataIndex : 'v3',
					renderer:function(value, cellmeta, record, rowIndex, columnIndex, store){
			            if(value!=''){
			            	return value;
			            }else{
			            	return "-";
			            }
			        },
					flex : 1
				},{
					header : '市电一路',
					dataIndex : 'v4',
					renderer:function(value, cellmeta, record, rowIndex, columnIndex, store){
			             
						if(value==0){
							return "断开";
						}else{
							return "常通";
						}
			        },
					flex : 1
				},{
					header : '市电二路',
					dataIndex : 'v5',
					renderer:function(value, cellmeta, record, rowIndex, columnIndex, store){
			             
						if(value==0){
							return "断开";
						}else{
							return "常通";
						}
			        },
					flex : 1
				},{
					header : '市电三路',
					dataIndex : 'v6',
					renderer:function(value, cellmeta, record, rowIndex, columnIndex, store){
			             
						if(value==0){
							return "断开";
						}else{
							return "常通";
						}
			        },
					flex : 1
				},{
					header : '市电四路',
					dataIndex : 'v7',
					renderer:function(value, cellmeta, record, rowIndex, columnIndex, store){
			             
						if(value==0){
							return "断开";
						}else{
							return "常通";
						}
			        },
					flex : 1
				},{
					header : '市电五路',
					dataIndex : 'v8',
					renderer:function(value, cellmeta, record, rowIndex, columnIndex, store){
			             
						if(value==0){
							return "断开";
						}else{
							return "常通";
						}
			        },
					flex : 1
				},{
					header : '市电六路',
					dataIndex : 'v9',
					renderer:function(value, cellmeta, record, rowIndex, columnIndex, store){
			             
						if(value==0){
							return "断开";
						}else{
							return "常通";
						}
			        },
					flex : 1
				},{
					header : '市电七路',
					dataIndex : 'v10',
					renderer:function(value, cellmeta, record, rowIndex, columnIndex, store){
			             
						if(value==0){
							return "断开";
						}else{
							return "常通";
						}
			        },
					flex : 1
				},{
					header : '市电八路',
					dataIndex : 'v11',
					renderer:function(value, cellmeta, record, rowIndex, columnIndex, store){
			             
						if(value==0){
							return "断开";
						}else{
							return "常通";
						}
			        },
					flex : 1
				}, {
					header : '采集时间',
					dataIndex : 'addtime',
					flex : 1.5
				}],
				dockedItems : [{
							id : 'pagingtoolbar',
							xtype : 'pagingtoolbar',
							store : HisIrStore, // same store GridPanel is using
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
}

function selectData() {
	var pa = {
		dtuId : Ext.getCmp('dtu').getValue(),
		lmuIds : Ext.getCmp('lmu').getValue(),
		selectDate : Ext.getCmp('addtime').getValue()
	};
	Ext.apply(HisIrStore.proxy.extraParams, pa);
	HisIrStore.loadPage(1);
}