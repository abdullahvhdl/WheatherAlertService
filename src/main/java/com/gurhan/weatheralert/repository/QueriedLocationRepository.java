package com.gurhan.weatheralert.repository;

import org.springframework.data.repository.CrudRepository;

import com.gurhan.weatheralert.model.QueriedLocation;

public interface QueriedLocationRepository extends CrudRepository<QueriedLocation, Integer>{

}
