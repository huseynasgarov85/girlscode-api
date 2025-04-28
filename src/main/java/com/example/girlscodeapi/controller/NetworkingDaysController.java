package com.example.girlscodeapi.controller;

import com.example.girlscodeapi.model.dto.request.NetworkingDaysRequest;
import com.example.girlscodeapi.model.dto.response.NetworkingDaysResponse;
import com.example.girlscodeapi.service.networkingDays.NetworkingDaysService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/networkingDays")
public class NetworkingDaysController {
    private final NetworkingDaysService service;

    @PostMapping(consumes = "multipart/form-data")
    @Operation(summary = "networkingDays insert api",description = "this is networkingDays insert api")
    public ResponseEntity<NetworkingDaysResponse> add(@ModelAttribute NetworkingDaysRequest request){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.add(request));
    }
    @GetMapping({"/{id}"})
    @Operation(summary = "networkingDays getById api",description = "this is networkingDays getById api")
    public ResponseEntity<NetworkingDaysResponse> getById(@PathVariable String id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.getById(id));
    }
    @PutMapping(value = {"/{id}"},consumes = "multipart/form-data")
    @Operation(summary = "networkingDays update api",description = "this is networkingDays update api")
    public ResponseEntity<NetworkingDaysResponse> update(@PathVariable String id,@ModelAttribute NetworkingDaysRequest request){
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.update(id,request));
    }

    @DeleteMapping({"/{id}"})
    @Operation(summary = "networkingDays delete api",description = "this is networkingDays delete api")
    public ResponseEntity<Void> delete(@PathVariable String id){
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
}
    @GetMapping
    @Operation(summary = "networkingDays getAll api",description = "this is networkingDays getAll api")
    public ResponseEntity<List<NetworkingDaysResponse>> getAll(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.getAll());
    }

}
