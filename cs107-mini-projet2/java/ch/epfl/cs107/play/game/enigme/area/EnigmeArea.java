package ch.epfl.cs107.play.game.enigme.area;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.enigme.Demo2Behavior;
import ch.epfl.cs107.play.game.enigme.EnigmeBehavior;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

public abstract class EnigmeArea extends Area{
	
	//The begin method should get the title of the "current area"
    public boolean begin(Window window, FileSystem fileSystem) {
		super.begin(window,fileSystem);
		//associate a grill 
		setBehavior(new EnigmeBehavior(window,getTitle()));
		//register an unique actor
		registerActor(new Background(this));
		return true;
    }
    
    
	//extends Area
	@Override
	public float getCameraScaleFactor() {
		return 22;
	}

	//we are not really using this approach, verify if it is really needed by Area (as abstract)
	public boolean canEnter (Interactable entity,List<DiscreteCoordinates> coordinates) {
    	int sum = 0;
    	System.out.println(coordinates);
    	for (DiscreteCoordinates coord: coordinates) {
    		//System.out.print(((Demo2Behavior) areaBehavior).canEnter(entity,coord));
    		//System.out.print(((Demo2Behavior) areaBehavior).isNull(coord));
    		//System.out.print(((Demo2Behavior) areaBehavior).isWall(coord));
    		//System.out.print(entity.getCurrentCells());
    		//System.out.println(((Entity) entity).getPosition());
    		if (coord.x>= 0 && coord.y >= 0 && coord.x < this.getHeight() && coord.y < this.getWidth()) {
    			if (((EnigmeBehavior)areaBehavior).canEnter(entity,coord)==true) {
        			sum = sum + 1;
        	    }
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
    	for (DiscreteCoordinates coord: coordinates) {
    		if (((EnigmeBehavior)areaBehavior).canLeave(entity,coord)==true) {
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
    

}
