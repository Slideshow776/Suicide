package no.sandramoen.suicide.actors

import com.badlogic.gdx.scenes.scene2d.ui.Table
import no.sandramoen.suicide.base.BaseActor
import no.sandramoen.suicide.base.BaseGame

class Background(x: Float, y: Float, s: Table) : BaseActor(x, y, s) {
    /*var background = loadTexture("images/testBackground.png")*/
    var background = test(BaseGame.textureAtlas!!.findRegion("testBackground"))
}
