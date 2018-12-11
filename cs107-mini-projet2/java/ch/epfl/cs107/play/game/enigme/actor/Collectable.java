package ch.epfl.cs107.play.game.enigme.actor;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.areagame.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public abstract class Collectable extends AreaEntity{

	boolean collected;
	
	public Collectable(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position);
		collected = false;
	}

	public Collectable(Area area, DiscreteCoordinates position) {
		super(area, Orientation.DOWN, position);
		collected = false;
	}
	
	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		return 
				Collections.singletonList(getCurrentMainCellCoordinates());
	}

	@Override
	public boolean takeCellSpace() {
		// non-traversable actor
		return true;
	}

	@Override
	public boolean isViewInteractable() {
		//if it is seen is interactable
		return true;
	}

	@Override
	public boolean isCellInteractable() {
		//just non-contact interaction
		return false;
	}
	
	
	//ADDITIONAL FUNCTIONS
	public void setCollected(boolean set) {
		collected = set;
	}
	
	public boolean getCollected() {
		return collected;
	}

	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		((EnigmeInteractionVisitor)v).interactWith(this);
	}
	
}