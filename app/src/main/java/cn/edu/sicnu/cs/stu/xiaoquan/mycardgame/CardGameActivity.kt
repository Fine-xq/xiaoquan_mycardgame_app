//package cn.edu.sicnu.cs.stu.xiaoquan.mycardgame
//
//import android.content.Intent
//import android.content.res.Configuration
//import android.os.Bundle
//import android.widget.Button
//import android.widget.TextView
//import androidx.appcompat.app.AppCompatActivity
//import androidx.recyclerview.widget.GridLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import cn.edu.sicnu.cardgame.CardMatchingGame
//import cn.edu.sicnu.cs.stu.xiaoquan.mycardgame.model.UserInfo
//
//class CardGameActivity : AppCompatActivity() {
//
//    private lateinit var recyclerView: RecyclerView
//    private lateinit var tvScore: TextView
//    private lateinit var btnReset: Button
//    private lateinit var btnBack: Button
//
//    private lateinit var game: CardMatchingGame
//    private lateinit var adapter: CardAdapter
//    private lateinit var user: UserInfo
//    private val totalCount = 24
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_card_game)
//
//        recyclerView = findViewById(R.id.recyclerView)
//        tvScore = findViewById(R.id.tvScore)
//        btnReset = findViewById(R.id.btnReset)
//        btnBack = findViewById(R.id.btnBack)
//
//        user = intent.getSerializableExtra("user") as UserInfo
//        game = CardMatchingGame(totalCount)
//        initRecycler()
//
//        btnReset.setOnClickListener {
//            game.reset()
//            tvScore.text = "Score: ${game.score}"
//            initRecycler()
//        }
//
//
//        btnBack.setOnClickListener {
//            if (game.score > user.highScore) {
//                user.highScore = game.score
//            }
//            val intent = Intent()
//            intent.putExtra("user", user)
//            setResult(RESULT_OK, intent)
//            finish()
//        }
//    }
//
//    private fun initRecycler() {
//        val span = if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) 6 else 4
//        recyclerView.layoutManager = GridLayoutManager(this, span)
//
//        val cardList = mutableListOf<cn.edu.sicnu.cardgame.Card>()
//        for (i in 0 until totalCount) {
//            cardList.add(game.cardAtIndex(i))
//        }
//
//        adapter = CardAdapter(cardList) { pos ->
//            game.chooseCardAtIndex(pos)
//            refreshUI()
//        }
//        recyclerView.adapter = adapter
//    }
//
//    private fun refreshUI() {
//        tvScore.text = "Score: ${game.score}"
//        adapter.notifyDataSetChanged()
//    }
//
//    override fun onConfigurationChanged(newConfig: Configuration) {
//        super.onConfigurationChanged(newConfig)
//        initRecycler()
//    }
//}
