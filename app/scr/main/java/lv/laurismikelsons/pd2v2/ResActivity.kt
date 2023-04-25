package lv.laurismikelsons.pd2v2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

const val TAG = "MainActivity"
class ResActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_res)

        val loading = findViewById<ProgressBar>(R.id.progress_bar)
        lifecycleScope.launchWhenCreated {
            loading.visibility = View.VISIBLE
            val response = try{
                RetrofitInstance.api.getItemData()
            }catch (e: Exception){
                Log.e(TAG, "Exception: $e")
                loading.visibility = View.GONE
                return@launchWhenCreated
            }
            if (response.isSuccessful && response.body() != null){
                Toast.makeText(this@ResActivity, ""+response.body()?.size+" users loaded!", Toast.LENGTH_SHORT).show()
                val usersRecyclerView = findViewById<RecyclerView>(R.id.users_list_view).apply {
                    adapter = ItemAdapter(){it}
                    layoutManager = LinearLayoutManager(this@ResActivity)
                    setHasFixedSize(true)
                }
                (usersRecyclerView.adapter as ItemAdapter).submitList(response.body())
                loading.visibility = View.GONE
            } else {
                Toast.makeText(this@ResActivity, "Something went wrong!", Toast.LENGTH_SHORT).show()
                loading.visibility = View.GONE
            }
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.nav_map ->{
                val intent = Intent(this,MapsActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_res ->{
                val intent = Intent(this,ResActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}