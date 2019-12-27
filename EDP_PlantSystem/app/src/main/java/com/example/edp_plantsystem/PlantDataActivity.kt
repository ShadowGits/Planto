package com.example.edp_plantsystem

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import java.util.concurrent.ExecutionException


class PlantDataActivity : AppCompatActivity() {
    private var listView: ListView? = null
    private var enterName: EditText? = null
    private var textView: TextView? = null
    private val plantInfo = PlantInfo()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plant_data)
        enterName = findViewById<View>(R.id.plantName) as EditText
        val getInfo = findViewById<View>(R.id.getInfo) as Button
        var data: ArrayList<String> = mutableListOf<String>() as ArrayList<String>

        getInfo.setOnClickListener {
            val plantName = enterName!!.text.toString().toLowerCase()
            Toast.makeText(this@PlantDataActivity, plantName, Toast.LENGTH_LONG).show()

            try {
                data = plantInfo.getPlantData(plantName)
                val mgr = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                mgr.hideSoftInputFromWindow(enterName!!.windowToken, 0)
                //textView!!.text=data.toString()
                //Log.i("Info", plantInfo.getPlantData(plantName))
            } catch (e: ExecutionException) {
                e.printStackTrace()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

            listView = findViewById(R.id.dataListView)

            //var intent=this@PlantDataActivity.intent
            //var data:ArrayList<String> = intent.getStringArrayListExtra("plantData")
            val itemsAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data)
            listView!!.adapter = itemsAdapter

        }
    }
}
