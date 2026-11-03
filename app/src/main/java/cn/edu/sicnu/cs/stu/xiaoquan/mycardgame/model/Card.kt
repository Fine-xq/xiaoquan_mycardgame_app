package cn.edu.sicnu.cardgame

class Card(private var suit: String, private var rank: String, var isChosen:Boolean = false, var isMatched:Boolean = false) {
    companion object {
        val rankStrings= arrayOf("A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K")
        val validSuits = arrayOf("♥", "♦", "♠", "♣")
    }

    override fun toString(): String {
        return suit + rank
    }

    fun match(otherCards: Array<Card>): Int {
        val allSameRank = otherCards.all { it.rank == this.rank }
        val allSameSuit = otherCards.all { it.suit == this.suit }
        return when {
            allSameRank -> 4
            allSameSuit -> 1
            else -> 0
        }
    }
}
