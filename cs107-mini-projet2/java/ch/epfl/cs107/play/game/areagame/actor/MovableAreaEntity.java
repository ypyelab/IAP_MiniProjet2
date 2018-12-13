package ch.epfl.cs107.play.game.areagame.actor;

import java.util.LinkedList;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.AreaBehavior.Cell;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;


/**
 * MovableAreaEntity are AreaEntity able to move on a grid
 */
public abstract class MovableAreaEntity extends AreaEntity {
	
	//Because is an Entity 
	///it has a current position and  a transformation (super class attributes)
	
	//Because it is an AreaEntity
	///it has an associated area, an orientation and the coordinates of the main cell (super class attributes)
	
	//Because it is a MovableAreaEntity
	///Indicate if the actor is currently moving
	private boolean isMoving;
	///Indicate how many frames the current move is supposed to take
	private int framesForCurrentMove;
	///It has a target cell (where the movableAreaEntity wants to go)
	private DiscreteCoordinates targetMainCellCoordinates;
	

    /**
     * Default MovableAreaEntity constructor
     * @param area (Area): Owner area. Not null
     * @param position (Coordinate): Initial position of the entity. Not null
     * @param orientation (Orientation): Initial orientation of the entity. Not null
     */
    public MovableAreaEntity(Area area, Orientation orientation, DiscreteCoordinates position) {
        super(area, orientation, position);
        resetMotion();
    }

    /**
     * Initialize or reset the current motion information
     */
    protected void resetMotion(){
        isMoving = false;
        framesForCurrentMove = 0;
        targetMainCellCoordinates = getCurrentMainCellCoordinates();
    }

    /**
     * 
     * @param frameForMove (int): number of frames used for simulating motion
     * @return (boolean): returns true if motion can occur
     */
  
    protected  boolean move(int framesForMove){
        if(!isMoving || targetMainCellCoordinates.equals(getCurrentMainCellCoordinates())) {
        	if (getOwnerArea().leaveAreaCells(this, getLeavingCells()) && getOwnerArea().enterAreaCells(this, getEnteringCells())) {
        		if (framesForMove>1) {
        			framesForMove=1;
        		}
        		framesForCurrentMove = framesForMove;
    		
        		//select objective cell
        		
        		Vector orientation = getOrientation().toVector();
        		targetMainCellCoordinates = getCurrentMainCellCoordinates().jump(orientation);
        		isMoving = true;
        		return true;			
        	}
        	else {
        		return false;
        	}
        }
        return false;
    }

    //You leave the cells, that you are passing by
    protected final List <DiscreteCoordinates> getLeavingCells(){
    	return getCurrentCells();
    }
    
    protected final List <DiscreteCoordinates> getEnteringCells(){
    	List <DiscreteCoordinates> entCell = new LinkedList<>();
    	for (DiscreteCoordinates coord: getCurrentCells()) {
    	    entCell.add(coord.jump(getOrientation().toVector()));
    	}
    	return entCell;
    }
    
    boolean vetoFromGrid() {
    	if (getOwnerArea().leaveAreaCells(this, getLeavingCells()) && getOwnerArea().enterAreaCells(this, getEnteringCells())) {
    		return false;
    	}
    	else{
    		return true;
    	}
    }
    
    /// MovableAreaEntity implements Actor

    @Override
    public void update(float deltaTime) {
        if(isMoving & !(targetMainCellCoordinates.equals(getCurrentMainCellCoordinates()))) {
        	isMoving = false;

        	Vector distance = getOrientation().toVector();
        	distance = distance.mul(1.0f/framesForCurrentMove);
        	setCurrentPosition(getPosition().add(distance));
        }
        else {
        	resetMotion();
        }
    }

    /// Implements Positionable

    @Override
    public Vector getVelocity() {
        return getOrientation().toVector().mul(framesForCurrentMove);
    }
    
    /// Is an AreaEntity
    @Override
    protected void setOrientation(Orientation orientation) {
    	if(!isMoving) {
    		super.setOrientation(orientation);
    	}
    }
}
