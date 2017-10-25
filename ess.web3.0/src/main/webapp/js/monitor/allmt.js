/**
 * 
 */
Ext.onReady(function() {
	Ext.create('Ext.container.Viewport', {
		title : 'Fit Layout',
		margin : '0 0 0 0',
		width : '100%',
		height : '100%',
		layout : 'border',
		border: true,
		items : [{
					title : '区域列表',
					width : 200,
					border : false,
					collapsible : true,
					split : true,
					region : 'west',
					html : "<div id='treeview' style='width:100%;height:100%'></div>"
				}, {
					title : '监控数据',
					border : false,
					region : 'center',
					html : "<div id='dataview' style='width:100%;height:100%'></div>"
				}],
		renderTo : Ext.getBody()
	});
	CreateTree();
	// CreateCurrentData();
	//获取最新缓存中的数据
	defineIrStore();
	//获取历史数据
	//defineHrStore();
	//创建midal
	CreateTabPanel();
	//最新数据获取定时器
	StartRefushIrData();
	//ContralPanel();
});


function CreateTree() {
	Ext.define('Marker', {
				extend : 'Ext.data.Model',
				fields : [{
							name : 'id',
							type : 'string'
						}, {
							name : 'lat',
							type : 'double'
						}, {
							name : 'lon',
							type : 'double'
						}, {
							name : 'level',
							type : 'int'
						}, {
							name : 'text',
							type : 'string'
						}]
			});
	var store = Ext.create('Ext.data.TreeStore', {
				id : 'store',
				model : 'Marker',
				proxy : {
					type : 'ajax',
					asyn : false,
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
	Ext.create('Ext.tree.Panel', {
				renderTo : 'treeview',
				border : false,
				width : '100%',
				height : '100%',
				store : store,
				rootVisible : false,
				listeners : {
					itemclick : function(view, record, item, index, e, eOpts) {
						stationId = record.get('id');
						//alert(record.data.leaf)
							lmuIrStore.reload({
								params : {
									pareId : stationId
								}
							});
						//	StopRefushHrData();
							//StartRefushIrData();
//						if (tabindex == 1) {
//							lmuHrStore.reload({
//										params : {
//											pareId : stationId
//										}
//									});
//							StopRefushIrData();
//							StartRefushHrData();
//						}
					}
				}
			})
}
var lmuIrStore, lmuHrStore;
var curIrData, curHrData, curIrDataLength = 0, curHrDataLength = 0, height = 0, stationId = 1;
//获取缓存MessageQueue
function defineIrStore() {
	lmuIrStore = Ext.create('Ext.data.Store', {
				model : 'CurrIrItem',
				proxy : {
					type : 'ajax',
					url : 'currentIrStoreByDtus.html',
					asyn : false,
					params : {
						pareId : stationId
					},
					reader : {
						type : 'json',
						root : 'arrIrStore',
						successProperty : 'success'
					}
				},
				autoLoad : true
			});
}

function defineHrStore() {
	lmuHrStore = Ext.create('Ext.data.Store', {
				model : 'CurrHrItem',
				proxy : {
					type : 'ajax',
					url : 'currentHrStoreByDtus.html',
					asyn : false,
					params : {
						pareId : stationId
					},
					reader : {
						type : 'json',
						root : 'arrHrStore',
						successProperty : 'success'
					}
				},
				autoLoad : true
			});
}

var tabindex = 0, intervalIr, intervalHr;;
function CreateTabPanel() {
	height = document.body.offsetHeight - 30;
	// alert(height);
	Ext.create('Ext.tab.Panel', {
				height : height,
				border : false,
				renderTo : 'dataview',
				items : [{
							title : '数据展现',
							id : 'tab1',
							xtype : 'panel',
							border : false,
							items : {
								xtype : 'grid',
								id : 'IrPanel',
								border : false,
								store : lmuIrStore,
								invalidateScrollerOnRefresh : false,
								height : height - 30,
								autoScroll : true,
								columns : [{
											header : '站点名称',
											dataIndex : 'name',
											flex : 1
										}, {
											header : '氨气浓度(ppm)',
											dataIndex : 'v1',
											renderer:function(value, cellmeta, record, rowIndex, columnIndex, store){
												if(value == "null"){
													return "设备已断开";
												}else{
													return value;
												}
									        },
											flex : 1
										}, {
											header : '空气温度(℃)',
											dataIndex : 'v2',
											renderer:function(value, cellmeta, record, rowIndex, columnIndex, store){
												if(value == "null"){
													return "设备已断开";
												}else{
													return value;
												}
									        },
											flex : 1
										}, {
											header : '空气湿度(%)',
											dataIndex : 'v3',
											renderer:function(value, cellmeta, record, rowIndex, columnIndex, store){
												if(value == "null"){
													return "设备已断开";
												}else{
													return value;
												}
									        },
											flex : 1
										},{
											header : '市电一路',
											dataIndex : 'v4',
											renderer:function(value, cellmeta, record, rowIndex, columnIndex, store){
												if(value == "null"){
													return "设备已断开";
												}else if(value == 0){
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
												if(value == "null"){
													return "设备已断开";
												}else if(value == 0){
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
												if(value == "null"){
													return "设备已断开";
												}else if(value == 0){
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
												if(value == "null"){
													return "设备已断开";
												}else if(value == 0){
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
												if(value == "null"){
													return "设备已断开";
												}else if(value == 0){
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
												if(value == "null"){
													return "设备已断开";
												}else if(value == 0){
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
												if(value == "null"){
													return "设备已断开";
												}else if(value == 0){
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
												if(value == "null"){
													return "设备已断开";
												}else if(value == 0){
													return "断开";
												}else{
													return "常通";
												}
									        },
											flex : 1
										},{
											header : '采集时间',
											dataIndex : 'addTime',
											flex : 1.5
										}],		   
								viewConfig : {
									loadMask : false
								}
							}
						}], 
				listeners : {
					tabchange : function(tabpanel, newcard, oldcard) {
						// Ext.Msg.alert('Tip',newcard.id);
					/*	if (newcard.id == "tab1") {
							tabindex = 0;
							lmuIrStore.reload({
										params : {
											pareId : stationId
										}
									});
							StopRefushHrData();
							StartRefushIrData();*/
							// alert(tabindex);
							/*	} 
							 * else { tabindex = 1; lmuHrStore.reload({ params : {
							 * pareId : stationId } }); StopRefushIrData();
							 * StartRefushHrData(); // alert(tabindex); }
							 */
					}
				}
			});
}

function StartRefushIrData() {
	intervalIr = setInterval(function() {
				// FillCurData();
				lmuIrStore.reload({
							params : {
								pareId : stationId
							}
						});
			}, 5000);
}

function StopRefushIrData() {
	clearTimeout(intervalIr);
}

function StartRefushHrData() {
	intervalIr = setInterval(function() {
				// FillCurData();
				lmuHrStore.reload({
							params : {
								pareId : stationId
							}
						});
			}, 5000);
}

function StopRefushHrData() {
	clearTimeout(intervalHr);
}

/*
 * function FillCurData() { CreateCurData(); lmuIrStore.each(function(record) {
 * for (var i = 0; i < curIrDataLength; i++) { if (record.get('id') ==
 * curIrData[i].id) { record.set('name', curIrData[i].name);
 * record.set('currentS', curIrData[i].currentS); record.set('currentV',
 * curIrData[i].currentV); record.set('currentI', curIrData[i].currentI);
 * record.set('currentTmp', curIrData[i].currentTmp); record.set('startTime',
 * curIrData[i].startTime); record.set('currentT', curIrData[i].currentT);
 * record.set('addTime', curIrData[i].addTime); continue; } } });
 * lmuHrStore.each(function(record) { for (var i = 0; i < curHrDataLength; i++) {
 * if (record.get('id') == curHrData[i].id) { record.set('name',
 * curHrData[i].name); record.set('hr0', curHrData[i].hr0); record.set('hr4',
 * curHrData[i].hr4); record.set('hr5', curHrData[i].hr5); record.set('hr6',
 * curHrData[i].hr6); record.set('hr7', curHrData[i].hr7); record.set('hr8',
 * curHrData[i].hr8); record.set('hr9', curHrData[i].hr9); record.set('hr10',
 * curHrData[i].hr10); record.set('hr11', curHrData[i].hr11); record.set('hr12',
 * curHrData[i].hr12); record.set('hr13', curHrData[i].hr13); record.set('hr14',
 * curHrData[i].hr14); record.set('hr15', curHrData[i].hr15); record.set('hr16',
 * curHrData[i].hr16); record.set('hr17', curHrData[i].hr17); record.set('hr18',
 * curHrData[i].hr18); record.set('hr19', curHrData[i].hr19); record.set('hr20',
 * curHrData[i].hr20); record.set('addTime', curHrData[i].addTime); continue; } }
 * }); }
 * 
 * function CreateCurData() { Ext.Ajax.request({ url :
 * 'currentStoreByDtus.html', async : true, params : { pareId : stationId },
 * success : function(response) { var data = Ext.decode(response.responseText);
 * curIrData = data.arrIrStore; curIrDataLength = curIrData.length; curHrData =
 * data.arrHrStore; curHrDataLength = curHrData.length; //
 * alert(curIrData.length); } }); }
 */
var contralPanel;
/*var ploy = Ext.create('Ext.data.Store', {
			model : 'Model',
			proxy : {
				type : 'ajax',
				method : 'POST',
				url : 'modelData.html',
				asyn : false,
				reader : {
					type : 'json',
					root : 'modelList',
					successProperty : 'success'
				}
			},
			autoLoad : true
		});*/
/*
 * var ploy = Ext.create('Ext.data.Store', { fields : ['val', 'name'], data : [{
 * "val" : "0", "name" : "自定义" }, { "val" : "1", "name" : "全夜灯" }, { "val" :
 * "2", "name" : "半夜灯" }] });
 */
/*function ReadOnly(bl) {
	Ext.getCmp('hr0').setDisabled(bl);
	Ext.getCmp('hr4').setDisabled(bl);
	Ext.getCmp('hr5').setDisabled(bl);
	Ext.getCmp('hr6').setDisabled(bl);
	Ext.getCmp('hr7').setDisabled(bl);
	Ext.getCmp('hr8').setDisabled(bl);
	Ext.getCmp('hr9').setDisabled(bl);
	Ext.getCmp('hr10').setDisabled(bl);
	Ext.getCmp('hr11').setDisabled(bl);
	Ext.getCmp('hr12').setDisabled(bl);
	Ext.getCmp('hr13').setDisabled(bl);
	Ext.getCmp('hr14').setDisabled(bl);
	Ext.getCmp('hr15').setDisabled(bl);
	Ext.getCmp('hr16').setDisabled(bl);
	Ext.getCmp('hr17').setDisabled(bl);
	Ext.getCmp('hr18').setDisabled(bl);
	Ext.getCmp('hr19').setDisabled(bl);
	Ext.getCmp('hr20').setDisabled(bl);
}*/

/*function ContralPanel() {
	contralPanel = Ext.create('Ext.window.Window', {
		width : 460,
		height : 420,
		closeAction : 'hide',
		overflow : true,
		title : '调压曲线',
		layout : 'fit',
		border : false,
		resizable : false,
		items : [{
			xtype : 'form',
			id : "contral",
			width : 450,
			height : 390,
			layout : {
				type : 'table',
				columns : 2
			},
			items : [{
						xtype : 'combobox',
						id : 'ploy',
						store : ploy,
						width : 200,
						queryMode : 'local',
						valueField : 'id',
						displayField : 'modelName',
						margin : '20 0 0 20',
						labelWidth : 60,
						fieldLabel : '调压策略',
						colspan : 2,
						listeners : {
							'select' : function(combo, records, eOpts) {
								// alert(records[0].data.id);
								currModel = records[0].data;
								var id = currModel.id;
								BindModel(id);
								if (id != 1) {
									ReadOnly(true);
								} else {
									ReadOnly(false);
								}
							}
						}
					}, {
						xtype : 'textfield',
						id : 'hr0',
						margin : '10 10 0 20',
						regex : /(^([2-9])$)|(^([1-9][0-9])$)|(^([0-1][0-7][0-9])$)|(^([0-1][0-8][0])$)/,
						regexText : '预热时间必须大于2小于等于180分钟',
						labelWidth : 60,
						fieldLabel : '预热时间',
						allowBlank : false,
						blankText : '预热时间不能为空'
					}, {
						xtype : 'textfield',
						id : 'hr4',
						margin : '10 20 0 0',
						regex : /(^([1-9])$)|(^([1][0-9])$)|(^([2][0])$)/,
						regexText : '调压速度必须大于1小于等于20',
						labelWidth : 60,
						fieldLabel : '调压速度',
						allowBlank : false,
						blankText : '调压速度不能为空'
					}, {
						xtype : 'textfield',
						id : 'hr5',
						margin : '10 10 0 20',
						labelWidth : 60,
						regex : /(^([0-1][0-9]|[2][0-3])([0-5][0-9])$)|(^([0-1][0-9]|[2][0-4])([0][0])$)/,
						regexText : '调压时间格式不正确，例如：2230代表22:30，2400代表关闭该时间点调压',
						fieldLabel : '时间1'
					}, {
						xtype : 'textfield',
						id : 'hr6',
						margin : '10 20 0 0',
						regex : /(^([1][8-9][0-9]$))|(^([2][0-9][0-9]$))|((^([3][0-1][0-9]$))|(^([3][0-2][0]$)))/,
						regexText : '预设电压只能为180到320',
						labelWidth : 60,
						fieldLabel : '电压1'
					}, {
						xtype : 'textfield',
						id : 'hr7',
						margin : '10 10 0 20',
						labelWidth : 60,
						regex : /(^([0-1][0-9]|[2][0-3])([0-5][0-9])$)|(^([0-1][0-9]|[2][0-4])([0][0])$)/,
						regexText : '调压时间格式不正确，例如：2230代表22:30，2400代表关闭该时间点调压',
						fieldLabel : '时间2'
					}, {
						xtype : 'textfield',
						id : 'hr8',
						margin : '10 20 0 0',
						regex : /(^([1][8-9][0-9]$))|(^([2][0-9][0-9]$))|((^([3][0-1][0-9]$))|(^([3][0-2][0]$)))/,
						regexText : '预设电压只能为180到320',
						labelWidth : 60,
						fieldLabel : '电压2'
					}, {
						xtype : 'textfield',
						id : 'hr9',
						margin : '10 10 0 20',
						regex : /(^([0-1][0-9]|[2][0-3])([0-5][0-9])$)|(^([0-1][0-9]|[2][0-4])([0][0])$)/,
						regexText : '调压时间格式不正确，例如：2230代表22:30，2400代表关闭该时间点调压',
						labelWidth : 60,
						fieldLabel : '时间3'
					}, {
						xtype : 'textfield',
						id : 'hr10',
						margin : '10 20 0 0',
						regex : /(^([1][8-9][0-9]$))|(^([2][0-9][0-9]$))|((^([3][0-1][0-9]$))|(^([3][0-2][0]$)))/,
						regexText : '预设电压只能为180到320',
						labelWidth : 60,
						fieldLabel : '电压3'
					}, {
						xtype : 'textfield',
						id : 'hr11',
						margin : '10 10 0 20',
						regex : /(^([0-1][0-9]|[2][0-3])([0-5][0-9])$)|(^([0-1][0-9]|[2][0-4])([0][0])$)/,
						regexText : '调压时间格式不正确，例如：2230代表22:30，2400代表关闭该时间点调压',
						labelWidth : 60,
						fieldLabel : '时间4'
					}, {
						xtype : 'textfield',
						id : 'hr12',
						margin : '10 20 0 0',
						regex : /(^([1][8-9][0-9]$))|(^([2][0-9][0-9]$))|((^([3][0-1][0-9]$))|(^([3][0-2][0]$)))/,
						regexText : '预设电压只能为180到320',
						labelWidth : 60,
						fieldLabel : '电压4'
					}, {
						xtype : 'textfield',
						id : 'hr13',
						margin : '10 10 0 20',
						regex : /(^([0-1][0-9]|[2][0-3])([0-5][0-9])$)|(^([0-1][0-9]|[2][0-4])([0][0])$)/,
						regexText : '调压时间格式不正确，例如：2230代表22:30，2400代表关闭该时间点调压',
						labelWidth : 60,
						fieldLabel : '时间5'
					}, {
						xtype : 'textfield',
						id : 'hr14',
						margin : '10 20 0 0',
						regex : /(^([1][8-9][0-9]$))|(^([2][0-9][0-9]$))|((^([3][0-1][0-9]$))|(^([3][0-2][0]$)))/,
						regexText : '预设电压只能为180到320',
						labelWidth : 60,
						fieldLabel : '电压5'
					}, {
						xtype : 'textfield',
						id : 'hr15',
						margin : '10 10 0 20',
						regex : /(^([0-1][0-9]|[2][0-3])([0-5][0-9])$)|(^([0-1][0-9]|[2][0-4])([0][0])$)/,
						regexText : '调压时间格式不正确，例如：2230代表22:30，2400代表关闭该时间点调压',
						labelWidth : 60,
						fieldLabel : '时间6'
					}, {
						xtype : 'textfield',
						id : 'hr16',
						margin : '10 20 0 0',
						regex : /(^([1][8-9][0-9]$))|(^([2][0-9][0-9]$))|((^([3][0-1][0-9]$))|(^([3][0-2][0]$)))/,
						regexText : '预设电压只能为180到320',
						labelWidth : 60,
						fieldLabel : '电压6'
					}, {
						xtype : 'textfield',
						id : 'hr17',
						margin : '10 10 0 20',
						regex : /(^([0-1][0-9]|[2][0-3])([0-5][0-9])$)|(^([0-1][0-9]|[2][0-4])([0][0])$)/,
						regexText : '调压时间格式不正确，例如：2230代表22:30，2400代表关闭该时间点调压',
						labelWidth : 60,
						fieldLabel : '时间7'
					}, {
						xtype : 'textfield',
						id : 'hr18',
						margin : '10 20 0 0',
						regex : /(^([1][8-9][0-9]$))|(^([2][0-9][0-9]$))|((^([3][0-1][0-9]$))|(^([3][0-2][0]$)))/,
						regexText : '预设电压只能为180到320',
						labelWidth : 60,
						fieldLabel : '电压7'
					}, {
						xtype : 'textfield',
						id : 'hr19',
						margin : '10 10 0 20',
						regex : /(^([0-1][0-9]|[2][0-3])([0-5][0-9])$)|(^([0-1][0-9]|[2][0-4])([0][0])$)/,
						regexText : '调压时间格式不正确，例如：2230代表22:30，2400代表关闭该时间点调压',
						labelWidth : 60,
						fieldLabel : '时间8'
					}, {
						xtype : 'textfield',
						id : 'hr20',
						margin : '10 20 0 0',
						regex : /(^([1][8-9][0-9]$))|(^([2][0-9][0-9]$))|((^([3][0-1][0-9]$))|(^([3][0-2][0]$)))/,
						regexText : '预设电压只能为180到320',
						labelWidth : 60,
						fieldLabel : '电压8'
					}, {
						xtype : 'button',
						text : '确定',
						width : 40,
						margin : '10 10 20 180',
						handler : function() {
							if (!Ext.getCmp('contral').isValid()) {
								return false;
							}
							ModelApply();
						}
					}, {
						xtype : 'button',
						text : '取消',
						width : 40,
						margin : '10 0 20 0',
						handler : function() {
							contralPanel.hide();
						}
					}]
		}]

	});
	Ext.getCmp('ploy').setValue(1);
}
*/
/*function BindModel(id) {
	// alert(id)
	// Ext.getCmp('status').setValue("编辑");
	Id = id;
	// oper = 1;
	ploy.each(function(record) {
				// alert(record.get('id'));
				if (record.get('id') == id) {
					// Ext.getCmp('ployname').setValue(record.get('modelName'));
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

	// modelWindow.show();
}*/
var Id, currModel;
/*function ModelApply() {
	var selections = Ext.getCmp("gridHr").getSelectionModel().selected.keys;
	// alert(selections);
	if(selections==""){
		Ext.Msg.alert("提示！","请选择设备！");
		return false;
	}

 * var model = currModel; if(Id==1){ model.preTime=Ext.getCmp('hr0').getValue();
 * model.speed=Ext.getCmp('hr4').getValue();
 * model.t1=Ext.getCmp('hr5').getValue(); model.v1=Ext.getCmp('hr6').getValue();
 * model.t2=Ext.getCmp('hr7').getValue(); model.v2=Ext.getCmp('hr8').getValue();
 * model.t3=Ext.getCmp('hr9').getValue();
 * model.v3=Ext.getCmp('hr10').getValue();
 * model.t4=Ext.getCmp('hr11').getValue();
 * model.v4=Ext.getCmp('hr12').getValue();
 * model.t5=Ext.getCmp('hr13').getValue();
 * model.v5=Ext.getCmp('hr14').getValue();
 * model.t6=Ext.getCmp('hr15').getValue();
 * model.v6=Ext.getCmp('hr16').getValue();
 * model.t7=Ext.getCmp('hr17').getValue();
 * model.v7=Ext.getCmp('hr18').getValue();
 * model.t8=Ext.getCmp('hr19').getValue();
 * model.v8=Ext.getCmp('hr20').getValue(); }
 
	if (Id == 1) {
		model = Ext.create("Model", {
					id : Id,
					modelName : "自定义",
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
				}).data
	} else {
		model = currModel;
	}
	Ext.Ajax.request({
				url : 'modelApply.html',
				params : {
					selections : selections,
					model : Ext.encode(model)
				},
				success : function(response) {
					var text = response.responseText;
				}
			});
}*/
/*function Reflush() {
	var selections = Ext.getCmp("gridHr").getSelectionModel().selected.keys;
	Ext.Ajax.request({
				url : 'reflush.html',
				params : {
					selections : selections
				},
				success : function(response) {
					var text = response.responseText;
				}
			});
}*/