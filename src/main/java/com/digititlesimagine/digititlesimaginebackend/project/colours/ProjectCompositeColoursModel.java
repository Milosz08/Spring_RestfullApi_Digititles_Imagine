/*
 * Copyright (c) 2022 by MILOSZ GILGA <https://miloszgilga.pl>
 *
 * File name: ProjectColoursTechnicalModel.java
 * Last modified: 14/02/2022, 22:25
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

package com.digititlesimagine.digititlesimaginebackend.project.colours;

import com.digititlesimagine.digititlesimaginebackend.utils.AuditModelExcludeIDInResponse;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Entity
@ToString
@Table(name = "project_composite_colours")
public class ProjectCompositeColoursModel extends AuditModelExcludeIDInResponse {

    @Column(name = "composite_colour")
    @NotNull(message = "Blank field responsible for composite colour colour property")
    @Size(min = 4, max = 7, message = "Field responsible for composite colour must have from 4 to 7 characters")
    @Pattern(
        regexp = "^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$",
        message = "Correct format for composite colour field is #XXXXXX or #XXX in hexadecimal"
    )
    private String compositeColour;

}