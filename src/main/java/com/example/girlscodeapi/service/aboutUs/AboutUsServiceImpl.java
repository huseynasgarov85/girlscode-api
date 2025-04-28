package com.example.girlscodeapi.service.aboutUs;

import com.example.girlscodeapi.exception.BaseException;
import com.example.girlscodeapi.mapper.AboutUsMapper;
import com.example.girlscodeapi.model.dto.request.AboutUsRequest;
import com.example.girlscodeapi.model.dto.request.AboutUsUpdate;
import com.example.girlscodeapi.model.dto.response.AboutUsResponse;
import com.example.girlscodeapi.model.entity.AboutUs;
import com.example.girlscodeapi.model.repo.AboutUsRepo;
import com.example.girlscodeapi.util.aboutUs.AboutUsStorageUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AboutUsServiceImpl implements AboutUsService {
    private final AboutUsRepo aboutUsRepo;
    private final AboutUsMapper aboutUsMapper;
    private final AboutUsStorageUtil aboutUsStorageUtil;

    @Override
    public AboutUsResponse post(AboutUsRequest aboutUsRequest) {
        log.info("ActionLog started post aboutUsRequest photo :" + aboutUsRequest.getMultipartFile());
        AboutUs aboutUs;
        try {
            String url = aboutUsStorageUtil.saveFile(aboutUsRequest.getMultipartFile());
            aboutUs = aboutUsMapper.mapToEntity(aboutUsRequest, url);
            aboutUsRepo.save(aboutUs);
        } catch (Exception e) {
            log.error("ActionLog error ");
            throw BaseException.unexpected();
        }
        log.info("ActionLog end post aboutUsRequest photo :" + aboutUsRequest.getMultipartFile());
        return aboutUsMapper.mapToResponse(aboutUs);
    }

    @Override
    public void update(AboutUsUpdate aboutUsUpdate, MultipartFile multipartFile) {
        log.info("ActionLog started update photo :" + multipartFile);
        AboutUs aboutUs = aboutUsRepo.findById("680a4ff926ecf67c6d171ee7").orElseThrow(() -> new RuntimeException("id not founded"));
        try {
            if (multipartFile != null && !multipartFile.isEmpty()) {
                aboutUsStorageUtil.removeFileIfExists(aboutUs.getUrl());
                String url = aboutUsStorageUtil.saveFile(multipartFile);
                aboutUs.setUrl(url);
            }
            if (aboutUsUpdate.getTextENG() != null && !aboutUsUpdate.getTextENG().isEmpty()) {
                aboutUs.setTextENG(aboutUsUpdate.getTextENG());
            }
            if (aboutUsUpdate.getTextAZ() != null && !aboutUsUpdate.getTextAZ().isEmpty()) {
                aboutUs.setTextAZ(aboutUsUpdate.getTextAZ());
            }
            aboutUsRepo.save(aboutUs);
        } catch (Exception e) {
            log.error("ActionLog error ");
            throw BaseException.unexpected();
        }
        log.info("ActionLog end update photo :" + multipartFile);
    }

    @Override
    public List<AboutUsResponse> getAll() {
        log.info("ActionLog started getAll");
        List<AboutUs> aboutUses = aboutUsRepo.findAll();
        log.info("ActionLog end getAll");
        return aboutUses.stream().map(aboutUsMapper::mapToResponse).toList();
    }
}
