package com.mntanproject.pos.item;

import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import io.objectbox.relation.ToMany;
import java.lang.reflect.Type;

public class ToManySerializer<TARGET> implements JsonSerializer<ToMany<TARGET>> {

    @Override
    public JsonElement serialize(ToMany<TARGET> src, Type typeOfSrc, JsonSerializationContext context) {

        for (int i=0;i<src.size();i++) {
            System.out.println(src.get(i));
            System.out.println("ToMany: " + context.serialize(src.get(i)));
        }
        return null;
    }

}
