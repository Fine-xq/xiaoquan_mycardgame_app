package cn.edu.sicnu.cs.stu.xiaoquan.mycardgame

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.edu.sicnu.cardgame.CardMatchingGame
import cn.edu.sicnu.cs.stu.xiaoquan.mycardgame.model.UserInfo
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

class GameFragment : Fragment() {
    private lateinit var tvScore: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var btnReset: Button
    private lateinit var adapter: CardAdapter
    private var game: CardMatchingGame? = null
    private val fileName = "game_save.dat"

    // 用户信息对象，用于更新最高分
    private var user: UserInfo? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // 从宿主MainActivity拿到用户信息
        user = (activity as MainActivity).userInfo

        tvScore = view.findViewById(R.id.tvScore)
        recyclerView = view.findViewById(R.id.recyclerView)
        btnReset = view.findViewById(R.id.btnReset)

        recyclerView.layoutManager = GridLayoutManager(requireContext(), 4)
        loadData()

        btnReset.setOnClickListener {
            game?.reset()
            refreshUI()
        }
    }

    private fun refreshUI() {
        game?.let {
            // 如果当前得分大于历史最高分，更新最高分
            user?.let { u ->
                if (it.score > u.highScore) {
                    u.highScore = it.score
                }
            }
            tvScore.text = "Score: ${it.score}"
            adapter = CardAdapter(it) { pos ->
                it.chooseCardAtIndex(pos)
                refreshUI()
            }
            recyclerView.adapter = adapter
        }
    }

    private fun loadData() {
        runCatching {
            val fis: FileInputStream = requireContext().openFileInput(fileName)
            val ois = ObjectInputStream(fis)
            val saved = ois.readObject() as CardMatchingGame
            ois.close()
            game = saved
        }.onFailure {
            // 读取存档失败，新建24张卡牌游戏
            game = CardMatchingGame(24)
        }
        refreshUI()
    }

    private fun saveData() {
        runCatching {
            val fos: FileOutputStream = requireContext().openFileOutput(fileName, Context.MODE_PRIVATE)
            val oos = ObjectOutputStream(fos)
            oos.writeObject(game)
            oos.close()
        }
    }

    // Fragment退到后台时保存游戏对象
    override fun onStop() {
        super.onStop()
        saveData()
    }
}
