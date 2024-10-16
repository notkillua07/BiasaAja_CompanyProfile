package com.example.biasaaja_companyprofile.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.biasaaja_companyprofile.model.Team

class TeamViewModel: ViewModel() {
    var teamLD = MutableLiveData<Team>()

    fun fetch(team: Team){
//        val student = Student("16055","Nonie","1998/03/28","5718444778","http://dummyimage.com/75x100"
//                + ".jpg/cc0000/ffffff")
//        studentLD.value = student
    }
}