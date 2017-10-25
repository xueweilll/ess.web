var sysStore;
var itemsPerPage = 20;
var logTypeComboBox = [[0, '请选择...'], [1, '系统日志'], [2, '操作日志']];
Ext.onReady(function() {

	// alert(sysStore.getCount());

	Ext.create('Ext.container.Viewport', {
		layout : 'border',
		width : '100%',
		height : '100%',
		margin : '0 0 0 0',
		items : [{
					title : '查询区域',
					region : 'north',
					border : false,
					collapsible : true,
					margin : '0 0 0 0',
					// height:'20%',
					width : '100%',
					height : 70,
					// items : searchPanel,
					html : "<div id='northview' style='width:100%;height:100%'></div>"
				}, {
					region : 'center',
					title : '系统日志列表',
					border : false,
					margin : '0 0 0 0',
					// items:syslogPanel,
					html : "<div id='centerview' style='width:100%;height:100%'></div>"
				}],
		rendTo : Ext.getBody()
	});

	searchPanel = Ext.create('Ext.panel.Panel', {
				border : false,
				renderTo : 'northview',
				//margin : '20 0 0 20',
				layout : {
					type : 'table',
					columns : 4
				},

				items : [{
							margin : '10 0 0 10',
							xtype : 'textfield',
							fieldLabel : '操作用户',
							name : 'operUser',
							id : 'operUser'
						}, {
							margin : '10 0 0 10',
							xtype : 'combobox',
							fieldLabel : '日志类型',
							id : 'logType',
							valueField : 'id',
							displayField : 'text',
							store : Ext.create('Ext.data.ArrayStore', {
										fields : ['id', 'text'],
										data : logTypeComboBox
									}),
							emptyText : '请选择...'
						}, {
							margin : '10 0 0 10',
							xtype : 'datefield',
							anchor : '100%',
							fieldLabel : '录入时间',
							name : 'addtime',
							id : 'addtime',
							format : "Y-m-d",
							maxValue : new Date()
						}, {
							margin : '10 0 0 30',
							xtype : 'button',
							text : '查询',
							handler : function() {
								var pa = {
									operUser : Ext.getCmp("operUser")
											.getValue(),
									logType : Ext.getCmp("logType").getValue(),
									addtime : Ext.util.Format.date(Ext
													.getCmp("addtime")
													.getValue(), 'Y-m-d')
								}
								Ext.apply(sysStore.proxy.extraParams, pa);
								sysStore.loadPage(1);
							}
						}]
			});

	CreatePanel();
});

function CreatePanel() {
	sysStore = Ext.create('Ext.data.Store', {
				autoLoad : true,
				pageSize : itemsPerPage,
				fields : [{
							name : 'log',
							type : 'string'
						}, {
							name : 'addtime',
							type : 'string'
							// dateFormat: 'Y-m-d'
					}	, {
							name : 'logType',
							type : 'string'
						}],
				proxy : {
					type : 'ajax',
					method : 'POST',
					async : false,
					url : 'logGrid.html',
					/*
					 * extraParams : { operUser :
					 * Ext.getCmp("operUser").getValue(), addtime :
					 * Ext.util.Format.date(Ext.getCmp("addtime").getValue(),
					 * 'Y-m-d') },
					 */
					reader : {
						type : 'json',
						root : 'arrLog',
						totalProperty : 'totalCount'
					}
				}
			});
	// document.getElementById("centerview").innerHTML = "";
	Ext.create('Ext.grid.Panel', {
				border : false,
				id : 'grid',
				width : '100%',
				height : '100%',
				store : sysStore,
				renderTo : 'centerview',
				columns : [{
					header : "序号",
					width : 80,
					sortable : true,
					flex : 2,
					renderer : function(value, cellmeta, record, rowIndex,
							columnIndex, store) {
						return rowIndex + 1;
					}
				}, {
					header : '日志类型',
					dataIndex : 'logType',
					renderer : logtype,
					sortable : true,
					flex : 2
				}, {
					header : '详细信息',
					dataIndex : 'log',
					sortable : true,
					flex : 3
				}, {
					header : '录入时间',
					dataIndex : 'addtime',
					renderer : parseDate,
					// render : Ext.util.Format.dateRenderer('Y-m-d'),
					sortable : true,
					flex : 3
				}],
				dockedItems : [{
							id : 'pagingtoolbar',
							xtype : 'pagingtoolbar',
							store : sysStore, // same store GridPanel is using
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

function logtype(logType) {
	if (logType == 1) {
		return "系统日志";
	} else {
		return "操作日志";
	}
}