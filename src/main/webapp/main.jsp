<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html>
    <head>
        <title>首页</title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">       
 	 <!-- <script type="text/javascript">%{out.print(com.he.utils.PageUtils.PrintInfo());}%</script> -->
     <!-- <link rel="stylesheet" type="text/css" href="js/desktop/resources/ext-theme-classic/ext-theme-classic-all.css" /> -->
    <!-- <link rel="stylesheet" type="text/css" href="js/desktop/resources/css/ext-all.css" /> -->
     <link rel="stylesheet" type="text/css" href="js/desktop/css/desktop.css" />
	 <link rel="stylesheet" type="text/css" href="js/desktop/css/main.css" />
	<!-- %{out.print(com.he.utils.GenerationJSPath.ExportJsPath(com.he.utils.GenerationJSPath.DESKTOP_JS_PATH));}% -->
	 
	<script type="text/javascript">	
		var sysMenus = eval('${menus}');
	</script>
	<!-- <script type="text/javascript" src="js/desktop/ext/ext-all.js"></script> -->
	<script src="<%=path%>/js/jquery-1.6.4.min.js"></script>
	<script type="text/javascript" src="js/desktop/ext/include-ext.js"></script>
    <!-- <script type="text/javascript" src="js/desktop/js/ext-theme-neptune.js"></script> -->
    <script type="text/javascript" src="js/desktop/ext/options-toolbar.js"></script>
    <script type="text/javascript" src="js/desktop/ext/ext-lang-zh_CN.js"></script>
	<script type="text/javascript" src="js/desktop/ext/examples.js"></script>
	<script type="text/javascript" src="js/desktop/ux/IFrame.js"></script>
    <script type="text/javascript" src="js/desktop/js/ShortcutModel.js"></script>
    <script type="text/javascript" src="js/desktop/js/StartMenu.js"></script>
    <script type="text/javascript" src="js/desktop/js/TaskBar.js"></script>
    <script type="text/javascript" src="js/desktop/js/Desktop.js"></script>
    <script type="text/javascript" src="js/desktop/js/App.js"></script>
    
   
    
    <script type="text/javascript" src="js/desktop/plugins/flash/swfobject.js"></script>
    <script type="text/javascript" src="js/desktop/js/Module.js"></script>
   
    <script type="text/javascript" src="js/desktop/js/Video.js"></script>
    <script type="text/javascript" src="js/desktop/js/Audio.js"></script>
    <script type="text/javascript" src="js/desktop/js/Wallpaper.js"></script>   
    <script type="text/javascript" src="js/desktop/ux/TabCloseMenu.js"></script>
    <script type="text/javascript" src="js/desktop/AccordionWindow.js"></script>    
    <script type="text/javascript" src="js/desktop/GridWindow.js"></script>
    
    <script type="text/javascript" src="js/desktop/app/comm/Paging.js"></script>
    <script type="text/javascript" src="js/desktop/app/comm/GridJsonStore.js"></script>
    <script type="text/javascript" src="js/desktop/app/comm/Msg.js"></script>
    <script type="text/javascript" src="js/desktop/app/comm/CommButton.js"></script>
    <script type="text/javascript" src="js/desktop/app/comm/CenterGrid.js"></script>
    <script type="text/javascript" src="js/desktop/app/comm/SearchPanel.js"></script>
    <script type="text/javascript" src="js/desktop/app/comm/CommWindow.js"></script>
    <script type="text/javascript" src="js/desktop/app/comm/CenterTabPanel.js"></script>
    <script type="text/javascript" src="js/desktop/app/comm/WestTreePanel.js"></script>
    <script type="text/javascript" src="js/desktop/app/comm/WestPanel.js"></script>
    <script type="text/javascript" src="js/desktop/app/comm/RowExpanders.js"></script>
    
    <script type="text/javascript" src="js/desktop/app/comm/CRUDPanel.js"></script>
    <script type="text/javascript" src="js/desktop/app/comm/CRUDRowEditPanel.js"></script>
     
    <script type="text/javascript" src="js/desktop/app/student/Student.js"></script>
    <script type="text/javascript" src="js/desktop/app/eims/Eims.js"></script>
    <script type="text/javascript" src="js/desktop/app/eims/sys/Menu.js"></script>
    <script type="text/javascript" src="js/desktop/app/user/User.js"></script>
    <script type="text/javascript" src="js/desktop/app/filesys/FileSys.js"></script>
    <script type="text/javascript" src="js/desktop/app/sys/Role.js"></script>
    <script type="text/javascript" src="js/desktop/app/otherApp/DouBan.js"></script>
    <script type="text/javascript" src="js/desktop/Notepad.js"></script>
    <script type="text/javascript" src="js/desktop/SystemStatus.js"></script>
    <script type="text/javascript" src="js/desktop/TabWindow.js"></script>
    <script type="text/javascript" src="js/desktop/VideoWindow.js"></script>
    <script type="text/javascript" src="js/desktop/AudioWindow.js"></script>
    <script type="text/javascript" src="js/desktop/SWFVideo.js"></script>
    <script type="text/javascript" src="js/desktop/BogusModule.js"></script>
    <script type="text/javascript" src="js/desktop/BogusMenuModule.js"></script>
    <script type="text/javascript" src="js/desktop/WallpaperModel.js"></script>  
    <script type="text/javascript" src="js/desktop/Settings.js"></script>            
    <script type="text/javascript" src="js/desktop/App.js"></script>
   

    <script type="text/javascript">
    	Ext.Loader.setConfig({enabled:true});
        Ext.Loader.setPath({           
            'Ext.ux.desktop': 'js/desktop/js' , 
            'desktop':'js/desktop/app',
           	'App':''
         
            
        });
		
        Ext.require('App');
       // Ext.require('app.comm.');
      /*   Ext.require('app.user.User');
        Ext.require('app.filesys.FileSys'); 
        Ext.require('app.eims.Eims');
        Ext.require('app.student.Student');*/
        var appPath='<%=path%>';
        var app;
        //var eimsMenuData;
        //var swfurl="js/desktop/video/99.swf";
        Ext.onReady(function () {
        	//Ext.ns('Ext.ux.desktop','app','app.comm','app.user','app.filesys','app.eims.Eims','app.student');
            app = new App();
           //var sysMenus = eval('${menus}');
            //alert(sysMenus);
        });
    </script>
    </head>
    <body>
    
       <a href="http://www.sencha.com" target="_blank" alt="Powered by Ext JS"
       id="poweredby"><div></div>
     	</a>
       
       

    </body>
</html>