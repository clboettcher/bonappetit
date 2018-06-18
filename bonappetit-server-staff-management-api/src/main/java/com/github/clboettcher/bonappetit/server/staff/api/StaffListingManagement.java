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
package com.github.clboettcher.bonappetit.server.staff.api;

import com.github.clboettcher.bonappetit.server.staff.api.dto.StaffListingDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Set;

@Path("/" + StaffListingManagement.ROOT_PATH)
@Api
public interface StaffListingManagement {

    String TAG = "staff";
    String ROOT_PATH = "staffListings";

    @POST
    @Path("/{title}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Creates a new staff listing", tags = {TAG})
    StaffListingDto createStaffListing(@ApiParam(value = "The title of the staff listing to update.")
                                       @PathParam("title") final String title,
                                       @ApiParam(value = "The ids of the staff members to put into the staff listing",
                                               required = true) final Set<Long> staffMemberIds);

    @PUT
    @Path("/{title}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Update the staff listing with the given title.", notes = "Since all persistent " +
            "data is immutable this operation will not actually modify the referenced staff listing. Instead a new " +
            "staff listing will be created and saved with the same title and a higher version number. The new staff " +
            "listing is returned.", tags = {TAG})
    StaffListingDto updateStaffListing(@ApiParam(value = "The title of the staff listing to update.", required = true)
                                       @PathParam("title") final String title,
                                       @ApiParam(value = "The ids of the staff members to put into this staff listing",
                                               required = true) final Set<Long> staffMemberIds);

}
