/*
 * Copyright (c) 2022 by MILOSZ GILGA <https://miloszgilga.pl>
 *
 * File name: ProjectPhotosServicesImplementation.java
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

import com.digititlesimagine.digititlesimaginebackend.exceptions.ApiRequestException;

import com.digititlesimagine.digititlesimaginebackend.project.ProjectModel;
import com.digititlesimagine.digititlesimaginebackend.project.ProjectRepository;

import org.apache.commons.io.FileUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;

import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ProjectPhotosServicesImplementation implements ProjectPhotosServices {

    private static final String UPLOAD_FOLDER = "java-uploads/";
    private static final String UPLOAD_DIR = System.getProperty("user.dir") + "/src/main/resources/static/" + UPLOAD_FOLDER;
    private static final List<String> IMG_TYPES = Arrays.asList("image/png", "image/jpeg");

    private final ProjectRepository projectRepository;

    public ProjectPhotosServicesImplementation(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public Path init(String projectId) {
        File createDir = new File(UPLOAD_DIR + File.separator + projectId);
        if (!createDir.exists()) {
            if (!createDir.mkdir()) {
                throw new ApiRequestException("Could not initialize folder for upload", HttpStatus.EXPECTATION_FAILED);
            }
        }
        return createDir.toPath();
    }

    @Override
    public Map<String, Object> getAllProjectImages(String projectId, HttpServletRequest request) {
        Map<String, Object> outBody = new HashMap<>();
        Path dir = new File(UPLOAD_DIR + File.separator + projectId).toPath();
        List<ProjectPhotosInfoResponseModel> fileInfos;
        try {
            Stream<Path> allPaths = Files.walk(dir, 2).filter(path -> !path.equals(dir)).map(dir::relativize);
            fileInfos = allPaths.map(path -> {
                String name = path.getFileName().toString();
                String baseUrl = ServletUriComponentsBuilder.fromRequestUri(request).replacePath(null).build().toUriString();
                String uri = baseUrl + "/" + UPLOAD_FOLDER + projectId + "/" + path.getFileName();
                return new ProjectPhotosInfoResponseModel(name, uri);
            }).collect(Collectors.toList());
        } catch (IOException e) {
            throw new ApiRequestException(
                "Could not load the files. Project with id: '" + projectId + "' id not exist", HttpStatus.EXPECTATION_FAILED
            );
        }
        outBody.put("projectImages", fileInfos);
        outBody.put("projectId", projectId);
        return outBody;
    }

    @Override
    public List<Map<String, Object>> getAllImagesFromAllProjects(HttpServletRequest request) {
        List<Map<String, Object>> allProjectsImages = new LinkedList<>();
        Path dir = new File(UPLOAD_DIR).toPath();
        try {
            Stream<Path> onlyIds = Files.walk(dir, 2)
                    .filter(path -> !path.equals(dir)).map(dir::relativize)
                    .filter(path -> !path.toString().contains(File.separator));
            onlyIds.forEach(projectId -> allProjectsImages.add(getAllProjectImages(projectId.toString(), request)));
        } catch (IOException e) {
            throw new ApiRequestException("Could not load the files", HttpStatus.EXPECTATION_FAILED);
        }
        return allProjectsImages;
    }

    @Override
    public Resource getImage(String projectId, String fileName) {
        try {
            Path file = new File(UPLOAD_DIR + File.separator + projectId).toPath().resolve(fileName);
            Resource resource = new UrlResource(file.toUri());
            if (!resource.exists() || !resource.isReadable()) {
                throw new ApiRequestException(
                    "Could not read the file with name: '" + fileName + "' from project with id: " + projectId,
                    HttpStatus.EXPECTATION_FAILED
                );
            }
            return resource;
        } catch (MalformedURLException e) {
            throw new ApiRequestException(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }

    @Override
    public ProjectPhotosResponseModel saveImagesForSingleProject(MultipartFile[] files, String id) {
        Path dirPath = init(id);
        List<String> fileNames = new ArrayList<>();
        Optional<ProjectModel> findingProject = projectRepository.findProjectModelById(id);
        if (!findingProject.isPresent()) {
            throw new ApiRequestException("Project with id: '" + id + "' not exist", HttpStatus.NOT_FOUND);
        }
        try {
            FileUtils.cleanDirectory(dirPath.toFile());
        } catch (IOException e) {
            throw new ApiRequestException("Could not remove files in selected directory", HttpStatus.EXPECTATION_FAILED);
        }
        Arrays.stream(files).forEach(file -> {
            String fileContentType = file.getContentType();
            if (!IMG_TYPES.contains(fileContentType)) {
                throw new ApiRequestException(
                    "File extension type: '" + file.getContentType() + "' is not supported", HttpStatus.EXPECTATION_FAILED
                );
            }
            try {
                Files.copy(file.getInputStream(), dirPath.resolve(Objects.requireNonNull(file.getOriginalFilename())));
            } catch (IOException e) {
                throw new ApiRequestException(
                        "Could not store the file '" + file.getOriginalFilename() + "'", HttpStatus.EXPECTATION_FAILED
                );
            }
            fileNames.add(file.getOriginalFilename());
        });
        return new ProjectPhotosResponseModel(fileNames);
    }

    @Override
    public void deleteImagesFromSingleProject(String projectId) {
        Path dir = new File(UPLOAD_DIR + File.separator + projectId).toPath();
        FileSystemUtils.deleteRecursively(dir.toFile());
    }

    @Override
    public void deleteImagesFromAllProjects() {
        Path dir = new File(UPLOAD_DIR).toPath();
        try {
            Stream<Path> onlyIds = Files.walk(dir, 2)
                    .filter(path -> !path.equals(dir)).map(dir::relativize)
                    .filter(path -> !path.toString().contains(File.separator));
            onlyIds.forEach(projectId -> {
                Path dirId = new File(UPLOAD_DIR + File.separator + projectId).toPath();
                FileSystemUtils.deleteRecursively(dirId.toFile());
            });
        } catch (IOException e) {
            throw new ApiRequestException("Could not delete the files", HttpStatus.EXPECTATION_FAILED);
        }
    }

}