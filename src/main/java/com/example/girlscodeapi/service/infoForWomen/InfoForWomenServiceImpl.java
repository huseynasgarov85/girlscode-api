package com.example.girlscodeapi.service.infoForWomen;

import com.example.girlscodeapi.constant.InfoForWomenConstant;
import com.example.girlscodeapi.exception.BaseException;
import com.example.girlscodeapi.mapper.InfoForWomenMapper;
import com.example.girlscodeapi.model.dto.request.InfoForWomenRequest;
import com.example.girlscodeapi.model.dto.response.InfoForWomenResponse;
import com.example.girlscodeapi.model.entity.InfoForWomen;
import com.example.girlscodeapi.model.repo.InfoForWomenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class InfoForWomenServiceImpl  implements InfoForWomenService{
    private final InfoForWomenMapper mapper;
   private final InfoForWomenRepository repository;
    @Override
    public InfoForWomenResponse add(InfoForWomenRequest request) {
        String fileName= UUID.randomUUID()+"_"+request.getFile().getOriginalFilename();
        Path filePath= Paths.get(InfoForWomenConstant.IMAGE_DIR+fileName);
        try {
            Files.createDirectories(filePath.getParent());
            Files.write(filePath,request.getFile().getBytes());
        } catch (IOException e) {
            log.warn("image could not be loaded: {}",e.getMessage());
        }

        InfoForWomen infoForWomen=mapper.mapToEntity(request);

        infoForWomen.setImageUrl(InfoForWomenConstant.IMAGE_DIR+fileName);
        repository.save(infoForWomen);
        return mapper.mapToResponse(infoForWomen);
    }

    @Override
    public InfoForWomenResponse getById(String id) {
        InfoForWomen infoForWomen=repository.findById(id).orElseThrow(()-> BaseException.notFound(InfoForWomen.class.getSimpleName(),"id",id));
        return mapper.mapToResponse(infoForWomen);
    }

    @Override
    public InfoForWomenResponse update(String id, InfoForWomenRequest request) {
        InfoForWomen infoForWomen=repository.findById(id).orElseThrow(()->BaseException.notFound(InfoForWomen.class.getSimpleName(),"id",id));
        if (infoForWomen !=null){
            String oldFileName=Paths.get(infoForWomen.getImageUrl()).getFileName().toString();
            Path oldPath=Paths.get(InfoForWomenConstant.IMAGE_DIR+oldFileName);
            try {
                Files.deleteIfExists(oldPath);
            } catch (IOException e) {
                log.warn("old image could not be deleted {}",e.getMessage());
            }
        }
        String newFileName=UUID.randomUUID()+"_"+request.getFile().getOriginalFilename();
        Path newPath=Paths.get(InfoForWomenConstant.IMAGE_DIR+newFileName);
        try {
            Files.createDirectories(newPath.getParent());
            Files.write(newPath,request.getFile().getBytes());
        } catch (IOException e) {
            log.warn("new image could not be loaded");
        }
        InfoForWomen infoForWomen1=mapper.map(request,infoForWomen);
        infoForWomen1.setImageUrl(InfoForWomenConstant.IMAGE_DIR+newFileName);
        repository.save(infoForWomen1);
        return mapper.mapToResponse(infoForWomen1);
    }

    @Override
    public void delete(String id) {
        InfoForWomen infoForWomen=repository.findById(id).orElseThrow(()-> BaseException.notFound(InfoForWomen.class.getSimpleName(),"id",id));
        if (infoForWomen !=null){
            String oldFileName=Paths.get(infoForWomen.getImageUrl()).getFileName().toString();
            Path oldPath=Paths.get(InfoForWomenConstant.IMAGE_DIR+oldFileName);
            try {
                Files.deleteIfExists(oldPath);
            } catch (IOException e) {
                log.warn("old image could not be deleted {}",e.getMessage());

            }
            repository.deleteById(id);
        }

    }

    @Override
    public List<InfoForWomenResponse> getAll() {
        List<InfoForWomen> infoForWomen=repository.findAll();
        return infoForWomen.stream()
                .map(mapper :: mapToResponse)
                .collect(Collectors.toList());
    }
}
