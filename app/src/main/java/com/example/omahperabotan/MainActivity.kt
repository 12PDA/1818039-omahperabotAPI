package com.example.omahperabotan

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.omahperabotan.activity.LoginActivity
import com.example.omahperabotan.activity.MasukActivity
import com.example.omahperabotan.fragment.HomeFragment
import com.example.omahperabotan.fragment.ProductFragment
import com.example.omahperabotan.fragment.ProfileFragment
import com.example.omahperabotan.helper.SharedPref
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    val fragmentberanda : Fragment = HomeFragment()
    val fragmentproduk  : Fragment = ProductFragment()
    val fragmentprofil  : Fragment = ProfileFragment()
    val fm       : FragmentManager = supportFragmentManager
    //fragment u menandai fragment default
    var active         : Fragment = fragmentberanda

    private lateinit var menu : Menu
    private lateinit var menuItem : MenuItem
    private lateinit var bottomNavigationView : BottomNavigationView

    private var statusLogin = false
    private lateinit var s:SharedPref


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpBottomNav()

        s = SharedPref(this)

    }

    fun setUpBottomNav(){
        fm.beginTransaction().add(R.id.container, fragmentberanda).show(fragmentberanda).commit()
        fm.beginTransaction().add(R.id.container, fragmentproduk).hide(fragmentproduk).commit()
        fm.beginTransaction().add(R.id.container, fragmentprofil).hide(fragmentprofil).commit()

        bottomNavigationView = findViewById(R.id.nav_view)
        menu = bottomNavigationView.menu
        menuItem = menu.getItem(0) //index 0 = fragmentHome
        menuItem.isChecked = true

        bottomNavigationView.setOnItemSelectedListener { item ->
            when(item.itemId){
                R.id.navigation_home->{
                    changeFragment(0,fragmentberanda)
                }
                R.id.navigation_product->{
                    changeFragment(1,fragmentproduk)
                }
                R.id.navigation_profile->{
                    if (s.getStatusLogin()) {
                        changeFragment(2, fragmentprofil)
                    } else{
                        startActivity(Intent(this, MasukActivity::class.java))
                    }
                }
            }

            false
        }
    }

    fun changeFragment(int: Int, fragment: Fragment){
        menuItem = menu.getItem(int)
        menuItem.isChecked = true
        //menampilkan fragment
        fm.beginTransaction().hide(active).show(fragment).commit()
        active = fragment
    }
}