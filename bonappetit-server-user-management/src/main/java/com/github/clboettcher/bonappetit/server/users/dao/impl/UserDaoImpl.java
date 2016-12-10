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
package com.github.clboettcher.bonappetit.server.users.dao.impl;

import com.github.clboettcher.bonappetit.server.users.dao.UserDao;
import com.github.clboettcher.bonappetit.server.users.entity.UserEntity;
import com.google.common.collect.Lists;
import de.qaware.heimdall.Password;
import de.qaware.heimdall.PasswordException;
import de.qaware.heimdall.PasswordFactory;
import de.qaware.heimdall.SecureCharArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.InternalServerErrorException;
import java.util.List;
import java.util.Optional;

@Component
public class UserDaoImpl implements UserDao {

    private Password password = PasswordFactory.create();

    @Autowired
    private UserEntityRepository userEntityRepository;

    @Override
    public void delete(UserEntity userEntity) {
        this.userEntityRepository.delete(userEntity);
    }

    @Override
    public UserEntity create(UserEntity userEntity) {
        try (SecureCharArray cleartext = new SecureCharArray(userEntity.getPasswordCleartext().toCharArray())) {
            String hash = password.hash(cleartext);
            userEntity.setPasswordHash(hash);
            return this.userEntityRepository.save(userEntity);
        } catch (PasswordException e) {
            throw new InternalServerErrorException("Password hashing failed", e);
        }
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return Lists.newArrayList(this.userEntityRepository.findAll());
    }

    @Override
    public Optional<UserEntity> getUserById(Long id) {
        return Optional.ofNullable(this.userEntityRepository.findOne(id));
    }
}
