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
package de.cb.playground.androidannotations.app.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import de.cb.playground.androidannotations.app.R;
import de.cb.playground.androidannotations.app.fragment.common.ErrorFragment;
import de.cb.playground.androidannotations.app.fragment.common.ErrorFragment_;
import de.cb.playground.androidannotations.app.fragment.common.ProgressFragment;
import de.cb.playground.androidannotations.app.fragment.common.ProgressFragment_;
import de.cb.playground.androidannotations.app.fragment.rest.BookListFragment;
import de.cb.playground.androidannotations.app.fragment.rest.BookListFragment_;
import de.cb.playground.androidannotations.app.rest.BooksServer;
import de.cb.playground.androidannotations.app.rest.entity.BookDto;
import de.cb.playground.androidannotations.app.rest.error.BackendErrorException;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Shows the use of {@link @RestService} to send requests to a REST service.
 */
@EActivity(R.layout.activity_rest)
public class RestActivity extends Activity {
    private static final String TAG = RestActivity.class.getName();

    @Bean
    BooksServer booksServer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Triggers the loading and display of data form a REST service call.
        if (savedInstanceState == null) {
            showProgress();
            updateData();
        }
    }

    /**
     * Loads all books via the {@link de.cb.playground.androidannotations.app.rest.SimpleRestClient}.
     */
    // Avoid android.os.NetworkOnMainThreadException
    @Background
    void updateData() {
        try {
            displayRestServiceResponses(booksServer.listBooks());
        } catch (BackendErrorException e) {
            displayError(e);
        }
    }

    /**
     * Replaces the current frament in the container view with a fragment that displays the fetched data.
     *
     * @param books The books fetched from the server.
     */
    // Is run on the UI Thread. Only this thread is allowed to modify views
    @UiThread
    void displayRestServiceResponses(List<BookDto> books) {
        final BookListFragment bookFrag = BookListFragment_.builder()
                .parcelableArrayListArg(BookListFragment.BOOKS_KEY, new ArrayList<Parcelable>(books))
                .build();

        Log.i(TAG, "Replacing the progress fragment with the books list fragment.");
        this.getFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, bookFrag)
                .commit();
    }

    /**
     * Displays a fragment showing the info from the given exception.
     *
     * @param e The exception to display.
     */
    private void displayError(BackendErrorException e) {
        Log.i(TAG, String.format("Displaying the error fragment with exception: %s", e));
        final ErrorFragment errorFrag = ErrorFragment_.builder()
                .arg(ErrorFragment.KEY_ERROR_MSG,
                        StringUtils.defaultString(
                                String.format("Connecting to '%s' backend failed: %s",
                                        e.getBackendName(),
                                        e.getMessage()),
                                e.getClass().getName()))
                .build();

        this.getFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, errorFrag)
                .commit();
    }

    /**
     * Initializes the container view with a fragment that shows a rotating progress bar.
     */
    private void showProgress() {
        final ProgressFragment progressFragment = ProgressFragment_.builder().build();

        Log.i(TAG, "Adding the progress fragment.");
        this.getFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, progressFragment)
                .commit();
    }
}
