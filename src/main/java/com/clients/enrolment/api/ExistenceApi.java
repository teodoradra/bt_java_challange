package com.clients.enrolment.api;

import com.clients.enrolment.dto.ClientCheckRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(url = "$existence-url", name = "existenceApi")
public interface ExistenceApi {
    @PostMapping(path = "/client/existence")
    Boolean checkClientExistence(@RequestBody ClientCheckRequest clientCheckRequest);
}
