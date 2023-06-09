package net.azarquiel.fmatic.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import net.azarquiel.fmatic.R
import net.azarquiel.fmatic.adapter.AdapterDrivers
import net.azarquiel.fmatic.adapter.AdapterTeams
import net.azarquiel.fmatic.model.Teams
import net.azarquiel.fmatic.viewModel.MainViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TeamsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TeamsFragment : Fragment() {
    private lateinit var adapter: AdapterTeams
    private lateinit var teams: List<Teams>
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
         * @return A new instance of fragment TeamsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TeamsFragment().apply {
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
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_teams, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onLaunchView()
    }

    private fun onLaunchView() {
        getAllTeams()
        initRV()
    }

    private fun initRV() {
        val rvTeams = requireView().findViewById<RecyclerView>(R.id.rvTeams)
        adapter = AdapterTeams(requireContext(),R.layout.row_teams)
        rvTeams.adapter = adapter
        rvTeams.layoutManager = GridLayoutManager(requireContext(),1)
    }

    private fun getAllTeams() =
        viewModel.getAllTeams().observe(viewLifecycleOwner) { it ->
            it?.let {
                adapter.setTeams(it)
                teams = it
            }
        }

}