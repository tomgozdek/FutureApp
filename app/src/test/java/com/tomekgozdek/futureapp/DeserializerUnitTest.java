package com.tomekgozdek.futureapp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tomekgozdek.futureapp.model.FutureItem;
import com.tomekgozdek.futureapp.model.adapter.FutureDateDeserializer;
import com.tomekgozdek.futureapp.model.adapter.FutureDeserializer;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class DeserializerUnitTest {

    private final String FutureItemJson = " {\n" +
            "      \"title\": \"stereocomparator\",\n" +
            "      \"description\": \"Biltong pork loin hamburger alcatra picanha prosciutto.    https:\\/\\/cringemdb.com\\/\",\n" +
            "      \"orderId\": 0,\n" +
            "      \"modificationDate\": \"2013-10-13\",\n" +
            "      \"image_url\": \"http:\\/\\/lorempixel.com\\/120\\/120\\/abstract\\/\"\n" +
            "    }";
    private static Gson gson;

    @BeforeClass
    public static void setup(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(FutureItem.class, new FutureDeserializer());
        gson = gsonBuilder.create();
    }

    @Test
    public void dateIsDeserializedProperly(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2013);
        calendar.set(Calendar.MONTH, 9);
        calendar.set(Calendar.DAY_OF_MONTH, 13);
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);

        FutureItem serializedItem = gson.fromJson(FutureItemJson, FutureItem.class);

        Calendar actualcalendar = Calendar.getInstance();
        actualcalendar.setTime(serializedItem.getModificationDate());

        Assert.assertTrue("Dates should point the same day",
                calendar.get(Calendar.YEAR) == actualcalendar.get(Calendar.YEAR)
                && calendar.get(Calendar.DAY_OF_YEAR) == actualcalendar.get(Calendar.DAY_OF_YEAR));

    }

    @Test
    public void itemUrlIsDeserializedProperly(){
        String expectedUrl = "https://cringemdb.com/";

        FutureItem serializedItem = gson.fromJson(FutureItemJson, FutureItem.class);
        Assert.assertEquals("Item urls are the same", expectedUrl, serializedItem.getUrl());
    }

    @Test
    public void itemDescriptionIsSerializedProperly() {
        String expectedDesc = "Biltong pork loin hamburger alcatra picanha prosciutto.";

        FutureItem serializedItem = gson.fromJson(FutureItemJson, FutureItem.class);
        Assert.assertEquals("Item urls are the same", expectedDesc, serializedItem.getDescription());
    }
}