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
import android.util.Log;
import android.widget.TextView;
import com.google.common.base.Joiner;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import de.cb.playground.androidannotations.app.R;
import de.cb.playground.androidannotations.app.db.DatabaseHelper;
import de.cb.playground.androidannotations.app.db.entity.Book;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OrmLiteDao;
import org.androidannotations.annotations.ViewById;

import java.util.List;

/**
 * Displays contents from the database.
 */
@EActivity(R.layout.activity_database)
public class DatabaseActivity extends Activity {

    private static final String TAG = DatabaseActivity.class.getName();

    // TODO Use non-runtime exception dao here and display data access errors in the error fragment instead of crashing
    @OrmLiteDao(helper = DatabaseHelper.class, model = Book.class)
    RuntimeExceptionDao<Book, Long> bookDao;

    @ViewById
    TextView booksListTextArea;

    // Is called after the views have been injected
    @AfterViews
    void displayAllBooks() {
        final List<Book> allBooks = bookDao.queryForAll();
        Log.i(TAG, String.format("Displaying books: %s", allBooks));
        booksListTextArea.setText(Joiner.on(',').join(allBooks));
    }
}
