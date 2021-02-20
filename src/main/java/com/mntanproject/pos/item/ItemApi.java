package com.mntanproject.pos.item;

import com.mntanproject.pos.database.GenericRelationApi;
import com.mntanproject.pos.supplier.Supplier_;

public class ItemApi extends GenericRelationApi<Item> {

    private static final Class<?> generatedClass = Item_.class;

    public ItemApi() {
        super(generatedClass);
    }
}
