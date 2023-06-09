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
import net.azarquiel.fmatic.model.Teams

class AdapterTeams (val context: Context, val layout: Int) : RecyclerView.Adapter<AdapterTeams.ViewHolder>() {

    private var dataList: List<Teams> = emptyList()

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

    internal fun setTeams(teams: List<Teams>) {
        this.dataList = teams
        notifyDataSetChanged()
    }


    class ViewHolder(viewlayout: View, val context: Context) : RecyclerView.ViewHolder(viewlayout) {
        fun bind(dataItem: Teams){
            itemView.findViewById<TextView>(R.id.tvTeamsName).apply { text = dataItem.teamName }
            when(dataItem.teamName.toLowerCase()){
                "red bull racing" -> itemView.findViewById<ImageView>(R.id.ivTeams).setImageResource(R.drawable.team_red_bull)
                "mercedes" -> itemView.findViewById<ImageView>(R.id.ivTeams).setImageResource(R.drawable.team_mercedes)
                "aston martin" -> itemView.findViewById<ImageView>(R.id.ivTeams).setImageResource(R.drawable.team_aston_martin)
                "alpine" -> itemView.findViewById<ImageView>(R.id.ivTeams).setImageResource(R.drawable.team_alpine)
                "ferrari" -> itemView.findViewById<ImageView>(R.id.ivTeams).setImageResource(R.drawable.team_ferrari)
                "mclaren" -> itemView.findViewById<ImageView>(R.id.ivTeams).setImageResource(R.drawable.team_mclaren)
                "haas f1 team" -> itemView.findViewById<ImageView>(R.id.ivTeams).setImageResource(R.drawable.team_haas)
                "alphatauri" -> itemView.findViewById<ImageView>(R.id.ivTeams).setImageResource(R.drawable.team_alphatauri)
                "alfa romeo" -> itemView.findViewById<ImageView>(R.id.ivTeams).setImageResource(R.drawable.team_alfa_romeo)
                "williams" -> itemView.findViewById<ImageView>(R.id.ivTeams).setImageResource(R.drawable.team_williams)
                else -> itemView.findViewById<ImageView>(R.id.ivTeams).setImageResource(R.drawable.ic_avatar)
            }
            itemView.tag = dataItem
        }

    }

}