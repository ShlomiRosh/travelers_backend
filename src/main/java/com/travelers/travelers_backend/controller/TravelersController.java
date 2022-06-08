package com.travelers.travelers_backend.controller;

import com.travelers.travelers_backend.model.Traveler;
import com.travelers.travelers_backend.service.Redis;
import com.travelers.travelers_backend.util.InputValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/travelers")
public class TravelersController {

    @Autowired
    Redis redis;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<?> insertTraveler(@RequestBody Traveler traveler) {

        try {
            if (InputValidation.isValidId(traveler.getId())) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            redis.set(traveler.getId(), traveler.isGreen());
            return new ResponseEntity<>(traveler, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getTravelerStatus(@PathVariable String id) {
        try {
            if (InputValidation.isValidId(id)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            Object status = redis.get(id);
            boolean isGreen = Boolean.parseBoolean(status.toString());
            return new ResponseEntity<>(isGreen, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
