package no.sandramoen.suicide.actors

import com.badlogic.gdx.scenes.scene2d.ui.Table
import no.sandramoen.suicide.base.BaseActor

class Background(x: Float, y: Float, s: Table) : BaseActor(x, y, s) {
    var background = loadTexture("graphics/testBackground.png")
}
