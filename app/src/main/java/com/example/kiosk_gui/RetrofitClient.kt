package com.example.stageus_week3

import com.google.gson.Gson
import okhttp3.Call
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

object RetrofitClient {
    //무조건 리턴값을 가져야 하고, 무조건 그 리턴값이 객체여야함! 그니까 편함

    fun initRetrofit() : Retrofit{

        var url = "http://3.35.214.92:3000" // 벡엔드 서버 주소
        var gson = Gson()                   // 서버와 주고받을 데이터 형식
        var clientBuilder = OkHttpClient.Builder().build() // http 통신 규약을 사용하겠다!
        var connection = Retrofit.Builder()
            .baseUrl(url)
            .client(clientBuilder)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        return connection
    }

    //받아온 데이터를 저장할 객체
    data class logindata(val message : String , val success : Boolean)
    data class resisterdata(val message : String , val success : Boolean)

    // api 로 요청을 보내는 함수, api 마다 만들어야 함
    interface loginApi {
        @GET("/account/login")
        fun getLogin(@Query("id")id : String,@Query("pw")pw : String) : retrofit2.Call<logindata>
    }
    interface resisterApi {
        @POST("/account")
        fun connectRequest(@Body parameters: HashMap<String,Any>): retrofit2.Call<resisterdata>
    }

}