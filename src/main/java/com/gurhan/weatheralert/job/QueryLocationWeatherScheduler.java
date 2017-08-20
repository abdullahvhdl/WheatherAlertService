package com.gurhan.weatheralert.job;

import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gurhan.weatheralert.dto.OpenWeatherResponse;
import com.gurhan.weatheralert.dto.RequestFindEventsByDateAndLocation;
import com.gurhan.weatheralert.model.Events;
import com.gurhan.weatheralert.model.QueriedLocation;
import com.gurhan.weatheralert.service.EventService;
import com.gurhan.weatheralert.service.QueriedLocationService;
import com.gurhan.weatheralert.util.OpenWeatherMapper;

@Component
public class QueryLocationWeatherScheduler {

	private static final Logger log = LoggerFactory.getLogger(QueryLocationWeatherScheduler.class);

	@Autowired
	private QueriedLocationService locationService;

	@Autowired
	private EventService eventService;

	@Value("${openweathermap.baseurl}")
	private String baseUrl;

	@Value("${openweathermap.appkey}")
	private String appKey;

	@Scheduled(fixedRate = 1000 * 60 * 60 * 3)
	// @Scheduled(fixedRate = 1000 * 30)
	public void updateWeatherInfo() {

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		Iterable<QueriedLocation> locations = locationService.findAll();

		Iterator<QueriedLocation> iterator = locations.iterator();
		while (iterator.hasNext()) {
			QueriedLocation location = iterator.next();
			OpenWeatherResponse inquiryLocationWeatherInfo = inquiryLocationWeatherInfo(location, mapper);
			if (inquiryLocationWeatherInfo != null) {
				insertOrUpdateLocationWeather(inquiryLocationWeatherInfo, location);
			}
		}
	}

	private OpenWeatherResponse inquiryLocationWeatherInfo(QueriedLocation location, ObjectMapper mapper) {
		RestTemplate template = new RestTemplate();
		String requestUrl = createRequestUrl(location);
		String respJson = template.getForObject(requestUrl, String.class);
		System.out.println(respJson);
		OpenWeatherResponse resp = null;
		try {
			resp = mapper.readValue(respJson, OpenWeatherResponse.class);
		} catch (Exception e) {
			log.error("There was an error querying the weather: log");
		}
		return resp;
	}

	private String createRequestUrl(QueriedLocation location) {
		StringBuilder sb = new StringBuilder();
		//@formatter:off
		sb.append(baseUrl).
		append("lon=").append(location.getLongtitude().toString()).
		append("&lat=").append(location.getLatitude().toString()).
		append("&appid=").append(appKey);
		//@formatter:on
		return sb.toString();
	}

	private void insertOrUpdateLocationWeather(OpenWeatherResponse inquiryLocationWeatherInfo, QueriedLocation location) {
		List<Events> eventList = OpenWeatherMapper.openWeatherResponse2Event(inquiryLocationWeatherInfo);
		for (Events event : eventList) {
			RequestFindEventsByDateAndLocation req = new RequestFindEventsByDateAndLocation();
			req.setLongtitude(event.getLongtitude());
			req.setLatitude(event.getLatitude());
			req.setDate(event.getDate());
			Events oldEventData = eventService.findByDateAndLocation(req);
			if (oldEventData != null) {
				event.setId(oldEventData.getId());
			}
			eventService.save(event);
		}
	}

}
