package com.mntanproject.pos.purchase;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class PurchaseItem {

    @Id
    public long id;
    public long purchaseId,itemId;
}
