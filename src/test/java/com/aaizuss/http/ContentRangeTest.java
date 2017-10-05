package com.aaizuss.http;

import org.junit.Test;

import java.util.Hashtable;

import static org.junit.Assert.assertEquals;

public class ContentRangeTest {

    @Test
    public void getRangeWithStartAndEnd() {
        Hashtable<String,Integer> range = new Hashtable<>();
        range.put(ContentRange.START, 5);
        range.put(ContentRange.END, 12);
        int[] result = ContentRange.getRange(range, 30);
        int[] expected = {5, 13};
        assertEquals(expected[0], result[0]);
        assertEquals(expected[1], result[1]);
    }

    @Test
    public void getRangeWithOnlyStart() {
        Hashtable<String,Integer> range = new Hashtable<>();
        range.put(ContentRange.START, 5);
        int[] result = ContentRange.getRange(range, 30);
        int[] expected = {5, 30};
        assertEquals(expected[0], result[0]);
        assertEquals(expected[1], result[1]);
    }

    @Test
    public void getRangeWithOnlyEnd() {
        Hashtable<String,Integer> range = new Hashtable<>();
        range.put(ContentRange.END, 8);
        int[] result = ContentRange.getRange(range, 30);
        int[] expected = {22, 30};
        assertEquals(expected[0], result[0]);
        assertEquals(expected[1], result[1]);
    }
}
