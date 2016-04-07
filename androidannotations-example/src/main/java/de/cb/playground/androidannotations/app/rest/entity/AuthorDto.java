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
package de.cb.playground.androidannotations.app.rest.entity;

import android.os.Parcel;
import android.os.Parcelable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthorDto implements Parcelable{
    private Long serverId;
    private String name;
    private Integer age;

    public AuthorDto(Parcel in) {
        this.serverId = in.readLong();
        this.name = in.readString();
        this.age = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(serverId);
        dest.writeString(name);
        dest.writeInt(age);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public AuthorDto createFromParcel(Parcel in) {
            return new AuthorDto(in);
        }

        public AuthorDto[] newArray(int size) {
            return new AuthorDto[size];
        }
    };
}
