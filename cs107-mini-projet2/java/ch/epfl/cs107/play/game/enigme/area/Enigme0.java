package ch.epfl.cs107.play.game.enigme.area;

import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.demo1.actor.MovingRock;
import ch.epfl.cs107.play.game.enigme.actor.Message;
import ch.epfl.cs107.play.game.enigme.actor.SignalDoor;
import ch.epfl.cs107.play.game.enigme.actor.TorchSp;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Window;

public class Enigme0 extends EnigmeArea{
	SignalDoor rndDoor1;
	SignalDoor rndDoor2;
	SignalDoor rndDoor3;
	TorchSp torch1;
	TorchSp torch2;
	TorchSp torch3;
	TorchSp torch4;
	TorchSp torch5;
	
	Message mov;
	TextGraphics text;
	
	//The begin method should get the title of the "current area"
	@Override
    public boolean begin(Window window, FileSystem fileSystem) {
		super.begin(window,fileSystem);
		rndDoor1 = new SignalDoor(this, "Level2",  new DiscreteCoordinates(1,6), new DiscreteCoordinates(9,29), Logic.TRUE, new DiscreteCoordinates(9,29));
		rndDoor2 = new SignalDoor(this, "Level3",  new DiscreteCoordinates(1,6), new DiscreteCoordinates(10,29), Logic.TRUE, new DiscreteCoordinates(10,29));
		rndDoor3 = new SignalDoor(this, "Enigme2",  new DiscreteCoordinates(1,6), new DiscreteCoordinates(11,29), Logic.TRUE, new DiscreteCoordinates(11,29));
		torch1 = new TorchSp(this, new DiscreteCoordinates(22,18));
		torch2 = new TorchSp(this, new DiscreteCoordinates(24,18));
		torch3 = new TorchSp(this, new DiscreteCoordinates(22,17));
		torch4 = new TorchSp(this, new DiscreteCoordinates(23,17));
		torch5 = new TorchSp(this, new DiscreteCoordinates(24,17));
		mov = new Message(new Vector(10f,10f),"Hello phantom!","Winter is coming, and you better manage to enter to your home..");	
		
		//plus Door1
		registerActor(rndDoor1);
		registerActor(rndDoor2);
		registerActor(rndDoor3);
		registerActor(torch1);
		registerActor(torch2);
		registerActor(torch3);
		registerActor(torch4);
		registerActor(torch5);
		registerActor(mov);
		
		return true;
    }
    
	@Override
	public String getTitle() {
		return "Enigme0";
	}

	//extends Area
	@Override
	public float getCameraScaleFactor() {
		return 50;
	}
	
}