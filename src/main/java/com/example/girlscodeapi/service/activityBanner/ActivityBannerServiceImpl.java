package com.example.girlscodeapi.service.activityBanner;

import com.example.girlscodeapi.exception.BaseException;
import com.example.girlscodeapi.mapper.ActivityBannerMapper;
import com.example.girlscodeapi.model.dto.request.ActivityBannerRequest;
import com.example.girlscodeapi.model.dto.response.ActivityBannerResponse;
import com.example.girlscodeapi.model.entity.ActivityBanner;
import com.example.girlscodeapi.model.repo.ActivityBannerRepository;
import com.example.girlscodeapi.util.activityBanner.ActivityBannerUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActivityBannerServiceImpl  implements ActivityBannerService{
    private final ActivityBannerMapper mapper;
    private final ActivityBannerRepository repository;
    private final ActivityBannerUtil activityBannerUtil;
    @Override
    public ActivityBannerResponse add(ActivityBannerRequest request) {
        ActivityBanner activityBanner=mapper.mapToEntity(request);
        activityBanner.setImageUrl(activityBannerUtil.saveFile(request.getFile()));
        repository.save(activityBanner);
        return mapper.mapToResponse(activityBanner);
    }

    @Override
    public ActivityBannerResponse getById() {
        ActivityBanner activityBanner=repository.findById("6818c0128f3ad4411215951c").orElseThrow(()-> BaseException.notFound(ActivityBanner.class.getSimpleName(),"id","6818c0128f3ad4411215951c"));
        return mapper.mapToResponse(activityBanner);
    }

    @Override
    public ActivityBannerResponse update(ActivityBannerRequest request) {
        ActivityBanner activityBanner=repository.findById("6818c0128f3ad4411215951c").orElseThrow(()-> BaseException.notFound(ActivityBanner.class.getSimpleName(),"id","6818c0128f3ad4411215951c"));
        if (activityBanner.getImageUrl() != null){
            activityBannerUtil.removeFile(activityBanner.getImageUrl());
        }

        ActivityBanner activityBanner1=mapper.map(request,activityBanner);
        activityBanner1.setImageUrl(activityBannerUtil.saveFile(request.getFile()));
        repository.save(activityBanner1);
        return mapper.mapToResponse(activityBanner1);
    }
}
