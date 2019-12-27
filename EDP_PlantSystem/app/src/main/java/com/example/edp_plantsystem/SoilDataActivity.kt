package com.example.edp_plantsystem

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import me.itangqi.waveloadingview.WaveLoadingView

class SoilDataActivity : AppCompatActivity() {
public var valuex:Int?=null

    fun setValuex(value:Int){
        valuex=value

    }
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_soil_data)
        val mWaveLoadingView = findViewById<WaveLoadingView>(R.id.waveLoadingView)
        mWaveLoadingView.setShapeType(WaveLoadingView.ShapeType.CIRCLE)
        mWaveLoadingView.centerTitleColor = Color.CYAN
        mWaveLoadingView.centerTitleSize = 24f

        mWaveLoadingView.centerTitle="Moisture "+mWaveLoadingView.progressValue+"%"


        mWaveLoadingView.borderWidth = 10f
        mWaveLoadingView.setAmplitudeRatio(60)
        mWaveLoadingView.waveColor = Color.BLUE
        mWaveLoadingView.borderColor = Color.GRAY
        //mWaveLoadingView.setTopTitleStrokeColor(Color.BLUE)
        //mWaveLoadingView.setTopTitleStrokeWidth(3f)
        mWaveLoadingView.setAnimDuration(3000)
        //mWaveLoadingView.pauseAnimation()
        //mWaveLoadingView.resumeAnimation()
        //mWaveLoadingView.cancelAnimation()
        mWaveLoadingView.startAnimation()

        while(true)
        {
            ThingsSpeakApiDataFetchActiivty()

            mWaveLoadingView.progressValue = valuex!!
        }

    }
}
