package com.example.girlscodeapi.service.programs;
import com.example.girlscodeapi.constant.ProgramsConstant;
import com.example.girlscodeapi.exception.BaseException;
import com.example.girlscodeapi.mapper.ProgramsMapper;
import com.example.girlscodeapi.model.dto.request.ProgramsRequest;
import com.example.girlscodeapi.model.dto.response.ProgramsResponse;
import com.example.girlscodeapi.model.entity.Programs;
import com.example.girlscodeapi.model.repo.ProgramsRepository;
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
@RequiredArgsConstructor
@Slf4j
public class ProgramsServiceImpl implements ProgramsService  {
    private  final ProgramsMapper mapper;
    private final ProgramsRepository repository;

    @Override
    public ProgramsResponse add(ProgramsRequest request) {
            String fileName = UUID.randomUUID() + "_" + request.getFile().getOriginalFilename();
            Path filePath = Paths.get(ProgramsConstant.IMAGE_DIR + fileName);
            try {
                Files.createDirectories(filePath.getParent());
                Files.write(filePath, request.getFile().getBytes());
            } catch (IOException e) {
                log.warn("image could not be loaded {}", e.getMessage());
            }
            Programs programs = mapper.mapToEntity(request);
            programs.setImageUrl(ProgramsConstant.IMAGE_DIR + fileName);
            repository.save(programs);
            return mapper.mapToResponse(programs);


    }

    @Override
    public ProgramsResponse getById(String id) {
        Programs programs=repository.findById(id).orElseThrow(()-> BaseException.notFound(Programs.class.getSimpleName(),"id",id));
        return mapper.mapToResponse(programs);
    }

    @Override
    public ProgramsResponse update(String id, ProgramsRequest request) {
        Programs programs=repository.findById(id).orElseThrow(()->BaseException.notFound(Programs.class.getSimpleName(),"id",id));
        if (programs != null){
            String oldFileName=Paths.get(programs.getImageUrl()).getFileName().toString();
            Path oldPath=Paths.get(ProgramsConstant.IMAGE_DIR+oldFileName);
            try {
                Files.deleteIfExists(oldPath);
            } catch (IOException e) {
                log.warn("old image could not be deleted {}",e.getMessage());
            }
        }
        String newFileName=UUID.randomUUID()+"_"+request.getFile().getOriginalFilename();
        Path newPath=Paths.get(ProgramsConstant.IMAGE_DIR+newFileName);
        try {
            Files.createDirectories(newPath.getParent());
            Files.write(newPath,request.getFile().getBytes());
        } catch (IOException e) {
            log.warn("new image could not be loaded {}",e.getMessage());
        }
        Programs programs1=mapper.map(request,programs);
        programs1.setImageUrl(ProgramsConstant.IMAGE_DIR+newFileName);
        repository.save(programs1);
        return mapper.mapToResponse(programs1);
    }

    @Override
    public void delete(String id) {
    Programs programs=repository.findById(id).orElseThrow(()->BaseException.notFound(Programs.class.getSimpleName(),"id",id));
    if (programs !=null){
        String fileName=Paths.get(programs.getImageUrl()).getFileName().toString();
        Path filePath=Paths.get(ProgramsConstant.IMAGE_DIR+fileName);
        try {
            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            log.warn("old image could not be deleted");
        }
        repository.deleteById(id);
    }
    }

    @Override
    public List<ProgramsResponse> getAll() {
        List<Programs> programsList=repository.findAll();
        return programsList.stream()
                .map(mapper :: mapToResponse)
                .collect(Collectors.toList());
    }
}
