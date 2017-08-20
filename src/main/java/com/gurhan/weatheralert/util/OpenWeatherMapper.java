package com.gurhan.weatheralert.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;

import com.gurhan.weatheralert.dto.OpenWeatherResponse;
import com.gurhan.weatheralert.dto.WeatherInfo;
import com.gurhan.weatheralert.model.Events;

public class OpenWeatherMapper {

	private OpenWeatherMapper() {

	}

	public static List<Events> openWeatherResponse2Event(OpenWeatherResponse resp) {
		List<Events> eventList = new ArrayList<>();
		for (WeatherInfo weatherInfo : resp.getWeatherInfoList()) {
			Events events = new Events();
			events.setCountryId(resp.getCity().getId());
			events.setHumidity(weatherInfo.getMain().getHumidity());
			events.setLatitude(resp.getCity().getCoord().getLat());
			events.setLongtitude(resp.getCity().getCoord().getLon());
			events.setMaxTemp(weatherInfo.getMain().getTempMax());
			events.setMinTemp(weatherInfo.getMain().getTempMin());
			events.setTemperature(weatherInfo.getMain().getTemp());
			events.setPressure(weatherInfo.getMain().getPressure());
			events.setName(resp.getCity().getName());
			try {
				events.setDate(DateUtils.parseDate( weatherInfo.getDtTxt(), "yyyy-MM-dd hh:mm:ss"));
			} catch (Exception e) {
			}

			eventList.add(events);
		}
		return eventList;
	}
}
