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
    public String update(CounterRequest counterRequest, String id) {
        log.info("ActionLog started update number :" + counterRequest.getNumber());
        Counter counter = new Counter();
        if (id == null) {
            counter.setNumber(counterRequest.getNumber());
            try {
                counterRepo.save(counter);
            } catch (Exception e) {
                log.error("ActionLog error happen");
                throw BaseException.unexpected();
            }
        } else {
            Counter updateCounter = counterRepo.findById(id).orElseThrow(() -> BaseException.notFound(Counter.class.getSimpleName(), "id", id.toString()));
            updateCounter.setNumber(counterRequest.getNumber());
            try {
                counterRepo.save(updateCounter);
            } catch (Exception e) {
                log.error("ActionLog error happen");
                throw BaseException.unexpected();
            }
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
}
