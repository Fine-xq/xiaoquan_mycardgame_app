package cn.edu.sicnu.cs.stu.xiaoquan.mycardgame

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlin.random.Random

class HelloFragment : Fragment() {
    private lateinit var tvHello: TextView
    private lateinit var btnClick: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_hello, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvHello = view.findViewById(R.id.tvHello)
        btnClick = view.findViewById(R.id.btnClick)

        btnClick.setOnClickListener {
            tvHello.text = "Random: ${Random.nextInt(1000)}"
        }
    }
}
