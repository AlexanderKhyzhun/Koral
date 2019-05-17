package com.yotalabs.koral.data

import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.GET

/**
 * @author SashaKhyzhun
 * Created on 4/26/19.
 */
interface Api {

    @GET("/test")
    fun test(): Observable<ResponseBody>

    @GET("/test")
    fun signUp(): Observable<ResponseBody>

}