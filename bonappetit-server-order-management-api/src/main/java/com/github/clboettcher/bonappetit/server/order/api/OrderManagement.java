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
package com.github.clboettcher.bonappetit.server.order.api;


import com.github.clboettcher.bonappetit.server.core.error.ErrorResponse;
import com.github.clboettcher.bonappetit.server.order.api.dto.read.ItemOrderDto;
import com.github.clboettcher.bonappetit.server.order.api.dto.write.ItemOrderCreationDto;
import io.swagger.annotations.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.List;

@Path(OrderManagement.ROOT_PATH)
@Api
public interface OrderManagement {

    String TAG = "orders";
    String ROOT_PATH = "/orders";

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(
            value = "Creates and prints the given orders.",
            notes = "Creates the given orders in the database. " +
                    "After the orders have been saved they are printed. If printing " +
                    "fails, the orders are not deleted from the database.",
            tags = {TAG}
    )
    @ApiResponses(@ApiResponse(
            code = 400,
            message = "A referenced entity (ordered item, staff member) does not exist.",
            response = ErrorResponse.class))
    Response createOrders(Collection<ItemOrderCreationDto> orders);

    /**
     * Returns all orders.
     * d
     *
     * @param orderedAfter Indicates that the response should only contain resources
     *                     ordered after the specified time.
     * @return A list of orders, may be empty.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Returns all orders.", tags = {TAG})
    List<ItemOrderDto> getAllOrders(
            @ApiParam(value = "The orderedAfter parameter indicates that the API response " +
                    "should only contain orders which were taken at or after the specified time. " +
                    "The value is an RFC 3339 formatted date-time value (1970-01-01T00:00:00Z) or a " +
                    "key word. Supported key words are: today. Only one of the params orderedAfter " +
                    "and orderedAt can be used.", required = false)
            @QueryParam("orderedAfter") String orderedAfter,

            @ApiParam(value = "The orderedAt parameter indicates that the API response " +
                    "should only contain orders which were taken at the specified date. " +
                    "The value is an RFC 3339 formatted date value (1970-01-01) or a " +
                    "key word. Supported key words are: today. Only one of the params orderedAfter " +
                    "and orderedAt can be used.", required = false)
            @QueryParam("orderedAt") String orderedAt);
}