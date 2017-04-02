package com.tomekgozdek.futureapp.api;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Rerofit java interface for making http calls to API.
 */

public interface FutureService {

    @GET("~dpaluch/test35/")
    Call<ApiResponse> listFutureItems();

}
