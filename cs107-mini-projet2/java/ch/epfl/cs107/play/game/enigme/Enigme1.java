package ch.epfl.cs107.play.game.enigme;

import ch.epfl.cs107.play.game.areagame.AreaGame;
import ch.epfl.cs107.play.game.enigme.actor.EnigmePlayer;
import ch.epfl.cs107.play.game.enigme.area.Enigme0;
import ch.epfl.cs107.play.game.enigme.area.Enigme2;
import ch.epfl.cs107.play.game.enigme.area.Level2;
import ch.epfl.cs107.play.game.enigme.area.Level3;
import ch.epfl.cs107.play.game.enigme.area.LevelSelector;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;


/**
 * Enigme Game is a concept of Game derived for AreaGame. It introduces the notion of Player
 * When initializing the player is added to the current area
 */
public class Enigme1 extends AreaGame {

	//The extra attribute of Demo2 is the actors
	EnigmePlayer player;

    /// Enigme implements Playable

    @Override
    public String getTitle() {
        return "Enigme1";
    }

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
    	super.begin(window, fileSystem);
    	///that are mainly the three areas+levelselector
    	addArea(new Enigme0());
    	addArea(new Level2());
    	addArea(new Level3());
    	addArea(new Enigme2());
    	addArea(new LevelSelector());
    	
    	//select one of those
    	currentArea = setCurrentArea("Enigme0", false);
        
    	//select one of those
    	//and the actor
    	DiscreteCoordinates xs = new DiscreteCoordinates(10,10);
    	player = new EnigmePlayer(currentArea,xs);
    	
    	//But go registering the player in both rooms
    	currentArea.registerActor(player);
    	currentArea.setViewCandidate(player);
    	return true;
    }

    @Override
    public void update(float deltaTime) {
        //super.update(deltaTime);
	
    	if (player.getPassDoor()) {
    		player.setIsPassingDoor(false);
    		getCurrentArea().unregisterActor(player);
    		currentArea = setCurrentArea(player.getLastDoor().getDestination(),true);
    		player.setCurrentPosition(player.getLastDoor().getCoordDest());	
    		player.setCurrentArea(currentArea);
    		
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
