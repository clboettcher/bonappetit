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

import com.github.clboettcher.bonappetit.server.staff.dao.StaffListingDao;
import com.github.clboettcher.bonappetit.server.staff.entity.StaffListingEntity;
import com.github.clboettcher.bonappetit.server.staff.entity.StaffListingId;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StaffListingDaoImpl implements StaffListingDao {

    @Autowired
    private StaffListingEntityRepository staffListingEntityRepository;

    @Override
    public StaffListingEntity findCurrentVersionByTitle(String title) {
        Iterable<StaffListingEntity> all = staffListingEntityRepository.findAll();

        StaffListingEntity found = null;

        for (StaffListingEntity current : all) {
            StaffListingId currentId = current.getId();
            if (title.equals(currentId.getTitle())) {
                if (found == null || currentId.getVersion() > found.getId().getVersion()) {
                    found = current;
                }
            }
        }

        return found;
    }

    @Override
    public StaffListingEntity save(StaffListingEntity staffListingEntity) {
        return staffListingEntityRepository.save(staffListingEntity);
    }

    @Override
    public StaffListingEntity update(StaffListingEntity staffListingEntity) {
        if (staffListingEntity.getId() == null || StringUtils.isBlank(staffListingEntity.getId()
                .getTitle()) || staffListingEntity.getId().getVersion() == null) {
            throw new IllegalArgumentException("Update requires id: " + staffListingEntity);
        }
        return staffListingEntityRepository.save(staffListingEntity);
    }

    @Override
    public List<StaffListingEntity> findAll() {
        return Lists.newArrayList(staffListingEntityRepository.findAll());
    }

    @Override
    public List<StaffListingEntity> findAllActive() {
        return this.removeInactive(this.findAll());
    }

    private List<StaffListingEntity> removeInactive(List<StaffListingEntity> all) {
        return all.stream()
                .filter(staffListingEntity -> staffListingEntity.getValidUntil() == null)
                .collect(Collectors.toList());
    }
}
