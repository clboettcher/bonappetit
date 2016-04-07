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
package de.cb.playground.androidannotations.app.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.google.common.collect.Sets;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import de.cb.playground.androidannotations.app.R;
import de.cb.playground.androidannotations.app.db.entity.Author;
import de.cb.playground.androidannotations.app.db.entity.Book;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Helper class to create, update the DB and to retrieve DAOs from.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String TAG = DatabaseHelper.class.getName();

    /**
     * Name of the DB file.
     */
    private static final String DATABASE_NAME = "database.db";

    /**
     * Any time you make changes to your database objects, you may have to increase the database version
     */
    private static final int DATABASE_VERSION = 1;

    /**
     * All classes that should be used when creating tables.
     */
     /*package-prviate*/static final Set<Class> ENTITY_CLASSES = Sets.<Class>newHashSet(Author.class, Book.class);

    /**
     * Cached DAOs.
     */
    private Map<Class, RuntimeExceptionDao> runtimeExceptionDaos = new HashMap<>(ENTITY_CLASSES.size());

    /**
     * Constructor initializing the helper.
     *
     * @param context App context.
     */
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config);
    }

    /**
     * Callback for first creation of DB.
     */
    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            Log.i(TAG, String.format("Creating tables for classes %s", ENTITY_CLASSES));
            for (Class clazz : ENTITY_CLASSES) {
                TableUtils.createTable(connectionSource, clazz);
            }
        } catch (SQLException e) {
            Log.e(TAG, String.format("Can't create database: %s", StringUtils.defaultString(e.getMessage(), "(no message)")), e);
            throw new RuntimeException(e);
        }

        setupTestData();
    }

    /**
     * Insert some Test-Data into the DB to work with.
     */
    private void setupTestData() {
        RuntimeExceptionDao<Book, Long> bookDao = getDaoForClass(Book.class);
        Book b = new Book("THE Java™ Programming Language", DateTime.now(), new Author("James Gosling", 100));
        bookDao.create(b);
        Log.i(TAG, String.format("Created %s", b));

        Book b2 = new Book("The C++ Programming Language", DateTime.now(), new Author("Bjarne Stroustrup", 150));
        bookDao.create(b2);
        Log.i(TAG, String.format("Created %s", b2));
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        Log.i(TAG, String.format("Upgrading database from version %d to version %d", oldVersion, newVersion));
    }

    /**
     * Returns the cached runtime DAO for the given class or creates a new one if none exists.
     *
     * @param clazz The class to get the DAO for.
     * @param <T>   The class type.
     * @return The DAO for the given class.
     */
    @SuppressWarnings("unchecked") // We know the types of the runtime dao since we create it here
    private <T> RuntimeExceptionDao<T, Long> getDaoForClass(Class<T> clazz) {
        if (!runtimeExceptionDaos.containsKey(clazz)) {
            runtimeExceptionDaos.put(clazz, super.getRuntimeExceptionDao(clazz));
        }

        return runtimeExceptionDaos.get(clazz);
    }

    /**
     * Close the database connections and clear any cached DAOs.
     */
    @Override
    public void close() {
        super.close();
        runtimeExceptionDaos.clear();
    }
}
