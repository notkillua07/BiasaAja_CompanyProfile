package com.example.biasaaja_companyprofile.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.biasaaja_companyprofile.model.Apply
import com.example.biasaaja_companyprofile.model.CompanyProfileDatabase
import com.example.biasaaja_companyprofile.model.Team
import com.example.biasaaja_companyprofile.model.User
import com.example.studentproject.util.buildCompanyProfileDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ApplyTeamViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {

    val applyLD = MutableLiveData<List<Apply>>()
    val applyLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    val selectedGameIdLD = MutableLiveData<Int>()
    val teamLD = MutableLiveData<Team>()

    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    init {
        refresh()
    }

    // Refresh the data
    fun refresh() {
        loadingLD.value = true
        applyLoadErrorLD.value = false
        launch {
            val db = CompanyProfileDatabase.buildDatabase(getApplication())
            try {
                applyLD.postValue(db.applyDao().selectAllApply())
                loadingLD.postValue(false)
            } catch (e: Exception) {
                applyLoadErrorLD.postValue(true)
                loadingLD.postValue(false)
            }
        }
    }

    fun addApply(list: List<Apply>) {
        launch {
            val db = buildCompanyProfileDb(getApplication())
            db.applyDao().insertAll(*list.toTypedArray())
        }
    }
}
