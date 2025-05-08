package com.example.girlscodeapi.model.repo;

import com.example.girlscodeapi.model.entity.ActivityBanner;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ActivityBannerRepository extends MongoRepository<ActivityBanner,String> {
}
