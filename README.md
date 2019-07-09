![Screenshot_2019-07-08-12-32-55](https://user-images.githubusercontent.com/4059636/60807955-72314800-a187-11e9-96ae-7c423aa12679.png)

[Youtube trailer](https://www.youtube.com/watch?v=0Z19qaYviks)

# Playing the Game
## To play on **Desktop**:
Java must be installed on your device.
* Click `Suicide.jar`
* Click "Download"
* Double click to run
   * Alternatively:
      * Open a terminal (cmd)
      * Navigate to the file `$cd [path]`
      * Run `$java -jar Suicide.jar`

## To play on Andoid:
* Click `android-release.apk`
* Download the file.
* Disable any operating system warnings about installing the app
* Install and play

# Game Design Document

1. Overall Vision
    * **Write a short paragraph explaining the game**       
    *Suicide?* is a life-themed story driven game. The player observes a character's emotional state over time and decides when to commit suicide in order to maximise happiness the last days they are alive. Once suicide is committed the game is over and the player will view the average happiness score based on positive and negative accumulated emotions.
    
    * **Describe the genre**    
    This is a single player story driven game.

    * **What is the target audience?**        
        This game is appropriate for a mature audience and is simple enough to be enjoyed by casual gamers. The target platform is Android phones and desktop computers. The game is controlled by touch screen or mouse clicks.

    * **Why play this game?**    
    This game teaches about feelings and needs that change over time, it also teaches about why someone would elect to commit suicide.    
    This game is fast-paced, and average gameplay sessions will be only a few minutes long, encouraging players to try repeatedly to achieve the highest score they can. A variety of background images and special effects will be used for visual interest.

2. Mechanics: the rules of the game world
    * **What are the character's goals?**    
    The short term goals are to observe the characters' emotions. The long term goals are to decide when to commit suicide in order to maximise the score.
    
    * **What abilities does the character have?**    
    The player is able to end the game at any time by using the suicide button.

    * **What obstacles or difficulties will the character face?**    
    Difficulties arise when the character's accumulated emotions might be more negative than positive. It is the player's task to keep score and guess what will happen next.

    * **What items can the character obtain**    
    There are no items to be obtained.

    * **What resources must be managed?**    
    Over the course of the game, random emotions are generated. Positive emotions award positive scores, and negative emotions award a negative score. Over time emotions accumulate score by arithmetic addition.

    * **Describe the game-world environment.**
    The game world is a single screen. On top, the emotions are shown, in the middle the character is shown, and on the bottom, the suicide button is shown.

3. Dynamics: the interaction between the player and the game mechanics
    * **What hardware is required by the game?**    
    To play this game on desktop a mouse or touch screen is required. To play this game on an android phone a touch screen is required. The game is entirely button based, meaning all choices simply require a touch or a click.

    * **What type of proficiency will the player need to develop to become proficient at the game?**
    To be proficient at this game the player will need to develop an intuition about what the score has become, and when the best time to commit suicide might be. It is also by the player's own definition of what proficient means because committing suicide is a subjective choice.

    * **What gameplay data is displayed during the game?**
    The gameplay data shown are random emotions. Emotions are shown long enough for the player to read and then they disappear. Alongside the emotion is the respective awarded score.

    * **What menus, screens, or overlays will there be?**
    First, a splash screen will be shown displaying the logo of the developer.
    Then the menu screen will become available, it shows some game art and depicts the start button which starts the level screen. The level screen contains the game and have no overlays.

    * **How does the player interact with the game at the software level?**
    There are no in-game functionalities. The player may quit the game or adjust it's volume as dictated by their devices' operating system.

4. Aesthetics: the visual, audio, narrative, and psychological aspects of the game
    * **Describe the style and feel of the game.**    
    The game's overall feel is melancholy and opens up for reflective thoughts about emotions, needs, and suicide. The basic graphical elements required will be the emotion text with a respective score, the character, and the suicide button. The character will progress over several days and seasons. Each day shows the character at home in the morning, going to work, leaving work and at home in the evening.

    * **Does the game use pixel art, line art, or realistic graphics?**    
    The graphical style is pixel art, and the colors used are cold.

    * **What style of background music, ambient sounds will the game use?**    
    The splash screen features no audio. The menu screen features a quiet ambient rain sound and piano music. The game itself will have a popping sound for each emotion, ambient sounds to match the seasons and piano music.
    
    * **What is the relevant backstory for the game?**    
    The backstory is that you are a person contemplating suicide, and deciding when to do it. With the help of emotional insight, the right decision will be made.
    
    * **What emotional state(s) does the game try to provoke?**
    The game creates feelings of sadness, helplessness, regret, peacefulness and dark humor.
    
    * **What makes the game fun?**    
    The fun aspect of this game comes from competition with oneself getting an acceptable score, and experiencing a procedural created storyline.

5. Development
    
    * **List the team members and their roles, responsibilities, and skills.**    
    This project will be completed individually; graphics and audio will be obtained from third-party websites that make their assets available under the Creative Commons license, and so the main task will be programming.
    
    * **What equipment is needed for this project?**    
    A desktop computer (with keyboard, mouse, and speakers) and internet access will be necessary to complete this project.
    
    * **What are the tasks that need to be accomplished to create this game?**    
    This project will use a simple Kanban board hosted on the project's GitHub page.
    The main sequence of steps to complete this project is as follows:    
        * Setting up a project scaffold
        * Programming game mechanics within a level screen
        * Obtaining video assets
        * Programming visualisation of game mechanics
        * Programming a menu screen
        * Programming a splash screen
        * Obtaining audio assets
        * Deployment

    * **What points in the development process are suitable for playtesting?**    
    The main points for playtesting are when the basic game mechanics of the level screen are implemented, and when it is visualised. The questions that will be asked are: 
        * Is the gameplay understandable?
        * Is the gameplay interesting?
        * Does the emotion text make sense?
        * Is it clear when to commit suicide?
        * Does the game provide a sense of closure?
        * How is the pace of the game?
        * Are there any improvement suggestions?        
    
    * **What are the plans for publication?**
    This game will be made available for free on desktop. It will be deployed on the Google Play store for 10 NOK and disseminated to various indie game-portal websites. Gameplay images and a video will be posted and marketed via social media.

# Afterthoughts
Some afterthoughts from project development and final product. This project was only meant as a small project and surmounted to about 48 hours of work. The final product became nearly identical to what was planned. 

Displaying random emotions and random needs in a game showed to be quite interesting, perhaps this may be further implemented in future games?
## TexturePacker
TexturePacker was implemented to collect graphical assets. For implementation see git commits in master. A `pack.json` was added to the folder to set `TextureFilter` globally. Separation of the graphical assets into _included_ and _excluded_ was needed because texturepacker was unable to process the big splash image.
## AssetManager
`AssetManager` was used to handle most assets (see git commits in master for implementation). This gives the advantage of only loading assets once. Since the loading time is not big a loading bar was not implemented
## Disposing
Disposing assets as soon as they are no longer needed is important for memory management. This project utilizes this successfully.
## Scenegraph
A complex scene graph were created in `LevelScreen`, this was good training.
## Graphics
**Graphics was harder than anticipated to create**, the overall style have improvement potential. Further increase in graphical skills is desired. 
## Fonts
A suprising amount of time went into sizing the font properly in relations to different screens. There is also no guarantee that it is readable, there seems to be some sort of bug making the font miss on some of its characters when scaled too big. The effective algorithm is as follows: 
```
fontParameters.size = (.06f * Gdx.graphics.height).toInt() // If the resolutions height is 1440 then the font size becomes 86
```
Where `.06f` is an arbitrary selection by trial and error.
## Gameplay
Gameplay is lacking. The player hardly have any choice (as per design). Future increase in game design is desired.
The score screen was mean to be ambiguous, this was successful.
## Creating a desktop .jar file
LibGDX's Gradle version is 4.6 in current project setups. This is to ensure certain dependencies works.

 In order to create a .jar file `$gradlew desktop:dist` may be run, and the resulting .jar fil will be found in `[project-dir]\desktop\build\libs`. 
 
 If however one needs to upgrade Gradle in order to meet a newer version of Java, this can be done by:
 * Changing the distribution in the file `[project-dir]/gradle/wrapper/gradle-wrapper.properties` to a higher number.
 * Then changing line 27 of the file `[project-name]/desktop/build.gradle` from `files(sourceSets.main.output.classesDir)` to `files(sourceSets.main.output.classesDirs)`.
 
 ## Google Play policies
 This game was deemed not appropriate for the Google Play app store. This was due to their policy about not promoting suicide. Blocking games, for this reason, is unfortunate, but I respect their decision and will not challenge them on this. 
 For future development; reading policies might thoroughly might be a good idea.
