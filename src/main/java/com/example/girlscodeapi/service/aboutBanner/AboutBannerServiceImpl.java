package com.example.girlscodeapi.service.aboutBanner;

import com.example.girlscodeapi.constant.AboutBannerConstant;
import com.example.girlscodeapi.exception.BaseException;
import com.example.girlscodeapi.mapper.AboutBannerMapper;
import com.example.girlscodeapi.model.dto.request.AboutBannerRequest;
import com.example.girlscodeapi.model.dto.response.AboutBannerResponse;
import com.example.girlscodeapi.model.entity.AboutBanner;
import com.example.girlscodeapi.model.repo.AboutBannerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Service
public class AboutBannerServiceImpl implements AboutBannerService{
    private final AboutBannerRepository repository;
    private final AboutBannerMapper mapper;
    @Override
    public AboutBannerResponse add(AboutBannerRequest request) {
        String fileName= UUID.randomUUID()+"_"+request.getFile().getOriginalFilename();
        Path filePath= Paths.get(AboutBannerConstant.IMAGE_DIR+fileName);
        try {
            Files.createDirectories(filePath.getParent());
            Files.write(filePath,request.getFile().getBytes());
        } catch (IOException e) {
            log.warn("image could not be loaded");
        }
        AboutBanner aboutBanner=mapper.mapToEntity(request);
        aboutBanner.setImageUrl(AboutBannerConstant.IMAGE_DIR+fileName);
        repository.save(aboutBanner);
        log.info("Action log about banner object {}",aboutBanner);
        return mapper.mapToResponse(aboutBanner);
    }

    @Override
    public AboutBannerResponse getById() {

        AboutBanner aboutBanner=repository.findById("6812167d7abed71b9f8d0246").orElseThrow(()->BaseException.notFound(AboutBanner.class.getSimpleName(),"id","6812167d7abed71b9f8d0246"));
        return mapper.mapToResponse(aboutBanner);
    }

    @Override
    public AboutBannerResponse update( AboutBannerRequest request) {
        AboutBanner aboutBanner=repository.findById("6812167d7abed71b9f8d0246").orElseThrow(()->BaseException.notFound(AboutBanner.class.getSimpleName(),"id","6812167d7abed71b9f8d0246"));
        if (aboutBanner !=null){
            String oldFileName=Paths.get(aboutBanner.getImageUrl()).getFileName().toString();
            Path oldPath=Paths.get(AboutBannerConstant.IMAGE_DIR+oldFileName);
            try {
                Files.deleteIfExists(oldPath);
            } catch (IOException e) {
                log.warn("image could not be deleted");
            }
        }
        String newFileName=UUID.randomUUID()+"_"+request.getFile().getOriginalFilename();
        Path   newPath=Paths.get(AboutBannerConstant.IMAGE_DIR+newFileName);
        try {
            Files.createDirectories(newPath.getParent());
            Files.write(newPath,request.getFile().getBytes());
        } catch (IOException e) {
            log.warn("new image could not be loaded");
        }
        AboutBanner aboutBanner1=mapper.map(request,aboutBanner);
        aboutBanner1.setImageUrl(AboutBannerConstant.IMAGE_DIR+newFileName);
        repository.save(aboutBanner1);
        return mapper.mapToResponse(aboutBanner1);
    }


}
