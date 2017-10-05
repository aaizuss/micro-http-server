package com.aaizuss.http;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RangeParserTest {

    @Test
    public void testGetRangeValuesForSingleRange() {
        String header = "bytes=100-150";
        String[] expected = {"100", "150"};
        String[] result = RangeParser.getRangeValues(header);

        assertEquals(expected[0], result[0]);
        assertEquals(expected[1], result[1]);
    }

    @Test
    public void testGetRangeValuesWithNoEndRange() {
        String header = "bytes=100-";
        String[] expected = {"100", ""};
        String[] result = RangeParser.getRangeValues(header);

        assertEquals(expected[0], result[0]);
        assertEquals(expected[1], result[1]);
    }

    @Test
    public void testGetRangeValuesWithNoStartRange() {
        String header = "bytes=-150";
        String[] expected = {"", "150"};
        String[] result = RangeParser.getRangeValues(header);

        assertEquals(expected[0], result[0]);
        assertEquals(expected[1], result[1]);
    }

    @Test
    public void testGetRangeValuesForRangeWithTotal() {
        String header = "bytes=100-150/250";
        String[] expected = {"100", "150"};
        String[] result = RangeParser.getRangeValues(header);

        assertEquals(expected[0], result[0]);
        assertEquals(expected[1], result[1]);
    }
}
