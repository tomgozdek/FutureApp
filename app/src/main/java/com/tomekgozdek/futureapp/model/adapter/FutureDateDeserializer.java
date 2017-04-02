package com.tomekgozdek.futureapp.model.adapter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Adapter type class for deserializing
 * {@link com.tomekgozdek.futureapp.model.FutureItem#modificationDate} field
 */

public class FutureDateDeserializer implements JsonDeserializer<Date>{

    private final String mDateFormat;

    public FutureDateDeserializer(String dateFormat){
        mDateFormat = dateFormat;
    }

    @Override
    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {

        return parseDate(json.getAsString());
    }

    private Date parseDate(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat(mDateFormat);
        try {
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
