package com.example.myactionbar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu) // menampilkan custom item pada action bar
        return true
    }

    // memasang event listener untuk dijalankan ketika item tersebut dipilih. Listener click pada menu action bar dapat memanfaatkan onOptionsItemSelected()
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            // R.id.menu1 maka yang terjadi adalah fragment pada R.id.fragment_container diganti dengan fragment baru yaitu MenuFragment
            R.id.menu1 -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container,MenuFragment())
                    .addToBackStack(null) // menyimpan fragment
                    .commit()
                return true
            }
            // R.id.menu2 diklik, maka aplikasi akan menjalankan activity baru yaitu MenuActivity
            R.id.menu2 -> {
                val i = Intent(this, MenuActivity::class.java)
                startActivity(i)
                return true
            }
            else -> return true
        }
    }
}
