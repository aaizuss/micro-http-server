package com.aaizuss.handler;

import com.aaizuss.http.Request;
import com.aaizuss.http.Response;

public interface Handler {

    Response execute(Request request);

}
