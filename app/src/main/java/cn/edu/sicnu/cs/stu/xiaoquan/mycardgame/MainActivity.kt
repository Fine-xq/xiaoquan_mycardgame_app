package cn.edu.sicnu.cs.stu.xiaoquan.mycardgame

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import cn.edu.sicnu.cs.stu.xiaoquan.mycardgame.model.UserInfo

class MainActivity : AppCompatActivity() {

    private lateinit var userInfo: UserInfo

    private lateinit var tvName: TextView
    private lateinit var tvGender: TextView
    private lateinit var tvPhone: TextView
    private lateinit var tvHighScore: TextView

    // 修改信息回调
    private val launcherEdit = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val data = result.data
            val newUser = data?.getSerializableExtra("user") as UserInfo?
            if (newUser != null) {
                userInfo = newUser
                refreshUI()
            }
        }
    }

    // 游戏页面回调 【就是你要加的这一块】
    private val launcherGame = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val data = result.data
            val newUser = data?.getSerializableExtra("user") as UserInfo?
            if (newUser != null) {
                userInfo = newUser
                refreshUI()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userInfo = UserInfo()

        tvName = findViewById(R.id.tv_name)
        tvGender = findViewById(R.id.tv_gender)
        tvPhone = findViewById(R.id.tv_phone)
        tvHighScore = findViewById(R.id.tv_highScore)

        val btnEdit: Button = findViewById(R.id.btn_edit)
        val btnStartGame: Button = findViewById(R.id.btn_start_game)

        refreshUI()

        btnEdit.setOnClickListener {
            val intent = Intent(this, EditInfoActivity::class.java)
            intent.putExtra("user", userInfo)
            launcherEdit.launch(intent)
        }

        // 游戏按钮点击事件，解开注释
        btnStartGame.setOnClickListener {
            val intent = Intent(this, CardGameActivity::class.java)
            intent.putExtra("user", userInfo)
            launcherGame.launch(intent)
        }
    }

    fun refreshUI() {
        tvName.text = "姓名：${userInfo.name}"
        tvGender.text = "性别：${userInfo.gender}"
        tvPhone.text = "电话：${userInfo.phone}"
        tvHighScore.text = "游戏最高分：${userInfo.highScore}"
    }
}
