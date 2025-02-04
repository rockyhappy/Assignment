package com.devrachit.swipeassignment.presentation.screens.addProductScreen

import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import com.devrachit.swipeassignment.R
import com.devrachit.swipeassignment.databinding.FragmentAddProductListDialogBinding


class AddProduct : BottomSheetDialogFragment() {

    private var _binding: FragmentAddProductListDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAddProductListDialogBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply{
            val adapter = ArrayAdapter.createFromResource(
                requireContext(),
                R.array.dropdown_items,
                android.R.layout.simple_spinner_item
            )
            adapter.setDropDownViewResource(R.layout.spinner_item)
            spinner.adapter = adapter
            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    val selectedItem = parent?.getItemAtPosition(position).toString()
                    (parent?.getChildAt(0) as TextView).setTextColor(resources.getColor(R.color.black))
                }
                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}