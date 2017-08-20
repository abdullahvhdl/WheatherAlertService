package com.gurhan.weatheralert.service;

import java.util.List;

import com.gurhan.weatheralert.dto.RequestFindEventsByDateAndLocation;
import com.gurhan.weatheralert.model.Events;

public interface EventService {

	public Events findByDateAndLocation(RequestFindEventsByDateAndLocation req);

	public void save(Events event);

	public List<Events> getEventListByTruncDateAndLocations(RequestFindEventsByDateAndLocation req);
}
