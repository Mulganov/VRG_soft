package com.mulganov.test_task.vrgsoft.model.rest;

import retrofit2.Call;
import retrofit2.http.GET;

public interface API {
    interface JSONPlaceHolderApi {
        @GET("top.json")
        Call<Top> getTop();
    }
}
