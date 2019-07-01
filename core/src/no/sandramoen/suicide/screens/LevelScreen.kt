package no.sandramoen.suicide.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input.Keys
import com.badlogic.gdx.scenes.scene2d.Event
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.*
import no.sandramoen.suicide.actors.Background
import no.sandramoen.suicide.base.BaseActor
import no.sandramoen.suicide.base.BaseScreen
import no.sandramoen.suicide.base.BaseGame

class LevelScreen : BaseScreen() {

    private var stopGame = false
    private var timer = 0f
    private val frequency = 5f

    private var sumScore = 0
    private var totalNumberOfEmotions = 0f
    private lateinit var scoreLabel: Label

    private lateinit var emotions: Emotions

    private lateinit var suicideButton: TextButton
    private lateinit var restartButton: TextButton
    private lateinit var returnToMenuButton: TextButton

    private lateinit var background: Background

    private lateinit var scoreTable: Table

    override fun initialize() {
        // scene graph layout
        val labelVerticalGroup = VerticalGroup()
        labelVerticalGroup.top()
        emotions = Emotions(labelVerticalGroup)

        // score table
        scoreTable = Table()
        scoreTable.setFillParent(true)

        scoreLabel = Label("", BaseGame.labelStyle)
        scoreTable.add(scoreLabel)

        restartButton = TextButton("Restart", BaseGame.textButtonStyle)
        restartButton.addListener { e: Event ->
            if (isTouchDownEvent(e)) {
                disableAndFadeOut()
                background.addAction(Actions.after(Actions.run {
                    BaseGame.setActiveScreen(LevelScreen())
                }))
            }
            false
        }

        returnToMenuButton = TextButton("Return to Menu", BaseGame.textButtonStyle)
        returnToMenuButton.addListener { e: Event ->
            if (isTouchDownEvent(e)) {
                disableAndFadeOut()
                background.addAction(Actions.after(Actions.run {
                    BaseGame.setActiveScreen(MenuScreen())
                }))
            }
            false
        }

        scoreTable.isVisible = false
        scoreTable.row()
        scoreTable.add(returnToMenuButton)
        scoreTable.row()
        scoreTable.add(restartButton)

        // ui table
        uiTable.bottom()

        suicideButton = TextButton("Suicide?", BaseGame.textButtonStyle)
        suicideButton.addListener {e: Event ->
            if(isTouchDownEvent(e)) {
                stopGame = true
                scoreLabel.setText("Score: ${sumScore/totalNumberOfEmotions}")
                scoreTable.isVisible = true
                scoreTable.addAction(
                        Actions.sequence(Actions.fadeOut(0f),Actions.fadeIn(1f))
                )
            }
            false
        }
        uiTable.add(suicideButton)

        val animationTable = Table()
        animationTable.setFillParent(true)

        val stack = Stack()
        stack.add(animationTable)
        stack.add(labelVerticalGroup)
        stack.add(scoreTable)
        stack.setFillParent(true)
        mainStage.addActor(stack)

        // actors
        background = Background(0f, 0f, t=animationTable)
        BaseActor.setWorldBounds(background)
        background.showNext()

        // fade in
        val duration = 1f
        background.addAction(Actions.sequence(Actions.fadeOut(0f), Actions.fadeIn(duration)))
        suicideButton.addAction(Actions.sequence(Actions.fadeOut(0f), Actions.fadeIn(duration)))
    }

    override fun update(dt: Float) {
        if (stopGame) {

            background.setAnimation(background.scoreBackground)
            background.setSize(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())

            suicideButton.addAction(Actions.fadeOut(.2f))
        } else {
            nextScene(dt)
        }
    }

    override fun keyDown(keycode: Int): Boolean {
        if (keycode == Keys.ENTER) {
            stopGame = true
        }
        return false
    }

    private fun nextScene(dt: Float) {
        if (timer >= frequency) {
            sumScore += emotions.randomizeEmotion()
            totalNumberOfEmotions += 1

            background.showNext()

            timer = 0f
        } else timer += dt
    }

    private fun disableAndFadeOut() {
        suicideButton.isDisabled = true
        returnToMenuButton.isDisabled = true
        restartButton.isDisabled = true

        val duration = 1f
        background.addAction(Actions.fadeOut(duration))
        scoreTable.addAction(Actions.fadeOut(duration))
    }
}
