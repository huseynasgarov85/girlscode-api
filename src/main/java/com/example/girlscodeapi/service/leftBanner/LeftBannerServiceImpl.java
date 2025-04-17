package com.example.girlscodeapi.service.leftBanner;

import com.example.girlscodeapi.constant.LeftBannerConstant;
import com.example.girlscodeapi.exception.BaseException;
import com.example.girlscodeapi.mapper.LeftBannerMapper;
import com.example.girlscodeapi.model.dto.response.LeftBannerResponse;
import com.example.girlscodeapi.model.entity.LeftBanner;
import com.example.girlscodeapi.model.repo.LeftBannerRepository;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@Data
@Slf4j
public class LeftBannerServiceImpl implements LeftBannerService  {
    private final LeftBannerRepository repository;
    private final LeftBannerMapper mapper;


    @Override
    public LeftBannerResponse add(MultipartFile file) {
        String fileName= UUID.randomUUID()+file.getOriginalFilename();
        Path filePath= Paths.get(LeftBannerConstant.BANNER_UPLOAD_DIR+fileName);
        try {
            Files.createDirectories(filePath.getParent());
            Files.write(filePath,file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        LeftBanner leftBanner=new LeftBanner();
        leftBanner.setUrl(LeftBannerConstant.BANNER_UPLOAD_DIR+fileName);
        repository.save(leftBanner);
        return mapper.mapToResponse(leftBanner);
    }

    @Override
    public LeftBannerResponse get() {
        LeftBanner leftBanner=repository.findById("67ff40eef6c53110712f75e0").orElseThrow(()->  BaseException.notFound(LeftBanner.class.getSimpleName(),"id","67ff40eef6c53110712f75e0"));
        return mapper.mapToResponse(leftBanner);
    }

    @Override
    public LeftBannerResponse update( MultipartFile file) {
        LeftBanner leftBanner=repository.findById("67ff40eef6c53110712f75e0").orElseThrow(()-> BaseException.notFound(LeftBanner.class.getSimpleName(),"id","67ff40eef6c53110712f75e0"));
        if (leftBanner.getUrl()!=null){
            String oldFileName=Paths.get(leftBanner.getUrl()).getFileName().toString();
            Path oldPath = Paths.get(LeftBannerConstant.BANNER_IMAGE_PATH).resolve(oldFileName);
            try {
                Files.deleteIfExists(oldPath);
            } catch (IOException e) {
                System.out.println("Old image could not be deleted: " + e.getMessage());
            }
        }

        String newFileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path newPath = Paths.get(LeftBannerConstant.BANNER_IMAGE_PATH).resolve(newFileName);

        try {
            Files.createDirectories(newPath.getParent());
            Files.write(newPath, file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("File could not be saved: " + e.getMessage());
        }
        leftBanner.setUrl(LeftBannerConstant.BANNER_UPLOAD_DIR+ newFileName);
        repository.save(leftBanner);
        return  mapper.mapToResponse(leftBanner);

    }
}
