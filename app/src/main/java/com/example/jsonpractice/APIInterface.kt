package com.example.jsonpractice
import retrofit2.Call
import retrofit2.http.GET
interface APIInterface {
    @GET("/contacts/")
    fun getUser():Call<Array<DataItem>>}