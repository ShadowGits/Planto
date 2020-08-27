package com.example.edp_plantsystem.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.edp_plantsystem.models.Plant
import com.example.edp_plantsystem.repositories.PlantDetailsRepository
import com.example.edp_plantsystem.repositories.PlantDetailsRepository.Companion.instance

internal class PlantDataActivityViewModel : ViewModel() {
    private var plantDetails: MutableLiveData<Plant?>? = null
    private var plantRepo: PlantDetailsRepository? = null
    fun queryPlantRepo(plantName: String?) {
        if (plantDetails != null) {
            return
        }
        //else instantiate repository
        plantRepo = instance
        plantDetails = plantRepo!!.getPlant(plantName!!)
    }

    fun getPlantDetails(): LiveData<Plant?>? {
        return plantDetails
    }
}