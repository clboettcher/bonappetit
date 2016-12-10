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
import de.qaware.heimdall.PasswordFactory;
import de.qaware.heimdall.SecureCharArray;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/**
 * Tests for {@link BonAppetitPasswordEncoder}.
 */
public class BonAppetitPasswordEncoderTests {


    private BonAppetitPasswordEncoder encoder;

    private Password password;

    @Before
    public void setUp() throws Exception {
        this.password = PasswordFactory.create();
        this.encoder = new BonAppetitPasswordEncoder(password);
    }

    @Test
    public void testMatches() throws Exception {
        String hash = password.hash(new SecureCharArray("s3cret".toCharArray()));
        assertThat(encoder.matches("s3cret", hash), is(true));
        assertThat(encoder.matches("foobar", hash), is(false));
        assertThat(encoder.matches(null, hash), is(false));
        assertThat(encoder.matches("", hash), is(false));
        assertThat(encoder.matches("   ", hash), is(false));
    }

    //    Convenience test to hash a password. Comment out when finished
//    @Test
    public void hashPassword() throws Exception {
        System.out.println(password.hash(new SecureCharArray("<your password here>".toCharArray())));
        fail("This test is not supposed to be running. Comment out and clear your passwords before committing!!!");
    }
}
