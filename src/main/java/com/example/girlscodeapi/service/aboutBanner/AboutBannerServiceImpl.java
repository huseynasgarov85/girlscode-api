package com.example.girlscodeapi.service.aboutBanner;

import com.example.girlscodeapi.exception.BaseException;
import com.example.girlscodeapi.mapper.AboutBannerMapper;
import com.example.girlscodeapi.model.dto.request.AboutBannerRequest;
import com.example.girlscodeapi.model.dto.response.AboutBannerResponse;
import com.example.girlscodeapi.model.entity.AboutBanner;
import com.example.girlscodeapi.model.repo.AboutBannerRepository;
import com.example.girlscodeapi.util.aboutBanner.AboutBannerUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class AboutBannerServiceImpl implements AboutBannerService{
    private final AboutBannerRepository repository;
    private final AboutBannerMapper mapper;
    private final AboutBannerUtil aboutBannerUtil;
    @Override
    public AboutBannerResponse add(AboutBannerRequest request) {

        AboutBanner aboutBanner=mapper.mapToEntity(request);
        aboutBanner.setImageUrl(aboutBannerUtil.saveFile(request.getFile()));
        repository.save(aboutBanner);
        log.info("Action log about banner object {}",aboutBanner);
        return mapper.mapToResponse(aboutBanner);
    }

    @Override
    public AboutBannerResponse getById() {

        AboutBanner aboutBanner=repository.findById("6812167d7abed71b9f8d0246").orElseThrow(()->BaseException.notFound(AboutBanner.class.getSimpleName(),"id","6812167d7abed71b9f8d0246"));
        return mapper.mapToResponse(aboutBanner);
    }

    @Override
    public AboutBannerResponse update( AboutBannerRequest request) {
        AboutBanner aboutBanner=repository.findById("6812167d7abed71b9f8d0246").orElseThrow(()->BaseException.notFound(AboutBanner.class.getSimpleName(),"id","6812167d7abed71b9f8d0246"));
        if (aboutBanner.getImageUrl() !=null){
           aboutBannerUtil.removeFile(aboutBanner.getImageUrl());
        }

        AboutBanner aboutBanner1=mapper.map(request,aboutBanner);
        aboutBanner1.setImageUrl(aboutBannerUtil.saveFile(request.getFile()));
        repository.save(aboutBanner1);
        return mapper.mapToResponse(aboutBanner1);
    }


}
