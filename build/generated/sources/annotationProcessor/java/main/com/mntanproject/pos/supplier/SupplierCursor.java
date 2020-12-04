package com.mntanproject.pos.supplier;

import io.objectbox.BoxStore;
import io.objectbox.Cursor;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.internal.CursorFactory;

// THIS CODE IS GENERATED BY ObjectBox, DO NOT EDIT.

/**
 * ObjectBox generated Cursor implementation for "Supplier".
 * Note that this is a low-level class: usually you should stick to the Box class.
 */
public final class SupplierCursor extends Cursor<Supplier> {
    @Internal
    static final class Factory implements CursorFactory<Supplier> {
        @Override
        public Cursor<Supplier> createCursor(io.objectbox.Transaction tx, long cursorHandle, BoxStore boxStoreForEntities) {
            return new SupplierCursor(tx, cursorHandle, boxStoreForEntities);
        }
    }

    private static final Supplier_.SupplierIdGetter ID_GETTER = Supplier_.__ID_GETTER;


    private final static int __ID_company = Supplier_.company.id;
    private final static int __ID_name = Supplier_.name.id;
    private final static int __ID_contactno = Supplier_.contactno.id;
    private final static int __ID_email = Supplier_.email.id;
    private final static int __ID_street = Supplier_.street.id;
    private final static int __ID_city = Supplier_.city.id;
    private final static int __ID_state = Supplier_.state.id;
    private final static int __ID_country = Supplier_.country.id;
    private final static int __ID_bank = Supplier_.bank.id;
    private final static int __ID_notes = Supplier_.notes.id;

    public SupplierCursor(io.objectbox.Transaction tx, long cursor, BoxStore boxStore) {
        super(tx, cursor, Supplier_.__INSTANCE, boxStore);
    }

    @Override
    public final long getId(Supplier entity) {
        return ID_GETTER.getId(entity);
    }

    /**
     * Puts an object into its box.
     *
     * @return The ID of the object within its box.
     */
    @Override
    public final long put(Supplier entity) {
        String company = entity.company;
        int __id1 = company != null ? __ID_company : 0;
        String name = entity.name;
        int __id2 = name != null ? __ID_name : 0;
        String contactno = entity.contactno;
        int __id3 = contactno != null ? __ID_contactno : 0;
        String email = entity.email;
        int __id4 = email != null ? __ID_email : 0;

        collect400000(cursor, 0, PUT_FLAG_FIRST,
                __id1, company, __id2, name,
                __id3, contactno, __id4, email);

        String street = entity.street;
        int __id5 = street != null ? __ID_street : 0;
        String city = entity.city;
        int __id6 = city != null ? __ID_city : 0;
        String state = entity.state;
        int __id7 = state != null ? __ID_state : 0;
        String country = entity.country;
        int __id8 = country != null ? __ID_country : 0;

        collect400000(cursor, 0, 0,
                __id5, street, __id6, city,
                __id7, state, __id8, country);

        String bank = entity.bank;
        int __id9 = bank != null ? __ID_bank : 0;
        String notes = entity.notes;
        int __id10 = notes != null ? __ID_notes : 0;

        long __assignedId = collect313311(cursor, entity.id, PUT_FLAG_COMPLETE,
                __id9, bank, __id10, notes,
                0, null, 0, null,
                0, 0, 0, 0,
                0, 0, 0, 0,
                0, 0, 0, 0,
                0, 0, 0, 0);

        entity.id = __assignedId;

        return __assignedId;
    }

}
