package com.example.girlscodeapi.service.WorkShopTraining;

import com.example.girlscodeapi.constant.WorkShopTrainingConstant;
import com.example.girlscodeapi.exception.BaseException;
import com.example.girlscodeapi.mapper.WorkShopTrainingMapper;
import com.example.girlscodeapi.model.dto.request.WorkShopTrainingRequest;
import com.example.girlscodeapi.model.dto.response.WorkShopTrainingResponse;
import com.example.girlscodeapi.model.entity.WorkShopTraining;
import com.example.girlscodeapi.model.repo.WorkShopTrainingRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class WorkShopTrainingServiceImpl implements WorkShopTrainingService {
    private  final WorkShopTrainingMapper mapper;
    private final WorkShopTrainingRepository repository;
    @Override
    public WorkShopTrainingResponse add(WorkShopTrainingRequest request) {
        String fileName= UUID.randomUUID()+"_"+request.getFile().getOriginalFilename();
        Path filePath= Paths.get(WorkShopTrainingConstant.IMAGE_DIR+fileName);
        try {
            Files.createDirectories(filePath.getParent());
            Files.write(filePath,request.getFile().getBytes());
        } catch (IOException e) {
            log.warn("image could not be loaded {}",e.getMessage());
        }
        WorkShopTraining workShopTraining=mapper.mapToEntity(request);
        workShopTraining.setImageUrl(WorkShopTrainingConstant.IMAGE_DIR+fileName);
        repository.save(workShopTraining);
        return mapper.mapToResponse(workShopTraining);
    }

    @Override
    public WorkShopTrainingResponse getById(String id) {
        WorkShopTraining workShopTraining=repository.findById(id).orElseThrow(()-> BaseException.notFound(WorkShopTraining.class.getSimpleName(),"id",id));

        return mapper.mapToResponse(workShopTraining);
    }

    @Override
    public WorkShopTrainingResponse update(String id, WorkShopTrainingRequest request) {
        WorkShopTraining workShopTraining=repository.findById(id).orElseThrow(()->BaseException.notFound(WorkShopTraining.class.getSimpleName(),"id",id));
        if (workShopTraining!=null){
            String oldFileName=Paths.get(workShopTraining.getImageUrl()).getFileName().toString();
            Path oldPath=Paths.get(WorkShopTrainingConstant.IMAGE_DIR+oldFileName);
            try {
                Files.deleteIfExists(oldPath);
            } catch (IOException e) {
                log.warn("image could not be deleted {}",e.getMessage());
            }
        }
            String newFileName=UUID.randomUUID()+"_"+request.getFile().getOriginalFilename();
            Path newPath=Paths.get(WorkShopTrainingConstant.IMAGE_DIR+newFileName);
            try {
                Files.createDirectories(newPath.getParent());
                Files.write(newPath,request.getFile().getBytes());
            } catch (IOException e) {
                log.warn("image could not be loaded");
            }
            WorkShopTraining workShopTraining1=mapper.map(request,workShopTraining);
            workShopTraining1.setImageUrl(WorkShopTrainingConstant.IMAGE_DIR+newFileName);
            repository.save(workShopTraining1);
        return mapper.mapToResponse(workShopTraining1);
    }

    @Override
    public void delete(String id) {
        WorkShopTraining workShopTraining=repository.findById(id).orElseThrow(()->BaseException.notFound(WorkShopTraining.class.getSimpleName(),"id",id));
        if (workShopTraining !=null){
            String filename=Paths.get(workShopTraining.getImageUrl()).getFileName().toString();
            Path filePath=Paths.get(WorkShopTrainingConstant.IMAGE_DIR+filename);
            try {
                Files.deleteIfExists(filePath);
            } catch (IOException e) {
                log.warn("image could not be deleted {}",e.getMessage());
            }

            repository.deleteById(id);
        }
    }

    @Override
    public List<WorkShopTrainingResponse> getAll() {
        List<WorkShopTraining> workShopTrainingList=repository.findAll();
        return workShopTrainingList.stream()
                .map(mapper :: mapToResponse)
                .collect(Collectors.toList());
    }
}
