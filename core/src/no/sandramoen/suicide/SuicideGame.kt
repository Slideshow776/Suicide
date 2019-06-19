package no.sandramoen.suicide

import no.sandramoen.suicide.base.BaseGame
import no.sandramoen.suicide.screens.LevelScreen

class SuicideGame : BaseGame(){
    override fun create() {
        super.create()
        setActiveScreen(LevelScreen())
    }
}
