package com.day4.spring.security.task1.circuitbreaker;

import com.day4.spring.security.task1.bukuLog.BukuModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientBukuController {

    @Autowired
    private ClientBukuService service;

    @PostMapping("api/submit-buku")
    public String submitCustomer(@RequestBody BukuModel buku) {
        return service.submitBuku(buku);
    }
}
