Ext.require('Ext.Ajax');
function BindXY(currentLat, currentLon) {
}
var data 
Ext.onReady(function() {
	data = Ext.create('Ext.data.Store', {
		fields : ['lat', 'lon'],
		proxy : {
			type : 'ajax',
			url : 'mapMarkers.html',
			reader : {
				type : 'json',
				root : 'markers',
				successProperty : 'success'
			}
		},
		autoLoad : true
	});
	Ext.create('Ext.container.Viewport', {
		title : 'Fit Layout',
		margin : '0 0 0 0',	
		width : '100%',
		height : '100%',
		layout : 'border',
		items : [{
					title : '区域列表',
					// id : "treeview",
					width : 200,
					border : false,
					collapsible : true,
					split : true,
					region : 'west',
					html : "<div id='treeview' style='width:100%;height:100%'></div>"
				}, {
					// xtype : 'Ext.panel.Panel',
					id:'bb',
					title : '猪舍',
					border : false,
					region : 'center',
					  width: '100%',
					  height: '100%',
					  bodyStyle : 'width:100%;height:100%;background:url(image/dap.jpg) no-repeat 0 0;background-size:100% 100%;position:relative;',
					//bodyStyle : 'background-image:url(image/dap.jpg);background-repeat: no-repeat;filter:"progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=\'scale\')";-moz-background-size:100% 100%;background-size:100% 100%;',
					items:[{
						xtype : 'dataview',
						    id:'banksView',  
			                store: data, 
			                trackOver:true,
			                multiSelect:true,  
			                itemSelector: 'div.bank',  
			                selectedItemCls:'bank-selected',  
			                overItemCls: 'bank-hover' ,
			                tpl : new Ext.XTemplate(
								'<div>',  
								'<tpl for=".">',
								'<img src="image/ls.png" style="top:{lat}%;left:{lon}%;width:4%;height:auto;position:absolute" onmouseover="showDTO()" onmouseout="hideDTO()"/>',
								'</tpl>',
								'</div>'
			                ),
			                listeners : {
			                    render : function() {
			                        Ext.fly(this.el).on('click', function(e, t) {
			                            alert(111);
			                            });
			                        }
			                }
					}]
				}],
		renderTo : Ext.getBody()
	});
	
	
	
	CreateTree();
	//CreateMap();
	CreateMarkers(0);
});


/*function ShowDetial(id) {


	var store = Ext.create('Ext.data.Store', {
				model : 'CurrIrItem',
				proxy : {
					type : 'ajax',
					url : 'currentIrStore.html',
					method : 'POST',
					async : true,
					extraParams : {
						dtuId : id
					},
					reader : {
						type : 'json',
						root : 'arrIrStore',
						successProperty : 'success'
					}
				},
				autoLoad : true
			});

	var intervalIr = setInterval(function() {
				store.reload();
			}, 5000);

	
	Ext.create('Ext.window.Window', {
		title : '控制面板',
		height : 380,
		width : 680,
		modal:true,
		layout : 'fit',
		items : {
			xtype : 'grid',
			id : 'IrPanel',
			border : false,
			store : store,
			columns : [{
				header : '设备名称',
				dataIndex : 'name',
				flex : 2
			}, {
				header : '水质溶解氧',
				dataIndex : 'v0',
				flex : 1
			}, {
				header : 'CO2浓度',
				dataIndex : 'v1',
				flex : 1
			}, {
				header : '空气湿度',
				dataIndex : 'v2',
				flex : 1
			}, {
				header : '空气温度',
				dataIndex : 'v3',
				flex : 1
			}, {
				header : '余水深度',
				dataIndex : 'v4',
				flex : 1
			}, {
				header : '水温度',
				dataIndex : 'v5',
				flex : 1
			}, {
				header : '土壤湿度',
				dataIndex : 'v6',
				flex : 1
			}, {
				header : '土壤温度',
				dataIndex : 'v7',
				flex : 1
			}, {
				header : '光照度',
				dataIndex : 'v8',
				flex : 1
			}, {
				header : '水质酸碱度(pH值)',
				dataIndex : 'v9',
				flex : 1
			}, {
				header : '采集时间',
				dataIndex : 'addTime',
				flex : 2
			}],
			viewConfig : {
				loadMask : false
			}
		},
		listeners : {
			"close" : function() {
				clearTimeout(intervalIr);
			}
		}
	}).show();

}*/

function CreateMarkers(pareId) {
	var intervalMarker = setInterval(function() {
		data.reload();
	}, 10000);
	
//	if (markers != null) {
//		map.removeLayer(markers);
//	}
//	markers = new OpenLayers.Layer.Markers("Markers");
//	map.addLayer(markers);
	/*data.on('load', function() {
				data.each(function(record) {
				//	 zb += "<img src='image/ls.png' style='top:"+record.data.lat+"%;left:"+record.data.lon+"%;width:4%;height:auto;position:absolute' onmouseover='showDTO()' onmouseout='hideDTO()'/>";
//						var src = "marker-blue.png";
//						if (record.get('line')) {
//							src = "marker-green.png";
//						}
//						alert(record.get('id'));
//						BindMarkers(record.get('title'), record.get('lat'),record.get('lon'), src, record.get('id'),ShowDetial);
					 //document.getElementById("mapview").innerHTML(zb);
				});
				//Ext.getCmp('mapview').html = zb;
				//Ext.getCmp('bb').body.update("<div id='mapview' style='width:100%;height:100%;background:url(image/dap.jpg) no-repeat 0 0;background-size:100% 100%;position:relative;'>"+zb+"</div>");
			});*/
} 

var pann;
function showDTO(){
	pann = Ext.create('Ext.window.Window', {
		title : "站点信息",
		height : 100,
		width : 200,
		layout : 'fit',
		html : '<p>站点名称：站点1</p><p>当前状态：正常 </p>'
	});
	pann.show();
}

function hideDTO(){
	pann.hide();
}

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
				// dataUrl:'stationTree.html',
				rootVisible : false,
				listeners : {
					itemclick : function(view, record, item, index, e, eOpts) {
						/*
						 * //Ext.Msg.alert(record.get('id')); var map =
						 * Ext.getCmp("map2"); //map.marker.setPosition(new
						 * google.maps.LatLng(31.22,121.45)); var marker = new
						 * google.maps.Marker({ position:new
						 * google.maps.LatLng(0,0), map:map, draggable:true,
						 * title:"position" }); map.addMarker(marker);
						 */
						// alert(record.get('lat'));
						pareId = record.get('id');
						// alert(pareId);
						//SetCenter(record.get('lat'), record.get('lon'), record.get('level'));
						CreateMarkers(pareId);
					}
				}
			});
}