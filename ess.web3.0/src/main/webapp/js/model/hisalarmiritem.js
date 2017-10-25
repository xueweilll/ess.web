/**
 * 
 */
Ext.define('HisAlarmIrItem', {
		extend : 'Ext.data.Model',
		fields : [ {
			name : 'stationName',// 站点名
			type : 'string'
		}, {
			name : 'type',// 设备类型
			type : 'int'
		},  {
			name : 'information',// 报警信息
			type : 'string'
		}, {
			name : 'confirm',//高，底
			type : 'string'
		}, {
			name : 'alarmTime',// 报警时间
			type : 'string'
		}, {
			name : 'handleTime',// 处理时间
			type : 'string'
		}, {
			name : 'status',// 是否处理
			type : 'string'
		}
		]
	});