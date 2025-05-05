package com.example.girlscodeapi.util.programs;
import com.example.girlscodeapi.constant.ProgramsConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Component
@Slf4j
public class ProgramsUtil {
    public String saveFile(MultipartFile file){
        String fileName= UUID.randomUUID()+"_"+file.getOriginalFilename();
        Path filePath= Paths.get(ProgramsConstant.FOLDER_PATH).resolve(fileName);
        try {
            Files.createDirectories(filePath.getParent());
            Files.write(filePath,file.getBytes());
            log.info("Load image {}",filePath);
        } catch (IOException e) {
            log.warn("image could not be loaded {}",e.getMessage());
        }
        return filePath.toString();
    }

    public void removeFile(String url){
        Path filePath= Paths.get(url);
        try {
            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            log.warn("image could not be deleted");
        }
    }
}
