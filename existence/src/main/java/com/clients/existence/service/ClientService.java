package com.clients.existence.service;

import com.clients.existence.dto.ClientCheckRequest;
import com.clients.existence.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public boolean checkClientExistence(ClientCheckRequest clientCheckRequest){
        return clientRepository.findById(clientCheckRequest.getCnp()).isPresent();
    }
}
