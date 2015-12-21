Ext.define('desktop.app.comm.WestPanel',{
	extend:'Ext.panel.Panel',
	alias:['widget.WestPanel'],
	requires:[
	'Ext.panel.Panel',
	'Ext.tree.Panel',
	'Ext.data.TreeStore'],
	
	id:'westPanel',
	//eimsMenuData:null,//菜单数据
	initComponent : function(config){
		var me=this;
			//me.getData();
		//var menus=me.getMenus(eimsMenuData);
		var menusItems = [];
		if(sysMenus) {
			for(var i = 0; i < sysMenus.length; i++) {
				menusItems.push({
					xtype : 'treepanel',
					iconCls : 'x-tree-icon x-tree-icon-parent',
					rootVisible : false,
					title : sysMenus[i]["text"],
					store : Ext.create('Ext.data.TreeStore', {
						root: sysMenus[i]
					}),
					listeners:{
						itemclick:function(treepanel, record, item, index, e, opts){
							if(record.get('leaf')){
								var viewType=record.raw['xtype'];
								//var tabpanel=Ext.getCmp("mainTab");
								var tabpanel=me.ownerCt.down("CenterTabPanel");
								/**
				            	 * 如果没有此视图, 创建视图.
				            	 */
				            	if (!tabpanel.down(viewType)) {
				                    var panel = [];
				                    panel.push({
				                    	xtype:viewType,
				                    	title: record.get('text'),
				                    	iconCls: "icon-tab",
				                    	layout:'border',
				                    	closable : true,
				                    	frame:false,				      
				                    });				                    
				                    tabpanel.add(panel);
				                    var p=tabpanel.down(viewType);
				                    tabpanel.setActiveTab(p);				                   
				            	}
				            	/**
				            	 * 如果有此视图, 刷新视图.
				            	 */
				            	else {
				            		var panel = tabpanel.down(viewType);
				                    tabpanel.setActiveTab(panel);
				                    panel.doLayout();
				            	}
							}
						}
					}
				});
			}
		}
		Ext.apply(this,{
			region : 'west',
			title : '菜单',
			id:me.id,
			width:200,
			collapsible : true,
			//xtype:'panel',
			/*split : true,
			width : 200,
			minWidth : 175,
			maxWidth : 400,
			collapsible : true,
			animCollapse : false,
			margins : '0 0 0 5',*/
			layout : 'accordion',
			border : true,
			items :menusItems
		});
		/**
		 * 注册侦听事件
		 */
		me.on({			
			//'beforerender':me.getMenus(),//组件渲染之前加载菜单数据
			//'add':me.getData()
			
		});
		me.callParent();
		
	},
	
	/**
	 * 组装菜单
	 */
	getMenus : function(eimsMenuData){
		//var menu=[];
		//var eimsMenuData=eimsMenuData;
		//console.log("eimsMenuData:"+eimsMenuData[0].title);
		var menusItems = [];
		if(sysMenus) {
			for(var i = 0; i < sysMenus.length; i++) {
				menusItems.push({
					xtype : 'treepanel',
					iconCls : 'x-tree-icon x-tree-icon-parent',
					rootVisible : false,
					title : sysMenus[i]["text"],
					store : Ext.create('Ext.data.TreeStore', {
						fields:['id','text','leaf','qtip','xtype','children'],
						root: sysMenus[i]
					})
				});
			}
		}
		return menusItems;
		/*if(Ext.isEmpty(eimsMenuData)){
			
			for(i=0,len=eimsMenuData.lenght;i<len;i++){
				menu.push({
					title: eimsMenuData[i].title,
        			id:"west_tree_"+i,
        			xtype:'treepanel',
                	//xtype:'WestTreePanel',
                	//selModel : this.getTreeSelectModel(),
        			store : Ext.create('Ext.data.TreeStore', {
        				autoLoad:true,
	    				proxy: {
	                        type: 'ajax',
	                        url: 'js/desktop/app/eims/WestTree.json'
	                    },
						root: eimsMenuData[i].children
					})
                	//menuNodes : .children 
				})
			}
		}
		return menu;*/
	},
	getData:function(){
		var me=this;
		/*var model=Ext.define('Ext.data.Model',{
			flieds:[{
				name:'title',
				name:'root'
				
			}]
		});
		var store=Ext.define('Ext.data.JsonStore',{
			model:model,
			data:'public/javascripts/desktop/app/eims/WestTree.json',
			autoLoad:true
		});*/
		Ext.Ajax.request({
			url: 'js/desktop/app/eims/WestTree.json',		   
		    success: function(response){
		    	
		        var text = response.responseText;	
		        Ext.example.msg('温馨提醒',text);
		        var eimsMenuData=Ext.decode(text);
		      	if(Ext.isArray(eimsMenuData))
		        {
		        	Ext.example.msg('温馨提醒','是个对象');
		        }
		        //Ext.example.msg('温馨提醒',"菜单加载成功");
		        var menu=me.getMenus(eimsMenuData);
		       	me.add(menu);
		       	me.doLayout();
		        	//eimsMenuData=text;
		        // process server response here
		        	//me.doLayout();
		    },
		    failure : function(response){
		    	Ext.example.msg('温馨提醒',"菜单加载失败");
		    }
				});
		//return me.data;
	}
});