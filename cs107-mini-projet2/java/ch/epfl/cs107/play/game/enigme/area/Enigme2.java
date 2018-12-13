package ch.epfl.cs107.play.game.enigme.area;

import ch.epfl.cs107.play.game.enigme.actor.Key;
import ch.epfl.cs107.play.game.enigme.actor.SignalDoor;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

public class Enigme2 extends EnigmeArea{
	SignalDoor notrndDoor1;
	Key key1;
	
	//The begin method should get the title of the "current area"
	@Override
    public boolean begin(Window window, FileSystem fileSystem) {
		super.begin(window,fileSystem);
		key1 = new Key(this, new DiscreteCoordinates(13,5));
		notrndDoor1 = new SignalDoor((Enigme2)this, "Enigme0",  new DiscreteCoordinates(23,18), new DiscreteCoordinates(7,0), key1, new DiscreteCoordinates(7,0));
					
		//plus Door1
		registerActor(notrndDoor1);
		registerActor(key1);

		return true;
    }
    
	@Override
	public String getTitle() {
		return "Enigme2";
	}

	//extends Area
	@Override
	public float getCameraScaleFactor() {
		return 50;
	}
	
}