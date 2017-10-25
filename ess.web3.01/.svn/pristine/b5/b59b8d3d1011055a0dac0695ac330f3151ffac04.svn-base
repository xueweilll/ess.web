var roleStore;
var itemsPerPage = 20;
var status;
var viewRoleWin;
var menuWin, menuStore, checkGroup;
var Ids = "";

Ext.onReady(function() {

	Ext.create('Ext.container.Viewport', {
		layout : 'border',
		width : '100%',
		height : '100%',
		margin : '0 0 0 0',
		items : [{
					region : 'center',
					title : '用户角色列表',
					border : false,
					margin : '0 0 0 0',
					html : "<div id='centerview' style='width:100%;height:100%'></div>"
				}],
		rendTo : Ext.getBody()

	});

	CreatePanel();
	createlimit();
});

function save(status) {

	if(!roleForm.isValid()){
		return false;
	}
	var rid = Ext.getCmp("id").getValue();
	var roleName = Ext.getCmp("roleName").getValue();

	if (roleName == null || roleName == "") {
		Ext.Msg.alert("提示", "角色名称不能为空！");
		return false;
	}

	if (rid == 1) {
		Ext.Msg.alert("提示", "超级管理员无法编辑！");
		return false;
	}

	Ext.Ajax.request({
				method : 'POST',
				url : 'editRole.html',
				params : {
					rid : rid,
					status : status,
					roleName : roleName
				},
				waitMsg : '正在操作,请等待!',
				scope : this,
				success : function(r, o) {
					roleStore.reload();
					// Ext.MessageBox.alert("提示", "保存成功!");
				},
				failure : function(r, o) {
					Ext.MessageBox.alert("提示", "保存失败！!");
				}
			});
	viewRoleWin.close();
}

function cancel() {
	viewRoleWin.close();
	viewUserWin.close();
}
var roleForm;
function CreateWin(id, status, roleName) {
	var html;
	if (status != 1) {
		html = '<div style="text-align:center"><input type="button" value="保存" onclick="save('
				+ status
				+ ')"/>&nbsp;&nbsp;'
				+ '<input type="button" value="取消" onclick="cancel()"/></div>'
	}

	viewRoleWin = Ext.create('Ext.window.Window', {
		id : 'viewRole',
		title : '角色设置',
		region : 'center',
		height : 220,
		width : 280,
		layout : 'fit',
		items : roleForm = new Ext.form.Panel({
					closable : false,
					border : false,
					layout : 'anchor',
					defaults : {
						anchor : '100%'
					},
					defaultType : 'textfield',
					items : [{
								id : 'id',
								fieldLabel : '角色ID',
								name : 'id',
								margin : '20 20 0 20',
								value : id,
								hidden : true
							}, {
								fieldLabel : '当前状态',
								id : 'status',
								name : 'status',
								value : status == 0 ? "添加" : (status == 1
										? "查看"
										: "编辑"),
								margin : '20 20 0 20',
								readOnly : true
							}, {
								fieldLabel : '角色名称',
								id : 'roleName',
								name : 'roleName',
								value : roleName,
								fieldLabel : '用户名',
								allowBlank : false,
								blankText : '角色名称不能为空',
								margin : '20 20 0 20'
							}, {
								xtype : 'panel',
								margin : '30 0 0 0',
								border : false,
								html : html
							}]
				})
	}).show();

}

function CreatePanel() {
	roleStore = Ext.create('Ext.data.Store', {
				autoLoad : true,
				pageSize : itemsPerPage,
				fields : [{
							name : 'roleName',
							type : 'string'
						}],
				proxy : {
					type : 'ajax',
					method : 'POST',
					async : false,
					url : 'listRole.html',
					reader : {
						type : 'json',
						root : 'roleList',
						totalProperty : 'totalCount'
					}
				}

			});

	document.getElementById("centerview").innerHTML = "";

	Ext.create('Ext.grid.Panel', {
		border : false,
		id : 'grid',
		width : '100%',
		height : '100%',
		store : roleStore,
		renderTo : 'centerview',
		columns : [{
			header : "序号",
			width : 80,
			sortable : true,
			flex : 2,
			renderer : function(value, cellmeta, record, rowIndex, columnIndex,
					store) {
				return rowIndex + 1;
			}
		}, {
			header : '角色名称',
			dataIndex : 'roleName',
			flex : 4
		}, {
			header : '操作',
			dataIndex : 'oper',
			flex : 3,
			renderer : function(val, metadata, record, rowIndex, colIndex,
					store) {
				return "&nbsp;&nbsp;<a href='javascript:void(0)' onclick=\"CreateWin('"
						+ record.get('id')
						+ "','"
						+ 1
						+ "','"
						+ record.get('roleName')
						+ "')\">查看</a>&nbsp;&nbsp;&nbsp;"
						+ "<a href='javascript:void(0)' onclick=\"CreateWin('"
						+ record.get('id')
						+ "','"
						+ 2
						+ "','"
						+ record.get('roleName')
						+ "')\">编辑</a>&nbsp;&nbsp;&nbsp;"
						+ "<a href='javascript:void(0)' onclick=\"delRole('"
						+ record.get('id')
						+ "')\">删除</a>"
						+ "&nbsp;&nbsp;&nbsp;<a href='javascript:void(0)' onclick=\"limit('"
						+ record.get('id') + "')\">权限配置</a>";
			}
		}],
		tbar : ['->', {
					text : '新建',
					handler : function() {
						status = 0;
						CreateWin(0, status, "");
					}
				}],
		dockedItems : [{
					id : 'pagingtoolbar',
					xtype : 'pagingtoolbar',
					store : roleStore,
					params : {
						start : 'start',
						limit : 'limit'
					},
					dock : 'bottom',
					displayInfo : true
				}]
	});
}

function delRole(id) {

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
						url : 'delRole.html',
						params : {
							rid : id
						},
						waitMsg : '正在操作,请等待!',
						scope : this,
						success : function(r, o) {
							roleStore.reload();
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
var checkboxItems = [];

function limit(rid) {
	menuWin = Ext.create('Ext.window.Window', {
		id : 'limit',
		title : '权限配置',
		// store : menuStore,
		modal : true,
		region : 'center',
		height : 280,
		width : 360,
		layout : 'fit',
		items : {
			xtype : 'panel',
			border : false,
			items : [{
				xtype : 'checkboxgroup',
				columns : 3,
				columns : [120, 120, 120],
				id : 'checkAll',
				items : [{
					boxLabel : '全选',
					inputValue : '100',
					handler : function() {
						// alert(this.checked);
						for (var i = 0; i < checkboxItems.length; i++) {
							Ext.getCmp('check' + checkboxItems[i].inputValue)
									.setValue(this.checked);
						}
					}
				}]
			}, {
				xtype : 'checkboxgroup',
				columns : 3,
				id : 'rolecheckbox',
				columns : [120, 120, 120],
				items : checkboxItems
			}, {
				xtype : 'button',
				text : '保存',
				margin : '30 0 0 80',
				handler : function() {
					saveLimit(rid);
					menuWin.close();
				}
			}, {
				xtype : 'button',
				text : '取消',
				margin : '30 0 0 80',
				handler : function() {
					menuWin.close();
				}
			}]
		}
	}).show();

	Ext.Ajax.request({
				url : 'limitList.html',
				params : {
					rid : rid
				},
				success : function(response) {
					var text = Ext.decode(response.responseText).ids;
					// alert(text);
					for (var i = 0; i < checkboxItems.length; i++) {
						var id = checkboxItems[i].inputValue;
						// alert(text.indexOf(id));
						if (text.indexOf(id) != -1) {
							// alert(id);
							Ext.getCmp('check' + id).setValue(true);
						} else {
							Ext.getCmp('check' + id).setValue(false);
						}

					}
				}
			})
}

function saveLimit(rid) {
	if (rid == 1) {
		Ext.Msg.alert("提示", "超级管理员无法分配权限！");
		return false;
	}
	Ids = "";
	for (var i = 0; i < checkboxItems.length; i++) {
		if (Ext.getCmp('check' + checkboxItems[i].inputValue).checked) {
			Ids += checkboxItems[i].inputValue + ",";
		}
	}
	Ext.Ajax.request({
		method : 'POST',
		url : 'newLimit.html',
		params : {
			rid : rid,
			Ids : Ids
		},
		scope : this
			// success : function(r, o) {
			// // Ext.MessageBox.alert("提示", "保存成功!");
			// },
			// failure : function(r, o) {
			// Ext.MessageBox.alert("提示", "保存失败！!");
			// }
		});
}

function createlimit() {
	menuStore = Ext.create('Ext.data.Store', {

				fields : [{
							name : 'id',
							type : 'number'
						}, {
							name : 'menuname',
							type : 'string'
						}],
				proxy : {
					type : 'ajax',
					method : 'POST',
					async : true,
					url : 'listMenu.html',
					reader : {
						type : 'json',
						root : 'menuList'
					}
				},
				autoLoad : true
			});

	menuStore.on('load', function() {
				menuStore.each(function(record) {
							checkboxItems.push({
										boxLabel : record.get('menuname'),
										inputValue : record.get('id'),
										id : 'check' + record.get('id')
									});
						});
			});
}
