package net.azarquiel.fmatic.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
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
               itemView.findViewById<TextView>(R.id.tvRaceName).apply { text = "${dataItem.firstname} ${dataItem.lastname}" }

                when(dataItem.lastname.toLowerCase()){
                    "albon" -> itemView.findViewById<ImageView>(R.id.ivDrivers).setImageResource(R.drawable.dr_albon)
                    "alonso" -> itemView.findViewById<ImageView>(R.id.ivDrivers).setImageResource(R.drawable.dr_alonso)
                    "bottas" -> itemView.findViewById<ImageView>(R.id.ivDrivers).setImageResource(R.drawable.dr_bottas)
                    "de vries" -> itemView.findViewById<ImageView>(R.id.ivDrivers).setImageResource(R.drawable.dr_de_vries)
                    "gasly" -> itemView.findViewById<ImageView>(R.id.ivDrivers).setImageResource(R.drawable.dr_gasly)
                    "hamilton" -> itemView.findViewById<ImageView>(R.id.ivDrivers).setImageResource(R.drawable.dr_hamilton)
                    "hulkenberg" -> itemView.findViewById<ImageView>(R.id.ivDrivers).setImageResource(R.drawable.dr_hulkenberg)
                    "leclerc" -> itemView.findViewById<ImageView>(R.id.ivDrivers).setImageResource(R.drawable.dr_leclerc)
                    "magnussen" -> itemView.findViewById<ImageView>(R.id.ivDrivers).setImageResource(R.drawable.dr_magnussen)
                    "norris" ->itemView.findViewById<ImageView>(R.id.ivDrivers).setImageResource(R.drawable.dr_norris)
                    "ocon" ->itemView.findViewById<ImageView>(R.id.ivDrivers).setImageResource(R.drawable.dr_ocon)
                    "perez" -> itemView.findViewById<ImageView>(R.id.ivDrivers).setImageResource(R.drawable.dr_perez)
                    "piastri" -> itemView.findViewById<ImageView>(R.id.ivDrivers).setImageResource(R.drawable.dr_piastri)
                    "russell" -> itemView.findViewById<ImageView>(R.id.ivDrivers).setImageResource(R.drawable.dr_russell)
                    "sainz" -> itemView.findViewById<ImageView>(R.id.ivDrivers).setImageResource(R.drawable.dr_sainz)
                    "sargeant" -> itemView.findViewById<ImageView>(R.id.ivDrivers).setImageResource(R.drawable.dr_sargeant)
                    "stroll" -> itemView.findViewById<ImageView>(R.id.ivDrivers).setImageResource(R.drawable.dr_stroll)
                    "tsunoda" -> itemView.findViewById<ImageView>(R.id.ivDrivers).setImageResource(R.drawable.dr_tsunoda)
                    "verstappen" -> itemView.findViewById<ImageView>(R.id.ivDrivers).setImageResource(R.drawable.dr_verstappen)
                    "zhou" -> itemView.findViewById<ImageView>(R.id.ivDrivers).setImageResource(R.drawable.dr_zhou)
                    else -> itemView.findViewById<ImageView>(R.id.ivDrivers).setImageResource(R.drawable.ic_avatar)
                }

                itemView.tag = dataItem
            }

        }

}