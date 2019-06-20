package no.sandramoen.suicide.screens

import com.badlogic.gdx.Input.Keys
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Stack
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup
import no.sandramoen.suicide.base.BaseScreen
import no.sandramoen.suicide.Emotions
import no.sandramoen.suicide.base.BaseGame

class LevelScreen : BaseScreen() {

    private var stopGame = false
    private var timer = 0f
    private val frequency = 1f  // 6f

    private var sumScore = 0
    private var totalNumberOfEmotions = 0f

    private lateinit var emotions: Emotions

    override fun initialize() {

        // scene graph layout
        val labelVerticalGroup = VerticalGroup()
        labelVerticalGroup.debug = true
        labelVerticalGroup.top()
        emotions = Emotions(labelVerticalGroup)

        val animationTable = Table()
        animationTable.debug = true
        animationTable.setFillParent(true)
        val animationLabel = Label("Area reserved for animation", BaseGame.labelStyle)
        animationTable.add(animationLabel)

        val stack = Stack()
        stack.add(animationTable)
        stack.add(labelVerticalGroup)
        stack.setFillParent(true)
        mainStage.addActor(stack)

        val buttonLabel = Label("Area reserved for button", BaseGame.labelStyle)
        uiTable.debug = true
        uiTable.bottom()
        uiTable.add(buttonLabel).width(500f).height(150f)
    }

    override fun update(dt: Float) {
        if (stopGame) {
            println("Score: ${sumScore/totalNumberOfEmotions}")
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
