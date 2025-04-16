package com.example.girlscodeapi.service.partners;

import com.example.girlscodeapi.exception.BaseException;
import com.example.girlscodeapi.mapper.PartnersMapper;
import com.example.girlscodeapi.model.entity.Partners;
import com.example.girlscodeapi.model.repo.PartnersRepo;
import com.example.girlscodeapi.model.request.PartnersRequest;
import com.example.girlscodeapi.model.response.PartnersResponse;
import com.example.girlscodeapi.util.partners.PartnersStorageUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PartnersServiceImpl implements PartnersService {
    private final PartnersRepo partnersRepo;
    private final PartnersStorageUtil partnersStorageUtil;
    private final PartnersMapper partnersMapper;

    @Override
    public String post(PartnersRequest partnersRequest) {
        log.info("ActionLog started post partnersRequest : " + partnersRequest);
        List<Partners> partnersList = new ArrayList<>();
        try {
            for (MultipartFile photo : partnersRequest.getMultipartFiles()) {
                Partners partners = new Partners();
                String fileUrl = partnersStorageUtil.saveFile(photo);
                partners.setUrl(fileUrl);
                partnersList.add(partners);
            }
            partnersRepo.saveAll(partnersList);
        } catch (Exception e) {
            log.error("ActionLog error ");
            throw BaseException.unexpected();
        }
        log.info("ActionLog end post partnersRequest : " + partnersRequest);
        return "Partners logo uploaded";
    }

    @Override
    public List<PartnersResponse> getAllLogos() {
        log.info("ActionLog started getAllLogos");
        List<Partners> partners = partnersRepo.findAll();
        log.info("ActionLog end getAllLogos");
        return partners.stream().map(partnersMapper::mapToResponse).toList();
    }

    @Override
    public void update(String id, MultipartFile multipartFile) {
        log.info("ActionLog started update id :" + id);
        Partners partners = findById(id);
        try {
            partnersStorageUtil.removeFileIfExists(partners.getUrl());
            String newUrl = partnersStorageUtil.saveFile(multipartFile);
            partners.setUrl(newUrl);
            partnersRepo.save(partners);
        } catch (Exception e) {
            log.error("ActionLog error occurred " + e.getMessage());
            throw BaseException.unexpected();
        }
        log.info("ActionLog end update id :" + id);
    }

    @Override
    public Partners findById(String id) {
        log.info("ActionLog started findById id :" + id);
        return partnersRepo.findById(id).orElseThrow(
                () -> BaseException.notFound(Partners.class.getSimpleName(), "id", id.toString())
        );
    }

    @Override
    public void remove(String id) {
        log.info("ActionLog started remove id : " + id);
        Partners partners = findById(id);
        try {
            partnersStorageUtil.removeFileIfExists(partners.getUrl());
            partnersRepo.deleteById(id);
        } catch (Exception e) {
            log.error("error message " + e.getMessage());
            throw BaseException.unexpected();
        }
        log.info("ActionLog end remove id : " + id);
    }

}
