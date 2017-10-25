/**
 * 
 */
function BindXY(currentLat, currentLon) {
	Ext.getCmp("stationy").setValue(currentLat);
	Ext.getCmp("stationx").setValue(currentLon);
}
var stationFrom;
var reloadValue;
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
					collapsible : true,  //可折叠
					split : true,
					region : 'west',
					html : "<div id='treeview' style='width:100%;height:100%'></div>"
				}, {
					title : '区域信息',
					border : false,
					split : true,
					collapsible : true,
					width : 300,
					height : 500,
					region : 'west',
					tbar : ['->', {
								// xtype : 'tbbutton',
								text : '新建',
								handler : function() {
									if (pareId == -1) {
										Ext.Msg.alert("提示","未选择左侧区域列表的节点！");
									} else {
										Ext.getCmp('save').setVisible(true);
										//Ext.getCmp('clear').setVisible(true);
										Clear("新建");
										statusVal = 1;
										pareId = Id;
										Id = 0;
									}
								}
							}, '-', {
								// xtype : 'tbbutton',
								text : '编辑',
								handler : function() {
									if (Id == 0) {
										Ext.Msg.alert("提示","未选择左侧区域列表的节点！");
									} else {
										Ext.getCmp('save').setVisible(true);
									//	Ext.getCmp('clear').setVisible(true);
										Ext.getCmp('status').setValue('编辑');
										statusVal = 2;
									}
								}
							}, '-', {
								// xtype : 'tbbutton',
								text : '删除',
								handler : function() {
									Ext.MessageBox.confirm('提示', '是否确定删除？',
											callback);
									function callback(id) {
										if (id == "yes") {
											Clear('查看');
											statusVal = 0;
											Edit();
										}
									}

								}
							}],
					html : "<div id='stationview' style='width:100%;height:100%'></div>"
				}, {
					title : '地图显示',
					border : false,
					region : 'center',
					html : "<div id='mapview' style='width:100%;height:100%'></div>"
				}],
		renderTo : Ext.getBody()
	});
	stationFrom = Ext.create('Ext.form.Panel', {
				width : 300,
				border : false,
				defaultType : 'textfield',
				buttonAlign : 'center',
				items : [{
							fieldLabel : '当前状态',
							name : 'status',
							id : 'status',
							margin : '20 20 20 20',
							value : "查看",
							readOnly : true

						}, {
							fieldLabel : '区域名称',
							name : 'stationName',
							id : 'stationName',
							fieldLabel : '区域名称',
							allowBlank : false,
							blankText : '区域名称不能为空',
							margin : '20 20 20 20'

						}, {
							fieldLabel : '区域描述',
							name : 'display',
							id : 'display',
							margin : '20 20 20 20'
						}, {
							fieldLabel : '区域坐标X',
							name : 'stationx',
							margin : '20 20 20 20',
							id : 'stationx'
						}, {
							fieldLabel : '区域坐标Y',
							name : 'stationy',
							margin : '20 20 20 20',
							id : 'stationy'
						}],
				buttons : [{
							id:"save", 
							text : '保存',
							hidden : true,
							handler : function() {
								Edit();
							}
						}/*, {
							id:"clear",
							text : '取消',
							hidden : true,
							handler : function() {
								// Edit();
								Clear('查看');
							}
						}*/],
				renderTo : 'stationview'
			})
	CreateTree();
	//CreateMap();
});

function Edit() {
	if (!stationFrom.isValid()&&statusVal!=0) {
		return false;
	}
	Ext.Ajax.request({
		url : 'stationEdit.html',
		params : {
			status : statusVal,
			id : Id,
			stationname : Ext.getCmp('stationName').getValue(),
			stationaddress : Ext.getCmp('display').getValue(),
			stationx : Ext.getCmp('stationx').getValue(),
			stationy : Ext.getCmp('stationy').getValue(),
			pareid : pareId,
			level : currentLevel
		},
		method : 'POST',
		success : function(response) {
			var text = response.responseText;
			// alert(Ext.decode(text).result);
			if (Ext.decode(text).result) {
				reloadValue = Id;
				store.reload();
				Ext.Msg.alert("结果提示","<font style='color:red;text-align:center'>操作成功!</font>");
			} else {
				Ext.Msg.alert("结果提示","<font style='color:red;text-align:center'>操作失败!</font>");
			}
		}
	});
}
var store;
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
				id : 'store',
				model : 'Marker',
				proxy : {
					getMethod: function(){ return 'POST'; },//亮点，设置请求方式,默认为GET  
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
				autoLoad : false,
				listeners: {  
			        'beforeload': function (store, op, options) {  
			            var params = {  
			            		"reloadValue" : reloadValue
			            };  
			            Ext.apply(store.proxy.extraParams, params);   
			        }  
			    }  
			});
	var trees = Ext.create('Ext.tree.Panel', {
				renderTo : 'treeview',
				border : false,
				width : '100%',
				height : '100%',
				store : store,
				rootVisible : false,
				listeners : {
					itemclick : function(view, record, item, index, e, eOpts) {
						// alert(record.get('objData').pareid);
						Id = record.get('objData').id;
						pareId = record.get('objData').pareid;
						BindStation("查看", record.get('text'), record
										.get('objData').stationaddress, record
										.get('objData').stationx, record
										.get('objData').stationy);
						/*SetCenter(record.get('objData').stationx, record.get('objData').stationy, record.get('objData').level);
						if (markers != null) {
							map.removeLayer(markers);
						}
						markers = new OpenLayers.Layer.Markers("Markers");
						map.addLayer(markers);
						var size = new OpenLayers.Size(21, 25);
						var offset = new OpenLayers.Pixel(-(size.w / 2),
								-size.h);
						var icon = new OpenLayers.Icon(
								'js/olapi/img/marker.png', size, offset);
						var marker = new OpenLayers.Marker(
								new OpenLayers.LonLat(
										record.get('objData').stationx, record
												.get('objData').stationy)
										.transform('EPSG:4326', 'EPSG:3857'),
								icon.clone());
						markers.addMarker(marker);*/
					}
				}
			});
	
}
var Id = 0, pareId = -1, statusVal = -1;

function Clear(status) {
	Ext.getCmp('status').setValue(status);
	Ext.getCmp('stationName').setValue("");
	Ext.getCmp('display').setValue("");
	Ext.getCmp('stationx').setValue("");
	Ext.getCmp('stationy').setValue("");
}

function BindStation(status, stationName, display, stationx, stationy) {
	Ext.getCmp('status').setValue(status);
	Ext.getCmp('stationName').setValue(stationName);
	Ext.getCmp('display').setValue(display);
	Ext.getCmp('stationx').setValue(stationx);
	Ext.getCmp('stationy').setValue(stationy);
	Ext.getCmp('save').setVisible(false);
	//Ext.getCmp('clear').setVisible(false);
}
