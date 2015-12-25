
Ext.define('desktop.app.sys.Role',{
	extend:'desktop.app.comm.CRUDPanel',
	alias:'widget.Role',
	moduleName:"sys",//请求模块名称
	controllersName:"Roles",//请求控制器名称
	editWindowId:'eimsRoleEdit',
	
	getStoreModel : function(){
		return 'desktop.app.sys.RoleModel';
	},
	getSearchFieldHeight : function(){
		return 120;
	},
	getSearchFields : function(){
		return[{
	        fieldLabel: '角色名称',
	        name: 'filter_LIKE_name',	       
	        anchor:'100%'  
	    },{
	        fieldLabel: '角色类型',	       
	        name: 'filter_LIKE_role_type',
	        anchor: '100%'  
	    }]
	},
	getFields:function(){
		return [
	        {name:'id'},
	        {name:'name'},
	        {name:'role_type'},
	        {name:'create_datetime',dateFormat:'Y-m-d H:i:s'},
	        {name:'update_datetime',dateFormat:'Y-m-d H:i:s'},
	        {name:'status'},
	        {name:'remark'}];	
	},
	getGridColumns:function(){
		var columns = [Ext.create('Ext.grid.RowNumberer'),{
			text:'角色名',
		    dataIndex:'name',
		    width:100,
			sortable:true,
			editor:{xtype:'textfield'}
		},{	   
			text:'角色类型',
		    dataIndex:'role_type',
		    align: "center",		  
		    width:130,
		    sortable:true,		
		    editor:{xtype:'textfield'}
		},{	     
			text:'创建日期',
		    dataIndex:'create_datetime',
		    renderer: Ext.util.Format.dateRenderer('Y-m-d'),		   
		    align: "center",
		    width:120,
		    sortable:true,
		    editor:{xtype:'datefield'}
		},{	     
			text:'更新日期',
		    dataIndex:'update_datetime',
		    renderer: Ext.util.Format.dateRenderer('Y-m-d'),	
		    align: "center",
		    width:120,
		    sortable:true,
		    format : 'Y-m-d',
		    editor:{xtype:'datefield'}
		},{	  			
			text:'状态',
		    dataIndex:'status',
		    width:150,
		    sortable:true,
		    editor:{xtype:'textfield'}
		},{	  		
			text:'备注',
		    dataIndex:'remark',
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
		        fieldLabel: '角色名称',
		        name: 'name',
		        allowBlank:false,
		        emptyText:'角色名称不能为空！',
		        msgTarget:'角色名称不能为空！',
		        anchor:'100%'  
		    },{
		        fieldLabel: '角色类型',
		        allowBlank:false,
		        emptyText:'角色类型不能空！',
		        name: 'role_type',
		        anchor: '100%'  
		    },{
		        fieldLabel: '状态',
		        allowBlank:false,
		        emptyText:'状态不能空！',
		        name: 'status',
		        anchor: '100%'  
		    },{
		        fieldLabel: '备注',	        
		        name: 'remark',
		        anchor: '100%'  
		    }]
	},	
	getRowBodyTpl:function(){
		var tpl=new Ext.XTemplate(
                '<p><b>角色名:</b> {name}</p>',
                '<p><b>角色类型:</b> {role_type}</p><br>',
                '<p><b>备注:</b> {remark}</p>'
           );
		return tpl;
	}
});
Ext.define('desktop.app.sys.RoleModel',{
	extend: 'Ext.data.Model',
	fields:[
	        {name:'id',type: 'string'},
	        {name:'name',type: 'string'},
	        {name:'role_type',type: 'string'},
	        {name:'create_datetime',type: 'string',dateFormat:'Y-m-d H:i:s'},
	        {name:'update_datetime',type: 'string',dateFormat:'Y-m-d H:i:s'},
	        {name:'status',type: 'string'},
	        {name:'remark',type: 'string'}
	       ]
});