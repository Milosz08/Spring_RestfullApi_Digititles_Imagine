/*
 * Copyright (c) 2022 by MILOSZ GILGA <https://miloszgilga.pl>
 *
 * File name: UserMessagesModel.java
 * Last modified: 11/02/2022, 12:36
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

import com.digititlesimagine.digititlesimaginebackend.utils.*;
import com.fasterxml.jackson.annotation.*;

import lombok.*;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "user_messages")
public class UserMessagesModel extends AuditModel {

    @Id
    @GenericGenerator(
        name = "user_messages_id",
        strategy = "com.digititlesimagine.digititlesimaginebackend.utils.CustomIdGenerator"
    )
    @GeneratedValue(generator = "user_messages_id")
    @Column(name = "user_messages_id")
    private String id;

    @Column(name = "user_name")
    @NotNull(message = "Blank field responsible for user name property")
    @Size(min = 3, max = 30, message = "Field responsible for user name should have from 3 to 30 characters")
    private String userName;

    @Column(name = "user_lastname")
    private String userLastname = "";

    @Column(name = "user_email")
    @NotEmpty(message = "Blank/empty field responsible for user email property")
    private String userEmail;

    @Column(name = "if_viewed")
    private boolean ifViewed = false;

    @Column(name = "user_message")
    @NotNull(message = "Blank field responsible for user message property")
    @Size(min = 10, max = 300, message = "Field responsible for user message should have from 10 to 300 characters")
    private String userMessage;

}