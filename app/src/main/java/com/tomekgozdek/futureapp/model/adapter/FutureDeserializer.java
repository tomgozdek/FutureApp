package com.tomekgozdek.futureapp.model.adapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.tomekgozdek.futureapp.model.FutureItem;

import java.lang.reflect.Type;
import java.util.Date;

/**
 * Created by TomekG on 2017-04-02.
 */

public class FutureDeserializer implements JsonDeserializer<FutureItem>{

    private Gson gson;
    public FutureDeserializer(){
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Date.class, new FutureDateDeserializer("yyyy-MM-dd"));
        gson = builder.create();
    }
    @Override
    public FutureItem deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        FutureItem futureItem = gson.fromJson(json, FutureItem.class);

        JsonObject jsonObject = json.getAsJsonObject();
        if(jsonObject.get("description") != null){
            String[] temp = new String[2];
            parseDescription(temp, jsonObject.get("description").getAsString());
            futureItem.setDescription(temp[0]);
            futureItem.setUrl(temp[1]);
        }

        return futureItem;
    }

    /**
     * Splits description and contained url into 2 string array, where 0 index points to
     * description and 1 index to url.
     */
    private void parseDescription(String[] result, String temp) {
        //TODO change this trivial logic to some regex
        int urlStart = temp.lastIndexOf("http");
        String description = temp;
        String url = "";
        if(urlStart != -1){
            description = temp.substring(0, urlStart).trim();
            //assumed that url is on the end of the description
            url = temp.substring(urlStart);
        }

        result[0] = description;
        result[1] = url;
    }
}
