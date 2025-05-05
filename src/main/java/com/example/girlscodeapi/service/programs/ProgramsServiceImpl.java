package com.example.girlscodeapi.service.programs;
import com.example.girlscodeapi.exception.BaseException;
import com.example.girlscodeapi.mapper.ProgramsMapper;
import com.example.girlscodeapi.model.dto.request.ProgramsRequest;
import com.example.girlscodeapi.model.dto.response.ProgramsResponse;
import com.example.girlscodeapi.model.entity.Programs;
import com.example.girlscodeapi.model.repo.ProgramsRepository;
import com.example.girlscodeapi.util.programs.ProgramsUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProgramsServiceImpl implements ProgramsService  {
    private  final ProgramsMapper mapper;
    private final ProgramsRepository repository;
    private final ProgramsUtil programsUtil;

    @Override
    public ProgramsResponse add(ProgramsRequest request) {
            Programs programs = mapper.mapToEntity(request);
            programs.setImageUrl(programsUtil.saveFile(request.getFile()));
            repository.save(programs);
            return mapper.mapToResponse(programs);


    }

    @Override
    public ProgramsResponse getById(String id) {
        Programs programs=repository.findById(id).orElseThrow(()-> BaseException.notFound(Programs.class.getSimpleName(),"id",id));
        return mapper.mapToResponse(programs);
    }

    @Override
    public ProgramsResponse update(String id, ProgramsRequest request) {
        Programs programs=repository.findById(id).orElseThrow(()->BaseException.notFound(Programs.class.getSimpleName(),"id",id));
        if (programs.getImageUrl() != null){
          programsUtil.removeFile(programs.getImageUrl());
        }
        Programs programs1=mapper.map(request,programs);
        programs1.setImageUrl(programsUtil.saveFile(request.getFile()));
        repository.save(programs1);
        return mapper.mapToResponse(programs1);
    }

    @Override
    public void delete(String id) {
    Programs programs=repository.findById(id).orElseThrow(()->BaseException.notFound(Programs.class.getSimpleName(),"id",id));
    if (programs.getImageUrl() !=null){
       programsUtil.removeFile(programs.getImageUrl());
        repository.deleteById(id);
    }
    }

    @Override
    public List<ProgramsResponse> getAll() {
        List<Programs> programsList=repository.findAll();
        return programsList.stream()
                .map(mapper :: mapToResponse)
                .collect(Collectors.toList());
    }
}
