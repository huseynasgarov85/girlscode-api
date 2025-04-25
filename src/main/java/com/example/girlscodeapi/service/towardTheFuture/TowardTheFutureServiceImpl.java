package com.example.girlscodeapi.service.towardTheFuture;

import com.example.girlscodeapi.exception.BaseException;
import com.example.girlscodeapi.mapper.TowardTheFutureMapper;
import com.example.girlscodeapi.model.dto.request.BottomTowardFutureRequest;
import com.example.girlscodeapi.model.dto.request.BottomTowardFutureRequestForUpdate;
import com.example.girlscodeapi.model.dto.request.TowardTheFutureRequest;
import com.example.girlscodeapi.model.dto.request.TowardTheFutureRequestForUpdate;
import com.example.girlscodeapi.model.dto.response.BottomTowardFutureResponse;
import com.example.girlscodeapi.model.dto.response.TowardTheFutureResponse;
import com.example.girlscodeapi.model.entity.BottomTowardFuture;
import com.example.girlscodeapi.model.entity.TowardTheFuture;
import com.example.girlscodeapi.model.repo.BottomTowardFutureRepo;
import com.example.girlscodeapi.model.repo.TowardTheFutureRepo;
import com.example.girlscodeapi.util.towardTheFuture.TowardTheFutureStorageUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TowardTheFutureServiceImpl implements TowardTheFutureService {
    private final TowardTheFutureMapper towardTheFutureMapper;
    private final TowardTheFutureRepo towardTheFutureRepo;
    private final TowardTheFutureStorageUtil towardTheFutureStorageUtil;
    private final BottomTowardFutureRepo bottomTowardFutureRepo;

    @Override
    public void post(TowardTheFutureRequest towardTheFutureRequest) {
        log.info("ActionLog started post photo : " + towardTheFutureRequest.getMultipartFile());
        try {
            int counter = 0;
            TowardTheFuture towardTheFuture;
            towardTheFuture = towardTheFutureMapper.mapToEntity(towardTheFutureRequest);
            for (MultipartFile photo : towardTheFutureRequest.getMultipartFile()) {
                String url = towardTheFutureStorageUtil.saveFile(photo);
                if (counter == 0) {
                    towardTheFuture.setFirstPhotoUrl(url);
                } else if (counter == 1) {
                    towardTheFuture.setSecondPhotoUrl(url);
                } else {
                    towardTheFuture.setThirdPhotoUrl(url);
                }
                counter++;
            }
            towardTheFutureRepo.save(towardTheFuture);
        } catch (Exception e) {
            log.error("ActionLog error ");
            throw BaseException.unexpected();
        }
        log.info("ActionLog end post photo : " + towardTheFutureRequest.getMultipartFile());
    }

    @Override
    public List<TowardTheFutureResponse> getAll() {
        log.info("ActionLog start getAll");
        List<TowardTheFuture> towardTheFutures = towardTheFutureRepo.findAll();
        log.info("ActionLog end getAll");
        return towardTheFutures.stream().map(towardTheFutureMapper::mapToResponse).toList();
    }

    @Override
    public TowardTheFutureResponse update(TowardTheFutureRequestForUpdate request, MultipartFile multipartFile) {
        log.info("ActionLog started update photoNumber :" + request.getPhotoNumber());
        TowardTheFuture towardTheFuture = towardTheFutureRepo.findById("").orElseThrow(() -> new RuntimeException("not found id"));
        try {
            if (request.getTitleAZ() != null && !request.getTitleAZ().isEmpty()) {
                towardTheFuture.setTitleAZ(request.getTitleAZ());
            }
            if (request.getTitleENG() != null && !request.getTitleENG().isEmpty()) {
                towardTheFuture.setTitleENG(request.getTitleENG());
            }
            if (request.getTextAZ() != null && !request.getTextAZ().isEmpty()) {
                towardTheFuture.setTextAZ(request.getTextAZ());
            }
            if (request.getTextENG() != null && !request.getTextENG().isEmpty()) {
                towardTheFuture.setTextENG(request.getTextENG());
            }
            if (multipartFile != null && !multipartFile.isEmpty()) {
                if (request.getPhotoNumber() == 1) {
                    towardTheFutureStorageUtil.removeFileIfExists(towardTheFuture.getFirstPhotoUrl());
                    String url = towardTheFutureStorageUtil.saveFile(multipartFile);
                    towardTheFuture.setFirstPhotoUrl(url);
                } else if (request.getPhotoNumber() == 2) {
                    towardTheFutureStorageUtil.removeFileIfExists(towardTheFuture.getSecondPhotoUrl());
                    String url = towardTheFutureStorageUtil.saveFile(multipartFile);
                    towardTheFuture.setSecondPhotoUrl(url);
                } else {
                    towardTheFutureStorageUtil.removeFileIfExists(towardTheFuture.getThirdPhotoUrl());
                    String url = towardTheFutureStorageUtil.saveFile(multipartFile);
                    towardTheFuture.setThirdPhotoUrl(url);
                }
            }
            towardTheFutureRepo.save(towardTheFuture);
        } catch (Exception e) {
            log.error("ActionLog error happen");
            throw BaseException.unexpected();
        }
        log.info("ActionLog end update photoNumber :" + request.getPhotoNumber());
        return towardTheFutureMapper.mapToResponse(towardTheFuture);
    }

    @Override
    public void postBottomSide(BottomTowardFutureRequest request) {
        log.info("ActionLog started postBottomSide");
        BottomTowardFuture bottomTowardFuture = towardTheFutureMapper.mapToEntityForBottomSide(request);
        try {
            bottomTowardFutureRepo.save(bottomTowardFuture);
        } catch (Exception e) {
            log.error("ActionLog error ");
            throw BaseException.unexpected();
        }
        log.info("ActionLog end postBottomSide");
    }

    @Override
    public List<BottomTowardFutureResponse> getAllBottomSide() {
        log.info("ActionLog started getAllBottomSide");
        List<BottomTowardFuture> bottomTowardFutures = bottomTowardFutureRepo.findAll();
        log.info("ActionLog end getAllBottomSide");
        return bottomTowardFutures.stream().map(towardTheFutureMapper::mapToResponseForBottomSide).toList();
    }

    @Override
    public void updateBottomSide(BottomTowardFutureRequestForUpdate request) {
        log.info("ActionLog started updateBottomSide");
        BottomTowardFuture bottomTowardFuture = bottomTowardFutureRepo.findById("680b7c976f019c279a0a88a0").orElseThrow(() -> new RuntimeException("Not found id"));
        try {
            if (request.getFirstTitleAZ() != null && !request.getFirstTitleAZ().isEmpty()) {
                bottomTowardFuture.setFirstTitleAZ(request.getFirstTitleAZ());
            }
            if (request.getFirstTitleENG() != null && !request.getFirstTitleENG().isEmpty()) {
                bottomTowardFuture.setFirstTitleENG(request.getFirstTitleENG());
            }
            if (request.getFirstValue() != null && !request.getFirstValue().isEmpty()) {
                bottomTowardFuture.setFirstValue(request.getFirstValue());
            }
            if (request.getSecondTitleAZ() != null && !request.getSecondTitleAZ().isEmpty()) {
                bottomTowardFuture.setSecondTitleAZ(request.getSecondTitleAZ());
            }
            if (request.getSecondTitleENG() != null && !request.getSecondTitleENG().isEmpty()) {
                bottomTowardFuture.setSecondTitleENG(request.getSecondTitleENG());
            }
            if (request.getSecondValue() != null && !request.getSecondValue().isEmpty()) {
                bottomTowardFuture.setSecondValue(request.getSecondValue());
            }
            if (request.getThirdTitleAZ() != null && !request.getThirdTitleAZ().isEmpty()) {
                bottomTowardFuture.setThirdTitleAZ(request.getThirdTitleAZ());
            }
            if (request.getThirdTitleENG() != null && !request.getThirdTitleENG().isEmpty()) {
                bottomTowardFuture.setThirdTitleENG(request.getThirdTitleENG());
            }
            if (request.getThirdValue() != null && !request.getThirdValue().isEmpty()) {
                bottomTowardFuture.setThirdValue(request.getThirdValue());
            }
            if (request.getFourthTitleAZ() != null && !request.getFourthTitleAZ().isEmpty()) {
                bottomTowardFuture.setFourthTitleAZ(request.getFourthTitleAZ());
            }
            if (request.getFourthTitleENG() != null && !request.getFourthTitleENG().isEmpty()) {
                bottomTowardFuture.setFourthValue(request.getFourthTitleENG());
            }
            if (request.getFourthValue() != null && !request.getFourthValue().isEmpty()) {
                bottomTowardFuture.setFourthValue(request.getFourthValue());
            }
            bottomTowardFutureRepo.save(bottomTowardFuture);
        } catch (Exception e) {
            log.error("ActionLog error ");
            throw BaseException.unexpected();
        }
        log.info("ActionLog end updateBottomSide");
    }

}
