package net.azarquiel.fmatic.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import net.azarquiel.fmatic.R
import net.azarquiel.fmatic.interfaces.GlobalInterface
import net.azarquiel.fmatic.model.TeamDetail
import net.azarquiel.fmatic.viewModel.MainViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TeamDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TeamDetailFragment : Fragment(), GlobalInterface {
    private var teamName: String? = null
    private val viewModel: MainViewModel = MainViewModel()

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TeamDetailFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TeamDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
            teamName = it.getString("team")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_team_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onLaunchView()
    }

    private fun onLaunchView() {
        getTeamDetail(teamName!!)
    }

    private fun getTeamDetail(teamName: String) =
        viewModel.getTeamDetails(teamName).observe(viewLifecycleOwner){
            it -> it.let {
                requireActivity().apply {
                    findViewById<TextView>(R.id.tvTeamChief).apply {
                        text = "El Jefe de equipo es${it.teamChief}"
                    }
                    findViewById<TextView>(R.id.tvTeamEntry).apply {
                        text = "Primera participacion en ${it.firstTeamEntry}"
                    }
                    findViewById<TextView>(R.id.tvTeamWorldChampions).apply {
                        text = "Mundiales ganados: ${it.worldChampionships}"
                    }
                    findViewById<TextView>(R.id.tvTeamFirstDriver).apply {
                        text = it.drivers.get(0).lastname
                    }
                    findViewById<TextView>(R.id.tvTeamSecondDriver).apply {
                        text = it.drivers.get(1).lastname
                    }
                    findViewById<ImageView>(R.id.ivTeamLogo).setImageResource(setTeamRef(it.teamName))
                    findViewById<ImageView>(R.id.ivTeamFirstDriver).setImageResource(setDriverRef(it.drivers.get(0).lastname))
                    findViewById<ImageView>(R.id.ivTeamSecondDriver).setImageResource(setDriverRef(it.drivers.get(1).lastname))
                }
            }
        }



}