package no.sandramoen.suicide.screens

import com.badlogic.gdx.Input.Keys
import com.badlogic.gdx.scenes.scene2d.Event
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.*
import no.sandramoen.suicide.base.BaseScreen
import no.sandramoen.suicide.base.BaseGame

class LevelScreen : BaseScreen() {

    private var stopGame = false
    private var timer = 0f
    private val frequency = 1f  // 6f

    private var sumScore = 0
    private var totalNumberOfEmotions = 0f
    private lateinit var scoreLabel: Label

    private lateinit var emotions: Emotions

    private lateinit var suicideButton: TextButton

    override fun initialize() {

        // scene graph layout
        val labelVerticalGroup = VerticalGroup()
        /*labelVerticalGroup.debug = true*/
        labelVerticalGroup.top()
        emotions = Emotions(labelVerticalGroup)

        val animationTable = Table()
        /*animationTable.debug = true*/
        animationTable.setFillParent(true)

        val scoreTable = Table()
        scoreTable.setFillParent(true)

        /*uiTable.debug = true*/
        uiTable.bottom()

        val stack = Stack()
        stack.add(animationTable)
        stack.add(labelVerticalGroup)
        stack.add(scoreTable)
        stack.setFillParent(true)
        mainStage.addActor(stack)

        // button
        suicideButton = TextButton("Suicide?", BaseGame.textButtonStyle)
        suicideButton.addListener {e: Event ->
            if(isTouchDownEvent(e)) {
                stopGame = true
            }
            false
        }
        uiTable.add(suicideButton)

        // score label
        scoreLabel = Label("", BaseGame.labelStyle)
        scoreTable.add(scoreLabel)
    }

    override fun update(dt: Float) {
        if (stopGame) {
            scoreLabel.setText("Score: ${sumScore/totalNumberOfEmotions}")
            suicideButton.addAction(Actions.fadeOut(.2f))
        } else {
            generateEmotion(dt)
        }
    }

    override fun keyDown(keycode: Int): Boolean {
        if (keycode == Keys.ENTER) {
            stopGame = true
        }
        return false
    }

    private fun generateEmotion(dt: Float) {
        if (timer >= frequency) {
            sumScore += emotions.randomizeEmotion()
            totalNumberOfEmotions += 1
            timer = 0f
        } else timer += dt
    }
}
