package net.azarquiel.fmatic.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import net.azarquiel.fmatic.R
import net.azarquiel.fmatic.interfaces.GlobalInterface
import net.azarquiel.fmatic.model.RaceCalendarEvents

class AdapterCalendar (val context: Context, val layout: Int) : RecyclerView.Adapter<AdapterCalendar.ViewHolder>() {

    private var dataList: List<RaceCalendarEvents> = emptyList()

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

    internal fun setRaceCalendar(raceCalendarEvents: List<RaceCalendarEvents>) {
        this.dataList = raceCalendarEvents
        notifyDataSetChanged()
    }


    class ViewHolder(viewlayout: View, val context: Context) : RecyclerView.ViewHolder(viewlayout), GlobalInterface {
        fun bind(dataItem: RaceCalendarEvents){
            itemView.findViewById<TextView>(R.id.tvRaceName).apply { text = dataItem.summary }
            itemView.findViewById<TextView>(R.id.tvRaceLocation).apply { text = dataItem.location }
            itemView.findViewById<TextView>(R.id.tvRaceTime).apply { text = deleteCharInString(dataItem.startDate,'T') }

            itemView.tag = dataItem
        }

    }

}