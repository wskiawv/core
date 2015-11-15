Ext.define('desktop.app.comm.CenterTabPanel',{
	extend:'Ext.tab.Panel',
	alias:['widget.CenterTabPanel'],
	requires:[
	          'Ext.tab.Panel',
	          'Ext.ux.TabCloseMenu'
	],
	
	
	id:'mainTab',
	initComponent : function(){
		var me=this;
		Ext.apply(this,{
			id:me.id,
			region:'center',
			renderTo: document.body,
			/*plugins: Ext.create('Ext.ux.TabCloseMenu',{
				closeAllTabsText:'关闭所有窗口',
				closeOthersTabsText:'关闭其他窗口',
				closeTabText:'关闭窗口'
			}),*/
			layout:'fit',
			items:[{
				title:'首页'
			}],
			plugins: Ext.create('Ext.ux.TabCloseMenu', {
	            extraItemsTail: [
	                '-',
	                {
	                    text: 'Closable',
	                    checked: true,
	                    hideOnClick: true,
	                    handler: function (item) {
	                        currentItem.tab.setClosable(item.checked);
	                    }
	                },
	                '-',
	                {
	                    text: 'Enabled',
	                    checked: true,
	                    hideOnClick: true,
	                    handler: function(item) {
	                        currentItem.tab.setDisabled(!item.checked);
	                    }
	                }
	            ],
	            listeners: {
	                beforemenu: function (menu, item) {
	                    var enabled = menu.child('[text="Enabled"]'); 
	                    menu.child('[text="Closable"]').setChecked(item.closable);
	                    if (item.tab.active) {
	                        enabled.disable();
	                    } else {
	                        enabled.enable();
	                        enabled.setChecked(!item.tab.isDisabled());
	                    }

	                    currentItem = item;
	                }
	            }
	        })
		});
		this.callParent(arguments);
	}
	
});
