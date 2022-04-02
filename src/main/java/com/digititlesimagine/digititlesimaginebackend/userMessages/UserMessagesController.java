/*
 * Copyright (c) 2022 by MILOSZ GILGA <https://miloszgilga.pl>
 *
 * File name: UserMessagesController.java
 * Last modified: 11/02/2022, 12:40
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

package com.digititlesimagine.digititlesimaginebackend.userMessages;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

import static com.digititlesimagine.digititlesimaginebackend.configurer.ServletConfigurer.*;

@RestController
@RequestMapping(APP_PREFIX + USER_MESSAGES)
@CrossOrigin
public class UserMessagesController {

    private final UserMessagesService userMessagesService;

    public UserMessagesController(UserMessagesService userMessagesService) {
        this.userMessagesService = userMessagesService;
    }

    @GetMapping
    public ResponseEntity<List<UserMessagesModel>> getAllUserMessages() {
        return new ResponseEntity<>(userMessagesService.getAllUserMessages(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserMessagesModel> addUserMessage(@Valid @RequestBody UserMessagesModel userMessagesModel) {
        return new ResponseEntity<>(userMessagesService.addUserMessage(userMessagesModel), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    ResponseEntity<UserMessageUpdatable> updateUserMessageStatus(@PathVariable String id) {
        return new ResponseEntity<>(userMessagesService.updateUserMessageStatus(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteSingleUserMessage(@PathVariable String id) {
        userMessagesService.deleteSingleUserMessageById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    ResponseEntity<Void> deleteAllUserMessages() {
        userMessagesService.deleteAllUserMessages();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}