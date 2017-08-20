package com.gurhan.weatheralert.service;


import com.gurhan.weatheralert.model.QueriedLocation;

public interface QueriedLocationService {

	public Iterable<QueriedLocation> findAll();
}
