package com.example.chatgpt_book_app

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var mainFragment: View
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize the views and the DrawerLayout
        drawerLayout = findViewById(R.id.drawer_layout)
        mainFragment = findViewById(R.id.fragment)
        actionBarDrawerToggle = ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close)

        // Set the ActionBarDrawerToggle for the DrawerLayout
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        // Set the navigation drawer icon to appear on the action bar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Set the listener for the navigation view item selections
        val navigationView = findViewById<NavigationView>(R.id.navigation_menu)
        navigationView.setNavigationItemSelectedListener { menuItem ->
            // Handle navigation view item clicks here.
            selectDrawerItem(menuItem)
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            true
        } else super.onOptionsItemSelected(item)
    }

    private fun selectDrawerItem(menuItem: MenuItem) {
        var fragment: Fragment? = null
        val fragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()

        // Handle navigation view item clicks
        when (menuItem.itemId) {
            R.id.home -> fragment = LibraryFragment()
            R.id.questions -> fragment = QuestionsFragment()
            R.id.recommendation -> fragment = RecommendationFragment()
            R.id.settings -> fragment = SettingsFragment()
            R.id.about_us -> fragment = AboutUsFragment()
            // Add cases for other menu items
        }

        // Replace the fragment in the main content area
        if (fragment != null) {
            fragmentTransaction.replace(R.id.fragment, fragment)
            fragmentTransaction.commit()
        }

        // Highlight the selected item, update the title, close the drawer, etc.
        menuItem.isChecked = true
        setTitle(menuItem.title)
        drawerLayout.closeDrawers()
    }
}
