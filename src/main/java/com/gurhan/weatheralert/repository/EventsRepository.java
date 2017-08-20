package com.gurhan.weatheralert.repository;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.gurhan.weatheralert.model.Events;

public interface EventsRepository extends CrudRepository<Events, Integer> {

	@Query("select e from Events e where e.longtitude=:longtitude and e.latitude=:latitude and e.date=:date")
	Events findByDateAndLocation(@Param("longtitude") Double longtitude, @Param("latitude") Double latitude, @Param("date") Date date);
	
	@Query("select e from Events e where  e.longtitude=:longtitude and e.latitude=:latitude and date_format(e.date,'%Y-%m-%d')=date_format(:date,'%Y-%m-%d')")
	Page<Events> getEventListByTruncDateAndLocations(@Param("longtitude") Double longtitude, @Param("latitude") Double latitude, @Param("date") Date date, Pageable rowCount);
}
