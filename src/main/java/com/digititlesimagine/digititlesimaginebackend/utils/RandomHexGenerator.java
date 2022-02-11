/*
 * Copyright (c) 2022 by MILOSZ GILGA <https://miloszgilga.pl>
 *
 * File name: RandomHexGenerator.java
 * Last modified: 11/02/2022, 19:22
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

import java.util.*;

public class RandomHexGenerator {

    private int sequenceStringCount;

    public RandomHexGenerator() {
        this.sequenceStringCount = 20;
    }

    public RandomHexGenerator(int sequenceStringCount) {
        this.sequenceStringCount = sequenceStringCount;
    }

    public String generateSequence() {
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        while (sb.length() < sequenceStringCount) {
            sb.append(Integer.toHexString(r.nextInt()));
        }
        return sb.substring(0, sequenceStringCount);
    }

}