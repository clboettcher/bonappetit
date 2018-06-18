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
package com.github.clboettcher.bonappetit.server.staff.dao.impl;

import com.github.clboettcher.bonappetit.server.staff.dao.StaffMemberDao;
import com.github.clboettcher.bonappetit.server.staff.entity.StaffMemberEntity;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Default impl of {@link StaffMemberDao}.
 */
@Component
public class StaffMemberDaoImpl implements StaffMemberDao {

    /**
     * The repository for {@link StaffMemberEntity}s.
     */
    private StaffMemberEntityRepository staffMemberEntityRepository;

    /**
     * Constructor setting the specified properties.
     *
     * @param staffMemberEntityRepository see {@link #staffMemberEntityRepository}.
     */
    @Autowired
    public StaffMemberDaoImpl(StaffMemberEntityRepository staffMemberEntityRepository) {
        this.staffMemberEntityRepository = staffMemberEntityRepository;
    }

    @Override
    public List<StaffMemberEntity> getStaffMembers() {
        Iterable<StaffMemberEntity> allEntities = staffMemberEntityRepository.findAll();
        if (!allEntities.iterator().hasNext()) {
            return Collections.emptyList();
        } else {
            return Lists.newArrayList(allEntities);
        }
    }

    @Override
    public StaffMemberEntity getStaffMember(Long id) {
        return staffMemberEntityRepository.findOne(id);
    }

    @Override
    public boolean exists(Long id) {
        return staffMemberEntityRepository.exists(id);
    }

    @Override
    public List<StaffMemberEntity> save(List<StaffMemberEntity> staffMembers) {
        return Lists.newArrayList(staffMemberEntityRepository.save(staffMembers));
    }

    @Override
    public List<StaffMemberEntity> findAllByIds(Set<Long> staffMemberIds) {
        return Lists.newArrayList(this.staffMemberEntityRepository.findAll(staffMemberIds));
    }
}
