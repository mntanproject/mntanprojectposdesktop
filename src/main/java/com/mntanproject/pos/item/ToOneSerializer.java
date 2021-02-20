package com.mntanproject.pos.item;

import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import io.objectbox.relation.ToOne;
import java.lang.reflect.Type;

public class ToOneSerializer<TARGET> implements JsonSerializer<ToOne<TARGET>> {
    @Override
    public JsonElement serialize(ToOne src, Type typeOfSrc, JsonSerializationContext context) {
        System.out.println("serializer: " + (src.getTarget()));
        return context.serialize(src.getTarget());

    }
}
