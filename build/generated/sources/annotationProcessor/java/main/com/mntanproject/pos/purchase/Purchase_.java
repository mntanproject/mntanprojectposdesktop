
package com.mntanproject.pos.purchase;

import com.mntanproject.pos.purchase.Purchase.PurchaseCustomConverter;
import com.mntanproject.pos.purchase.PurchaseCursor.Factory;
import io.objectbox.EntityInfo;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.internal.CursorFactory;
import io.objectbox.internal.IdGetter;
import java.util.ArrayList;

// THIS CODE IS GENERATED BY ObjectBox, DO NOT EDIT.

/**
 * Properties for entity "Purchase". Can be used for QueryBuilder and for referencing DB names.
 */
public final class Purchase_ implements EntityInfo<Purchase> {

    // Leading underscores for static constants to avoid naming conflicts with property names

    public static final String __ENTITY_NAME = "Purchase";

    public static final int __ENTITY_ID = 3;

    public static final Class<Purchase> __ENTITY_CLASS = Purchase.class;

    public static final String __DB_NAME = "Purchase";

    public static final CursorFactory<Purchase> __CURSOR_FACTORY = new Factory();

    @Internal
    static final PurchaseIdGetter __ID_GETTER = new PurchaseIdGetter();

    public final static Purchase_ __INSTANCE = new Purchase_();

    public final static io.objectbox.Property<Purchase> id =
        new io.objectbox.Property<>(__INSTANCE, 0, 1, long.class, "id", true, "id");

    public final static io.objectbox.Property<Purchase> total =
        new io.objectbox.Property<>(__INSTANCE, 1, 2, float.class, "total");

    public final static io.objectbox.Property<Purchase> createdOn =
        new io.objectbox.Property<>(__INSTANCE, 2, 3, java.util.Date.class, "createdOn");

    public final static io.objectbox.Property<Purchase> lastEditedOn =
        new io.objectbox.Property<>(__INSTANCE, 3, 5, java.util.Date.class, "lastEditedOn");

    public final static io.objectbox.Property<Purchase> supplierId =
        new io.objectbox.Property<>(__INSTANCE, 4, 4, long.class, "supplierId");

    public final static io.objectbox.Property<Purchase> items =
        new io.objectbox.Property<>(__INSTANCE, 5, 6, String.class, "items", false, "items", PurchaseCustomConverter.class, ArrayList.class);

    @SuppressWarnings("unchecked")
    public final static io.objectbox.Property<Purchase>[] __ALL_PROPERTIES = new io.objectbox.Property[]{
        id,
        total,
        createdOn,
        lastEditedOn,
        supplierId,
        items
    };

    public final static io.objectbox.Property<Purchase> __ID_PROPERTY = id;

    @Override
    public String getEntityName() {
        return __ENTITY_NAME;
    }

    @Override
    public int getEntityId() {
        return __ENTITY_ID;
    }

    @Override
    public Class<Purchase> getEntityClass() {
        return __ENTITY_CLASS;
    }

    @Override
    public String getDbName() {
        return __DB_NAME;
    }

    @Override
    public io.objectbox.Property<Purchase>[] getAllProperties() {
        return __ALL_PROPERTIES;
    }

    @Override
    public io.objectbox.Property<Purchase> getIdProperty() {
        return __ID_PROPERTY;
    }

    @Override
    public IdGetter<Purchase> getIdGetter() {
        return __ID_GETTER;
    }

    @Override
    public CursorFactory<Purchase> getCursorFactory() {
        return __CURSOR_FACTORY;
    }

    @Internal
    static final class PurchaseIdGetter implements IdGetter<Purchase> {
        @Override
        public long getId(Purchase object) {
            return object.id;
        }
    }

}
