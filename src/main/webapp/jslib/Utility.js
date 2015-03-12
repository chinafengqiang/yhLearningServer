
    serializeObject = function(form){
    	
    	var o = {};
    	$.each(form.serializeArray(), function(index){
    		if(o[this['name']]){
    			o[this['name']] = o[this['name']] + "," + this['value'];
    		}else{
    			o[this['name']] = this['value'];
    		}
    		
    	});
    	
    	return o;
    	
    };


    function trim(str){   //删除左右两端的空格
        return str.replace(/(^\s*)|(\s*$)/g, "");
    }
    
    function ltrim(str){   //删除左边的空格
        return str.replace(/(^\s*)/g,"");
    }
    
    function rtrim(str){   //删除右边的空格
        return str.replace(/(\s*$)/g,"");
    }

    /** 
    IsDate: 用于判断一个字符串是否是日期格式的字符串 

返回值： 
    true或false 

参数： 
    DateString： 需要判断的字符串 
    Dilimeter ： 日期的分隔符，缺省值为´-´ 

Author: PPDJ 
    sample: 
    var date = ´1999-1-2´; 
    if (IsDate(date)) 
    { 
    alert(´You see, the default separator is "-"); 
    } 
    date = ´1999/1/2"; 
    if (IsDate(date,´/´)) 
    { 
    alert(´The date´s separator is "/"); 
    } 
    */

    function IsDate(DateString, Dilimeter) {
        if (DateString == null) return false;
        if (Dilimeter == "" || Dilimeter == null)
            Dilimeter = "-";
        var tempy = "";
        var tempm = "";
        var tempd = "";
        var tempArray;
        if (DateString.length < 8 && DateString.length > 10)
            return false;
        tempArray = DateString.split(Dilimeter);
        if (tempArray.length != 3)
            return false;
        if (tempArray[0].length == 4) {
            tempy = tempArray[0];
            tempd = tempArray[2];
        }
        else {
            tempy = tempArray[2];
            tempd = tempArray[1];
        }
        tempm = tempArray[1];
        var tDateString = tempy + "/" + tempm + "/" + tempd + " 8:0:0"; //加八小时是因为我们处于东八区 
        var tempDate = new Date(tDateString);
        if (isNaN(tempDate))
            return false;
        if (((tempDate.getUTCFullYear()).toString() == tempy) && (tempDate.getMonth() == parseInt(tempm) - 1) && (tempDate.getDate() == parseInt(tempd))) {
            return true;
        }
        else {
            return false;
        }
    } 



    //显示隐藏切换层
    function is_show(id, actionID,hiddenStr,showStr) {
        action = document.getElementById(actionID);  
        if(id = document.getElementById(id)){ 
            if(id.style.display == "none"){
                id.style.display = "block";
                action.innerHTML = hiddenStr;
            }else{
                id.style.display = "none";
                action.innerHTML = showStr;
            } 
        }
    }

    function openModelWindow(url, width, height) {
        window.showModalDialog(url, "", "dialogHeight: " + height + "px; dialogWidth: " + width + "px; edge: Raised; center: Yes; help: Yes; resizable: 0;scroll:Yes; status: Yes;");
    }
    function openWindow(url, width, height) {
        window.open(url, "", "height=" + height + "px, width=" + width + "px, toolbar=no, menubar=no, scrollbars=yes,resizable=no, status=Yes");
    }


    //V1 method
    String.prototype.format = function() {
        var args = arguments;
        return this.replace(/\{(\d+)\}/g,
        function(m, i) {
            return args[i];
        });
    }



    //V2 static
    String.format = function() {
        if (arguments.length == 0)
            return null;

        var str = arguments[0];
        for (var i = 1; i < arguments.length; i++) {
            var re = new RegExp('\\{' + (i - 1) + '\\}', 'gm');
            str = str.replace(re, arguments[i]);
        }
        return str;
    }

    function SwitchUSButtonOn(idStr,siteRoot) {
        $("#" + idStr).attr("style", "width: 59px; height: 17px; background-image: url("+siteRoot+"/home/us_hover_button.jpg); background-repeat: no-repeat; text-align: center;");
    }
    function SwitchUSButtonOut(idStr, siteRoot) {
        $("#" + idStr).attr("style", "width: 59px; height: 18px; background-image: url(" + siteRoot + "/home/us_button.jpg); background-repeat: no-repeat; text-align: center;");
    }

    function ResetForm(frmName) {
        document.forms[frmName].reset();
    }

    function g(o) {
        return document.getElementById(o);
    }
    function HoverLi(m, n, counter) {
        for (var i = 1; i <= counter; i++) {
            g('tb_' + m + i).className = 'normaltab';
            g('tbc_' + m + i).className = 'undis';
        }
        g('tbc_' + m + n).className = 'dis';
        g('tb_' + m + n).className = 'hovertab';
    }

    function senfe(o, a, b, c) {
        var t = document.getElementById(o).getElementsByTagName("tr");
        for (var i = 0; i < t.length; i++) {
            t[i].style.backgroundColor = (t[i].sectionRowIndex % 2 == 0) ? a : b;
            t[i].onmouseover = function() {
                if (this.x != "1") this.style.backgroundColor = c;
            }
            t[i].onmouseout = function() {
                if (this.x != "1") this.style.backgroundColor = (this.sectionRowIndex % 2 == 0) ? a : b;
            }
        }
    }

    function newEditor(txtId) {


        var editor = CKEDITOR.replace(txtId);
        CKFinder.setupCKEditor(editor, { basePath: '/ckfinder/', rememberLastFolder: false });
    }

    function newEditorNoImg(txtId) {


        var editor = CKEDITOR.replace(txtId, {
            toolbar: 'WithoutImgFlash'
        });
        CKFinder.setupCKEditor(editor, { basePath: '/ckfinder/', rememberLastFolder: false });
    }