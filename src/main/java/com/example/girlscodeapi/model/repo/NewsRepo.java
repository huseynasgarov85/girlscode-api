package com.example.girlscodeapi.model.repo;

import com.example.girlscodeapi.model.entity.CoverPhoto;
import com.example.girlscodeapi.model.enums.filter.DateFilter;
import com.example.girlscodeapi.model.enums.recommended.Recommended;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NewsRepo extends MongoRepository<CoverPhoto, String> {
    @Query("{ 'recommended' : TRUE }")
    List<CoverPhoto> findByRecommendedTrue(Recommended recommended);

    @Query("SELECT n FROM coverPhoto n WHERE n.date >= someDate")
    Page<CoverPhoto> findAllWithPagination(@Param("someDate") DateFilter date, Pageable pageable);

}
