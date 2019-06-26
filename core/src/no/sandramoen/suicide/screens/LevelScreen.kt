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
    private val frequency = 5f  // 6f

    private var sumScore = 0
    private var totalNumberOfEmotions = 0f
    private lateinit var scoreLabel: Label

    private lateinit var emotions: Emotions

    private lateinit var suicideButton: TextButton

    private lateinit var background: Background

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

        // actors
        background = Background(0f, 0f, t=animationTable)
        BaseActor.setWorldBounds(background)
        background.showNext()

        // fade in
        val duration = 1f
        background.addAction(Actions.sequence(Actions.fadeOut(0f), Actions.fadeIn(duration)))
        scoreLabel.addAction(Actions.sequence(Actions.fadeOut(0f), Actions.fadeIn(duration)))
    }

    override fun update(dt: Float) {
        if (stopGame) {
            scoreLabel.setText("Score: ${sumScore/totalNumberOfEmotions}")

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
            /*background.setSize(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())*/

            timer = 0f
        } else timer += dt
    }
}
