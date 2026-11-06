package cn.edu.sicnu.cs.stu.xiaoquan.mycardgame

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.fragment.app.Fragment
import cn.edu.sicnu.cs.stu.xiaoquan.mycardgame.model.UserInfo

class MainActivity : AppCompatActivity() {

    lateinit var userInfo: UserInfo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        userInfo = UserInfo()

        val bottomNav: BottomNavigationView = findViewById(R.id.bottomNV)

        //默认打开信息页InfoFragment
        loadFragment(InfoFragment.newInstance())

        bottomNav.setOnItemSelectedListener { item ->
            val fragment: Fragment = when(item.itemId){
                R.id.info -> InfoFragment.newInstance()
                R.id.game -> GameFragment()
                R.id.hello -> HelloFragment()
                else -> InfoFragment.newInstance()
            }
            loadFragment(fragment)
            true
        }
    }

    private fun loadFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayout, fragment)
            .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_about -> {
                AlertDialog.Builder(this)
                    .setTitle(getString(R.string.about_title))
                    .setMessage(getString(R.string.about_msg))
                    .setPositiveButton(getString(R.string.btn_ok), null)
                    .show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
