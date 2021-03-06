package no.sandramoen.suicide.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input.Keys
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.scenes.scene2d.Event
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputEvent.Type
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import no.sandramoen.suicide.actors.Background
import no.sandramoen.suicide.base.BaseGame
import no.sandramoen.suicide.base.BaseScreen

class MenuScreen : BaseScreen() {
    private lateinit var background: Background
    private lateinit var title: Label
    private lateinit var startButton: TextButton
    private lateinit var quitButton: TextButton
    private lateinit var music: Music

    override fun initialize() {

        // assets
        background = Background(0f, 0f, s=mainStage)
        background.menuBackground

        title = Label("Suicide?", BaseGame.biggerLabelStyle)

        startButton = TextButton("Start", BaseGame.textButtonStyle)
        startButton.addListener { e: Event ->
            val ie = e as InputEvent
            if (ie.type == Type.touchDown)
                levelScreen()
            false
        }

        quitButton = TextButton("Quit", BaseGame.textButtonStyle)
        quitButton.addListener { e: Event ->
            val ie = e as InputEvent
            if (ie.type == Type.touchDown)
                exit()
            false
        }

        music = BaseGame.assetManager!!.get("audio/MENU__frankum__sadnes-piano-loop.mp3", Music::class.java)
        music.isLooping = true
        music.play()

        // scene graph
        uiTable.add(title).colspan(2)
        uiTable.row()
        uiTable.add(startButton)
        uiTable.add(quitButton)

        // fade in
        val duration = 1f
        background.addAction(Actions.sequence(Actions.fadeOut(0f), Actions.fadeIn(duration)))
        title.addAction(Actions.sequence(Actions.fadeOut(0f), Actions.fadeIn(duration)))
        startButton.addAction(Actions.sequence(Actions.fadeOut(0f), Actions.fadeIn(duration)))
        quitButton.addAction(Actions.sequence(Actions.fadeOut(0f), Actions.fadeIn(duration)))
    }

    override fun update(dt: Float) {}

    override fun keyDown(keycode: Int): Boolean {
        if (Gdx.input.isKeyPressed(Keys.ENTER))
            levelScreen()
        if (Gdx.input.isKeyPressed(Keys.ESCAPE))
            exit()
        return false
    }

    private fun levelScreen() {
        disableAndFadeOut(1f)
        background.addAction(Actions.after(Actions.run {
            BaseGame.setActiveScreen(LevelScreen())
        }))
    }

    private fun exit() {
        disableAndFadeOut(.5f)
        background.addAction(Actions.after(Actions.run {
            Gdx.app.exit()
        }))
    }

    private fun disableAndFadeOut(duration: Float) {
        music.stop()
        startButton.isDisabled = true
        quitButton.isDisabled = true
        background.addAction(Actions.fadeOut(duration))
        title.addAction(Actions.fadeOut(duration))
        startButton.addAction(Actions.fadeOut(duration))
        quitButton.addAction(Actions.fadeOut(duration))
    }
}
