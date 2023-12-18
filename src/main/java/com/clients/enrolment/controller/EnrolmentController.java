package com.clients.enrolment.controller;

import com.clients.enrolment.dto.ClientCheckRequest;
import com.clients.enrolment.dto.ClientCheckResponse;
import com.clients.enrolment.dto.Document;
import com.clients.enrolment.service.ClientCheckService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Validated
@RequestMapping("/enrolment")
public class EnrolmentController {
    @Autowired
    private ClientCheckService clientCheckService;

    @PostMapping("/client-check")
    public ResponseEntity<ClientCheckResponse> performClientCheck(@Valid @RequestBody ClientCheckRequest request) {
        ClientCheckResponse response = clientCheckService.performClientCheck(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/generate", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> generateDocument(@RequestParam("type") Document documentType) {
        return new ResponseEntity<>(clientCheckService.getDocument(documentType), HttpStatus.OK);
    }

    @PostMapping("/submit")
    public ResponseEntity<String> submitDocument(@RequestBody MultipartFile file) {
        // more actions to upload the file
        return (file.isEmpty()) ? ResponseEntity.badRequest().body("Empty file!") :
                ResponseEntity.ok("Successfully uploaded the file");
    }
}
