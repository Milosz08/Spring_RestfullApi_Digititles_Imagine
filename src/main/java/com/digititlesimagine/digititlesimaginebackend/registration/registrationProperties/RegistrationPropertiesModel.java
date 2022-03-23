/*
 * Copyright (c) 2022 by MILOSZ GILGA <https://miloszgilga.pl>
 *
 * File name: RegistrationUserDataModel.java
 * Last modified: 17/02/2022, 19:18
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

package com.digititlesimagine.digititlesimaginebackend.registration.registrationProperties;

import com.digititlesimagine.digititlesimaginebackend.utils.AuditModelExcludeIDInResponse;
import com.digititlesimagine.digititlesimaginebackend.utils.Enums;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;

@Data
@Entity
@ToString
@Table(name = "registration_properties")
public class RegistrationPropertiesModel extends AuditModelExcludeIDInResponse {

    @Column(name = "service_type")
    @NotNull(message = "Blank field responsible for service type property")
    private String serviceType;

    @Column(name = "filmmaker_size")
    @NotNull(message = "Blank field responsible for filmmaker size property")
    private String filmmakerSize;

    @Column(name = "filmmaker_budget")
    @NotNull(message = "Blank field responsible for filmmaker budget property")
    private int filmmakerBudget;

    @Column(name = "if_viewed")
    private boolean ifViewed = false;

}