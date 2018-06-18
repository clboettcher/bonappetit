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
package com.github.clboettcher.bonappetit.server.staff.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;

import java.util.List;

@ApiModel(description = "The payload to create a new staff listing with.")
@NoArgsConstructor
public class StaffListingDto {

    @ApiModelProperty(value = "The title.", example = "Friday Lounge", required = true)
    private String title;

    @ApiModelProperty(value = "The version.", example = "2", required = true)
    private Integer version;

    @ApiModelProperty(value = "The ids of the staff members that this listing consists of.", required = true)
    private List<StaffMemberDto> staffMembers;

    @ApiModelProperty(value = "The timestamp that this staff listing is valid from.", required = true)
    private DateTime validFrom;

    @ApiModelProperty(value = "The timestamp that this staff listing is valid until.")
    private DateTime validUntil;

    @Builder
    public StaffListingDto(String title,
                           Integer version,
                           List<StaffMemberDto> staffMembers,
                           DateTime validFrom,
                           DateTime validUntil) {
        this.title = title;
        this.version = version;
        this.staffMembers = staffMembers;
        this.validFrom = validFrom;
        this.validUntil = validUntil;
    }

    public String getTitle() {
        return title;
    }

    public Integer getVersion() {
        return version;
    }

    public List<StaffMemberDto> getStaffMembers() {
        return staffMembers;
    }

    public DateTime getValidFrom() {
        return validFrom;
    }

    public DateTime getValidUntil() {
        return validUntil;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public void setStaffMembers(List<StaffMemberDto> staffMembers) {
        this.staffMembers = staffMembers;
    }

    public void setValidFrom(DateTime validFrom) {
        this.validFrom = validFrom;
    }

    public void setValidUntil(DateTime validUntil) {
        this.validUntil = validUntil;
    }
}
