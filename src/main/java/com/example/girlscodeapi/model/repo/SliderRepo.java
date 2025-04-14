package com.example.girlscodeapi.model.repo;

import com.example.girlscodeapi.model.entity.Slider;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SliderRepo extends MongoRepository<Slider, String> {
}
