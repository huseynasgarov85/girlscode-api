package com.example.girlscodeapi.service.news;

import com.example.girlscodeapi.model.dto.request.CoverPhotoRequest;
import com.example.girlscodeapi.model.dto.request.CoverPhotoRequestForUpdate;
import com.example.girlscodeapi.model.dto.request.PhotoRequest;
import com.example.girlscodeapi.model.dto.request.PhotoRequestForUpdate;
import com.example.girlscodeapi.model.dto.response.CoverPhotoResponse;
import com.example.girlscodeapi.model.dto.response.NewsResponse;
import com.example.girlscodeapi.model.dto.response.PhotoResponse;
import com.example.girlscodeapi.model.enums.filter.DateFilter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface NewsService {

    CoverPhotoResponse post(CoverPhotoRequest coverPhotoRequest);

    PhotoResponse postListPhoto(PhotoRequest photoRequest);

    List<NewsResponse> getAll(DateFilter dateFilter);

    String updateCover(String coverId, CoverPhotoRequestForUpdate coverPhotoRequestForUpdate, MultipartFile multipartFile);

    String updateListPhotos(String photoId, PhotoRequestForUpdate photoRequestForUpdate, MultipartFile multipartFiles);

    void removed(String coverId);
}
