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
package de.cb.playground.androidannotations.app.rest;

import de.cb.playground.androidannotations.app.rest.entity.BookDto;
import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Rest;
import org.androidannotations.api.rest.RestClientErrorHandling;
import org.androidannotations.api.rest.RestClientSupport;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.List;

/**
 * Provides an interface to access a REST api.
 * <p/>
 * Make sure to run de.cb.playgroundandroidannotations.mockserver.MockServer from module mock-server to start the server
 * that is called.
 */
@Rest(
        // The emulator can access localhost at 10.0.2.2
        // If the a dynamic root URL should be used, see https://github.com/excilys/androidannotations/wiki/Rest%20API#rooturl
        rootUrl = "http://10.0.2.2:8888",
        converters = {MappingJackson2HttpMessageConverter.class, StringHttpMessageConverter.class}
)
public interface SimpleRestClient extends RestClientSupport,RestClientErrorHandling {
    @Get("/ping")
    @SuppressWarnings("unused")
    String ping();

    @Get("/books")
    List<BookDto> listBooks();
}
