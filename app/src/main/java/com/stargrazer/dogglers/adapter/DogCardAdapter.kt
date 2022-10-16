/*
* Copyright (C) 2021 The Android Open Source Project.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package com.stargrazer.dogglers.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.stargrazer.dogglers.R
import com.stargrazer.dogglers.data.DataSource

/**
 * Adapter to inflate the appropriate list item layout and populate the view with information
 * from the appropriate data source
 */
class DogCardAdapter(
    private val context: Context?,
    private val layout: Int
): RecyclerView.Adapter<DogCardAdapter.DogCardViewHolder>() {

    val dogList = DataSource.dogs

    /**
     * Initialize view elements
     */
    class DogCardViewHolder(view: View?): RecyclerView.ViewHolder(view!!) {
        val photoView: ImageView = view!!.findViewById(R.id.photo)
        val nameView: TextView = view!!.findViewById(R.id.name)
        val ageView: TextView = view!!.findViewById(R.id.age)
        val hobbiesView: TextView = view!!.findViewById(R.id.hobbies)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogCardViewHolder {
        val adapterLayout = if(layout==3) {
            LayoutInflater.from(parent.context).inflate(R.layout.grid_list_item,parent, false)
        } else {
            LayoutInflater.from(parent.context).inflate(R.layout.vertical_horizontal_list_item,parent, false)
        }
        return DogCardViewHolder(adapterLayout)
    }

    override fun getItemCount() = dogList.size

    override fun onBindViewHolder(holder: DogCardViewHolder, position: Int) {
        val dog = dogList[position]
        holder.photoView.setImageResource(dog.imageResourceId)
        holder.nameView.text=dog.name
        val resources = context?.resources
        holder.ageView.text=resources?.getString(R.string.dog_age,dog.age)
        holder.hobbiesView.text=resources?.getString(R.string.dog_hobbies,dog.hobbies)
    }
}
