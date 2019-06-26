package no.sandramoen.suicide.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import no.sandramoen.suicide.base.BaseActor
import no.sandramoen.suicide.base.BaseGame
import no.sandramoen.suicide.base.BaseScreen

class SplashScreen : BaseScreen() {
    override fun initialize() {
        val background = BaseActor(0f, 0f, s=mainStage)
        background.setAnimation(BaseGame.splashAnim!!)
        background.setSize(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())

        background.addAction(
                Actions.sequence(
                        Actions.fadeOut(0f),
                        Actions.fadeIn(2f),
                        Actions.delay(2f),
                        Actions.fadeOut(2f)
        ))
        background.addAction(Actions.after(Actions.run {
            dispose()
            BaseGame.setActiveScreen(MenuScreen())
        }))
    }

    override fun update(dt: Float) {}

    override fun dispose() {
        super.dispose()
        BaseGame.splashTexture!!.dispose()
    }
}
