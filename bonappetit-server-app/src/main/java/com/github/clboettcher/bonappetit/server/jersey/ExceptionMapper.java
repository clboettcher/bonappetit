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
package com.github.clboettcher.bonappetit.server.jersey;

import com.github.clboettcher.bonappetit.server.core.error.ErrorResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

public class ExceptionMapper implements javax.ws.rs.ext.ExceptionMapper<Throwable> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionMapper.class);
    /**
     * List of exception classes where the message is written to the response.
     */
    public static final List<Class<? extends WebApplicationException>> WRITE_MSG_EXCEPTIONS = Arrays.asList(
            BadRequestException.class,
            NotFoundException.class,
            ForbiddenException.class,
            InternalServerErrorException.class
    );
    private static final EnumSet<Response.Status.Family> IGNORABLE_STATUS_FAMILIES = EnumSet.of(
            Response.Status.Family.SUCCESSFUL,
            Response.Status.Family.REDIRECTION
    );

    @Override
    public Response toResponse(Throwable exception) {
        Response waeResponse = getIgnorableWebApplicationExceptionResponse(exception);
        if (waeResponse != null) {
            return waeResponse;
        }

        ErrorResponse errorResponse = toErrorResponse(exception);

        this.log(exception, errorResponse);

        return Response.status(errorResponse.getStatus())
                .entity(errorResponse)
                .build();
    }

    private ErrorResponse toErrorResponse(Throwable exception) {
        if (exception instanceof WebApplicationException) {
            return toErrorResponse((WebApplicationException) exception);
        }

        return new ErrorResponse(Response.Status.INTERNAL_SERVER_ERROR);
    }


    private Response getIgnorableWebApplicationExceptionResponse(Throwable t) {
        if (t instanceof WebApplicationException) {
            WebApplicationException wae = (WebApplicationException) t;
            final Response.StatusType status = wae.getResponse().getStatusInfo();
            if (IGNORABLE_STATUS_FAMILIES.contains(status.getFamily())) {
                return wae.getResponse();
            }
        }
        return null;
    }

    private ErrorResponse toErrorResponse(WebApplicationException e) {
        Response.Status status = Response.Status.fromStatusCode(e.getResponse().getStatus());
        for (Class<? extends WebApplicationException> exceptionClass : WRITE_MSG_EXCEPTIONS) {
            if (exceptionClass.isAssignableFrom(e.getClass())) {
                // In this case we forward the error message if there is one
                String msg;
                if (StringUtils.isEmpty(e.getMessage())) {
                    msg = status.getReasonPhrase();
                } else {
                    msg = e.getMessage();
                }
                return new ErrorResponse(status, msg);
            }
        }
        return new ErrorResponse(status);
    }

    private void log(Throwable t, ErrorResponse errorResponse) {
        final String errorMsg = String.format("Returning error response %s. Caused by %s: %s.",
                errorResponse,
                t.getClass().getSimpleName(),
                t.getMessage());

        if (errorResponse.getStatusType().getFamily() == Response.Status.Family.CLIENT_ERROR) {
            LOGGER.warn(errorMsg);
        } else {
            LOGGER.error(errorMsg, t);
        }
    }

}
