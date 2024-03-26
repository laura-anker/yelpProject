package com.example.countrystuff

import android.util.Log
import com.example.countrystuff.YelpBusiness
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import java.util.concurrent.TimeUnit

class YelpManager {
    val okHttpClient: OkHttpClient
    //kotlin equivalent of a constructor
    init {
        val builder=OkHttpClient.Builder()
        //builder.connectTimeout(5,TimeUnit.SECONDS)
        val loggingInterceptor=HttpLoggingInterceptor()
        loggingInterceptor.level=HttpLoggingInterceptor.Level.BODY
        builder.addInterceptor(loggingInterceptor)

        okHttpClient=builder.build()

    }
    fun retrieveYelps(latitude: Double, longitude:Double,apiKey:String):List<YelpBusiness>{

        val request=Request.Builder()
            .url("https://api.yelp.com/v3/businesses/search?latitude=$latitude&longitude=$longitude")
            .header("Authorization", "Bearer $apiKey")
            .get()
            .build()

        val response: Response=okHttpClient.newCall(request).execute()
        val responseBody=response.body?.string()
        if (response.isSuccessful && !responseBody.isNullOrEmpty()){
            val yelps= mutableListOf<YelpBusiness>()
            val json = JSONObject(responseBody)
            val businesses = json.getJSONArray("businesses")
            for (i in 0 until businesses.length()){
                val currentBusiness = businesses.getJSONObject(i)
                val name = currentBusiness.getString("name")
                val rating = currentBusiness.getDouble("rating")
                val icon = currentBusiness.getString("image_url")
                val url = currentBusiness.getString("url")
                val categories= currentBusiness.getJSONArray("categories")
                val currentCategory = categories.getJSONObject(0)
                val title = currentCategory.getString("title")
                val yelp = YelpBusiness(
                    restaurantName = name,
                    category = title,
                    rating = rating,
                    icon = icon,
                    url = url
                )
                yelps.add(yelp)
            }

            return yelps
        }else {

            return listOf()
        }
    }
}