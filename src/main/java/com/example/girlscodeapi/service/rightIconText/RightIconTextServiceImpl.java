package com.example.girlscodeapi.service.rightIconText;

import com.example.girlscodeapi.constant.RightIconTextConstant;
import com.example.girlscodeapi.exception.BaseException;
import com.example.girlscodeapi.mapper.RightIconTextMapper;
import com.example.girlscodeapi.model.dto.request.RightIconTextRequest;
import com.example.girlscodeapi.model.dto.response.RightIconTextResponse;
import com.example.girlscodeapi.model.entity.RightIconText;
import com.example.girlscodeapi.model.repo.RightIconTextRepository;
import lombok.RequiredArgsConstructor;
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
public class RightIconTextServiceImpl implements RightIconTextService{
    private final RightIconTextMapper mapper;
    private final RightIconTextRepository repository;

    @Override
    public RightIconTextResponse add(RightIconTextRequest request) {
        String fileName= UUID.randomUUID()+"_"+request.getMultipartFile().getOriginalFilename();
        Path filePath= Paths.get(RightIconTextConstant.ICON_DIR+fileName);
        try {
            Files.createDirectories(filePath.getParent());
            Files.write(filePath,request.getMultipartFile().getBytes());
        }catch (IOException ex){
            System.out.println("image could not be loaded "+ex.getMessage());
        }
        RightIconText rightIconText=new RightIconText();
        rightIconText.setIconUrl(RightIconTextConstant.ICON_DIR+fileName);
        rightIconText.setTitleAz(request.getTitleAz());
        rightIconText.setTitleEng(request.getTitleEng());
        rightIconText.setDescriptionAz(request.getDescriptionAz());
        rightIconText.setDescriptionEng(request.getDescriptionEng());
        repository.save(rightIconText);
        return mapper.mapToResponse(rightIconText);
    }

    @Override
    public RightIconTextResponse getById(String id) {
        RightIconText rightIconText=repository.findById(id).orElseThrow(()-> BaseException.notFound(RightIconText.class.getSimpleName(),"id",id));
        return mapper.mapToResponse(rightIconText);

    }

    @Override
    public RightIconTextResponse update(String id, RightIconTextRequest request) {
        RightIconText rightIconText=repository.findById(id).orElseThrow(()->BaseException.notFound(RightIconText.class.getSimpleName(),"id",id));
            if (rightIconText !=null){
                String oldFileName=Paths.get(rightIconText.getIconUrl()).getFileName().toString();
                Path oldPath = Paths.get(RightIconTextConstant.ICON_PATH).resolve(oldFileName);
                try {
                    Files.deleteIfExists(oldPath);
                } catch (IOException e) {
                    System.out.println("Old image could not be deleted: " + e.getMessage());
                }
            }
        String newFileName = UUID.randomUUID() + "_" + request.getMultipartFile().getOriginalFilename();
        Path newPath = Paths.get(RightIconTextConstant.ICON_PATH).resolve(newFileName);
        try {
            Files.createDirectories(newPath.getParent());
            Files.write(newPath, request.getMultipartFile().getBytes());
        } catch (IOException e) {
            throw new RuntimeException("File could not be saved: " + e.getMessage());
        }
        rightIconText.setIconUrl(RightIconTextConstant.ICON_DIR+newFileName);
        rightIconText.setTitleAz(request.getTitleAz());
        rightIconText.setTitleEng(request.getTitleEng());
        rightIconText.setDescriptionAz(request.getDescriptionAz());
        rightIconText.setDescriptionEng(request.getDescriptionEng());
        repository.save(rightIconText);
        return mapper.mapToResponse(rightIconText);
    }

    @Override
    public void  delete(String id) {
        RightIconText rightIconText = repository.findById(id).orElseThrow(() -> BaseException.notFound(RightIconText.class.getSimpleName(),"id",id));
        if (rightIconText != null) {
            String oldFileName = Paths.get(rightIconText.getIconUrl()).getFileName().toString();
            Path oldPath = Paths.get(RightIconTextConstant.ICON_PATH).resolve(oldFileName);
            try {
                Files.deleteIfExists(oldPath);
            } catch (IOException e) {
                System.out.println("Old image could not be deleted: " + e.getMessage());
            }
        }
        repository.deleteById(id);

    }

    @Override
    public List<RightIconTextResponse> getAll() {
        List<RightIconText> rightIconTexts=repository.findAll();
        return rightIconTexts.stream()
                .map(mapper ::mapToResponse)
                .collect(Collectors.toList());
    }


}
//67ff9d76c58219736f8c7157