package cn.edu.sicnu.cs.stu.xiaoquan.mycardgame

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cn.edu.sicnu.cardgame.Card

class CardAdapter(
    private val cardList: List<Card>,
    private val onItemClick: (Int) -> Unit
) : RecyclerView.Adapter<CardAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvCard: TextView = itemView.findViewById(R.id.tvCardText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val card = cardList[position]
        holder.tvCard.text = if (card.isChosen || card.isMatched) card.toString() else "?"
        holder.itemView.setOnClickListener {
            onItemClick(position)
        }
    }

    override fun getItemCount(): Int = cardList.size
}
