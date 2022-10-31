package com.plcoding.retrofitcrashcourse

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.http.HttpResponseCache
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.plcoding.retrofitcrashcourse.databinding.ActivityMainBinding
import retrofit2.HttpException
import java.io.IOException

const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()

        lifecycleScope.launchWhenCreated {
            binding.progressBar.isVisible = true
            var cacheInterceptor = CacheInterceptor(this@MainActivity)
            val response = try {
                if (!NetworkUtils.hasNetwork()) {
                    Toast.makeText(getApplicationContext(),"Internet Connection is not Available : Data fetched from cache", Toast.LENGTH_LONG).show()
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

}