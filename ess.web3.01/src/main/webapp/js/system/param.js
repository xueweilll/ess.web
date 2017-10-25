var refreshBox,languageBox;
Ext.onReady(function() {
	Ext.create('Ext.container.Viewport', {
		layout : 'border',
		width : '100%',
		height : '100%',
		margin : '0 0 0 0',
		items : [ {
			region : 'center',
			title : '参数设置',
			border : false,
			margin : '0 0 0 0',
			html : "<div id='centerview' style='width:100%;height:100%'></div>"
		} ],
		rendTo : Ext.getBody()
	});

	CreatePanel();
	
});

function CreatePanel() {
	var panel = Ext.create('Ext.panel.Panel', {
	    bodyPadding: 50,
	    margin : '50 0 0 200',
	    width : 500,
	    height : 300,
	    border : false,
	    renderTo:'centerview',
	    items: [{
	    	xtype : 'textfield',
	    	id : 'IP',
			fieldLabel : 'IP地址',
			margin : '20 0 0 0'
	    },{
	    	xtype : 'textfield',
	    	id : 'Port',
			fieldLabel : '端口号',
			margin : '20 0 0 0'
	    },
	    refreshBox,
	    languageBox
	    ],
	    buttonAlign:'center',
	    buttons : [{
	    	text: '确定',
	    	margin : '0 40 0 0',
	        handler: function() {
	        	save();
	        }
	    },{
	    	text: '恢复默认',
	        handler: function() {
	        	getDefault();
	        }
	    }]
	});
	
	var paramStore = Ext.create('Ext.data.Store', {
		autoLoad : true,
	    fields: [{
			name : 'id',
			type : 'number'
		},{
			name : 'name',
			type : 'string'
		},{
			name : 'value',
			type : 'string'
		},{
			name : 'defaultValue',
			type : 'string'
		}],
	    proxy : {
			type : 'ajax',
			method : 'POST',
			url : 'param.html',
			reader : {
				type : 'json',
				root : 'paramsList',
				successProperty : 'success'
			}
		}
	});
	var IP,Port,Refresh,Language;
	paramStore.on('load',function() {
		paramStore.each(function(record) {
			if(record.get('name')=="IP"){
				IP=record.get('value');
			}
			if(record.get('name')=="Port"){
				Port=record.get('value');
			}
			if(record.get('name')=="Refresh"){
				Refresh=record.get('value');
			}
			if(record.get('name')=="Language"){
				Language=record.get('value');
			}
		});
		Ext.getCmp('Refresh').setValue(Refresh);
		Ext.getCmp('Language').setValue(Language);
		Ext.getCmp('IP').setValue(IP);
		Ext.getCmp('Port').setValue(Port);
	});
}

var refreshStore = Ext.create('Ext.data.Store', {
    fields: ['name', 'value'],
    data : [
        {"name":"5000", "value":"5000"},
        {"name":"10000", "value":"10000"},
        {"name":"15000", "value":"15000"}
    ]
});

var refreshBox = Ext.create('Ext.form.ComboBox', {
	id : 'Refresh',
	margin : '20 0 0 0',
    fieldLabel: '页面刷新时间',
    store: refreshStore,
    queryMode: 'local',
    displayField: 'value',
    valueField: 'name'
});


var languageStore = Ext.create('Ext.data.Store', {
    fields: ['name', 'value'],
    data : [
        {"name":"CH", "value":"中文"},
        {"name":"EN", "value":"ENGLISH"}
    ]
});

languageBox = Ext.create('Ext.form.ComboBox', {
	id : 'Language',
	margin : '20 0 0 0',
    fieldLabel: '系统语言',
    store: languageStore,
    queryMode: 'local',
    displayField: 'value',
    valueField: 'name'
});

function save() {
	var IP = Ext.getCmp('IP').getValue();
	var Port = Ext.getCmp('Port').getValue();
	var Refresh = Ext.getCmp('Refresh').getValue();
	var Language = Ext.getCmp('Language').getValue();
	
	Ext.Ajax.request({
		method : 'POST',
		url : 'paramEdit.html',
		async : false,
		params : {
			IP : IP,
			Port : Port,
			Refresh : Refresh,
			Language : Language
		},
		waitMsg : '正在操作,请等待!',
		scope : this,
		success : function(r, o) {
		},
		failure : function(r, o) {
			Ext.MessageBox.alert("提示", "保存失败！!");
		}
	});
}

function getDefault() {
	var paramStore = Ext.create('Ext.data.Store', {
		autoLoad : true,
	    fields: [{
			name : 'id',
			type : 'number'
		},{
			name : 'name',
			type : 'string'
		},{
			name : 'value',
			type : 'string'
		},{
			name : 'defaultValue',
			type : 'string'
		}],
	    proxy : {
			type : 'ajax',
			method : 'POST',
			url : 'param.html',
			reader : {
				type : 'json',
				root : 'paramsList',
				successProperty : 'success'
			}
		}
	});
	var IP,Port,Refresh,Language;
	paramStore.on('load',function() {
		paramStore.each(function(record) {
			if(record.get('name')=="IP"){
				IP=record.get('defaultValue');
			}
			if(record.get('name')=="Port"){
				Port=record.get('defaultValue');
			}
			if(record.get('name')=="Refresh"){
				Refresh=record.get('defaultValue');
			}
			if(record.get('name')=="Language"){
				Language=record.get('defaultValue');
			}
		});
		Ext.getCmp('Refresh').setValue(Refresh);
		Ext.getCmp('Language').setValue(Language);
		Ext.getCmp('IP').setValue(IP);
		Ext.getCmp('Port').setValue(Port);
		save();
	});
}


