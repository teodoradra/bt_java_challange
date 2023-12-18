package com.clients.existence.controller;

import com.clients.existence.dto.ClientCheckRequest;
import com.clients.existence.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientExistenceController {

    @Autowired
    private ClientService clientService;

    @PostMapping("/client/existence")
    public ResponseEntity<Boolean> checkClientExistence(@RequestBody ClientCheckRequest clientCheckRequest){
        return ResponseEntity.ok(clientService.checkClientExistence(clientCheckRequest));
    }
}
