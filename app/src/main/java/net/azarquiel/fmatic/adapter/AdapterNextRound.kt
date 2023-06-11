package net.azarquiel.fmatic.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import net.azarquiel.fmatic.R
import net.azarquiel.fmatic.interfaces.GlobalInterface
import net.azarquiel.fmatic.model.NextRoundEvents

class AdapterNextRound (val context: Context, val layout: Int) : RecyclerView.Adapter<AdapterNextRound.ViewHolder>() {

    private var dataList: List<NextRoundEvents> = emptyList()

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

    internal fun setNextRound(nextRoundEvents: List<NextRoundEvents>) {
        this.dataList = nextRoundEvents
        notifyDataSetChanged()
    }


    class ViewHolder(viewlayout: View, val context: Context) : RecyclerView.ViewHolder(viewlayout),
        GlobalInterface {
        fun bind(dataItem: NextRoundEvents){
            itemView.findViewById<TextView>(R.id.tvNextRoundName).apply { text = dataItem.summary }
            itemView.findViewById<TextView>(R.id.tvNextRoundLocation).apply { text = dataItem.location }
            itemView.findViewById<TextView>(R.id.tvNextRoundTime).apply { text = deleteCharInString(dataItem.startDate,'T') }

            itemView.tag = dataItem
        }

    }

}