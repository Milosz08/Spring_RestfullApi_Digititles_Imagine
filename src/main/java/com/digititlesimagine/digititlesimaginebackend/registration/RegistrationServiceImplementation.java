/*
 * Copyright (c) 2022 by MILOSZ GILGA <https://miloszgilga.pl>
 *
 * File name: RegistrationServiceImplementation.java
 * Last modified: 17/02/2022, 19:33
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

import com.digititlesimagine.digititlesimaginebackend.exceptions.ApiRequestException;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegistrationServiceImplementation implements RegistrationService {

    private final RegistrationRepository repository;

    public RegistrationServiceImplementation(RegistrationRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<RegistrationModel> getAllRegistrations() {
        return repository.findAll();
    }

    @Override
    public RegistrationModel getSingleRegistrationById(String id) {
        Optional<RegistrationModel> foundRegistration = repository.findRegistrationModelById(id);
        if (foundRegistration.isPresent()) {
            return foundRegistration.get();
        }
        throw new ApiRequestException("Registration from with id: '" + id + "' does not exist", HttpStatus.NOT_FOUND);
    }

    @Override
    public RegistrationModel addRegistration(RegistrationModel registration) {
        if (!registration.getEmail().contains("@")) {
            throw new ApiRequestException(
                "User email field has not valid template in registration form", HttpStatus.BAD_REQUEST
            );
        }
        return repository.save(registration);
    }

    @Override
    public ViewedRegistrationResponse setViewedRegistrationById(String id) {
        Optional<RegistrationModel> findingRegistration = repository.findRegistrationModelById(id);
        if (findingRegistration.isPresent()) {
            RegistrationModel foundElement = findingRegistration.get();
            foundElement.setId(id);
            if(foundElement.getPropertiesData().isIfViewed()) {
               throw new ApiRequestException("Registration form  with id: " + id + " was already viewed", HttpStatus.FORBIDDEN);
            }
            foundElement.getPropertiesData().setIfViewed(true);
            repository.save(foundElement);
            return new ViewedRegistrationResponse(foundElement.getId());
        }
        throw new ApiRequestException("Registration from with id: '" + id + "' does not exist", HttpStatus.NOT_FOUND);
    }

    @Override
    public void deleteSingleRegistrationById(String id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteAllRegistrations() {
        repository.deleteAll();
    }
}