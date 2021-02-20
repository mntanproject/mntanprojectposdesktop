package com.mntanproject.pos.purchase;

import com.mntanproject.pos.database.GenericRelationApi;
import mntanproject.core.server.response.HttpResponse;

public class PurchaseApi extends GenericRelationApi<Purchase> {

    private static final Class<?> generatedPurchase= Purchase_.class;

    public PurchaseApi() {
        super(generatedPurchase);
    }

    @Override
    public String toJson(Object obj){
       PurchaseParser purchaseParser = new PurchaseParser((Purchase) obj);
       return purchaseParser.toJson(true);

    }








}
