
Ext.define('desktop.app.eims.sys.Menu',{
	extend:'desktop.app.comm.CRUDRowEditPanel',
	alias:'widget.Menu',
	moduleName:"sys",//请求模块名称
	controllersName:"Menus",//请求控制器名称
	editWindowId:'eimsMenuEdit',
	
	getStoreModel : function(){
		return 'desktop.app.eims.sys.MenuModel';
	},
	getSearchFieldHeight : function(){
		return 120;
	},
	getSearchFields : function(){
		return[{
	        fieldLabel: '菜单名称',
	        name: 'filter_LIKE_text',	       
	        anchor:'100%'  
	    },{
	        fieldLabel: '组件类别',	       
	        name: 'filter_LIKE_xtype',
	        anchor: '100%'  
	    }]
	},
	getFields:function(){
		return [
	        {name:'id',type: 'int'},
	        {name:'text',type: 'string'},
	        {name:'qtip',type: 'string'},
	        {name:'xtype',type: 'string'},
	        {name:'leaf',type: 'boolean'},
	        {name:'iconCls',type: 'string'},
	        {name:'modules',type: 'string'},
	        {name:'orderNum',type: 'int'},
	        {name:'pid',type: 'int'}];	
	},
	getGridColumns:function(){
		var columns = [Ext.create('Ext.grid.RowNumberer'),{
			text:'菜单名称',
		    dataIndex:'text',
		    width:100,
			sortable:true,
			editor:{xtype:'textfield'}
		},{	   
			text:'组件类别',
		    dataIndex:'xtype',
		    align: "center",		  
		    width:130,
		    sortable:true,		
		    editor:{xtype:'textfield'}
		},{	     
			text:'提示名称',
		    dataIndex:'qtip',
		    //format : 'Y-m-d',
		    align: "center",
		    width:120,
		    sortable:true,
		    editor:{xtype:'textfield'}
		},{	     
			text:'是否叶子节点',
		    dataIndex:'leaf',
		   // format : 'Y-m-d',
		    align: "center",
		    width:120,
		    sortable:true,
		    //format : 'Y-m-d',
		    editor:{xtype:'textfield'}
		},{	  			
			text:'组件显示图标名称',
		    dataIndex:'iconCls',
		    width:150,
		    sortable:true,
		    editor:{xtype:'textfield'}
		},{	  		
			text:'模块',
		    dataIndex:'modules',
		    width:150,
		    sortable:true,
		    flex     : 1,
		    editor:{xtype:'textfield'}
		}];
		return columns;
	},
	getWindowFormItems:function(){
		return[{
		    	xtype:'hidden',
		    	name:'id'
		    },{
		        fieldLabel: '菜单名称',
		        name: 'text',
		        allowBlank:false,
		        emptyText:'菜单名称不能为空！',
		        msgTarget:'菜单名称不能为空！',
		        anchor:'100%'  
		    },{
		        fieldLabel: '组件类别',
		        allowBlank:false,
		        emptyText:'组件类别不能空！',
		        name: 'xtype',
		        anchor: '100%'  
		    },{
		        fieldLabel: '组件显示图标名称',
		        allowBlank:false,
		        emptyText:'组件显示图标名称不能空！',
		        name: 'iconCls',
		        anchor: '100%'  
		    },{
		        fieldLabel: '所属模块',	        
		        name: 'modules',
		        anchor: '100%'  
		    }]
	},	
	getRowBodyTpl:function(){
		var tpl=new Ext.XTemplate(
                '<p><b>菜单名称:</b> {text}</p>',
                '<p><b>组件类别:</b> {xtype}</p><br>'
               
           );
		return tpl;
	}
});
Ext.define('desktop.app.eims.sys.MenuModel',{
	extend: 'Ext.data.Model',
	fields:[
	        {name:'id',type: 'int'},
	        {name:'text',type: 'string'},
	        {name:'qtip',type: 'string'},
	        {name:'xtype',type: 'string'},
	        {name:'leaf',type: 'boolean'},
	        {name:'iconCls',type: 'string'},
	        {name:'modules',type: 'string'},
	        {name:'orderNum',type: 'int'},
	        {name:'pid',type: 'int'}
	       ]
});