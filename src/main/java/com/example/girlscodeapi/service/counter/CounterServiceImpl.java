package com.example.girlscodeapi.service.counter;

import com.example.girlscodeapi.exception.BaseException;
import com.example.girlscodeapi.mapper.CounterMapper;
import com.example.girlscodeapi.model.dto.request.CounterRequest;
import com.example.girlscodeapi.model.dto.response.CounterResponse;
import com.example.girlscodeapi.model.entity.Counter;
import com.example.girlscodeapi.model.repo.CounterRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CounterServiceImpl implements CounterService {
    private final CounterRepo counterRepo;
    private final CounterMapper counterMapper;

    @Override
    @Transactional(rollbackFor = BaseException.class, propagation = Propagation.REQUIRED)
    public String update(CounterRequest counterRequest) {
        log.info("ActionLog started update number :" + counterRequest.getNumber());
        try {
            Counter updateCounter = counterRepo.findById("68022d0be6dd776af31a4f33").orElseThrow(() -> new RuntimeException("Id not founded"));
            updateCounter.setNumber(counterRequest.getNumber());
            updateCounter.setUrl(counterRequest.getUrl());
            counterRepo.save(updateCounter);
        } catch (Exception e) {
            log.error("ActionLog error happen");
            throw BaseException.unexpected();
        }
        return "process successfully happen";
    }

    @Override
    public List<CounterResponse> getAll() {
        log.info("ActionLog started getById ");
        List<Counter> counter = counterRepo.findAll();
        log.info("ActionLog end getById ");
        return counter.stream().map(counterMapper::mapToResponse).toList();
    }

    @Override
    public void postOneTime(CounterRequest counterRequest) {
        Counter counter = counterMapper.mapToEntity(counterRequest);
        counterRepo.save(counter);
    }
}
