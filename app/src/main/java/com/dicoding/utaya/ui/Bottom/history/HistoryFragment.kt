package com.dicoding.utaya.ui.Bottom.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.utaya.R
import com.dicoding.utaya.ui.Bottom.history.History
import com.dicoding.utaya.ui.Bottom.history.ListHistoryAdapter
import com.dicoding.utaya.databinding.FragmentHistoryBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HistoryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HistoryFragment : Fragment() {
    private var _binding: FragmentHistoryBinding? = null

    private lateinit var rvHistory: RecyclerView
    private val list = ArrayList<History>()

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        rvHistory = binding.rvHistory
        rvHistory.setHasFixedSize(true)
        list.addAll(getListHistory())
        showRecyclerList()
        return root

    }

    private fun getListHistory(): ArrayList<History> {
        val dataSkin = resources.getStringArray(R.array.data_skin)
        val dataFoto = resources.obtainTypedArray(R.array.data_foto_skin)
        val listHistory = ArrayList<History>()
        for (i in dataSkin.indices) {
            val hero = History(dataSkin[i], dataFoto.getResourceId(i, -1))
            listHistory.add(hero)
        }
        return listHistory

    }

    private fun showRecyclerList() {
        rvHistory.layoutManager = LinearLayoutManager(requireContext())
        val listHistoryAdapter = ListHistoryAdapter(list)
        rvHistory.adapter = listHistoryAdapter
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}