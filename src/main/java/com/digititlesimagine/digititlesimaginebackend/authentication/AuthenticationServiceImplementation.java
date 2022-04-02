/*
 * Copyright (c) 2022 by MILOSZ GILGA <https://miloszgilga.pl>
 *
 * File name: AuthenticationsServiceImplementation.java
 * Last modified: 12/02/2022, 00:43
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

package com.digititlesimagine.digititlesimaginebackend.authentication;

import com.digititlesimagine.digititlesimaginebackend.exceptions.*;
import com.digititlesimagine.digititlesimaginebackend.jwt.*;
import com.digititlesimagine.digititlesimaginebackend.utils.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.security.crypto.password.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class AuthenticationServiceImplementation implements AuthenticationService {

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationRepository authenticationRepository;
    private final DbUserDetailsService dbUserDetailsService;
    private final JwtUtil jwtUtil;

    public AuthenticationServiceImplementation(
            PasswordEncoder passwordEncoder,
            AuthenticationRepository authenticationRepository,
            DbUserDetailsService dbUserDetailsService,
            JwtUtil jwtUtil
    ) {
        this.passwordEncoder = passwordEncoder;
        this.authenticationRepository = authenticationRepository;
        this.dbUserDetailsService = dbUserDetailsService;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public AuthenticationModel getSingleCmsUser(Enums.Authentications role) {
        Optional<AuthenticationModel> findUserByRole = authenticationRepository.findAuthenticationsModelByRole(role);
        if (findUserByRole.isPresent()) {
            return findUserByRole.get();
        }
        throw new ApiRequestException("User with role: '" + role + "' does not exist", HttpStatus.NOT_FOUND);
    }

    @Override
    public AuthenticationModel addSingleCmsUser(AuthenticationModel model) {
        return authenticationRepository.save(encodeFields(model));
    }

    @Override
    public AuthenticationModel editSingleCmsUser(Enums.Authentications role, AuthenticationModel model) {
        Optional<AuthenticationModel> findCmsUserByRole = authenticationRepository.findAuthenticationsModelByRole(role);
        if (findCmsUserByRole.isPresent()) {
            model.setId(findCmsUserByRole.get().getId());
            return authenticationRepository.save(encodeFields(model));
        }
        throw new ApiRequestException("User with role: '" + role + "' does not exist", HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<AuthenticationResponse> validateCredentials(AuthenticationModel model) {
        String name = model.getUsername();
        Optional<AuthenticationModel> findByUsername = authenticationRepository.findAuthenticationModelByUsername(name);
        if (findByUsername.isPresent()) {
            AuthenticationModel foundModel = findByUsername.get();
            Enums.Authentications role = foundModel.getRole();
            List<Boolean> valid = validateAllFields(model, findByUsername.get());
            if (valid.stream().allMatch(item -> item)) {
                String bearer = "";
                if (role != Enums.Authentications.UNDEFINED) {
                    UserDetails userDetails = dbUserDetailsService.loadUserByUsername(model.getUsername());
                    bearer = jwtUtil.generateToken(userDetails);
                    return new ResponseEntity<>(
                        new AuthenticationResponse("Authorised", bearer, role, valid), HttpStatus.ACCEPTED
                    );
                }
            }
            return new ResponseEntity<>(
                new AuthenticationResponse("Not authorised", null, role, valid), HttpStatus.FORBIDDEN
            );
        }
        List<Boolean> allFalse = Arrays.asList(false, false, false);
        return new ResponseEntity<>(
            new AuthenticationResponse("User not found", null, null, allFalse),
            HttpStatus.NOT_FOUND
        );
    }

    private List<Boolean> validateAllFields(AuthenticationModel model, AuthenticationModel foundModel) {
        List<Boolean> returnList = new ArrayList<>();
        returnList.add(model.getUsername().equals(foundModel.getUsername()));
        returnList.add(BCrypt.checkpw(model.getPassword(), foundModel.getPassword()));
        return returnList;
    }

    private AuthenticationModel encodeFields(AuthenticationModel model) {
        model.setPassword(passwordEncoder.encode(model.getPassword()));
        return model;
    }

}