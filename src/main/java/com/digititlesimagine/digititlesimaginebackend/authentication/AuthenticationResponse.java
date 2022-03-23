/*
 * Copyright (c) 2022 by MILOSZ GILGA <https://miloszgilga.pl>
 *
 * File name: AuthenticationResponse.java
 * Last modified: 12/02/2022, 01:28
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
import com.digititlesimagine.digititlesimaginebackend.utils.*;
import lombok.*;
import org.springframework.http.*;

import java.util.*;

@Data
@ToString
public class AuthenticationResponse {

    private String status;
    private String bearerToken;
    private Enums.Authentications authLevel;
    private Map<String, Object> errors;
    private long servletTime;

    public AuthenticationResponse(
            String status, String bearerToken, Enums.Authentications authLevel, List<Boolean> valid
    ) {
        this.status = status;
        this.bearerToken = bearerToken;
        this.authLevel = authLevel;
        this.servletTime = System.currentTimeMillis();
        this.errors = generateAllErrors(valid);
    }

    private Map<String, Object> generateAllErrors(List<Boolean> valid) {
        Map<String, Object> credentialsFields = new LinkedHashMap<>();
        String[] allKeys = { "username", "password" };
        if(valid.isEmpty() || valid.size() > 3) {
            throw new ApiRequestException("Credentials data fields have bad size", HttpStatus.BAD_REQUEST);
        }
        for(int i = 0; i < allKeys.length; i++) {
            credentialsFields.put(allKeys[i], !valid.get(i));
        }
        return credentialsFields;
    }

}