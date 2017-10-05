package com.aaizuss.http;

// currently assumes the header looks like "bytes=0-50" or "bytes=0-50/100"
public class RangeParser {
    public static String[] getRangeValues(String header) {
        String[] values = new String[2];

        int beginIndex = header.indexOf("=");
        int endIndex = header.indexOf("-");
        int stopIndex = findStopIndex(header);
        String rangeStart = header.substring(beginIndex + 1, endIndex);
        String rangeEnd = header.substring(endIndex + 1, stopIndex);
        values[0] = rangeStart;
        values[1] = rangeEnd;

        return values;
    }

    private static int findStopIndex(String header) {
        int stopIndex = header.indexOf("/");
        if (stopIndex == -1) {
            stopIndex = header.length();
        }
        return stopIndex;
    }
}
