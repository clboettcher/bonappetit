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
package com.github.clboettcher.bonappetit.server.users.impl;

import com.github.clboettcher.bonappetit.server.users.api.UserManagement;
import com.github.clboettcher.bonappetit.server.users.api.dto.UserCreationDto;
import com.github.clboettcher.bonappetit.server.users.api.dto.UserDto;
import com.github.clboettcher.bonappetit.server.users.dao.UserDao;
import com.github.clboettcher.bonappetit.server.users.entity.UserEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Component
public class UserManagementImpl implements UserManagement {

    @Context
    private UriInfo uri;

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserEntityMapper toEntityMapper;

    @Autowired
    private UserDtoMapper toDtoMapper;

    private String adminUsername;

    @Autowired
    public UserManagementImpl(Environment environment) {
        this.adminUsername = environment.getRequiredProperty("security.admin.username");
    }

    @Override
    public List<UserDto> getUsers() {
        return toDtoMapper.mapToUserDtos(userDao.getAllUsers());
    }

    @Override
    public UserDto getUserById(Long id) {
        UserEntity userEntity = getUserOrThrowBRE(id);
        return toDtoMapper.mapToUserDto(userEntity);
    }

    private UserEntity getUserOrThrowBRE(Long id) {
        return userDao.getUserById(id).orElseThrow(() ->
                new NotFoundException(String.format("User with id %d cannot be returned " +
                        "because it does not exist.", id))
        );
    }

    @Override
    public Response createUser(UserCreationDto userCreationDto) {
        assertValid(userCreationDto);

        UserEntity toSave = toEntityMapper.mapToUserEntity(userCreationDto);
        UserEntity saved = this.userDao.create(toSave);

        String location = String.format("%s/%d", USERS_PATH, saved.getId());
        UriBuilder baseUriBuilder = uri.getBaseUriBuilder().path(location);

        return Response.ok()
                .location(baseUriBuilder.build())
                .build();
    }

    @Override
    public Response deleteUser(Long id) {
        UserEntity userEntity = getUserOrThrowBRE(id);
        this.userDao.delete(userEntity);
        return Response.noContent().build();
    }

    private void assertValid(UserCreationDto userCreationDto) {
        if (userCreationDto == null) {
            throw new BadRequestException("No user data provided.");
        }
        if (StringUtils.isBlank(userCreationDto.getUsername())) {
            throw new BadRequestException("Username must be provided");
        }
        if (this.adminUsername.equals(userCreationDto.getUsername())) {
            throw new BadRequestException(String.format("Username '%s' is a reserved username.",
                    this.adminUsername));
        }
        if (StringUtils.isBlank(userCreationDto.getPassword())) {
            throw new BadRequestException("Password must be provided");
        }
    }
}
