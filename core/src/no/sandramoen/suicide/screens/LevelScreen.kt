package no.sandramoen.suicide.screens

import com.badlogic.gdx.Input.Keys
import no.sandramoen.suicide.base.BaseScreen
import no.sandramoen.suicide.Emotions

class LevelScreen : BaseScreen() {

    private var stopGame = false
    private var timer = 0f
    private val frequency = 6f

    private var sumScore = 0
    private var totalNumberOfEmotions = 0f

    private val emotions = Emotions()

    override fun initialize() {

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
