package com.williams.platformscience.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.williams.platformscience.databinding.FragmentDriversBinding
import com.williams.platformscience.domain.Driver

class DriversFragment(private val drivers: List<Driver>, private val onDriverSelectedListener: OnDriverSelectedListener): DialogFragment() {

    private var _binding: FragmentDriversBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDriversBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.driversRecyclerView.apply {
            adapter = DriversAdapter(drivers, onDriverSelectedListener)
            layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}