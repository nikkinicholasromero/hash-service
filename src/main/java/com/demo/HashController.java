package com.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HashController {
    @Autowired
    private HashService hashService;

    @GetMapping("salt")
    public String getRandomSalt() {
        return hashService.generateRandomSalt();
    }

    @GetMapping("hash")
    public String hash(@RequestParam("clear") String clear, @RequestParam("salt") String salt) {
        return hashService.hash(clear, salt);
    }
}
