/*
 * Copyright (c) 2022 by MILOSZ GILGA <https://miloszgilga.pl>
 *
 * File name: ProjectServicesImplementation.java
 * Last modified: 15/02/2022, 14:37
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

import com.digititlesimagine.digititlesimaginebackend.exceptions.ApiRequestException;

import com.digititlesimagine.digititlesimaginebackend.project.software.ProjectSoftwareRepository;
import com.digititlesimagine.digititlesimaginebackend.project.software.ProjectSoftwareUsedRepository;
import com.digititlesimagine.digititlesimaginebackend.utils.AuditModel;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProjectServicesImplementation extends ServicesImplementationHelpers implements ProjectServices {

    public ProjectServicesImplementation(
            ProjectRepository repository,
            ProjectSoftwareRepository softRepository,
            ProjectSoftwareUsedRepository usedSoftRepository
    ) {
        super(repository, softRepository, usedSoftRepository);
    }

    @Override
    public List<ProjectModel> getAllProjects() {
        List<ProjectModel> reversed = repository.findAll();
        Collections.reverse(reversed);
        for(ProjectModel singleProject : reversed) {
            sortAboutAndProductionParagraphs(singleProject);
        }
        reversed.sort(Comparator.comparing(AuditModel::getCreatedAt));
        return reversed;
    }

    @Override
    public ProjectModel getSingleProjectById(String id) {
        Optional<ProjectModel> foundProject = repository.findProjectModelById(id);
        if (foundProject.isPresent()) {
            ProjectModel sortedProject = foundProject.get();
            sortAboutAndProductionParagraphs(sortedProject);
            return sortedProject;
        }
        throw new ApiRequestException("Project with id: '" + id + "' does not exist", HttpStatus.NOT_FOUND);
    }

    @Override
    public ProjectModel getSingleProjectByPath(String path) {
        Optional<ProjectModel> foundProject = repository.findProjectModelByPath(path);
        if (foundProject.isPresent()) {
            ProjectModel sortedProject = foundProject.get();
            sortAboutAndProductionParagraphs(sortedProject);
            return sortedProject;
        }
        throw new ApiRequestException("Project with title path: '" + path + "' does not exist", HttpStatus.NOT_FOUND);
    }

    @Override
    public ProjectModel addSingleProject(ProjectModel project) {
        ProjectModel savedData = saveSingleProjectSoftCascade(project);
        sortAboutAndProductionParagraphs(savedData);
        savedData.setProjectPath(generateProjectPath(savedData.getTitle()));
        return savedData;
    }

    @Override
    public ProjectModel editSingleProject(String id, ProjectModel project) {
        Optional<ProjectModel> foundProject = repository.findProjectModelById(id);
        if (foundProject.isPresent()) {
            project.setId(id);
            ProjectModel savedData = saveSingleProjectSoftCascade(project);
            sortAboutAndProductionParagraphs(savedData);
            return savedData;
        }
        throw new ApiRequestException("Project with id: '" + id + "' does not exist", HttpStatus.NOT_FOUND);
    }

    @Override
    public void deleteSingleProject(String id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteAllProjects() {
        usedSoftRepository.deleteAll();
        softRepository.deleteAll();
        repository.deleteAll();
    }

}