package ch.epfl.cs107.play.game.enigme.actor;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.actor.EnigmePlayer.EnigmePlayerHandler;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class Lever extends Switchable{

	private Sprite sOn;
	private Sprite sOff;

	public Lever(Area area, DiscreteCoordinates position) {
		super(area, position);
		sOn = new Sprite("lever.big.left",1,1.f,this);
		sOff = new Sprite("lever.big.right",1,1.f,this);
	}
	
	//Proper methods (what makes it special)
	public void moveToLeft() {
		this.switchOn = true;
	}
	
	public void moveToRight() {
		this.switchOn = false;
	}
	
	//Other methods (as area entity)
	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		return 
				Collections.singletonList(getCurrentMainCellCoordinates());
	}

	@Override
	public boolean takeCellSpace() {
		return true;
	}

	@Override
	public boolean isViewInteractable() {
		return true;
	}

	@Override
	public boolean isCellInteractable() {
		return false;
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