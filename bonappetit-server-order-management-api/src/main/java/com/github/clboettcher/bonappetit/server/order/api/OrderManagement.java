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
import com.github.clboettcher.bonappetit.server.order.api.dto.read.OptionOrderDto;
import com.github.clboettcher.bonappetit.server.order.api.dto.read.SummaryDto;
import com.github.clboettcher.bonappetit.server.order.api.dto.write.ItemOrderCreationDto;
import io.swagger.annotations.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api
@Path("/")
public interface OrderManagement {

    String TAG = "orders";
    String ORDERS_ROOT_PATH = "orders";
    String OPTION_ORDERS_ROOT_PATH = "optionOrders";
    String PRINT_SUMMARY_REQUESTS_ROOT_PATH = "printSummaryRequests";
    String CANCEL_ORDERS_REQUESTS_ROOT_PATH = "cancelOrdersRequests";
    String SUMMARY_ROOT_PATH = "summary";

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path(ORDERS_ROOT_PATH)
    @ApiOperation(
            value = "Creates and prints the given orders.",
            notes = "Creates the given orders in the database. " +
                    "After the orders have been saved they are printed. If printing " +
                    "fails, the orders are deleted from the database.",
            tags = {TAG}
    )
    @ApiResponses(@ApiResponse(
            code = 400,
            message = "A required property is missing or a referenced entity " +
                    "(ordered item, ordered option, staff member) does not exist in the database.",
            response = ErrorResponse.class))
    Response createOrders(@ApiParam(value = "The orders to create",
            required = true) List<ItemOrderCreationDto> orders);

    /**
     * Returns all orders.
     *
     * @param orderedAfter Indicates that the response should only contain resources
     *                     ordered after the specified time.
     * @return A list of orders, may be empty.
     */
    @GET
    @Path(ORDERS_ROOT_PATH)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Returns all orders.", tags = {TAG})
    List<ItemOrderDto> getAllOrders(
            @ApiParam(value = "The orderedBefore parameter indicates that the API response " +
                    "should only contain orders which were taken at or before the specified time. " +
                    "The value is an RFC 3339 formatted date-time value (1970-01-01T00:00:00Z) or a " +
                    "key word. Supported key words are: today. Only orderedBefore and orderedAfter " +
                    "can be used together. The orderedAt param must be used without orderedBefore and " +
                    "orderedAfter.", required = false)
            @QueryParam("orderedBefore") String orderedBefore,

            @ApiParam(value = "The orderedAfter parameter indicates that the API response " +
                    "should only contain orders which were taken at or after the specified time. " +
                    "The value is an RFC 3339 formatted date-time value (1970-01-01T00:00:00Z) or a " +
                    "key word. Supported key words are: today. Only orderedBefore and orderedAfter " +
                    "can be used together. The orderedAt param must be used without orderedBefore and " +
                    "orderedAfter.", required = false)
            @QueryParam("orderedAfter") String orderedAfter,

            @ApiParam(value = "The orderedAt parameter indicates that the printed API response " +
                    "should only contain orders which were taken at the specified date. " +
                    "The value is an RFC 3339 formatted date value (1970-01-01) or a " +
                    "key word. Supported key words are: today. Only orderedBefore and orderedAfter " +
                    "can be used together. The orderedAt param must be used without orderedBefore and " +
                    "orderedAfter.", required = false)
            @QueryParam("orderedAt") String orderedAt,

            @ApiParam(value = "The status filter param can be used to filter the result by status. " +
                    "Multiple values can be separated by comma.", required = false)
            @QueryParam("status") String status
    );

    /**
     * Returns the order with the specified id.
     *
     * @param id The id to look for.
     * @return The order.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Get a single order by id.", tags = {TAG})
    @Path(ORDERS_ROOT_PATH + "/{id}")
    ItemOrderDto getOrderById(@ApiParam(value = "The id to look for.") @PathParam("id") Long id);

    /**
     * Returns the option orders from the option with the given id.
     *
     * @param id The option id.
     * @return The option orders, may be empty if the specified order does not contain any option orders.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Gets the option orders of the order with the given id.", tags = {TAG})
    @Path(ORDERS_ROOT_PATH + "/{id}/optionOrders")
    List<OptionOrderDto> getOptionOrdersForOrder(@ApiParam(value = "The id of the order to " +
            "return the option orders from.") @PathParam("id") Long id);

    /**
     * Returns all stored option orders.
     *
     * @return The option orders, may be empty.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Gets all stored option orders.", tags = {TAG})
    @Path(OPTION_ORDERS_ROOT_PATH)
    List<OptionOrderDto> getAllOptionOrders();

    /**
     * Get a summary of a list of orders.
     *
     * @param orderedBefore Summary should only contain orders at or before this time (RFC 3339 formatted date-time value).
     * @param orderedAfter  Summary should only contain orders at or after this time (RFC 3339 formatted date-time value).
     * @param orderedAt     Summary should only contain orders at or before this date (RFC 3339 formatted date value).
     * @return The order summary, may not contain any orders.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Returns the summary data.", tags = {TAG})
    @Path(SUMMARY_ROOT_PATH)
    SummaryDto getSummary(
            @ApiParam(value = "The orderedBefore parameter indicates that the API response " +
                    "should only contain orders which were taken at or before the specified time. " +
                    "The value is an RFC 3339 formatted date-time value (1970-01-01T00:00:00Z) or a " +
                    "key word. Supported key words are: today. Only orderedBefore and orderedAfter " +
                    "can be used together. The orderedAt param must be used without orderedBefore and " +
                    "orderedAfter.", required = false)
            @QueryParam("orderedBefore") String orderedBefore,

            @ApiParam(value = "The orderedAfter parameter indicates that the API response " +
                    "should only contain orders which were taken at or after the specified time. " +
                    "The value is an RFC 3339 formatted date-time value (1970-01-01T00:00:00Z) or a " +
                    "key word. Supported key words are: today. Only orderedBefore and orderedAfter " +
                    "can be used together. The orderedAt param must be used without orderedBefore and " +
                    "orderedAfter.", required = false)
            @QueryParam("orderedAfter") String orderedAfter,

            @ApiParam(value = "The orderedAt parameter indicates that the printed API response " +
                    "should only contain orders which were taken at the specified date. " +
                    "The value is an RFC 3339 formatted date value (1970-01-01) or a " +
                    "key word. Supported key words are: today. Only orderedBefore and orderedAfter " +
                    "can be used together. The orderedAt param must be used without orderedBefore and " +
                    "orderedAfter.", required = false)
            @QueryParam("orderedAt") String orderedAt
    );

    /**
     * Create a request to print a summary.
     * <p>
     * The summary is created and printed synchronously. Therefore the
     * create summary request cannot be fetched or canceled. The endpoint returns 204.
     *
     * @param orderedBefore Summary should only contain orders at or before this time (RFC 3339 formatted date-time value).
     * @param orderedAfter  Summary should only contain orders at or after this time (RFC 3339 formatted date-time value).
     * @param orderedAt     Summary should only contain orders at or before this date (RFC 3339 formatted date value).
     * @return A response indicating the success of the operation.
     */
    @POST
    @ApiOperation(value = "Create a request to print the summary.", notes = "The summary contains a list" +
            "of orders including the total prices and the sum of all orders.", tags = {TAG})
    @Path(PRINT_SUMMARY_REQUESTS_ROOT_PATH)
    Response createPrintSummaryRequest(
            @ApiParam(value = "The orderedBefore parameter indicates that the summary " +
                    "should only contain orders which were taken at or before the specified time. " +
                    "The value is an RFC 3339 formatted date-time value (1970-01-01T00:00:00Z) or a " +
                    "key word. Supported key words are: today. Only orderedBefore and orderedAfter " +
                    "can be used together. The orderedAt param must be used without orderedBefore and " +
                    "orderedAfter.", required = false)
            @FormParam("orderedBefore") String orderedBefore,

            @ApiParam(value = "The orderedAfter parameter indicates that the summary " +
                    "should only contain orders which were taken at or after the specified time. " +
                    "The value is an RFC 3339 formatted date-time value (1970-01-01T00:00:00Z) or a " +
                    "key word. Supported key words are: today. Only orderedBefore and orderedAfter " +
                    "can be used together. The orderedAt param must be used without orderedBefore and " +
                    "orderedAfter.", required = false)
            @FormParam("orderedAfter") String orderedAfter,

            @ApiParam(value = "The orderedAt parameter indicates that the printed summary " +
                    "should only contain orders which were taken at the specified date. " +
                    "The value is an RFC 3339 formatted date value (1970-01-01) or a " +
                    "key word. Supported key words are: today. Only orderedBefore and orderedAfter " +
                    "can be used together. The orderedAt param must be used without orderedBefore and " +
                    "orderedAfter.", required = false)
            @FormParam("orderedAt") String orderedAt
    );

    /**
     * Create a request to cancel a list of orders.
     * <p>
     * The orders are updated synchronously. Therefore the
     * create cancel orders request cannot be fetched or canceled. The endpoint returns 204.
     *
     * @return A response indicating the success of the operation.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Create a request to cancel a list of orders.", tags = {TAG})
    @Path(CANCEL_ORDERS_REQUESTS_ROOT_PATH)
    Response createCancelOrdersRequest(
            @ApiParam(value = "The ids of the orders that should be cancelled",
                    required = true)
            List<Long> orderIds
    );
}
