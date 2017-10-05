package com.aaizuss.encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

public class ShaEncoder {
    public static String encode(String string) {
        String sha = "";
        try {
            MessageDigest msgDigest = MessageDigest.getInstance("SHA-1");
            msgDigest.reset();
            msgDigest.update(string.getBytes("UTF-8"));
            sha = bytesToHex(msgDigest.digest());
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return sha;
    }

    private static String bytesToHex(byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String hex = formatter.toString();
        formatter.close();
        return hex;
    }
}
