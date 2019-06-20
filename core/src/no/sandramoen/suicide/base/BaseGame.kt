package no.sandramoen.suicide.base

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputMultiplexer
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle

abstract class BaseGame : Game() {

    init {
        game = this
    }

    companion object {
        private var game: BaseGame? = null

        var needs: List<String>? = null
        var positiveEmotions: List<String>? = null
        var negativeEmotions: List<String>? = null
        var labelStyle: LabelStyle? = null

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

        // font
        val fontGenerator = FreeTypeFontGenerator(Gdx.files.internal("fonts/OpenSans.ttf"))
        val fontParameters = FreeTypeFontGenerator.FreeTypeFontParameter()
        fontParameters.size = 24
        fontParameters.color = Color.WHITE
        fontParameters.borderWidth = 2f
        fontParameters.borderColor = Color.BLACK
        fontParameters.borderStraight = true
        fontParameters.minFilter = Texture.TextureFilter.Linear
        fontParameters.magFilter = Texture.TextureFilter.Linear

        val customFont = fontGenerator.generateFont(fontParameters)

        labelStyle = LabelStyle()
        labelStyle!!.font = customFont
    }

    private fun readFromFile(fileName: String): List<String> {
        val file = Gdx.files.internal("text/$fileName")
        val text = file.readString()

        val wordsArray = text.split("\r\n")

        val words = mutableListOf<String>()
        for (word in wordsArray) {
            words.add(word)
        }

        return words
    }
}
