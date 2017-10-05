package com.aaizuss.http;

import java.util.Hashtable;

public class ContentRange {

    public static final String START = "Start";
    public static final String END = "End";

    public static int[] getRange(Hashtable<String,Integer> range, int contentLength) {
        int[] result = new int[2];
        if (hasStartAndEnd(range)) {
            result[0] = range.get(START);
            result[1] = range.get(END) + 1;
        } else if (hasOnlyStart(range)) {
            result[0] = range.get(START);
            result[1] = contentLength;
        } else if (hasOnlyEnd(range)) {
            result[0] = contentLength - (range.get(END) - 1);
            result[1] = contentLength;
        }
        return result;
    }

    private static boolean hasStartAndEnd(Hashtable<String,Integer> range) {
        return hasStart(range) && hasEnd(range);
    }

    private static boolean hasOnlyStart(Hashtable<String,Integer> range) {
        return hasStart(range) && !hasEnd(range);
    }

    private static boolean hasOnlyEnd(Hashtable<String,Integer> range) {
        return !hasStart(range) && hasEnd(range);
    }

    private static boolean hasStart(Hashtable<String,Integer> range) {
        return range.containsKey(START);
    }

    private static boolean hasEnd(Hashtable<String,Integer> range) {
        return range.containsKey(END);
    }
}
