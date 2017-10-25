/**
 * 添加用户设置 userWin 查看用户设置窗口 viewUserWin 编辑用户设置窗口 updateUserWin
 */

var userStore;
var userPanel;
var itemsPerPage = 20;
// var roleComboBox = [['0', '请选择...'], ['1', '超级管理员'], ['3', '管理员']];
Ext.define('Role', {
			extend : 'Ext.data.Model',
			fields : [{
						name : 'id',
						type : 'int'
					}, {
						name : 'pareId',
						type : 'int'
					}, {
						name : 'roleName',
						type : 'string'
					}]
		});
var roleComboBox = Ext.create('Ext.data.Store', {
			model : 'Role',
			proxy : {
				type : 'ajax',
				url : 'roles.html',
				method : 'POST',
				reader : {
					type : 'json',
					root : 'roleList',
					successProperty : 'success'
				}
			},
			autoLoad : true,
			listeners : {
				load : function(style, records, options) {
					// alert('aa');
					var rs = Ext.create('Role', {
								id : 0,
								roleName : '请选择...'
							});
					roleComboBox.insert(0, rs);
				}
			}
		});
Ext.onReady(function() {

	Ext.create('Ext.container.Viewport', {
		layout : 'border',
		width : '100%',
		height : '100%',
		items : [{
					region : 'center',
					title : '用户列表',
					border : false,
					margin : '0 0 0 0',
					html : "<div id='centerview' style='width:100%;height:100%'></div>"

				}],
		rendTo : Ext.getBody()
	});

	CreatePanel();
});

function CreatePanel() {
	userStore = Ext.create('Ext.data.Store', {
				autoLoad : true,
				// storeId : 'userStore',
				pageSize : itemsPerPage,
				fields : [{
							name : 'role.roleName',
							type : 'string'
						}, {
							name : 'username',
							type : 'string'
						}, {
							name : 'password',
							type : 'string'
						}, {
							name : 'email',
							type : 'string'
						}, {
							name : 'id',
							type : 'int'
						}, {
							name : 'rid',
							type : 'int'
						}],
				proxy : {
					type : 'ajax',
					method : 'POST',
					async : false,
					url : 'listUser.html',
					reader : {
						type : 'json',
						root : 'userList',
						totalProperty : 'totalCount'
					}
				}

			});
	Ext.create('Ext.grid.Panel', {
		border : false,
		id : 'grid',
		width : '100%',
		height : '100%',
		store : userStore,
		renderTo : 'centerview',
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
			header : '角色名称',
			dataIndex : 'role.roleName',
			flex : 2
		}, {
			header : '用户名称',
			dataIndex : 'username',
			flex : 2
		}, {
			header : 'Email',
			dataIndex : 'email',
			flex : 2
		}, {
			header : '操作',
			dataIndex : 'oper',
			flex : 2,
			renderer : function(val, metadata, record, rowIndex, colIndex,
					store) {
				return "&nbsp;&nbsp;<a href='javascript:void(0)' onclick=\"CreateWin('"
						+ record.get('id')
						+ "','"
						+ 1
						+ "',"+ record.get('rid')+",'"
						+ record.get('username')
						+ "','"
						+ record.get('password')
						+ "','"
						+ record.get('email')
						+ "')\">查看</a>&nbsp;&nbsp;&nbsp;"
						+ "<a href='javascript:void(0)' onclick=\"CreateWin('"
						+ record.get('id')
						+ "','"
						+ 2
						+ "','"
						+ record.get('rid')
						+ "','"
						+ record.get('username')
						+ "','"
						+ record.get('password')
						+ "','"
						+ record.get('email')
						// + "','"
						// + record.get('rid')
						+ "')\">编辑</a>&nbsp;&nbsp;&nbsp;"
						+ "<a href='javascript:void(0)' onclick=\"delUser('"
						+ record.get('id') + "')\">删除</a>"
			}
		}],
		tbar : ['->', {
					text : '新建',
					handler : function() {
						status = 0;
						CreateWin(0, status, 0, "", "", "", "");
					}
				}],
		dockedItems : [{
					id : 'pagingtoolbar',
					xtype : 'pagingtoolbar',
					store : userStore, // same store GridPanel is using
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
	var uid = Ext.getCmp("id").getValue();
	var username = Ext.getCmp("username").getValue();
	var password = Ext.getCmp("password").getValue();
	var email = Ext.getCmp("email").getValue();
	var roleId = Ext.getCmp("roleName").getValue();
	if (uid == 1) {
		Ext.Msg.alert("提示", "系统账户无法编辑！");
		return false;
	}
	if (roleId == null || roleId == 0) {
		Ext.Msg.alert("提示", "角色名称不能为空！");
		return false;
	}
	if (username == "") {
		Ext.Msg.alert("提示", "用户名称不能为空！");
		return false;
	}
	if (password == "") {
		Ext.Msg.alert("提示", "用户密码不能为空！");
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
	if (email == "") {
		Ext.Msg.alert("提示", "邮箱地址不能为空！");
		return false;
	}

	Ext.Ajax.request({
				method : 'POST',
				url : 'editUser.html',
				params : {
					uid : uid,
					status : status,
					username : username,
					password : password,
					email : email,
					roleId : roleId
				},
				waitMsg : '正在操作,请等待!',
				scope : this,
				success : function(r, o) {
					userStore.reload();
					// Ext.MessageBox.alert("提示", "保存成功!");
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
function CreateWin(id, status, rid, username, password, email) {
	var html;
	if (status != 1) {
		html = '<div style="text-align:center"><input type="button" value="保存" onclick="save('
				+ status
				+ ')"/>&nbsp;&nbsp;'
				+ '<input type="button" value="取消" onclick="cancel()"/></div>'
	}
	viewUserWin = Ext.create('Ext.window.Window', {
		id : 'viewUser',
		title : '用户设置',
		region : 'center',
		height : 380,
		width : 320,
		layout : 'fit',
		items : userFrom = new Ext.form.Panel({
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
								fieldLabel : '用户ID',
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
								xtype : 'combobox',
								fieldLabel : '角色名称',
								id : 'roleName',
								margin : '20 20 0 20',
								valueField : 'id',
								displayField : 'roleName',
								store : roleComboBox,
								emptyText : '请选择…………'
							}, {
								fieldLabel : '用户名称',
								name : 'username',
								id : 'username',
								margin : '20 20 0 20',
								fieldLabel : '用户名称',
								allowBlank : false,
								blankText : '用户名不能为空',
								value : username
								// readOnly : true
						}	, {
								fieldLabel : '用户密码',
								name : 'password',
								id : 'password',
								inputType : 'password',
								margin : '20 20 0 20',
								fieldLabel : '用户密码',
								allowBlank : false,
								blankText : '用户密码不能为空',
								value : password
								// readOnly : true
						}	, {
								fieldLabel : '邮箱地址',
								name : 'email',
								id : 'email',
								margin : '20 20 0 20',
								value : email
								// readOnly : true
						}	, {
								xtype : 'panel',
								margin : '30 0 0 0',
								border : false,
								html : html
							}]
				})
	}).show();
	Ext.getCmp('roleName').setValue(parseInt(rid));
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
