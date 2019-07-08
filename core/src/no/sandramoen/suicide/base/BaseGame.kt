package no.sandramoen.suicide.base

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputMultiplexer
import com.badlogic.gdx.assets.AssetErrorListener
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.Texture.TextureFilter
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver
import com.badlogic.gdx.assets.AssetDescriptor
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.graphics.g2d.*

abstract class BaseGame : Game(), AssetErrorListener {
    private lateinit var fontGenerator: FreeTypeFontGenerator
    private lateinit var customFont: BitmapFont
    private lateinit var biggerCustomFont: BitmapFont

    init {
        game = this
    }

    companion object {
        private var game: BaseGame? = null

        var needs: List<String>? = null
        var positiveEmotions: List<String>? = null
        var negativeEmotions: List<String>? = null
        var labelStyle: LabelStyle? = null
        var biggerLabelStyle: LabelStyle? = null
        var textButtonStyle: TextButtonStyle? = null
        var textureAtlas: TextureAtlas? = null
        var splashTexture: Texture? = null
        var splashAnim: Animation<TextureRegion>? = null
        var assetManager: AssetManager? = null

        fun setActiveScreen(s: BaseScreen) {
            game?.setScreen(s)
        }
    }

    override fun create() {
        // discrete input
        val im = InputMultiplexer()
        Gdx.input.inputProcessor = im

        // asset manager
        assetManager = AssetManager()
        assetManager!!.setErrorListener(this)

        assetManager!!.load("audio/LEVEL__bradovic__piano.wav", Music::class.java)
        assetManager!!.load("audio/MENU__frankum__sadnes-piano-loop.mp3", Music::class.java)
        assetManager!!.load("audio/SCORE__meral__pianokeys-meral.wav", Music::class.java)

        assetManager!!.load("images/included/packed/suicide.pack.atlas", TextureAtlas::class.java)
        val resolver = InternalFileHandleResolver()
        assetManager!!.setLoader(FreeTypeFontGenerator::class.java, FreeTypeFontGeneratorLoader(resolver))
        assetManager!!.setLoader(BitmapFont::class.java, ".ttf", FreetypeFontLoader(resolver))

        assetManager!!.finishLoading()
        textureAtlas = assetManager!!.get("images/included/packed/suicide.pack.atlas") // all images are found in this global static variable

        needs = readFromFile("needs.txt")
        positiveEmotions = readFromFile("positives.txt")
        negativeEmotions = readFromFile("negatives.txt")

        // images that are excluded from the asset manager
        splashTexture = Texture(Gdx.files.internal("images/excluded/splash_image.jpg"))
        splashTexture!!.setFilter(TextureFilter.Nearest, TextureFilter.Nearest)
        splashAnim = Animation(1f, TextureRegion(splashTexture))

        // fonts
        fontGenerator = FreeTypeFontGenerator(Gdx.files.internal("fonts/OpenSans.ttf"))
        val fontParameters = FreeTypeFontGenerator.FreeTypeFontParameter()
        fontParameters.size = (.06f * Gdx.graphics.height).toInt() // If the resolutions height is 1440 then the font size becomes 86
        fontParameters.color = Color.WHITE
        fontParameters.borderWidth = 2f
        fontParameters.borderColor = Color.BLACK
        fontParameters.borderStraight = true
        fontParameters.minFilter = TextureFilter.Linear
        fontParameters.magFilter = TextureFilter.Linear

        val biggerFontParameters = FreeTypeFontGenerator.FreeTypeFontParameter()
        biggerFontParameters.size = (.1f * Gdx.graphics.height).toInt() // If the resolutions height is 1440 then the font size becomes 86
        biggerFontParameters.color = Color.SALMON
        biggerFontParameters.borderWidth = 2f
        biggerFontParameters.borderColor = Color.BLACK
        biggerFontParameters.borderStraight = true
        biggerFontParameters.minFilter = TextureFilter.Linear
        biggerFontParameters.magFilter = TextureFilter.Linear

        customFont = fontGenerator.generateFont(fontParameters)
        biggerCustomFont = fontGenerator.generateFont(biggerFontParameters)
        fontGenerator.dispose()

        // labels
        labelStyle = LabelStyle()
        labelStyle!!.font = customFont

        biggerLabelStyle = LabelStyle()
        biggerLabelStyle!!.font = biggerCustomFont

        // buttons
        textButtonStyle = TextButtonStyle()
        val buttonTex = textureAtlas!!.findRegion("button")
        val buttonPatch = NinePatch(buttonTex, 24, 24, 24, 24)
        textButtonStyle!!.up = NinePatchDrawable(buttonPatch)
        textButtonStyle!!.font = customFont
    }

    override fun error(asset: AssetDescriptor<*>, throwable: Throwable) {
        Gdx.app.error("BaseGame.kt", "Could not load asset: " + asset.fileName, throwable)
    }

    @Override
    override fun dispose() {
        super.dispose()
        assetManager!!.dispose()
        customFont.dispose()
        biggerCustomFont.dispose()
        splashTexture!!.dispose()
        textureAtlas!!.dispose()
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
