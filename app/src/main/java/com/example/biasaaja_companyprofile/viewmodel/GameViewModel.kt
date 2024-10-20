package com.example.biasaaja_companyprofile.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.example.biasaaja_companyprofile.model.Game

class GameViewModel (application: Application) : AndroidViewModel(application) {
    val gamesLD = MutableLiveData<ArrayList<Game>>()
    val gamesLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    val TAG = "volleyTag"
    private var queue: RequestQueue? = null


    fun refresh() {
        gamesLoadErrorLD.value = false
        loadingLD.value = true

        queue = Volley.newRequestQueue(getApplication())
        val url = "https://www.jsonkeeper.com/b/UF73"

        val stringRequest = StringRequest(Request.Method.GET, url, {
            loadingLD.value = false
            Log.d("showvolley", it)
            val type = object : TypeToken<List<Game>>() {}.type
            val result = Gson().fromJson<List<Game>>(it, type)
            gamesLD.value = result as ArrayList<Game>?
            loadingLD.value = false

            Log.d("showvolley", result.toString())

        }, {
            Log.d("showvolley", it.toString())
            gamesLoadErrorLD.value = true
            loadingLD.value = false
        })

        stringRequest.tag = TAG
        queue?.add(stringRequest)


    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }



}