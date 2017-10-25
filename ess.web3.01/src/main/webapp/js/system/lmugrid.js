﻿/**
 * 
 */
Ext.define('LmuGrid', {
			extend : 'Ext.grid.Panel',
			requires : ['Ext.selection.CellModel', 'Ext.grid.*', 'Ext.data.*',
					'Ext.util.*', 'Ext.form.*', 'Lmu'],
			xtype : 'cellediting',
			frame : true,

			initComponent : function() {
				this.cellEditing = new Ext.grid.plugin.CellEditing({
							clicksToEdit : 1
						});

				Ext.apply(this, {
							plugins : [this.cellEditing],
							store : lmustore,
							columns : [{
										header : '设备编号',
										dataIndex : 'code',
										flex : 1.5,
										editor : {
											allowBlank : false,
											blankText : '设备编号不能为空'
										}
									}, {
										header : '设备名称',
										dataIndex : 'name',
										flex : 3,
										editor : {
											allowBlank : false,
											blankText : '设备名称不能为空'
										}
									}, {
										header : '设备描述',
										dataIndex : 'address',
										flex : 4,
										editor : {
											allowBlank : true
										}
									}, {
										header : '删除',
										xtype : 'actioncolumn',
										flex : 1.5,
										sortable : false,
										menuDisabled : true,
										items : [{
													icon : 'image/delete.gif',
													tooltip : '删除行',
													scope : this,
													handler : this.onRemoveClick
												}]
									}],
							selModel : {
								selType : 'cellmodel'
							},
							tbar : ['->',{
										text : '添加',
										scope : this,
										handler : this.onAddClick
									}]
						});

				this.callParent();

				this.on('afterlayout', this.loadStore, this, {
							delay : 1,
							single : true
						})
			},

			/*loadStore : function() {
				this.getStore().load({
							// store loading is asynchronous, use a load listener or callback to handle results
							callback : this.onStoreLoad
						});
			},

			onStoreLoad : function() {
				Ext.Msg.show({
							title : 'Store Load Callback',
							msg : 'store was loaded, data available for processing',
							icon : Ext.Msg.INFO,
							buttons : Ext.Msg.OK
						});
			},*/

			onAddClick : function() {
				// Create a model instance
				var rec = new Lmu({
							code : '1',
							name : 'test',
							address : 'test',
							dtu:{'id':0}
						});

				this.getStore().insert(0, rec);
				this.cellEditing.startEditByPosition({
							row : 0,
							column : 0
						});
			},

			onRemoveClick : function(grid, rowIndex) {
				this.getStore().removeAt(rowIndex);
			}
		})