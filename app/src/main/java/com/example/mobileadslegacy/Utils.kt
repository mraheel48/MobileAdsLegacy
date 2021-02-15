package com.example.mobileadslegacy

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.widget.Toast

object Utils {

    const val bannerTestId = "ca-app-pub-3940256099942544/6300978111"
    const val interstitialTestId = "ca-app-pub-3940256099942544/1033173712"
    const val nativeAdvancedTestId = "ca-app-pub-3940256099942544/2247696110"
    const val rewardedAdTestId = "ca-app-pub-3940256099942544/5224354917"

    //**********************Method for checking network************************************//
    @Suppress("DEPRECATION")
    fun isNetworkAvailable(context: Context?): Boolean {
        val connectivityManager =
            (context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
         val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

    fun showToast(context: Context, message: String) {
        val activity = context as Activity
        activity.runOnUiThread { //show your Toast here..
            Toast.makeText(context.applicationContext, message, Toast.LENGTH_SHORT).show()
        }
    }


}