package com.example.girlscodeapi.model.repo;

import com.example.girlscodeapi.model.entity.Photo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PhotoRepo extends MongoRepository<Photo, String> {

    List<Photo> findByCoverPhotoId(String id);
}
