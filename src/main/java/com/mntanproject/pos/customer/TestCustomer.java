package com.mntanproject.pos.customer;

import com.mntanproject.pos.database.ObjectBoxDB;
import com.mntanproject.pos.supplier.Supplier;
import io.objectbox.Box;
import io.objectbox.Property;
import io.objectbox.query.QueryBuilder;
import okhttp3.*;

import java.io.IOException;
import java.lang.reflect.Field;

public class TestCustomer {

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    OkHttpClient client = new OkHttpClient();


    String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    String bowlingJson(String player1, String player2) {
        return "{'name':'monica4555',"
                + "'company':'monicaComp'"
                + "}";
    }

    public static void main(String[] args) throws IOException {
        TestCustomer  example = new TestCustomer ();
        String json = example.bowlingJson("Jesse", "Jake");
        String response = example.post("http://192.168.1.12:8090/api/customer/add/", json);
        System.out.println(response);
    }
}
