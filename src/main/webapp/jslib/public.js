function attachSelectBox(oInputField,value,url){
	jQuery.getJSON(url,{},function(data){
		var options = new Array();

		for (var i=0;i<data.length;i++){
			option = document.createElement('Option');
			option.text = data[i].name;
			option.value = data[i].value;
			try{
				oInputField.add(option,null);
			}catch(ex){
				oInputField.add(option);
			}
			if (option.value==value){
				option.selected=true;
			}
		}
	});
}

//checkbox框只让其提交0或1
function attachCheckBox(oCheckField,oHiddenField){
	if (jQuery(oHiddenField).val()==1){
		oCheckField.checked = true;
	}else{
		oCheckField.checked = false;
	}
	jQuery(oCheckField).click(function(){
		if (oCheckField.checked)
			jQuery(oHiddenField).val("1");
		else
			jQuery(oHiddenField).val("0");
	});
}

/*
 * 包含新增页面的框架
 * title：标题
 * url：包含页面的地址
 * formId：包含页面的form的ID
 * width：包含页面的宽度
 * height：包含页面的高度
 */
function addrow(title, url, formId, width, height) {
	title = title ? title : '';
	formId = formId ? formId : 'formId';
	width = width ? width : 400;
	height = height ? height : 300;
	showWindow({
		title : title,
		href : url,
		width : width,
		height : height
		/*onLoad : function() {
			$('#' + formId).form('clear');
		}*/
	});
}


/*
 * 新增操作
 * url：执行新增的地址
 * formId：提交数据的form的ID
 * datagrid：新增页面父列表框的ID，用于新增后涮新父列表
 * info：操作后的提示信息
 */
function add(url,formId,datagrid,info) {
	formId = formId ? formId : 'formId';
	datagrid = datagrid ? datagrid : 'dataTableId';
	var r = $('#'+formId).form('validate');
	if (!r) {
		return false;
	}
	$.post(url, $("#"+formId).serializeArray(), function(data) {
		$('#MyPopWindow').window('close');
		$('#'+datagrid).datagrid('reload');
		if(data)
			$.messager.alert('提示','数据操作成功', 'info');
		else
			$.messager.alert('提示', '数据操作失败', 'info');
		
	});
}

/*
 * 包含更新页面的框架
 * title：标题
 * url：包含页面的地址
 * formId：包含页面的form的ID
 * datagrid：新增页面父列表框的ID
 * width：包含页面的宽度
 * height：包含页面的高度
 * isOpt：form加载后是否处理一页页面事件
 */
function updaterow(title,url,formId,datagrid,width,height,isOpt){
	formId = formId ? formId : 'formId';
	datagrid = datagrid ? datagrid : 'dataTable';
	width = width ? width : 400;
	height = height ? height : 300;
	var rows = $('#'+datagrid).datagrid('getSelections');
	if(rows.length==0){
		$.messager.alert('提示',"请选择要更新的数据",'info');
		return;
	}
	if(rows.length > 1){
		$.messager.alert('提示',"只能选择一行数据进行更新",'info');
		return;
	}
	showWindow({
			title:title,
			href:url,
			width:width,
			height:height,
			onLoad: function(){
			//自动将数据填充到表单中，无需再查询数据库，这里需要注意：
			//如果用的是struts2，它的表单元素的名称都是user.id这样的，那load的时候不能加.user要.form('load', rows[0]);
			//而spring mvc中表单元素的名称不带对象前缀，直拉就是id，所以这里load的时候是：.form('load', rows[0].user)
				$("#"+formId).form('load', rows[0]);
				if(isOpt){
					formOpt();
				}
			}
		});
}

function updaterow(title,url,datagrid,pk,width,height){
	datagrid = datagrid ? datagrid : 'dataTable';
	pk = pk?pk : 'ID';
	width = width ? width : 600;
	height = height ? height : 400;
	var rows = $('#'+datagrid).datagrid('getSelections');
	if(rows.length==0){
		$.messager.alert('提示',"请选择要编辑的数据",'info');
		return;
	}
	if(rows.length > 1){
		$.messager.alert('提示',"只能选择一行数据进行编辑",'info');
		return;
	}
	
	dialog = parent.fq.modalDialog({
		title : title,
		url : fq.contextPath + url+"?"+pk+"="+rows[0][pk],
		width : width,
		height : height,
		buttons : [ {
			text:'取消',
			iconCls:'icon-no',
			handler:function(){
				dialog.window('close');
			}
			},{
			text : '编辑',
			iconCls:'icon-ok',
			handler : function() {
				dialog.find('iframe').get(0).contentWindow.submitForm(dialog,$("#"+datagrid), parent.$);
			}
		} ]
	});
}


function updaterows(title,url,datagrid,pk,width,height){
	datagrid = datagrid ? datagrid : 'dataTable';
	pk = pk?pk : 'ID';
	width = width ? width : 600;
	height = height ? height : 400;
	var rows = $('#'+datagrid).datagrid('getSelections');
	if(rows.length==0){
		$.messager.alert('提示',"请选择要操作的数据",'info');
		return;
	}
	var params = "";
	$.each(rows,function(i,obj){
 		var pkValue = obj[pk];
 		if(i==0) 
 			params += pkValue;
 		else
 			params += ","+pkValue;
 	});
	
	dialog = parent.fq.modalDialog({
		title : title,
		url : fq.contextPath + url+"?ids="+params,
		width : width,
		height : height,
		buttons : [ {
			text:'取消',
			iconCls:'icon-no',
			handler:function(){
				dialog.window('close');
			}
			},{
			text : '编辑',
			iconCls:'icon-ok',
			handler : function() {
				dialog.find('iframe').get(0).contentWindow.submitForm(dialog,$("#"+datagrid), parent.$);
			}
		} ]
	});
}


/*
 * 删除操作
 * url：删除操作的地址
 * datagrid：新增页面父列表框的ID，用于新增后涮新父列表
 */
function deleterow(url,datagrid,pkName){
		//var rows = $('#'+datagrid).datagrid('getSelections');
		var rows = $('#'+datagrid).datagrid('getChecked');
		if(rows.length == 0){
			$.messager.alert('提示','请选择要删除的项！','info');
			return;
		}
		//alert(rows.length);
		$.messager.confirm('提示','确定要删除吗?',function(result){
        if (result){
        	var ps = "";
        	$.each(rows,function(i,obj){
        		var pkValue = obj[pkName];
        		/*$.each(n,function(key,value){
        			if(key==pkName){
        				pkValue = value;
        			}
        		});*/
        		if(i==0) 
        			ps += "?id="+pkValue;
        		else
        			ps += "&id="+pkValue;
        	});
        /*	$.post(url+ps,function(data){
	        	$('#'+datagrid).datagrid('reload'); 
        		$.messager.alert('提示','数据删除成功','info');
        	});*/
        	//alert(url+ps);
        	$.post(url+ps, 
    				function(result) {
    					if (result.success) {
    						parent.$.messager.alert('提示',"删除操作成功", 'info');
    						dataGrid.datagrid('reload');
    						dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
    					}
    					parent.$.messager.progress('close');
    				}, 'JSON');
        }
    });
	}


function setrow(url,datagrid,pkName){
	var rows = $('#'+datagrid).datagrid('getChecked');
	if(rows.length == 0){
		$.messager.alert('提示','请选择要重置的项！','info');
		return;
	}
	$.messager.confirm('提示','确定要重置吗?',function(result){
    if (result){
    	var ps = "";
    	$.each(rows,function(i,obj){
    		var pkValue = obj[pkName];
    		if(i==0) 
    			ps += "?id="+pkValue;
    		else
    			ps += "&id="+pkValue;
    	});
    	$.post(url+ps, 
				function(result) {
					if (result.success) {
						parent.$.messager.alert('提示',"重置操作成功", 'info');
						dataGrid.datagrid('reload');
						dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
					}
					parent.$.messager.progress('close');
				}, 'JSON');
    }
});
}


function show(title,url,datagrid,pk,width,height){
	datagrid = datagrid ? datagrid : 'dataTable';
	pk = pk?pk : 'ID';
	var rows = $('#'+datagrid).datagrid('getSelections');
	if(rows.length==0){
		$.messager.alert('提示',"请选择要查看的项",'info');
		return;
	}
	if(rows.length > 1){
		$.messager.alert('提示',"只能选择一项",'info');
		return;
	}
	
	dialog = parent.fq.modalDialog({
		title : title,
		url : fq.contextPath + url+"?"+pk+"="+rows[0][pk],
		width : width,
		height : height,
		buttons : [ {
			text:'关闭',
			iconCls:'icon-no',
			handler:function(){
				dialog.window('close');
			}
			} ]
	});
}



function show(title,url,width,height){
	width = width ? width : 600;
	height = height ? height : 400;
	dialog = parent.fq.modalDialog({
		title : title,
		url : fq.contextPath + url,
		width : width,
		height : height,
		buttons : [ {
			text:'关闭',
			iconCls:'icon-no',
			handler:function(){
				dialog.window('close');
			}
			} ]
	});
}

// 获取页面的高度、宽度
function getPageSize() {
    var xScroll, yScroll;
    if (window.innerHeight && window.scrollMaxY) {
        xScroll = window.innerWidth + window.scrollMaxX;
        yScroll = window.innerHeight + window.scrollMaxY;
    } else {
        if (document.body.scrollHeight > document.body.offsetHeight) { // all but Explorer Mac    
            xScroll = document.body.scrollWidth;
            yScroll = document.body.scrollHeight;
        } else { // Explorer Mac...would also work in Explorer 6 Strict, Mozilla and Safari    
            xScroll = document.body.offsetWidth;
            yScroll = document.body.offsetHeight;
        }
    }
    var windowWidth, windowHeight;
    if (self.innerHeight) { // all except Explorer    
        if (document.documentElement.clientWidth) {
            windowWidth = document.documentElement.clientWidth;
        } else {
            windowWidth = self.innerWidth;
        }
        windowHeight = self.innerHeight;
    } else {
        if (document.documentElement && document.documentElement.clientHeight) { // Explorer 6 Strict Mode    
            windowWidth = document.documentElement.clientWidth;
            windowHeight = document.documentElement.clientHeight;
        } else {
            if (document.body) { // other Explorers    
                windowWidth = document.body.clientWidth;
                windowHeight = document.body.clientHeight;
            }
        }
    }       
    // for small pages with total height less then height of the viewport    
    if (yScroll < windowHeight) {
        pageHeight = windowHeight;
    } else {
        pageHeight = yScroll;
    }    
    // for small pages with total width less then width of the viewport    
    if (xScroll < windowWidth) {
        pageWidth = xScroll;
    } else {
        pageWidth = windowWidth;
    }
    arrayPageSize = new Array(pageWidth, pageHeight, windowWidth, windowHeight);
    //alert(windowWidth+" "+windowHeight);
    return arrayPageSize;
}


/**  
 * StringBuffer Class, to join two string is the most use  
 *   
 */  
function StringBuffer()   
{   
    this._strings = [];   
    if(arguments.length==1)   
    {   
        this._strings.push(arguments[0]);   
    }   
}   
  
StringBuffer.prototype.append = function(str)   
{   
    this._strings.push(str);   
    return this;   
}; 
  
StringBuffer.prototype.toString = function()   
{   
    return this._strings.join("");   
};
  
/* 返回长度 */  
StringBuffer.prototype.length = function()   
{   
    var str = this._strings.join("");   
    return str.length;   
};   
  
/* 删除后几位字符 */  
StringBuffer.prototype.del = function(num)   
{   
    var len = this.length();   
    var str = this.toString();   
    str = str.slice(0,len-num);   
    this._strings = [];   
    this._strings.push(str);   
};  


//添加
function addFun(title,url,formId,width,height) {
	width = width ? width : 600;
	height = height ? height : 400;
	parent.$.modalDialog({
		title : title,
		width : width,
		height : height,
		href : url,
		buttons : [ {
			text : '添加',
			handler : function() {
				parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
				var f = parent.$.modalDialog.handler.find('#'+formId);
				parent.$.modalDialog.openner_dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
				f.submit();
			}
		} ]
	});
}

//编辑
function editFun(title,url,formId,width,height) {
	/*alert(url);
	if (id == undefined) {
		var rows = dataGrid.datagrid('getSelections');
		id = rows[0].id;
	} else {
		dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
	}*/
	
	width = width ? width : 600;
	height = height ? height : 400;
	parent.$.modalDialog({
		title : title,
		width : width,
		height : height,
		href : url,
		buttons : [ {
			text : '编辑',
			handler : function() {
				parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
				parent.$.modalDialog.openner_dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
				var f = parent.$.modalDialog.handler.find('#'+formId);
				f.submit();
			}
		} ]
	});
}


//删除
function deleteFun(url) {
	/*if (id == undefined) {//点击右键菜单才会触发这个
		var rows = dataGrid.datagrid('getSelections');
		id = rows[0].id;
	} else {//点击操作里面的删除图标会触发这个
		dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
	}*/
	
	parent.$.messager.confirm('询问', '您是否要删除当前信息？', function(b) {
		if (b) {
				parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});
				$.post(url, 
				function(result) {
					if (result.success) {
						parent.$.messager.alert('提示',"删除操作成功", 'info');
						dataGrid.datagrid('reload');
						dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
					}
					parent.$.messager.progress('close');
				}, 'JSON');
		}
	});
}


//编辑
function showFun(title,url,width,height) {
	parent.$.messager.progress('close');
	width = width ? width : 600;
	height = height ? height : 400;
	dialog = parent.$.modalDialog({
		title : title,
		width : width,
		height : height,
		href : url,
		buttons : [ {
			text : '关闭',
			handler : function() {
				dialog.window('close');
			}
		} ]
	});
}

function submitForm(url){
	parent.$.messager.progress('close');
	$('#form').form({
		url : url,
		onSubmit : function() {
			parent.$.messager.progress({
				title : '提示',
				text : '数据处理中，请稍后....'
			});
			var isValid = $(this).form('validate');
			if (!isValid) {
				parent.$.messager.progress('close');
			}
			return isValid;
		},
		success : function(result) {
			parent.$.messager.progress('close');
			result = $.parseJSON(result);
			if (result.success) {
				parent.$.modalDialog.openner_dataGrid.datagrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_dataGrid这个对象，是因为user.jsp页面预定义好了
				parent.$.modalDialog.handler.dialog('close');
			} else {
				parent.$.messager.alert('错误', result.msg, 'error');
			}
		}
	});
}

function attachGradeSelectBox(oInputField,value,url){
	jQuery.getJSON(url,{},function(data){
		var options = new Array();
		for (var i=0;i<data.length;i++){
			option = document.createElement('Option');
			option.text = data[i].name;
			option.value = data[i].id;
			try{
				oInputField.add(option,null);
			}catch(ex){
				oInputField.add(option);
			}
			if (option.value==value){
				option.selected=true;
			}
		}
	});
}


function attachCategorySelectBox(oInputField,value,url){
	jQuery.getJSON(url,{},function(data){
		var options = new Array();
		for (var i=0;i<data.length;i++){
			option = document.createElement('Option');
			option.text = data[i].name;
			option.value = data[i].id;
			try{
				oInputField.add(option,null);
			}catch(ex){
				oInputField.add(option);
			}
			if (option.value==value){
				option.selected=true;
			}
		}
	});
}

function attachResMPathSelectBox(oInputField,value,url){
	jQuery.getJSON(url,{},function(data){
		var options = new Array();
		for (var i=0;i<data.length;i++){
			option = document.createElement('Option');
			option.text = data[i].name;
			option.value = data[i].m_path;
			try{
				oInputField.add(option,null);
			}catch(ex){
				oInputField.add(option);
			}
			if (option.value==value){
				option.selected=true;
			}
		}
	});
}

