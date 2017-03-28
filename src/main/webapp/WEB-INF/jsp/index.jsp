<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>

<%@ include file="/WEB-INF/jsp/common/meta.jsp" %>
<title>ECRM-首页</title>
<meta name="Keywords" content="ECRM，四川亿立方科技有限公司"/>
<meta name="Description" content="ECRM,CRM，ERP，GSP，四川亿立方，亿立方，亿立方资讯，四川亿立方科技倾力打造的客户关系管理系统"/> 


<%@ include file="/WEB-INF/jsp/common/link.jsp" %>
<!-- init -->
<script type="text/javascript">
$(function() {
    BJUI.init({
        JSPATH       : 'BJUI/',         //[可选]框架路径
        PLUGINPATH   : 'BJUI/plugins/', //[可选]插件路径
        loginInfo    : {url:'login_timeout.html', title:'登录', width:400, height:200}, // 会话超时后弹出登录对话框
        statusCode   : {ok:200, error:300, timeout:301}, //[可选]
        ajaxTimeout  : 50000, //[可选]全局Ajax请求超时时间(毫秒)
        pageInfo     : {total:'total', pageCurrent:'pageCurrent', pageSize:'pageSize', orderField:'orderField', orderDirection:'orderDirection'}, //[可选]分页参数
        alertMsg     : {displayPosition:'topcenter', displayMode:'slide', alertTimeout:3000}, //[可选]信息提示的显示位置，显隐方式，及[info/correct]方式时自动关闭延时(毫秒)
        keys         : {statusCode:'statusCode', message:'message'}, //[可选]
        ui           : {
                         windowWidth      : 0,    //框架可视宽度，0=100%宽，> 600为则居中显示
                         showSlidebar     : true, //[可选]左侧导航栏锁定/隐藏
                         clientPaging     : true, //[可选]是否在客户端响应分页及排序参数
                         overwriteHomeTab : false //[可选]当打开一个未定义id的navtab时，是否可以覆盖主navtab(我的主页)
                       },
        debug        : true,    // [可选]调试模式 [true|false，默认false]
        theme        : 'sky' // 若有Cookie['bjui_theme'],优先选择Cookie['bjui_theme']。皮肤[五种皮肤:default, orange, purple, blue, red, green]
    })
    
    // main - menu
    /*  */
    $('#bjui-accordionmenu')
        .collapse()
        .on('hidden.bs.collapse', function(e) {
            $(this).find('> .panel > .panel-heading').each(function() {
                var $heading = $(this), $a = $heading.find('> h4 > a')
                
                if ($a.hasClass('collapsed')) $heading.removeClass('active')
            })
        })
        .on('shown.bs.collapse', function (e) {
            $(this).find('> .panel > .panel-heading').each(function() {
                var $heading = $(this), $a = $heading.find('> h4 > a')
                
                if (!$a.hasClass('collapsed')) $heading.addClass('active')
            })
        })
       
    
    $(document).on('click', 'ul.menu-items > li > a', function(e) {
        var $a = $(this), $li = $a.parent(), options = $a.data('options').toObj()
        var onClose = function() {
            $li.removeClass('active')
        }
        var onSwitch = function() {
            $('#bjui-accordionmenu').find('ul.menu-items > li').removeClass('switch')
            $li.addClass('switch')
        }
        
        $li.addClass('active')
        if (options) {
            options.url      = $a.attr('href')
            options.onClose  = onClose
            options.onSwitch = onSwitch
            if (!options.title) options.title = $a.text()
            
            if (!options.target)
                $a.navtab(options)
            else
                $a.dialog(options)
        }
        
        e.preventDefault()
    })
    
    //时钟
    var today = new Date(), time = today.getTime()
    $('#bjui-date').html(today.formatDate('yyyy/MM/dd'))
    setInterval(function() {
        today = new Date(today.setSeconds(today.getSeconds() + 1))
        $('#bjui-clock').html(today.formatDate('HH:mm:ss'))
    }, 1000)
})

//菜单-事件
function MainMenuClick(event, treeId, treeNode) {
    event.preventDefault()
    
    if (treeNode.isParent) {
        var zTree = $.fn.zTree.getZTreeObj(treeId)
        
        zTree.expandNode(treeNode, !treeNode.open, false, true, true)
        return
    }
    
    if (treeNode.target && treeNode.target == 'dialog')
        $(event.target).dialog({id:treeNode.tabid, url:treeNode.url, title:treeNode.name})
    else
        $(event.target).navtab({id:treeNode.tabid, url:treeNode.url, title:treeNode.name, fresh:treeNode.fresh, external:treeNode.external})
}
</script>
<!-- for doc begin -->
<link type="text/css" rel="stylesheet" href="${ctx}/js/syntaxhighlighter-2.1.382/styles/shCore.css"/>
<link type="text/css" rel="stylesheet" href="${ctx}/js/syntaxhighlighter-2.1.382/styles/shThemeEclipse.css"/>
<script type="text/javascript" src="${ctx}/js/syntaxhighlighter-2.1.382/scripts/brush.js"></script>
<link href="${ctx}/doc/doc.css" rel="stylesheet">
<script type="text/javascript">
$(function(){
    SyntaxHighlighter.config.clipboardSwf = ${ctx}+'/js/syntaxhighlighter-2.1.382/scripts/clipboard.swf'
    $(document).on(BJUI.eventType.initUI, function(e) {
        SyntaxHighlighter.highlight();
    })
})
</script>
<!-- for doc end -->
</head>
<body>
    <!--[if lte IE 7]>
        <div id="errorie"><div>您还在使用老掉牙的IE，正常使用系统前请升级您的浏览器到 IE8以上版本 <a target="_blank" href="http://windows.microsoft.com/zh-cn/internet-explorer/ie-8-worldwide-languages">点击升级</a>&nbsp;&nbsp;强烈建议您更改换浏览器：<a href="http://down.tech.sina.com.cn/content/40975.html" target="_blank">谷歌 Chrome</a></div></div>
    <![endif]-->
    <div id="bjui-window">
    <header id="bjui-header">
        <div class="bjui-navbar-header">
            <button type="button" class="bjui-navbar-toggle btn-default" data-toggle="collapse" data-target="#bjui-navbar-collapse">
                <i class="fa fa-bars"></i>
            </button>
               <a class="bjui-navbar-logo" href="#"><img src="${ctx}/images/logo.png"></a>
        </div>
        <nav id="bjui-navbar-collapse">
            <ul class="bjui-navbar-right">
                <li class="datetime"><div><span id="bjui-date"></span> <span id="bjui-clock"></span></div></li>
                <li><a>欢迎你，<%=request.getSession(false).getAttribute("fullname")+","+request.getSession(false).getAttribute("username") %></a></li>
                <li><a href="#">消息 <span class="badge">4</span></a></li>
                <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">我的账户 <span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="changepwd.html" data-toggle="dialog" data-id="changepwd_page" data-mask="true" data-width="400" data-height="260">&nbsp;<span class="glyphicon glyphicon-lock"></span> 修改密码&nbsp;</a></li>
                        <li><a href="#">&nbsp;<span class="glyphicon glyphicon-user"></span> 我的资料</a></li>
                        <li class="divider"></li>
                        <li><a href="<c:url value='logout'/>" class="red">&nbsp;<span class="glyphicon glyphicon-off"></span> 注销登陆</a></li>
                        <li>
                        <form action="<c:url value='/logout'/>" method="post">
								<security:csrfInput />
								<input type="submit" value="注销"/>
							</form>
						</li>
                    </ul>
                </li>
                <!-- -->
                <li><a href="${ctx}/index_tree.html?${_csrf.parameterName}=${_csrf.token}" title="切换为列表导航(窄版)" style="background-color:#ff7b61;">列表导航栏(窄版)</a></li>
                
                
                <li class="dropdown"><a href="#" class="dropdown-toggle theme blue" data-toggle="dropdown" title="切换皮肤"><i class="fa fa-tree"></i></a>
                    <ul class="dropdown-menu" role="menu" id="bjui-themes">
                        <li><a href="javascript:;" class="theme_default" data-toggle="theme" data-theme="default">&nbsp;<i class="fa fa-tree"></i> 黑白分明&nbsp;&nbsp;</a></li>
                        <li><a href="javascript:;" class="theme_orange" data-toggle="theme" data-theme="orange">&nbsp;<i class="fa fa-tree"></i> 橘子红了</a></li>
                        <li><a href="javascript:;" class="theme_purple" data-toggle="theme" data-theme="purple">&nbsp;<i class="fa fa-tree"></i> 紫罗兰</a></li>
                        <li class="active"><a href="javascript:;" class="theme_blue" data-toggle="theme" data-theme="blue">&nbsp;<i class="fa fa-tree"></i> 天空蓝</a></li>
                        <li><a href="javascript:;" class="theme_green" data-toggle="theme" data-theme="green">&nbsp;<i class="fa fa-tree"></i> 绿草如茵</a></li>
                    </ul>
                </li>
            </ul>
        </nav>
        <!--  
        <div id="bjui-hnav">
            <button type="button" class="btn-default bjui-hnav-more-left" title="导航菜单左移"><i class="fa fa-angle-double-left"></i></button>
            <div id="bjui-hnav-navbar-box">
                <ul id="bjui-hnav-navbar">
                  
                    <li class="active">
                    <a href="javascript:;" data-toggle="slidebar"> 表格</a>
                        <div class="items" data-noinit="true">
                            <ul id="bjui-hnav-tree2" class="ztree ztree_main" data-toggle="ztree" data-on-click="MainMenuClick" data-expand-all="true" data-faicon="table">
                                <li data-id="2" data-pid="0" data-faicon="folder-open-o" data-faicon-close="folder-o">表格</li>
                                <li data-id="20" data-pid="2" data-url="table.html" data-tabid="table" data-faicon="table">普通表格</li>
                                <li data-id="21" data-pid="2" data-url="table-fixed.html" data-tabid="table-fixed" data-faicon="list-alt">固定表头表格</li>
                                <li data-id="22" data-pid="2" data-url="table-edit.html" data-tabid="table-edit" data-faicon="indent">可编辑表格</li>
                            </ul>
                        </div>
                    </li>
                 
                </ul>
            </div>
            <button type="button" class="btn-default bjui-hnav-more-right" title="导航菜单右移"><i class="fa fa-angle-double-right"></i></button>
        </div>
        -->
    </header>
    <div id="bjui-container">
        <div id="bjui-leftside">
            <div id="bjui-sidebar-s">
                <div class="collapse"></div>
            </div>
            <div id="bjui-sidebar">
                <div class="toggleCollapse"><h2><i class="fa fa-bars"></i> 导航栏 <i class="fa fa-bars"></i></h2><a href="javascript:;" class="lock"><i class="fa fa-lock"></i></a></div>
                <div class="panel-group panel-main" data-toggle="accordion" id="bjui-accordionmenu" data-heightbox="#bjui-sidebar" data-offsety="26">
                   
                   
			       <div class="panel panel-default">
                    <div class="panel-heading panelContent">
                        <h4 class="panel-title"><a data-toggle="collapse" data-parent="#bjui-accordionmenu" href="#bjui-collapse0" class="active"><i class="fa fa-table"></i>&nbsp;个人办公</a></h4>
                    </div>
                    <div id="bjui-collapse0" class="panel-collapse panelContent collapse in">
                        <div class="panel-body" >
                            <ul id="bjui-tree2" class="ztree ztree_main" data-toggle="ztree" data-on-click="MainMenuClick" data-expand-all="true">
				 														  							 <li data-id="12" data-pid="2" data-url="${ctx}/upload/uploadFile.jsp?${_csrf.parameterName}=${_csrf.token}" data-tabid="mygo/index">上传文件</li>
								     								  							      								    <li data-id="33" data-pid="2" data-url="index.php/home/mygos/index" data-tabid="mygos/index">员工去向</li>
								     								  							      								    <li data-id="8" data-pid="2" data-url="index.php/home/mytask/index" data-tabid="mytask/index">我的任务</li>
								     								  							      								    <li data-id="28" data-pid="2" data-url="index.php/home/task/index" data-tabid="task/index">指派任务</li>
								     								  							      								    <li data-id="34" data-pid="2" data-url="index.php/home/myreport/index" data-tabid="myreport/index">我的工作汇报</li>
								     								  							      								    <li data-id="52" data-pid="2" data-url="index.php/home/report/index" data-tabid="report/index">批注工作汇报</li>
								     								  							      								    <li data-id="44" data-pid="2" data-url="index.php/home/doc/index" data-tabid="doc/index">我的文档</li>
								     								  							      								    <li data-id="47" data-pid="2" data-url="index.php/home/zhishi/index" data-tabid="zhishi/index">知识管理</li>
								     								  							      								    								     <li data-id="40" data-pid="2" >通知公告</li>								  							      
								     								  							      								    								     
								     								  							      								<li data-id="41" data-pid="40" data-url="index.php/home/info/index" data-tabid="info/index">发通知</li>
<li data-id="42" data-pid="40" data-url="index.php/home/myinfo/index" data-tabid="myinfo/index">收通知</li>								    								     <li data-id="27" data-pid="2" >通讯录</li>								  							     
 <li data-id="29" data-pid="27" data-url="index.php/home/contact/index" data-tabid="contact/index">我的通讯录</li>
 <li data-id="30" data-pid="27" data-url="index.php/home/pcontact/index" data-tabid="pcontact/index">公共通讯录</li>
 <li data-id="46" data-pid="27" data-url="index.php/home/ccontact/index" data-tabid="ccontact/index">公司通讯录</li>                            
                           </ul>
                        </div>
                    </div>
                    <div class="panelFooter"><div class="panelFooterContent"></div></div>
                   </div>				 
                    
               
                   
                      <!--  -->
                <div class="panel panel-default">
                    <div class="panel-heading panelContent">
                        <h4 class="panel-title"><a data-toggle="collapse" data-parent="#bjui-accordionmenu" href="#bjui-collapse2" class="" ><i class="fa fa-table"></i>&nbsp;组织机构</a></h4>
                    </div>
                    <div id="bjui-collapse2" class="panel-collapse panelContent collapse">
                        <div class="panel-body">
                            <ul id="bjui-tree1" class="ztree ztree_main" data-toggle="ztree" data-on-click="MainMenuClick" data-expand-all="false">		
                            												  								  
								     						<li data-id="62" data-pid="1" data-url="${ctx}/organization/department_list.do?${_csrf.parameterName}=${_csrf.token}" data-tabid="organization/department/department_list">部门管理</li>
								     						<li data-id="32" data-pid="1" data-url="${ctx}/organization/position_list.do?${_csrf.parameterName}=${_csrf.token}" data-tabid="organization/position_list">职位管理</li>
								     						<li data-id="26" data-pid="1" data-url="${ctx}/organization/department_list.do" data-tabid="stock/index">用户管理</li>
								     								  							      								    <li data-id="63" data-pid="1" data-url="index.php/home/stock/baojing" data-tabid="stock/baojing">权限管理</li>
								     								  							                                  </ul>
                        </div>
                    </div>
                    <div class="panelFooter"><div class="panelFooterContent"></div></div>
                </div>
                   
                    
                    
                </div>
            </div>
        </div>
        <div id="bjui-navtab" class="tabsPage">
            <div class="tabsPageHeader">
                <div class="tabsPageHeaderContent">
                    <ul class="navtab-tab nav nav-tabs">
                        <li data-url="${ctx }/index_layout.html?${_csrf.parameterName}=${_csrf.token}"><a href="javascript:;"><span><i class="fa fa-home"></i> #maintab#</span></a></li>
                    </ul>
                </div>
                <div class="tabsLeft"><i class="fa fa-angle-double-left"></i></div>
                <div class="tabsRight"><i class="fa fa-angle-double-right"></i></div>
                <div class="tabsMore"><i class="fa fa-angle-double-down"></i></div>
            </div>
            <ul class="tabsMoreList">
                <li><a href="javascript:;">#maintab#</a></li>
            </ul>
            <div class="navtab-panel tabsPageContent">
                <div class="navtabPage unitBox">
                    <div class="bjui-pageContent" style="background:#FFF;">
                        Loading...
                    </div>
                </div>
            </div>
        </div>
    </div>
  
         <%@ include file="/WEB-INF/jsp/common/footer.jsp" %>
    </div>
</body>
</html>