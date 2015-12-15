Ext.define('desktop.app.comm.commButton',{
	extend:'Ext.button.Button',
	requires:[
		'Ext.button.Button'		
	],
	initComponent: function(config){
		
		/*var url = config.url, hide = false;
		if(!Ext.isEmpty(url)){
		 	hide = true;
		}*/
		config=Ext.applyIf({
			hideMode: 'visibility'
			//hidden : hide 
		},config);
		this.callParent(config);
		
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
	initComponent : function(config){
		config = Ext.apply({
			text : '新增', 
			iconCls: 'add'
		}, config);
		this.callParent(config);
		
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
	initComponent : function(config){
		config = Ext.apply({
			text : '编辑', 
			iconCls: 'edit',
			disabled:true
		}, config);
		this.callParent(config);
	
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
	
	initComponent : function(config){
		Ext.applyIf(this,config);
		config = Ext.applyIf({
			text : '删除', 
			iconCls: 'delete',
			disabled:true
		}, config);
		this.callParent(config);
		
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
	
	initComponent : function(config){
		config = Ext.apply({
			text : '保存', 
			iconCls: 'save'
		}, config);
		this.callParent(config);
		
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
	
	initComponent : function(config){
		config = Ext.apply({
			text : '取消', 
			iconCls: 'cancel'
		}, config);
		this.callParent(config);
		
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
	
	initComponent : function(config){
		config = Ext.apply({
			text : '搜索', 
			iconCls: 'search'
		}, config);
		this.callParent(config);
		
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
	
	initComponent : function(config){
		config = Ext.apply({
			text : '导出', 
			iconCls: 'export'
		}, config);
		this.callParent(config);
		
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
	
	initComponent : function(config){
		config = Ext.apply({
			text : '导入', 
			iconCls: 'import'
		}, config);
		this.callParent(config);
		
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
	
	initComponent : function(config){
		config = Ext.apply({
			text : '打印', 
			iconCls: 'print'
		}, config);
		this.callParent(config);
		
	}		
});

Ext.define('desktop.app.comm.DownloadButton',{
	extend:'desktop.app.comm.commButton',
	
	requires:['desktop.app.comm.commButton'],
	
	initComponent:function(config){
		config=Ext.apply({
			text:'下载',
			iconCls:'download'			
		},config);
		this.callParent(config);
	}
});

Ext.define('desktop.app.comm.UploadButton',{
	extend:'desktop.app.comm.commButton',
	
	requires:['desktop.app.comm.commButton'],
	
	initComponent:function(config){
		config=Ext.apply({
			text:'上传',
			iconCls:'upload'	
		},config);
		this.callParent(config);
	}
	
	
});

Ext.define('desktop.app.comm.CutButton',{
	extend:'desktop.app.comm.commButton',
	requires:['desktop.app.comm.commButton'],
	
	
	initComponent:function(config){
		config=Ext.apply({
			text:'剪切',
			iconCls:'cut'	
		},config);
		this.callParent(config);
	}
});

Ext.define('desktop.app.comm.CopyButton',{
	extend:'desktop.app.comm.commButton',
	requires:['desktop.app.comm.commButton'],
	
	
	initComponent:function(config){
		config=Ext.apply({
			text:'复制',
			iconCls:'copy'	
		},config);
		this.callParent(config);
	}
});

Ext.define('desktop.app.comm.PasteButton',{
	extend:'desktop.app.comm.commButton',
	requires:['desktop.app.comm.commButton'],
	
	
	initComponent:function(config){
		config=Ext.apply({
			text:'粘贴',
			iconCls:'paste'	
		},config);
		this.callParent(config);
	}
});
