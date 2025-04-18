package com.example.girlscodeapi.service.fAQ;


import com.example.girlscodeapi.model.dto.request.FAQRequest;
import com.example.girlscodeapi.model.dto.response.FAQResponse;


import java.util.List;


public interface FAQService {
FAQResponse add(FAQRequest request);
FAQResponse getById(String id);
FAQResponse update(FAQRequest request);
void delete(String id);
List<FAQResponse> getAll();
}
