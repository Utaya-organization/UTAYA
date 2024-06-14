package com.dicoding.utaya.ui.Bottom.camera

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dicoding.utaya.databinding.FragmentCameraBinding

class CameraFragment : Fragment() {

    private var _binding : FragmentCameraBinding? = null
    private val binding get() = _binding!!
    private val REQUEST_IMAGE_CAPTURE = 1
    private val SELECT_PICTURE = 2

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val CameraViewModel =
            ViewModelProvider(this).get(CameraViewModel::class.java)
        _binding = FragmentCameraBinding.inflate(inflater,container,false)
        val root : View = binding.root

        binding.cameraButton.setOnClickListener {
            dispatchTakePictureIntent()
        }

        binding.galleryButton.setOnClickListener {
            dispatchSelectPictureIntent()
        }

        return root
    }

    private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(requireActivity().packageManager)?.also {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }
        }
    }

    private fun dispatchSelectPictureIntent() {
        Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI).also { selectPictureIntent ->
            selectPictureIntent.resolveActivity(requireActivity().packageManager)?.also {
                startActivityForResult(selectPictureIntent, SELECT_PICTURE)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            binding.previewImageView.setImageBitmap(imageBitmap)
        } else if (requestCode == SELECT_PICTURE && resultCode == Activity.RESULT_OK) {
            val selectedImageUri = data?.data
            binding.previewImageView.setImageURI(selectedImageUri)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
