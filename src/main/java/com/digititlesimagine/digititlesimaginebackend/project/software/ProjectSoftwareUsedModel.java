/*
 * Copyright (c) 2022 by MILOSZ GILGA <https://miloszgilga.pl>
 *
 * File name: ProjectSoftwareUsedModel.java
 * Last modified: 14/02/2022, 21:13
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

package com.digititlesimagine.digititlesimaginebackend.project.software;

import com.digititlesimagine.digititlesimaginebackend.utils.AuditModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@ToString
@Table(name = "project_software_used")
public class ProjectSoftwareUsedModel extends AuditModel {

    @Id
    @Column(name = "software_used_key")
    @JsonIgnore
    private String id;

    @Column(name = "software_for")
    @NotNull(message = "Blank field responsible for software used for property")
    @Size(min = 5, message = "Field responsible for software used for should have at least of 5 characters")
    private String softwareFor;

    @Valid
    @ManyToOne(targetEntity = ProjectSoftwareModel.class, cascade = CascadeType.MERGE)
    @JoinColumn(name = "software_ref_key", referencedColumnName = "primary_key")
    @NotNull(message = "Blank field responsible for software info object")
    private ProjectSoftwareModel software = new ProjectSoftwareModel();

}