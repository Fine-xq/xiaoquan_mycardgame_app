package cn.edu.sicnu.cs.stu.xiaoquan.mycardgame

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.GridView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import cn.edu.sicnu.cardgame.CardMatchingGame
import cn.edu.sicnu.cs.stu.xiaoquan.mycardgame.model.UserInfo

class CardGameActivity : AppCompatActivity() {

    private lateinit var tvScore: TextView
    private lateinit var gridView: GridView
    private lateinit var btnReset: Button
    private lateinit var btnBack: Button

    private lateinit var user: UserInfo
    private lateinit var game: CardMatchingGame

    private val cardTotal = 16

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_game)

        tvScore = findViewById(R.id.tvScore)
        gridView = findViewById(R.id.gridView)
        btnReset = findViewById(R.id.btnReset)
        btnBack = findViewById(R.id.btnBack)

        user = intent.getSerializableExtra("user") as UserInfo
        initGame()

        gridView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            game.chooseCardAtIndex(position)
            refreshUI()
        }

        btnReset.setOnClickListener {
            initGame()
        }

        btnBack.setOnClickListener {
            if (game.score > user.highScore) {
                user.highScore = game.score
            }
            val intent = Intent()
            intent.putExtra("user", user)
            setResult(RESULT_OK, intent)
            finish()
        }
    }

    private fun initGame() {
        // 传入16张卡牌，使用你写好的CardMatchingGame逻辑
        game = CardMatchingGame(cardTotal)
        refreshUI()
    }

    private fun refreshUI() {
        tvScore.text = "Score: ${game.score}"
        gridView.adapter = cardAdapter()
    }

    private fun cardAdapter(): BaseAdapter {
        return object : BaseAdapter() {
            override fun getCount(): Int = cardTotal
            override fun getItem(position: Int): Any = game.cardAtIndex(position)
            override fun getItemId(position: Int): Long = position.toLong()

            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = convertView ?: layoutInflater.inflate(R.layout.item_card, parent, false)
                val tvCardText = view.findViewById<TextView>(R.id.tvCardText)
                val card = game.cardAtIndex(position)

                when {
                    card.isMatched -> {
                        view.setBackgroundColor(Color.LTGRAY)
                        tvCardText.setTextColor(Color.BLACK)
                        tvCardText.text = card.toString()
                    }
                    card.isChosen -> {
                        view.setBackgroundColor(Color.WHITE)
                        tvCardText.setTextColor(Color.BLACK)
                        tvCardText.text = card.toString()
                    }
                    else -> {
                        view.setBackgroundColor(Color.parseColor("#992222"))
                        tvCardText.setTextColor(Color.WHITE)
                        tvCardText.text = "?"
                    }
                }
                return view
            }
        }
    }
}
