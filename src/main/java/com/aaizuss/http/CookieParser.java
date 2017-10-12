package com.aaizuss.http;

import java.util.Hashtable;

// https://tools.ietf.org/html/rfc6265

// this only handles parsing name and value - ignores other cookie properties
public class CookieParser {
    private static Hashtable<String,String> cookie;

    public static String getCookie(String rawCookie, String cookieName) throws InvalidCookieException {
        parseNameValue(getRawParams(rawCookie));
        return cookie.get(cookieName);
    }

    private static void parseNameValue(String[] rawCookieParams) throws InvalidCookieException {
        cookie = new Hashtable<>();
        String[] nameValuePair = rawCookieParams[0].trim().split("=");
        if (nameValuePair.length != 2) {
            throw new InvalidCookieException();
        } else {
            String name = nameValuePair[0].trim();
            String value = nameValuePair[1].trim();
            cookie.put(name, value);
        }
    }

    private static String[] getRawParams(String rawCookie) {
        return rawCookie.split(";");
    }

}
