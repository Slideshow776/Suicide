package no.sandramoen.suicide.base

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputMultiplexer

abstract class BaseGame : Game() {

    init {
        game = this
    }

    companion object {
        private var game: BaseGame? = null

        var needs: List<String>? = null
        var positiveEmotions: List<String>? = null
        var negativeEmotions: List<String>? = null

        fun setActiveScreen(s: BaseScreen) {
            game?.setScreen(s)
        }
    }

    override fun create() {
        val im = InputMultiplexer()
        Gdx.input.inputProcessor = im

        needs = readFromFile("needs.txt")
        positiveEmotions = readFromFile("positives.txt")
        negativeEmotions = readFromFile("negatives.txt")
    }

    private fun readFromFile(fileName: String): List<String> {
        val file = Gdx.files.internal("assets/text/$fileName")
        val text = file.readString()

        val wordsArray = text.split("\r\n")

        val words = mutableListOf<String>()
        for (word in wordsArray) {
            words.add(word)
        }

        return words
    }
}
