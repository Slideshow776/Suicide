package no.sandramoen.suicide.screens

import com.badlogic.gdx.Input.Keys
import com.badlogic.gdx.math.MathUtils
import no.sandramoen.suicide.base.BaseScreen

class LevelScreen : BaseScreen() {

    private var stopGame = false
    private var timer = 0f
    private val frequency = 2f

    private var sumScore = 0f
    private var totalNumberOfEmotions = 0f

    private var lastEmotionWasNegative = false // assumes first last emotion was positive
    private var probabilityOfStayingPositive = .6f
    private var probabilityOfStayingNegative = .7f

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
            randomizeEmotion()
            timer = 0f
        } else timer += dt
    }

    private fun randomizeEmotion() {
        if (lastEmotionWasNegative) {
            if (MathUtils.randomBoolean(probabilityOfStayingNegative)) { // input is probability of true
                sumScore -= 1
                println("-1")
                lastEmotionWasNegative = true
            } else {
                sumScore += 1
                println("+1")
                lastEmotionWasNegative = false
            }
        } else {
            if (MathUtils.randomBoolean(probabilityOfStayingPositive)) { // input is probability of true
                sumScore += 1
                println("+1")
                lastEmotionWasNegative = false
            } else {
                sumScore -= 1
                println("-1")
                lastEmotionWasNegative = true
            }

        }
        totalNumberOfEmotions += 1
    }
}
