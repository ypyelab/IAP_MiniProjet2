package ch.epfl.cs107.play.game.enigme.area;

import java.util.Collections;

import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.enigme.EnigmeBehavior;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

public class LevelSelector extends EnigmeArea{
	
	//The begin method should get the title of the "current area"
	@Override
    public boolean begin(Window window, FileSystem fileSystem) {
		super.begin(window,fileSystem);
		//plus Door1
		registerActor(new Door(this, "Level1",  new DiscreteCoordinates(5,1), new DiscreteCoordinates(2,7), new DiscreteCoordinates(2,7)));
		//plus Door2
		registerActor(new Door(this, "Level2",  new DiscreteCoordinates(5,1), new DiscreteCoordinates(3,7), new DiscreteCoordinates(3,7)));
		//plus Door 3 to Door 7
		//registerActor(new Door(this, "",  null, new DiscreteCoordinates(4,7), new DiscreteCoordinates(4,7)));
		//registerActor(new Door(this, "",  null, new DiscreteCoordinates(5,7), new DiscreteCoordinates(5,7)));
		//registerActor(new Door(this, "",  null, new DiscreteCoordinates(6,7), new DiscreteCoordinates(6,7)));
		//registerActor(new Door(this, "",  null, new DiscreteCoordinates(7,7), new DiscreteCoordinates(7,7)));
		//registerActor(new Door(this, "",  null, new DiscreteCoordinates(8,7), new DiscreteCoordinates(8,7)));
		
		return true;
    }
    
	@Override
	public String getTitle() {
		return "LevelSelector";
	}


	
}