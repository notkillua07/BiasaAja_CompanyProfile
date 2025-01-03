package com.example.biasaaja_companyprofile.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.example.biasaaja_companyprofile.model.Game
import com.example.biasaaja_companyprofile.model.User
import com.example.studentproject.util.buildCompanyProfileDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.math.log
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class GameViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {
    private val job = Job() // Coroutine job for background tasks
    val gamesLD = MutableLiveData<ArrayList<Game>>()
    val allGamesLD = MutableLiveData<ArrayList<String>>()  // List of game names
    val selectedGameIdLD = MutableLiveData<Int?>()  // Holds the selected game ID
    val gamesLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    private var queue: RequestQueue? = null

    val TAG = "volleyTag"

    init {
        refresh()
    }

    // Function to fetch game data from the URL
    fun refresh() {
        gamesLoadErrorLD.value = false
        loadingLD.value = true
        launch{
            val db = buildCompanyProfileDb(getApplication())
            gamesLD.value = ArrayList(db.gameDao().selectAllGame())
            loadingLD.value = false
            Log.e("GameViewModel","Game Selected!")
        }

//        queue = Volley.newRequestQueue(getApplication())
//        val url = "https://www.jsonkeeper.com/b/UF73"
//
//        val stringRequest = StringRequest(Request.Method.GET, url, {
//            val type = object : TypeToken<List<Game>>() {}.type
//            val result = Gson().fromJson<List<Game>>(it, type)
//
//            val gameNames = result.map { it.name ?: "Unknown" }
//            allGamesLD.value = ArrayList(gameNames)
//
//            gamesLD.value = ArrayList(result)
//            loadingLD.value = false
//
//            Log.d("showvolley", result.toString())
//
//        }, {
//            gamesLoadErrorLD.value = true
//            loadingLD.value = false
//        })
//
//        stringRequest.tag = TAG
//        queue?.add(stringRequest)
    }

    // Function to set the selected game ID and trigger necessary actions
    fun setSelectedGameId(gameId: Int) {
        selectedGameIdLD.value = gameId
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }

    fun addGame(game: Game){
        launch{
            val db = buildCompanyProfileDb(getApplication())
            db.gameDao().insertAll(game)
            Log.e("GameViewModel","Game Inserted!")
        }
    }

    // Function to get the game name from the team ID
    fun getGameNameByGameId(gameId: Int): String {
        var game: Game? = null
        launch{
            val db = buildCompanyProfileDb(getApplication())
            game = db.gameDao().selectGameId(gameId.toString())
        }
        return game?.name ?: "Unknown Game"
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO // Execute coroutines in IO dispatcher

}
