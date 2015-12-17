
Ext.define('desktop.app.sys.Role',{
	extend:'desktop.app.comm.CRUDPanel',
	alias:'widget.Role',
	moduleName:"sys",//请求模块名称
	controllersName:"Roles",//请求控制器名称
	
	
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
		    format : 'Y-m-d',
		    align: "center",
		    width:120,
		    sortable:true,
		    editor:{xtype:'datefield'}
		},{	     
			text:'更新日期',
		    dataIndex:'update_datetime',
		    format : 'Y-m-d',
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
		    sortable:false,
		    flex     : 1,
		    editor:{xtype:'textfield'}
		}];
		return columns;
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
/*Ext.define('desktop.app.comm.RoleEditWindow',{
	extend:'desktop.app.comm.CommWindow',
	getItems:function(){
		return [{
			
		}];
	}
});*/