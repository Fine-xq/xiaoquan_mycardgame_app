package cn.edu.sicnu.cs.stu.xiaoquan.mycardgame

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import cn.edu.sicnu.cs.stu.xiaoquan.mycardgame.model.UserInfo

class EditInfoActivity : AppCompatActivity() {
    private lateinit var etName: EditText
    private lateinit var etGender: EditText
    private lateinit var etPhone: EditText
    private lateinit var user: UserInfo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_info)

        etName = findViewById(R.id.et_name)
        etGender = findViewById(R.id.et_gender)
        etPhone = findViewById(R.id.et_phone)
        val btnSave: Button = findViewById(R.id.btn_save)

        user = intent.getSerializableExtra("user") as UserInfo

        etName.setText(user.name)
        etGender.setText(user.gender)
        etPhone.setText(user.phone)

        btnSave.setOnClickListener {
            user.name = etName.text.toString()
            user.gender = etGender.text.toString()
            user.phone = etPhone.text.toString()

            val intent = Intent()
            intent.putExtra("user", user)
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}
