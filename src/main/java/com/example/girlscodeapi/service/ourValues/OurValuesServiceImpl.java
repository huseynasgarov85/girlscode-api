package com.example.girlscodeapi.service.ourValues;

import com.example.girlscodeapi.exception.BaseException;
import com.example.girlscodeapi.mapper.OurValuesMapper;
import com.example.girlscodeapi.model.dto.request.OurValuesRequest;
import com.example.girlscodeapi.model.dto.response.OurValuesResponse;
import com.example.girlscodeapi.model.entity.OurValues;
import com.example.girlscodeapi.model.repo.OurValuesRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OurValuesServiceImpl implements OurValuesService {
    private final OurValuesRepo ourValuesRepo;
    private final OurValuesMapper ourValuesMapper;

    @Override
    public void post(OurValuesRequest ourValuesRequest) {
        log.info("ActionLog started post");
        OurValues ourValues = ourValuesMapper.mapToEntity(ourValuesRequest);
        try {
            ourValuesRepo.save(ourValues);
        } catch (Exception e) {
            log.error("ActionLog error ");
            throw BaseException.unexpected();
        }
        log.info("ActionLog end post");
    }

    @Override
    public List<OurValuesResponse> getAll() {
        log.info("ActionLog started getAll");
        List<OurValues> ourValues = ourValuesRepo.findAll();
        log.info("ActionLog end getAll");
        return ourValues.stream().map(ourValuesMapper::mapToResponseForGetAll).toList();
    }

    @Override
    public void update(String id, OurValuesRequest request) {
        log.info("ActionLog started update id :" + id);
        OurValues ourValues = ourValuesRepo.findById(id).orElseThrow(() -> BaseException.notFound(OurValues.class.getSimpleName(), "id", id.toString()));
        try {
          //  OurValues afterUpdate = ourValuesMapper.mapFromRequestToEntityForUpdate(request, ourValues);
            if (request.getTitleAZ() != null && !request.getTitleAZ().isEmpty()) {
                ourValues.setTitleAZ(request.getTitleAZ());
            }
            if (request.getTitleENG() != null && !request.getTitleENG().isEmpty()) {
                ourValues.setTitleENG(request.getTitleENG());
            }
            if (request.getTextAZ() != null && !request.getTextAZ().isEmpty()) {
                ourValues.setTextAZ(request.getTextAZ());
            }
            if (request.getTextENG() != null && !request.getTextENG().isEmpty()) {
                ourValues.setTextENG(request.getTextENG());
            }
            ourValuesRepo.save(ourValues);
        } catch (Exception e) {
            log.error("ActionLog error ");
            throw BaseException.unexpected();
        }
        log.info("ActionLog end update id :" + id);
    }

    @Override
    public void remove(String id) {
        log.info("ActionLog started remove id :" + id);
        try {
            ourValuesRepo.deleteById(id);
        } catch (Exception e) {
            log.error("ActionLog error ");
            throw BaseException.unexpected();
        }
        log.info("ActionLog end remove id :" + id);
    }
}
