/*
 * Copyright (c) 2022 by MILOSZ GILGA <https://miloszgilga.pl>
 *
 * File name: RegistrationModel.java
 * Last modified: 17/02/2022, 19:16
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

import com.digititlesimagine.digititlesimaginebackend.registration.registrationProperties.RegistrationPropertiesModel;
import com.digititlesimagine.digititlesimaginebackend.utils.AuditModelWithId;

import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;

@Data
@Entity
@ToString
@Table(name = "registration")
public class RegistrationModel extends AuditModelWithId {

    @Column(name = "user_name")
    @NotNull(message = "Blank field responsible for user name property")
    @Size(min = 2, max = 30, message = "Field responsible for user name should have from 2 to 30 characters")
    private String username;

    @Column(name = "user_lastname")
    @NotNull(message = "Blank field responsible for user lastname property")
    private String lastname;

    @Column(name = "user_email")
    @NotNull(message = "Blank field responsible for user email property")
    @Size(min = 5, max = 30, message = "Field responsible for user lastname should have from 5 to 30 characters")
    private String email;

    @Column(name = "user_message")
    @NotNull(message = "Blank field responsible for user message property")
    @Size(min = 10, max = 300, message = "Field responsible for user message should have from 10 to 300 characters")
    private String message;

    @Valid
    @OneToOne(targetEntity = RegistrationPropertiesModel.class, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "properties_ref_key", referencedColumnName = "primary_key")
    @NotNull(message = "Blank object responsible for storing registration properties data")
    private RegistrationPropertiesModel propertiesData = new RegistrationPropertiesModel();

}