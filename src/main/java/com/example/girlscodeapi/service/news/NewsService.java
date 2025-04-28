package com.example.girlscodeapi.service.news;

import com.example.girlscodeapi.model.dto.request.*;
import com.example.girlscodeapi.model.dto.response.CoverPhotoResponse;
import com.example.girlscodeapi.model.dto.response.PhotoResponse;
import com.example.girlscodeapi.model.enums.filter.DateFilter;
import com.example.girlscodeapi.model.enums.recommended.Recommended;
import com.example.girlscodeapi.model.enums.recommended.RecommendedCheck;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.data.domain.Pageable;

public interface NewsService {

    CoverPhotoResponse post(CoverPhotoRequest coverPhotoRequest);

    PhotoResponse postListPhoto(PhotoRequest photoRequest);

    Object getAll(DateFilter dateFilter, Integer page, Integer size, RecommendedCheck recommended);

    String updateCover(String coverId, CoverPhotoRequestForUpdate coverPhotoRequestForUpdate, MultipartFile multipartFile);

    String updateListPhotos(String photoId, PhotoRequestForUpdate photoRequestForUpdate, MultipartFile multipartFiles);

    void removed(String coverId);
}
