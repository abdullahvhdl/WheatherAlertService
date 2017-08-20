package com.gurhan.weatheralert.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OpenWeatherResponse {

	@JsonProperty("list")
	private List<WeatherInfo> weatherInfoList;

	@JsonProperty("city")
	private City city;

	public List<WeatherInfo> getWeatherInfoList() {
		return weatherInfoList;
	}

	public void setWeatherInfoList(List<WeatherInfo> weatherInfoList) {
		this.weatherInfoList = weatherInfoList;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

}
