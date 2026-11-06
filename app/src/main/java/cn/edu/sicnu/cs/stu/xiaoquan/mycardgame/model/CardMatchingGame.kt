package cn.edu.sicnu.cardgame
import java.io.Serializable

class CardMatchingGame(val count: Int) : Serializable {
    var score = 0
        private set
    private var cardCount = count
    private val cards: MutableList<Card>

    init {
        cards = mutableListOf()
        initCards()
    }

    private fun initCards() {
        val deck = Deck()
        cards.clear()
        for (i in 1..cardCount) {
            val card: Card? = deck.drawRandomCard()
            if (card != null) {
                cards.add(card)
            }
        }
    }

    fun reset() {
        score = 0
        initCards()
    }

    fun cardAtIndex(index: Int): Card {
        return cards[index]
    }

    val MISMATCH_PENALTY = 2
    val MATCH_BONUS = 4
    val COST_TO_CHOOSE = 1

    fun chooseCardAtIndex(index: Int) {
        val card = cardAtIndex(index)
        if (card.isMatched) return

        if (card.isChosen) {
            card.isChosen = false
            return
        }

        val chosenCards = cards.filter { it.isChosen && !it.isMatched }

        if (chosenCards.size == 2) {
            val matchScore = card.match(chosenCards.toTypedArray())
            score -= COST_TO_CHOOSE
            if (matchScore > 0) {
                score += matchScore * MATCH_BONUS
                card.isMatched = true
                chosenCards[0].isMatched = true
                chosenCards[1].isMatched = true
            } else {
                score -= MISMATCH_PENALTY
                chosenCards[0].isChosen = false
                chosenCards[1].isChosen = false
            }
        } else {
            score -= COST_TO_CHOOSE
        }
        card.isChosen = true
    }
}
