/*
 * Copyright (c) 2022 by MILOSZ GILGA <https://miloszgilga.pl>
 *
 * File name: ProjectPhotosServices.java
 * Last modified: 17/02/2022, 21:42
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

package com.digititlesimagine.digititlesimaginebackend.projectPhotosHandling;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public interface ProjectPhotosServices {

    Path init(String projectId);

    Map<String, Object> getAllProjectImages(String projectId, HttpServletRequest request);
    List<Map<String, Object>> getAllImagesFromAllProjects(HttpServletRequest request);
    Resource getImage(String projectId, String fileName);
    ProjectPhotosResponseModel saveImagesForSingleProject(MultipartFile[] files, String id);
    void deleteImagesFromSingleProject(String projectId);
    void deleteImagesFromAllProjects();

}