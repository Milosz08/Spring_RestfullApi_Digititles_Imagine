/*
 * Copyright (c) 2022 by MILOSZ GILGA <https://miloszgilga.pl>
 *
 * File name: ServicesImplementationHelpers.java
 * Last modified: 16/02/2022, 21:43
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

import com.digititlesimagine.digititlesimaginebackend.project.aboutAndProduction.ProductionAboutAuditModel;
import com.digititlesimagine.digititlesimaginebackend.project.software.ProjectSoftwareModel;
import com.digititlesimagine.digititlesimaginebackend.project.software.ProjectSoftwareRepository;
import com.digititlesimagine.digititlesimaginebackend.project.software.ProjectSoftwareUsedModel;
import com.digititlesimagine.digititlesimaginebackend.project.software.ProjectSoftwareUsedRepository;

import com.digititlesimagine.digititlesimaginebackend.utils.RandomHexGenerator;

import java.util.*;
import java.util.stream.Collectors;

public class ServicesImplementationHelpers {

    protected final ProjectRepository repository;
    protected final ProjectSoftwareRepository softRepository;
    protected final ProjectSoftwareUsedRepository usedSoftRepository;

    public ServicesImplementationHelpers(
            ProjectRepository repository,
            ProjectSoftwareRepository softRepository,
            ProjectSoftwareUsedRepository usedSoftRepository
    ) {
        this.repository = repository;
        this.softRepository = softRepository;
        this.usedSoftRepository = usedSoftRepository;
    }

    protected static String generateProjectPath(String title) {
        String titleLower = title.toLowerCase(Locale.ROOT)
                .replaceAll(":", "")
                .replaceAll("'", "-")
                .replaceAll(" ", "-");
        return titleLower.charAt(0) == '-' ? titleLower.substring(0) : titleLower;
    }

    private void findSingleSoftProjObject(ProjectSoftwareUsedModel usedSoft) {
        List<ProjectSoftwareModel> findSoftObjectProps = softRepository.findProjectSoftwareByAllParams(
                usedSoft.getSoftware().getSoftwareFullName(), usedSoft.getSoftware().getSoftwareShortName()
        );
        if (!findSoftObjectProps.isEmpty()) {
            usedSoft.getSoftware().setId(findSoftObjectProps.get(0).getId());
        } else {
            usedSoft.getSoftware().setId(new RandomHexGenerator().generateSequence());
            softRepository.save(usedSoft.getSoftware());
        }
    }

    protected ProjectModel saveSingleProjectSoftCascade(ProjectModel project) {
        ProjectModel removeDuplicates = removeDuplicatesSoftwareForEntities(project);
        for (ProjectSoftwareUsedModel usedSoft : removeDuplicates.getUsedSoftware()) {
            findSingleSoftProjObject(usedSoft);
            Optional<ProjectSoftwareUsedModel> findFullSoftModel = usedSoftRepository.findMatchUsedSoftware(
                    usedSoft.getSoftwareFor()
            );
            if (findFullSoftModel.isPresent()) {
                usedSoft.setId(findFullSoftModel.get().getId());
            } else {
                usedSoft.setId(new RandomHexGenerator().generateSequence());
                usedSoftRepository.save(usedSoft);
            }
        }
        return repository.save(removeDuplicates);
    }

    private ProjectModel removeDuplicatesSoftwareForEntities(ProjectModel project) {
        List<ProjectSoftwareUsedModel> removeDuplicates = project.getUsedSoftware()
                .stream().distinct().collect(Collectors.toList());
        project.setUsedSoftware(removeDuplicates);
        return project;
    }

    protected void sortAboutAndProductionParagraphs(ProjectModel project) {
        project.getAboutSection().sort(Comparator.comparingInt(ProductionAboutAuditModel::getParagraphOrder));
        project.getProdSection().sort(Comparator.comparingInt(ProductionAboutAuditModel::getParagraphOrder));
    }

    protected float ConvertResObjectIntoFrame(String nativeResolution) {
        String[] firstAndSecondPart = nativeResolution.split("x");
        double firstAndSecond = Double.parseDouble(firstAndSecondPart[0]) / Double.parseDouble(firstAndSecondPart[1]);
        return Float.parseFloat(String.format(Locale.US, "%.2f", firstAndSecond));
    }

}