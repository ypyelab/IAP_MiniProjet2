package ch.epfl.cs107.play.game.enigme.area;

import ch.epfl.cs107.play.game.enigme.actor.SignalDoor;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Window;

public class LevelSelector extends EnigmeArea{
	
	//The begin method should get the title of the "current area"
	@Override
    public boolean begin(Window window, FileSystem fileSystem) {
		super.begin(window,fileSystem);

		//plus Door1
		registerActor(new SignalDoor(this, "Level1",  new DiscreteCoordinates(1,6), new DiscreteCoordinates(2,7), Logic.TRUE, new DiscreteCoordinates(2,7)));
		//plus Door2
		registerActor(new SignalDoor(this, "Level2",  new DiscreteCoordinates(2,6), new DiscreteCoordinates(3,7), Logic.TRUE, new DiscreteCoordinates(3,7)));
		//plus Door 3 to Door 7
		registerActor(new SignalDoor(this, "Level3",  new DiscreteCoordinates(2,6), new DiscreteCoordinates(4,7), Logic.TRUE, new DiscreteCoordinates(4,7)));
		registerActor(new SignalDoor(this, "LevelSelector",  new DiscreteCoordinates(5,6), new DiscreteCoordinates(5,7), Logic.FALSE, new DiscreteCoordinates(5,7)));
		registerActor(new SignalDoor(this, "LevelSelector",  new DiscreteCoordinates(6,6), new DiscreteCoordinates(6,7), Logic.FALSE, new DiscreteCoordinates(6,7)));
		registerActor(new SignalDoor(this, "LevelSelector",  new DiscreteCoordinates(7,6), new DiscreteCoordinates(7,7), Logic.FALSE, new DiscreteCoordinates(7,7)));
		registerActor(new SignalDoor(this, "LevelSelector",  new DiscreteCoordinates(8,6), new DiscreteCoordinates(8,7), Logic.FALSE, new DiscreteCoordinates(8,7)));
		
		return true;
    }
    
	@Override
	public String getTitle() {
		return "LevelSelector";
	}


	
}