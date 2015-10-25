Ext.define('desktop.app.comm.commButton',{
	extend:'Ext.button.Button',
	requires:[
		'Ext.button.Button'		
	],
	constructor : function(config){
		
		/*var url = config.url, hide = false;
		if(!Ext.isEmpty(url)){
		 	hide = true;
		}*/
		config=Ext.apply({
			hideMode: 'visibility'
			//hidden : hide 
		},config);
		this.callParent(arguments);
		
	}
});


/**
 * 新增
 */
Ext.define('desktop.app.comm.AddButton',{
	extend:'desktop.app.comm.commButton',
	
	requires:[
		'desktop.app.comm.commButton'		
	],
	constructor : function(config){
		config = Ext.apply({
			text : '新增', 
			iconCls: 'add'
		}, config);
		this.callParent(arguments);
		
	}		
});

/**
 * 编辑
 */
Ext.define('desktop.app.comm.EditButton',{
	extend:'desktop.app.comm.commButton',
	
	requires:[
		'desktop.app.comm.commButton'		
	],
	constructor : function(config){
		config = Ext.apply({
			text : '编辑', 
			iconCls: 'edit',
			disabled:true
		}, config);
		this.callParent(arguments);
	
	}		
});

/**
 * 删除
 */
Ext.define('desktop.app.comm.DeleteButton',{
	extend:'desktop.app.comm.commButton',
	
	requires:[
		'desktop.app.comm.commButton'		
	],
	
	constructor : function(config){
		config = Ext.apply({
			text : '删除', 
			iconCls: 'delete',
			disabled:true
		}, config);
		this.callParent(arguments);
		
	}		
});

/**
 * 保存
 */
Ext.define('desktop.app.comm.SaveButton',{
	extend:'desktop.app.comm.commButton',
	
	requires:[
		'desktop.app.comm.commButton'		
	],
	
	constructor : function(config){
		config = Ext.apply({
			text : '保存', 
			iconCls: 'save'
		}, config);
		this.callParent(arguments);
		
	}		
});

/**
 * 取消
 */
Ext.define('desktop.app.comm.CancelButton',{
	extend:'desktop.app.comm.commButton',
	
	requires:[
		'desktop.app.comm.commButton'		
	],
	
	constructor : function(config){
		config = Ext.apply({
			text : '取消', 
			iconCls: 'cancel'
		}, config);
		this.callParent(arguments);
		
	}		
});

/**
 * 搜索
 */
Ext.define('desktop.app.comm.SearchButton',{
	extend:'desktop.app.comm.commButton',
	
	requires:[
		'desktop.app.comm.commButton'		
	],
	
	constructor : function(config){
		config = Ext.apply({
			text : '搜索', 
			iconCls: 'search'
		}, config);
		this.callParent(arguments);
		
	}		
});


/**
 * 导出
 */
Ext.define('desktop.app.comm.ExportButton',{
	extend:'desktop.app.comm.commButton',
	
	requires:[
		'desktop.app.comm.commButton'		
	],
	
	constructor : function(config){
		config = Ext.apply({
			text : '导出', 
			iconCls: 'export'
		}, config);
		this.callParent(arguments);
		
	}		
});

/**
 * 导入
 */
Ext.define('desktop.app.comm.ImportButton',{
	extend:'desktop.app.comm.commButton',
	
	requires:[
		'desktop.app.comm.commButton'		
	],
	
	constructor : function(config){
		config = Ext.apply({
			text : '导入', 
			iconCls: 'import'
		}, config);
		this.callParent(arguments);
		
	}		
});

/**
 * 打印
 */
Ext.define('desktop.app.comm.PrintButton',{
	extend:'desktop.app.comm.commButton',
	
	requires:[
		'desktop.app.comm.commButton'		
	],
	
	constructor : function(config){
		config = Ext.apply({
			text : '打印', 
			iconCls: 'print'
		}, config);
		this.callParent(arguments);
		
	}		
});

Ext.define('desktop.app.comm.DownloadButton',{
	extend:'desktop.app.comm.commButton',
	
	requires:['desktop.app.comm.commButton'],
	
	constructor:function(config){
		config=Ext.apply({
			text:'下载',
			iconCls:'download'			
		},config);
		this.callParent();
	}
});

Ext.define('desktop.app.comm.UploadButton',{
	extend:'desktop.app.comm.commButton',
	
	requires:['desktop.app.comm.commButton'],
	
	constructor:function(config){
		config=Ext.apply({
			text:'上传',
			iconCls:'upload'	
		});
		this.callParent();
	}
	
	
});

Ext.define('desktop.app.comm.CutButton',{
	extend:'desktop.app.comm.commButton',
	requires:['desktop.app.comm.commButton'],
	
	
	constructor:function(config){
		config=Ext.apply({
			text:'剪切',
			iconCls:'cut'	
		});
		this.callParent();
	}
});

Ext.define('desktop.app.comm.CopyButton',{
	extend:'desktop.app.comm.commButton',
	requires:['desktop.app.comm.commButton'],
	
	
	constructor:function(config){
		config=Ext.apply({
			text:'复制',
			iconCls:'copy'	
		});
		this.callParent();
	}
});

Ext.define('desktop.app.comm.PasteButton',{
	extend:'desktop.app.comm.commButton',
	requires:['desktop.app.comm.commButton'],
	
	
	constructor:function(config){
		config=Ext.apply({
			text:'粘贴',
			iconCls:'paste'	
		});
		this.callParent();
	}
});
