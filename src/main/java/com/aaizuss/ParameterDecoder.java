package com.aaizuss;

import java.util.ArrayList;
import java.util.Hashtable;

public class ParameterDecoder {
    public static String decode(String rawQuery) {
        String[] chars = formatParams(rawQuery);
        ArrayList<Integer> indicesToIgnore = new ArrayList<>();

        return iterateAndDecode(chars, indicesToIgnore);
    }

    private static String iterateAndDecode(String[] chars, ArrayList<Integer> indicesToIgnore) {
        String result = "";
        for (int i = 0; i < chars.length; i++) {
            String currentChar = chars[i];
            if (startsEncoding(currentChar)) {
                String decoded = getCodeAndDecode(chars, i);
                result += decoded;
                indicesToIgnore.add(i + 1);
                indicesToIgnore.add(i + 2);
            } else if (!indicesToIgnore.contains(i)) {
                result += currentChar;
            }
        }
        return result;
    }

    private static String getCodeAndDecode(String[] chars, int startIndex) {
        String code = chars[startIndex] + chars[startIndex + 1] + chars[startIndex + 2];
        return getValueForCode(code);
    }

    private static boolean startsEncoding(String character) {
        return character.equals("%");
    }

    private static String getValueForCode(String code) {
        return getDecodeMap().get(code);
    }

    private static Hashtable<String,String> getDecodeMap() {
        Hashtable<String,String> codes = new Hashtable<>();
        codes.put("%20", " ");
        codes.put("%21", "!");
        codes.put("%22", "\"");
        codes.put("%23", "#");
        codes.put("%24", "$");
        codes.put("%26", "&");
        codes.put("%27", "\'");
        codes.put("%28", "(");
        codes.put("%29", ")");
        codes.put("%2A", "*");
        codes.put("%2B", "+");
        codes.put("%2C", ",");
        codes.put("%2F", "/");
        codes.put("%2E", ".");
        codes.put("%3A", ":");
        codes.put("%3B", ";");
        codes.put("%3C", "<");
        codes.put("%3D", "=");
        codes.put("%3E", ">");
        codes.put("%3F", "?");
        codes.put("%40", "@");
        codes.put("%5B", "[");
        codes.put("%5D", "]");
        codes.put("%5C", "\\");
        codes.put("%5E", "^");
        codes.put("%5F", "_");
        codes.put("%60", "`");
        codes.put("%7B", "{");
        codes.put("%7C", "|");
        codes.put("%7D", "}");
        codes.put("%7E", "~");
        return codes;
    }

    private static String[] formatParams(String query) {
        String formatted = query.replace("=", " = ").replace("&", "\n");
        return formatted.split("");
    }
}
