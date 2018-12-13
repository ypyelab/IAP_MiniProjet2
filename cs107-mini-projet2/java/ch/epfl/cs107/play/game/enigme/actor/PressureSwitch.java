package ch.epfl.cs107.play.game.enigme.actor;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.actor.EnigmePlayer.EnigmePlayerHandler;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public class PressureSwitch extends Switchable{

	private Sprite sOn;
	private Sprite sOff;

	public PressureSwitch(Area area, DiscreteCoordinates position) {
		super(area, position);
		sOn = new Sprite("GroundLightOn",1,1.f,this);
		sOff = new Sprite("GroundLightOff",1,1.f,this);
	}
	

	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		return 
				Collections.singletonList(getCurrentMainCellCoordinates());
	}

	@Override
	public boolean takeCellSpace() {
		return false;
	}

	@Override
	public boolean isViewInteractable() {
		return false;
	}

	@Override
	public boolean isCellInteractable() {
		return true;
	}

	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		((EnigmePlayerHandler)v).interactWith(this);		
	}

	@Override
	public void draw(Canvas canvas) {
		if (switchOn) {
			sOn.draw(canvas);
		}
		else {
			sOff.draw(canvas);
		}
	}
	
}