package com.example.girlscodeapi.repository;

import com.example.girlscodeapi.model.entity.HeroInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

public interface HeroInfoRepository extends MongoRepository<HeroInfo, String> {
}
