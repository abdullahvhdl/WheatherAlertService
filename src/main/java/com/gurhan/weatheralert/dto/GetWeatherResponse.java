package com.gurhan.weatheralert.dto;

import java.util.List;

import com.gurhan.weatheralert.model.Events;

public class GetWeatherResponse {

	private int code;
	private int rows;
	private List<Events> list;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public List<Events> getList() {
		return list;
	}

	public void setList(List<Events> list) {
		this.list = list;
	}

}
