package com.example.girlscodeapi.service.networkingDays;


import com.example.girlscodeapi.constant.NetworkingDaysConstant;
import com.example.girlscodeapi.exception.BaseException;
import com.example.girlscodeapi.mapper.NetworkingDaysMapper;
import com.example.girlscodeapi.model.dto.request.NetworkingDaysRequest;
import com.example.girlscodeapi.model.dto.response.NetworkingDaysResponse;
import com.example.girlscodeapi.model.entity.NetworkingDays;
import com.example.girlscodeapi.model.repo.NetworkingDaysRepository;
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
public class NetworkingDaysServiceImpl implements NetworkingDaysService {
    private final NetworkingDaysRepository repository;
    private final NetworkingDaysMapper mapper;
    @Override
    public NetworkingDaysResponse add(NetworkingDaysRequest request) {
        String fileName= UUID.randomUUID()+"_"+request.getFile().getOriginalFilename();
        Path filePath= Paths.get(NetworkingDaysConstant.IMAGE_DIR+fileName);
        try {
            Files.createDirectories(filePath.getParent());
            Files.write(filePath,request.getFile().getBytes());
        } catch (IOException e) {
        log.warn("image could not be loaded {}",e.getMessage());
        }
        NetworkingDays networkingDays=mapper.mapToEntity(request);
        networkingDays.setImageUrl(NetworkingDaysConstant.IMAGE_DIR+fileName);
        repository.save(networkingDays);
        return mapper.mapToResponse(networkingDays);
    }

    @Override
    public NetworkingDaysResponse getById(String id) {
        NetworkingDays networkingDays=repository.findById(id).orElseThrow(()-> BaseException.notFound(NetworkingDays.class.getSimpleName(),"id",id));
        return mapper.mapToResponse(networkingDays);
    }

    @Override
    public NetworkingDaysResponse update(String id, NetworkingDaysRequest request) {
        NetworkingDays  networkingDays=repository.findById(id).orElseThrow(()->BaseException.notFound(NetworkingDays.class.getSimpleName(),"id",id));
        if (networkingDays!=null){
            String oldFileName=Paths.get(networkingDays.getImageUrl()).getFileName().toString();
            Path oldPath=Paths.get(NetworkingDaysConstant.IMAGE_DIR+oldFileName);
            try {
                Files.deleteIfExists(oldPath);
            } catch (IOException e) {
                log.warn(" old image could not be deleted {}",e.getMessage());
            }
        }
        String newFileName=UUID.randomUUID()+"_"+request.getFile().getOriginalFilename();
        Path newPath=Paths.get(NetworkingDaysConstant.IMAGE_DIR+newFileName);
        try {
            Files.createDirectories(newPath.getParent());
            Files.write(newPath,request.getFile().getBytes());
        } catch (IOException e) {
            log.warn("new image could not be loaded {}",e.getMessage());
        }
        NetworkingDays networkingDays1=mapper.map(request,networkingDays);
        networkingDays1.setImageUrl(NetworkingDaysConstant.IMAGE_DIR+newFileName);
        repository.save(networkingDays1);
        return mapper.mapToResponse(networkingDays1);
    }

    @Override
    public void delete(String id) {
    NetworkingDays networkingDays=repository.findById(id).orElseThrow(()->BaseException.notFound(NetworkingDays.class.getSimpleName(),"id",id));
    if (networkingDays!=null){
        String oldFileName=Paths.get(networkingDays.getImageUrl()).getFileName().toString();
        Path oldPath=Paths.get(NetworkingDaysConstant.IMAGE_DIR+oldFileName);
        try {
            Files.deleteIfExists(oldPath);
        } catch (IOException e) {
            log.warn("Image could not be deleted {}",e.getMessage());
        }
        repository.deleteById(id);
    }
    }

    @Override
    public List<NetworkingDaysResponse> getAll() {
        List<NetworkingDays> networkingDaysList=repository.findAll();
        return networkingDaysList.stream()
                .map(mapper :: mapToResponse)
                .collect(Collectors.toList());
    }
}
