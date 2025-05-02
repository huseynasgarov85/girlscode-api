package com.example.girlscodeapi.service.becomeMember.left;

import com.example.girlscodeapi.model.dto.request.BecomeMemberLeftRequest;
import com.example.girlscodeapi.model.dto.request.BecomeMemberLeftRequestForUpdate;
import com.example.girlscodeapi.model.dto.response.BecomeMemberLeftResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BecomeMemberLeftService {

    String post(BecomeMemberLeftRequest request);

    List<BecomeMemberLeftResponse> getAll();

    void update(BecomeMemberLeftRequestForUpdate request, MultipartFile multipartFile);
}
