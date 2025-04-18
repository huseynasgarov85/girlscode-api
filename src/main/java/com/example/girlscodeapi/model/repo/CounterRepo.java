package com.example.girlscodeapi.model.repo;

import com.example.girlscodeapi.model.entity.Counter;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface CounterRepo extends MongoRepository<Counter, String> {
    @Query("SELECT c FROM COUNTER ORDER BY c.id asc LIMIT 1")
    Optional<Counter> findFirst();

}
