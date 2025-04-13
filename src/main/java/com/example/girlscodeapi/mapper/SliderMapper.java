package com.example.girlscodeapi.mapper;

import com.example.girlscodeapi.model.entity.Slider;
import com.example.girlscodeapi.model.response.SliderResponse;
import org.springframework.stereotype.Component;

@Component
public class SliderMapper {

    public SliderResponse mapToResponse(Slider slider) {
        return SliderResponse.builder().url(slider.getUrl()).id(slider.getId()).build();
    }
}
