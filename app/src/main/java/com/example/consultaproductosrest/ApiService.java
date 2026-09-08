package com.example.consultaproductosrest;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("products")
    Call<ProductoResponse> getProductos();

}