package no.sandramoen.suicide.screens

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup
import no.sandramoen.suicide.base.BaseGame

/*
* Mixin class for LevelScreen
* */
class Emotions(private val verticalGroup: VerticalGroup) {
    private var lastEmotionWasNegative = false // assumes first last emotion was positive
    private var probabilityOfStayingPositive = .55f
    private var probabilityOfStayingNegative = .75f

    init {
    }

    fun randomizeEmotion(): Int {
        if (lastEmotionWasNegative) {
            return if (MathUtils.randomBoolean(probabilityOfStayingNegative)) { // input is probability of true
                labelEmotion(getEmotion(false), false)
                lastEmotionWasNegative = true
                -1
            } else {
                labelEmotion(getEmotion(true), true)
                lastEmotionWasNegative = false
                +1
            }
        } else {
            return if (MathUtils.randomBoolean(probabilityOfStayingPositive)) { // input is probability of true
                labelEmotion(getEmotion(true), true)
                lastEmotionWasNegative = false
                +1
            } else {
                labelEmotion(getEmotion(false), false)
                lastEmotionWasNegative = true
                -1
            }

        }
    }

    private fun labelEmotion(emotion: String, positive: Boolean) {
        val label = Label(emotion, BaseGame.labelStyle)
        label.isVisible = false
        label.addAction(Actions.sequence(
                Actions.fadeOut(0f),
                Actions.visible(true),
                Actions.fadeIn(.125f),
                Actions.fadeOut(6f)
        ))
        verticalGroup.addActorAt(0, label)
        label.addAction(Actions.after(Actions.run { verticalGroup.removeActor(label) }))
        if (positive) label.color = Color.GREEN
        else label.color = Color.RED
    }

    private fun getEmotion(positive: Boolean): String {
        val need = BaseGame.needs!![MathUtils.random(BaseGame.needs!!.size -1)]
        return if (positive)
            "I feel ${BaseGame.positiveEmotions!![MathUtils.random(0, BaseGame.positiveEmotions!!.size -1)]} because I have $need +1"
        else
            "I feel ${BaseGame.negativeEmotions!![MathUtils.random(0, BaseGame.negativeEmotions!!.size -1)]} because I need $need -1"
    }
}
