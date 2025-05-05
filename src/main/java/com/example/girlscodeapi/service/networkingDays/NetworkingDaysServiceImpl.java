package com.example.girlscodeapi.service.networkingDays;

import com.example.girlscodeapi.exception.BaseException;
import com.example.girlscodeapi.mapper.NetworkingDaysMapper;
import com.example.girlscodeapi.model.dto.request.NetworkingDaysRequest;
import com.example.girlscodeapi.model.dto.response.NetworkingDaysResponse;
import com.example.girlscodeapi.model.entity.NetworkingDays;
import com.example.girlscodeapi.model.repo.NetworkingDaysRepository;
import com.example.girlscodeapi.util.networkingDays.NetworkingDaysUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class NetworkingDaysServiceImpl implements NetworkingDaysService {
    private final NetworkingDaysRepository repository;
    private final NetworkingDaysMapper mapper;
    private final NetworkingDaysUtil networkingDaysUtil;
    @Override
    public NetworkingDaysResponse add(NetworkingDaysRequest request) {
        NetworkingDays networkingDays=mapper.mapToEntity(request);
        networkingDays.setImageUrl(networkingDaysUtil.saveFile(request.getFile()));
        repository.save(networkingDays);
        return mapper.mapToResponse(networkingDays);
    }

    @Override
    public NetworkingDaysResponse getById(String id) {
        NetworkingDays networkingDays=repository.findById(id).orElseThrow(()-> BaseException.notFound(NetworkingDays.class.getSimpleName(),"id",id));
        return mapper.mapToResponse(networkingDays);
    }

    @Override
    public NetworkingDaysResponse update(String id, NetworkingDaysRequest request) {
        NetworkingDays  networkingDays=repository.findById(id).orElseThrow(()->BaseException.notFound(NetworkingDays.class.getSimpleName(),"id",id));
        if (networkingDays.getImageUrl()!=null){
           networkingDaysUtil.removeFile(networkingDays.getImageUrl());
        }
        NetworkingDays networkingDays1=mapper.map(request,networkingDays);
        networkingDays1.setImageUrl(networkingDaysUtil.saveFile(request.getFile()));
        repository.save(networkingDays1);
        return mapper.mapToResponse(networkingDays1);
    }

    @Override
    public void delete(String id) {
    NetworkingDays networkingDays=repository.findById(id).orElseThrow(()->BaseException.notFound(NetworkingDays.class.getSimpleName(),"id",id));
    if (networkingDays.getImageUrl()!=null){
        networkingDaysUtil.removeFile(networkingDays.getImageUrl());
        repository.deleteById(id);
    }
    }

    @Override
    public List<NetworkingDaysResponse> getAll() {
        List<NetworkingDays> networkingDaysList=repository.findAll();
        return networkingDaysList.stream()
                .map(mapper :: mapToResponse)
                .collect(Collectors.toList());
    }
}
