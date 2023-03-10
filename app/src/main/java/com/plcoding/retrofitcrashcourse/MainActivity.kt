package com.plcoding.retrofitcrashcourse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.applovin.mediation.MaxAd
import com.applovin.mediation.MaxAdViewAdListener
import com.applovin.mediation.MaxError
import com.applovin.mediation.ads.MaxAdView
import com.applovin.sdk.AppLovinSdkConfiguration
import com.plcoding.retrofitcrashcourse.databinding.ActivityMainBinding
import retrofit2.HttpException
import java.io.IOException

const val TAG = "MainActivity"

class MainActivity : AppCompatActivity(), MaxAdViewAdListener {

    private lateinit var binding: ActivityMainBinding

    private lateinit var todoAdapter: TodoAdapter

    private var adView: MaxAdView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        com.applovin.sdk.AppLovinSdk.getInstance(this).mediationProvider = "max"
        com.applovin.sdk.AppLovinSdk.getInstance( this ).initializeSdk { _: AppLovinSdkConfiguration ->
            createBannerAd()
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()

        lifecycleScope.launchWhenCreated {
            binding.progressBar.isVisible = true
            val cacheInterceptor = CacheInterceptor(this@MainActivity)
            val response = try {

                if (!NetworkUtils.hasNetwork()) {
                    Toast.makeText(applicationContext,"Internet Connection is not Available : Data fetched from cache", Toast.LENGTH_LONG).show()
                    cacheInterceptor.apiCache.getTodos()
                }
                else {
                    RetrofitInstance.api.getTodos()
                }

            } catch(e: IOException) {
                Log.e(TAG, "IOException, you might not have internet connection and no cache exist")
                binding.progressBar.isVisible = false
                return@launchWhenCreated
            } catch (e: HttpException) {
                Log.e(TAG, "HttpException, unexpected response")
                binding.progressBar.isVisible = false
                return@launchWhenCreated
            }
            if(response.isSuccessful && response.body() != null ) {
                todoAdapter.todos = response.body()!!
            } else {
                Log.e(TAG, "Response not successful")
            }
            binding.progressBar.isVisible = false
        }
    }


    private fun setupRecyclerView() = binding.rvTodos.apply {
        todoAdapter = TodoAdapter()
        adapter = todoAdapter
        layoutManager = LinearLayoutManager(this@MainActivity)
    }

    private fun createBannerAd()
    {
        adView = MaxAdView("YOUR_AD_UNIT_ID", this)
        adView?.setListener(this)

        // Stretch to the width of the screen for banners to be fully functional
        val width = ViewGroup.LayoutParams.MATCH_PARENT

        // Banner height on phones and tablets is 50 and 90, respectively
        val heightPx = resources.getDimensionPixelSize(R.dimen.banner_height)

        adView?.layoutParams = FrameLayout.LayoutParams(width, heightPx)

        adView?.setBackgroundColor("#F2F3F4")

        val rootView = findViewById<ViewGroup>(android.R.id.content)
        rootView.addView(adView)

        // Load the ad
        adView?.loadAd()
        val linearLayout = findViewById<LinearLayout>(R.id.adLayout)
        linearLayout.addView(adView)

    }

    override fun onAdLoaded(ad: MaxAd?) {
        adView!!.visibility = View.VISIBLE
        adView!!.startAutoRefresh()    }

    override fun onAdDisplayed(ad: MaxAd?) {
        TODO("Not yet implemented")
    }

    override fun onAdHidden(ad: MaxAd?) {
        TODO("Not yet implemented")
    }

    override fun onAdClicked(ad: MaxAd?) {
        TODO("Not yet implemented")
    }

    override fun onAdLoadFailed(adUnitId: String?, error: MaxError?) {
        TODO("Not yet implemented")
    }

    override fun onAdDisplayFailed(ad: MaxAd?, error: MaxError?) {
        TODO("Not yet implemented")
    }

    override fun onAdExpanded(ad: MaxAd?) {
        TODO("Not yet implemented")
    }

    override fun onAdCollapsed(ad: MaxAd?) {
        TODO("Not yet implemented")
    }

}

private fun MaxAdView.setBackgroundColor(s: String) {

}


