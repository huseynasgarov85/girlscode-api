package com.example.girlscodeapi.service.becomeMember.left;

import com.example.girlscodeapi.exception.BaseException;
import com.example.girlscodeapi.mapper.BecomeMemberLeftMapper;
import com.example.girlscodeapi.model.dto.request.BecomeMemberLeftRequest;
import com.example.girlscodeapi.model.dto.request.BecomeMemberLeftRequestForUpdate;
import com.example.girlscodeapi.model.dto.response.BecomeMemberLeftResponse;
import com.example.girlscodeapi.model.entity.BecomeMemberLeft;
import com.example.girlscodeapi.model.repo.BecomeMemberLeftRepo;
import com.example.girlscodeapi.util.becomeMemberLeft.BecomeMemberLeftStorageUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BecomeMemberLeftServiceImpl implements BecomeMemberLeftService {
    private final BecomeMemberLeftRepo becomeMemberLeftRepo;
    private final BecomeMemberLeftMapper mapper;
    private final BecomeMemberLeftStorageUtil becomeMemberLeftStorageUtil;

    @Override
    public String post(BecomeMemberLeftRequest request) {
        log.info("ActionLog started post request");
        String url = becomeMemberLeftStorageUtil.saveFile(request.getMultipartFile());
        BecomeMemberLeft becomeMemberLeft;
        try {
            becomeMemberLeft = mapper.mapToEntity(request, url);
            becomeMemberLeftRepo.save(becomeMemberLeft);
        } catch (Exception e) {
            log.error("ActionLog error ");
            throw BaseException.unexpected();
        }
        log.info("ActionLog end post request");
        return becomeMemberLeft.getId();
    }

    @Override
    public List<BecomeMemberLeftResponse> getAll() {
        log.info("ActionLog started getAll");
        List<BecomeMemberLeft> becomeMemberLefts = becomeMemberLeftRepo.findAll();
        log.info("ActionLog end getAll");
        return becomeMemberLefts.stream().map(mapper::mapToResponse).collect(Collectors.toList());
    }

    @Override
    public void update(BecomeMemberLeftRequestForUpdate request, MultipartFile multipartFile) {
        log.info("ActionLog started");
        BecomeMemberLeft becomeMemberLeft = becomeMemberLeftRepo.findById("6813c796cb98ef2fe06dbe2a").orElseThrow(() -> new RuntimeException("not found id"));
        try {
            if (request.getTextAZ() != null && !request.getTextAZ().isEmpty()) {
                becomeMemberLeft.setTextAZ(request.getTextAZ());
            }
            if (request.getTextENG() != null && !request.getTextENG().isEmpty()) {
                becomeMemberLeft.setTextENG(request.getTextENG());
            }
            if (request.getTitleAZ() != null && !request.getTitleAZ().isEmpty()) {
                becomeMemberLeft.setTitleAZ(request.getTitleAZ());
            }
            if (request.getTitleENG() != null && !request.getTitleENG().isEmpty()) {
                becomeMemberLeft.setTitleENG(request.getTitleENG());
            }
            if (multipartFile != null && !multipartFile.isEmpty()) {
                becomeMemberLeftStorageUtil.removeFileIfExists(becomeMemberLeft.getUrl());
                String url = becomeMemberLeftStorageUtil.saveFile(multipartFile);
                becomeMemberLeft.setUrl(url);
            }
            becomeMemberLeftRepo.save(becomeMemberLeft);
        } catch (Exception e) {
            log.error("ActionLog error");
            throw BaseException.unexpected();
        }
        log.info("ActionLog end :");
    }
}
