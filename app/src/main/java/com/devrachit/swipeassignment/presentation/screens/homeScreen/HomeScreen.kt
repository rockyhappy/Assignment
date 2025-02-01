package com.devrachit.swipeassignment.presentation.screens.homeScreen

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.devrachit.swipeassignment.R
import com.devrachit.swipeassignment.data.models.ProductItem
import com.devrachit.swipeassignment.databinding.FragmentHomeScreenBinding
import com.devrachit.swipeassignment.presentation.adapters.OnClickListener
import com.devrachit.swipeassignment.presentation.adapters.ProductListAdapter
import com.google.android.material.appbar.AppBarLayout
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.math.abs


class HomeScreen : Fragment() {

    private lateinit var binding: FragmentHomeScreenBinding

    private val viewModel: HomeScreenViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Home", "onCreate: Called")
        context?.let {
            viewModel.getData(true, it)
        }
    }

    override fun onResume() {
        super.onResume()
        context?.let {
            viewModel.getData(true, it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeScreenBinding.inflate(inflater, container, false)
        setupAppBarLayout()
        setupSearchListeners()
        binding.recycler.layoutManager = LinearLayoutManager(this.context)
        binding.SwipeRefreshLayout.setOnRefreshListener {
            refresh()
        }

        lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                binding.apply {
                    scrollContent.visibility = View.VISIBLE
                    viewModel.uiStates.collectLatest { states ->
                        Log.d("HomeScreen ", "onCreateView: $states")
                        if (states.loading) {
                            recycler.visibility = View.GONE
                            SwipeRefreshLayout.visibility = View.GONE
                            progressBar.visibility = View.VISIBLE
                            progressBar.startShimmer()
                        } else {
                            progressBar.stopShimmer()
                            progressBar.visibility = View.GONE
                            SwipeRefreshLayout.isRefreshing = states.isRefreshing
//                            SwipeRefreshLayout.visibility = View.VISIBLE
//                            recycler.visibility = View.VISIBLE
                        }
//                        if (states.error.isNotEmpty()) {
//                            Toast.makeText(requireContext(), states.error, Toast.LENGTH_SHORT)
//                                .show()
//                        }
                        if (states.productsList.isNotEmpty()) {
                            Log.d("HomeScreen adapter", "onCreateView: ${states.productsList}")
                            val dataList :List<ProductItem> = states.filteredList
                            val adapter = ProductListAdapter(dataList, object : OnClickListener {
                                override fun onClick(index: Int) {
                                    // Handle click
                                }
                            })
                            recycler.adapter = adapter
                            SwipeRefreshLayout.visibility = View.VISIBLE
                            recycler.visibility = View.VISIBLE
//
//
                        }
                    }
                }
            }
        }

        return binding.root
    }
    private fun refresh() {
        lifecycleScope.launch {

            binding.SwipeRefreshLayout.isRefreshing = true

            delay(1200)
            viewModel.changeRefreshState(true)
            viewModel.getData(true, requireContext())
            viewModel.changeRefreshState(false)
            binding.SwipeRefreshLayout.isRefreshing = false
        }
    }

    private fun setupAppBarLayout() {
        binding.appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            val totalScrollRange = appBarLayout.totalScrollRange
            when {
                abs(verticalOffset) == totalScrollRange -> {
                    // Fully collapsed
                    binding.collapsedToolbar.visibility = View.VISIBLE
                    binding.expandedHeaderContent.visibility = View.INVISIBLE
                }

                verticalOffset == 0 -> {
                    // Fully expanded
                    binding.collapsedToolbar.visibility = View.INVISIBLE
                    binding.expandedHeaderContent.visibility = View.VISIBLE
                }

                else -> {
                    // In between expanded and collapsed
                    binding.collapsedToolbar.visibility = View.INVISIBLE
                    binding.expandedHeaderContent.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun setupSearchListeners() {
        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                Log.d("Search", "User typed: $s")
                if (binding.etSearch2.text.toString() != s.toString()) {
                    binding.etSearch2.setText(s)
                    viewModel.filterProducts(s.toString())
                }
            }

            override fun afterTextChanged(s: Editable) {}
        })
        binding.etSearch2.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                Log.d("Search", "User typed: $s")
                if (binding.etSearch.text.toString() != s.toString()) {
                    binding.etSearch.setText(s)
                    viewModel.filterProducts(s.toString())
                }
            }

            override fun afterTextChanged(s: Editable) {}
        })
    }
}