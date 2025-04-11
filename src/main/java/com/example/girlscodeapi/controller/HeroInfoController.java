package com.example.girlscodeapi.controller;

import com.example.girlscodeapi.model.dto.request.HeroInfoRequest;
import com.example.girlscodeapi.model.dto.response.HeroInfoResponse;
import com.example.girlscodeapi.model.entity.HeroInfo;
import com.example.girlscodeapi.repository.HeroInfoRepository;
import com.example.girlscodeapi.service.HeroInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping({"/heroInfo"})
public class HeroInfoController {
    HeroInfoService heroInfoService;
    private final HeroInfoService sliderService;
    HeroInfoRepository heroInfoRepository;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HeroInfoResponse addForTest(@RequestBody HeroInfoRequest heroInfoRequest){
        return  heroInfoService.addForTest(heroInfoRequest);
    }
//
//    @PostMapping
//    public HeroInfo addEntity(@RequestBody HeroInfo heroInfo){
//        heroInfoRepository.save(heroInfo);
//        return heroInfo;
//    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestBody HeroInfo heroInfo){
        heroInfoRepository.save(heroInfo);
    }


    @PostMapping(consumes = "multipart/form-data", value = "/addPhoto")
    @ResponseStatus(HttpStatus.CREATED)
   // @Operation(summary = "slider photo api", description = "this end point add to slider photo")
    public String add(@RequestParam("photo") MultipartFile multipartFile) {
        sliderService.add(multipartFile);
        return "everything is okey" ;
    }

}
