package com.example.girlscodeapi.service.successStory;

import com.example.girlscodeapi.exception.BaseException;
import com.example.girlscodeapi.mapper.SuccessStoryMapper;
import com.example.girlscodeapi.model.dto.request.SuccessStoryRequest;
import com.example.girlscodeapi.model.dto.request.SuccessStoryRequestForUpdate;
import com.example.girlscodeapi.model.dto.response.SuccessStoryResponse;
import com.example.girlscodeapi.model.entity.SuccessStory;
import com.example.girlscodeapi.model.repo.SuccessStoryRepo;
import com.example.girlscodeapi.util.successStory.SuccessStoryStorageUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SuccessStoryServiceImpl implements SuccessStoryService {
    private final SuccessStoryRepo successStoryRepo;
    private final SuccessStoryMapper successStoryMapper;
    private final SuccessStoryStorageUtil successStoryStorageUtil;

    @Override
    public String post(SuccessStoryRequest successStoryRequest) {
        log.info("ActionLog started post multipartFile :" + successStoryRequest.getMultipartFile());
        try {
            String url = successStoryStorageUtil.saveFile(successStoryRequest.getMultipartFile());
            SuccessStory successStory = successStoryMapper.mapToEntity(successStoryRequest, url);
            successStoryRepo.save(successStory);
        } catch (Exception e) {
            log.error("ActionLog error happen");
            throw BaseException.unexpected();
        }
        log.info("ActionLog end post multipartFile :" + successStoryRequest.getMultipartFile());
        return "successStory full saved";
    }

    @Override
    public List<SuccessStoryResponse> getAll() {
        log.info("ActionLog started getAll");
        List<SuccessStory> successStories = successStoryRepo.findAll();
        log.info("ActionLog end getAll");
        return successStories.stream().map(successStoryMapper::mapToResponse).toList();
    }

    @Override
    public void update(SuccessStoryRequestForUpdate successStoryRequest, String id, MultipartFile multipartFile) {
        log.info("ActionLog started update fullName :" + successStoryRequest.getFullNameAZ());
        SuccessStory successStory = findById(id);
        try {
            if (multipartFile != null && !multipartFile.isEmpty()) {
                successStoryStorageUtil.removeFileIfExists(successStory.getUrl());
                String url = successStoryStorageUtil.saveFile(multipartFile);
                successStory.setUrl(url);
            }
            if (successStoryRequest.getDateTime() != null) {
                successStory.setDateTime(successStoryRequest.getDateTime());
            }
            if (successStoryRequest.getFullNameAZ() != null && !successStoryRequest.getFullNameAZ().isEmpty()) {
                successStory.setFullNameAZ(successStoryRequest.getFullNameAZ());
            }
            if (successStoryRequest.getFullNameENG() != null && !successStoryRequest.getFullNameENG().isEmpty()) {
                successStory.setFullNameENG(successStoryRequest.getFullNameENG());
            }
            if (successStoryRequest.getTextAZ() != null && !successStoryRequest.getTextAZ().isEmpty()) {
                successStory.setTextAZ(successStoryRequest.getTextAZ());
            }
            if (successStoryRequest.getTextENG() != null && !successStoryRequest.getTextENG().isEmpty()) {
                successStory.setTextENG(successStoryRequest.getTextENG());
            }
            if (successStoryRequest.getTitleAZ() != null && !successStoryRequest.getTitleAZ().isEmpty()) {
                successStory.setTitleAZ(successStoryRequest.getTitleAZ());
            }
            if (successStoryRequest.getTitleENG() != null && !successStoryRequest.getTitleENG().isEmpty()) {
                successStory.setTitleENG(successStoryRequest.getTitleENG());
            }
            if (successStoryRequest.getRow() != null) {
                successStory.setRow(successStoryRequest.getRow());
            }
            successStoryRepo.save(successStory);
        } catch (Exception e) {
            log.error("ActionLog error");
            throw BaseException.unexpected();
        }
        log.info("ActionLog end update fullName : " + successStoryRequest.getFullNameAZ());
    }

    @Override
    public SuccessStory findById(String id) {
        log.info("ActionLog started findById id :" + id);
        return successStoryRepo.findById(id).orElseThrow(() -> BaseException.notFound(SuccessStory.class.getSimpleName(), "id", id.toString()));
    }

    @Override
    public void remove(String id) {
        log.info("ActionLog started remove id :" + id);
        SuccessStory successStory = findById(id);
        try {
            successStoryStorageUtil.removeFileIfExists(successStory.getUrl());
            successStoryRepo.deleteById(id);
        } catch (Exception e) {
            log.warn("ActionLog error ");
            throw BaseException.unexpected();
        }
        log.info("ActionLog end remove id :" + id);
    }
}
