package net.nitrado.api.test;

import net.nitrado.api.Nitrapi;
import net.nitrado.api.test.mock.MockHttpClient;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

public class ApiTestCase {
    MockHttpClient client;
    Nitrapi api;

    @Before
    public void setup() {
        client = new MockHttpClient();
        api = new Nitrapi("test");
        api.setHttpClient(client);
    }


    @After
    public void tearDown() {
        client = null;
        api = null;
    }


    /**
     * Asserts that a given date contains the expected values.
     *
     * @param year
     * @param month  month, 1 based
     * @param day
     * @param hour
     * @param minute
     * @param second
     * @param date
     */
    public void assertDate(int year, int month, int day, int hour, int minute, int second, GregorianCalendar date) {
        assertEquals("year", year, date.get(Calendar.YEAR));
        assertEquals("month", month, date.get(Calendar.MONTH) + 1);
        assertEquals("day", day, date.get(Calendar.DAY_OF_MONTH));
        assertEquals("hour", hour, date.get(Calendar.HOUR_OF_DAY));
        assertEquals("minute", minute, date.get(Calendar.MINUTE));
        assertEquals("seconds", second, date.get(Calendar.SECOND));

    }
}
