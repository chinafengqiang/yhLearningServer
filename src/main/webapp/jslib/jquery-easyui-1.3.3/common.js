function confirm_easyui(title, question, loc) {
    $.messager.confirm(title, question, function(r) {
    if (r) {
            location.href = loc;
        }
    });
}

function isEmptyOrNull(str) {
    
    var content=str;
    if (content!=null) {
        content= content.replace(/(^\s*)|(\s*$)/g, "");
    } else {
        return true;
    }

    if (content == "") {
        return true;
    }

    return false;

}

//转换Json日期格式
function ChangeDateFormat(cellval) {

    var date = new Date(parseInt(cellval.replace("/Date(", "").replace(")/", ""), 10));

    var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;

    var currentDate = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();

    var currentTime = date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds();

    return date.getFullYear() + "-" + month + "-" + currentDate + " " + date.toLocaleTimeString();

}

//转换Json日期格式
function ChangeShortDateFormat(cellval) {

    var date = new Date(parseInt(cellval.replace("/Date(", "").replace(")/", ""), 10));

    var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;

    var currentDate = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();

    return date.getFullYear() + "-" + month + "-" + currentDate;

}

//转换品牌Tag的格式
function ChangeBrandTag(brandTags) {

    if (brandTags == null || brandTags == "")
        return "";

    var result = "";
    var brandList = brandTags.split("|");

    for (var i = 1; i < brandList.length; i++) {
        if (i == (brandList.length - 1)) {
            result = result + (brandList[i].split("_"))[2] ;
        } else {
            result = result + (brandList[i].split("_"))[2] + ",";
        }
    }

    return result;
}