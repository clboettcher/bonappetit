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
import java.util.List;
import java.util.Set;

@Path("/")
@Api
public interface StaffListingManagement {

    String TAG = "staff";
    String ROOT_PATH = "staffListings";
    String CURRENT_STAFF_LISTING_ROOT = "currentStaffListing";

    @GET
    @Path("/" + StaffListingManagement.ROOT_PATH)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Returns all stored staff listings", tags = {TAG})
    List<StaffListingDto> getAllStaffListings(@ApiParam(value = "Whether to include old inactive versions in the " +
            "output.")
                                              @QueryParam("showInactive") @DefaultValue("false") final boolean
                                                      showInactive);

    @GET
    @Path("/" + CURRENT_STAFF_LISTING_ROOT)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Returns the currently active staff listing.", notes = "This is the only endpoint that the " +
            "app should use directly. The server should decive which staff listing is currently active to make sure " +
            "all apps work with the same data.", tags = {TAG})
    StaffListingDto getCurrentStaffListing();

    @GET
    @Path("/" + StaffListingManagement.ROOT_PATH + "/{title}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Returns the current version of the staff listing with the given title.", tags =
            {TAG})
    StaffListingDto getAllStaffListings(@ApiParam(value = "The title of the staff listing to get.")
                                        @PathParam("title") final String title);

    @POST
    @Path("/" + StaffListingManagement.ROOT_PATH + "/{title}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Creates a new staff listing", tags = {TAG})
    StaffListingDto createStaffListing(@ApiParam(value = "The title of the staff listing to update.")
                                       @PathParam("title") final String title,
                                       @ApiParam(value = "The ids of the staff members to put into the staff listing",
                                               required = true) final Set<Long> staffMemberIds);

    @PUT
    @Path("/" + StaffListingManagement.ROOT_PATH + "/{title}")
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
