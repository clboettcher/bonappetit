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

import com.fasterxml.jackson.datatype.joda.JodaModule;
import de.cb.playground.androidannotations.app.rest.entity.BookDto;
import de.cb.playground.androidannotations.app.rest.error.BackendErrorException;
import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.rest.RestService;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Encapsulates access the to books REST API.
 */
@EBean
public class BooksServer {

    @RestService
    SimpleRestClient restService;

    public List<BookDto> listBooks() throws BackendErrorException {
        try {
            return restService.listBooks();
        } catch (RestClientException e) {
            throw new BackendErrorException("books", e.getMessage(), e);
        }
    }

    /**
     * Configures the spring rest template that is used to make requests to the REST service.
     * <p/>
     * See <a href="https://gist.github.com/eltabo/8931347">https://gist.github.com/eltabo/8931347</a>
     */
    @AfterInject
    void configureRestTemplate() {
        final RestTemplate tmpl = restService.getRestTemplate();
        // Add joda support to jackson message converter
        configureMessageConverters(tmpl);
        // Configure timeouts
        configureRequestFactory(tmpl);

        // Configure error handling
        // TODO set the error handler when figured out how to do it
    }

    private void configureMessageConverters(RestTemplate tmpl) {
        final List<HttpMessageConverter<?>> converters = tmpl.getMessageConverters();
        for (HttpMessageConverter<?> converter : converters) {
            if (converter instanceof MappingJackson2HttpMessageConverter) {
                final MappingJackson2HttpMessageConverter jacksonConv = (MappingJackson2HttpMessageConverter) converter;
                jacksonConv.getObjectMapper().registerModule(new JodaModule());
            }
        }
    }

    private void configureRequestFactory(RestTemplate tmpl) {
        final SimpleClientHttpRequestFactory requestFactory = (SimpleClientHttpRequestFactory) tmpl.getRequestFactory();
        requestFactory.setConnectTimeout(1000);
        requestFactory.setReadTimeout(5000);
    }
}
