Ext.define('desktop.app.comm.RowExpanders',{
	extend:'Ext.grid.plugin.RowExpander',
	alias:['plugin.RowExpanders'],
	mixins: {
        observable: 'Ext.util.Observable'
    },      
    requires: ['Ext.grid.plugin.RowExpander'],
    
    constructor : function(config){
    	var me=this;
    	me.pluginConfig=config;
    	Ext.apply(me,config);
    	Ext.apply(me,{
    		ptype:'rowexpander'
    	});
    	me.on({
    		
    	});
    	me.callParent();
    }
});