package com.example.garageapp.fragments

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.garageapp.R
import com.example.garageapp.Utils.BitMapConverter
import com.example.garageapp.Utils.FirebaseResult
import com.example.garageapp.ViewModel.MainViewModel
import com.example.garageapp.adapters.carAdapter
import com.example.garageapp.databinding.FragmentMainBinding
import com.example.garageapp.models.carInfo
import com.google.firebase.auth.FirebaseAuth
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File


@AndroidEntryPoint
class MainFragment : Fragment() {
    private var _binding:FragmentMainBinding?=null
    private val binding get() = _binding!!
    private lateinit var carAdapter: carAdapter

    var MakeName: String =""
    var ModelName: String =""
    private val MainViewModel by viewModels<MainViewModel>()

    lateinit var carinfo:carInfo
    lateinit var image:String
    lateinit var imageUri: Uri
    lateinit var lst:MutableList<com.example.garageapp.models.Result>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding= FragmentMainBinding.inflate(inflater,container,false)
        carAdapter=carAdapter(::onCarClicked,::onAddImage)
        MainViewModel.getAllMakes()
        MainViewModel.getAllCar()
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = carAdapter
        return binding.root
    }

    private fun onAddImage(carInfo: carInfo) {
        imageUri = createURI()!!
        if(this::imageUri.isInitialized){
            carinfo = carInfo
            contract.launch(imageUri)
        }
    }
    val contract= registerForActivityResult(ActivityResultContracts.TakePicture()){
        if(this::imageUri.isInitialized) {
            val source: ImageDecoder.Source =
                ImageDecoder.createSource(activity?.applicationContext!!.contentResolver, imageUri)
            val bitmap: Bitmap = ImageDecoder.decodeBitmap(source)
            val imgStr: String = BitMapConverter.converterBitmapToString(bitmap)
            image = imgStr
            MainViewModel.updateCar(carInfo(carinfo.id, carinfo.carMaker, carinfo.carModel, image))
        }
    }

    private fun createURI(): Uri?{
        try{
        val image= File(parentFragment?.activity?.applicationContext?.filesDir,"camera_photo.png")
        return FileProvider.getUriForFile(
            activity?.applicationContext!!,
            "com.example.garageapp.fileProvider",image)
        }catch (e:Exception) {
            Log.d("pp", e.toString())
            return null
        }
    }

    private fun onCarClicked(carInfo: carInfo) {
        MainViewModel.deleteCar(carInfo)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.BtnLogout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            MainViewModel.deleteAll()
            findNavController().popBackStack()
        }
        MainViewModel._AllCars.observe(viewLifecycleOwner, Observer {
            carAdapter.submitList(it)
        })

        val make :ArrayList<String> = arrayListOf()

        MainViewModel._AllMakes.observe(viewLifecycleOwner, Observer {
            when(it){
                is FirebaseResult.Error -> {}
                is FirebaseResult.Loading -> {}
                is FirebaseResult.Success -> {
                    lst=it.data!!.toMutableList()
                    it.data.forEach {
                        make.add(it.Make_Name)
                    }
                    val listAdapter: ArrayAdapter<String>? =activity?.let {
                        ArrayAdapter(it,R.layout.dropdown_item,make)
                    }
                    binding.spinner.adapter=listAdapter
                }
            }
        })

        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Log.d("PP",id.toString())
                var makeID:Int= 0
                lst.forEach {
                    if (it.Make_Name==make[position]){
                        makeID= it.Make_ID
                    }
                }
                //val makeID= make[position].Make_ID.toString()
                MakeName=make[position]
                setupSpinner2(makeID.toString())
            }
        }

        MainViewModel._AllModels.observe(viewLifecycleOwner, Observer {
            when(it){
                is FirebaseResult.Error -> {}
                is FirebaseResult.Loading -> {}
                is FirebaseResult.Success -> {
                    val model :ArrayList<String> = arrayListOf()
                    it.data?.forEach {
                        model.add(it.Model_Name)
                    }

                    val ArrayAdapter: ArrayAdapter<String>? =activity?.let {
                        ArrayAdapter(it,R.layout.dropdown_item,model)
                    }
                    binding.spinner2.adapter=ArrayAdapter
                    binding.spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                        override fun onNothingSelected(parent: AdapterView<*>?) {

                        }

                        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                            Log.d("PP",id.toString())
                            ModelName=model.toString()
                        }
                    }
                }
            }
        })

        binding.BtnAdd.setOnClickListener {
            if(MakeName.isNotEmpty() && ModelName.isNotEmpty()){
                MainViewModel.addCarInDB(carInfo(null,MakeName,ModelName,null))
            }
        }
    }

    private fun setupSpinner2(makeID: String) {
        MainViewModel.getModelsForMakeID(makeID)
    }
}