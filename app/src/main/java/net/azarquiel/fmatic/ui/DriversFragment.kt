package net.azarquiel.fmatic.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import net.azarquiel.fmatic.R
import net.azarquiel.fmatic.adapter.AdapterDrivers
import net.azarquiel.fmatic.interfaces.GlobalInterface
import net.azarquiel.fmatic.model.Drivers
import net.azarquiel.fmatic.viewModel.MainViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

//variables
class DriversFragment : Fragment(), GlobalInterface{
    private lateinit var rvDrivers: RecyclerView
    private lateinit var adapter: AdapterDrivers
    private lateinit var drivers: List<Drivers>
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
         * @return A new instance of fragment PilotsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DriversFragment().apply {
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
    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_drivers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
    }

    private fun setup() {
        getAllDrivers()
        initRV()
    }

    private fun initRV() {
        val rvDrivers = requireView().findViewById<RecyclerView>(R.id.rvDrivers)
        val gridLayoutManager = GridLayoutManager(requireContext(), 2)
        adapter = AdapterDrivers(requireContext(),R.layout.row_driver)
        rvDrivers.adapter = adapter
        rvDrivers.layoutManager = gridLayoutManager
    }

    private fun getAllDrivers() {
        viewModel.getAllDrivers().observe(viewLifecycleOwner, Observer {
            it -> it?.let{
                adapter.setDrivers(it)
                drivers = it
            }
        })
    }
}