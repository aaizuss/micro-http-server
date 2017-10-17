package com.aaizuss.http;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class BasicAuthenticatorTest {
    private String authHeaderValue = "Basic YWRtaW46aHVudGVyMg==";
    private String poorlyFormattedHeaderValue = "Basi YWRtaW46aHVudGVyMg==";

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testDecodeCredentials() {
        assertEquals("admin:hunter2", BasicAuthenticator.decodeCredentials("YWRtaW46aHVudGVyMg=="));
    }

    @Test
    public void testGetCredentialsWithValidFormatting() throws AuthFormatException {
        assertEquals("YWRtaW46aHVudGVyMg==", BasicAuthenticator.extractCredentials(authHeaderValue));
    }

    @Test
    public void testGetCredentialsWithBadFormattingThrowsException() throws AuthFormatException {
        thrown.expect(AuthFormatException.class);
        thrown.expectMessage("Invalid Authorization header format");

        BasicAuthenticator.extractCredentials(poorlyFormattedHeaderValue);
    }
}
