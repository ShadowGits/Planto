package com.example.edp_plantsystem;
import android.graphics.Color;
import android.os.AsyncTask;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.ColorRes;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

import me.itangqi.waveloadingview.WaveLoadingView;

public class ThingsSpeakApiDataFetchActiivty extends AppCompatActivity {
    private static final String TAG = "UsingThingspeakAPI";
    private static final String THINGSPEAK_CHANNEL_ID = "907091";
    private static final String THINGSPEAK_API_KEY = "YZW2L13XJF6M6323"; //GARBAGE KEY
    private static final String THINGSPEAK_API_KEY_STRING = "api_key";
    /* Be sure to use the correct fields for your own app*/
    private static final String THINGSPEAK_FIELD1 = "field1";
    private static final String THINGSPEAK_FIELD2 = "field2";
    private static final String THINGSPEAK_UPDATE_URL = "https://api.thingspeak.com/update?";
    private static final String THINGSPEAK_CHANNEL_URL = "https://api.thingspeak.com/channels/";
    private static final String THINGSPEAK_FEEDS_LAST = "/feeds.json/?results=2&";
    TextView t1,t2;
    Button b1;
    WaveLoadingView mWaveLoadingView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_things_speak_api_data_fetch_actiivty);

        //SoilDataActivity object=new SoilDataActivity();
        mWaveLoadingView= findViewById(R.id.waveLoadingView);
        mWaveLoadingView.setProgressValue(45);
        mWaveLoadingView.setCenterTitle("Initial "+mWaveLoadingView.getProgressValue()+"%");


        mWaveLoadingView.setShapeType(WaveLoadingView.ShapeType.CIRCLE);
        mWaveLoadingView.setCenterTitleColor(Color.CYAN);

        mWaveLoadingView.setCenterTitleSize (24f);



        mWaveLoadingView.setBorderWidth(10f);
        mWaveLoadingView.setAmplitudeRatio(60);
        mWaveLoadingView.setWaveColor(Color.BLUE);
        mWaveLoadingView.setBorderColor(Color.GRAY);

        mWaveLoadingView.setAnimDuration(3000);

        mWaveLoadingView.startAnimation();
//        b1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                try {
//                    new FetchThingspeakTask().execute();
//                    //object.setValuex(100*(((int)(Double.parseDouble(t1.getText().toString()))))/1024);
//
//                }
//                catch(Exception e){
//                    Log.e("ERROR", e.getMessage(), e);
//                }
//
//
//            }
//        });


        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                //your method
                new FetchThingspeakTask().execute();
            }
        }, 0, 500);


//        while(true)
//        {
//
//
//            Log.i("hi","contibuous");
//
//        }

    }







    class FetchThingspeakTask extends AsyncTask<Void, Void, String> {
        protected String doInBackground(Void... urls) {
            try {
                URL url = new URL(THINGSPEAK_CHANNEL_URL + THINGSPEAK_CHANNEL_ID +
                        THINGSPEAK_FEEDS_LAST + THINGSPEAK_API_KEY_STRING + "=" +
                        THINGSPEAK_API_KEY + "");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    bufferedReader.close();
                    return stringBuilder.toString();
                }
                finally{
                    urlConnection.disconnect();
                }
            }
            catch(Exception e) {
                Log.e("ERROR", e.getMessage(), e);
                return null;
            }
        }
        protected void onPostExecute(String response) {
            Log.i("JSON",response);
            if(response == null) {
                Toast.makeText(ThingsSpeakApiDataFetchActiivty.this, "There was an error", Toast.LENGTH_SHORT).show();
                return;
            }
            try {
                JSONObject channel = new JSONObject(response);
                JSONArray feeds=channel.getJSONArray("feeds");

                JSONObject parse=feeds.getJSONObject(1);
                Log.i("JSON#",channel.toString());
                double v1 = parse.getDouble(THINGSPEAK_FIELD1);
                int v2=(int)(v1);

                if(v2<400)
                    mWaveLoadingView.setProgressValue(100);
                else if(v2>=400 && v2 <600)
                {
                    mWaveLoadingView.setProgressValue(80);

                }
                else if(v2>=600 && v2 <800)
                {
                    mWaveLoadingView.setProgressValue(55);

                }
                else if(v2>=800 && v2 <950)
                {
                    mWaveLoadingView.setProgressValue(25);

                }
                else

                    mWaveLoadingView.setProgressValue(5);








                mWaveLoadingView.setCenterTitle("Moisture "+mWaveLoadingView.getProgressValue()+"%");

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}