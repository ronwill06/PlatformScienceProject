package com.williams.platformscience.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.williams.platformscience.databinding.FragmentShipmentsBinding
import com.williams.platformscience.domain.Driver
import com.williams.platformscience.domain.Shipment
import com.williams.platformscience.domain.ShipmentsDriversResponse
import com.williams.platformscience.extensions.readAssetsFile
import kotlinx.serialization.json.Json

class ShipmentsFragment : Fragment(), OnDriverSelectedListener {

    private var _binding: FragmentShipmentsBinding? = null
    private val binding get() = _binding!!
    private var shipmentsAdapter: ShipmentsAdapter? = null
    private lateinit var driversFragment: DriversFragment

    // I would move this to Hilt using viewmodel-ktx for dependency injection
    private val viewModel = ShipmentsViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShipmentsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Normally, I would flow a Clean Architectural pattern here, with DataSource, Repository,
        // and Use classes. However, given the brevity of this project, I decided to save this data in instance
        // variables in the ShipmentsViewModel
        val jsonString = requireActivity().assets.readAssetsFile("data.json")
        val response = Json.decodeFromString<ShipmentsDriversResponse>(jsonString)
        viewModel.saveShipmentsAndDrivers(response)

        driversFragment = DriversFragment(viewModel.drivers, this)
        shipmentsAdapter = ShipmentsAdapter(viewModel.shipments, this)
        binding.shipmentsRecyclerView.apply {
            adapter = shipmentsAdapter
            layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onOpenDriverPickerForShipment(shipment: Shipment) {
        viewModel.currentSelectedShipment = shipment
        driversFragment.show(childFragmentManager, null)
    }

    override fun onDriverSelected(driver: Driver) {
        driversFragment.dismiss()
        val shippingAddress = viewModel.currentSelectedShipment?.destinationStreetName
        if (shippingAddress != null) {
            val indexToUpdate = shipmentsAdapter?.findIndexOfShipmentWithAddress(shippingAddress) ?: -1
            if (indexToUpdate != -1) {
                shipmentsAdapter?.updateShipmentAtIndexWithDriver(indexToUpdate, driver)
                binding.shipmentsRecyclerView.post {
                    shipmentsAdapter?.notifyItemChanged(indexToUpdate)
                }
            }
        }
    }
}