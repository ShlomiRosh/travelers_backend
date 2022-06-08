package com.travelers.travelers_backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/travelers")
public class TravelersController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<?> hello() {
        return ResponseEntity.ok("Hello World!");
    }
}
