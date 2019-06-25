package no.sandramoen.suicide.base

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputMultiplexer
import com.badlogic.gdx.assets.AssetErrorListener
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.NinePatch
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver
import com.badlogic.gdx.assets.AssetDescriptor



abstract class BaseGame : Game(), AssetErrorListener {
    private lateinit var assetManager: AssetManager
    private lateinit var fontGenerator: FreeTypeFontGenerator

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
        var textureAtlas: TextureAtlas? = null

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
        assetManager.setErrorListener(this)

        assetManager.load("images/packed/suicide.pack.atlas", TextureAtlas::class.java)
        val resolver = InternalFileHandleResolver()
        assetManager.setLoader(FreeTypeFontGenerator::class.java, FreeTypeFontGeneratorLoader(resolver))
        assetManager.setLoader(BitmapFont::class.java, ".ttf", FreetypeFontLoader(resolver))
        assetManager.finishLoading();
        textureAtlas = assetManager.get("images/packed/suicide.pack.atlas") // all images are found in this global static variable
        textureAtlas!!.findRegion("button").texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear)

        needs = readFromFile("needs.txt")
        positiveEmotions = readFromFile("positives.txt")
        negativeEmotions = readFromFile("negatives.txt")

        // fonts
        fontGenerator = FreeTypeFontGenerator(Gdx.files.internal("fonts/OpenSans.ttf"))
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
    override fun dispose()    {
        assetManager.dispose()
        fontGenerator.dispose()
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
