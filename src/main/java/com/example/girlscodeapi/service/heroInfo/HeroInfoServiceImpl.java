package com.example.girlscodeapi.service.heroInfo;

import com.example.girlscodeapi.constant.HeroInfoConstant;
import com.example.girlscodeapi.exception.BaseException;
import com.example.girlscodeapi.mapper.HeroInfoMapper;

import com.example.girlscodeapi.model.dto.request.HeroInfoRequest;
import com.example.girlscodeapi.model.dto.response.HeroInfoResponse;
import com.example.girlscodeapi.model.entity.HeroInfo;
import com.example.girlscodeapi.model.repo.HeroInfoRepository;
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
public class HeroInfoServiceImpl implements HeroInfoService {
    private final HeroInfoRepository heroInfoRepository;
    private final HeroInfoMapper heroInfoMapper;


    @Override
    public HeroInfoResponse add(HeroInfoRequest request) {
        String fileName = UUID.randomUUID() + "_" + request.getFile().getOriginalFilename();
        Path filePath = Paths.get(HeroInfoConstant.UPLOAD_DIR + fileName);
        try {
            Files.createDirectories(filePath.getParent());
            Files.write(filePath, request.getFile().getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        HeroInfo heroInfo = heroInfoMapper.mapToEntity(request);
        heroInfo.setUrl(HeroInfoConstant.UPLOAD_DIR + fileName);
        heroInfoRepository.save(heroInfo);
        return heroInfoMapper.mapToResponse(heroInfo);
    }

    @Override
    public HeroInfoResponse update(HeroInfoRequest request) {
        HeroInfo heroInfo = heroInfoRepository.findById("680f5459de9faa6ea664ae39")
                .orElseThrow(() -> BaseException.notFound(HeroInfo.class.getSimpleName(), "id", "680f5459de9faa6ea664ae39"));

        if (heroInfo!= null) {
            String oldFilename = Paths.get(heroInfo.getUrl()).getFileName().toString();
            Path oldPath = Paths.get(HeroInfoConstant.UPLOAD_DIR+oldFilename);
            try {
                Files.deleteIfExists(oldPath);
            } catch (IOException e) {
                System.out.println("Old image could not be deleted: " + e.getMessage());
            }
        }

        String newFilename = UUID.randomUUID() + "_" + request.getFile().getOriginalFilename();
        Path newPath = Paths.get(HeroInfoConstant.UPLOAD_DIR+newFilename);

        try {
            Files.createDirectories(newPath.getParent());
            Files.write(newPath, request.getFile().getBytes());
        } catch (IOException e) {
            throw new RuntimeException("File could not be saved: " + e.getMessage());
        }
        HeroInfo heroInfo1=heroInfoMapper.map(request,heroInfo);
        heroInfo1.setUrl(HeroInfoConstant.UPLOAD_DIR + newFilename);
        heroInfoRepository.save(heroInfo1);
        return heroInfoMapper.mapToResponse(heroInfo1);
    }

    @Override
    public HeroInfoResponse get() {
        HeroInfo heroInfo = heroInfoRepository.findById("680f5459de9faa6ea664ae39").orElseThrow(() -> BaseException.notFound(HeroInfo.class.getSimpleName(), "id", "67fcd32d4b04077e46668e5d"));
        return heroInfoMapper.mapToResponse(heroInfo);
    }

}
