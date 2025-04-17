package com.example.girlscodeapi.controller;
import com.example.girlscodeapi.model.dto.response.HeroInfoResponse;
import com.example.girlscodeapi.service.heroInfo.HeroInfoService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequiredArgsConstructor
@RequestMapping({"/heroInfo"})
public class HeroInfoController {
   private final HeroInfoService heroInfoService;





@GetMapping
    public  ResponseEntity<HeroInfoResponse> get(){
    return ResponseEntity.status(HttpStatus.OK).
            body(heroInfoService.get());
}

@PutMapping(consumes = "multipart/form-data")
@Operation(summary = "This api used update process", description = "api update photo")
    public ResponseEntity<HeroInfoResponse> update(MultipartFile file,String text){
    return ResponseEntity.status(HttpStatus.OK)
            .body(heroInfoService.update(file,text));
}

}
