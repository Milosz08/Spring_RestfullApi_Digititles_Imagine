/*
 * Copyright (c) 2022 by MILOSZ GILGA <https://miloszgilga.pl>
 *
 * File name: UserMessagesServicesImplementation.java
 * Last modified: 11/02/2022, 19:16
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

import com.digititlesimagine.digititlesimaginebackend.exceptions.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class UserMessagesServiceImplementation implements UserMessagesService {

    @Autowired
    private UserMessagesRepository userMessagesRepository;

    @Override
    public List<UserMessagesModel> getAllUserMessages() {
        return userMessagesRepository.findAll();
    }

    @Override
    public UserMessagesModel addUserMessage(UserMessagesModel userMessagesModel) {
        String emailField = userMessagesModel.getUserEmail();
        if (!emailField.contains("@")) {
            throw new ApiRequestException("Email address is not valid!", HttpStatus.BAD_REQUEST);
        }
        return userMessagesRepository.save(userMessagesModel);
    }

    @Override
    public UserMessageUpdatable updateUserMessageStatus(String messageId) {
        Optional<UserMessagesModel> findingMessage = userMessagesRepository.findUserMessagesModelById(messageId);
        if (findingMessage.isPresent()) {
            findingMessage.get().setIfViewed(true);
            userMessagesRepository.save(findingMessage.get());
            return new UserMessageUpdatable(messageId, findingMessage.get().isIfViewed());
        }
        throw new ApiRequestException("Message at searched id: '" + messageId + "' does not exist", HttpStatus.NOT_FOUND);
    }

    @Override
    public void deleteSingleUserMessageById(String id) {
        Optional<UserMessagesModel> findingMessage = userMessagesRepository.findUserMessagesModelById(id);
        if (findingMessage.isPresent()) {
            userMessagesRepository.deleteById(id);
        } else {
            throw new ApiRequestException("Message at searched id: '" + id + "' does not exist", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void deleteAllUserMessages() {
        userMessagesRepository.deleteAll();
    }

}