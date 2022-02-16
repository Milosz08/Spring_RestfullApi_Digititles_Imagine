/*
 * Copyright (c) 2022 by MILOSZ GILGA <https://miloszgilga.pl>
 *
 * File name: ProjectTyposModel.java
 * Last modified: 14/02/2022, 20:29
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

package com.digititlesimagine.digititlesimaginebackend.project.typos;

import com.digititlesimagine.digititlesimaginebackend.utils.AuditModelWithId;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;

@Data
@Entity
@ToString
@Table(name = "project_typos")
public class ProjectTyposModel extends AuditModelWithId {

    @Column(name = "font_family")
    @NotNull(message = "Blank field responsible for font family property")
    @Size(min = 2, max = 50, message = "Field responsible for font family should have from 2 to 20 characters")
    private String fontFamily;

    @Column(name = "font_type")
    @NotNull(message = "Blank field responsible for font type property")
    @Size(min = 2, max = 20, message = "Field responsible for font type should have from 2 to 20 characters")
    private String fontType;

    @Column(name = "font_size")
    @NotNull(message = "Blank field responsible for font size property")
    @Size(min = 2, message = "Field responsible for font size should have at least of 2 characters")
    private String fontSize;

    @Column(name = "line_height")
    @NotNull(message = "Blank field responsible for line height property")
    @Size(min = 2, message = "Field responsible for line height should have at least of 2 characters")
    private String lineHeight;

}