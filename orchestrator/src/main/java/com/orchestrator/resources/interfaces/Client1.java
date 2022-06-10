package com.orchestrator.resources.interfaces;

import com.orchestrator.resources.dto.RequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "client1", path = "calc", url = "http://localhost:8081")
public interface Client1 {
    @PostMapping
    List<Integer> send(@RequestBody RequestDto numbers);
}

