package no.sandramoen.suicide.base

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.Texture.TextureFilter
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

    fun loadAnimationFromFiles(fileNames: Array<String>, frameDuration: Float, loop: Boolean,
                               textureFilter: TextureFilter = TextureFilter.Linear): Animation<TextureRegion> {
        val textureArray: Array<TextureRegion> = Array()

        for (i in 0 until fileNames.size) {
            val texture = Texture(Gdx.files.internal(fileNames[i]))
            texture.setFilter(textureFilter, textureFilter)
            textureArray.add(TextureRegion(texture))
        }

        val anim: Animation<TextureRegion> = Animation(frameDuration, textureArray)

        if (loop)
            anim.playMode = Animation.PlayMode.LOOP
        else
            anim.playMode = Animation.PlayMode.NORMAL

        if (animation == null)
            setAnimation(anim)

        return anim
    }

    fun loadAnimationFromSheet(fileName: String, rows: Int, cols: Int, frameDuration: Float, loop: Boolean, textureFilter: TextureFilter = TextureFilter.Linear): Animation<TextureRegion> {
        val texture = Texture(Gdx.files.internal(fileName), true)
        texture.setFilter(textureFilter, textureFilter)
        val frameWidth: Int = texture.width / cols
        val frameHeight: Int = texture.height / rows

        val temp = TextureRegion.split(texture, frameWidth, frameHeight)
        val textureArray: Array<TextureRegion> = Array()

        for (r in 0 until rows) {
            for (c in 0 until cols) {
                textureArray.add(temp[r][c])
            }
        }

        val anim: Animation<TextureRegion> = Animation(frameDuration, textureArray)

        if (loop)
            anim.playMode = Animation.PlayMode.LOOP
        else
            anim.playMode = Animation.PlayMode.NORMAL

        if (animation == null)
            setAnimation(anim)

        return anim
    }

    fun loadTexture(fileName: String): Animation<TextureRegion> {
        val fileNames: Array<String> = Array(1)
        fileNames.add(fileName)
        return loadAnimationFromFiles(fileNames, 1f, true)
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