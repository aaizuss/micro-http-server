package com.aaizuss;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CookieParserTest {

    private String rawCookie;

    @Test
    public void testGetCookieFromName() throws InvalidCookieException {
        rawCookie = "type=chocolate";
        assertEquals("chocolate", CookieParser.getCookie(rawCookie, "type"));
    }

    @Test
    public void testGetCookieFromDifferentName() throws InvalidCookieException {
        rawCookie = "texture=chewy";
        assertEquals("chewy", CookieParser.getCookie(rawCookie, "texture"));
    }

    @Test
    public void testGetCookieFromComplexParams() throws InvalidCookieException {
        rawCookie = "type=sugar; Path=/accounts; Expires=Wed, 13 Jan 2021 22:23:01 GMT; Secure; HttpOnly";
        assertEquals("sugar", CookieParser.getCookie(rawCookie, "type"));
    }
}
