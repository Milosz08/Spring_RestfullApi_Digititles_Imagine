/*
 * Copyright (c) 2022 by MILOSZ GILGA <https://miloszgilga.pl>
 *
 * File name: FileManagement.java
 * Last modified: 21/07/2022, 20:04
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

package com.digititlesimagine.digititlesimaginebackend.utils;

import com.digititlesimagine.digititlesimaginebackend.projectPhotosHandling.ProjectPhotosServicesImplementation;

import java.io.File;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class FileManagement {

    public static final String FILE_SEPARATOR = System.getProperty("file.separator");

    public static String getImagesFolderPath() {
        URL url = ProjectPhotosServicesImplementation.class.getProtectionDomain().getCodeSource().getLocation();
        String jarPath = URLDecoder.decode(url.getFile(), StandardCharsets.UTF_8);
        return new File(jarPath).getParentFile().getPath() + FILE_SEPARATOR + "java-uploads" + FILE_SEPARATOR;
    }

}