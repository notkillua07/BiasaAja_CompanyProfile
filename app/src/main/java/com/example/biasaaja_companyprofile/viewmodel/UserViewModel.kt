package com.example.app.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.biasaaja_companyprofile.model.User
import com.example.biasaaja_companyprofile.util.SessionManager
import com.example.studentproject.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class UserViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {
    private val job = Job() // Coroutine job for background tasks
    val loginStatus = MutableLiveData<Boolean>()
    val registerStatus = MutableLiveData<String>()
    private val sessionManager = SessionManager(application.applicationContext)

    fun login(username: String, password: String) {
        launch {
            val db = buildDb(getApplication())
            val user = db.userDao().selectUser(username)
            if (user != null && user.password == password) {
                // Login successful, save session
                sessionManager.saveLoginState(username, user.firstname, user.lastname)
                loginStatus.postValue(true)
            } else {
                loginStatus.postValue(false) // Login failed
            }
        }
    }

    // Register logic
    fun register(firstname: String, lastname: String, username: String, password: String, confirmPassword: String) {
        if (password != confirmPassword) {
            registerStatus.postValue("Passwords do not match!")
            return
        }

        launch {
            val db = buildDb(getApplication())
            val existingUser = db.userDao().selectUser(username)
            if (existingUser != null) {
                registerStatus.postValue("Username already exists!")
            } else {
                val newUser = User(
                    firstname = firstname,
                    lastname = lastname,
                    username = username,
                    password = password
                )
                db.userDao().insertAll(newUser)
                registerStatus.postValue("Registration successful!")
            }
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO // Execute coroutines in IO dispatcher
}
