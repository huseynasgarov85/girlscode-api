package com.example.girlscodeapi.model.repo;

import com.example.girlscodeapi.model.entity.CoverPhoto;
import com.example.girlscodeapi.model.enums.recommended.Recommended;
import com.example.girlscodeapi.model.enums.recommended.RecommendedCheck;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface NewsRepo extends MongoRepository<CoverPhoto, String> {
    @Query("{ 'recommended' : TRUE }")
    List<CoverPhoto> findByRecommendedTrue(RecommendedCheck recommended);

}
