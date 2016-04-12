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
import android.widget.Toast;
import de.cb.playground.androidannotations.app.R;
import de.cb.playground.androidannotations.app.greet.Greeter;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;

/**
 * Application entry activity providing the navigation for the various examples.
 */
@EActivity(R.layout.activity_main)
public class MainActivity extends Activity {
    // Injects custom EBean
    @Bean
    Greeter greeter;

    @Click(R.id.greetButton)
    void greet() { // The method name matches the buttons ID
        Toast.makeText(this, greeter.getGreet(), Toast.LENGTH_SHORT).show();
    }
}
