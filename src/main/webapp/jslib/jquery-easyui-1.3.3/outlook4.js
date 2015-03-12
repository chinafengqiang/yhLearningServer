

parent.$(function() {
    InitLeftMenu();
    tabClose();
    tabCloseEven();
})

//初始化左侧
function InitLeftMenu() {
    parent.$("#nav").accordion({ animate: false });
/*
    parent.$.each(parent._menus1.menus, function(i, n) {
        var menulist = '';
        menulist += '<ul>';
        parent.$.each(n.menus, function(j, o) {
            menulist += '<li><div><a ref="' + o.menuid + '" href="#" rel="' + o.url + '" ><span class="icon ' + o.icon + '" >&nbsp;</span><span class="nav">' + o.menuname + '</span></a></div></li> ';
        })
        menulist += '</ul>';

        parent.$('#nav').accordion('add', {
            title: n.menuname,
            content: menulist,
            iconCls: 'icon ' + n.icon
        });

    });
*/
    parent.$('.easyui-accordion li a').click(function() {
        var tabTitle = parent.$(this).children('.nav').text();

        var url = parent.$(this).attr("rel");
        var menuid = parent.$(this).attr("ref");
        var icon = getIcon(menuid, icon);

        addTab(tabTitle, url, icon);
        parent.$('.easyui-accordion li div').removeClass("selected");
        parent.$(this).parent().addClass("selected");
    }).hover(function() {
        parent.$(this).parent().addClass("hover");
    }, function() {
        parent.$(this).parent().removeClass("hover");
    });

    //选中第一个
    var panels = parent.$('#nav').accordion('panels');
    var t = panels[0].panel('options').title;
    parent.$('#nav').accordion('select', t);
}
//获取左侧导航的图标
function getIcon(menuid) {
    var icon = 'icon ';
    /*parent.$.each(parent._menus1.menus, function(i, n) {
        parent.$.each(n.menus, function(j, o) {
            if (o.menuid == menuid) {
                icon += o.icon;
            }
        })
    })*/

    return icon;
}

function addTab(subtitle, url, icon) {
    if (!parent.$('#tabs').tabs('exists', subtitle)) {
        parent.$('#tabs').tabs('add', {
            title: subtitle,
            content: createFrame(url),
            closable: true,
            icon: icon
        });
    } else {
        parent.$('#tabs').tabs('select', subtitle);
        parent.$('#mm-tabupdate').click();
    }
    tabClose();
}

function createFrame(url) {
    var s = '<iframe scrolling="auto" frameborder="0"  src="' + url + '" style="width:100%;height:100%;"></iframe>';
    return s;
}

function tabClose() {
    /*双击关闭TAB选项卡*/
    parent.$(".tabs-inner").dblclick(function() {
        var subtitle = parent.$(this).children(".tabs-closable").text();
        parent.$('#tabs').tabs('close', subtitle);
    })
    /*为选项卡绑定右键*/
    parent.$(".tabs-inner").bind('contextmenu', function(e) {
        parent.$('#mm').menu('show', {
            left: e.pageX,
            top: e.pageY
        });

        var subtitle = parent.$(this).children(".tabs-closable").text();

        parent.$('#mm').data("currtab", subtitle);
        parent.$('#tabs').tabs('select', subtitle);
        return false;
    });
}
//绑定右键菜单事件
function tabCloseEven() {
    //刷新
    parent.$('#mm-tabupdate').click(function() {
        var currTab = parent.$('#tabs').tabs('getSelected');
        var url = parent.$(currTab.panel('options').content).attr('src');
        parent.$('#tabs').tabs('update', {
            tab: currTab,
            options: {
                content: createFrame(url)
            }
        })
    })
    //关闭当前
    parent.$('#mm-tabclose').click(function() {
        var currtab_title = parent.$('#mm').data("currtab");
        parent.$('#tabs').tabs('close', currtab_title);
    })
    //全部关闭
    parent.$('#mm-tabcloseall').click(function() {
        parent.$('.tabs-inner span').each(function(i, n) {
            var t = parent.$(n).text();
            parent.$('#tabs').tabs('close', t);
        });
    });
    //关闭除当前之外的TAB
    parent.$('#mm-tabcloseother').click(function() {
        parent.$('#mm-tabcloseright').click();
        parent.$('#mm-tabcloseleft').click();
    });
    //关闭当前右侧的TAB
    parent.$('#mm-tabcloseright').click(function() {
        var nextall = parent.$('.tabs-selected').nextAll();
        if (nextall.length == 0) {
            //msgShow('系统提示','后边没有啦~~','error');
            alert('后边没有啦~~');
            return false;
        }
        nextall.each(function(i, n) {
            var t = parent.$('a:eq(0) span', parent.$(n)).text();
            parent.$('#tabs').tabs('close', t);
        });
        return false;
    });
    //关闭当前左侧的TAB
    parent.$('#mm-tabcloseleft').click(function() {
        var prevall = parent.$('.tabs-selected').prevAll();
        if (prevall.length == 0) {
            alert('到头了，前边没有啦~~');
            return false;
        }
        prevall.each(function(i, n) {
            var t = parent.$('a:eq(0) span', parent.$(n)).text();
            parent.$('#tabs').tabs('close', t);
        });
        return false;
    });

    //退出
    parent.$("#mm-exit").click(function() {
        parent.$('#mm').menu('hide');
    })
}

//弹出信息窗口 title:标题 msgString:提示信息 msgType:信息类型 [error,info,question,warning]
function msgShow(title, msgString, msgType) {
    parent.$.messager.alert(title, msgString, msgType);
}

