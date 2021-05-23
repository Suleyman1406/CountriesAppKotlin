package com.dadashow.countriesappkotlin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.dadashow.countriesappkotlin.R
import com.dadashow.countriesappkotlin.databinding.ItemCountryBinding
import com.dadashow.countriesappkotlin.model.Country
import com.dadashow.countriesappkotlin.util.downloadImageFromUrl
import com.dadashow.countriesappkotlin.util.placeholderProgressBar
import com.dadashow.countriesappkotlin.view.FeedFragmentDirections
import kotlinx.android.synthetic.main.item_country.view.*

class CountryAdapter(var countryList:ArrayList<Country>):
    RecyclerView.Adapter<CountryAdapter.CountryViewHolder>(),CountryClickListener {

    class CountryViewHolder(var view:ItemCountryBinding):RecyclerView.ViewHolder(view.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        var inflater=LayoutInflater.from(parent.context)
        //var view=inflater.inflate(R.layout.item_country,parent,false);
        val view=DataBindingUtil.inflate<ItemCountryBinding>(inflater,R.layout.item_country,parent,false)
        return CountryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.view.country=countryList[position]
        holder.view.listener=this

//        holder.view.name.text= countryList[position].countryName
//        holder.view.region.text= countryList[position].countryRegion
//        holder.view.setOnClickListener {
//            var action=FeedFragmentDirections.actionFeedFragmentToCountryFragment(countryList[position].uuid)
//            Navigation.findNavController(it).navigate(action)
//        }
//        holder.view.imageView.downloadImageFromUrl(countryList[position].imageUrl!!,
//            placeholderProgressBar(holder.view.context))
    }

    override fun getItemCount(): Int {
    return countryList.size
    }
    fun uptadeList(newcountryList:ArrayList<Country>){
        countryList.clear()
        countryList.addAll(newcountryList)
        notifyDataSetChanged()
    }

    override fun onCountryClicked(view: View) {
        var action=FeedFragmentDirections.actionFeedFragmentToCountryFragment(view.countryUuidTextView.text.toString().toInt())
            Navigation.findNavController(view).navigate(action)
    }
}