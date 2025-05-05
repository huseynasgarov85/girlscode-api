package com.example.girlscodeapi.service.becomeMember.form;

import com.example.girlscodeapi.exception.BaseException;
import com.example.girlscodeapi.mapper.FormMemberMapper;
import com.example.girlscodeapi.model.dto.request.FormMemberRequest;
import com.example.girlscodeapi.model.dto.request.FormMemberRequestUpdate;
import com.example.girlscodeapi.model.dto.response.FormMemberResponse;
import com.example.girlscodeapi.model.entity.FormMember;
import com.example.girlscodeapi.model.repo.FormMemberRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class FormMemberServiceImpl implements FormMemberService {
    private final FormMemberRepo formMemberRepo;
    private final FormMemberMapper formMemberMapper;

    @Override
    public void post(FormMemberRequest formMemberRequest) {
        log.info("ActionLog started post fullName : " + formMemberRequest.getFullName());
        FormMember formMember = formMemberMapper.mapToEntity(formMemberRequest);
        try {
            formMemberRepo.save(formMember);
        } catch (Exception e) {
            log.error("ActionLog error");
            throw BaseException.unexpected();
        }
        log.info("ActionLog end post fullName : " + formMemberRequest.getFullName());
    }

    @Override
    public List<FormMemberResponse> getAll() {
        log.info("ActionLog started getAll");
        List<FormMember> formMembers = formMemberRepo.findAll();
        log.info("ActionLog end getAll");
        return formMembers.stream().map(formMemberMapper::mapToResponse).collect(Collectors.toList());
    }

    @Override
    public void update(String id, FormMemberRequestUpdate requestUpdate) {
        log.info("ActionLog started formMemberID :" + id);
        FormMember formMember = formMemberRepo.findById(id).orElseThrow(() -> BaseException.notFound(FormMember.class.getSimpleName(), "id", id.toString()));
        try {
            if (requestUpdate.getFullName() != null && !requestUpdate.getFullName().isEmpty()) {
                formMember.setFullName(requestUpdate.getFullName());
            }
            if (requestUpdate.getMail() != null && !requestUpdate.getMail().isEmpty()) {
                formMember.setMail(requestUpdate.getMail());
            }
            if (requestUpdate.getMessage() != null && !requestUpdate.getMessage().isEmpty()) {
                formMember.setMessage(requestUpdate.getMessage());
            }
            if (requestUpdate.getMobileNumber() != null && !requestUpdate.getMobileNumber().isEmpty()) {
                formMember.setMobileNumber(requestUpdate.getMobileNumber());
            }
            formMemberRepo.save(formMember);
        } catch (Exception e) {
            log.error("ActionLog error");
            throw BaseException.unexpected();
        }
        log.info("ActionLog end formMemberID :" + id);
    }

    @Override
    public void remove(String id) {
        log.info("ActionLog started remove ID :" + id);
        try {
            formMemberRepo.deleteById(id);
        } catch (Exception e) {
            log.error("ActionLog error");
            throw BaseException.unexpected();
        }
        log.info("ActionLog end remove ID :" + id);
    }
}
