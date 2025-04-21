package com.example.girlscodeapi.service.fAQ;


import com.example.girlscodeapi.exception.BaseException;
import com.example.girlscodeapi.mapper.FAQMapper;
import com.example.girlscodeapi.model.dto.request.FAQRequest;
import com.example.girlscodeapi.model.dto.response.FAQResponse;

import com.example.girlscodeapi.model.entity.FAQ;
import com.example.girlscodeapi.model.repo.FAQRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FAQServiceImpl implements FAQService {
    private final FAQRepository repository;
    private final FAQMapper mapper;

    @Override
    public FAQResponse add(FAQRequest request) {
        FAQ fAQ = mapper.map(request);
        repository.save(fAQ);
        return mapper.mapToResponse(fAQ);
    }

    @Override
    public FAQResponse getById(String id) {
        FAQ fAQ = repository.findById(id).orElseThrow(() -> BaseException.notFound(FAQ.class.getSimpleName(), "id", id));
        return mapper.mapToResponse(fAQ);
    }

    @Override
    public FAQResponse update(FAQRequest request) {
        FAQ fAQ = repository.findById(request.getId()).orElseThrow(() -> BaseException.notFound(FAQ.class.getSimpleName(), "id", request.getId()));
        FAQ faq1 = mapper.map(request, fAQ);
        repository.save(faq1);
        return mapper.mapToResponse(faq1);
    }

    @Override
    public void delete(String id) {
    FAQ fAQ=repository.findById(id).orElseThrow(()->BaseException.notFound(FAQ.class.getSimpleName(),"id",id));
    repository.deleteById(id);

    }

    @Override
    public List<FAQResponse> getAll() {
        List<FAQ> fAQList=repository.findAll();
        return fAQList.stream()
                .map(mapper :: mapToResponse)
                .collect(Collectors.toList());
    }
}
