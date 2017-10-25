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
	//findtotal();回点刷新
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
						// alert('aa');
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
						stationId = record.get('id');
						//alert(record.data.leaf)
						HisIrStore.reload({
								params : {
									stationid : stationId,
									judgeTotal:0
								}
							});
						var pareId = record.get('objData').id;
						var pa = {
							pareId : pareId
						}
						Ext.apply(dtustore.proxy.extraParams, pa);
						dtustore.reload();
						/*Ext.apply(HisIrStore.proxy.extraParams, pa);
						HisIrStore.reload();*/
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
							// anchor : '100%',
							fieldLabel : '查询处理时间',
							labelWidth : 80,
							name : 'addtime',
							id : 'addtime',
							format : "Y-m-d",
							maxValue : new Date()
						}/*, {
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
	/*Ext.getCmp('lmu').setValue(0);*/
	//Ext.getCmp('alarmType').setValue(0);
	HisIrStore = Ext.create('Ext.data.Store', {
				autoLoad : true,
				pageSize : itemsPerPage,
				model : 'HisAlarmIrItem',
				proxy : {
					type : 'ajax',
					method : 'POST',
					async : false,
					url : 'historyPoliceDate.html',
					extraParams : {
						dtuId : -1,
						lmuId : 0,
						selectDate : '',
						alarmType : 0,
						judgeTotal:0,
						stationid:-1
					},

					reader : {
						type : 'json',
						root : 'hisAlamList',
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
					dataIndex : 'stationName',
					renderer:function(value, cellmeta, record, rowIndex, columnIndex, store){
			            if(value!=''){
			            	return value;
			            }else{
			            	return "-";
			            }
			        },
					sortable : true,
					flex : 1
				}, {
					header : '设备名',
					dataIndex : 'type',
					renderer:function(value, cellmeta, record, rowIndex, columnIndex, store){
			            if(value ==1){
			            	return "氨气传感器";
			            }else if(value= 2){
			            	return "温度传感器";
			            }else if(value==3){
			            	return "湿度传感器";
			            }else {
			            	return "市电";
			            }
			        },
					flex : 1
				}, {
					header : '报警信息',
					dataIndex : 'information',
					renderer:function(value, cellmeta, record, rowIndex, columnIndex, store){
			            if(value!=''){
			            	return value;
			            }else{
			            	return "-";
			            }
			        },
					flex : 1
				}, {
					header : '均值判断',
					dataIndex : 'confirm',
					renderer:function(value, cellmeta, record, rowIndex, columnIndex, store){
			            if(value==0){
			            	return "底";
			            }else{
			            	return "高";
			            }
			        },
					flex : 1
				},{
					header : '报警时间',
					dataIndex : 'alarmTime',
					renderer:function(value, cellmeta, record, rowIndex, columnIndex, store){
			             
			            //console.info(value+"aaaaaaaaaaaa");
						if(value!=''){
							return value;
						}else{
							return "-";
						}
			        },
					flex : 1
				},{
					header : '处理时间',
					dataIndex : 'handleTime',
					renderer:function(value, cellmeta, record, rowIndex, columnIndex, store){
			             
			            //console.info(value+"aaaaaaaaaaaa");
						if(value!=''){
							return value;
						}else{
							return "-";
						}
			        },
					flex : 1
				},{
					header : '是否处理',
					dataIndex : 'status',
					renderer:function(value, cellmeta, record, rowIndex, columnIndex, store){
			             
			            //console.info(value+"aaaaaaaaaaaa");
						if(value==1){
							return "已处理";
						}else{
							return "未处理";
						}
			        },
					flex : 1
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
	// return Format(addtime, "yyyy-MM-dd");
}

function selectData() {
	//alert(Ext.getCmp('addtime').getValue());
	var pa = {
		dtuId : Ext.getCmp('dtu').getValue(),
		lmuIds : Ext.getCmp('lmu').getValue(),
		selectDate : Ext.getCmp('addtime').getValue(),
		stationid:-1
		//alarmType : Ext.getCmp('alarmType').getValue()
	};
	Ext.apply(HisIrStore.proxy.extraParams, pa);
	HisIrStore.loadPage(1);
}

function findtotal() {
	var pa = {
      judgeTotal:-1
	};
	Ext.apply(HisIrStore.proxy.extraParams, pa);
	HisIrStore.loadPage(1);
}