package com.example.girlscodeapi.service.leftBanner;
import com.example.girlscodeapi.exception.BaseException;
import com.example.girlscodeapi.mapper.LeftBannerMapper;
import com.example.girlscodeapi.model.dto.response.LeftBannerResponse;
import com.example.girlscodeapi.model.entity.LeftBanner;
import com.example.girlscodeapi.model.repo.LeftBannerRepository;

import com.example.girlscodeapi.util.leftBanner.LeftBannerUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
@Service
@Data
@Slf4j
public class LeftBannerServiceImpl implements LeftBannerService  {
    private final LeftBannerRepository repository;
    private final LeftBannerMapper mapper;
    private final LeftBannerUtil leftBannerUtil;

    @Override
    public LeftBannerResponse add(MultipartFile file) {
        LeftBanner leftBanner=new LeftBanner();
        leftBanner.setUrl(leftBannerUtil.saveFile(file));
        repository.save(leftBanner);
        return mapper.mapToResponse(leftBanner);
    }

    @Override
    public LeftBannerResponse get() {
        LeftBanner leftBanner=repository.findById("67ff40eef6c53110712f75e0").orElseThrow(()->  BaseException.notFound(LeftBanner.class.getSimpleName(),"id","67ff40eef6c53110712f75e0"));
        return mapper.mapToResponse(leftBanner);
    }

    @Override
    public LeftBannerResponse update( MultipartFile file) {
        LeftBanner leftBanner=repository.findById("67ff40eef6c53110712f75e0").orElseThrow(()-> BaseException.notFound(LeftBanner.class.getSimpleName(),"id","67ff40eef6c53110712f75e0"));
        if (leftBanner.getUrl()!=null){
        leftBannerUtil.removeFile(leftBanner.getUrl());
        }

        leftBanner.setUrl(leftBannerUtil.saveFile(file));
        repository.save(leftBanner);
        return  mapper.mapToResponse(leftBanner);

    }
}
