package com.dicoding.utaya.ui.Bottom.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.dicoding.utaya.data.utils.Preference
import com.dicoding.utaya.databinding.FragmentProfileBinding
import com.dicoding.utaya.ui.changePassword.ChangePwActivity
import com.dicoding.utaya.ui.login.LoginActivity

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment with View Binding
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val view = binding.root

        val username = Preference.getUsername(requireContext())

        binding.tvUsername.text = username

        binding.btToChangePw.setOnClickListener {
            val intent = Intent(activity, ChangePwActivity::class.java)
            startActivity(intent)
        }

        // Set onClickListener untuk tombol logout
        binding.btLogout.setOnClickListener {
            showLogoutDialog()
        }

        return view
    }

    private fun showLogoutDialog() {
        AlertDialog.Builder(requireContext()).apply {
            setTitle("Konfirmasi Logout")
            setMessage("Apakah Anda yakin ingin keluar?")
            setPositiveButton("Ya") { _, _ ->
                logout()
            }
            setNegativeButton("Tidak") { dialog, _ ->
                dialog.dismiss()
            }
            create()
            show()
        }
    }

    private fun logout() {
        // Menghapus token pengguna
        Preference.logOut(requireContext())

        // Navigasi ke LoginActivity
        val intent = Intent(activity, LoginActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
