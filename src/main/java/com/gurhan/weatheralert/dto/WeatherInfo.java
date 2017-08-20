package com.gurhan.weatheralert.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WeatherInfo {

	@JsonProperty("dt_txt")
	private String dtTxt;
	
	@JsonProperty("main")
	private MainInfo main;

	public String getDtTxt() {
		return dtTxt;
	}

	public void setDtTxt(String dtTxt) {
		this.dtTxt = dtTxt;
	}

	public MainInfo getMain() {
		return main;
	}

	public void setMain(MainInfo main) {
		this.main = main;
	}

}
