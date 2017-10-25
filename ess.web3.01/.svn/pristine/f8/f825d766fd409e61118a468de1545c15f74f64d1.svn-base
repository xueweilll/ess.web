var userStore;
var userPanel;
var itemsPerPage = 20;
// var roleComboBox = [['0', '请选择...'], ['1', '超级管理员'], ['3', '管理员']];
/*Ext.define('lmulimit', {
			extend : 'Ext.data.Model',
			fields : [{
						name : 'id',
						type : 'int'
					}, {
						name : 'up',
						type : 'String'
					}, {
						name : 'down',
						type : 'string'
					}, {
						name : 'msg',
						type : 'string'
					}, {
						name : 'rate',
						type : 'int'
					}]
		});
var roleComboBox = Ext.create('Ext.data.Store', {
			model : 'lmulimit',
			proxy : {
				type : 'ajax',
				url : 'lmuLists.html',
				method : 'POST',
				reader : {
					type : 'json',
					root : 'lmuLimitList',
					successProperty : 'success'
				}
			},
			autoLoad : true,
			listeners : {
				load : function(style, records, options) {
					// alert('aa');
					var rs = Ext.create('lmulimit', {
								id : 0,
								roleName : '请选择...'
							});
					roleComboBox.insert(0, rs);
				}
			}
		});*/
Ext.onReady(function() {

	Ext.create('Ext.container.Viewport', {
		layout : 'border',
		width : '100%',
		height : '100%',
		items : [{
					region : 'center',
					title : '报警列表',
					border : false,
					margin : '0 0 0 0',
					html : "<div id='lmulimit' style='width:100%;height:100%'></div>"

				}],
		rendTo : Ext.getBody()
	});

	CreatePanel();
});

function CreatePanel() {
	lmuLimitStore = Ext.create('Ext.data.Store', {
				autoLoad : true,
				// storeId : 'userStore',
				pageSize : itemsPerPage,
				fields : [{
							name : 'msg',
							type : 'string'
						}, {
							name : 'type',
							type : 'int'
						}, {
							name : 'up',
							type : 'string'
						}, {
							name : 'down',
							type : 'string'
						}, {
							name : 'id',
							type : 'int'
						}, {
							name : 'rate',
							type : 'int'
						}],
				proxy : {
					type : 'ajax',
					method : 'POST',
					async : false,
					url : 'listLmuLimit.html',
					reader : {
						type : 'json',
						root : 'lmuLimitList',
						totalProperty : 'totalCount'
					}
				}

			});
	Ext.create('Ext.grid.Panel', {
		border : false,
		id : 'grid',
		width : '100%',
		height : '100%',
		store : lmuLimitStore,
		renderTo : 'lmulimit',
		columns : [{
			header : "序号",
			// width : 80,
			sortable : true,
			flex : 2,
			renderer : function(value, cellmeta, record, rowIndex, columnIndex,
					store) {
				return rowIndex + 1;
			}
		}, {
			header : '设备信息',
			dataIndex : 'msg',
			flex : 2
		}, {
			header : '下限',
			dataIndex : 'down',
			flex : 2
		}, {
			header : '上限',
			dataIndex : 'up',
			flex : 2
		}, {
			header : '类型',
			dataIndex : 'type',
			flex : 2
		}, {
			header : '报警频率',
			dataIndex : 'rate',
			flex : 2
		}, {
			header : '操作',
			dataIndex : 'oper',
			flex : 2,
			renderer : function(val, metadata, record, rowIndex, colIndex,
					store) {
				
				var msg = record.get("msg");
				console.log(msg);
				return "&nbsp;&nbsp;<a href='javascript:void(0)' onclick=\"CreateWin('"
						+ record.get('id')
						+ "','"
						+ 1
						+ "',"+"'"
						+msg +"'"
						+",'"
						+ record.get('up')
						+ "','"
						+ record.get('down')
						+ "','"
						+ record.get('rate')
						+ "','"
						+ record.get('type')
						+ "')\">查看</a>&nbsp;&nbsp;&nbsp;"
						+ "<a href='javascript:void(0)' onclick=\"CreateWin('"
						+ record.get('id')
						+ "','"
						+ 2
						+ "'," + "'"
						+ msg + "'"
						+ ",'"
						+ record.get('up')
						+ "','"
						+ record.get('down')
						+ "','"
						+ record.get('rate')
						+ "','"
						+ record.get('type')
						// + "','"
						// + record.get('rid')
						+ "')\">编辑</a>&nbsp;&nbsp;&nbsp;"
						/*+ "<a href='javascript:void(0)' onclick=\"delUser('"
						+ record.get('id') + "')\">删除</a>"*/
			}
		}],
		tbar : ['->', {
					text : '新建',
					handler : function() {
						status = 0;
						CreateWin(0, status, "", "", "", "", "","");
					}
				}],
		dockedItems : [{
					id : 'pagingtoolbar',
					xtype : 'pagingtoolbar',
					store : lmuLimitStore, // same store GridPanel is using
					params : {
						start : 'start',
						limit : 'limit'
					},
					dock : 'bottom',
					displayInfo : true
				}]
	})
}
var viewUserWin, status;

function save(status) {
	if (!userFrom.isValid()) {
		return false;
	}
	var id = Ext.getCmp("id").getValue();
	var msg = Ext.getCmp("msg").getValue();
	var down = Ext.getCmp("down").getValue();
	var up = Ext.getCmp("up").getValue();
	var rate = Ext.getCmp("rate").getValue();
	var type = Ext.getCmp("type").getValue();
	
	if (msg == null || msg == "") {
		Ext.Msg.alert("提示", "设备信息不能为空！");
		return false;
	}
	if (down == "") {
		Ext.Msg.alert("提示", "报警下限不能为空！");
		return false;
	}
	if (up == "") {
		Ext.Msg.alert("提示", "报警上限不能为空！");
		return false;
	}
	// if (confirmPassword == "") {
	// Ext.Msg.alert("提示", "确认密码不能为空！");
	// return false;
	// }
	// if (password != confirmPassword) {
	// Ext.Msg.alert("提示", "用户密码必须和确认密码一致！");
	// return false;
	// }
	if (rate == null || rate == "") {
		Ext.Msg.alert("提示", "报警频率不能为空！");
		return false;
	}
	
	Ext.Ajax.request({
				method : 'POST',
				url : 'editLmuLimit.html',
				params : {
					id : id,
					msg : msg,
					status : status,
					down : down,
					up : up,
					rate : rate,
					type : type
				},
				waitMsg : '正在操作,请等待!',
				scope : this,
				success : function(r, o) {
					lmuLimitStore.reload();
					Ext.MessageBox.alert("提示", "保存成功!");
				},
				failure : function(r, o) {
					Ext.MessageBox.alert("提示", "保存失败！!");
				}
			});
	viewUserWin.close();

}

function cancel() {
	viewUserWin.close();
}

var userFrom;
function CreateWin(id, status, msg, up, down, rate,type) {
	var html;
	if (status != 1) {
		html = '<div style="text-align:center"><input type="button" value="保存" onclick="save('
				+ status
				+ ')"/>&nbsp;&nbsp;'
				+ '<input type="button" value="取消" onclick="cancel()"/></div>'
	}
	viewUserWin = Ext.create('Ext.window.Window', {
		id : 'viewUser',
		title : '报警设置', 
		region : 'center',
		height : 380,
		width : 320,
		layout : 'fit',
		items : userFrom = new Ext.form.Panel({
					width : 300,
					height : 450,
					closable : false,
					border : false,
					layout : 'anchor',
					defaults : {
						anchor : '100%'
					},
					defaultType : 'textfield',
					items : [{
								id : 'id',
								fieldLabel : 'ID',
								name : 'id',
								margin : '20 20 0 20',
								value : id,
								hidden : true
							}, {
								fieldLabel : '当前状态',
								name : 'status',
								id : 'status',
								value : status == 0 ? "添加" : (status == 1
										? "查看"
										: "编辑"),
								margin : '20 20 0 20',
								readOnly : true
							}, {
								fieldLabel : '设备信息',
								name : 'msg',
								id : 'msg',
								margin : '20 20 0 20',
								fieldLabel : '设备信息',
								allowBlank : false,
								blankText : '设备信息不能为空',
								value : msg
						   }, {
								fieldLabel : '报警下限',
								name : 'down',
								id : 'down',
								margin : '20 20 0 20',
								fieldLabel : '报警下限',
								allowBlank : false,
								blankText : '报警下限不能为空',
								value : down,
								regex : /^-?\d+$/,
								regexText : '输入整数'
								// readOnly : true
						   }, {
								fieldLabel : '报警上限',
								name : 'up',
								id : 'up',
								margin : '20 20 0 20',
								fieldLabel : '报警上限',
								allowBlank : false,
								blankText : '报警上限不能为空',
								value : up,
								regex : /^-?\d+$/,
								regexText : '输入整数'
								// readOnly : true
						   }, {
								fieldLabel : '报警频率',
								name : 'rate',
								id : 'rate',
								margin : '20 20 0 20',
								fieldLabel : '报警频率',
								allowBlank : false,
								blankText : '报警频率不能为空',
								value : rate,
								regex : /^[0-9]*[1-9][0-9]*$/,
								regexText : '输入正整数'
								// readOnly : true
						   }, {
								fieldLabel : '设备类型',
								name : 'type',
								id : 'type',
								margin : '20 20 0 20',
								fieldLabel : '设备类型',
								allowBlank : false,
								blankText : '设备类型不能为空',
								value : type,
								regex : /^[0-9]*[1-9][0-9]*$/,
								regexText : '输入正整数'
								// readOnly : true
						   }, {
								xtype : 'panel',
								margin : '30 0 0 0',
								border : false,
								html : "<div style='text-align:center;color:red'>1,氨气报警  2,温度报警  3,湿度报警,请勿修改或重复添加</div>"
							}, {
								xtype : 'panel',
								margin : '30 0 0 0',
								border : false,
								html : html
							}]
				})
	}).show();
}

function delUser(id) {
	if (id == 0 || id == null) {
		Ext.Msg.alert("提示", "ID为空！");
		return false;
	}
	if (id == 1) {
		Ext.Msg.alert("提示", "超级管理员无法删除！");
		return false;
	}
	var submitFun = function(buttonId, text, opt) {
		if (buttonId == 'yes') {
			Ext.Ajax.request({
						method : 'POST',
						url : 'delUser.html',
						params : {
							uid : id
						},
						waitMsg : '正在操作,请等待!',
						scope : this,
						success : function(r, o) {
							userStore.reload();
							// Ext.MessageBox.alert("提示", "删除成功!");
						},
						failure : function(r, o) {
							Ext.MessageBox.alert("提示", "删除失败！!");
						}
					});
		}
	};
	Ext.Msg.show({
				title : '提示',
				msg : '确定要删除吗？',
				buttons : Ext.Msg.YESNO,
				scope : this,
				fn : submitFun
			});
}
