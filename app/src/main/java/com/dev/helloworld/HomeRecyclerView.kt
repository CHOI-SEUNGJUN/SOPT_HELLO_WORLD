package com.dev.helloworld

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class HomeRecyclerViewAdapter : RecyclerView.Adapter<HomeRecyclerViewAdapter.HomeRecyclerViewHolder>() {

    private val dummy = listOf(
        SomethingData(R.color.colorPrimary, "테스트1", R.color.colorPrimaryDark),
        SomethingData(R.color.design_default_color_error, "테스트2", R.color.design_default_color_on_secondary),
        SomethingData(R.color.colorPrimary, "테스트3", R.color.colorPrimary),
        SomethingData(R.color.colorPrimary, "테스트4", R.color.colorPrimaryDark),
        SomethingData(R.color.colorPrimary, "테스트5", R.color.colorPrimaryDark)
    )

    inner class HomeRecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val profile = view.findViewById<ImageView>(R.id.ivProfile)
        private val name = view.findViewById<TextView>(R.id.txtName)
        private val content = view.findViewById<ImageView>(R.id.ivContent)

        fun bind(data: SomethingData) {
            profile.setBackgroundColor(data.profile)
            name.text = data.name
            content.setBackgroundColor(data.content)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeRecyclerViewHolder {
        return HomeRecyclerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_something, parent, false))
    }

    override fun getItemCount(): Int = dummy.size

    override fun onBindViewHolder(holder: HomeRecyclerViewHolder, position: Int) {
        holder.bind(dummy[position])
    }

}

data class SomethingData(
    val profile: Int,
    val name: String,
    val content: Int
)