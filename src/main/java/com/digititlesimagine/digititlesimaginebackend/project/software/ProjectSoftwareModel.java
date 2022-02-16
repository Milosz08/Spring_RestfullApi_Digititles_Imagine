/*
 * Copyright (c) 2022 by MILOSZ GILGA <https://miloszgilga.pl>
 *
 * File name: ProjectSoftwareModel.java
 * Last modified: 14/02/2022, 21:03
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
import com.digititlesimagine.digititlesimaginebackend.utils.AuditModelExcludeIDInResponse;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;

@Data
@Entity
@ToString
@Table(name = "project_software")
public class ProjectSoftwareModel extends AuditModel {

    @Id
    @Column(name = "primary_key")
    @JsonIgnore
    private String id;

    @Column(name = "software_name")
    @NotNull(message = "Blank field responsible for software name property")
    @Size(min = 2, max = 100, message = "Field responsible for software name should have from 2 to 100 characters")
    private String softwareFullName;

    @Column(name = "software_short")
    @NotNull(message = "Blank field responsible for software short name property")
    @Size(min = 2, max = 2, message = "Field responsible for software short name should have from 2 characters")
    private String softwareShortName;

}