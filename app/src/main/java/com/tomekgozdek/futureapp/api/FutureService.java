package com.tomekgozdek.futureapp.api;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Rerofit java interface for making http calls to API.
 */

public interface FutureService {

    /**
     * Retrieves {@link ApiResponse} object containing list of
     * {@link com.tomekgozdek.futureapp.model.FutureItem} items.
     *
     * @return {@link ApiResponse} object.
     */
    @GET("~dpaluch/test35/")
    Call<ApiResponse> listFutureItems();

}
