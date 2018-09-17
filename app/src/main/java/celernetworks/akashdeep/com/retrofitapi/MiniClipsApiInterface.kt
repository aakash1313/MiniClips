package celernetworks.akashdeep.com.retrofitapi

import celernetworks.akashdeep.com.videointro.mainui.roomdatabase.entity.MiniClipEntity
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit
import retrofit2.http.Url
import okhttp3.ResponseBody



/**
 * This class is the api interface for the app. It is the class which contains the retrofit calls
 * and other implementation.
 * This is the class to be add further api calls.
 */
interface  MiniClipsApiInterface {

    @GET("/pictures")
    fun getPictures(): Call<List<MiniClipEntity>>

    @GET
    fun downloadFile(@Url fileUrl: String): Call<ResponseBody>

    companion object {
        fun create(): MiniClipsApiInterface {
            val okHttpClient: OkHttpClient = OkHttpClient.Builder().connectTimeout(60, TimeUnit.SECONDS).writeTimeout(60, TimeUnit.SECONDS).readTimeout(60, TimeUnit.SECONDS).addInterceptor(GenericInterceptor()).build()
            val retrofit = Retrofit.Builder().baseUrl("""http://private-04a55-videoplayer1.apiary-mock.com""").client(okHttpClient).addConverterFactory(GsonConverterFactory.create()).build()
            return retrofit.create(MiniClipsApiInterface::class.java)
        }
    }
}