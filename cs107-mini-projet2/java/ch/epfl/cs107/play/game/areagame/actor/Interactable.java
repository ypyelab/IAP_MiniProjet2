package ch.epfl.cs107.play.game.areagame.actor;

import java.util.List;

import ch.epfl.cs107.play.math.DiscreteCoordinates;

/**
 * Models objects receptive to interaction (i.e. Interactor can interact with them)
 * @see Interactor
 * This interface makes sense only in the "AreaGame" context with Actor contained into Area Cell
 */
public interface Interactable {
	public abstract List <DiscreteCoordinates> getCurrentCells();
	
	public abstract boolean takeCellSpace();
	
	public abstract boolean isViewInteractable();
	
	public abstract boolean isCellInteractable();
}
