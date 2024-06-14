package com.dicoding.utaya.ui.Bottom.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.utaya.R
import com.dicoding.utaya.data.produk.ListProdukAdapter
import com.dicoding.utaya.data.produk.Produk
import com.dicoding.utaya.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private lateinit var rvProduk: RecyclerView
    private val list = ArrayList<Produk>()

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        rvProduk = binding.rvProduk
        rvProduk.setHasFixedSize(true)
        list.addAll(getListProduk())
        showRecyclerList()
        return root

    }

    private fun getListProduk(): ArrayList<Produk> {
        val dataMerk = resources.getStringArray(R.array.data_merk)
        val dataHarga = resources.getStringArray(R.array.data_harga)
        val dataLink = resources.getStringArray(R.array.data_link)
        val dataArtikel = resources.getStringArray(R.array.data_article)
        val dataFoto = resources.obtainTypedArray(R.array.data_foto)
        val listHero = ArrayList<Produk>()
        for (i in dataMerk.indices) {
            val hero = Produk(dataMerk[i], dataHarga[i], dataLink[i], dataArtikel[i], dataFoto.getResourceId(i, -1))
            listHero.add(hero)
        }
        return listHero

    }

    private fun showRecyclerList() {
        rvProduk.layoutManager = LinearLayoutManager(requireContext())
        val listProdukAdapter = ListProdukAdapter(list)
        rvProduk.adapter = listProdukAdapter
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}