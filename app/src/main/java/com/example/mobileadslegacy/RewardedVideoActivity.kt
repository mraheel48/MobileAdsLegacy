package com.example.mobileadslegacy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.rewarded.RewardItem
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdCallback
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback

class RewardedVideoActivity : AppCompatActivity() {

    private lateinit var rewardedLoad: Button
    private lateinit var rewardedShow: Button

    private var mIsLoading = false
    private lateinit var mRewardedAd: RewardedAd

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rewarded_video)

        MobileAds.initialize(this) {}

        rewardedLoad = findViewById(R.id.rewardedLoad)
        rewardedShow = findViewById(R.id.rewardedShow)

        rewardedLoad.setOnClickListener {
            if (Utils.isNetworkAvailable(this)) {
                Utils.showToast(this, "Ads Loading plz wait")
                loadRewardedAd()

            } else {
                Utils.showToast(this, "NetWork not Connect plz Check your Network")
            }
        }

        rewardedShow.setOnClickListener {
            showRewardedVideo()
        }
    }

    private fun loadRewardedAd() {
        if (!(::mRewardedAd.isInitialized) || !mRewardedAd.isLoaded) {
            mIsLoading = true
            mRewardedAd = RewardedAd(this, Utils.rewardedAdTestId)
            mRewardedAd.loadAd(AdRequest.Builder().build(),
                object : RewardedAdLoadCallback() {
                    override fun onRewardedAdLoaded() {
                        mIsLoading = false
                        Utils.showToast(this@RewardedVideoActivity, "onRewardedAdLoaded")
                    }

                    override fun onRewardedAdFailedToLoad(loadAdError: LoadAdError) {
                        mIsLoading = false
                        Utils.showToast(this@RewardedVideoActivity, "onRewardedAdFailedToLoad")
                    }
                }
            )
        }
    }

    private fun showRewardedVideo() {

        if (mRewardedAd.isLoaded) {
            mRewardedAd.show(
                this,
                object : RewardedAdCallback() {
                    override fun onUserEarnedReward(rewardItem: RewardItem) {
                        Utils.showToast(this@RewardedVideoActivity, "onUserEarnedReward")
                    }

                    override fun onRewardedAdClosed() {
                        Utils.showToast(this@RewardedVideoActivity, "onRewardedAdClosed")
                        loadRewardedAd()
                    }

                    override fun onRewardedAdFailedToShow(adError: AdError) {
                        Utils.showToast(this@RewardedVideoActivity, "onRewardedAdFailedToShow")

                    }

                    override fun onRewardedAdOpened() {
                        Utils.showToast(this@RewardedVideoActivity, "onRewardedAdOpened")
                    }
                }
            )
        }else{
            Utils.showToast(this, "Ads is not Loading plz wait")
            loadRewardedAd()
        }
    }

}