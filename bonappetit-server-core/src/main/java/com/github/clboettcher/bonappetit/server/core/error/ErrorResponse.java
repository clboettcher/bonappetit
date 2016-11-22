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
package com.github.clboettcher.bonappetit.server.core.error;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.ws.rs.core.Response;

public class ErrorResponse {

    private Response.Status status;
    private String message;

    public ErrorResponse(Response.Status status, String message) {
        this.status = status;
        this.message = message;
    }

    public ErrorResponse(Response.Status status) {
        this(status, status.getReasonPhrase());
    }

    @ApiModelProperty(value = "The HTTP status that was sent.", example = "400", required = true)
    public int getStatus() {
        return status.getStatusCode();
    }

    @ApiModelProperty(hidden = true)
    @JsonIgnore
    public Response.Status getStatusType() {
        return status;
    }

    @ApiModelProperty(value = "The error message.", example = "Bad Request: xyz", required = true)
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("status", status)
                .append("message", message)
                .toString();
    }
}
