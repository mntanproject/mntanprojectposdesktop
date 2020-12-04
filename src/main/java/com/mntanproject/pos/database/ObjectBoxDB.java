package com.mntanproject.pos.database;

import com.mntanproject.pos.MyObjectBox;
import io.objectbox.BoxStore;

public class ObjectBoxDB {

    public String dbname;
    private static BoxStore boxStore;

    public static void init() {
         boxStore = MyObjectBox.builder().name("objectbox-mntanprojectpos-db").build();
    }

    public static BoxStore get() {
        return boxStore;
    }

    public static void close(){
        if (boxStore != null){
            boxStore.close();
        }
    }
}
