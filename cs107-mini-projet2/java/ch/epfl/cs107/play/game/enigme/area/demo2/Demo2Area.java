package ch.epfl.cs107.play.game.enigme.area.demo2;
import java.util.List;

import ch.epfl.cs107.play.game.actor.Entity;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.enigme.Demo2Behavior;
import ch.epfl.cs107.play.game.enigme.Demo2Behavior.Demo2Cell;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;
public abstract class Demo2Area extends Area{
	
	//Has the same attributes as Area
	
	//The begin method should get the title of the "current area"
    public boolean begin(Window window, FileSystem fileSystem) {
		super.begin(window,fileSystem);
		//associate a grill 
		setBehavior(new Demo2Behavior(window,getTitle()));
		//register an unique actor
		
		registerActor(new Background(this));
		return true;
    }
    
    
    public boolean isDoor(DiscreteCoordinates coord) {
		return (((Demo2Behavior) areaBehavior).isDoor(coord));	
	}
    
    
    public boolean canEnter (Interactable entity,List<DiscreteCoordinates> coordinates) {
    	int sum = 0;
    	for (DiscreteCoordinates coord: coordinates) {
    		//System.out.print(((Demo2Behavior) areaBehavior).canEnter(entity,coord));
    		//System.out.print(((Demo2Behavior) areaBehavior).isNull(coord));
    		//System.out.print(((Demo2Behavior) areaBehavior).isWall(coord));
    		//System.out.print(entity.getCurrentCells());
    		//System.out.println(((Entity) entity).getPosition());
    		
    		if (((Demo2Behavior) areaBehavior).canEnter(entity,coord)==true) {
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
	
    public boolean canLeave (Interactable entity,List<DiscreteCoordinates> coordinates) {
    	int sum = 0;
    	System.out.println(coordinates.size());
    	for (DiscreteCoordinates coord: coordinates) {
    		if (((Demo2Behavior) areaBehavior).canLeave(entity,coord)==true) {
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
    
	/** @return (float): camera scale factor, assume it is the same in x and y direction */
    public float getCameraScaleFactor() {
    	return 22f;
    }
    
    public int getFrameRate() {
		return 24;
	}
}