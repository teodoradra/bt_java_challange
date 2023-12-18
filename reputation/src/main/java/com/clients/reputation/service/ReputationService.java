package com.clients.reputation.service;

import com.clients.reputation.dto.ClientCheckRequest;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class ReputationService {

    public int getClientReputation(ClientCheckRequest clientCheckRequest){
        Random random = new Random();
        if (clientCheckRequest.getCnp().contains("12")) return random.nextInt(0,20);
        else if (clientCheckRequest.getCnp().contains("34")) return random.nextInt(21,99);
        else return random.nextInt(100,200);
    }
}
