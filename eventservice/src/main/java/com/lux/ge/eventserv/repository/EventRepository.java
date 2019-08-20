package com.lux.ge.eventserv.repository;

import org.springframework.data.repository.CrudRepository;

import com.lux.ge.eventserv.model.DataFileEvent;

public interface EventRepository extends CrudRepository<DataFileEvent, Long> {

}
