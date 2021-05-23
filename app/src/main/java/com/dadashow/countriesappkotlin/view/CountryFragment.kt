package com.dadashow.countriesappkotlin.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dadashow.countriesappkotlin.R
import com.dadashow.countriesappkotlin.databinding.FragmentCountryBinding
import com.dadashow.countriesappkotlin.util.downloadImageFromUrl
import com.dadashow.countriesappkotlin.util.placeholderProgressBar
import com.dadashow.countriesappkotlin.viewmodel.CountryViewModel
import kotlinx.android.synthetic.main.fragment_country.*

class CountryFragment : Fragment() {
    private lateinit var viewModel : CountryViewModel
    private lateinit var databinding : FragmentCountryBinding
    var countryUuid =0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        databinding=DataBindingUtil.inflate(inflater,R.layout.fragment_country,container,false)
        return databinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
         super.onViewCreated(view, savedInstanceState)
        arguments?.let{
            countryUuid=CountryFragmentArgs.fromBundle(it).countryUuid
        }
        viewModel=ViewModelProvider(this).get(CountryViewModel::class.java)
        viewModel.getDataFromRoom(countryUuid)

        observeLiveData()
    }
    fun observeLiveData(){
        viewModel.country.observe(viewLifecycleOwner, Observer {country->
            country?.let {
                    databinding.country=country
//                countryName.text=it.countryName
//                countryCapital.text=it.countryCapital
//                countryCurrency.text=it.countryCurrency
//                countryLanguage.text=it.countryLanguage
//                countryRegion.text=it.countryRegion
//                context?.let {
//                    countryImage.downloadImageFromUrl(country.imageUrl!!, placeholderProgressBar(it))
//                }
            }
        })
    }


}