package com.tomekgozdek.futureapp.api;

import com.tomekgozdek.futureapp.model.FutureItem;

import java.util.List;

/**
 * Created by TomekG on 2017-04-02.
 */

public class ApiResponse {
    List<FutureItem> data;

    public List<FutureItem> getData() {
        return data;
    }
}
