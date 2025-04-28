package com.example.girlscodeapi.model.repo;

import com.example.girlscodeapi.model.entity.WorkShopTraining;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WorkShopTrainingRepository extends MongoRepository<WorkShopTraining,String> {
}
