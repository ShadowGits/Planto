package com.example.edp_plantsystem.Fragments;

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment;
import com.example.edp_plantsystem.Adapter.IrrigationAdapter
import com.example.edp_plantsystem.IrrigationClass
import com.example.edp_plantsystem.R

import kotlin.collections.ArrayList

class IrrigationLogFragment: Fragment() {

    var arrayListIrrigationX= mutableListOf<IrrigationClass>() as ArrayList<IrrigationClass>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {



        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.activity_irrigation_fragment, container, false)
        //arrayListIrrigationX=FetchThingspeakTask().execute().get()

//

        arrayListIrrigationX.add(IrrigationClass("27-08-20","Dont water me",R.drawable.we_are_watered))

        var listView=view.findViewById<ListView>(R.id.irrigationLog)
        var adapter=IrrigationAdapter(activity,arrayListIrrigationX)

        listView.adapter=adapter
        return view

    }


//
//        inner class FetchThingspeakTask : AsyncTask<Void, Void, ArrayList<IrrigationClass>>() {
//        override fun doInBackground(vararg urls: Void): ArrayList<IrrigationClass> {
//            var arrayListIrrigation= mutableListOf<IrrigationClass>() as ArrayList<IrrigationClass>
//
//            try {
//            val url = URL(THINGSPEAK_CHANNEL_URL + THINGSPEAK_CHANNEL_ID +
//                    THINGSPEAK_FEEDS_LAST + THINGSPEAK_API_KEY_STRING + "=" +
//                    THINGSPEAK_API_KEY + "")
//            val urlConnection = url.openConnection() as HttpURLConnection
//            try {
//                val bufferedReader = BufferedReader(InputStreamReader(urlConnection.inputStream)!!)
//                val stringBuilder = StringBuilder()
//                var line: String? = null;
//                while ({ line = bufferedReader.readLine(); line }() != null) {
//
//                        stringBuilder.append(line).append("\n")
//                }
//                bufferedReader.close()
//                var response= stringBuilder.toString()
//
//
//                try {
//                    //Log.i("JSON YOLO",response!!)
//                    val channel = JSONObject(response)
//                    val feeds = channel.getJSONArray("feeds")
//                    var check=0
//                    for (i in 0 until 1000)
//                    {
//                        var p=feeds.getJSONObject(i).getString("field1")
//                        //Log.i("CheckinBabe",p)
//                        if(p != "null"){
//                            check=i
//                            break}
//
//
//                    }
//                    //Log.i("CheckinBabe",check.toString())
//                    var hashMap1 : HashMap<String, Int>
//                            = HashMap()
//
//                    for ( i in (check+1) until 1000) {
//                        var parse1 = feeds.getJSONObject(i)
//                        var v1S=parse1.getString(THINGSPEAK_FIELD1)
//                        if(v1S=="null")
//                            continue
//                        var d1="2019-11-09"
//                        var d2="2019-11-11"
//
//                        var d3="2019-11-12"
//
//
//
//
//                        //val parse2 = feeds.getJSONObject(19)
//
//
//                        //Log.i("JSON#", channel.toString())
//
//                        var date_time=parse1.getString("created_at")
//                        var date=date_time.substring(0,10)
//                        var time=date_time.substring(11,13)
//
//                        //Log.i("9090909",date+time)
//
//                            var v1 = v1S.toInt()
//                            if(hashMap1.containsKey(date+time))
//                                continue
//                            else {
//                                hashMap1[date+time] = 0
//                                //Log.i("HASGMAP",hashMap1.toString())
//
//
//                                if (v1 > 850) {
//                                    arrayListIrrigation.add(IrrigationClass("$date  $time:00", "Water me Please!", R.drawable.water_me_emoji))
//                                }
//
//                                if (v1 in 501..800) {
//
//                                    arrayListIrrigation.add(IrrigationClass("$date  $time:00", "We were watered recently", R.drawable.take_rest_emoji))
//                                    Log.i("FUCK", arrayListIrrigation[0].irrigationStatus)
//                                }
//                                if (v1 < 500)
//                                    arrayListIrrigation.add(IrrigationClass("$date  $time:00", "Take rest. We are happy and moist", R.drawable.we_are_watered))
//                            }
//
//
//                    }
//
//
//
//
//
////                    val v2 = parse2.getInt(THINGSPEAK_FIELD1)
////                    Log.i("pardfgh",v1.toString()+v2.toString())
//
//
//                } catch (e:JSONException) {
//                    e.printStackTrace()
//                }
//            } finally {
//                urlConnection.disconnect()
//            }
//        } catch (e: Exception) {
//            Log.e("ERROR", e.message, e)
//
//        }
//            return arrayListIrrigation
//
//            }
//
//
//
//
//    }
//
//    companion object{
//        private val TAG = "UsingThingspeakAPI"
//        private val THINGSPEAK_CHANNEL_ID = "907091"
//        private val THINGSPEAK_API_KEY = "YZW2L13XJF6M6323" //GARBAGE KEY
//        private val THINGSPEAK_API_KEY_STRING = "api_key"
//        /* Be sure to use the correct fields for your own app*/
//        private val THINGSPEAK_FIELD1 = "field1"
//        private val THINGSPEAK_FIELD2 = "field2"
//        private val THINGSPEAK_UPDATE_URL = "https://api.thingspeak.com/update?"
//        private val THINGSPEAK_CHANNEL_URL = "https://api.thingspeak.com/channels/"
//        private val THINGSPEAK_FEEDS_LAST = "/feeds.json/?results=1000&"
//    }


}



