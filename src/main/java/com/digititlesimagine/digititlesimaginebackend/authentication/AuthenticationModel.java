/*
 * Copyright (c) 2022 by MILOSZ GILGA <https://miloszgilga.pl>
 *
 * File name: AuthenticationsModel.java
 * Last modified: 12/02/2022, 00:42
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
import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "authentications")
public class AuthenticationModel extends AuditModelWithId {

    @Column(name = "cms_username")
    @NotNull(message = "Blank field responsible for username property")
    @Size(min = 8, max = 500, message = "Field responsible for cms username should have at least 8 characters")
    private String username;

    @Column(name = "cms_password")
    @NotNull(message = "Blank field responsible for password property")
    @Size(min = 8, max = 500, message = "Field responsible for cms password should have at least 8 characters")
    private String password;

    @Column(name = "cms_role")
    private Enums.Authentications role;

}