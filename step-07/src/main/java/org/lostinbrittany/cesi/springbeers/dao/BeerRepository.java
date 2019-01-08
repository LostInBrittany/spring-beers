package org.lostinbrittany.cesi.springbeers.dao;

import java.util.Optional;
import org.lostinbrittany.cesi.springbeers.model.Beer;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface BeerRepository extends MongoRepository<Beer, String> {

    public Optional<Beer> findById(String id);
}