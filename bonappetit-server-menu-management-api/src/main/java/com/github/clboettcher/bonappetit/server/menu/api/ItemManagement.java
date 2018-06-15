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
package com.github.clboettcher.bonappetit.server.menu.api;

import com.github.clboettcher.bonappetit.server.core.error.ErrorResponse;
import com.github.clboettcher.bonappetit.server.menu.api.dto.read.ItemDto;
import com.github.clboettcher.bonappetit.server.menu.api.dto.write.ItemCreationDto;
import io.swagger.annotations.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Public rest interface to manage menu items.
 */
@Path("/items")
@Api(tags = {ItemManagement.TAG})
public interface ItemManagement {

    String TAG = "items";

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Creates the given items in the database.", tags = {ItemManagement.TAG})
    @ApiResponses(
            @ApiResponse(
                    code = 400,
                    message = "The request did not contain items to create or any of the items were invalid",
                    response = ErrorResponse.class
            )
    )
    Response createItems(@ApiParam(value = "The items to create.", required = true) List<ItemCreationDto>
                                 itemCreationDtos);

    /**
     * Returns all items.
     *
     * @return An item list.
     */
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Returns all items", tags = {ItemManagement.TAG})
    List<ItemDto> getItems();

    /**
     * Returns the item with the given ID.
     *
     * @param id The ID to look for.
     *
     * @return An item.
     */
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Returns the item with the given ID.", tags = {ItemManagement.TAG})
    @ApiResponses({
            @ApiResponse(
                    code = 400,
                    message = "If param id is blank.",
                    response = ErrorResponse.class
            ),
            @ApiResponse(
                    code = 404,
                    message = "The requested item does not exist.",
                    response = ErrorResponse.class
            )}
    )
    ItemDto getItemById(@ApiParam(value = "The item ID to look for.") @PathParam("id") Long id);
}
