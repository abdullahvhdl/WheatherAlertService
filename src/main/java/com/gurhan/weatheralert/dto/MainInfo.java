package com.gurhan.weatheralert.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MainInfo {
	
	@JsonProperty("temp")
	private Double temp;
	
	@JsonProperty("temp_min")
	private Double tempMin;
	
	@JsonProperty("temp_max")
	private Double tempMax;
	
	@JsonProperty("pressure")
	private Double pressure;
	
	@JsonProperty("humidity")
	private Double humidity;

	public Double getTemp() {
		return temp;
	}

	public void setTemp(Double temp) {
		this.temp = temp;
	}

	public Double getTempMin() {
		return tempMin;
	}

	public void setTempMin(Double tempMin) {
		this.tempMin = tempMin;
	}

	public Double getTempMax() {
		return tempMax;
	}

	public void setTempMax(Double tempMax) {
		this.tempMax = tempMax;
	}

	public Double getPressure() {
		return pressure;
	}

	public void setPressure(Double pressure) {
		this.pressure = pressure;
	}

	public Double getHumidity() {
		return humidity;
	}

	public void setHumidity(Double humidity) {
		this.humidity = humidity;
	}

}
