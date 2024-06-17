package com.dicoding.utaya.Bottom.camera

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dicoding.utaya.databinding.FragmentCameraBinding
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date

class CameraFragment : Fragment() {

    private var _binding: FragmentCameraBinding? = null
    private val binding get() = _binding!!
    private val REQUEST_IMAGE_CAPTURE = 1
    private val SELECT_PICTURE = 2
    private lateinit var photoURI: Uri

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val cameraViewModel =
            ViewModelProvider(this).get(CameraViewModel::class.java)
        _binding = FragmentCameraBinding.inflate(inflater, container, false)
        val root: View = binding.root

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
                val photoFile: File? = try {
                    createImageFile()
                } catch (ex: IOException) {
                    // Error occurred while creating the File
                    null
                }
                photoFile?.also {
                    photoURI = FileProvider.getUriForFile(
                        requireContext(),
                        "com.dicoding.utaya.fileprovider",
                        it
                    )
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
                }
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
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            binding.previewImageView.setImageURI(photoURI)
        } else if (requestCode == SELECT_PICTURE && resultCode == Activity.RESULT_OK) {
            val selectedImageUri = data?.data
            binding.previewImageView.setImageURI(selectedImageUri)
        }
    }

    private fun createImageFile(): File {
        // Create an image file name
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File = requireActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!
        return File.createTempFile(
            "JPEG_${timeStamp}_",
            ".jpg",
            storageDir
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
