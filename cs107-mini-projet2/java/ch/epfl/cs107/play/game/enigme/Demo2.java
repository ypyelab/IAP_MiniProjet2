package ch.epfl.cs107.play.game.enigme;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.AreaGame;
import ch.epfl.cs107.play.game.enigme.area.demo2.Demo2Area;
import ch.epfl.cs107.play.game.enigme.area.demo2.Room0;
import ch.epfl.cs107.play.game.enigme.area.demo2.Room1;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Window;
import ch.epfl.cs107.play.game.enigme.actor.demo2.Demo2Player;

public class Demo2 extends AreaGame{
	
	//The extra attribute of Demo2 is the actor
	Demo2Player player;
	
	//The begin method should initialize all the attributes of Demo2
    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
    	super.begin(window, fileSystem);
    	///that are mainly the two areas
    	addArea(new Room0());
    	addArea(new Room1());

    	//select one of those
    	currentArea = setCurrentArea("LevelSelector", false);
        
    	//and the actor
    	DiscreteCoordinates xs = new DiscreteCoordinates(5,5);
    	player = new Demo2Player(currentArea,xs);
    	
    	//But go registering the player in both rooms
    	currentArea.registerActor(player);
    	currentArea.setViewCandidate(player);
    	return true;
    }
	
    ///The update method should update the current area (as AreaGame does)
    @Override
    public void update(float deltaTime) {
    	if (player.needChangeOfRoom) {
    		if (getCurrentArea().getTitle()=="LevelSelector") {
    			getCurrentArea().unregisterActor(player);
    			getCurrentArea().suspend();
    			currentArea = setCurrentArea("Level1",false);
    			player.setCurrentPosition(new DiscreteCoordinates(5,2));	
    		}
    		else {
    			getCurrentArea().unregisterActor(player);
    			getCurrentArea().suspend();
    			currentArea = setCurrentArea("LevelSelector",false);
    			player.setCurrentPosition(new DiscreteCoordinates(5,5));
    		}
    		player.setCurrentArea(currentArea);
    		player.setNoNeedOfChange();
			getCurrentArea().registerActor(player);	
    	}
    	player.update(deltaTime);
    	getCurrentArea().update(deltaTime);
    	getCurrentArea().setViewCandidate(player);
    }
    
    ///The end method should not make anything until now (As AreaGame does)
	
    @Override
	public int getFrameRate() {
		return 24;
	}

	@Override
	public String getTitle() {
		return "Demo2";
	}
	
}