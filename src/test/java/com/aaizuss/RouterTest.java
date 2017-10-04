package com.aaizuss;

import com.aaizuss.handler.Handler;
import com.aaizuss.http.Request;
import com.aaizuss.http.Response;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class RouterTest {
    private Router router;
    private Request request = new Request("GET", "/path/to/resource");
    private Request postRequest = new Request("POST", "/form");

    @Before
    public void setUp() {
        router = new Router();
    }

    @Test
    public void testAddRoute() {
        router.addRoute("POST", "/form", new MockHandler(postRequest));
        router.addRoute("GET", "/path/to/resource", new MockHandler(request));
        
        assertTrue(router.getRoutes().containsKey("POST /form"));
        assertTrue(router.getRoutes().containsKey("GET /path/to/resource"));
    }

    @Test
    public void testGetHandler() {
        router.addRoute("POST", "/form", new MockHandler(postRequest));
        Handler handler = router.getHandler(postRequest);

        assertTrue(handler instanceof MockHandler);
    }

    @Test
    public void testGetResponse() {
        router.addRoute("GET", "/path/to/resource", new MockHandler(request));
        Response response = router.getResponse(request);

        assertEquals(Status.OK, response.getStatus());
    }

}
