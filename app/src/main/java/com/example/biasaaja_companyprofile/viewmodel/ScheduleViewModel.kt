package com.example.biasaaja_companyprofile.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.biasaaja_companyprofile.model.Schedule
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ScheduleViewModel (application: Application) : AndroidViewModel(application) {
    val scheduleLD = MutableLiveData<ArrayList<Schedule>>()
    val LoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    val TAG = "volleyTag"
    private var queue: RequestQueue? = null


    fun refresh() {
        LoadErrorLD.value = false
        loadingLD.value = true

        queue = Volley.newRequestQueue(getApplication())
        val url = "https://www.jsonkeeper.com/b/3PKL"

        val stringRequest = StringRequest(Request.Method.GET, url, {
            loadingLD.value = false
            Log.d("showvolley", it)
            val type = object : TypeToken<List<Schedule>>() {}.type
            val result = Gson().fromJson<List<Schedule>>(it, type)
            scheduleLD.value = result as ArrayList<Schedule>?
            loadingLD.value = false

            Log.d("showvolley", result.toString())

        }, {
            Log.d("showvolley", it.toString())
            LoadErrorLD.value = true
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