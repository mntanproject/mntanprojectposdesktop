package com.mntanproject.pos.purchase;

import com.mntanproject.pos.item.Item;
import io.objectbox.BoxStore;
import io.objectbox.Cursor;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.internal.CursorFactory;
import io.objectbox.relation.ToMany;
import java.util.List;

// THIS CODE IS GENERATED BY ObjectBox, DO NOT EDIT.

/**
 * ObjectBox generated Cursor implementation for "Purchase".
 * Note that this is a low-level class: usually you should stick to the Box class.
 */
public final class PurchaseCursor extends Cursor<Purchase> {
    @Internal
    static final class Factory implements CursorFactory<Purchase> {
        @Override
        public Cursor<Purchase> createCursor(io.objectbox.Transaction tx, long cursorHandle, BoxStore boxStoreForEntities) {
            return new PurchaseCursor(tx, cursorHandle, boxStoreForEntities);
        }
    }

    private static final Purchase_.PurchaseIdGetter ID_GETTER = Purchase_.__ID_GETTER;


    private final static int __ID_total = Purchase_.total.id;
    private final static int __ID_createdOn = Purchase_.createdOn.id;

    public PurchaseCursor(io.objectbox.Transaction tx, long cursor, BoxStore boxStore) {
        super(tx, cursor, Purchase_.__INSTANCE, boxStore);
    }

    @Override
    public final long getId(Purchase entity) {
        return ID_GETTER.getId(entity);
    }

    /**
     * Puts an object into its box.
     *
     * @return The ID of the object within its box.
     */
    @Override
    public final long put(Purchase entity) {
        java.util.Date createdOn = entity.createdOn;
        int __id2 = createdOn != null ? __ID_createdOn : 0;

        long __assignedId = collect313311(cursor, entity.id, PUT_FLAG_FIRST | PUT_FLAG_COMPLETE,
                0, null, 0, null,
                0, null, 0, null,
                __id2, __id2 != 0 ? createdOn.getTime() : 0, 0, 0,
                0, 0, 0, 0,
                0, 0, 0, 0,
                __ID_total, entity.total, 0, 0);

        entity.id = __assignedId;

        attachEntity(entity);
        checkApplyToManyToDb(entity.items, Item.class);
        return __assignedId;
    }

    private void attachEntity(Purchase entity) {
        // Transformer will create __boxStore field in entity and init it here:
        // entity.__boxStore = boxStoreForEntities;
    }

}
