package com.example.girlscodeapi.service.news;

import com.example.girlscodeapi.exception.BaseException;
import com.example.girlscodeapi.mapper.NewsMapper;
import com.example.girlscodeapi.model.dto.request.CoverPhotoRequest;
import com.example.girlscodeapi.model.dto.request.CoverPhotoRequestForUpdate;
import com.example.girlscodeapi.model.dto.request.PhotoRequest;
import com.example.girlscodeapi.model.dto.request.PhotoRequestForUpdate;
import com.example.girlscodeapi.model.dto.response.CoverPhotoResponse;
import com.example.girlscodeapi.model.dto.response.NewsResponse;
import com.example.girlscodeapi.model.dto.response.PhotoResponse;
import com.example.girlscodeapi.model.entity.CoverPhoto;
import com.example.girlscodeapi.model.entity.Photo;
import com.example.girlscodeapi.model.enums.filter.DateFilter;
import com.example.girlscodeapi.model.repo.NewsRepo;
import com.example.girlscodeapi.model.repo.PhotoRepo;
import com.example.girlscodeapi.util.news.NewsCoverPhotoStorageUtil;
import com.example.girlscodeapi.util.news.NewsListPhotoStorageUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class NewsServiceImpl implements NewsService {
    private final NewsRepo newsRepo;
    private final NewsMapper newsMapper;
    private final NewsCoverPhotoStorageUtil newsCoverPhotoStorageUtil;
    private final NewsListPhotoStorageUtil newsListPhotoStorageUtil;
    private final PhotoRepo photoRepo;

    @Override
    public CoverPhotoResponse post(CoverPhotoRequest coverPhotoRequest) {
        log.info("ActionLog started post coverPhotoRequest : " + coverPhotoRequest.getMultipartFile());
        CoverPhoto coverPhoto;
        try {
            String url = newsCoverPhotoStorageUtil.saveFile(coverPhotoRequest.getMultipartFile());
            coverPhoto = newsMapper.mapToEntityCoverPhoto(coverPhotoRequest, url);
            newsRepo.save(coverPhoto);
        } catch (Exception e) {
            log.info("ActionLog error happen");
            throw BaseException.unexpected();
        }
        log.info("ActionLog end post coverPhotoRequest :" + coverPhotoRequest.getMultipartFile());

        return newsMapper.mapToResponse(coverPhoto);
    }

    @Override
    @Transactional(rollbackFor = BaseException.class, propagation = Propagation.REQUIRED)
    public PhotoResponse postListPhoto(PhotoRequest photoRequest) {
        log.info("ActionLog start post photoRequest : " + photoRequest.getMultipartFile());
        Photo photo = new Photo();
        LinkedList<Photo> photos = new LinkedList<>();
        try {
            for (MultipartFile multipartFile : photoRequest.getMultipartFile()) {
                String url = newsListPhotoStorageUtil.saveFile(multipartFile);
                photo = newsMapper.mapToEntityListPhoto(photoRequest, url);
                photos.add(photo);
            }
            photoRepo.saveAll(photos);
        } catch (Exception e) {
            log.info("ActionLog error happen");
            throw BaseException.unexpected();
        }
        CoverPhoto coverPhoto = newsRepo.findById(photo.getCoverPhotoId()).orElseThrow(() -> BaseException.notFound(CoverPhoto.class.getSimpleName(), "id", photoRequest.getCoverPhotoId()));
        coverPhoto.setPhotos(photos);
        CoverPhoto save = newsRepo.save(coverPhoto);
        log.info("saved id " + save.getId());
        log.info("ActionLog end post photoRequest : " + photoRequest.getMultipartFile());
        return newsMapper.mapToPhotoResponse(photo);
    }

    @Override
    public List<NewsResponse> getAll(DateFilter dateFilter) {
        log.info("ActionLog started getAll");
        List<CoverPhoto> coverPhotos = newsRepo.findAll();
        log.info("coverData " + coverPhotos);
        List<NewsResponse> filter = new ArrayList<>();
        switch (dateFilter) {
            case recent_history:
                filter = recentHistory(coverPhotos);
                break;
            case distant_history:
                filter = distantHistory(coverPhotos);
                break;
        }
        log.info("ActionLog end getAll");
        return filter;
    }

    @Override
    public String updateCover(String coverId, CoverPhotoRequestForUpdate coverPhotoRequestForUpdate, MultipartFile multipartFile) {
        log.info("ActionLog stared updateCover coverId :" + coverId);
        CoverPhoto coverPhoto = newsRepo.findById(coverId).orElseThrow(() -> BaseException.notFound(CoverPhoto.class.getSimpleName(), "id", coverId.toString()));
        newsCoverPhotoStorageUtil.removeFileIfExists(coverPhoto.getUrl());
        try {
            if (multipartFile != null && !multipartFile.isEmpty()) {
                String url = newsCoverPhotoStorageUtil.saveFile(multipartFile);
                coverPhoto.setUrl(url);
            }
            if (coverPhotoRequestForUpdate.getTitleAZ() != null && !coverPhotoRequestForUpdate.getTitleAZ().isEmpty()) {
                coverPhoto.setTitleAZ(coverPhotoRequestForUpdate.getTitleAZ());
            }
            if (coverPhotoRequestForUpdate.getTitleENG() != null && !coverPhotoRequestForUpdate.getTitleENG().isEmpty()) {
                coverPhoto.setTitleENG(coverPhotoRequestForUpdate.getTitleENG());
            }
            if (coverPhotoRequestForUpdate.getTextAZ() != null && !coverPhotoRequestForUpdate.getTextAZ().isEmpty()) {
                coverPhoto.setTextAZ(coverPhotoRequestForUpdate.getTextAZ());
            }
            if (coverPhotoRequestForUpdate.getTextENG() != null && !coverPhotoRequestForUpdate.getTextENG().isEmpty()) {
                coverPhoto.setTextENG(coverPhotoRequestForUpdate.getTextENG());
            }
            if (coverPhotoRequestForUpdate.getDate() != null) {
                coverPhoto.setDate(coverPhotoRequestForUpdate.getDate());
            }
            newsRepo.save(coverPhoto);
        } catch (Exception e) {
            log.error("ActionLog error " + e.getMessage());
            throw BaseException.unexpected();
        }
        log.info("ActionLog end updateCover coverId :" + coverId);
        return "update process is successfully finished";
    }

    @Override
    public String updateListPhotos(String photoId, PhotoRequestForUpdate photoRequestForUpdate, MultipartFile multipartFile) {
        log.info("ActionLog started updateListPhotos photoId : " + photoId);
        Photo photo = photoRepo.findById(photoId).orElseThrow(() -> BaseException.notFound(Photo.class.getSimpleName(), "id", photoId.toString()));
        newsListPhotoStorageUtil.removeFileIfExists(photo.getUrl());
        try {
            if (multipartFile != null && !multipartFile.isEmpty()) {
                String url = newsListPhotoStorageUtil.saveFile(multipartFile);
                photo.setUrl(url);
            }
            if (photoRequestForUpdate.getCoverPhotoId() != null) {
                photo.setId(photoRequestForUpdate.getCoverPhotoId());
            }
            photoRepo.save(photo);
        } catch (Exception e) {
            log.error("ActionLog error " + e.getMessage());
            throw BaseException.unexpected();
        }
        log.info("ActionLog end updateListPhotos photoId : " + photoId);
        return "updateListPhotos successfully finished";
    }

    @Override
    public void removed(String coverId) {
        log.info("ActionLog removed started coverId :" + coverId);
        CoverPhoto coverPhoto = newsRepo.findById(coverId).orElseThrow(() -> BaseException.notFound(CoverPhoto.class.getSimpleName(), "id", coverId.toString()));
        try {
            newsCoverPhotoStorageUtil.removeFileIfExists(coverPhoto.getUrl());
            newsRepo.deleteById(coverId);
            List<Photo> photos = photoRepo.findByCoverPhotoId(coverPhoto.getId());
            for (Photo photo : photos) {
                newsListPhotoStorageUtil.removeFileIfExists(photo.getUrl());
            }
        } catch (Exception e) {
            log.error("ActionLog error " + e.getMessage());
            throw BaseException.unexpected();
        }
        log.info("ActionLog removed end coverId :" + coverId);
    }

    private List<NewsResponse> recentHistory(List<CoverPhoto> coverPhotos) {
        List<NewsResponse> responseList = new ArrayList<>();

        List<CoverPhoto> sorted = coverPhotos.stream()
                .sorted(Comparator.comparing(CoverPhoto::getDate).reversed())
                .toList();

        for (CoverPhoto photo : sorted) {
            responseList.add(newsMapper.mapToNewsResponse(photo));
        }
        return responseList;
    }

    private List<NewsResponse> distantHistory(List<CoverPhoto> coverPhoto) {
        List<NewsResponse> responseList = new ArrayList<>();
        List<CoverPhoto> sorted = coverPhoto.stream()
                .sorted(Comparator.comparing(CoverPhoto::getDate))
                .toList();
        for (CoverPhoto photo : sorted) {
            responseList.add(newsMapper.mapToNewsResponse(photo));
        }
        return responseList;
    }
}
