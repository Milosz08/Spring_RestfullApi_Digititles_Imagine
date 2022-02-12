/*
 * Copyright (c) 2022 by MILOSZ GILGA <https://miloszgilga.pl>
 *
 * File name: AuthenticationsRepository.java
 * Last modified: 12/02/2022, 00:43
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

package com.digititlesimagine.digititlesimaginebackend.authentication;

import com.digititlesimagine.digititlesimaginebackend.utils.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface AuthenticationRepository extends JpaRepository<AuthenticationModel, String> {

    @Query("SELECT a FROM AuthenticationModel a WHERE a.role=:role")
    Optional<AuthenticationModel> findAuthenticationsModelByRole(@Param("role") Enums.Authentications role);

    @Query("SELECT a FROM AuthenticationModel a WHERE a.username=:name")
    Optional<AuthenticationModel> findAuthenticationModelByUsername(@Param("name") String name);

}