package com.example.testproj.network

import com.example.testproj.model.CharactersDataItem
import com.example.testproj.network.RequestUrl.CHARACTERS_API
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface RetrofitServiceAnnotator {

    @GET(CHARACTERS_API)
    suspend fun getCharactersData():Array<CharactersDataItem>

    @GET
    suspend fun getImage(@Url imageUrl:String):Response<ResponseBody>

}