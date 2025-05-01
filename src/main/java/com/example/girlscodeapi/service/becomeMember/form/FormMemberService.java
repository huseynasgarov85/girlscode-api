package com.example.girlscodeapi.service.becomeMember.form;

import com.example.girlscodeapi.model.dto.request.FormMemberRequest;
import com.example.girlscodeapi.model.dto.request.FormMemberRequestUpdate;
import com.example.girlscodeapi.model.dto.response.FormMemberResponse;

import java.util.List;

public interface FormMemberService {
    void post(FormMemberRequest formMemberRequest);

    List<FormMemberResponse> getAll();

    void update(String id, FormMemberRequestUpdate requestUpdate);

    void remove(String id);
}
