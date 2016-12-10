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

import com.github.clboettcher.bonappetit.server.users.dao.UserDao;
import com.github.clboettcher.bonappetit.server.users.entity.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class BonAppetitUserDetailsService implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BonAppetitUserDetailsService.class);
    private final String adminUsername;
    private final String adminPasswordHash;

    private UserDao userDao;

    @Autowired
    public BonAppetitUserDetailsService(UserDao userDao, Environment environment) {
        this.userDao = userDao;
        this.adminUsername = environment.getRequiredProperty("security.admin.username");
        this.adminPasswordHash = environment.getRequiredProperty("security.admin.passwordHash");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (this.adminUsername.equals(username)) {
            // The admin username is treated differently since it is not stored in the db.
            return new User(
                    adminUsername,
                    adminPasswordHash,
                    AuthorityUtils.createAuthorityList(Authority.ADMIN.toString(), Authority.USER.toString())
            );
        } else {
            UserEntity userEntity = userDao.getUserByUsername(username).orElseThrow(() -> {
                LOGGER.warn(String.format("Denying access for user with username '%s' because " +
                                "the user does not exist.",
                        username));
                return new UsernameNotFoundException(String.format("Username '%s' not found", username));
            });

            return new User(
                    userEntity.getUsername(),
                    userEntity.getPasswordHash(),
                    AuthorityUtils.createAuthorityList(Authority.USER.toString())
            );
        }
    }
}
