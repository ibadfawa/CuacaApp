package com.example.cuacaapp

import android.app.SearchManager
import android.content.ComponentName
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cuacaapp.databinding.ActivityMainBinding
import com.example.cuacaapp.view.WeatherItemAdapter
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        binding.lifecycleOwner = this
        binding.listWeatherRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.listWeatherRecyclerView.adapter = WeatherItemAdapter()
        lifecycleScope.launch {
        }
    }

    override fun onRestart() {
        super.onRestart()
        binding.viewModel?.asyncAwait(binding.xyz)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.searchview, menu)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView: SearchView = menu?.findItem(R.id.action_search)?.actionView as SearchView
        val componentName =
            ComponentName(applicationContext, MainActivity::class.java) //getComponentName();
        val info = searchManager.getSearchableInfo(componentName)
        searchView.setSearchableInfo(info)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) binding.viewModel?.searchWeather(query)
                Toast.makeText(this@MainActivity, "OK", Toast.LENGTH_LONG).show()
                return true
            }
        })
        return true
    }

}