
package com.mntanproject.pos;

import com.mntanproject.pos.customer.Customer;
import com.mntanproject.pos.customer.CustomerCursor;
import com.mntanproject.pos.customer.Customer_;
import com.mntanproject.pos.item.Item;
import com.mntanproject.pos.item.ItemCursor;
import com.mntanproject.pos.item.Item_;
import com.mntanproject.pos.purchase.Purchase;
import com.mntanproject.pos.purchase.PurchaseCursor;
import com.mntanproject.pos.purchase.PurchaseItem;
import com.mntanproject.pos.purchase.PurchaseItemCursor;
import com.mntanproject.pos.purchase.PurchaseItem_;
import com.mntanproject.pos.purchase.Purchase_;
import com.mntanproject.pos.supplier.Supplier;
import com.mntanproject.pos.supplier.SupplierCursor;
import com.mntanproject.pos.supplier.Supplier_;
import io.objectbox.BoxStore;
import io.objectbox.BoxStoreBuilder;
import io.objectbox.ModelBuilder;
import io.objectbox.ModelBuilder.EntityBuilder;
import io.objectbox.model.PropertyFlags;
import io.objectbox.model.PropertyType;

// THIS CODE IS GENERATED BY ObjectBox, DO NOT EDIT.
/**
 * Starting point for working with your ObjectBox. All boxes are set up for your objects here.
 * <p>
 * First steps (Android): get a builder using {@link #builder()}, call {@link BoxStoreBuilder#androidContext(Object)},
 * and {@link BoxStoreBuilder#build()} to get a {@link BoxStore} to work with.
 */
public class MyObjectBox {

    public static BoxStoreBuilder builder() {
        BoxStoreBuilder builder = new BoxStoreBuilder(getModel());
        builder.entity(Item_.__INSTANCE);
        builder.entity(PurchaseItem_.__INSTANCE);
        builder.entity(Supplier_.__INSTANCE);
        builder.entity(Customer_.__INSTANCE);
        builder.entity(Purchase_.__INSTANCE);
        return builder;
    }

    private static byte[] getModel() {
        ModelBuilder modelBuilder = new ModelBuilder();
        modelBuilder.lastEntityId(5, 8925388192994975963L);
        modelBuilder.lastIndexId(0, 0L);
        modelBuilder.lastRelationId(0, 0L);

        buildEntityItem(modelBuilder);
        buildEntityPurchaseItem(modelBuilder);
        buildEntitySupplier(modelBuilder);
        buildEntityCustomer(modelBuilder);
        buildEntityPurchase(modelBuilder);

        return modelBuilder.build();
    }

    private static void buildEntityItem(ModelBuilder modelBuilder) {
        EntityBuilder entityBuilder = modelBuilder.entity("Item");
        entityBuilder.id(2, 2087493086560667813L).lastPropertyId(7, 8271214205323964194L);
        entityBuilder.flags(io.objectbox.model.EntityFlags.USE_NO_ARG_CONSTRUCTOR);

        entityBuilder.property("id", PropertyType.Long).id(1, 2552309908272431130L)
                .flags(PropertyFlags.ID);
        entityBuilder.property("name", PropertyType.String).id(2, 7971694890988674973L);
        entityBuilder.property("barcode", PropertyType.String).id(3, 7381048183178545869L);
        entityBuilder.property("notes", PropertyType.String).id(4, 4161212984221238531L);
        entityBuilder.property("quantityOnHand", PropertyType.Float).id(5, 7444266276218324344L)
                .flags(PropertyFlags.NOT_NULL);
        entityBuilder.property("purchasePrice", PropertyType.Float).id(6, 5384243491203163328L)
                .flags(PropertyFlags.NOT_NULL);
        entityBuilder.property("sellingPrice", PropertyType.Float).id(7, 8271214205323964194L)
                .flags(PropertyFlags.NOT_NULL);


        entityBuilder.entityDone();
    }

    private static void buildEntityPurchaseItem(ModelBuilder modelBuilder) {
        EntityBuilder entityBuilder = modelBuilder.entity("PurchaseItem");
        entityBuilder.id(5, 8925388192994975963L).lastPropertyId(3, 8478296796232661141L);
        entityBuilder.flags(io.objectbox.model.EntityFlags.USE_NO_ARG_CONSTRUCTOR);

        entityBuilder.property("id", PropertyType.Long).id(1, 3516061428767877077L)
                .flags(PropertyFlags.ID);
        entityBuilder.property("purchaseId", PropertyType.Long).id(2, 3866108218749229459L)
                .flags(PropertyFlags.NOT_NULL);
        entityBuilder.property("itemId", PropertyType.Long).id(3, 8478296796232661141L)
                .flags(PropertyFlags.NOT_NULL);


        entityBuilder.entityDone();
    }

    private static void buildEntitySupplier(ModelBuilder modelBuilder) {
        EntityBuilder entityBuilder = modelBuilder.entity("Supplier");
        entityBuilder.id(4, 4529302708005220731L).lastPropertyId(11, 439665898141772501L);
        entityBuilder.flags(io.objectbox.model.EntityFlags.USE_NO_ARG_CONSTRUCTOR);

        entityBuilder.property("id", PropertyType.Long).id(1, 2246601755771143771L)
                .flags(PropertyFlags.ID);
        entityBuilder.property("company", PropertyType.String).id(2, 6664671108157237282L);
        entityBuilder.property("name", PropertyType.String).id(3, 3523655765560635223L);
        entityBuilder.property("contactno", PropertyType.String).id(4, 1371559478285193052L);
        entityBuilder.property("email", PropertyType.String).id(5, 6573934639819174232L);
        entityBuilder.property("street", PropertyType.String).id(6, 7501083564381277300L);
        entityBuilder.property("city", PropertyType.String).id(7, 6079398071789428068L);
        entityBuilder.property("state", PropertyType.String).id(8, 5733008235781987050L);
        entityBuilder.property("country", PropertyType.String).id(9, 5398709359948897616L);
        entityBuilder.property("bank", PropertyType.String).id(10, 3316625132156260981L);
        entityBuilder.property("notes", PropertyType.String).id(11, 439665898141772501L);


        entityBuilder.entityDone();
    }

    private static void buildEntityCustomer(ModelBuilder modelBuilder) {
        EntityBuilder entityBuilder = modelBuilder.entity("Customer");
        entityBuilder.id(1, 7962689150567666882L).lastPropertyId(11, 4864809658214195399L);
        entityBuilder.flags(io.objectbox.model.EntityFlags.USE_NO_ARG_CONSTRUCTOR);

        entityBuilder.property("id", PropertyType.Long).id(1, 5499334786606691170L)
                .flags(PropertyFlags.ID);
        entityBuilder.property("name", PropertyType.String).id(2, 990753746376828552L);
        entityBuilder.property("company", PropertyType.String).id(3, 1373954827603172048L);
        entityBuilder.property("contact", PropertyType.String).id(4, 4879202830493961259L);
        entityBuilder.property("email", PropertyType.String).id(5, 3732291610775990495L);
        entityBuilder.property("street", PropertyType.String).id(6, 1062719163979393218L);
        entityBuilder.property("city", PropertyType.String).id(7, 1153278133070424890L);
        entityBuilder.property("state", PropertyType.String).id(8, 3319362406259430515L);
        entityBuilder.property("country", PropertyType.String).id(9, 8711235212729853168L);
        entityBuilder.property("bank", PropertyType.String).id(10, 7285964555320907484L);
        entityBuilder.property("notes", PropertyType.String).id(11, 4864809658214195399L);


        entityBuilder.entityDone();
    }

    private static void buildEntityPurchase(ModelBuilder modelBuilder) {
        EntityBuilder entityBuilder = modelBuilder.entity("Purchase");
        entityBuilder.id(3, 8050912771388907097L).lastPropertyId(5, 7321988419142898435L);
        entityBuilder.flags(io.objectbox.model.EntityFlags.USE_NO_ARG_CONSTRUCTOR);

        entityBuilder.property("id", PropertyType.Long).id(1, 7861542812595423630L)
                .flags(PropertyFlags.ID);
        entityBuilder.property("total", PropertyType.Float).id(2, 6643498890010629770L)
                .flags(PropertyFlags.NOT_NULL);
        entityBuilder.property("createdOn", PropertyType.Date).id(3, 8020995672773953470L);
        entityBuilder.property("lastEditedOn", PropertyType.Date).id(5, 7321988419142898435L);
        entityBuilder.property("supplierId", PropertyType.Long).id(4, 4517040027228705031L)
                .flags(PropertyFlags.NOT_NULL);


        entityBuilder.entityDone();
    }


}
