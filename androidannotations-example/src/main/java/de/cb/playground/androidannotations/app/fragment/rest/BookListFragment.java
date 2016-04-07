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
package de.cb.playground.androidannotations.app.fragment.rest;

import android.app.Fragment;
import android.widget.TextView;
import com.google.common.base.Joiner;
import de.cb.playground.androidannotations.app.R;
import de.cb.playground.androidannotations.app.rest.entity.BookDto;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

@EFragment(R.layout.fragment_bookslist)
public class BookListFragment extends Fragment {

    public static final String BOOKS_KEY = BookListFragment.class.getName() + ".key.books";

    @ViewById(R.id.restServiceBooksListContent)
    TextView booksListTextView;

    @AfterViews
    void init() {
        final ArrayList<BookDto> books = getArguments().getParcelableArrayList(BOOKS_KEY);
        booksListTextView.setText(Joiner.on(',').join(books));
    }
}
