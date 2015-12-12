
Ext.define('desktop.app.sys.Role',{
	extend:'desktop.app.comm.CRUDPanel',
	alias:['widget.Role'],
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
			text:'姓名',
		    dataIndex:'name',
		    width:150,
			sortable:true
			
		},{	   
			text:'角色类型',
		    dataIndex:'role_type',
		    align: "center",		  
		    width:130,
		    sortable:true		   
		},{	     
			text:'创建日期',
		    dataIndex:'create_datetime',
		    format : 'Y-m-d',
		    align: "center",
		    width:100,
		    sortable:true		    
		},{	     
			text:'更新日期',
		    dataIndex:'update_datetime',
		    format : 'Y-m-d',
		    align: "center",
		    width:100,
		    sortable:true,
		    editor:{xtype:'datefield'}
		},{	  			
			text:'状态',
		    dataIndex:'status',
		    width:150,
		    sortable:true		    
		},{	  		
			text:'备注',
		    dataIndex:'remark',
		    width:150,
		    sortable:true		   
		}];
		return columns;
	}
});