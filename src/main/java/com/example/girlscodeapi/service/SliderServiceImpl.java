package com.example.girlscodeapi.service;

import com.example.girlscodeapi.exception.BaseException;
import com.example.girlscodeapi.mapper.SliderMapper;
import com.example.girlscodeapi.model.dto.StorageDto;
import com.example.girlscodeapi.model.entity.Slider;
import com.example.girlscodeapi.model.repo.SliderRepo;
import com.example.girlscodeapi.model.response.SliderResponse;
import com.example.girlscodeapi.util.StorageUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class SliderServiceImpl implements SliderService {
    private final SliderRepo sliderRepo;
    private final SliderMapper sliderMapper;
    private final StorageUtil storageUtil;

    @Override
    public String add(MultipartFile multipartFile) {
        log.info("ActionLog add started multipartFile " + multipartFile);
        Slider slider = new Slider();
        try {
            StorageDto storageDto = storageUtil.getStorageDto(multipartFile);
            slider.setUrl(storageDto.getUrl());
            sliderRepo.save(slider);
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

        Slider slider = sliderRepo.findById(id).orElseThrow(
                () -> BaseException.notFound(Slider.class.getSimpleName(), "id", id.toString())
        );
        try {
            StorageDto storageDto = storageUtil.getStorageDto(multipartFile);
            slider.setUrl(storageDto.getUrl());
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
            sliderRepo.deleteById(id);
        } catch (Exception e) {
            log.error("ActionLog error ");
            throw new RuntimeException();
        }
        log.info("ActionLog end remove id :" + id);
    }


//    private void deleteOldPhotoIfExists(String oldUrl) {
//        if (oldUrl == null || oldUrl.isBlank()) {
//            log.warn("Old photo URL is null or empty. Skip deleting.");
//            return;
//        }
//
//        try {
//            String filePath = getPathFromUrl(oldUrl);
//            File oldFile = new File(filePath);
//
//            if (oldFile.exists()) {
//                boolean deleted = oldFile.delete();
//                if (deleted) {
//                    log.info("ActionLog: Old photo deleted: {}", oldFile.getName());
//                } else {
//                    log.warn("ActionLog: Failed to delete old photo: {}", oldFile.getName());
//                }
//            } else {
//                log.warn("ActionLog: Old file does not exist at path: {}", filePath);
//            }
//
//        } catch (Exception e) {
//            log.error("ActionLog: Error occurred while deleting old photo: {}", oldUrl, e);
//        }
//    }
//
//    private String getPathFromUrl(String url) {
//        String fileName = Paths.get(url).getFileName().toString(); // "photo.jpg"
//        String uploadBasePath = "C:/your-project-root/uploads/"; // <-- düzəliş et
//
//        return uploadBasePath + fileName;
//    }






}
