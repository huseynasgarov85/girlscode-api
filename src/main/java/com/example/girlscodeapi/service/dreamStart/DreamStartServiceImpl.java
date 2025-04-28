package com.example.girlscodeapi.service.dreamStart;

import com.example.girlscodeapi.constant.DreamStartConstant;
import com.example.girlscodeapi.exception.BaseException;
import com.example.girlscodeapi.mapper.DreamStartMapper;
import com.example.girlscodeapi.model.dto.request.DreamStartRequest;
import com.example.girlscodeapi.model.dto.response.DreamStartResponse;
import com.example.girlscodeapi.model.entity.DreamStart;
import com.example.girlscodeapi.model.repo.DreamStartRepository;
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
public class DreamStartServiceImpl implements DreamStartService{
   private final DreamStartRepository repository;
   private final DreamStartMapper mapper;

    @Override
    public DreamStartResponse add(DreamStartRequest request) {
        String fileName= UUID.randomUUID()+"_"+request.getFile().getOriginalFilename();
        Path filePath=Paths.get(DreamStartConstant.IMAGE_DIR+fileName);
        try {
            Files.createDirectories(filePath.getParent());
            Files.write(filePath,request.getFile().getBytes());
        } catch (IOException e) {
            log.warn("image could not be loaded");
        }
        DreamStart dreamStart=mapper.mapToEntity(request);
        dreamStart.setImageUrl(DreamStartConstant.IMAGE_DIR+fileName);
        repository.save(dreamStart);

        return  mapper.mapToResponse(dreamStart);
    }

    @Override
    public DreamStartResponse getById() {
        DreamStart dreamStart =repository.findById("680caf06a8009e26fce987d5").orElseThrow(()-> BaseException.notFound(DreamStart.class.getSimpleName(),"id","680caf06a8009e26fce987d5"));
        return mapper.mapToResponse(dreamStart);
    }

    @Override
    public DreamStartResponse update( DreamStartRequest request) {
        DreamStart dreamStart=repository.findById("680caf06a8009e26fce987d5").orElseThrow(()->BaseException.notFound(DreamStart.class.getSimpleName(),"id","680caf06a8009e26fce987d5"));
        if (dreamStart!=null){
            String oldFileName=Paths.get(dreamStart.getImageUrl()).getFileName().toString();
            Path oldPath=Paths.get(DreamStartConstant.IMAGE_DIR+oldFileName);

            log.info("old path {}",oldPath);

            try {
                Files.deleteIfExists(oldPath);
            } catch (IOException e) {
                log.warn("image could not be deleted {}",e.getMessage());
            }
        }
        String newFileName=UUID.randomUUID()+"_"+request.getFile().getOriginalFilename();
        Path newPath=Paths.get(DreamStartConstant.IMAGE_DIR+newFileName);
        log.info("new path {}",newPath);
        try {
            Files.createDirectories(newPath.getParent());
            Files.write(newPath,request.getFile().getBytes());
        } catch (IOException e) {
            log.warn("image could not be loaded {}",e.getMessage());
        }
        DreamStart dreamStart1=mapper.map(request,dreamStart);
        dreamStart1.setImageUrl(DreamStartConstant.IMAGE_DIR+newFileName);
        repository.save(dreamStart1);

        return mapper.mapToResponse(dreamStart1);
    }
}
