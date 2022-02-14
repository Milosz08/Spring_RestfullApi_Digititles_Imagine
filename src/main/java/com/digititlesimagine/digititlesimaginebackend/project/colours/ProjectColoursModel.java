/*
 * Copyright (c) 2022 by MILOSZ GILGA <https://miloszgilga.pl>
 *
 * File name: ProjectColoursModel.java
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

package com.digititlesimagine.digititlesimaginebackend.project.colours;

import com.digititlesimagine.digititlesimaginebackend.utils.AuditModelWithId;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.LinkedList;
import java.util.List;

@Data
@Entity
@ToString
@Table(name = "project_colours")
public class ProjectColoursModel extends AuditModelWithId {

    @Column(name = "main_background")
    @NotNull(message = "Blank field responsible for main background colour property")
    @Size(min = 4, max = 7, message = "Field responsible for main background colour must have from 4 to 7 characters")
    @Pattern(
        regexp = "^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$",
        message = "Correct format for main background colour field is #XXXXXX or #XXX in hexadecimal"
    )
    private String mainBackground;

    @Column(name = "main_header")
    @NotNull(message = "Blank field responsible for main header colour property")
    @Size(min = 4, max = 7, message = "Field responsible for main header colour must have from 4 to 7 characters")
    @Pattern(
        regexp = "^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$",
        message = "Correct format for main header colour field is #XXXXXX or #XXX in hexadecimal"
    )
    private String mainHeader;

    @Column(name = "tint_header")
    @NotNull(message = "Blank field responsible for tint header colour property")
    @Size(min = 4, max = 7, message = "Field responsible for tint header colour must have from 4 to 7 characters")
    @Pattern(
        regexp = "^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$",
        message = "Correct format for tint header colour field is #XXXXXX or #XXX in hexadecimal"
    )
    private String tintHeader;

    @Column(name = "paragr_foreground")
    @NotNull(message = "Blank field responsible for paragraph foreground colour property")
    @Size(min = 4, max = 7, message = "Field responsible for paragraph foreground colour must have from 4 to 7 characters")
    @Pattern(
        regexp = "^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$",
        message = "Correct format for paragraph foreground colour field is #XXXXXX or #XXX in hexadecimal"
    )
    private String paragrForeground;

    @Column(name = "tech_background")
    @NotNull(message = "Blank field responsible for technicals background colour property")
    @Size(min = 4, max = 7, message = "Field responsible for technicals background colour must have from 4 to 7 characters")
    @Pattern(
        regexp = "^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$",
        message = "Correct format for technicals background colour field is #XXXXXX or #XXX in hexadecimal"
    )
    private String techBackground;

    @OneToMany(targetEntity = ProjectCompositeColoursModel.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "project_colours_ref_key", referencedColumnName = "primary_key")
    @NotNull(message = "Empty array of objects responsible for storing project composite colours")
    private List<ProjectCompositeColoursModel> compositeColours = new LinkedList<>();

}