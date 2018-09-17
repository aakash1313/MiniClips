package celernetworks.akashdeep.com.retrofitapi

import celernetworks.akashdeep.com.utils.isNetworkAvailable
import okhttp3.Interceptor
import okhttp3.Response
import java.net.SocketException

/**
 * Intereceptor as a part of the retorfit call to perform some basic checks before doing further
 * actions. This saves the app from some common crashes in future.
 */
class GenericInterceptor : Interceptor {

    constructor(){
    }
    override fun intercept(chain: Interceptor.Chain): Response {

        var request = chain.request()

        if(!isNetworkAvailable())
        {
            throw GenericApiException(SocketException())
        }
        val response = chain.proceed(request)

        // todo deal with the issues the way you need to
        if (response.code() == 500) {
            throw GenericApiException(InternalServerError())
        }
        return response
    }
}