package com.example.girlscodeapi.mapper;

import com.example.girlscodeapi.model.dto.request.CoverPhotoRequest;
import com.example.girlscodeapi.model.dto.request.PhotoRequest;
import com.example.girlscodeapi.model.dto.response.CoverPhotoResponse;
import com.example.girlscodeapi.model.dto.response.NewsResponse;
import com.example.girlscodeapi.model.dto.response.PhotoResponse;
import com.example.girlscodeapi.model.entity.CoverPhoto;
import com.example.girlscodeapi.model.entity.Photo;
import org.springframework.stereotype.Component;

@Component
public class NewsMapper {

    public CoverPhoto mapToEntityCoverPhoto(CoverPhotoRequest coverPhotoRequest, String url) {
        return CoverPhoto
                .builder()
                .date(coverPhotoRequest.getDate())
                .textAZ(coverPhotoRequest.getTextAZ())
                .textENG(coverPhotoRequest.getTextENG())
                .titleAZ(coverPhotoRequest.getTitleAZ())
                .titleENG(coverPhotoRequest.getTitleENG())
                .url(url)
                .build();
    }


    public Photo mapToEntityListPhoto(PhotoRequest photoRequest, String url) {
        return Photo
                .builder()
                .coverPhotoId(photoRequest.getCoverPhotoId())
                .url(url)
                .build();
    }

    public CoverPhotoResponse mapToResponse(CoverPhoto coverPhoto) {
        return CoverPhotoResponse.builder().id(coverPhoto.getId()).build();
    }

    public NewsResponse mapToNewsResponse(CoverPhoto coverPhoto) {
        return NewsResponse
                .builder()
                .date(coverPhoto.getDate())
                .photos(coverPhoto.getPhotos())
                .titleAZ(coverPhoto.getTitleAZ())
                .titleENG(coverPhoto.getTitleENG())
                .textENG(coverPhoto.getTextENG())
                .id(coverPhoto.getId())
                .textAZ(coverPhoto.getTextAZ())
                .url(coverPhoto.getUrl())
                .build();
    }

    public PhotoResponse mapToPhotoResponse(Photo photo) {
        return PhotoResponse.builder().id(photo.getId()).build();
    }


}
