/*
 * Copyright (c) 2022 by MILOSZ GILGA <https://miloszgilga.pl>
 *
 * File name: ProjectController.java
 * Last modified: 15/02/2022, 14:38
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

import com.digititlesimagine.digititlesimaginebackend.projectPhotosHandling.ProjectPhotosServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.digititlesimagine.digititlesimaginebackend.configurer.ServletConfigurer.*;

@RestController
@RequestMapping(APP_PREFIX + PROJECTS)
@CrossOrigin
public class ProjectController {

    @Autowired
    private ProjectServices services;
    @Autowired
    private ProjectPhotosServices photosServices;

    @GetMapping
    public ResponseEntity<List<ProjectModel>> getAllProjects() {
        return new ResponseEntity<>(services.getAllProjects(), HttpStatus.OK);
    }

    @GetMapping("/{idValue}")
    public ResponseEntity<ProjectModel> getSingleProjectById(@PathVariable String idValue) {
        return new ResponseEntity<>(services.getSingleProjectById(idValue), HttpStatus.OK);
    }

    @GetMapping("/title/{titleValue}")
    public ResponseEntity<ProjectModel> getSingleProjectByPath(@PathVariable String titleValue) {
        return new ResponseEntity<>(services.getSingleProjectByPath(titleValue), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProjectModel> addSingleProject(@Valid @RequestBody ProjectModel project) {
        return new ResponseEntity<>(services.addSingleProject(project), HttpStatus.CREATED);
    }

    @PutMapping("/{idValue}")
    public ResponseEntity<ProjectModel> editSingleProject(
            @Valid @RequestBody ProjectModel project, @PathVariable String idValue
    ) {
        return new ResponseEntity<>(services.editSingleProject(idValue, project), HttpStatus.OK);
    }

    @DeleteMapping("/{idValue}")
    public ResponseEntity<?> deleteSingleProject(@PathVariable String idValue) {
        services.deleteSingleProject(idValue);
        photosServices.deleteImagesFromSingleProject(idValue);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAllProjects() {
        services.deleteAllProjects();
        photosServices.deleteImagesFromAllProjects();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}