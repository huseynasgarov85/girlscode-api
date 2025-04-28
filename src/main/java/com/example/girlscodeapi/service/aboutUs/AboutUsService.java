package com.example.girlscodeapi.service.aboutUs;

import com.example.girlscodeapi.model.dto.request.AboutUsRequest;
import com.example.girlscodeapi.model.dto.request.AboutUsUpdate;
import com.example.girlscodeapi.model.dto.response.AboutUsResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AboutUsService {

    AboutUsResponse post(AboutUsRequest aboutUsRequest);

    void update(AboutUsUpdate aboutUsUpdate, MultipartFile multipartFile);

    List<AboutUsResponse> getAll();
}
