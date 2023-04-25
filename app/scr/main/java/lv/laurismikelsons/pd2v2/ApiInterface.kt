package lv.laurismikelsons.pd2v2

import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET("/random_ten")
    suspend fun getItemData() : Response<List<Item>>
}