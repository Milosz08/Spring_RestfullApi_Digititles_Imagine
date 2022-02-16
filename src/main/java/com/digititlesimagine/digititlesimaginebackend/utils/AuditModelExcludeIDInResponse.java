/*
 * Copyright (c) 2022 by MILOSZ GILGA <https://miloszgilga.pl>
 *
 * File name: AuditModelExcludeIDInResponse.java
 * Last modified: 16/02/2022, 19:55
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

package com.digititlesimagine.digititlesimaginebackend.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.*;
import javax.persistence.*;

@Data
@ToString
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AuditModelExcludeIDInResponse extends AuditModel {

    @Id
    @GenericGenerator(
        name = "primary_key",
        strategy = "com.digititlesimagine.digititlesimaginebackend.utils.CustomIdGenerator"
    )
    @GeneratedValue(generator = "primary_key")
    @JsonIgnore
    @Column(name = "primary_key", length = 20)
    private String id;

}