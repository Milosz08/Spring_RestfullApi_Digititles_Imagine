/*
 * Copyright (c) 2022 by MILOSZ GILGA <https://miloszgilga.pl>
 *
 * File name: ReturnedViewedMessage.java
 * Last modified: 17/02/2022, 19:39
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

import lombok.*;

import java.util.LinkedHashMap;
import java.util.Map;

@Data
@ToString
public class ViewedRegistrationResponse {

    private String registrationId;
    private boolean ifViewed;
    private Map<String, Object> httpStatus;
    private long servletTime;

    public ViewedRegistrationResponse(String registrationId) {
        this.registrationId = registrationId;
        this.httpStatus =  settingHttpStatusReturnedObject();
        this.ifViewed = true;
        this.servletTime = System.currentTimeMillis();
    }

    private Map<String, Object> settingHttpStatusReturnedObject() {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("statusValue", HttpStatus.OK.value());
        body.put("statusDescription", "Registration form was viewed");
        return body;
    }

}