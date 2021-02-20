package com.mntanproject.pos;

import mntanproject.core.server.response.ContentType;
import mntanproject.core.server.response.HttpResponse;
import mntanproject.core.server.response.StatusCode;

public class FaviconApi {


    public HttpResponse favicon(String params) {
        HttpResponse response = new HttpResponse(StatusCode.ERROR, ContentType.JSON, "fetch favicon");
        System.out.println("Fetching favicon...");
        return response;
    }
}
