package com.example.edp_plantsystem

import android.os.AsyncTask
import android.util.Log

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.util.ArrayList
import java.util.concurrent.ExecutionException

class PlantInfo {


    private inner class parsePlantData : AsyncTask<String, Void, ArrayList<String>>(){

        override fun doInBackground(vararg strings: String): ArrayList<String> {

            val result = ArrayList<String>()

            val urlString = "https://trefle.io/api/plants?q=" + strings[0] + "&token=YlNvYUlTRHd0QWtZdkQ1M1I2eXVmUT09"
            val url: URL
            var json = ""
            try {
                url = URL(urlString)
                var httpURLConnection = url.openConnection() as HttpURLConnection
                var `in` = httpURLConnection.inputStream
                var inputStreamReader = InputStreamReader(`in`)

                var data = inputStreamReader.read()
                while (data != -1) {
                    json += data.toChar() + ""
                    data = inputStreamReader.read()
                }

                val jsonArray = JSONArray(json)
                //Log.i("json",jsonArray.toString());
                var firstPlantResult: JSONObject

                var requiredIndex = -1
                for (j in 0 until jsonArray.length()) {
                    firstPlantResult = jsonArray.getJSONObject(j)
                    if (firstPlantResult.getBoolean("complete_data")) {
                        requiredIndex = j
                        break
                    }


                }
                if (requiredIndex == -1) {
                    result.add("No data available")
                } else {
                    firstPlantResult = jsonArray.getJSONObject(requiredIndex)
                    //Log.i("Info",firstPlantResult.toString());
                    val link = "https" + firstPlantResult.getString("link").substring(4) + "?token=YlNvYUlTRHd0QWtZdkQ1M1I2eXVmUT09"
                    val plantInfoURL = URL(link)
                    Log.i("Info", link)


                    httpURLConnection = plantInfoURL.openConnection() as HttpURLConnection
                    `in` = httpURLConnection.inputStream
                    inputStreamReader = InputStreamReader(`in`)
                    data = inputStreamReader.read()
                    json = ""
                    while (data != -1) {
                        json += data.toChar() + ""
                        data = inputStreamReader.read()

                    }
                    //Log.i("info",json);

                    val secondJSONresponse = JSONObject(json)

                    //main object
                    val mainSpecies = secondJSONresponse.getJSONObject("main_species")
                    Log.i("Info", mainSpecies.toString())

                    //sub objects
                    val specifications = mainSpecies.getJSONObject("specifications")
                    val seed=mainSpecies.getJSONObject("seed")
                    val growth=mainSpecies.getJSONObject("growth")
                    val fruit_or_seed=mainSpecies.getJSONObject("fruit_or_seed")
                    val flower=mainSpecies.getJSONObject("flower")
                    Log.i("Info", specifications.toString())

                    //sub level 2
                    val temp=growth.getJSONObject("temperature_minimum")

                    //string at level2 at growth object
                    val moisture_use=growth.getString("moisture_use")
                    val fertility_requirement=growth.getString("fertility_requirement")
                    val salinity_tolerance=growth.getString("salinity_tolerance")

                    Log.i("info", specifications.getString("growth_rate"))
                    result.add("Growth Rate => " + specifications.getString("growth_rate"))
                    result.add("Growing Period => " + specifications.getString("growth_period"))
                    result.add("Growth habit => " + specifications.getString("growth_habit"))
                    result.add("Form =>" + specifications.getString("growth_form"))
                    result.add("Bloom Time =>" + seed.getString("bloom_period"))
                    result.add("Temperature lower limit =>"+temp.getString("deg_c")+" C")
                    result.add("Saline soil adaptation =>$salinity_tolerance")
                    result.add("$moisture_use usage of moisture required")
                    result.add("$fertility_requirement usage adaptive to fertilizers")
                    result.add(flower.getString("color")+" colored flowering")
                    result.add("Color of seeds and fruits ranges in "+fruit_or_seed.getString("color"))





                    //result.add(firstPlantResult.getString("link"));
                    //result.add(json);
                }

            } catch (e: MalformedURLException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            } catch (e: JSONException) {
                e.printStackTrace()
            }

            return result


        }
    }

    @Throws(ExecutionException::class, InterruptedException::class)
    fun getPlantData(plantName: String): ArrayList<String> {

        var plantDataArrayList :ArrayList<String> = mutableListOf<String>() as ArrayList<String>
        plantDataArrayList.add("--This server is responsive for your current query--");
        plantDataArrayList.add("--Visit the query tab on main screen for more info about the plant--");


        var temp:ArrayList<String>
        for (j in 0 until 3) {
            temp=parsePlantData().execute(plantName).get()
            if (temp[0].equals("No data available",ignoreCase = true))
                continue
            else

                plantDataArrayList.remove("--This server is responsive for your current query--");
            plantDataArrayList.remove("--Visit the query tab on main screen for more info about the plant--");
                plantDataArrayList=temp
                //plantDataArrayList.add(j.toString())
                break

        }

        //return plantDataArrayList.toString()
        return plantDataArrayList
    }

}
