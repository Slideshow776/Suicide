package no.sandramoen.suicide.base

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputMultiplexer
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.NinePatch
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable

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
        var textButtonStyle: TextButtonStyle? = null
        var assetManager: AssetManager? = null

        fun setActiveScreen(s: BaseScreen) {
            game?.setScreen(s)
        }
    }

    override fun create() {
        val im = InputMultiplexer()
        Gdx.input.inputProcessor = im

        assetManager = AssetManager()

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

        textButtonStyle = TextButtonStyle()
        val buttonTex = Texture(Gdx.files.internal("images/button.png"))
        val buttonPatch = NinePatch(buttonTex, 24, 24, 24, 24)
        textButtonStyle!!.up = NinePatchDrawable(buttonPatch)
        textButtonStyle!!.font = customFont
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
