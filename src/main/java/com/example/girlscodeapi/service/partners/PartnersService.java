package com.example.girlscodeapi.service.partners;

import com.example.girlscodeapi.model.entity.Partners;
import com.example.girlscodeapi.model.dto.request.PartnersRequest;
import com.example.girlscodeapi.model.dto.response.PartnersResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PartnersService {
    String post(PartnersRequest partnersRequest);

    List<PartnersResponse> getAllLogos();

    void update(String id, MultipartFile multipartFile);

    Partners findById(String id);

    void remove(String id);
}
