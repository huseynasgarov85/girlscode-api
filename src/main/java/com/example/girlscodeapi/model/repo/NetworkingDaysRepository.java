package com.example.girlscodeapi.model.repo;

import com.example.girlscodeapi.model.entity.NetworkingDays;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NetworkingDaysRepository extends MongoRepository<NetworkingDays,String> {
}
