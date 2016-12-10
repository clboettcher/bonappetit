/*
 * Copyright (c) 2016 Claudius Boettcher (pos.bonappetit@gmail.com)
 *
 * This file is part of BonAppetit. BonAppetit is an Android based
 * Point-of-Sale client-server application for small restaurants.
 *
 * BonAppetit is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * BonAppetit is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with BonAppetit.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.github.clboettcher.bonappetit.server.security;

import de.qaware.heimdall.Password;
import de.qaware.heimdall.PasswordException;
import de.qaware.heimdall.SecureCharArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.ws.rs.InternalServerErrorException;

@Component
public class BonAppetitPasswordEncoder implements PasswordEncoder {

    @Autowired
    private Password password;

    @Override
    public String encode(CharSequence rawPassword) {
        try (SecureCharArray cleartext = new SecureCharArray(((String) rawPassword)
                .toCharArray())) {
            return password.hash(cleartext);
        } catch (PasswordException e) {
            throw new InternalServerErrorException("Password hashing failed", e);
        }
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        try (SecureCharArray cleartext = new SecureCharArray(((String) rawPassword).toCharArray())) {
            try {
                return password.verify(cleartext, encodedPassword);
            } catch (PasswordException e) {
                throw new InternalServerErrorException("Password processing failed", e);
            }
        }
    }

}
