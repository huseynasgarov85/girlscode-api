package com.example.girlscodeapi.service.heroInfo;

import com.example.girlscodeapi.exception.BaseException;
import com.example.girlscodeapi.mapper.HeroInfoMapper;
import com.example.girlscodeapi.model.dto.request.HeroInfoRequest;
import com.example.girlscodeapi.model.dto.response.HeroInfoResponse;
import com.example.girlscodeapi.model.entity.HeroInfo;
import com.example.girlscodeapi.model.repo.HeroInfoRepository;
import com.example.girlscodeapi.util.heroInfo.HeroInfoUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;



@Service
@Data
@Slf4j
public class HeroInfoServiceImpl implements HeroInfoService {
    private final HeroInfoRepository heroInfoRepository;
    private final HeroInfoMapper heroInfoMapper;
    private final HeroInfoUtil heroInfoUtil;

    @Override
    public HeroInfoResponse add(HeroInfoRequest request) {
        HeroInfo heroInfo = heroInfoMapper.mapToEntity(request);
        heroInfo.setUrl(heroInfoUtil.saveFile(request.getFile()));
        heroInfoRepository.save(heroInfo);
        return heroInfoMapper.mapToResponse(heroInfo);
    }

    @Override
    public HeroInfoResponse update(HeroInfoRequest request) {
        HeroInfo heroInfo = heroInfoRepository.findById("680f5459de9faa6ea664ae39")
                .orElseThrow(() -> BaseException.notFound(HeroInfo.class.getSimpleName(), "id", "680f5459de9faa6ea664ae39"));

        if (heroInfo.getUrl()!= null) {
          heroInfoUtil.removeFile(heroInfo.getUrl());
        }
        HeroInfo heroInfo1=heroInfoMapper.map(request,heroInfo);
        heroInfo1.setUrl(heroInfoUtil.saveFile(request.getFile()));
        heroInfoRepository.save(heroInfo1);
        return heroInfoMapper.mapToResponse(heroInfo1);
    }

    @Override
    public HeroInfoResponse get() {
        HeroInfo heroInfo = heroInfoRepository.findById("680f5459de9faa6ea664ae39").orElseThrow(() -> BaseException.notFound(HeroInfo.class.getSimpleName(), "id", "67fcd32d4b04077e46668e5d"));
        return heroInfoMapper.mapToResponse(heroInfo);
    }

}
