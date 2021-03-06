package no.sandramoen.suicide.actors

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Table
import no.sandramoen.suicide.base.BaseActor
import no.sandramoen.suicide.base.BaseGame

class Background(x: Float, y: Float, s: Stage? = null, t: Table? = null) : BaseActor(x, y, s, t) {
    val menuBackground = loadTexture(BaseGame.textureAtlas!!.findRegion("menuBackground"))
    val scoreBackground = loadTexture(BaseGame.textureAtlas!!.findRegion("gameOver"))

    private val background0 = loadTexture(BaseGame.textureAtlas!!.findRegion("morning"))
    private val background1 = loadTexture(BaseGame.textureAtlas!!.findRegion("goingToWork"))
    private val background2 = loadTexture(BaseGame.textureAtlas!!.findRegion("comingHome"))
    private val background3 = loadTexture(BaseGame.textureAtlas!!.findRegion("evening"))

    private val backgrounds = arrayOf(background0, background1, background2, background3)
    private var index = 0

    init {
        setSize(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
    }

    fun showNext() {
        setAnimation(backgrounds[index])
        setSize(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())

        if (index >= 3) index = 0
        else index++
    }
}
