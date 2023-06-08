package net.azarquiel.fmatic.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import net.azarquiel.fmatic.R
import net.azarquiel.fmatic.model.Drivers

class AdapterDrivers (val context: Context, val layout: Int) : RecyclerView.Adapter<AdapterDrivers.ViewHolder>() {

        private var dataList: List<Drivers> = emptyList()

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

        internal fun setDrivers(drivers: List<Drivers>) {
            this.dataList = drivers
            notifyDataSetChanged()
        }


        class ViewHolder(viewlayout: View, val context: Context) : RecyclerView.ViewHolder(viewlayout) {
            fun bind(dataItem: Drivers){
                itemView.findViewById<TextView>(R.id.tvDriverName).apply { text = dataItem.firstname }

                itemView.tag = dataItem
            }

        }

}