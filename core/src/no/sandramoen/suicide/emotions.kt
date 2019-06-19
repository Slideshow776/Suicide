package no.sandramoen.suicide

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.math.MathUtils
import no.sandramoen.suicide.base.BaseGame

class Emotions {

    private var lastEmotionWasNegative = false // assumes first last emotion was positive
    private var probabilityOfStayingPositive = .6f
    private var probabilityOfStayingNegative = .7f

    init {

    }

    fun randomizeEmotion(): Int {
        if (lastEmotionWasNegative) {
            return if (MathUtils.randomBoolean(probabilityOfStayingNegative)) { // input is probability of true
                print(getEmotion(false))
                println(" -1")
                lastEmotionWasNegative = true
                -1
            } else {
                print(getEmotion(true))
                println(" +1")
                lastEmotionWasNegative = false
                +1
            }
        } else {
            return if (MathUtils.randomBoolean(probabilityOfStayingPositive)) { // input is probability of true
                print(getEmotion(true))
                println(" +1")
                lastEmotionWasNegative = false
                +1
            } else {
                print(getEmotion(false))
                println(" -1")
                lastEmotionWasNegative = true
                -1
            }

        }
    }

    private fun getEmotion(positive: Boolean): String {
        val need = BaseGame.needs!![MathUtils.random(BaseGame.needs!!.size -1)]
        return if (positive)
            "I feel ${BaseGame.positiveEmotions!![MathUtils.random(0, BaseGame.positiveEmotions!!.size -1)]} because I have $need"
        else
            "I feel ${BaseGame.negativeEmotions!![MathUtils.random(0, BaseGame.negativeEmotions!!.size -1)]} because I need $need"
    }
}
