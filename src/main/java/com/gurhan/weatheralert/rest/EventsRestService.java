package com.gurhan.weatheralert.rest;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gurhan.weatheralert.constant.ApplicationConstants;
import com.gurhan.weatheralert.dto.GetWeatherResponse;
import com.gurhan.weatheralert.dto.RequestFindEventsByDateAndLocation;
import com.gurhan.weatheralert.model.Events;
import com.gurhan.weatheralert.service.EventService;

@RestController
@RequestMapping("/weather")
public class EventsRestService {

	@Autowired
	private EventService eventService;

	@RequestMapping(method = RequestMethod.GET)
	public GetWeatherResponse getWeather(@RequestParam(value = "lon") Double lon, @RequestParam(value = "lat") Double lat, @RequestParam(value = "date", required = false) String strDate,
			@RequestParam(value = "row", required = false) Integer row) {
		GetWeatherResponse resp = new GetWeatherResponse();
		Date date = null;

		if ((date = checkDate(strDate)) == null) {
			resp.setCode(400);
			return resp;
		}

		if (row == null) {
			row = ApplicationConstants.DEFAULT_QUERY_ROW_COUNT;
		}

		RequestFindEventsByDateAndLocation req = new RequestFindEventsByDateAndLocation();
		req.setDate(date);
		req.setLatitude(lat);
		req.setLongtitude(lon);
		req.setRowCount(row);
		List<Events> eventList = eventService.getEventListByTruncDateAndLocations(req);

		if (eventList == null || eventList.isEmpty()) {
			resp.setCode(204);
			return resp;
		}

		resp.setCode(200);
		resp.setList(eventList);
		resp.setRows(eventList.size());
		return resp;
	}

	private Date checkDate(String date) {
		try {
			Date currentTime = Calendar.getInstance().getTime();
			if (date == null) {
				return currentTime;
			}
			Date parseDate = DateUtils.parseDate(date, "yyyy-MM-dd");
			long diff = parseDate.getTime() - currentTime.getTime();
			if (TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) >= 5) {
				return null;
			}
			return parseDate;
		} catch (ParseException e) {
			return null;
		}
	}
}
