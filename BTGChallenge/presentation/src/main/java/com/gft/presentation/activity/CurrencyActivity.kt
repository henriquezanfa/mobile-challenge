package com.gft.presentation.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.gft.presentation.R
import com.gft.presentation.databinding.ActivityCurrencyBinding
import com.gft.presentation.entities.Status
import com.gft.presentation.viewmodel.CurrencyViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class CurrencyActivity : AppCompatActivity() {

    private val viewModel: CurrencyViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityCurrencyBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_currency)
        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        viewModel.getLabels()
    }

    override fun onStart() {
        super.onStart()

        viewModel.getLabelsLiveData().observe(this, Observer {
            when (it?.responseType) {
                Status.ERROR -> {
                    Log.i("ERROR", it.error?.message)
                }
                Status.LOADING -> {
                    Log.i("LOADING", "$it")
                }
                Status.SUCCESSFUL -> {
                    Log.i("SUCCESSFUL", "$it")
                }
            }
        })
    }
}
