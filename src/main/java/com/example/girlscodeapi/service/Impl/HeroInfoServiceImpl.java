package com.example.girlscodeapi.service.Impl;

import com.example.girlscodeapi.mapper.HeroInfoMapper;
import com.example.girlscodeapi.model.dto.request.HeroInfoRequest;
import com.example.girlscodeapi.model.dto.request.StorageDto;
import com.example.girlscodeapi.model.dto.response.HeroInfoResponse;
import com.example.girlscodeapi.model.entity.HeroInfo;
import com.example.girlscodeapi.model.entity.Slider;
import com.example.girlscodeapi.repository.HeroInfoRepository;
import com.example.girlscodeapi.repository.SliderRepo;
import com.example.girlscodeapi.service.HeroInfoService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.plaf.SliderUI;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
@Data
@Slf4j
public class HeroInfoServiceImpl implements HeroInfoService {
    private final HeroInfoRepository heroInfoRepository;
    private final HeroInfoMapper heroInfoMapper;
    private final SliderRepo sliderRepo;

    private static final String UPLOAD_FOLDER_PATH = "C:/Users/LENOVO/Desktop/uploads"; // local klasör yolu

    @Override
    public HeroInfoResponse addForTest(HeroInfoRequest heroInfoRequest) {
        HeroInfo heroInfo = heroInfoMapper.mapToEntity(heroInfoRequest);
        heroInfoRepository.save(heroInfo);
        HeroInfoResponse heroInfoResponse = heroInfoMapper.mapToResponse(heroInfo);
        return heroInfoResponse;
    }

    @Override
    public String add(MultipartFile multipartFile) {
        log.info("ActionLog add started multipartFile " + multipartFile);
        Slider slider = new Slider();
        try {
            StorageDto storageDto = getStorageDto(multipartFile);
            slider.setUrl(storageDto.getUrl());
            sliderRepo.save(slider);
        } catch (Exception e) {
            log.info("ActionLog error");
        }
        log.info("ActionLog add end multipartFile " + multipartFile);
        return "file uploaded successfully";
    }

    private StorageDto getStorageDto(MultipartFile multipartFile) {
        log.info("photo: " + multipartFile.getOriginalFilename());
        try {
            String fileName = UUID.randomUUID() + "_" + multipartFile.getOriginalFilename();

            Path folderPath = Paths.get(UPLOAD_FOLDER_PATH);

            if (!Files.exists(folderPath)) {
                Files.createDirectories(folderPath);
            }

            Path filePath = folderPath.resolve(fileName);
            Files.copy(multipartFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            String fileUrl = filePath.toUri().toString();

            log.info("Saved file to local path: " + fileUrl);

            return StorageDto.builder()
                    .url(fileUrl)
                    .build();

        } catch (IOException e) {
            log.error("Error occurred while saving file", e);
        }
        return null;
    }


}
