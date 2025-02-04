package com.devrachit.swipeassignment.presentation.screens.homeScreen

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.devrachit.swipeassignment.R
import com.devrachit.swipeassignment.data.models.ProductItem
import com.devrachit.swipeassignment.databinding.FragmentHomeScreenBinding
import com.devrachit.swipeassignment.databinding.LayoutDialogItemDetailBinding
import com.devrachit.swipeassignment.presentation.adapters.OnClickListener
import com.devrachit.swipeassignment.presentation.adapters.ProductListAdapter
import com.devrachit.swipeassignment.utility.isConnected
import com.google.android.material.appbar.AppBarLayout
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.math.abs


class HomeScreen : Fragment() {

    private lateinit var binding: FragmentHomeScreenBinding
    var searchEditText: CharSequence = ""

    private val viewModel: HomeScreenViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Home", "onCreate: Called")
        checkInternetAndLoadData()
    }

    override fun onResume() {
        super.onResume()
        checkInternetAndLoadData()
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
                    fabAddItem.setOnClickListener {
                        findNavController().navigate(R.id.action_homeScreenFragment_to_bottomSheetFrag)
                    }
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

                        }
//                        if (states.error.isNotEmpty()) {
//                            Toast.makeText(requireContext(), states.error, Toast.LENGTH_SHORT)
//                                .show()
//                        }
                        if (states.productsList.isNotEmpty()) {
                            Log.d("HomeScreen adapter", "onCreateView: ${states.productsList}")
                            val dataList: List<ProductItem> = states.filteredList
                            val adapter = ProductListAdapter(dataList, object : OnClickListener {
                                override fun onClick(index: Int) {
                                    showItemDetailsDialog(dataList[index])
                                }
                            })
                            recycler.adapter = adapter
                            SwipeRefreshLayout.visibility = View.VISIBLE
                            recycler.visibility = View.VISIBLE
                        }
                    }
                }
            }
        }

        return binding.root
    }

    private fun refresh() {
        lifecycleScope.launch {
            binding.apply {
                etSearch.text.clear()
                etSearch2.text.clear()
            }
            binding.SwipeRefreshLayout.isRefreshing = true
            delay(1000)
            viewModel.changeRefreshState(true)
            checkInternetAndLoadData()
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
        binding.etSearch.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                viewModel.filterProducts(searchEditText.toString())
                true
            } else {
                false
            }
        }
        binding.clearSearch.setOnClickListener {
            binding.etSearch.text.clear()
            binding.etSearch2.text.clear()
            searchEditText = ""
            viewModel.filterProducts("")
        }
        binding.clearSearch2.setOnClickListener {
            binding.etSearch.text.clear()
            binding.etSearch2.text.clear()
            searchEditText = ""
            viewModel.filterProducts("")
        }
        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                Log.d("Search", "User typed: $s")
                binding.clearSearch2.visibility = if (s.isNullOrEmpty()) View.GONE else View.VISIBLE
                if (binding.etSearch2.text.toString() != s.toString()) {
                    binding.etSearch2.setText(s)
                    searchEditText = s
                    viewModel.filterProducts(s.toString())
                }
            }

            override fun afterTextChanged(s: Editable) {}

        })
        binding.etSearch2.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                Log.d("Search", "User typed: $s")
                binding.clearSearch.visibility = if (s.isNullOrEmpty()) View.GONE else View.VISIBLE
                if (binding.etSearch.text.toString() != s.toString()) {
                    binding.etSearch.setText(s)
                    searchEditText = s
                    viewModel.filterProducts(s.toString())
                }
            }

            override fun afterTextChanged(s: Editable) {}
        })
    }

    private fun showItemDetailsDialog(product: ProductItem) {
        val binding = LayoutDialogItemDetailBinding.inflate(LayoutInflater.from(context))
        val dialogBuilder = AlertDialog.Builder(requireContext(), R.style.CustomDialog)
            .setView(binding.root)


        val dialog = dialogBuilder.create()
        dialog.show()
        binding.itemName.text = product.productName
        binding.itemPrice.text = "₹" + product.price.toString()
        binding.itemType.text = "Type: " + product.productType
        binding.itemTax.text = "Tax: " + product.tax.toString()
        Glide.with(binding.itemImage.context).load(product.image).placeholder(R.drawable.picture)
            .into(binding.itemImage)


    }

    private fun showNoInternetDialog() {
        val dialogView =
            LayoutInflater.from(context).inflate(R.layout.layout_no_internet_dialog, null)
        val dialogBuilder = AlertDialog.Builder(requireContext(), R.style.CustomDialog)
            .setView(dialogView)

        val dialog = dialogBuilder.create()
        dialog.show()

        dialogView.findViewById<Button>(R.id.retry).setOnClickListener {
            dialog.dismiss()
            checkInternetAndLoadData()
        }
    }

    private fun checkInternetAndLoadData() {
        context?.let {
            if (!isConnected(it)) {
                showNoInternetDialog()
            }
            viewModel.getData(true, it)
        }
    }
}