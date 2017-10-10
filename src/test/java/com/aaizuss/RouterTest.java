package com.aaizuss;

import com.aaizuss.http.Request;
import com.aaizuss.http.Response;
import com.aaizuss.http.Status;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class RouterTest {
    private Router router;
    private Request request = new Request("GET", "/path/to/resource");
    private Request postRequest = new Request("POST", "/form");

    @Before
    public void setUp() {
        router = new Router();
    }

    @Test
    public void testCreateKey() {
        String expected = ("POST /form");
        assertEquals(expected, router.createKey(postRequest));
    }

    @Test
    public void testAddRoute() {
        router.addRoute("POST", "/form", new MockHandler(Status.OK));

        assertEquals(Status.OK, router.getResponse(postRequest).getStatus());
    }

    @Test
    public void testGetResponseReturnsNotFoundWhenNoRoute() {
        String responseStatus = router.getResponse(postRequest).getStatus();
        assertEquals(Status.NOT_FOUND, responseStatus);
    }

    @Test
    public void testGetResponse() {
        router.addRoute("GET", "/path/to/resource", new MockHandler(Status.OK));
        router.addRoute("DELETE", "/image", new MockHandler(Status.METHOD_NOT_ALLOWED));
        Response response = router.getResponse(request);

        assertEquals(Status.OK, response.getStatus());
    }

    @Test
    public void testGetResponseTwo() {
        router.addRoute("GET", "/path/to/resource", new MockHandler(Status.OK));
        router.addRoute("DELETE", "/image", new MockHandler(Status.METHOD_NOT_ALLOWED));
        Request deleteRequest = new Request("DELETE", "/image");
        Response response = router.getResponse(deleteRequest);

        assertEquals(Status.METHOD_NOT_ALLOWED, response.getStatus());
    }

}
