/*
 * Copyright (c) 2022 by MILOSZ GILGA <https://miloszgilga.pl>
 *
 * File name: RegistrationController.java
 * Last modified: 17/02/2022, 19:32
 * Project name: digititles-imagine-backend
 *
 * Licensed under the MIT license; you may not use this file except in compliance with the License.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * THE ABOVE COPYRIGHT NOTICE AND THIS PERMISSION NOTICE SHALL BE INCLUDED IN ALL
 * COPIES OR SUBSTANTIAL PORTIONS OF THE SOFTWARE.
 */

package com.digititlesimagine.digititlesimaginebackend.registration;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.digititlesimagine.digititlesimaginebackend.configurer.ServletConfigurer.*;

@RestController
@RequestMapping(APP_PREFIX + REGISTRATION)
@CrossOrigin
public class RegistrationController {

    private final RegistrationService service;

    public RegistrationController(RegistrationService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<RegistrationModel>> getAllRegistrations() {
        return new ResponseEntity<>(service.getAllRegistrations(), HttpStatus.OK);
    }

    @GetMapping("/{idValue}")
    public ResponseEntity<RegistrationModel> getSingleRegistration(@PathVariable String idValue) {
        return new ResponseEntity<>(service.getSingleRegistrationById(idValue), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RegistrationModel> addRegistrationForm(@Valid @RequestBody RegistrationModel registration) {
        return new ResponseEntity<>(service.addRegistration(registration), HttpStatus.CREATED);
    }

    @PutMapping("/viewed/{idValue}")
    public ResponseEntity<ViewedRegistrationResponse> setRegistrationViewed(@PathVariable String idValue) {
        return new ResponseEntity<>(service.setViewedRegistrationById(idValue), HttpStatus.OK);
    }

    @DeleteMapping("/{idValue}")
    public ResponseEntity<?> deleteSingleRegistration(@PathVariable String idValue) {
        service.deleteSingleRegistrationById(idValue);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAllRegistrations() {
        service.deleteAllRegistrations();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}