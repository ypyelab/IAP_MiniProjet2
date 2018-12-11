package ch.epfl.cs107.play.game.areagame;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Interactor;
import ch.epfl.cs107.play.game.areagame.io.ResourcePath;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Image;
import ch.epfl.cs107.play.window.Window;

/**
 * AreaBehavior manages a map of Cells.
 */
public abstract class AreaBehavior
{

    /// The behavior is an Image of size height x width
    private final Image behaviorMap;
    private final int width,height;
    
    /// Conversion of the image into an array of cells
    private final Cell [][] cells;
    
    /**
     * Default AreaBehavior Constructor
     * @param window (Window): graphic context, not null
     * @param fileName (String): name of the file containing the behavior image, not null
     */
    public AreaBehavior(Window window, String fileName){
    	behaviorMap =  window.getImage(ResourcePath.getBehaviors(fileName), null, false);
    	width = behaviorMap.getWidth();
    	height = behaviorMap.getHeight();
    	cells = new Cell [width][height];
    }
    
    //At area behavior the decision of can leave of can enter must be done. 
    //However it depends of the decision at cell level(which depends of each game).
    public abstract boolean canLeave (Interactable entity,DiscreteCoordinates coord);
    public abstract boolean canEnter (Interactable entity,DiscreteCoordinates coord);

    
    protected void leave(Interactable entity, List<DiscreteCoordinates> coordinates) {
    	for (DiscreteCoordinates coord: coordinates) {
    		cells[coord.x][coord.y].entities.remove(entity);
    	}	
    }
    
    protected void enter(Interactable entity, List<DiscreteCoordinates> coordinates) {
    	for (DiscreteCoordinates coord: coordinates) {
    		cells[coord.x][coord.y].entities.add(entity);
    	}	
    }

    public void cellInteractionOf(Interactor interactor) {
    	for (DiscreteCoordinates coord: interactor.getCurrentCells()) {
    		cells[coord.x][coord.y].cellInteractionOf(interactor);
    	}
    }
    
    public void viewInteractionOf(Interactor interactor) {
    	for (DiscreteCoordinates coord: interactor.getFieldOfViewCells()) {
    		cells[coord.x][coord.y].viewInteractionOf(interactor);
    	}
    }
    
    public abstract class Cell implements Interactable{
    	//basic attributes of the cell
    	DiscreteCoordinates coord;
      	Set<Interactable> entities;
      	
    	public Cell(int x, int y) {
    		coord = new DiscreteCoordinates(x,y);
    		entities = new HashSet<>();
    	}
    	
    	//Clearly depends of each game -and each entity
    	protected abstract boolean canEnter(Interactable entity);
    	protected abstract boolean canLeave(Interactable entity);
    	
    	private void cellInteractionOf(Interactor interactor) {
    		for(Interactable interactable: entities) {
    			if(interactable.isCellInteractable()) {
    				interactor.interactWith(interactable);
    			}
    		}
    	}
    	private void viewInteractionOf(Interactor interactor) {
    		for(Interactable interactable: entities) {
    			if(interactable.isViewInteractable()) {
    				interactor.interactWith(interactable);
    			}
    		}
    	}
    	
    	public List <DiscreteCoordinates> getCurrentCells(){	
    		List <DiscreteCoordinates> currentCells = new LinkedList<>();
    		currentCells.add(coord);
    		return currentCells;
    	}
    	
    	protected Set<Interactable> getInteractables(){
    		return entities;
    	}
    	//...
    }
   
    
    protected int getWidth() {
    	return width;
    }

    protected int getHeight() {
    	return height;
    }
    
    protected Image getBehaviorMap() {
		return behaviorMap;
	}
    
    protected Cell [][] getCells(){
    	return cells;
    }
}
