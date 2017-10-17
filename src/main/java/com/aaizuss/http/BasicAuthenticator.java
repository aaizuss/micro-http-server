package com.aaizuss.http;

import java.util.Base64;

// https://tools.ietf.org/html/rfc7617
// https://www.w3.org/Protocols/HTTP/1.0/spec.html#BasicAA
// users should write their own method to validate the credentials
public class BasicAuthenticator {

    private static String BASIC = "Basic";

    public static String decodeCredentials(String codedCredentials) {
        byte[] decodedText = Base64.getDecoder().decode(codedCredentials.getBytes());
        return new String(decodedText);
    }

    public static String extractCredentials(String headerValue) throws AuthFormatException {
        String credentials;
        if (validAuthHeader(headerValue)) {
            credentials = headerValue.substring(6);
        } else {
            throw new AuthFormatException();
        }
        return credentials;
    }

    private static boolean validAuthHeader(String headerValue) {
        return isBasicAuthType(headerValue) && validLength(headerValue);
    }

    private static boolean isBasicAuthType(String headerValue) {
        return getAuthType(headerValue).equals(BASIC);
    }

    private static boolean validLength(String headerValue) {
        return headerValue.length() == 26;
    }

    private static String getAuthType(String headerValue) {
        String[] parts = headerValue.split(" ");
        return parts[0];
    }

}
