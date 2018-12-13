package ch.epfl.cs107.play.game.enigme.actor;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.actor.EnigmePlayer.EnigmePlayerHandler;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;

public abstract class Switchable extends AreaEntity implements Logic{

	protected boolean switchOn;
	
	public Switchable(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position);
		switchOn = false;
	}

	public Switchable(Area area, DiscreteCoordinates position) {
		super(area, Orientation.DOWN, position);
		switchOn = false;
	}
	
	@Override
	public boolean isOn() {
		if(switchOn == true) {
			return true;
		}
		return false;
	}	
	
	public void setSwitch(boolean set) {
		switchOn = set;
	}
	
}