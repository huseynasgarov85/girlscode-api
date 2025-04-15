package com.example.girlscodeapi.service.Impl;

import com.example.girlscodeapi.constant.UploadFolderConstant;
import com.example.girlscodeapi.exception.BaseException;
import com.example.girlscodeapi.mapper.SliderMapper;
import com.example.girlscodeapi.model.dto.StorageDto;
import com.example.girlscodeapi.model.entity.Slider;
import com.example.girlscodeapi.model.repo.SliderRepo;
import com.example.girlscodeapi.model.response.SliderResponse;
import com.example.girlscodeapi.service.SliderService;
import com.example.girlscodeapi.util.StorageUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
@Slf4j
public class SliderServiceImpl implements SliderService {
    private final SliderRepo sliderRepo;
    private final SliderMapper sliderMapper;
    private final StorageUtil storageUtil;

    @Override
    public String add(List<MultipartFile> multipartFile) {
        log.info("ActionLog add started multipartFile " + multipartFile);
        try {
            LinkedList<Slider> sliders = new LinkedList<>();
            for (MultipartFile photo : multipartFile) {
                Slider slider = new Slider();
                String fileUrl = storageUtil.saveFile(photo);
                slider.setUrl(fileUrl);
                sliders.add(slider);
            }
            sliderRepo.saveAll(sliders);
        } catch (Exception e) {
            log.info("ActionLog error");
            throw BaseException.unexpected();
        }
        log.info("ActionLog add end multipartFile " + multipartFile);
        return "photos uploaded";
    }

    @Override
    public List<SliderResponse> getAll() {
        log.info("ActionLog getAll started");
        List<Slider> slider = sliderRepo.findAll();
        List<SliderResponse> sliderResponses = slider.stream().map(sliderMapper::mapToResponse).toList();
        log.info("ActionLog getAll end");
        return sliderResponses;
    }

    @Override
    public void update(String id, MultipartFile multipartFile) {
        log.info("ActionLog start update id :" + id);
        Slider slider = findById(id);
        try {
            storageUtil.removeFileIfExists(slider.getUrl());
            String newFileUrl = storageUtil.saveFile(multipartFile);
            slider.setUrl(newFileUrl);
            sliderRepo.save(slider);
        } catch (Exception e) {
            log.error("ActionLog error happen");
        }
        log.info("ActionLog end update id :" + id);
    }

    @Override
    public void remove(String id) {
        log.info("ActionLog started remove id :" + id);
        try {
            Slider slider = findById(id);
            storageUtil.removeFileIfExists(slider.getUrl());
            sliderRepo.deleteById(id);
        } catch (Exception e) {
            log.error("ActionLog error ");
            throw new RuntimeException();
        }
        log.info("ActionLog end remove id :" + id);
    }

    @Override
    public Slider findById(String id) {
        return sliderRepo.findById(id).orElseThrow(
                () -> BaseException.notFound(Slider.class.getSimpleName(), "id", id.toString())
        );
    }

}

