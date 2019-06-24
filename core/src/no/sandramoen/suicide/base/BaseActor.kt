package no.sandramoen.suicide.base

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.Texture.TextureFilter
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.utils.Array
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.scenes.scene2d.Group
import com.badlogic.gdx.scenes.scene2d.ui.Table

open class BaseActor(x: Float, y: Float, s: Table) : Group() {

    private var animation: Animation<TextureRegion>?
    private var elapsedTime: Float = 0F
    private var animationPaused: Boolean = false

    init {
        this.x = x
        this.y = y
        s.addActor(this)
        animation = null
    }

    override fun act(dt: Float) {
        super.act(dt)

        if (!animationPaused)
            elapsedTime += dt
    }

    override fun draw(batch: Batch, parentAlpha: Float) {
        //  apply color tint effect
        val c: Color = color
        batch.setColor(c.r, c.g, c.b, c.a)

        if (animation != null && isVisible) {
            batch.draw(
                    animation!!.getKeyFrame(elapsedTime),
                    x,
                    y,
                    originX,
                    originY,
                    width,
                    height,
                    scaleX,
                    scaleY,
                    rotation
            )
        }
        super.draw(batch, parentAlpha)
    }

    // Graphics ---------------------------------------------------------------------------------------------------
    fun setAnimation(anim: Animation<TextureRegion>) {
        animation = anim
        val tr: TextureRegion = animation!!.getKeyFrame(0.toFloat())
        val w: Float = tr.regionWidth.toFloat()
        val h: Float = tr.regionHeight.toFloat()
        setSize(w, h)
        setOrigin(w/2, h/2)
    }

    fun setAnimationPaused(pause: Boolean) {
        animationPaused = pause
    }

    fun loadAnimationFromTextureRegions(textureRegionList: Array<TextureRegion>, frameDuration: Float, loop: Boolean): Animation<TextureRegion> {

        val anim: Animation<TextureRegion> = Animation(frameDuration, textureRegionList)

        if (loop)
            anim.playMode = Animation.PlayMode.LOOP
        else
            anim.playMode = Animation.PlayMode.NORMAL

        if (animation == null)
            setAnimation(anim)

        return anim
    }

    fun loadTexture(region: TextureAtlas.AtlasRegion): Animation<TextureRegion> {
        val textureList: Array<TextureRegion> = Array(1)
        textureList.add(region as TextureRegion)
        return loadAnimationFromTextureRegions(textureList, 1f, true)
    }

    fun isAnimationFinished(): Boolean {
        return animation!!.isAnimationFinished(elapsedTime)
    }

    companion object {
        private lateinit var worldBounds: Rectangle

        fun setWorldBounds(width: Float, height: Float) { worldBounds = Rectangle(0f, 0f, width, height) }
        fun setWorldBounds(ba: BaseActor) = setWorldBounds(ba.width, ba.height)
        fun getWorldBounds() = worldBounds
    }
}