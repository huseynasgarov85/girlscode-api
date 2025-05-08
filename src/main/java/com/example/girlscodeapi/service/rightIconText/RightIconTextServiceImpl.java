package com.example.girlscodeapi.service.rightIconText;

import com.example.girlscodeapi.exception.BaseException;
import com.example.girlscodeapi.mapper.RightIconTextMapper;
import com.example.girlscodeapi.model.dto.request.RightIconTextRequest;
import com.example.girlscodeapi.model.dto.response.RightIconTextResponse;
import com.example.girlscodeapi.model.entity.RightIconText;
import com.example.girlscodeapi.model.repo.RightIconTextRepository;
import com.example.girlscodeapi.util.rightIconText.RightIconTextUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RightIconTextServiceImpl implements RightIconTextService{
    private final RightIconTextMapper mapper;
    private final RightIconTextRepository repository;
    private final RightIconTextUtil rightIconTextUtil;

    @Override
    public RightIconTextResponse add(RightIconTextRequest request) {

        RightIconText rightIconText=mapper.mapToEntity(request);
        rightIconText.setIconUrl(rightIconTextUtil.saveFile(request.getMultipartFile()));
        repository.save(rightIconText);
        return mapper.mapToResponse(rightIconText);
    }

    @Override
    public RightIconTextResponse getById(String id) {
        RightIconText rightIconText=repository.findById(id).orElseThrow(()-> BaseException.notFound(RightIconText.class.getSimpleName(),"id",id));
        return mapper.mapToResponse(rightIconText);

    }

    @Override
    public RightIconTextResponse update(String id, RightIconTextRequest request) {
        RightIconText rightIconText=repository.findById(id).orElseThrow(()->BaseException.notFound(RightIconText.class.getSimpleName(),"id",id));
            if (rightIconText.getIconUrl() !=null){
              rightIconTextUtil.removeFile(rightIconText.getIconUrl());
            }
            RightIconText rightIconText1=mapper.map(request,rightIconText);
        rightIconText1.setIconUrl(rightIconTextUtil.saveFile(request.getMultipartFile()));

        repository.save(rightIconText1);
        return mapper.mapToResponse(rightIconText1);
    }

    @Override
    public void  delete(String id) {
        RightIconText rightIconText = repository.findById(id).orElseThrow(() -> BaseException.notFound(RightIconText.class.getSimpleName(),"id",id));
        if (rightIconText != null) {
            rightIconTextUtil.removeFile(rightIconText.getIconUrl());
        }
        repository.deleteById(id);

    }

    @Override
    public List<RightIconTextResponse> getAll() {
        List<RightIconText> rightIconTexts=repository.findAll();
        return rightIconTexts.stream()
                .map(mapper ::mapToResponse)
                .collect(Collectors.toList());
    }


}
