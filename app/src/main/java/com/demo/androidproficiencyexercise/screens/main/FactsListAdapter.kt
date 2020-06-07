package com.demo.androidproficiencyexercise.screens.main

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.demo.androidproficiencyexercise.R
import com.demo.androidproficiencyexercise.database.entity.Facts
import com.demo.androidproficiencyexercise.databinding.FactListRowBinding

/**
 * Created by Ashish Nair on 07/06/20.
 */
class FactsListAdapter : RecyclerView.Adapter<FactsListAdapter.ViewHolder>() {
    private var factList: List<Facts> = ArrayList()

    class ViewHolder(private val binding: FactListRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(facts: Facts) {
            binding.factModel = facts

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding: FactListRowBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.fact_list_row,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return  factList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(factList[position])
    }

    fun updateFactsList(factList: List<Facts>) {
        this.factList = factList
        notifyDataSetChanged()
    }

}