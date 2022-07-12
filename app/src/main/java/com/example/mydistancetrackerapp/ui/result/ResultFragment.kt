package com.example.mydistancetrackerapp.ui.result

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.mydistancetrackerapp.R
import com.example.mydistancetrackerapp.databinding.FragmentMapsBinding
import com.example.mydistancetrackerapp.databinding.FragmentResultBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ResultFragment : BottomSheetDialogFragment() {

    private val args: ResultFragmentArgs by navArgs()

    private var _binding: FragmentResultBinding ? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResultBinding.inflate(inflater, container, false)

        binding.distanceValueTestview.text = getString(R.string.result, args.result.distance)
        binding.timeValueTestview.text = args.result.time.toString()

        binding.shareButton.setOnClickListener {
            sharedResult()
        }

        return binding.root
    }

    private fun sharedResult() {
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            type="text/plain"
            putExtra(Intent.EXTRA_TEXT, "I went ${args.result.distance}kim in ${args.result.time}!")
        }
        startActivity(shareIntent)
    }


    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}

