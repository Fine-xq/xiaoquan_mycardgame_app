package cn.edu.sicnu.cs.stu.xiaoquan.mycardgame.model

import java.io.Serializable

class UserInfo : Serializable {
    var name: String = ""
    var gender: String = ""
    var phone: String = ""
    var highScore: Int = 0
}
