package com.example.girlscodeapi.controller;

import com.example.girlscodeapi.model.base.BaseResponse;
import com.example.girlscodeapi.model.dto.request.*;
import com.example.girlscodeapi.model.dto.response.ContactInfoResponse;
import com.example.girlscodeapi.model.dto.response.ContactResponse;
import com.example.girlscodeapi.model.dto.response.SocialMediaInContactResponse;
import com.example.girlscodeapi.model.entity.SocialMediaInContact;
import com.example.girlscodeapi.service.contact.ContactService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/contact")
public class ContactController {
    private final ContactService service;

    @PostMapping
    @Operation(summary = "contact create api", description = "this is contact create api")
    public ResponseEntity<ContactResponse> add(@Valid @RequestBody ContactRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.add(request));
    }

    @GetMapping({"/{id}"})
    @Operation(summary = "contact get api", description = "this is contact get api")
    public ResponseEntity<ContactResponse> getById(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.getById(id));
    }

    @GetMapping
    @Operation(summary = "contact getAll api", description = "this is contact getAll api")
    public ResponseEntity<List<ContactResponse>> getAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.getAll());
    }

    @DeleteMapping({"/{id}"})
    @Operation(summary = "contact delete api", description = "this is contact delete api")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping({"/{id}"})
    @Operation(summary = "contact update api", description = "this is contact update api")
    public ResponseEntity<ContactResponse> update(@PathVariable String id, @Valid @RequestBody ContactRequest request) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.update(id, request));
    }


    @PostMapping("/contactInfoLeft")
    @Operation(summary = "This end point will post left side in contact info", description = "post contact info left side")
    public BaseResponse<Void> post(@RequestBody @Valid ContactInfoRequest contactInfoRequest) {
        service.post(contactInfoRequest);
        return BaseResponse.success();
    }

    @GetMapping("/contactInfoLeft")
    @Operation(summary = "this end point get all data's", description = "get all data")
    public BaseResponse<List<ContactInfoResponse>> getAllContactInfo() {
        return BaseResponse.success(service.getAllContactInfo());
    }

    @PutMapping("/contactInfoLeft/{id}")
    @Operation(summary = "this end point update field's", description = "update field's")
    public BaseResponse<Void> update(@PathVariable String id, @RequestBody(required = false) @Valid ContactInfoRequestUpdate contactInfoRequestUpdate) {
        service.update(id, contactInfoRequestUpdate);
        return BaseResponse.success();
    }

    @DeleteMapping("/contactInfoLeft/{id}")
    @Operation(summary = "remove form", description = "remove user detail")
    public BaseResponse<Void> remove(@PathVariable String id) {
        service.remove(id);
        return BaseResponse.success();
    }

    @PostMapping("/socialMedia")
    @Operation(summary = "this end point will post social media links", description = "post social media links")
    public BaseResponse<Void> postSocialMedia(@RequestBody @Valid SocialMediaInContactRequest socialMediaInContactRequest) {
        service.postSocialMedia(socialMediaInContactRequest);
        return BaseResponse.success();
    }

    @GetMapping("/socialMedia")
    @Operation(summary = "this end point will getAll data's", description = "getAll data's")
    public BaseResponse<List<SocialMediaInContactResponse>> getAllSocialMedia() {
        return BaseResponse.success(service.getAllSocialMedia());
    }

    @PutMapping("/socialMedia")
    @Operation(summary = "this end point update social media links", description = "update social media links")
    public BaseResponse<Void> update(@RequestPart(required = false) SocialMediaInContactRequestUpdate socialMediaInContactRequest) {
        service.update(socialMediaInContactRequest);
        return BaseResponse.success();
    }
}
