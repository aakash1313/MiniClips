package celernetworks.akashdeep.com.retrofitapi

import celernetworks.akashdeep.com.MiniClipsApplication
import celernetworks.akashdeep.com.videointro.R
import java.net.SocketException

/**
 * This class is added as a part of the Generic interceptor which currently supports user defined if
 * there is no network or error 505. It is scalable to support other exceptions in future releases
 */
class GenericApiException : Exception{

    override lateinit var message:String

    constructor(e:Exception){


        if(e is SocketException){
            this.message = MiniClipsApplication.appInstance.getString(R.string.retrofit_network_error)
        }
        else if(e is InternalServerError)
            //We can support other exceptions here
        {
            this.message = MiniClipsApplication.appInstance.getString(R.string.retrofit_internal_server_error)
        }
    }
}

