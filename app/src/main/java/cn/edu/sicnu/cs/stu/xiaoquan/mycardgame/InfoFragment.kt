package cn.edu.sicnu.cs.stu.xiaoquan.mycardgame

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import cn.edu.sicnu.cs.stu.xiaoquan.mycardgame.model.UserInfo

class InfoFragment : Fragment() {

    private lateinit var tvName: TextView
    private lateinit var tvGender: TextView
    private lateinit var tvPhone: TextView
    private lateinit var tvHighScore: TextView
    private lateinit var btnEdit: Button

    private var user: UserInfo? = null

    private val launcherEdit = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if(result.resultCode == android.app.Activity.RESULT_OK){
            val data = result.data
            val newUser = data?.getSerializableExtra("user") as UserInfo?
            if(newUser != null){
                user = newUser
                refreshUI()
                //把修改后的user回传给MainActivity
                (activity as MainActivity).userInfo = newUser
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvName = view.findViewById(R.id.tv_name)
        tvGender = view.findViewById(R.id.tv_gender)
        tvPhone = view.findViewById(R.id.tv_phone)
        tvHighScore = view.findViewById(R.id.tv_highScore)
        btnEdit = view.findViewById(R.id.btn_edit)

        //从MainActivity拿到user对象
        user = (activity as MainActivity).userInfo
        refreshUI()

        btnEdit.setOnClickListener {
            val intent = Intent(requireContext(), EditInfoActivity::class.java)
            intent.putExtra("user", user)
            launcherEdit.launch(intent)
        }
    }

    fun refreshUI(){
        user?.let {
            tvName.text = "姓名：${it.name}"
            tvGender.text = "性别：${it.gender}"
            tvPhone.text = "电话：${it.phone}"
            tvHighScore.text = "游戏最高分：${it.highScore}"
        }
    }

    companion object{
        fun newInstance() = InfoFragment()
    }
}
