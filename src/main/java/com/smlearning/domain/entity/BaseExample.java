package com.smlearning.domain.entity;

public class BaseExample {

	// 起始值
	protected int offset;
		
	// 偏移量
	protected int limit;
    public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

}
