package com.example.girlscodeapi.service.successStory;

import com.example.girlscodeapi.model.dto.request.SuccessStoryRequest;
import com.example.girlscodeapi.model.dto.request.SuccessStoryRequestForUpdate;
import com.example.girlscodeapi.model.dto.response.SuccessStoryResponse;
import com.example.girlscodeapi.model.entity.SuccessStory;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SuccessStoryService {

    String post(SuccessStoryRequest successStoryRequest);

    List<SuccessStoryResponse> getAll();

    void update(SuccessStoryRequestForUpdate successStoryRequest, String id, MultipartFile multipartFile);

    SuccessStory findById(String id);

    void remove(String id);
}
