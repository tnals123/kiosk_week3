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

        var url = "http://52.79.157.214:3000" // 벡엔드 서버 주소
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
    data class logincheckdata(val message : String , val success : Boolean)
    data class categorydata(val message : String , val success : Boolean , val data : List<menu_list2>)
    data class menu_list(var menu_name : String , var menu_price : Int , var menu_image : String)
    data class menu_list2(var category_name: String)
    data class history_list(var name : String, var count : Int, var sum_price : Int)
    data class menudata(val message : String , val success : Boolean , val data : List<menu_list>)
    data class orderdata(val message : String , val success : Boolean)
    data class historydata(val message : String , val success : Boolean, val data : List<history_list>, var total_price : Int)

//    "message": String
//    "success": Boolean
//    "data": List [
//    {
//        "category_name": String
//    }
//    ]

    // api 로 요청을 보내는 함수, api 마다 만들어야 함
    interface loginApi {
        @GET("/account/login")
        fun getLogin(@Query("id")id : String,@Query("pw")pw : String) : retrofit2.Call<logindata>
    }
    interface loginCheck {
        @GET("/account/overlap")
        fun getLoginCheck(@Query("id")id : String) : retrofit2.Call<logincheckdata>
    }
    interface resisterApi {
        @POST("/account")
        fun connectRequest(@Body parameters: HashMap<String,Any>): retrofit2.Call<resisterdata>
    }
    interface orderApi {
        @POST("/account")
        fun putOrder(@Body id : String, @Body parameters: HashMap<String,Any>, @Body total_price: Int): retrofit2.Call<orderdata>
    }
    interface categoryApi {
        @GET("/category")
        fun getCategory(@Query("lang")lang : String) : retrofit2.Call<categorydata>
    }
    interface menuApi {
        @GET("/category/menu")
        fun getMenu(@Query("category_name")category_name : String,@Query("lang")lang : String) : retrofit2.Call<menudata>
    }
    interface historyApi {
        @GET("/order")
        fun getHistory(@Query("id")id : String) : retrofit2.Call<historydata>
    }

}