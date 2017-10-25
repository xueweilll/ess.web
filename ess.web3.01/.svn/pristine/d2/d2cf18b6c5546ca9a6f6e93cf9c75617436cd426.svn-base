/**
 * 
 */
var loginform;
Ext.onReady(function() {
	/*
	 * Ext.create('Ext.Viewport', { id : 'border-example', layout : 'border',
	 * 0 0', autoEl : { tag : 'div', html : '<img src="image/logo.png"/>' } }) ]
	 * items : [ Ext.create('Ext.Component', { region : 'north', margins : '0 0
	 * });
	 */
	Ext.create('Ext.window.Window', {
		singleton : true,
		//title : '系统登录',
		width :600,
		height :386,
		bodyStyle:'background: transparent;',
		cls:'mycls',
		modal : false,
		closable : false,
		resizable : false,
		closeAction : 'hide',
		draggable : false,
		baseCls: 'my-panel-no-border',
		border : false,
		layout : 'border',
		items : [ Ext.create('Ext.Component', {
			region : 'north',
			height : 154, // give north and south regions a
			collapsed : true,
			border : false,
			margins : '0 0 0 0',
			autoEl : {
				tag : 'div',
				html : '<img src="image/11.png"/>'

			}
		}), loginform = Ext.create('Ext.form.Panel',{
				region :'center',
				border : false,
				bodyStyle:'background: transparent;background-image:url(image/login-from.png);',				
				items : [ {
				xtype : 'textfield',
				id : 'username',
				margin : '40 10 0 40',
				width : 250,
				height:36,
				cls:'mytext',
				labelWidth : 60,
				fieldLabel : '用户名',
				value : 'admin',
				allowBlank : false,
				blankText : '用户名不能为空'
			}, {
				xtype : 'textfield',
				inputType : 'password',
				id : 'password',
				margin : '20 10 0 40',
				width : 250,
				height:36,
				cls:'mytext',
				labelWidth : 60,
				fieldLabel : '密　码',
				value : '1234',
				allowBlank : false,
				blankText : '用户名不能为空',
				listeners:{
					specialkey:function(field,e){
						if(e.getKey()==Ext.EventObject.ENTER){
							onLogin();
						}
					}
				}
			} ]
			}),
			Ext.create('Ext.panel.Panel',{
				region : 'east',
				border : false,
				width : 300,
				bodyStyle:'background: transparent;',
				
				items : [ {
					xtype:'button',
				//	text : "登录",
					cls:'mybutton2',
					width : 80,
					handler : onLogin
				}, {
					xtype:'button',
				//	text : "重置",
					cls:'mybutton1',
					width : 80,
					handler : onReset
				} ]
			}),Ext.create('Ext.panel.Panel',{
			bodyStyle:'background: transparent;',
				region : 'south',
				height:60,
				border : false,
				html:'<p class="bq">© 康乐畜牧 版权所有</p>'
			})],
	}).show();
});

function onLogin() {
	if (loginform.isValid()) {
		Ext.Ajax.request({
			url : 'loginForm.html',
			params : {
				username : Ext.getCmp('username').getValue(),
				password : Ext.getCmp('password').getValue()
			},
			success : function(response) {
				var obj = Ext.decode(response.responseText);
				if (obj.resBool) {
					window.location.href = 'index.html';
				} else {
					Ext.Msg.alert('提示', obj.resStr);
				}
			}
		});
	}
}

function onReset() {
	 Ext.getCmp('username').setValue('');
	 Ext.getCmp('password').setValue('');
}