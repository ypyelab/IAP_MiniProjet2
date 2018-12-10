package ch.epfl.cs107.play.game.areagame;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
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
    
    public boolean canLeave (Interactable entity,List<DiscreteCoordinates> coordinates) {
    	int sum = 0;
    	for (DiscreteCoordinates coord: coordinates) {
    		if (cells[coord.x][coord.y].canLeave(entity)==true) {
    	    	sum = sum + 1;
    	    }
    	}
    	//verify that for all cells are agreement to enter
    	if (sum == coordinates.size()) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    
    public boolean canEnter (Interactable entity,List<DiscreteCoordinates> coordinates) {
    	int sum = 0;
    	for (DiscreteCoordinates coord: coordinates) {
    		if (cells[coord.x][coord.y].canEnter(entity)==true) {
    	    	sum = sum + 1;
    	    }
    	}
    	//verify that for all cells are agreement to enter
    	if (sum == coordinates.size()) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    
    protected void leave(Interactable entity, List<DiscreteCoordinates> coordinates) {
    	for (DiscreteCoordinates coord: coordinates) {
    		cells[coord.x][coord.y].set.remove(entity);
    	}	
    }
    
    protected void enter(Interactable entity, List<DiscreteCoordinates> coordinates) {
    	for (DiscreteCoordinates coord: coordinates) {
    		cells[coord.x][coord.y].set.add(entity);
    	}	
    }

    public abstract class Cell implements Interactable{
    	//basic attributes of the cell
    	DiscreteCoordinates coord;
      	Set<Interactable> set;
      	
    	public Cell(int x, int y) {
    		coord = new DiscreteCoordinates(x,y);
    		set = new HashSet<>();
    	}
    	
    	//Clearly depends of each game -and each entity
    	protected abstract boolean canEnter(Interactable entity);
    	
    	protected abstract boolean canLeave(Interactable entity);
    	
    	
    	public List <DiscreteCoordinates> getCurrentCells(){	
    		List <DiscreteCoordinates> currentCells = new LinkedList<>();
    		currentCells.add(coord);
    		return currentCells;
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
