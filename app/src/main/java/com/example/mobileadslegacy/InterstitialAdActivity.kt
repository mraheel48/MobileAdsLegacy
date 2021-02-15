package com.example.mobileadslegacy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.google.android.gms.ads.*

class InterstitialAdActivity : AppCompatActivity() {

    lateinit var loadInterstitialAd: Button
    lateinit var showInterstitialAd: Button

    private lateinit var mInterstitialAd: InterstitialAd

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_interstitial_ad)

        // Initialize the Mobile Ads SDK.
        MobileAds.initialize(this) {}
        mInterstitialAd = InterstitialAd(this)
        mInterstitialAd.adUnitId = Utils.interstitialTestId

        loadInterstitialAd = findViewById(R.id.loadInterstitialAd)
        showInterstitialAd = findViewById(R.id.showInterstitialAd)

        mInterstitialAd.adListener = object : AdListener() {

            override fun onAdLoaded() {
                Log.d("AdManager", "Ad Loaded")
                Utils.showToast(this@InterstitialAdActivity, "Ad Loaded")
            }

            override fun onAdFailedToLoad(errorCode: Int) {
                if (errorCode == AdRequest.ERROR_CODE_NO_FILL) {
                    Utils.showToast(this@InterstitialAdActivity, "ERROR_CODE_NO_FILL")
                }
            }

            override fun onAdOpened() {
                Log.d("AdManager", "Ad Opened")
                Utils.showToast(this@InterstitialAdActivity, "Ad Opened")
            }

            override fun onAdClicked() {
                Log.d("AdManager", "Ad Clicked")
                Utils.showToast(this@InterstitialAdActivity, "Ad Clicked")
            }

            override fun onAdLeftApplication() {
                Log.d("AdManager", "User Left Application")
                Utils.showToast(this@InterstitialAdActivity, "User Left Application")
            }

            override fun onAdClosed() {
                Utils.showToast(this@InterstitialAdActivity, "Ads is Close")
                if (!mInterstitialAd.isLoaded) {
                    Utils.showToast(this@InterstitialAdActivity, "Ads Loading plz wait")
                    mInterstitialAd.loadAd(AdRequest.Builder().build())
                } else {
                    Utils.showToast(this@InterstitialAdActivity, "Ads is already load")
                }
            }

        }

        loadInterstitialAd.setOnClickListener {

            if (Utils.isNetworkAvailable(this)) {

                if (!mInterstitialAd.isLoaded) {
                    Utils.showToast(this, "Ads Loading plz wait")
                    mInterstitialAd.loadAd(AdRequest.Builder().build())
                } else {
                    Utils.showToast(this, "Ads is already load")
                }

                // Create the InterstitialAd and set it up.
                /* mInterstitialAd = InterstitialAd(this).apply {

                     adUnitId = Utils.interstitialTestId

                     adListener = (object : AdListener() {

                                 override fun onAdLoaded() {
                                     Utils.showToast(this@InterstitialAdActivity, "onAdLoaded()")
                                 }

                                 override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                                     val error =
                                         "domain: ${loadAdError.domain}, code: ${loadAdError.code}, " + "message: ${loadAdError.message}"
                                     Utils.showToast(
                                         this@InterstitialAdActivity,
                                         "onAdFailedToLoad() with error"
                                     )
                                 }

                                 override fun onAdClosed() {
                                     Utils.showToast(this@InterstitialAdActivity, "Ads is Close")
                                 }

                             })
                 }*/

            } else {
                Utils.showToast(this, "NetWork not Connect plz Check your Network")
            }

        }

        showInterstitialAd.setOnClickListener {
            showInterstitial()
        }
    }

    // Show the ad if it's ready. Otherwise toast and restart the game.
    private fun showInterstitial() {
        if (mInterstitialAd.isLoaded) {
            mInterstitialAd.show()
        } else {
            Utils.showToast(this, "Ad wasn't loaded.")
        }
    }
}