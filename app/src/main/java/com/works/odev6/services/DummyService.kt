package com.works.odev6.services

import com.works.odev6.models.DummyProducts
import com.works.odev6.models.JWTData
import com.works.odev6.models.JWTUser
import com.works.odev6.models.Product
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface DummyService {
    @GET("products")
    fun Products(@Query("limit") limit: Int): Call<DummyProducts>

    @GET("products/search")
    fun Filter(@Query("q") keyword: String): Call<DummyProducts>

    @POST("auth/login")
    fun login(@Body jwtUser: JWTUser): Call<JWTData>


}