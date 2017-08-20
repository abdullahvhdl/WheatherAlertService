package com.gurhan.weatheralert.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gurhan.weatheralert.model.QueriedLocation;
import com.gurhan.weatheralert.repository.QueriedLocationRepository;
import com.gurhan.weatheralert.service.QueriedLocationService;

@Component
public class QueriedLocationServiceImpl implements QueriedLocationService {

	@Autowired
	private QueriedLocationRepository locationRepo;

	@Override
	public Iterable<QueriedLocation> findAll() {
		return locationRepo.findAll();
	}

}
