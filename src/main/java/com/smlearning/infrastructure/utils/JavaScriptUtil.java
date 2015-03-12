package com.smlearning.infrastructure.utils;

/**
 * JavaScript脚本工具�?
 * @author 
 */
public class JavaScriptUtil {
  
	/**
	 * 弹出提示信息
	 * @param message
	 * @return
	 */
	public static String getCodeByAlert(String message) {
		
		return "<script>alert('" + message + "');</script>";
	}

	/**
	 * 关闭本窗�?
	 * @return
	 */
	public static String getCodeByCloseSelf() {
		
		return "<script>close();</script>";
	}
	
	/**
	 * 关闭父窗�?
	 * @return
	 */
	public static String getCodeByCloseParent() {
		
		return "<script>window.parent.close();</script>";
	}
	
	/**
	 * 关闭父父窗口
	 * @return
	 */
	public static String getCodeByCloseParentParent() {
		
		return "<script>window.parent.parent.close();</script>";
	}
	
	/**
	 * 提交本页表单
	 * @param formName
	 * @return
	 */
	public static String getCodeBySubmit(String formName) {
		
		return "<script>window.document." + formName + ".submit();</script>";
	}
	
	/**
	 * 刷新当前�?
	 * @return
	 */
	public static String getCodeByRefresh() {
		
		return "<script>window.document.location.href=window.document.location.href;</script>";
	}	
	
	/**
	 * 刷新父页
	 * @return
	 */
	public static String getCodeByRefreshParent() {
		
		return "<script>window.opener.document.location.href=window.opener.document.location.href;</script>";
	}
	
	/**
	 * 提交父页表单
	 * @param parentFormName
	 * @return
	 */
	public static String getCodeBySubmitParent(String parentFormName) {
		
		return "<script>window.opener.document." + parentFormName + ".submit();</script>";
	}	
	
	/**
	 * 提交父页表单
	 * @param parentFormName
	 * @return
	 */
	public static String getCodeBySubmitParentOpener(String parentFormName) {
		
		
		return "<script>window.parent.opener.document." + parentFormName + ".submit();</script>";
	}

	/**
	 * 弹出提示窗口，并提交iframe�?在页的表�?
	 * @param message 消息
	 * @param formName �?刷新的表单名�?
	 * @return
	 */
	public static String getCodeByAlert_SubmitByIFrame(String message, String formName) {
		
		return "<script>alert('" + message + "');window.parent.document." + formName + ".submit();</script>";
	}
	
	/**
	 * 弹出提示窗口，并刷新父页
	 * @param message 消息
	 * @param parentFormName �?刷新的表单名�?
	 * @return
	 */
	public static String getCodeByAlert_SubmitParent(String message, String parentFormName) {
		
		return "<script>alert('" + message + "');window.opener.document." + parentFormName + ".submit();</script>";
	}
	
	/**
	 * 弹出提示窗口，提交iFrame的父页的父页的父页的form表单
	 * @param message 消息
	 * @param parentFormName �?刷新的表单名�?
	 * @return
	 */
	public static String getCodeByAlert_IFrame_SubmitParent(String message, String parentFormName) {
		
		return "<script>alert('" + message + "');window.parent.parent.opener.document." + parentFormName + ".submit();</script>";
	}
	
	
	/**
	 * 提交父页表单,关闭自己
	 * @param parentFormName
	 * @return
	 */
	public static String getCodeByCloseSelf_SubmitParent(String parentFormName) {
		
		return "<script>window.opener.document." + parentFormName + ".submit();close();</script>";
	}
	
	/**
	 * 刷新父页,关闭自己
	 * @return
	 */
	public static String getCodeByCloseSelf_RefreshParent() {
		
		return "<script>window.opener.document.location.href=window.opener.document.location.href;close();</script>";

	}
	
	public static String getCodeByAlertCloseSelf_RefreshParent(String message) {
		
		return "<script>alert('" + message + "');window.opener.document.location.href=window.opener.document.location.href;close();</script>";

	}
	
	/**
	 * 弹出提示信息，关闭自�?
	 * @param message
	 * @return
	 */
	
	public static String getCodeByAlert_CloseSelf(String message) {
		
		return "<script>alert('" + message + "');close();</script>";
	}
	
	/**
	 * 弹出提示信息，关闭自己，提交父页表单
	 * @param message
	 * @param parentFormName
	 * @return
	 */
	public static String getCodeByAlert_CloseSelf_SubmitParent(String message, String parentFormName) {
	

		return "<script>alert('" + message + "');window.opener.document." + parentFormName + ".submit();close();</script>";

	}
	
	/**
	 * 弹出提示信息，关闭自己，刷新父页
	 * @param message
	 * @return
	 */
	public static String getCodeByAlert_CloseSelf_RefreshParent(String message) {
		
		return "<script>alert('" + message + "');window.opener.document.location.href=window.opener.document.location.href;close();</script>";
	}
	
	/**
	 * 弹出提示信息，刷新父�?
	 * @param message
	 * @return
	 */
	public static String getCodeByAlert_RefreshParent(String message){
		
		return "<script>alert('" + message + "');window.opener.document.location.href=window.opener.document.location.href;</script>";
	
	}
	
	/**
	 * 弹出提示信息，关闭自己，刷新iframe父页
	 * @param message
	 * @return
	 */
	public static String getCodeByAlert_CloseSelf_RefreshIFrameParent(String message) {
		
		return "<script>alert('" + message + "');window.parent.document.location.href=window.parent.document.location.href;close();</script>";
	}
	
	public static String getCodeByAlert_CloseSelf_OpenerRefreshIFrameParent(String message) {
		
		return "<script>alert('" + message + "');window.parent.opener.document.location.href=window.parent.opener.document.location.href;window.parent.close();</script>";
	}
	
	/**
	 * 弹出提示信息，刷新iframe父页
	 * @param message
	 * @return
	 */
	public static String getCodeByAlert_RefreshIFrameParent(String message){
		
		return "<script>alert('" + message + "');window.parent.document.location.href=window.parent.document.location.href;</script>";
		                                                                              
	}
	
	/**
	 *  弹出提示信息，�?�过提交表单刷新iframe父页
	 * @param parentFormName
	 * @param message
	 * @return
	 */
	public static String getCodeByAlert_RefreshIFrameParentBySubmit(String parentFormName, String message){
		
		return "<script>alert('" + message + "');window.parent.document." + parentFormName + ".submit()</script>";
		                                                                              
	}
	
	
	/**
	 *，刷新iframe父页
	 * @return
	 */
	public static String getCodeByRefreshIFrameParent(){
		
		return "<script>window.parent.document.location.href=window.parent.document.location.href;</script>";
		                                                                              
	}
	
	
	/**
	 * 提交表单刷新iframe父页
	 * @param parentFormName
	 * @return
	 */
	public static String getCodeByRefreshIFrameParentBySubmit(String parentFormName){
		
		return "<script>window.parent.document." + parentFormName + ".submit()</script>";
		                                                                              
	}
	
	/**
	 * 刷新iframe父页父页
	 * @param message
	 * @return
	 */
	public static String getCodeByRefreshIFrameParentOperBySubmit(String message){
		
		return "<script>alert('" + message + "');window.parent.parent.opener.document.location.href=window.parent.parent.opener.document.location.href;</script>";
		                                                                              
	}
	/**
	 * 刷新iframe当前�?
	 * @return
	 */
	public static String getCodeByRefreshIFrame() {
		
		return "<script>window.document.location.href=window.document.location.href;</script>";
	}
	
	/**
	 * 调用iframe父页的方�?
	 * @param functionName
	 * @return
	 */
	public static String getCodeByInvokeIFrameParentFunction(String functionName) {
		
		return "<script>window.parent." + functionName + ";</script>";
	}
	
	/**
	 * 调用本页方法
	 * @param functionName
	 * @return
	 */
	public static String getCodeByInvokeFunction(String functionName) {
		
		return "<script>window." + functionName + ";</script>"; 
	}
	
	/**
	 * 在本页打�?新窗�?
	 * @param url 链接地址
	 * @param width 宽度
	 * @param height 高度
	 * @param windowName 窗口�?
	 * @return
	 */
	public static String getCodeByOpenNewWindow(String url, Integer width, Integer height, String windowName ) {
	
		return "<script>openWindowSpecifySize('" + url + "'," + height + "," + width + ",'" + windowName + "');</script>";
	}

	/**
	 * 在父页打�?新窗�?
	 * @param url 链接地址
	 * @param width 宽度
	 * @param height 高度
	 * @param windowName 窗口�?
	 * @return
	 */
	public static String getCodeByOpenNewWindowInParent(String url, Integer width, Integer height, String windowName ) {
		
		return "<script>window.parent.openWindowSpecifySize('" + url + "'," + height + "," + width + ",'" + windowName + "');</script>";
	}                                  
	

	/**
	 * 弹出信息，刷新iFrame父窗口的父窗口，关闭父窗�?
	 * @param message 信息
	 * @return
	 */
	public static String getCodeByAlert_closePrent_IFrame(String message){
		
		return "<script>alert('" + message + "');window.parent.opener.document.location.href=window.parent.opener.document.location.href;window.parent.close();</script>";
	}
	
	/**
	 * 弹出信息，关闭父窗口
	 * @param message 信息
	 * @return
	 */
	public static String getCodeByAlert_closePrent_IFrame_exam(String message){
		
		return "<script>alert('" + message + "');window.parent.close();</script>";
	}
	/**
	 * 关闭父窗�?
	 * @param message 信息
	 * @return
	 */
	public static String getCode_closePrent_IFrame_exam(){
		
		return "<script>window.parent.close();</script>";
	}
	
	/**
	 * 以全屏方式打�?新窗�?
	 * @param url 链接地址
	 * @return
	 */
	public static String getCodeByOpenNewFullWindow(String url,String windowName){
		
		return "<script>window.parent.open('" + url + "'" + ",'" + windowName + "','fullscreen=yes,scrollbars=yes');</script>" ;
	//	return "<script>window.parent.open('" + url + "'" + ",'" + windowName + "','fullscreen=3,titlebar=no,toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=yes,edge:Raised,left=0,top=0,height=768,width=1024,false');</script>" ;
	}
	
	public static String getCodeByOpenNewWindow(String url,String windowName){
		
		return "<script>window.parent.open('" + url + "'" + ",'" + windowName + "','height=700,width=1000,menubar=no,scrollbars=yes');</script>" ;
	//	return "<script>window.parent.open('" + url + "'" + ",'" + windowName + "','fullscreen=3,titlebar=no,toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=yes,edge:Raised,left=0,top=0,height=768,width=1024,false');</script>" ;
	}
	
	/**
	 * 换IFRAME父窗连接
	 * @param url 链接地址
	 * @return
	 */
	public static String getCodeByChangeParentHref(String url){
		
		return "<script>window.parent.location.href='"+url+"';</script>" ;
	}
	
	/**
	 * 弹出提示窗口，关闭父窗口,并提交iframe父页的父页表�?
	 * @param message 消息
	 * @param formName �?刷新的表单名�?
	 * @return
	 */
	public static String getCodeByAlert_SubmitByIFrameParent(String message, String formName) {
		
		return "<script>alert('" + message + "');window.parent.opener.document." + formName + ".submit();window.parent.close();</script>";
	}
	
	/**
	 * 关闭父父窗口,并提交iframe父父页的父页表单
	 * @param formName �?刷新的表单名�?
	 * @return
	 */
	public static String getCodeByCloseParentParent_Submit(String formName) {
		
		return "<script>window.parent.parent.opener.document." + formName + ".submit();window.parent.parent.close();</script>";
	}
}
