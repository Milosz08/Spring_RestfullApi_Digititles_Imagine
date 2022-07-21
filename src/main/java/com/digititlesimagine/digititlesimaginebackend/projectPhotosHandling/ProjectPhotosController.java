/*
 * Copyright (c) 2022 by MILOSZ GILGA <https://miloszgilga.pl>
 *
 * File name: ProjectPhotosController.java
 * Last modified: 17/02/2022, 21:27
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
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;
import java.util.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.servlet.http.HttpServletRequest;

import static com.digititlesimagine.digititlesimaginebackend.configurer.ServletConfigurer.*;
import static com.digititlesimagine.digititlesimaginebackend.utils.FileManagement.FILE_SEPARATOR;
import static com.digititlesimagine.digititlesimaginebackend.utils.FileManagement.getImagesFolderPath;

@RestController
@RequestMapping(APP_PREFIX + PHOTOS)
@CrossOrigin
public class ProjectPhotosController {

    private final ProjectPhotosServices services;

    public ProjectPhotosController(ProjectPhotosServices services) {
        this.services = services;
    }

    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> getAllFilesFromAllProjects(HttpServletRequest request) {
        return new ResponseEntity<>(services.getAllImagesFromAllProjects(request), HttpStatus.OK);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<Map<String, Object>> getAllFilesFromOneProject(
            @PathVariable String projectId, HttpServletRequest request
    ) {
        return new ResponseEntity<>(services.getAllProjectImages(projectId, request), HttpStatus.OK);
    }

    @GetMapping("/{projectId}/{fileName:.+}")
    public ResponseEntity<Resource> getFileProjectByIdAndName(@PathVariable String projectId, @PathVariable String fileName) {
        Resource file = services.getImage(projectId, fileName);
        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
            .body(file);
    }

    @GetMapping(value = "/java-upload/{projectId}/{filename}", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] download(@PathVariable String projectId, @PathVariable String filename) throws IOException {
        return Files.readAllBytes(Paths.get(getImagesFolderPath() + projectId + FILE_SEPARATOR + filename));
    }

    @PostMapping(value = "/upload/{projectId}", consumes = { "multipart/form-data" })
    public ResponseEntity<ProjectPhotosResponseModel> fileUpload(
            @RequestParam("files") MultipartFile[] files, @PathVariable String projectId
    ) {
        return new ResponseEntity<>(services.saveImagesForSingleProject(files, projectId), HttpStatus.CREATED);
    }

}