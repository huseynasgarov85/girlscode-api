package com.example.girlscodeapi.service;

import com.example.girlscodeapi.model.repo.SliderRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Slf4j
public class SliderServiceImpl implements SliderService {
    private final SliderRepo sliderRepo;

    @Override
    public String add(MultipartFile multipartFile) {
        log.info("ActionLog add started  ");
        return "";
    }
}
