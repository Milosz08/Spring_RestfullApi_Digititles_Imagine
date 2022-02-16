/*
 * Copyright (c) 2022 by MILOSZ GILGA <https://miloszgilga.pl>
 *
 * File name: ProductionAboutAuditModel.java
 * Last modified: 14/02/2022, 20:30
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

package com.digititlesimagine.digititlesimaginebackend.project.aboutAndProduction;

import com.digititlesimagine.digititlesimaginebackend.utils.AuditModelExcludeIDInResponse;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;

@Data
@ToString
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class ProductionAboutAuditModel extends AuditModelExcludeIDInResponse {

    @Column(name = "paragraph_iter")
    private int paragraphIter;

    @Column(name = "project_paragraph")
    @NotNull(message = "Blank field responsible for paragraph property")
    @Size(min = 10, max = 500, message = "Field responsible for paragraph should have from 10 to 500 characters")
    private String paragraph;

}