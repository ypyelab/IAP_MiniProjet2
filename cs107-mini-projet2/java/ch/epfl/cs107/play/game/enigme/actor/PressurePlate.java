package ch.epfl.cs107.play.game.enigme.actor;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.actor.EnigmePlayer.EnigmePlayerHandler;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Canvas;

public class PressurePlate extends AreaEntity implements Logic{

	private Sprite sOn;
	private Sprite sOff;
	private float durAct;
	private float tempDurAct;
	private boolean switchOn;

	public PressurePlate(Area area, DiscreteCoordinates position) {
		super(area, Orientation.DOWN,position);
		sOn = new Sprite("GroundLightOn",1,1.f,this);
		sOff = new Sprite("GroundLightOff",1,1.f,this);
		durAct = 0.3f;
		tempDurAct = 0.0f;
		switchOn = false;
	}
	
	public void setSwitch() {
		switchOn = true;
		tempDurAct = 0.0f;
	}
	
	@Override
	public void update (float deltatime) {
		if (switchOn == true && tempDurAct < durAct) {
			tempDurAct = tempDurAct+deltatime;
			switchOn = true;
		}
		else if (switchOn == true && tempDurAct >= durAct) {
			tempDurAct = 0.0f;
			switchOn = false;
		}
	}
	
	@Override
	public boolean isOn() {
		if(switchOn == true) {
			return true;
		}
		return false;
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