package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Canvas;

public class SignalDoor extends Door{
	
	Logic log;
	Sprite sOn;
	Sprite sOff;
	
	public SignalDoor(Area area, String destination, DiscreteCoordinates coordDest, DiscreteCoordinates position, Logic log, DiscreteCoordinates... doorTotalPos) {
		super(area, destination, coordDest, position, doorTotalPos);
		this.log = log;
		sOn = new Sprite("door.open.1",1,1.f,this);
		sOff = new Sprite("door.close.1",1,1.f,this);
	}
	
	@Override
	public boolean takeCellSpace() {
		if(log.isOn()) {
			return false;
		}
		return true;
	}
	
	@Override
	public boolean isCellInteractable() {
		if(log.isOn()) {
			return true;
		}
		return false;
	}
	
	@Override
	public void draw(Canvas canvas) {
		if(log.isOn()) {
			sOn.draw(canvas);;
		}
		else {
			sOff.draw(canvas);
		}
	}
}