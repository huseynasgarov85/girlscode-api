package com.example.girlscodeapi.service.becomeMember;

import com.example.girlscodeapi.mapper.BecomeMemberLeftMapper;
import com.example.girlscodeapi.model.dto.request.BecomeMemberLeftRequest;
import com.example.girlscodeapi.model.repo.BecomeMemberLeftRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BecomeMemberLeftServiceImpl implements BecomeMemberLeftService {
    private final BecomeMemberLeftRepo becomeMemberLeftRepo;
    private final BecomeMemberLeftMapper mapper;

    @Override
    public String post(BecomeMemberLeftRequest request) {
        return "";
    }
}
