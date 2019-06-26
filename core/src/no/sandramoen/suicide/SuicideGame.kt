package no.sandramoen.suicide

import no.sandramoen.suicide.base.BaseGame
import no.sandramoen.suicide.screens.LevelScreen
import no.sandramoen.suicide.screens.MenuScreen
import no.sandramoen.suicide.screens.SplashScreen

class SuicideGame : BaseGame(){
    override fun create() {
        super.create()
        setActiveScreen(SplashScreen())
    }
}
