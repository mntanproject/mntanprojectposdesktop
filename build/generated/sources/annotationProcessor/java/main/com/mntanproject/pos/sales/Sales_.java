
package com.mntanproject.pos.sales;

import com.mntanproject.pos.sales.Sales.SalesItemsConverter;
import com.mntanproject.pos.sales.SalesCursor.Factory;
import io.objectbox.EntityInfo;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.internal.CursorFactory;
import io.objectbox.internal.IdGetter;
import java.util.ArrayList;

// THIS CODE IS GENERATED BY ObjectBox, DO NOT EDIT.

/**
 * Properties for entity "Sales". Can be used for QueryBuilder and for referencing DB names.
 */
public final class Sales_ implements EntityInfo<Sales> {

    // Leading underscores for static constants to avoid naming conflicts with property names

    public static final String __ENTITY_NAME = "Sales";

    public static final int __ENTITY_ID = 6;

    public static final Class<Sales> __ENTITY_CLASS = Sales.class;

    public static final String __DB_NAME = "Sales";

    public static final CursorFactory<Sales> __CURSOR_FACTORY = new Factory();

    @Internal
    static final SalesIdGetter __ID_GETTER = new SalesIdGetter();

    public final static Sales_ __INSTANCE = new Sales_();

    public final static io.objectbox.Property<Sales> id =
        new io.objectbox.Property<>(__INSTANCE, 0, 1, long.class, "id", true, "id");

    public final static io.objectbox.Property<Sales> customerId =
        new io.objectbox.Property<>(__INSTANCE, 1, 2, long.class, "customerId");

    public final static io.objectbox.Property<Sales> total =
        new io.objectbox.Property<>(__INSTANCE, 2, 3, float.class, "total");

    public final static io.objectbox.Property<Sales> date =
        new io.objectbox.Property<>(__INSTANCE, 3, 4, java.util.Date.class, "date");

    public final static io.objectbox.Property<Sales> lastModified =
        new io.objectbox.Property<>(__INSTANCE, 4, 5, java.util.Date.class, "lastModified");

    public final static io.objectbox.Property<Sales> items =
        new io.objectbox.Property<>(__INSTANCE, 5, 6, String.class, "items", false, "items", SalesItemsConverter.class, ArrayList.class);

    @SuppressWarnings("unchecked")
    public final static io.objectbox.Property<Sales>[] __ALL_PROPERTIES = new io.objectbox.Property[]{
        id,
        customerId,
        total,
        date,
        lastModified,
        items
    };

    public final static io.objectbox.Property<Sales> __ID_PROPERTY = id;

    @Override
    public String getEntityName() {
        return __ENTITY_NAME;
    }

    @Override
    public int getEntityId() {
        return __ENTITY_ID;
    }

    @Override
    public Class<Sales> getEntityClass() {
        return __ENTITY_CLASS;
    }

    @Override
    public String getDbName() {
        return __DB_NAME;
    }

    @Override
    public io.objectbox.Property<Sales>[] getAllProperties() {
        return __ALL_PROPERTIES;
    }

    @Override
    public io.objectbox.Property<Sales> getIdProperty() {
        return __ID_PROPERTY;
    }

    @Override
    public IdGetter<Sales> getIdGetter() {
        return __ID_GETTER;
    }

    @Override
    public CursorFactory<Sales> getCursorFactory() {
        return __CURSOR_FACTORY;
    }

    @Internal
    static final class SalesIdGetter implements IdGetter<Sales> {
        @Override
        public long getId(Sales object) {
            return object.id;
        }
    }

}