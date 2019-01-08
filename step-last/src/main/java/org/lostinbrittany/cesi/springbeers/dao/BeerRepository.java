package org.lostinbrittany.cesi.springbeers.dao;

import org.lostinbrittany.cesi.springbeers.model.Beer;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface BeerRepository extends MongoRepository<Beer, String> {

    public Beer findById(String id);
}