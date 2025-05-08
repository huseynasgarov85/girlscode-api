package com.example.girlscodeapi.service.workShopTraining;

import com.example.girlscodeapi.exception.BaseException;
import com.example.girlscodeapi.mapper.WorkShopTrainingMapper;
import com.example.girlscodeapi.model.dto.request.WorkShopTrainingRequest;
import com.example.girlscodeapi.model.dto.response.WorkShopTrainingResponse;
import com.example.girlscodeapi.model.entity.WorkShopTraining;
import com.example.girlscodeapi.model.repo.WorkShopTrainingRepository;
import com.example.girlscodeapi.util.workShopTraining.WorkShopTrainingUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class WorkShopTrainingServiceImpl implements WorkShopTrainingService {
    private  final WorkShopTrainingMapper mapper;
    private final WorkShopTrainingRepository repository;
    private final WorkShopTrainingUtil workShopTrainingUtil;
    @Override
    public WorkShopTrainingResponse add(WorkShopTrainingRequest request) {
        WorkShopTraining workShopTraining=mapper.mapToEntity(request);
        workShopTraining.setImageUrl(workShopTrainingUtil.saveFile(request.getFile()));
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
        if (workShopTraining.getImageUrl()!=null){
            workShopTrainingUtil.removeFile(workShopTraining.getImageUrl());
        }

            WorkShopTraining workShopTraining1=mapper.map(request,workShopTraining);
            workShopTraining1.setImageUrl(workShopTrainingUtil.saveFile(request.getFile()));
            repository.save(workShopTraining1);
        return mapper.mapToResponse(workShopTraining1);
    }

    @Override
    public void delete(String id) {
        WorkShopTraining workShopTraining=repository.findById(id).orElseThrow(()->BaseException.notFound(WorkShopTraining.class.getSimpleName(),"id",id));
        if (workShopTraining.getImageUrl() !=null){
           workShopTrainingUtil.removeFile(workShopTraining.getImageUrl());

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
