Ext.define('desktop.app.comm.CenterGrid',{
	extend:'Ext.grid.Panel',
	alias:['widget.CenterGrid'],
	requires:[
		'Ext.grid.plugin.*',
		'Ext.grid.*'
	],
	
	
	
	initComponent : function(config){
		var me=this;
		config=Ext.apply(config,{
			stripeRows: true,
        	border: true,
        	region:'center',
        	margins: "0 3 3 3",
        	loadMask: true,
        	bbar:Ext.create('desktop.app.comm.Paging',{
	      		store: me.getStore(),
	            displayInfo: true,
	            displayMsg: '当前显示 {0} - {1} 共 {2}',
	            emptyMsg: "没有记录",
	            refreshText:'刷新',
	            prevText:'上一页',
	            nextText:'下一页',
	            firstText:'第一页',
	            afterPageText:'最后页',
	            beforePageText:'上一页',
	            lastText:'最后页'
	         })
			
		});
		Ext.apply(this,config);
		this.callParent();
	},
	getStore:function(){
		
	}

	
});
