package com.example.mobileadslegacy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.widget.Button
import android.widget.FrameLayout
import com.google.android.gms.ads.*

class AdaptiveBanners : AppCompatActivity() {

    private lateinit var adView: AdView
    lateinit var ad_view_container: FrameLayout
    lateinit var loadBanner: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adaptive_banners)
        // Initialize the Mobile Ads SDK.
        MobileAds.initialize(this) { }

        adView = AdView(this)

        ad_view_container = findViewById(R.id.ad_view_container)
        loadBanner = findViewById(R.id.loadBanner)

        loadBanner.setOnClickListener {
            if (Utils.isNetworkAvailable(this)) {
                Utils.showToast(this, "Ads Loading plz wait")
                ad_view_container.post { loadBanner() }
            } else {
                Utils.showToast(this, "NetWork not Connect plz Check your Network")
            }
        }

    }

    /** Called when leaving the activity  */
    public override fun onPause() {
        adView.pause()
        super.onPause()
    }

    /** Called when returning to the activity  */
    public override fun onResume() {
        super.onResume()
        adView.resume()
    }

    /** Called before the activity is destroyed  */
    public override fun onDestroy() {
        adView.destroy()
        super.onDestroy()
    }


    // Determine the screen width (less decorations) to use for the ad width.
    // If the ad hasn't been laid out, default to the full screen width.
    @Suppress("DEPRECATION")
    private val adSize: AdSize
        get() {
            val display = windowManager.defaultDisplay
            val outMetrics = DisplayMetrics()
            display.getMetrics(outMetrics)
            val density = outMetrics.density

            var adWidthPixels = ad_view_container.width.toFloat()
            if (adWidthPixels == 0f) {
                adWidthPixels = outMetrics.widthPixels.toFloat()
            }

            val adWidth = (adWidthPixels / density).toInt()
            return AdSize.getCurrentOrientationBannerAdSizeWithWidth(this, adWidth)
        }

    private fun loadBanner() {
        adView.adUnitId = Utils.bannerTestId
        val adSize = adSize
        adView.adSize = adSize
        ad_view_container.addView(adView)
        val adRequest = AdRequest.Builder().build()
        // Start loading the ad in the background.
        try {
            adView.loadAd(adRequest)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }


}