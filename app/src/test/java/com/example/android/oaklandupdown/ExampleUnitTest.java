package com.example.android.oaklandupdown;

import org.junit.Test;

import java.lang.Exception;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    public void location_name_isCorrect() {
        Place place = new Place("Cosecha", "Restaurant", "$$");

        String expected = "Cosecha";
        String actual = place.getName();

        assertEquals(expected, actual);

    }

}