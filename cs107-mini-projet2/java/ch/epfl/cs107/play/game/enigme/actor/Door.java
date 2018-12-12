package ch.epfl.cs107.play.game.enigme.actor;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.actor.EnigmePlayer.EnigmePlayerHandler;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public class Door extends AreaEntity{

	String destination;
	DiscreteCoordinates coordDest;
	DiscreteCoordinates [] doorTotalPos;
	
	public Door(Area area, String destination, DiscreteCoordinates coordDest, Orientation orientation, DiscreteCoordinates position, DiscreteCoordinates... doorTotalPos) {
		super(area, orientation, position);
		this.destination = destination;
		this.coordDest = coordDest;
		this.doorTotalPos = doorTotalPos;		
		// TODO Auto-generated constructor stub
	}
	
	public Door(Area area, String destination, DiscreteCoordinates coordDest, DiscreteCoordinates position, DiscreteCoordinates... doorTotalPos) {
		super(area, Orientation.DOWN, position);
		this.destination = destination;
		this.coordDest = coordDest;
		this.doorTotalPos = doorTotalPos;		
		// TODO Auto-generated constructor stub
	}
	
	public String getDestination() {
		return destination;
	}
	
	public DiscreteCoordinates getCoordDest() {
		return coordDest;
	}

	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		return 
				Collections.singletonList(getCurrentMainCellCoordinates());
	}

	@Override
	public boolean takeCellSpace() {
		//traversable
		return false;
	}

	@Override
	public boolean isViewInteractable() {
		// not answer to view
		return false;
	}

	@Override
	public boolean isCellInteractable() {
		// just answer to interaction of contact
		return true;
	}

	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		((EnigmePlayerHandler)v).interactWith(this);
	}
	
}