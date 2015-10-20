/*!
 * Ext JS Library 4.0
 * Copyright(c) 2006-2011 Sencha Inc.
 * licensing@sencha.com
 * http://www.sencha.com/license
 */

/**
 * @class Ext.ux.desktop.Desktop
 * @extends Ext.panel.Panel
 * <p>This class manages the wallpaper, shortcuts and taskbar.</p>
 */
Ext.define('Ext.ux.desktop.Desktop', {
    extend: 'Ext.panel.Panel',

    alias: 'widget.desktop',

    uses: [
        'Ext.util.MixedCollection',
        'Ext.menu.Menu',
        'Ext.view.View', // dataview
        'Ext.window.Window',
		'Ext.button.Button',
        'Ext.ux.desktop.TaskBar',
        'Ext.ux.desktop.Wallpaper'
    ],

    activeWindowCls: 'ux-desktop-active-win',
    inactiveWindowCls: 'ux-desktop-inactive-win',
    lastActiveWindow: null,

    border: false,
    html: '&#160;',
    layout: 'fit',

    xTickSize: 1,
    yTickSize: 1,

    app: null,

    /**
     * @cfg {Array|Store} shortcuts
     * The items to add to the DataView. This can be a {@link Ext.data.Store Store} or a
     * simple array. Items should minimally provide the fields in the
     * {@link Ext.ux.desktop.ShorcutModel ShortcutModel}.
     */
    shortcuts: null,

    /**
     * @cfg {String} shortcutItemSelector
     * This property is passed to the DataView for the desktop to select shortcut items.
     * If the {@link #shortcutTpl} is modified, this will probably need to be modified as
     * well.
     */
    shortcutItemSelector: 'div.ux-desktop-shortcut',

    /**
     * @cfg {String} shortcutTpl桌面图标和背景图配置模板
     * This XTemplate is used to render items in the DataView. If this is changed, the
     * {@link shortcutItemSelect} will probably also need to changed.
     */
    shortcutTpl: [
        '<tpl for=".">',
            '<div class="ux-desktop-shortcut" id="{name}-shortcut">',
                '<div class="ux-desktop-shortcut-icon {iconCls}" title="{name}">',
                    '<img src="',Ext.BLANK_IMAGE_URL,'" title="{name}">',
                '</div>',
                '<span class="ux-desktop-shortcut-text" title="{name}">{name}</span>',
            '</div>',
        '</tpl>',
        '<div class="x-clear"></div>'
    ],

    /**
     * @cfg {Object} taskbarConfig任务栏配置对象
     * The config object for the TaskBar.
     */
    taskbarConfig:null,
    //任务栏右键菜单
    windowMenu: null,
	
    initComponent: function () {
        var me = this;
        //窗口最大化，最小化，还原菜单配置项
        me.windowMenu = new Ext.menu.Menu(me.createWindowMenu());
        //任务栏
        me.bbar = me.taskbar = new Ext.ux.desktop.TaskBar(me.taskbarConfig);
        //添加任务显示桌面按钮
        me.showDesktopButton=Ext.create('Ext.button.Button',{
        	//text:'显示桌面',
        	iconCls:'icon-grid',
        	scope:me,
        	handler:me.onMinimizeWindow
        });      
        me.taskbar.add(me.showDesktopButton);
        //任务栏右键菜单
        me.taskbar.windowMenu = me.windowMenu;
        //桌面窗口的集合
        me.windows = new Ext.util.MixedCollection();
        //右键菜单
        me.contextMenu = new Ext.menu.Menu(me.createDesktopMenu());
        //桌面所有配置项
        me.items = [
            { xtype: 'wallpaper', id: me.id+'_wallpaper' },
            me.createDataView()
        ];

        me.callParent();
        //DataView
        me.shortcutsView = me.items.getAt(1);
        me.shortcutsView.on('itemclick', me.onShortcutItemClick, me);
        me.shortcutsView.on('render', me.onRenderShortcut, me);
        //wallpaper主题
        var wallpaper = me.wallpaper;
        me.wallpaper = me.items.getAt(0);
        if (wallpaper) {
            me.setWallpaper(wallpaper, me.wallpaperStretch);
        }
    },
    /**
     * 组件渲染之后执行的函数
     */
    afterRender: function () {
        var me = this;
        me.callParent();
        me.el.on('contextmenu', me.onDesktopMenu, me);
        //追加处理
        Ext.Function.defer(me.initShortcut, 200);   
        Ext.Function.defer(me.initShortcut, 500);
    },
	/**
	 * shortcuts 自动换行
	 */
    initShortcut: function () {
        var btnHeight = 64;
        var btnWidth = 64;
        var btnPadding = 30;
        var bottom;
        var numberOfItems = 0;
        var taskBarHeight = Ext.query(".ux-taskbar")[0].clientHeight + 40;
        var bodyHeight = Ext.getBody().getHeight() - taskBarHeight;
        var items = Ext.query(".ux-desktop-shortcut");
        var col = { index: 1, x: btnPadding };
        var row = { index: 1, y: btnPadding };
        for (var i = 0, len = items.length; i < len; i++) {
               numberOfItems += 1;
                bottom = row.y + btnHeight;
                if (((bodyHeight < bottom) ? true : false) && bottom > (btnHeight + btnPadding)) {
                    numberOfItems = 0;
                    col = { index: col.index++, x: col.x + btnWidth + btnPadding };
                    row = { index: 1, y: btnPadding };
                }
                Ext.fly(items[i]).setXY([col.x, row.y]);
                row.index++;
                row.y = row.y + btnHeight + btnPadding;


        };
    },   
        
    /**
     * onRenderShortcut 桌面拖动 
     */
    onRenderShortcut: function (v) {
        var me = this;
        me.shortcutsView.dragZone = new Ext.dd.DragZone(v.getEl(), {
            getDragData: function (e) {
                var sourceEl = e.getTarget(v.itemSelector, 10);
                if (sourceEl) {
                    var d = sourceEl.cloneNode(true);
                    d.style.left = 0;
                    d.style.top = 0;
                    d.id = Ext.id();
                    return v.dragData = {
                        sourceEl: sourceEl,
                        ddel: d,
                        sourceStore: v.store,
                        draggedRecord: v.getRecord(sourceEl)
                    }
                }
            },
            getRepairXY: function () {
                return this.dragData.repairXY;
            },
            onMouseUp: function (e) {
                var currDom = Ext.fly(this.dragData.sourceEl);
                var oldXY = currDom.getXY();
                var newXY = e.getXY();
                var width = currDom.getWidth();
                var height = currDom.getHeight();
                if (Math.abs(oldXY[0] - newXY[0]) > width || Math.abs(oldXY[1] - newXY[1]) > height) {
                    var mymaxx = me.getHeight() - me.taskbar.getHeight() - height - 10;
                    var mymaxy = me.getWidth() - width;
                        if (newXY[1] > mymaxx) { newXY[1] = mymaxx; }
                        if (newXY[1] < 1) { newXY[1] = 0; }
                        if (newXY[0] < 1) { newXY[0] = 0; }
                        if (newXY[0] > mymaxy) { newXY[0] = mymaxy; }
                    currDom.setXY(newXY);
                }

            }
        });
    },
    //------------------------------------------------------
    // Overrideable configuration creation methods
    /**
     * 创建dataview，主要配置项store,tpl模板,itemselect选择事件
     */
    createDataView: function () {
        var me = this;
        return {
            xtype: 'dataview',
            overItemCls: 'x-view-over',
            trackOver: true,
            itemSelector: me.shortcutItemSelector,
            store: me.shortcuts,
            style: {
                position: 'absolute'
            },
            x: 0, y: 0,
            tpl: new Ext.XTemplate(me.shortcutTpl)
            
        };
    },
    
	/**
	 * 创建桌面右键菜单
	 * @return {}
	 */
    createDesktopMenu: function () {
        var me = this, ret = {
            items: me.contextMenuItems || []
        };

        if (ret.items.length) {
            ret.items.push('-');
        }

        ret.items.push(
        		{ text: '刷新浏览器', handler: me.reLoadPage, scope: me,minWindow:0 },
        		{ text: '重排图标', handler: me.initShortcut, scope: me, minWindows: 0 },
        		{ text: '显示桌面', handler: me.onMinimizeWindow, scope: me ,minWindows: 1 },  
        		{ text: '关闭所有窗口', handler: me.onWindowMenuAllClose, scope: me,minWindows: 1  },
                { text: '平铺窗口', handler: me.tileWindows, scope: me, minWindows: 1 },
                { text: '重叠窗口', handler: me.cascadeWindows, scope: me, minWindows: 1 })

        return ret;
    },
    /**
     * 刷新浏览器
     */
    reLoadPage : function(){
    	location.reload();
    },
    /**
     * 最小化所有窗口
     */
    onMinimizeWindow : function (){    	
    	var me=this;
    	var win=me.windows;
    	if(Ext.isEmpty(win)){
    		return;
    	}
    	var count=win.getCount();
    	var i=true;
    	console.log("打开窗口数量： "+count);    
    	if(i)
    	{
	    	me.windows.each(function(win){
	    		win.hide();
	    	});	    	
	    	i=false;
    	}else if(i==false){
    		me.windows.each(function(win){
	    		win.show();
	    	});
	    	i=true;
    	}
    },
    
	/**
	 * 桌面任务栏右键菜单配置
	 * @return {}
	 */
    createWindowMenu: function () {
        var me = this;
        return {
            defaultAlign: 'br-tr',
            items: [
                { text: '还原', handler: me.onWindowMenuRestore, scope: me },
                { text: '最小化', handler: me.onWindowMenuMinimize, scope: me },
                { text: '最大化', handler: me.onWindowMenuMaximize, scope: me },
                '-',
                { text: '关闭', handler: me.onWindowMenuClose, scope: me }
                
            ],
            listeners: {
                beforeshow: me.onWindowMenuBeforeShow,
                hide: me.onWindowMenuHide,
                scope: me
            }
        };
    },
    /**
     * 关闭所有打开的窗口
     */
    onWindowMenuAllClose:function(){
    	var me=this;
    	var win=me.windows;
    	if(Ext.isEmpty(win)){
    		return;
    	}
    	var count=win.getCount();
    	console.log("打开窗口数量： "+count);    	
    	me.windows.each(function(win){
    		win.close();
    	});
    },

    //------------------------------------------------------
    // Event handler methods
    /**
     * 
     */
    onDesktopMenu: function (e) {
        var me = this, menu = me.contextMenu;
        e.stopEvent();
        if (!menu.rendered) {
            menu.on('beforeshow', me.onDesktopMenuBeforeShow, me);
        }
        menu.showAt(e.getXY());
        menu.doConstrain();
    },

    onDesktopMenuBeforeShow: function (menu) {
        var me = this, count = me.windows.getCount();

        menu.items.each(function (item) {
            var min = item.minWindows || 0;
            item.setDisabled(count < min);
        });
    },
    /**
     * dataview item 项单击事件处理函数
     */
    onShortcutItemClick: function (dataView, record) {
        var me = this, module = me.app.getModule(record.data.module),
            win = module && module.createWindow();

        if (win) {
            me.restoreWindow(win);
        }
    },

    onWindowClose: function(win) {
        var me = this;
        me.windows.remove(win);
        me.taskbar.removeTaskButton(win.taskButton);
        me.updateActiveWindow();
    },

    //------------------------------------------------------
    // Window context menu handlers

    onWindowMenuBeforeShow: function (menu) {
        var items = menu.items.items, win = menu.theWin;
        items[0].setDisabled(win.maximized !== true && win.hidden !== true); // Restore
        items[1].setDisabled(win.minimized === true); // Minimize
        items[2].setDisabled(win.maximized === true || win.hidden === true); // Maximize
    },

    onWindowMenuClose: function () {
        var me = this, win = me.windowMenu.theWin;

        win.close();
    },

    onWindowMenuHide: function (menu) {
        Ext.defer(function() {
            menu.theWin = null;
        }, 1);
    },

    onWindowMenuMaximize: function () {
        var me = this, win = me.windowMenu.theWin;

        win.maximize();
        win.toFront();
    },

    onWindowMenuMinimize: function () {
        var me = this, win = me.windowMenu.theWin;

        win.minimize();
    },

    onWindowMenuRestore: function () {
        var me = this, win = me.windowMenu.theWin;

        me.restoreWindow(win);
    },

    //------------------------------------------------------
    // Dynamic (re)configuration methods

    getWallpaper: function () {
        return this.wallpaper.wallpaper;
    },

    setTickSize: function(xTickSize, yTickSize) {
        var me = this,
            xt = me.xTickSize = xTickSize,
            yt = me.yTickSize = (arguments.length > 1) ? yTickSize : xt;

        me.windows.each(function(win) {
            var dd = win.dd, resizer = win.resizer;
            dd.xTickSize = xt;
            dd.yTickSize = yt;
            resizer.widthIncrement = xt;
            resizer.heightIncrement = yt;
        });
    },

    setWallpaper: function (wallpaper, stretch) {
        this.wallpaper.setWallpaper(wallpaper, stretch);
        return this;
    },

    //------------------------------------------------------
    // Window management methods

    cascadeWindows: function() {
        var x = 0, y = 0,
            zmgr = this.getDesktopZIndexManager();

        zmgr.eachBottomUp(function(win) {
            if (win.isWindow && win.isVisible() && !win.maximized) {
                win.setPosition(x, y);
                x += 20;
                y += 20;
            }
        });
    },
	/**
	 * 创建窗口方法
	 * @param {} config
	 * @param {} cls
	 * @return {}
	 */
    createWindow: function(config, cls) {
        var me = this, win, cfg = Ext.applyIf(config || {}, {
                stateful: false,
                isWindow: true,
                constrainHeader: true,
                minimizable: true,
                maximizable: true
            });

        cls = cls || Ext.window.Window;
        win = me.add(new cls(cfg));

        me.windows.add(win);

        win.taskButton = me.taskbar.addTaskButton(win);
        win.animateTarget = win.taskButton.el;
		
        win.on({
            activate: me.updateActiveWindow,
            beforeshow: me.updateActiveWindow,
            deactivate: me.updateActiveWindow,
            minimize: me.minimizeWindow,
            destroy: me.onWindowClose,
            show:me.doWindowLayout,
            scope: me
        });

        win.on({
            boxready: function () {
                win.dd.xTickSize = me.xTickSize;
                win.dd.yTickSize = me.yTickSize;

                if (win.resizer) {
                    win.resizer.widthIncrement = me.xTickSize;
                    win.resizer.heightIncrement = me.yTickSize;
                }
            },
            single: true
        });

        // replace normal window close w/fadeOut animation:
        win.doClose = function ()  {
            win.doClose = Ext.emptyFn; // dblclick can call again...
            win.el.disableShadow();
            win.el.fadeOut({
                listeners: {
                    afteranimate: function () {
                        win.destroy();
                    }
                }
            });
        };

        return win;
    },
    doWindowLayout : function(){
    	this.doLayout();
    },
	/**
	 * 获取活动窗口
	 * @return {}
	 */
    getActiveWindow: function () {
        var win = null,
            zmgr = this.getDesktopZIndexManager();

        if (zmgr) {
            // We cannot rely on activate/deactive because that fires against non-Window
            // components in the stack.

            zmgr.eachTopDown(function (comp) {
                if (comp.isWindow && !comp.hidden) {
                    win = comp;
                    return false;
                }
                return true;
            });
        }

        return win;
    },
	
    getDesktopZIndexManager: function () {
        var windows = this.windows;
        // TODO - there has to be a better way to get this...
        return (windows.getCount() && windows.getAt(0).zIndexManager) || null;
    },

    getWindow: function(id) {
        return this.windows.get(id);
    },
	/**
	 * 最小化
	 * @param {} win
	 */
    minimizeWindow: function(win) {
        win.minimized = true;
        win.hide();
    },

    restoreWindow: function (win) {
        if (win.isVisible()) {
            win.restore();
            win.toFront();
        } else {
            win.show();
        }
        return win;
    },

    tileWindows: function() {
        var me = this, availWidth = me.body.getWidth(true);
        var x = me.xTickSize, y = me.yTickSize, nextY = y;

        me.windows.each(function(win) {
            if (win.isVisible() && !win.maximized) {
                var w = win.el.getWidth();

                // Wrap to next row if we are not at the line start and this Window will
                // go off the end
                if (x > me.xTickSize && x + w > availWidth) {
                    x = me.xTickSize;
                    y = nextY;
                }

                win.setPosition(x, y);
                x += w + me.xTickSize;
                nextY = Math.max(nextY, y + win.el.getHeight() + me.yTickSize);
            }
        });
    },

    updateActiveWindow: function () {
        var me = this, activeWindow = me.getActiveWindow(), last = me.lastActiveWindow;
        if (activeWindow === last) {
            return;
        }

        if (last) {
            if (last.el.dom) {
                last.addCls(me.inactiveWindowCls);
                last.removeCls(me.activeWindowCls);
            }
            last.active = false;
        }

        me.lastActiveWindow = activeWindow;

        if (activeWindow) {
            activeWindow.addCls(me.activeWindowCls);
            activeWindow.removeCls(me.inactiveWindowCls);
            activeWindow.minimized = false;
            activeWindow.active = true;
        }

        me.taskbar.setActiveButton(activeWindow && activeWindow.taskButton);
    }
});
