package com.dadashow.countriesappkotlin.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dadashow.countriesappkotlin.R
import com.dadashow.countriesappkotlin.adapter.CountryAdapter
import com.dadashow.countriesappkotlin.viewmodel.FeedViewModel
import kotlinx.android.synthetic.main.fragment_feed.*

class FeedFragment : Fragment() {
    private  var adapter=CountryAdapter(arrayListOf())
    private lateinit var viewModel:FeedViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            viewModel=ViewModelProvider(this).get(FeedViewModel::class.java)
            viewModel.refreshData()

            countryList.layoutManager=LinearLayoutManager(context)
            countryList.adapter=adapter
            observeLiveData()
        swipeRefreshLayout.setOnRefreshListener {
            countryList.visibility=View.INVISIBLE
            countryError.visibility=View.INVISIBLE
            countryLoading.visibility=View.VISIBLE
            viewModel.refreshDataFromAPI()
            swipeRefreshLayout.isRefreshing=false
        }

    }
    fun observeLiveData(){
        viewModel.countries.observe(viewLifecycleOwner, Observer { countries->
            countries?.let {
                countryList.visibility=View.VISIBLE
                adapter.uptadeList(ArrayList(countries))
            }
        })
        viewModel.countryErrr.observe(viewLifecycleOwner, Observer { countryError1->
            countryError1?.let {
                if (it){
                    countryError.visibility=View.VISIBLE
                    countryList.visibility=View.INVISIBLE
                }else{
                    countryError.visibility=View.INVISIBLE
                }
            }
        })
        viewModel.countryLoading.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it){
                    countryLoading.visibility=View.VISIBLE
                    countryList.visibility=View.INVISIBLE
                }else{
                    countryLoading.visibility=View.INVISIBLE

                }
            }
        })
    }
}