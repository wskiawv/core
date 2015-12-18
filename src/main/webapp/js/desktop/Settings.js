/*!
 * Ext JS Library 4.0
 * Copyright(c) 2006-2011 Sencha Inc.
 * licensing@sencha.com
 * http://www.sencha.com/license
 */

Ext.define('Settings', {
    extend: 'Ext.window.Window',

    uses: [
        'Ext.tree.Panel',
        'Ext.tree.View',
        'Ext.form.field.Checkbox',
        'Ext.layout.container.Anchor',
        'Ext.layout.container.Border',

        'Ext.ux.desktop.Wallpaper',

        'WallpaperModel'
    ],

    layout: 'anchor',
    title: '系统设置',
    modal: true,
    width: 640,
    height: 480,
    border: false,

    initComponent: function () {
        var me = this;

        me.selected = me.desktop.getWallpaper();
        me.stretch = me.desktop.wallpaper.stretch;

        me.preview = Ext.create('widget.wallpaper');
        me.preview.setWallpaper(me.selected);
        me.tree = me.createTree();
        me.theme = me.createTheme();
        me.buttons = [
            { text: '确定', handler: me.onOK, scope: me },
            { text: '取消', handler: me.close, scope: me }
        ];
        //主题
		//Ext.themeName = theme = getQueryParam('theme') || defaultTheme;
        me.items = [
            {
                anchor: '0 -30',
                border: false,
                layout: 'border',
                items: [me.theme,
                    me.tree,
                    {
                        xtype: 'panel',
                        title: 'Preview',
                        region: 'center',
                        layout: 'fit',
                        items: [ me.preview ]
                    }
                ]
            }
          /*  {
                xtype: 'checkbox',
                boxLabel: 'Stretch to fit',
                checked: me.stretch,
                listeners: {
                    change: function (comp) {
                        me.stretch = comp.checked;
                    }
                }
            },{
                    xtype: 'combo',
                    rtl: false,
                    width: 170,
                    labelWidth: 45,
                    fieldLabel: '主题',
                    displayField: 'name',
                    valueField: 'value',
                    labelStyle: 'cursor:move;',
                   // margin: '0 5 0 0',
                    store: Ext.create('Ext.data.Store', {
                        fields: ['value', 'name'],
                        data : [
                            { value: 'access', name: '黑色' },
                            { value: 'classic', name: '淡蓝色' },
                            { value: 'gray', name: '灰色' },
                            { value: 'neptune', name: '深蓝色' }
                        ]
                    }),
                    value: 'neptune',
                    listeners: {
                        select: function(combo) {
                        	var link = Ext.getDom('theme');
							var href = link.getAttribute('href');
							var lastg = href.lastIndexOf('/')+1;
							var oldcss = href.substring(lastg);
							var newcss = combo.getValue();
							if(oldcss.indexOf(newcss)==-1){
								if(newcss=='classic'){
									href = href.substring(0,lastg)+"ext-all.css";
									link.setAttribute('href',href);
								}else{
									href = href.substring(0,lastg)+"ext-all-"+newcss+"-debug.css";
									link.setAttribute('href',href);
								}							
							}
                          
                        }
                    }
                }*/
        ];

        me.callParent();
    },
	/*getQueryParam : function(name, queryString) {
        var match = RegExp(name + '=([^&]*)').exec(queryString || location.search);
        return match && decodeURIComponent(match[1]);
    },*/
    createTree : function() {
        var me = this;

        function child (img) {
            return { img: img, text: me.getTextOfWallpaper(img), iconCls: '', leaf: true };
        }

        var tree = new Ext.tree.Panel({
            title: '桌面背景设置',
            rootVisible: false,
            lines: false,
            autoScroll: true,
            width: 150,
            region: 'west',
            split: true,
            minWidth: 100,
            listeners: {
                afterrender: { fn: this.setInitialSelection, delay: 100 },
                select: this.onSelect,
                scope: this
            },
            store: new Ext.data.TreeStore({
                model: 'WallpaperModel',
                root: {
                    text:'Wallpaper',
                    expanded: true,
                    children:[
                        { text: "None", iconCls: '', leaf: true },
                        child('Blue-Sencha.jpg'),
                        child('Dark-Sencha.jpg'),
                        child('Wood-Sencha.jpg'),
                        child('blue.jpg'),
                        child('desk.jpg'),
                        child('desktop.jpg'),
                        child('desktop2.jpg'),
                        child('sky.jpg')
                    ]
                }
            })
        });

        return tree;
    },
    createTheme : function(){
    	var theme=new Ext.panel.Panel({
    		title:'主题设置',
    		height:60,
    		region: 'north',
    		items:[{
                    xtype: 'combo',
                    rtl: false,
                    width: 170,
                    labelWidth: 45,
                    fieldLabel: '主题',
                    displayField: 'name',
                    valueField: 'value',
                    labelStyle: 'cursor:move;',
                    margin: '0 5 0 0',
                    store: Ext.create('Ext.data.Store', {
                        fields: ['value', 'name'],
                        data : [
                            { value: 'access', name: '黑色' },
                            { value: 'classic', name: '经典' },
                            { value: 'gray', name: '灰色' },
                            { value: 'neptune', name: '自然' }
                        ]
                    }),
                    value: 'neptune',
                    listeners: {
                        select: function(combo) {
                        	var link = Ext.getDom('theme');
							var href = link.getAttribute('href');
							var lastg = href.lastIndexOf('/')+1;
							var oldcss = href.substring(lastg);
							var newcss = combo.getValue();
							if(oldcss.indexOf(newcss)==-1){
								if(newcss=='classic'){									
									href = href.substring(0,lastg)+"ext-all.css";
									link.setAttribute('href',href);
								}else{									
									href = href.substring(0,lastg)+"ext-all-"+newcss+"-debug.css";
									link.setAttribute('href',href);
								}							
							}
                          
                        }
                    }
                }]
    	});
    	return theme;
    },
    getTextOfWallpaper: function (path) {
        var text = path, slash = path.lastIndexOf('/');
        if (slash >= 0) {
            text = text.substring(slash+1);
        }
        var dot = text.lastIndexOf('.');
        text = Ext.String.capitalize(text.substring(0, dot));
        text = text.replace(/[-]/g, ' ');
        return text;
    },

    onOK: function () {
        var me = this;
        if (me.selected) {
            me.desktop.setWallpaper(me.selected, me.stretch);
        }
        me.destroy();
    },

    onSelect: function (tree, record) {
        var me = this;

        if (record.data.img) {
            me.selected = 'js/desktop/wallpapers/' + record.data.img;
        } else {
            me.selected = Ext.BLANK_IMAGE_URL;
        }

        me.preview.setWallpaper(me.selected);
    },

    setInitialSelection: function () {
        var s = this.desktop.getWallpaper();
        if (s) {
            var path = 'js/desktop/Wallpaper/' + this.getTextOfWallpaper(s);
            this.tree.selectPath(path, 'text');
        }
    }
});
