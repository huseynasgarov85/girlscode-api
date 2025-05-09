package com.example.girlscodeapi.mapper;

import com.example.girlscodeapi.model.dto.request.CoverPhotoRequest;
import com.example.girlscodeapi.model.dto.request.PhotoRequest;
import com.example.girlscodeapi.model.dto.response.CoverPhotoResponse;
import com.example.girlscodeapi.model.dto.response.NewsResponse;
import com.example.girlscodeapi.model.dto.response.PhotoResponse;
import com.example.girlscodeapi.model.entity.CoverPhoto;
import com.example.girlscodeapi.model.entity.Photo;
import com.example.girlscodeapi.model.enums.recommended.Recommended;
import org.springframework.stereotype.Component;

@Component
public class NewsMapper {

    public CoverPhoto mapToEntityCoverPhoto(CoverPhotoRequest coverPhotoRequest, String url, Recommended recommended) {
        return CoverPhoto
                .builder()
                .date(coverPhotoRequest.getDate())
                .textAZ(coverPhotoRequest.getTextAZ())
                .textENG(coverPhotoRequest.getTextENG())
                .titleAZ(coverPhotoRequest.getTitleAZ())
                .titleENG(coverPhotoRequest.getTitleENG())
                .url(url)
                .recommended(recommended)
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
                .recommended(coverPhoto.getRecommended())
                .build();
    }

    public PhotoResponse mapToPhotoResponse(Photo photo) {
        return PhotoResponse.builder().id(photo.getId()).build();
    }

    public NewsResponse mapToNewsResponse(NewsResponse newsResponse) {
        return NewsResponse
                .builder()
                .date(newsResponse.getDate())
                .photos(newsResponse.getPhotos())
                .titleAZ(newsResponse.getTitleAZ())
                .titleENG(newsResponse.getTitleENG())
                .textENG(newsResponse.getTextENG())
                .id(newsResponse.getId())
                .textAZ(newsResponse.getTextAZ())
                .url(newsResponse.getUrl())
                .recommended(newsResponse.getRecommended())
                .build();
    }


}
