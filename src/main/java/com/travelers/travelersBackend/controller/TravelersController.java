package com.travelers.travelersBackend.controller;

import com.travelers.travelersBackend.model.Traveler;
import com.travelers.travelersBackend.service.Redis;
import com.travelers.travelersBackend.util.CustomException;
import com.travelers.travelersBackend.util.InputValidation;
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

        InputValidation.validate(traveler);
        redis.set(traveler.getId(), traveler.isGreen());
        return new ResponseEntity<>(traveler, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getTravelerStatus(@PathVariable String id) {

            Object status = redis.get(id);
            if (status == null) throw new CustomException("Status with id: " + id + " not found");
            boolean isGreen = Boolean.parseBoolean(status.toString());
            return new ResponseEntity<>(isGreen, HttpStatus.OK);

    }
}
