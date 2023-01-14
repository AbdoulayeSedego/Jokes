package com.example.jokes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jokes.databinding.ActivityMainBinding
import okio.IOException
import retrofit2.HttpException


const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var jokesAdapter: JokesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        setupRecyclerView()

        lifecycleScope.launchWhenCreated {
            val response = try {
                RetrofitInstance.api.getJokes()
            } catch (e: IOException) {
                Log.e(TAG, "IOException, check your internet")
                return@launchWhenCreated
            } catch (e: HttpException){
                Log.e(TAG, "HttpException, unexpected response")
                return@launchWhenCreated
            }
            if(response.isSuccessful && response.body() != null) {
                jokesAdapter.jokes = response.body()!!
            } else {
                Log.e(TAG, "Response unsuccessful")
            }
        }

    }

    private fun setupRecyclerView() = binding.rvItem.apply{
        jokesAdapter = JokesAdapter()
        adapter = jokesAdapter
        layoutManager = LinearLayoutManager(this@MainActivity)
    }
}