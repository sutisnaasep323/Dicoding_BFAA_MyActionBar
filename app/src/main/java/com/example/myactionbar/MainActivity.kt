package com.example.myactionbar

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import android.widget.Toast
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu) // menampilkan custom item pada action bar

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.search).actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        // untuk memberikan hint pada user tentang query search apa yang telah dimasukkan. Hal ini akan memudahkan pengguna untuk memasukkan suatu kata
        searchView.queryHint = resources.getString(R.string.search_hint)
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            /*
            Gunakan method ini ketika search selesai atau OK. ketika disearch akan tampil toast
             */
            override fun onQueryTextSubmit(query: String): Boolean {
                Toast.makeText(this@MainActivity, query, Toast.LENGTH_SHORT).show()
                return true
            }

            /*
            Gunakan method ini untuk merespon tiap perubahan huruf pada searchView. akan dipanggil setiap kali user memasukkan atau mengubah query yang ada pada inputan searchview
             */
            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
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
