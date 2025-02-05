package com.devrachit.swipeassignment.presentation.screens.addProductScreen

import android.Manifest
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.devrachit.swipeassignment.R
import com.devrachit.swipeassignment.databinding.FragmentAddProductListDialogBinding
import com.devrachit.swipeassignment.presentation.adapters.ImageListAdapter
import com.devrachit.swipeassignment.utility.PermissionManager
import com.devrachit.swipeassignment.utility.SnackBar
import android.Manifest.permission.READ_MEDIA_IMAGES
import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.app.AlertDialog
import androidx.collection.emptyLongSet
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.Glide
import com.devrachit.swipeassignment.data.models.ProductItem
import com.devrachit.swipeassignment.databinding.LayoutDialogItemDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.devrachit.swipeassignment.domain.models.UploadProductModel
import com.devrachit.swipeassignment.presentation.adapters.OnImageClickListener
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class AddProduct : BottomSheetDialogFragment() {

    private var _binding: FragmentAddProductListDialogBinding? = null
    private val binding get() = _binding!!
    private lateinit var imageListAdapter: ImageListAdapter
    private lateinit var imageList: MutableList<Uri>
    private lateinit var permissionLauncher: ActivityResultLauncher<String>
    private val viewModel : AddProductViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                SnackBar(requireContext()).showSuccessSnack(binding.root, "Permission Granted")
            } else {
                SnackBar(requireContext()).showErrorSnack(binding.root, "Permission Denied")
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAddProductListDialogBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            setupSpinner()
            setupImageRecycler()
            setupAddImagesButton()
            onClickListener()
            lifecycleScope.launch{
                viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.uiStates.collectLatest {
                        if (it.loading) {
                            showLoadingDialog()
                        }
                        else{
                            hindLoadingDialog()
                        }
                    }
                }
            }
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 22 && resultCode == RESULT_OK && data != null) {
            if (data.clipData != null) {
                val count = data.clipData!!.itemCount
                val list = arrayListOf<Uri>()
                for (i in 0 until count) {
                    val imageUri = data.clipData!!.getItemAt(i).uri
                    list.add(imageUri)
                }
                imageList.addAll(list)
                imageListAdapter.notifyDataSetChanged()

            } else {
                val imageUri = data.data!!
                imageList.add(imageUri)
                imageListAdapter.notifyDataSetChanged()
            }
        }
    }


    private fun setupAddImagesButton() {
        val pickMultipleMediaIntent =
            Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI).apply {
                putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            }

        binding.addImages.setOnClickListener {
            if (PermissionManager().checkPermissionImagePermssion(requireContext())) {
                startActivityForResult(pickMultipleMediaIntent, 22)
            } else {
                requestImagePermission()
            }
        }
    }

    private fun requestImagePermission() {
        permissionLauncher.launch(
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                READ_MEDIA_IMAGES
            } else {
                READ_EXTERNAL_STORAGE
            }
        )
        SnackBar(requireContext()).showInfoSnack(binding.root, "Permission Not Granted")
    }
    private fun setupSpinner() {
        val adapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.dropdown_items,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(R.layout.spinner_item)
        binding.spinner.adapter = adapter
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }
    private fun setupImageRecycler() {
        imageList = mutableListOf()
        imageListAdapter = ImageListAdapter(imageList, object : OnImageClickListener {
            override fun onImageClick(uri: Uri) {
                showItemDetailsDialog(uri)
            }

            override fun onRemoveImageClick(uri: Uri) {
                imageList.remove(uri)
                imageListAdapter.notifyDataSetChanged()
            }
        })
        binding.imageRecycler.adapter = imageListAdapter
    }
    fun onClickListener() {
        binding.apply {
            postButton.setOnClickListener {
                val productName = name.text.toString()
                val price = price.text.toString()
                val spinner = spinner.selectedItem.toString()
                val tax = itemTax.text.toString()

                val product = UploadProductModel(
                    productName = productName,
                    productType = spinner,
                    price = price,
                    tax = tax,
                    files = imageList
                )
                viewLifecycleOwner.lifecycleScope.launch {
                    val response = viewModel.postProduct(requireContext(), product)
                    if (response as Boolean) {
                        clearFields()
                        SnackBar(requireContext()).showSuccessSnack(
                            binding.root,
                            "Product Uploaded"
                        )
                    } else {
                        SnackBar(requireContext()).showErrorSnack(
                            binding.root,
                            "Product Not Uploaded"
                        )
                    }
                }
            }
        }
    }
    fun showLoadingDialog() {
        binding.apply {
            postButton.visibility = View.GONE
            progressBar.visibility = View.VISIBLE
        }
    }
    fun hindLoadingDialog() {
        binding.apply {
            postButton.visibility = View.VISIBLE
            progressBar.visibility = View.GONE
        }
    }
    fun clearFields() {
        binding.apply {
            name.text?.clear()
            price.text?.clear()
            itemTax.text?.clear()
            imageList.clear()
            imageListAdapter.notifyDataSetChanged()
        }
    }
    private fun showItemDetailsDialog(uri: Uri) {
        val binding = LayoutDialogItemDetailBinding.inflate(LayoutInflater.from(context))
        val dialogBuilder = AlertDialog.Builder(requireContext(), R.style.CustomDialog)
            .setView(binding.root)


        val dialog = dialogBuilder.create()
        dialog.show()
        binding.itemName.visibility = View.GONE
        binding.itemPrice.visibility = View.GONE
        binding.itemType.visibility = View.GONE
        binding.itemTax.visibility = View.GONE
        Glide.with(binding.itemImage.context).load(uri).placeholder(R.drawable.picture)
            .into(binding.itemImage)


    }

}