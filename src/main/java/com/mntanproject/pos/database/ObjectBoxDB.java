package com.mntanproject.pos.database;


import com.mntanproject.pos.MyObjectBox;
import io.objectbox.BoxStore;
import io.objectbox.DebugFlags;

public class ObjectBoxDB {

    public String dbname;
    private static BoxStore boxStore;

    public static void init() {

        boxStore = MyObjectBox.builder().name("objectbox-mntanprojectpos-db").build();
        System.out.println("initialized boxstore from init");

    }

    public static BoxStore get() {
        if (boxStore == null){
            System.out.println("boxstore is null reinitialize boxstore");
            init();
        }
        return boxStore;
    }

    public static void close(){
        if (boxStore != null){
            boxStore.close();
        }
    }
}
