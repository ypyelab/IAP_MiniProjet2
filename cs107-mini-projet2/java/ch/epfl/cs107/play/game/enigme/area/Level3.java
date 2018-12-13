package ch.epfl.cs107.play.game.enigme.area;

import ch.epfl.cs107.play.game.enigme.actor.Key;
import ch.epfl.cs107.play.game.enigme.actor.Lever;
import ch.epfl.cs107.play.game.enigme.actor.PressurePlate;
import ch.epfl.cs107.play.game.enigme.actor.PressureSwitch;
import ch.epfl.cs107.play.game.enigme.actor.SignalDoor;
import ch.epfl.cs107.play.game.enigme.actor.SignalRock;
import ch.epfl.cs107.play.game.enigme.actor.Torch;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.And;
import ch.epfl.cs107.play.signal.logic.MultipleAnd;
import ch.epfl.cs107.play.signal.logic.Not;
import ch.epfl.cs107.play.signal.logic.Or;
import ch.epfl.cs107.play.window.Window;

public class Level3 extends EnigmeArea{

	//extra parameter
	
	Key key;
	Torch torch;
	PressurePlate presPlate;
	PressureSwitch presSwitch1;
	PressureSwitch presSwitch2;
	PressureSwitch presSwitch3;
	PressureSwitch presSwitch4;
	PressureSwitch presSwitch5;
	PressureSwitch presSwitch6;
	PressureSwitch presSwitch7;
	Lever lev1;
	Lever lev2;
	Lever lev3;
	SignalDoor sigDoor;
	SignalRock sigRock1;
	SignalRock sigRock2;
	SignalRock sigRock3;

	
	//The begin method should get the title of the "current area"
	@Override
    public boolean begin(Window window, FileSystem fileSystem) {
		super.begin(window,fileSystem);
		//initializing attributes
		key = new Key(this, new DiscreteCoordinates(1,3));
		torch = new Torch(this, new DiscreteCoordinates(7,5), true);
		presPlate = new PressurePlate(this, new DiscreteCoordinates(9,8));
		presSwitch1  = new PressureSwitch(this, new DiscreteCoordinates(4,4));
		presSwitch2 = new PressureSwitch(this, new DiscreteCoordinates(5,4));
		presSwitch3 = new PressureSwitch(this, new DiscreteCoordinates(6,4));
		presSwitch4 = new PressureSwitch(this, new DiscreteCoordinates(5,5));
		presSwitch5 = new PressureSwitch(this, new DiscreteCoordinates(4,6));
		presSwitch6 = new PressureSwitch(this, new DiscreteCoordinates(5,6));
		presSwitch7 = new PressureSwitch(this, new DiscreteCoordinates(6,6));
		lev1 = new Lever(this, new DiscreteCoordinates(10,5));
		lev2 = new Lever(this, new DiscreteCoordinates(9,5));
		lev3 = new Lever(this, new DiscreteCoordinates(8,5));
		sigDoor = new SignalDoor(this, "LevelSelector", new DiscreteCoordinates(3,6),new DiscreteCoordinates(5,9), key, new DiscreteCoordinates(5,9));
		sigRock1 = new SignalRock(this, new DiscreteCoordinates(6,8), presPlate);
		sigRock2 = new SignalRock(this, new DiscreteCoordinates(5,8), new MultipleAnd(presSwitch1,presSwitch2,presSwitch3,presSwitch4,presSwitch5,presSwitch6,presSwitch7));
		sigRock3 = new SignalRock(this, new DiscreteCoordinates(4,8),new Or(new And (lev1, new And(new Not(lev2),lev3)),torch));
		
		
		//registering attributes <actors>
		registerActor(key);
		registerActor(torch);
		registerActor(presPlate);
		registerActor(presSwitch1);
		registerActor(presSwitch2);
		registerActor(presSwitch3);
		registerActor(presSwitch4);
		registerActor(presSwitch5);
		registerActor(presSwitch6);
		registerActor(presSwitch7);
		registerActor(lev1);
		registerActor(lev2);
		registerActor(lev3);
		registerActor(sigDoor);
		registerActor(sigRock1);
		registerActor(sigRock2);
		registerActor(sigRock3);
		return true;
    }
	
	@Override
	public String getTitle() {
		return "Level3";
	}

	
}