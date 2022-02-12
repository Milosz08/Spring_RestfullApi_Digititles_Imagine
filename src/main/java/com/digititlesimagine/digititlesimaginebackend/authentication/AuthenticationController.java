/*
 * Copyright (c) 2022 by MILOSZ GILGA <https://miloszgilga.pl>
 *
 * File name: AuthenticationsController.java
 * Last modified: 12/02/2022, 00:42
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

import com.digititlesimagine.digititlesimaginebackend.utils.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.validation.annotation.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;

import static com.digititlesimagine.digititlesimaginebackend.utils.ServletConfigurer.*;

@RestController
@RequestMapping(APP_PREFIX + AUTHENTICATIONS)
@CrossOrigin
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/{role}")
    public ResponseEntity<AuthenticationModel> getSingleAuthentication(@PathVariable Enums.Authentications role) {
        return new ResponseEntity<>(authenticationService.getSingleCmsUser(role), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AuthenticationModel> addSingleAuthentication(@Validated @RequestBody AuthenticationModel model) {
        return new ResponseEntity<>(authenticationService.addSingleCmsUser(model), HttpStatus.CREATED);
    }

    @PostMapping("/validate")
    public ResponseEntity<AuthenticationResponse> validateCredentials(@Validated @RequestBody AuthenticationModel model) {
        return authenticationService.validateCredentials(model);
    }

    @PutMapping("/{role}")
    public ResponseEntity<AuthenticationModel> editSingleAuthneticatin(
            @PathVariable Enums.Authentications role, @Validated @RequestBody AuthenticationModel model
    ) {
        return new ResponseEntity<>(authenticationService.editSingleCmsUser(role, model), HttpStatus.OK);
    }

}