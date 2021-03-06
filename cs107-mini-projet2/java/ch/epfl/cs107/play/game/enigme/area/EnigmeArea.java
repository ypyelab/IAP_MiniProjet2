package ch.epfl.cs107.play.game.enigme.area;
import java.util.List;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.enigme.EnigmeBehavior;
import ch.epfl.cs107.play.game.enigme.actor.Collectable;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

public abstract class EnigmeArea extends Area{
	
	@Override
	public void update(float deltatime) {
		for (Actor actor: actors) {
			if (actor instanceof Collectable && ((Collectable)actor).getCollected()) {
				this.unregisterActor(actor);
			}
		}
		super.update(deltatime);
	}
	
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
    	for (DiscreteCoordinates coord: coordinates) {
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
