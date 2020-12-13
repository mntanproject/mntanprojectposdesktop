package com.mntanproject.pos.supplier;

import com.mntanproject.pos.database.GenericApi;

public class SupplierApi extends GenericApi<Supplier> {

    private static final Class<?> generatedSupplier = Supplier_.class;

    public SupplierApi() {
        super(generatedSupplier);
        System.out.println("SupplierApi called");
    }

}
