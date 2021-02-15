package com.example.mobileadslegacy

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.MobileAds

class MainActivity : AppCompatActivity() {

    private lateinit var banner:Button
    private lateinit var btnInterstitialAd:Button
    private lateinit var nativeAdvanced:Button
    private lateinit var rewardedVideo:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MobileAds.initialize(this) {}
        banner = findViewById(R.id.banner)
        btnInterstitialAd = findViewById(R.id.btnInterstitialAd)
        nativeAdvanced = findViewById(R.id.nativeAdvanced)
        rewardedVideo = findViewById(R.id.rewardedVideo)

        banner.setOnClickListener {
            startActivity(Intent(this, AdaptiveBanners::class.java))
        }

        btnInterstitialAd.setOnClickListener {
            startActivity(Intent(this, InterstitialAdActivity::class.java))
        }

        nativeAdvanced.setOnClickListener {
            startActivity(Intent(this, NativeAdvancedActivity::class.java))
        }

        rewardedVideo.setOnClickListener {
            startActivity(Intent(this, RewardedVideoActivity::class.java))
        }

    }
}