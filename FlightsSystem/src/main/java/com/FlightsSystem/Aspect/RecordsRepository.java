package com.FlightsSystem.Aspect;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface RecordsRepository extends MongoRepository<Record, Integer> {

}
