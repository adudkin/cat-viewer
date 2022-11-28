package com.example.catviewer

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.catviewer.insertion.CatResponse
import com.example.catviewer.insertion.CatService
import com.example.catviewer.room.CatData
import com.example.catviewer.room.DbConnection
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {
    private val catEntityDao =
        Room.databaseBuilder(application, DbConnection::class.java, "database")
            .build()
            .entityDao()

    val list = catEntityDao.getAll()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.thecatapi.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun insert() {
        foundCatUrl.value?.let {
            viewModelScope.launch {
                val cat = CatData(id = 0, it)
                catEntityDao.insert(cat)
            }
        }
        refresh()
    }

    val foundCatUrl = MutableLiveData<String>()

    fun refresh() {
        retrofit.create(CatService::class.java).getAll()
            .enqueue(object : Callback<List<CatResponse>> {
                override fun onResponse(
                    call: Call<List<CatResponse>>,
                    response: Response<List<CatResponse>>
                ) {
                    response.body()?.first()?.let { foundCatUrl.value = it.url }
                }

                override fun onFailure(call: Call<List<CatResponse>>, t: Throwable) {

                }

            })
    }
}