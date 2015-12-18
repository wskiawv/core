/**
 * 教育信息管理系统
 */
Ext.define('desktop.app.eims.Eims',{
	extend: 'Ext.ux.desktop.Module',
	requires:[
	'Ext.ux.desktop.Module',
	'desktop.app.comm.WestPanel',
	'desktop.app.comm.CenterTabPanel'
	],
	id:'eims',
	init: function(){
    	this.launcher = {
            text: '教育信息管理系统',
            iconCls:'icon-grid',
            handler : this.createWindow,
            scope: this
        };
       
    },
    /*config:[{items:[{xtype:'WestPanel'}]}],*/
    
	createWindow : function(){
    	var me=this,desktop = this.app.getDesktop();
    	var tabpanel=Ext.getCmp('mainTab');
    	//var westpanel=Ext.getCmp('westPanel');
    	if(!tabpanel){
    		tabpanel=Ext.create('desktop.app.comm.CenterTabPanel');
    		//return tabpanel;
    	}
    	/*if(!westpanel)
    	{
    		westpanel=Ext.create('desktop.app.comm.WestPanel');
       	}*/
        var win = desktop.getWindow(me.id);
        if(!win){
        	win = desktop.createWindow({
        		id:me.id,
        		title:'教育信息管理系统',
                width:800,
                height:500,
                iconCls: 'icon-grid',
                animCollapse:false,
                constrainHeader:true,
                layout:'border',
                items:[{xtype:'WestPanel'},tabpanel]
             	//items:[me.getWestPanel(),me.getTabPanel()]
              //  items:[{xtype:'CenterTabPanel'}]
        	});
        }
        win.show();
       //	win.add(westpanel,tabpanel);
        //win.add(tabpanel);
        return win;
    }
});