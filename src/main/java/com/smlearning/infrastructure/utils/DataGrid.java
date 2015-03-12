package com.smlearning.infrastructure.utils;

import java.util.ArrayList;
import java.util.List;

public class DataGrid {

	private Long total = 0L;
	private List<?> rows = new ArrayList<Object>();

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}


}
