package com.dadashow.countriesappkotlin.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dadashow.countriesappkotlin.model.Country
import com.dadashow.countriesappkotlin.service.CountryDatabase
import kotlinx.coroutines.launch

class CountryViewModel(application: Application) : BaseViewModel(application) {
      var country=MutableLiveData<Country>()

    fun getDataFromRoom(uuid:Int){
       launch {
           val dao=CountryDatabase(getApplication()).countryDao()
           country.value=dao.getCountry(uuid)
       }
    }
}