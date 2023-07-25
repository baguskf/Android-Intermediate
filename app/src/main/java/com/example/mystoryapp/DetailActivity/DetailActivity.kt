package com.example.mystoryapp.DetailActivity

import android.content.ContentValues.TAG
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.mystoryapp.R
import com.example.mystoryapp.UserPreference
import com.example.mystoryapp.ViewModelFactory
import com.example.mystoryapp.api.ApiConfig
import com.example.mystoryapp.api.ResponseDetailStory
import com.example.mystoryapp.api.Story
import com.example.mystoryapp.databinding.ActivityDetailBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "Setting")
class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var detailViewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.setDisplayShowHomeEnabled(true)
        setViewModel()

    }

    private fun setViewModel(){
        detailViewModel = ViewModelProvider(
            this,
            ViewModelFactory(UserPreference.getInstance(dataStore))
        )[DetailViewModel::class.java]

        detailViewModel.getUserData().observe(this) { userData ->
            val tokken = userData.token
            println("ini ada token sih : $tokken")
            getDetailListUser(tokken)
        }


    }


    private fun getDetailListUser(token: String){
        val getId = intent.getStringExtra("Data")
        val client = getId?.let {
            ApiConfig.getApiService().getDetailStory("Bearer " + token,
                it
            )
        }
        isLoading(true)
        if (client != null) {
            client.enqueue(object : Callback<ResponseDetailStory> {
                override fun onResponse(call: Call<ResponseDetailStory>, response: Response<ResponseDetailStory>) {
                    isLoading(false)
                    if(response.isSuccessful){
                        val responsBody = response.body()
                        if (responsBody!= null && !responsBody.error){
                            setUserData(responsBody.story)
                        }
                    } else {
                        Toast.makeText(this@DetailActivity,getString(R.string.responEror) , Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ResponseDetailStory>, t: Throwable) {
                    isLoading(false)
                    Log.e(TAG, "onFailure: ${t.message.toString()}")
                }
            })
        }
    }

    private fun setUserData(listUser: Story){
        binding.apply {
            tvname.text = listUser.name
            tvdesc.text = listUser.description
            Glide.with(this@DetailActivity)
                .load(listUser.photoUrl)
                .into(imgDetail)
        }

    }



    private fun isLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}