/*
 * Copyright (c) 2022 by MILOSZ GILGA <https://miloszgilga.pl>
 *
 * File name: ProjectGeneralModel.java
 * Last modified: 14/02/2022, 17:50
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

package com.digititlesimagine.digititlesimaginebackend.project;

import com.digititlesimagine.digititlesimaginebackend.project.aboutAndProduction.*;
import com.digititlesimagine.digititlesimaginebackend.project.colours.ProjectColoursModel;
import com.digititlesimagine.digititlesimaginebackend.project.render.ProjectRenderModel;
import com.digititlesimagine.digititlesimaginebackend.project.software.ProjectSoftwareUsedModel;
import com.digititlesimagine.digititlesimaginebackend.project.typos.ProjectTyposModel;
import com.digititlesimagine.digititlesimaginebackend.utils.AuditModelWithId;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import com.fasterxml.jackson.annotation.*;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;

@Data
@Entity
@ToString
@Table(name = "project")
public class ProjectModel extends AuditModelWithId {

    @Column(name = "project_title")
    @NotNull(message = "Blank field responsible for project title property")
    @Size(min = 2, max = 100, message = "Field responsible for project title should have from 2 to 100 characters")
    private String title;

    @Column(name = "title_path")
    private String projectPath;

    @Column(name = "embed_code")
    @NotNull(message = "Blank field responsible for project embed code property")
    @NotEmpty(message = "Empty field responsible for project embed code property")
    private String embedCode;

    @Column(name = "prod_year")
    @NotNull(message = "Blank field responsible for project production year code property")
    private int prodYear;

    @Column(name = "prod_company")
    @NotNull(message = "Blank field responsible for project production company code property")
    @NotEmpty(message = "Empty field responsible for project production company code property")
    private String prodCompany;

    @Valid
    @OneToMany(targetEntity = ProjectAboutModel.class, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "project_ref_key", referencedColumnName = "primary_key")
    @NotNull(message = "Empty array of objects responsible for storing project about section")
    private List<ProjectAboutModel> aboutSection = new LinkedList<>();

    @Valid
    @OneToMany(targetEntity = ProjectProductionModel.class, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "project_ref_key", referencedColumnName = "primary_key")
    @NotNull(message = "Empty array of objects responsible for storing project production section")
    private List<ProjectProductionModel> prodSection = new LinkedList<>();

    @Valid
    @OneToOne(targetEntity = ProjectTyposModel.class, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "typos_ref_key", referencedColumnName = "primary_key")
    @NotNull(message = "Blank object responsible for storing project typography")
    private ProjectTyposModel typography = new ProjectTyposModel();

    @Valid
    @OneToOne(targetEntity = ProjectRenderModel.class, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "render_ref_key", referencedColumnName = "primary_key")
    @NotNull(message = "Blank object responsible for storing project render properties")
    private ProjectRenderModel renderProps = new ProjectRenderModel();

    @Valid
    @OneToOne(targetEntity = ProjectColoursModel.class, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "colours_ref_key", referencedColumnName = "primary_key")
    @NotNull(message = "Blank object responsible for storing project colours")
    private ProjectColoursModel projectColours = new ProjectColoursModel();

    @Valid
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
        name = "software_binding",
        joinColumns = @JoinColumn(name = "project_id", referencedColumnName = "primary_key"),
        inverseJoinColumns = @JoinColumn(name = "software_used_key")
    )
    @NotNull(message = "Empty array of objects responsible for storing used software in project")
    private List<ProjectSoftwareUsedModel> usedSoftware = new LinkedList<>();

}