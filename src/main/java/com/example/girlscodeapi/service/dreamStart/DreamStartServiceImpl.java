package com.example.girlscodeapi.service.dreamStart;

import com.example.girlscodeapi.exception.BaseException;
import com.example.girlscodeapi.mapper.DreamStartMapper;
import com.example.girlscodeapi.model.dto.request.DreamStartRequest;
import com.example.girlscodeapi.model.dto.response.DreamStartResponse;
import com.example.girlscodeapi.model.entity.DreamStart;
import com.example.girlscodeapi.model.repo.DreamStartRepository;
import com.example.girlscodeapi.util.dreamStart.DreamStartUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class DreamStartServiceImpl implements DreamStartService{
   private final DreamStartRepository repository;
   private final DreamStartMapper mapper;
   private final DreamStartUtil dreamStartUtil;

    @Override
    public DreamStartResponse add(DreamStartRequest request) {
        DreamStart dreamStart=mapper.mapToEntity(request);
        dreamStart.setImageUrl(dreamStartUtil.saveFile(request.getFile()));
        repository.save(dreamStart);

        return  mapper.mapToResponse(dreamStart);
    }

    @Override
    public DreamStartResponse getById() {
        DreamStart dreamStart =repository.findById("680caf06a8009e26fce987d5").orElseThrow(()-> BaseException.notFound(DreamStart.class.getSimpleName(),"id","680caf06a8009e26fce987d5"));
        return mapper.mapToResponse(dreamStart);
    }

    @Override
    public DreamStartResponse update( DreamStartRequest request) {
        DreamStart dreamStart=repository.findById("680caf06a8009e26fce987d5").orElseThrow(()->BaseException.notFound(DreamStart.class.getSimpleName(),"id","680caf06a8009e26fce987d5"));
        if (dreamStart.getImageUrl()!=null){
          dreamStartUtil.removeFile(dreamStart.getImageUrl());
        }
        DreamStart dreamStart1=mapper.map(request,dreamStart);
        dreamStart1.setImageUrl(dreamStartUtil.saveFile(request.getFile()));
        repository.save(dreamStart1);

        return mapper.mapToResponse(dreamStart1);
    }
}
