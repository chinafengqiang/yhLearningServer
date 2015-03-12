
var node_id;
var node_pid;
var node_name;


document.onclick = function()
{
  var menu = document.getElementById("dtreeContextMenu");
	menu.style.display = "none";
};

function dtreeContextMenu(id, pid, name)
{
  node_id = id;
  node_pid = pid;
  node_name = name;

  var menu = document.getElementById("dtreeContextMenu");
  menu.style.left = event.clientX  + "px";
  menu.style.top = event.clientY + document.documentElement.scrollTop + "px";
  menu.style.display = "block";
}

function dtreeContextMenu_add(url)
{
 //window.location = url + "?id=" + node_id + "&pid=" + node_pid;

  var turl = url + "?id=" + node_id + "&pid=" + node_pid;
 
  	parent.$.modalDialog({
		title : '课件分类',
		width : 400,
		height : 500,
		href : turl,
		buttons : [ {
			text : '添加',
			handler : function() {
				parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
				var f = parent.$.modalDialog.handler.find('#form');
				f.submit();
			}
		} ]
	});

}

function dtreeContextMenu_edit(url)
{
   if(node_id == 0)
    {
      alert("顶级节点不能重命名");
    }
    else
    {
	  var turl = url + "&id=" + node_id + "&pid=" + node_pid;

        parent.$.modalDialog({
		title : '编辑课件分类',
		width : 400,
		height : 500,
		href : turl,
		buttons : [ {
			text : '编辑',
			handler : function() {
				parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
				var f = parent.$.modalDialog.handler.find('#form');
				f.submit();
			}
		} ]
	});

    }
}


function dtreeContextMenu_delete(url)
{
    if(node_id == 0)
    {
      alert("顶级节点不能删除");
    }
    else
    {
      if (confirm("您确定要删除节点 【" + node_name + "】 吗？"))
      {

        var turl = url + "&id=" + node_id + "&pid=" + node_pid;

		jQuery.ajax({  
        type:"post",  
        url:turl,  
        dataType:"json",  
        success: function(result) {
			if (result.success) {
             parent.$.messager.alert('提示', result.msg, 'info');
			  returnFun();
			}	
        }  
    });  

      }
    }
  
}
