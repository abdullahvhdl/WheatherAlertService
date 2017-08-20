package com.gurhan.weatheralert.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.gurhan.weatheralert.dto.RequestFindEventsByDateAndLocation;
import com.gurhan.weatheralert.model.Events;
import com.gurhan.weatheralert.repository.EventsRepository;
import com.gurhan.weatheralert.service.EventService;

@Component
public class EventServiceImpl implements EventService {

	@Autowired
	private EventsRepository eventsRepo;

	@Override
	public Events findByDateAndLocation(RequestFindEventsByDateAndLocation req) {
		return eventsRepo.findByDateAndLocation(req.getLongtitude(), req.getLatitude(), req.getDate());
	}

	@Override
	public void save(Events event) {
		eventsRepo.save(event);
	}

	@Override
	public List<Events> getEventListByTruncDateAndLocations(RequestFindEventsByDateAndLocation req) {
		Pageable rowCount = new PageRequest(0, req.getRowCount());
		return eventsRepo.getEventListByTruncDateAndLocations(req.getLongtitude(), req.getLatitude(), req.getDate(), rowCount).getContent();
	}

}
