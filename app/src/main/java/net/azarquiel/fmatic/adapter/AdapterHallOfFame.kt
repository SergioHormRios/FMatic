package net.azarquiel.fmatic.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import net.azarquiel.fmatic.R
import net.azarquiel.fmatic.model.HallOfFames

class AdapterHallOfFame(val context: Context, val layout: Int) : RecyclerView.Adapter<AdapterHallOfFame.ViewHolder>() {

    private var dataList: List<HallOfFames> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewlayout = layoutInflater.inflate(layout, parent, false)
        return ViewHolder(viewlayout, context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    internal fun setHallOfFame(hallOfFame: List<HallOfFames>) {
        this.dataList = hallOfFame
        notifyDataSetChanged()
    }


    class ViewHolder(viewlayout: View, val context: Context) : RecyclerView.ViewHolder(viewlayout) {
        fun bind(dataItem: HallOfFames){
            itemView.findViewById<TextView>(R.id.tvHallOfFame).apply { text = dataItem.driver }
            itemView.tag = dataItem
        }

    }

}