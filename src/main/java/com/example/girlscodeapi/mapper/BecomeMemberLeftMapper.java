package com.example.girlscodeapi.mapper;
import com.example.girlscodeapi.model.dto.request.BecomeMemberLeftRequest;
import com.example.girlscodeapi.model.dto.response.BecomeMemberLeftResponse;
import com.example.girlscodeapi.model.entity.BecomeMemberLeft;
import org.springframework.stereotype.Component;

@Component
public class BecomeMemberLeftMapper {



    public BecomeMemberLeft mapToEntity(BecomeMemberLeftRequest request, String url) {
        return BecomeMemberLeft
                .builder()
                .url(url)
                .textAZ(request.getTextAZ())
                .textENG(request.getTextENG())
                .titleAZ(request.getTitleAZ())
                .titleENG(request.getTitleENG())
                .build();
    }


    public BecomeMemberLeftResponse mapToResponse(BecomeMemberLeft becomeMemberLeft){
        return BecomeMemberLeftResponse
                .builder()
                .id(becomeMemberLeft.getId())
                .textAZ(becomeMemberLeft.getTextAZ())
                .textENG(becomeMemberLeft.getTextENG())
                .titleAZ(becomeMemberLeft.getTitleAZ())
                .titleENG(becomeMemberLeft.getTitleENG())
                .url(becomeMemberLeft.getUrl())
                .build();
    }
}
