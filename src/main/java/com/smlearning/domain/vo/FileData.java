package com.smlearning.domain.vo;

import java.io.File;

public class FileData {

	private String savePath;
	/** 这里的名字和html的名字必须对称 */
	private File img;
	/** 要上传的文件类型 */
	private String imgContentType;
	/** 文件的名称 */
	private String imgFileName;

	private String orderId;

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public File getImg() {
		return img;
	}

	public void setImg(File img) {
		this.img = img;
	}

	public String getImgContentType() {
		return imgContentType;
	}

	public void setImgContentType(String imgContentType) {
		this.imgContentType = imgContentType;
	}

	public String getImgFileName() {
		return imgFileName;
	}

	public void setImgFileName(String imgFileName) {
		this.imgFileName = imgFileName;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
}
