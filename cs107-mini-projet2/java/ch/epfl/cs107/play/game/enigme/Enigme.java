package ch.epfl.cs107.play.game.enigme;

import ch.epfl.cs107.play.game.areagame.AreaGame;
import ch.epfl.cs107.play.game.enigme.actor.EnigmePlayer;
import ch.epfl.cs107.play.game.enigme.actor.demo2.Demo2Player;
import ch.epfl.cs107.play.game.enigme.area.Level1;
import ch.epfl.cs107.play.game.enigme.area.Level2;
import ch.epfl.cs107.play.game.enigme.area.Level3;
import ch.epfl.cs107.play.game.enigme.area.LevelSelector;
import ch.epfl.cs107.play.game.enigme.area.demo2.Demo2Area;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;


/**
 * Enigme Game is a concept of Game derived for AreaGame. It introduces the notion of Player
 * When initializing the player is added to the current area
 */
public class Enigme extends AreaGame {

	//The extra attribute of Demo2 is the actor
		EnigmePlayer player;

    /// Enigme implements Playable

    @Override
    public String getTitle() {
        return "Enigme";
    }

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
    	super.begin(window, fileSystem);
    	///that are mainly the four areas
    	addArea(new LevelSelector());
    	addArea(new Level1());
    	addArea(new Level2());
    	addArea(new Level3());
    	
    	//select one of those
    	currentArea = setCurrentArea("LevelSelector", false);
        currentArea.begin(window, fileSystem);
        
    	//and the actor
    	DiscreteCoordinates xs = new DiscreteCoordinates(5,5);
    	player = new EnigmePlayer(currentArea,xs);
    	
    	//But go registering the player in both rooms
    	currentArea.registerActor(player);
    	currentArea.setViewCandidate(player);
    	return true;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
	
    	if (player.needChangeOfRoom) {
    		if (getCurrentArea().getTitle()=="LevelSelector") {
    			getCurrentArea().unregisterActor(player);
    			currentArea = setCurrentArea("Level1",false);
    			player.setCurrentPosition(new DiscreteCoordinates(5,2));	
    		}
    		else {
    			getCurrentArea().unregisterActor(player);
    			currentArea = setCurrentArea("LevelSelector",false);
    			player.setCurrentPosition(new DiscreteCoordinates(5,5));
    		}
    		player.setCurrentArea(currentArea);
    		player.setNoNeedOfChange();
			getCurrentArea().registerActor(player);	
    	}
    	getCurrentArea().update(deltaTime);
    	getCurrentArea().setViewCandidate(player);
    }


    @Override
    public int getFrameRate() {
        return 24;
    }
}
