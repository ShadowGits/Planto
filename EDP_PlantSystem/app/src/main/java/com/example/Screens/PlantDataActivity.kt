package com.example.Screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.lifecycle.ViewModelProviders
import com.example.edp_plantsystem.R
import com.example.edp_plantsystem.viewmodels.PlantDataActivityViewModel


class PlantDataActivity : AppCompatActivity() {
    private var listView: ListView? = null
    private var enterName: EditText? = null
    private var textView: TextView? = null
    private lateinit var plantDataActivityViewModel:PlantDataActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plant_data)

        plantDataActivityViewModel=ViewModelProviders.of(this).get(PlantDataActivityViewModel::class.java)

        enterName=findViewById(R.id.plantName)

        val getInfo = findViewById<View>(R.id.getInfo) as Button
        var data: ArrayList<String> = mutableListOf<String>() as ArrayList<String>
        data.add("Lorem Ipsum")

//        plantDataActivityViewModel.getPlantDetails()!!.observe(this, Observer {
//            Toast.makeText(this,"hi DATA CHANGES ",Toast.LENGTH_SHORT).show()
//        })

        getInfo.setOnClickListener {
            val plantName = enterName!!.text.toString().toLowerCase()
            Log.i("plantname",plantName)
            plantDataActivityViewModel.queryPlantRepo(plantName)


            }

            listView = findViewById(R.id.dataListView)

            //var intent=this@PlantDataActivity.intent
            //var data:ArrayList<String> = intent.getStringArrayListExtra("plantData")
            val itemsAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data)
            listView!!.adapter = itemsAdapter

        }
    }
