package com.clients.enrolment.api;

import com.clients.enrolment.dto.ClientCheckRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(url = "${reputation-url}", name = "reputationApi")
public interface ReputationApi {
    @PostMapping(path = "/client/reputation")
    Integer getClientReputation(@RequestBody ClientCheckRequest clientCheckRequest);
}
