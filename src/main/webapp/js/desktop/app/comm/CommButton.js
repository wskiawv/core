Ext.define('desktop.app.comm.CommButton',{
	extend:'Ext.button.Button',
	requires:[
		'Ext.button.Button'		
	],
	alias:['widget.CommButton'],
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
		/*var me=this;
		//me=Ext.apply([],config);
		me = Ext.apply([],{
			hideMode: 'visibility'
		});
		me.callParent();*/
		
	}
});


/**
 * 新增
 */
Ext.define('desktop.app.comm.AddButton',{
	extend:'desktop.app.comm.CommButton',
	alias:['widget.AddButton'],
	requires:[
		'desktop.app.comm.CommButton'		
	],
	
	initComponent : function(config){
		var me=this;
		Ext.apply(me,{
			text:'新增',
			tooltip:'新增',
			iconCls: 'add'
        });
		config=Ext.applyIf({
			
		},config);
		this.callParent(config);
		/*var me=this;
		//me=Ext.apply([],config);
		me = Ext.apply([],{
			text : '新增', 
			iconCls: 'add'
		});
		me.callParent();*/
		
	}		
});

/**
 * 编辑
 */
Ext.define('desktop.app.comm.EditButton',{
	extend:'desktop.app.comm.CommButton',
	alias:['widget.EditButton'],
	requires:[
		'desktop.app.comm.CommButton'		
	],
	
	initComponent : function(config){
		var me=this;
		Ext.apply(me,{
			text : '编辑', 
			tooltip:'编辑',
			iconCls: 'edit'
        });
		config=Ext.applyIf({			
		},config);
		this.callParent(config);
	/*	var me=this;
		//me=Ext.apply([],config);
		me = Ext.apply([],{
			text : '编辑', 
			iconCls: 'edit',
			disabled:true
		});
		me.callParent();*/
	}		
});

/**
 * 删除
 */
Ext.define('desktop.app.comm.DeleteButton',{
	extend:'desktop.app.comm.CommButton',
	alias:['widget.DeleteButton'],
	requires:[
		'desktop.app.comm.CommButton'		
	],
	
	initComponent : function(config){
		var me=this;
		Ext.apply(me,{
			text : '删除', 
			tooltip:'删除',
			iconCls: 'delete'
        });
		config=Ext.applyIf({
			
		},config);
		this.callParent(config);
	/*	Ext.applyIf(this,config);
		config = Ext.applyIf({
			text : '删除', 
			iconCls: 'delete',
			disabled:true
		}, config);
		this.callParent(config);*/
		
	}		
});

/**
 * 保存
 */
Ext.define('desktop.app.comm.SaveButton',{
	extend:'desktop.app.comm.CommButton',
	alias:['widget.SaveButton'],
	requires:[
		'desktop.app.comm.CommButton'		
	],
	
	initComponent : function(config){
		var me=this;
		Ext.apply(me,{
			text : '保存', 
			tooltip:'保存',
			iconCls: 'save'
        });
		config=Ext.applyIf({
		
		},config);
		this.callParent(config);
	/*	config = Ext.apply({
			text : '保存', 
			iconCls: 'save'
		}, config);
		this.callParent(config);*/
		
	}		
});

/**
 * 取消
 */
Ext.define('desktop.app.comm.CancelButton',{
	extend:'desktop.app.comm.CommButton',
	alias:['widget.CancelButton'],
	requires:[
		'desktop.app.comm.CommButton'		
	],
	
	initComponent : function(config){
		var me=this;
		Ext.apply(me,{
			text : '取消', 
			tooltip : '取消',
			iconCls: 'cancel'
        });
		config = Ext.apply({
			
		}, config);
		this.callParent(config);
		
	}		
});
/**
 * 重置
 */
Ext.define('desktop.app.comm.ResetButton',{
	extend:'desktop.app.comm.CommButton',
	alias:['widget.ResetButton'],
	requires:[
		'desktop.app.comm.CommButton'		
	],
	
	initComponent : function(config){
		var me=this;
		Ext.apply(me,{
			text : '重置', 
			tooltip:'重置',
			iconCls: 'reset'
        });
		config = Ext.apply({
			
		}, config);
		this.callParent(config);
		
	}		
});

/**
 * 搜索
 */
Ext.define('desktop.app.comm.SearchButton',{
	extend:'desktop.app.comm.CommButton',
	alias:['widget.SearchButton'],
	requires:[
		'desktop.app.comm.CommButton'		
	],
	
	initComponent : function(config){
		var me=this;
		Ext.apply(me,{
			text : '搜索', 
			tooltip : '搜索',
			iconCls: 'search'
        });
		config = Ext.apply({
			
		}, config);
		this.callParent(config);
		
	}		
});


/**
 * 导出
 */
Ext.define('desktop.app.comm.ExportButton',{
	extend:'desktop.app.comm.CommButton',
	alias:['widget.ExportButton'],
	requires:[
		'desktop.app.comm.CommButton'		
	],
	
	initComponent : function(config){
		var me=this;
		Ext.apply(me,{
			text : '导出', 
			tooltip:'导出',
			iconCls: 'export'
        });
		config = Ext.apply({
			
		}, config);
		this.callParent(config);
		
	}		
});

/**
 * 导入
 */
Ext.define('desktop.app.comm.ImportButton',{
	extend:'desktop.app.comm.CommButton',
	alias:['widget.ImportButton'],
	requires:[
		'desktop.app.comm.CommButton'		
	],
	
	initComponent : function(config){
		var me=this;
		Ext.apply(me,{
			text : '导入', 
			tooltip :'导入',
			iconCls: 'import'
        });
		config = Ext.apply({
			
		}, config);
		this.callParent(config);
		
	}		
});

/**
 * 打印
 */
Ext.define('desktop.app.comm.PrintButton',{
	extend:'desktop.app.comm.CommButton',
	alias:['widget.PrintButton'],
	requires:[
		'desktop.app.comm.CommButton'		
	],
	
	initComponent : function(config){
		var me=this;
		Ext.apply(me,{
			text : '打印', 
			tooltip :'打印',
			iconCls: 'print'
        });
		config = Ext.apply({
			
		}, config);
		this.callParent(config);
		
	}		
});

Ext.define('desktop.app.comm.DownloadButton',{
	extend:'desktop.app.comm.CommButton',
	alias:['widget.DownloadButton'],
	requires:['desktop.app.comm.CommButton'],
	
	initComponent:function(config){
		var me=this;
		Ext.apply(me,{
			text:'下载',
			tooltip : '下载',
			iconCls:'download'	
        });
		config=Ext.apply({
					
		},config);
		this.callParent(config);
	}
});
/**
 * 上传按钮
 */
Ext.define('desktop.app.comm.UploadButton',{
	extend:'desktop.app.comm.CommButton',
	alias:['widget.UploadButton'],
	requires:['desktop.app.comm.CommButton'],
	
	initComponent:function(config){
		var me=this;
		Ext.apply(me,{
			text:'上传',
			tooltip : '上传',
			iconCls:'upload'
        });
		config=Ext.apply({
				
		},config);
		this.callParent(config);
	}
	
	
});
/**
 * 剪切按钮
 */
Ext.define('desktop.app.comm.CutButton',{
	extend:'desktop.app.comm.CommButton',
	alias:['widget.CutButton'],
	requires:['desktop.app.comm.CommButton'],
	
	
	initComponent:function(config){
		var me=this;
		Ext.apply(me,{
			text:'剪切',
			tooltip : '剪切',
			iconCls:'cut'
        });
		config=Ext.apply({
			
		},config);
		this.callParent(config);
	}
});
/**
 * 复制按钮
 */
Ext.define('desktop.app.comm.CopyButton',{
	extend:'desktop.app.comm.CommButton',
	alias:['widget.CopyButton'],
	requires:['desktop.app.comm.CommButton'],
	
	
	initComponent:function(config){
		var me=this;
		Ext.apply(me,{
			text:'复制',
			tooltip : '复制',
			iconCls:'copy'
        });
		config=Ext.apply({
				
		},config);
		this.callParent(config);
	}
});

/**
 * 粘贴按钮
 */
Ext.define('desktop.app.comm.PasteButton',{
	extend:'desktop.app.comm.CommButton',
	alias:['widget.PasteButton'],
	requires:['desktop.app.comm.CommButton'],
	
	
	initComponent:function(config){
		var me=this;
		Ext.apply(me,{
			text:'粘贴',
			tooltip : '粘贴',
			iconCls:'paste'
        });
		config=Ext.apply({
				
		},config);
		this.callParent(config);
	}
});
