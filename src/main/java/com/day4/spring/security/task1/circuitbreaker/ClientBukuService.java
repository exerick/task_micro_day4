package com.day4.spring.security.task1.circuitbreaker;

import com.day4.spring.security.task1.bukuLog.BukuModel;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ClientBukuService {
    private final String apiUrl = "http://localhost:8080/buku";

    @CircuitBreaker(name = "bukuService", fallbackMethod = "submitBukuError")
    public String submitBuku(BukuModel buku) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, buku, String.class);
        return response.getBody();
    }

    public String submitBukuError(BukuModel buku) {
        return "service buku down ";
    }
}
