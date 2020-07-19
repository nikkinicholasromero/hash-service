package com.demo.controller;

import com.demo.service.HashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HashController {
    @Autowired
    private HashService hashService;

    @GetMapping("salt")
    public String generateRandomSalt() {
        return hashService.generateRandomSalt();
    }

    @PostMapping("hash")
    public String hash(@RequestBody HashRequest hashRequest) {
        return hashService.hash(hashRequest.getClear(), hashRequest.getSalt());
    }
}
