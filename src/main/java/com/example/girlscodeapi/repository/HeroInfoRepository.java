package com.example.girlscodeapi.repository;

import com.example.girlscodeapi.model.entity.HeroInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HeroInfoRepository extends MongoRepository<HeroInfo, String> {
}
