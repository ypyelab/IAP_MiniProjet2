package ch.epfl.cs107.play.game.enigme.actor;

import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public class Door extends AreaEntity{

	String destination;
	DiscreteCoordinates coordDest;
	
	public Door(Area area, String destination, DiscreteCoordinates coordDest, Orientation orientation, DiscreteCoordinates position, List<DiscreteCoordinates> extraPosition) {
		super(area, orientation, position);
		// TODO Auto-generated constructor stub
	}
	
	public String getDestination() {
		return destination;
	}

	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean takeCellSpace() {
		// non-traversable
		return true;
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

	
}