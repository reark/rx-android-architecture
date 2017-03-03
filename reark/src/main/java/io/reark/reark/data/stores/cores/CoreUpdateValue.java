/**
 * This file is part of QuickBeer.
 * Copyright (C) 2017 Antti Poikela <antti.poikela@iki.fi>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package io.reark.reark.data.stores.cores;

import android.content.ContentProviderOperation;
import android.net.Uri;
import android.support.annotation.NonNull;

/**
 * A class used to represent a change to the database.
 */
final class CoreUpdateValue<U> implements CoreValue<U> {

    private final int id;

    @NonNull
    private final Uri uri;

    @NonNull
    private final U item;

    private CoreUpdateValue(int id, @NonNull Uri uri, @NonNull U item) {
        this.id = id;
        this.item = item;
        this.uri = uri;
    }

    @NonNull
    static <U> CoreUpdateValue<U> create(int id, @NonNull Uri uri, @NonNull U item) {
        return new CoreUpdateValue<>(id, uri, item);
    }

    @NonNull
    CoreOperation toOperation(@NonNull ContentProviderOperation operation) {
        return new CoreOperation(id, uri, operation);
    }

    @NonNull
    CoreOperation noOperation() {
        return new CoreOperation(id, uri);
    }

    public int id() {
        return id;
    }

    @NonNull
    public Uri uri() {
        return uri;
    }

    @NonNull
    public U item() {
        return item;
    }

    @NonNull
    @Override
    public Type type() {
        return Type.UPDATE;
    }
}
