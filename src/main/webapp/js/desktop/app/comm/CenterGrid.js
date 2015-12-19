Ext.define('desktop.app.comm.CenterGrid',{
	extend:'Ext.grid.Panel',
	alias:['widget.CenterGrid'],
	requires:[
		'Ext.grid.plugin.*',
		'Ext.grid.*',
		'desktop.app.comm.Paging'
	],
	viewConfig: {
        stripeRows: true,
        enableTextSelection: true
    },	
	initComponent : function(config){
		var me=this;
		Ext.apply(me,{
			region:'center',
        	autoScroll:true
        });
		me.bbar=Ext.create('desktop.app.comm.Paging', {
		        store: me.store,  
		        dock : 'bottom',
		        displayInfo : true,
		        displayMsg: '当前显示 {0} - {1} 共 {2}',
		        emptyMsg: "没有记录",
		        refreshText:'刷新',
		        prevText:'上一页',
		        nextText:'下一页',
		        firstText:'第一页',
		        afterPageText:'页',
		        beforePageText:'第',
		        lastText:'最后页'          
		});		
		me.on('afterrender',function(grid){
	            grid.getStore().load();
	            
	    });
		me.callParent([me]);
	}
	
});
