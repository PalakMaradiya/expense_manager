package com.example.khatabook.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.khatabook.R
import com.example.khatabook.databinding.ActivityHomeScreenBinding

class HomeScreenActivity : AppCompatActivity() {
    lateinit var  binding: ActivityHomeScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initview()
    }

    private fun initview() {



        binding.nevView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.calender -> {
                    val intent = Intent(this, calanderActivity::class.java)
                    startActivity(intent)
                }

                R.id.Home -> {
                    val intent = Intent(this, HomeScreenActivity::class.java)
                    startActivity(intent)
                }

                R.id.Category -> {
                    val intent = Intent(this, CategoryActivity::class.java)
                    startActivity(intent)
                }

                R.id.payment -> {
                    val intent = Intent(this, PaymentModeActivity::class.java)
                    startActivity(intent)
                }

            }
            true


        }

        binding.imgNaveview.setOnClickListener {

            binding.DrawerLayout.openDrawer(binding.nevView)
        }

        binding.DrawerLayout.setOnClickListener {
            binding.DrawerLayout.closeDrawer(binding.nevView)
        }

        binding.cardViewAddCategory.setOnClickListener {

            val i = Intent(this@HomeScreenActivity, CategoryActivity::class.java)
            startActivity(i)
        }
        binding.imgCalander.setOnClickListener {

            val i = Intent(this@HomeScreenActivity, calanderActivity::class.java)
            startActivity(i)
        }

        binding.imgPremium.setOnClickListener {

            val i = Intent(this@HomeScreenActivity, PreminumActivity::class.java)
            startActivity(i)
        }

        binding.cardviewAddincome.setOnClickListener {

            val i = Intent(this@HomeScreenActivity, IncomeActivity::class.java)
            startActivity(i)
        }

    }
}