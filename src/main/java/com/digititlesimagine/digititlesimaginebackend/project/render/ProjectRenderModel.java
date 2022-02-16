/*
 * Copyright (c) 2022 by MILOSZ GILGA <https://miloszgilga.pl>
 *
 * File name: ProjectRenderModel.java
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

package com.digititlesimagine.digititlesimaginebackend.project.render;

import com.digititlesimagine.digititlesimaginebackend.utils.AuditModelExcludeIDInResponse;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;

@Data
@Entity
@ToString
@Table(name = "project_render")
public class ProjectRenderModel extends AuditModelExcludeIDInResponse {

    @Column(name = "rendering_time")
    @NotNull(message = "Blank field responsible for rendering time property")
    private float renderingTime;

    @Column(name = "sampling_codec")
    @NotNull(message = "Blank field responsible for sampling codec property")
    @Size(min = 2, max = 10, message = "Field responsible for sampling codec should have from 2 to 10 characters")
    private String samplingCodec;

    @Column(name = "native_res")
    @NotNull(message = "Blank field responsible for native resolution property")
    @Size(min = 7, max = 9, message = "Field responsible for native resolution should have from 2 to 10 characters")
    private String nativeResolution;

    @Column(name = "short_res")
    @NotNull(message = "Blank field responsible for short name of resolution property")
    @Size(min = 2, max = 2, message = "Field responsible for short name of resolution should have from 2 characters")
    private String shortResolution;

    @Column(name = "if_imax")
    private boolean ifImax = false;

    @Column(name = "aspect_ratio")
    @NotNull(message = "Blank field responsible for aspect ratio property")
    private float aspectRatio;

}