package com.example.girlscodeapi.service.infoForWomen;

import com.example.girlscodeapi.exception.BaseException;
import com.example.girlscodeapi.mapper.InfoForWomenMapper;
import com.example.girlscodeapi.model.dto.request.InfoForWomenRequest;
import com.example.girlscodeapi.model.dto.response.InfoForWomenResponse;
import com.example.girlscodeapi.model.entity.InfoForWomen;
import com.example.girlscodeapi.model.repo.InfoForWomenRepository;
import com.example.girlscodeapi.util.infoForWomen.InfoForWomenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class InfoForWomenServiceImpl  implements InfoForWomenService{
    private final InfoForWomenMapper mapper;
   private final InfoForWomenRepository repository;
   private final InfoForWomenUtil infoForWomenUtil;
    @Override
    public InfoForWomenResponse add(InfoForWomenRequest request) {
        InfoForWomen infoForWomen=mapper.mapToEntity(request);
        infoForWomen.setImageUrl(infoForWomenUtil.saveFile(request.getFile()));
        repository.save(infoForWomen);
        return mapper.mapToResponse(infoForWomen);
    }

    @Override
    public InfoForWomenResponse getById(String id) {
        InfoForWomen infoForWomen=repository.findById(id).orElseThrow(()-> BaseException.notFound(InfoForWomen.class.getSimpleName(),"id",id));
        return mapper.mapToResponse(infoForWomen);
    }

    @Override
    public InfoForWomenResponse update(String id, InfoForWomenRequest request) {
        InfoForWomen infoForWomen=repository.findById(id).orElseThrow(()->BaseException.notFound(InfoForWomen.class.getSimpleName(),"id",id));
        if (infoForWomen.getImageUrl() !=null){
          infoForWomenUtil.removeFile(infoForWomen.getImageUrl());
        }

        InfoForWomen infoForWomen1=mapper.map(request,infoForWomen);
        infoForWomen1.setImageUrl(infoForWomenUtil.saveFile(request.getFile()));
        repository.save(infoForWomen1);
        return mapper.mapToResponse(infoForWomen1);
    }

    @Override
    public void delete(String id) {
        InfoForWomen infoForWomen=repository.findById(id).orElseThrow(()-> BaseException.notFound(InfoForWomen.class.getSimpleName(),"id",id));
        if (infoForWomen.getImageUrl() !=null){
           infoForWomenUtil.removeFile(infoForWomen.getImageUrl());
            repository.deleteById(id);
        }

    }

    @Override
    public List<InfoForWomenResponse> getAll() {
        List<InfoForWomen> infoForWomen=repository.findAll();
        return infoForWomen.stream()
                .map(mapper :: mapToResponse)
                .collect(Collectors.toList());
    }
}
