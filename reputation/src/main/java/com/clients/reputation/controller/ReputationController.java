package com.clients.reputation.controller;

import com.clients.reputation.dto.ClientCheckRequest;
import com.clients.reputation.service.ReputationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReputationController {

    @Autowired
    private ReputationService reputationService;

    @PostMapping("/client/reputation")
    public ResponseEntity<Integer> getClientReputation(@RequestBody ClientCheckRequest clientCheckRequest){
        return ResponseEntity.ok(reputationService.getClientReputation(clientCheckRequest));
    }
}
