package ch.epfl.cs107.play.game.areagame;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import ch.epfl.cs107.play.game.Playable;
import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Interactor;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;


/**
 * Area is a "Part" of the AreaGame. It is characterized by its AreaBehavior and a List of Actors
 */
public abstract class Area implements Playable {

    // Context objects
	private Window window;
	private FileSystem fileSystem;
	//The behavior Map
	protected AreaBehavior areaBehavior;
	
	// List of actors
	protected List <Actor> actors;
	private List <Actor> registeredActors;
	private List <Actor> unregisteredActors;
	///And specifically  interactables
	private Map <Interactable, List<DiscreteCoordinates>> interactablesToEnter;
	private Map <Interactable, List<DiscreteCoordinates>> interactablesToLeave;
	//Add specifically interactors
	private List <Interactor> interactors;
	
	
	//Indicator if the area have been taken before
	private boolean hasStarted;
	
	//Camera Parameter
	//actor on which the view is centered
	private Actor viewCandidate;
	private Vector viewCenter;
	private float scaleFactor;
	
	/** @return (float): camera scale factor, assume it is the same in x and y direction */
    public abstract float getCameraScaleFactor();
    
    
    /**
     * Add an actor to the actors list
     * @param a (Actor): the actor to add, not null
     * @param forced (Boolean): if true, the method ends
     */
    private void addActor(Actor a, boolean forced) {
        if (forced) {}
        else {
        	boolean errorOccured = !actors.add(a);
        	//Say an error occurred if <<that>> actor can not go to <<that>> cell
        	if (a instanceof Interactable) {
        		
        		errorOccured = errorOccured || !enterAreaCells(((Interactable)a),((Interactable) a).getCurrentCells());
        	}
    	
        	//Say an error occurred if <<that>> actor could not be add to the list of interactors
        	if (a instanceof Interactor) {
        		errorOccured = errorOccured || !interactors.add((Interactor)a);
        	}
        	
        	if (errorOccured && !forced) {
        		System.out.println("Actor "+ a + "cannot be "
    				+ "completely added, so remove it from where it was");
        		removeActor(a,true);
        	}
        }
    }

    /**
     * Remove an actor form the actor list
     * @param a (Actor): the actor to remove, not null
     * @param forced (Boolean): if true, the method ends
     */
    private void removeActor(Actor a, boolean forced){
    	if (forced) {}
    	else {
    		//further treatment
    		boolean errorOccured = !actors.remove(a);
    		//Say an error occurred if <<that>> actor can not be removed of <<that>> cell
        	if (a instanceof Interactable) {
        		errorOccured = errorOccured || !leaveAreaCells(((Interactable)a),((Interactable)a).getCurrentCells());
        	}
        	
        	//Say an error occurred if <<that>> actor could not be add to the list of interactors
        	if (a instanceof Interactor) {
        		errorOccured = errorOccured || !interactors.remove((Interactor)a);
        	}
        	
    		if (errorOccured && !forced) {
    			System.out.println("Actor "+ a + "cannot be "
    					+ "removed, so create it");
    			addActor(a,true);
    		}
    	}
    }

    /**
     * Register an actor : will be added at next update
     * @param a (Actor): the actor to register, not null
     * @return (boolean): true if the actor is correctly registered
     */
    public final boolean registerActor(Actor a){
    	//Verify that actor is not already in the list
    	if (!registeredActors.contains(a)) {
    		registeredActors.add(a);
    		return true;
    	}  		
        return false;
    }

    /**
     * Unregister an actor : will be removed at next update
     * @param a (Actor): the actor to unregister, not null
     * @return (boolean): true if the actor is correctly unregistered
     */
    public final boolean unregisterActor(Actor a){
    	//Verify that actor is not already in the list
    	if (!unregisteredActors.contains(a)) {
    		unregisteredActors.add(a);
    		return true;
    	}  
        return false;
    }
    
    /**
     * Purge the register of actors: 
     * add registered-actors to actors and remove unregistered-actors in actors
     */
    private final void purgeRegistration(){
    	//add registered-actors 
    	if (!registeredActors.isEmpty()){
    		//System.out.println(registeredActors.toString());
    		for (Actor rActor: registeredActors) {
    			addActor(rActor,false);
    		}
        	registeredActors.clear();
    	}
    	
    	//remove unregistered-actors
    	if (!unregisteredActors.isEmpty()) {
    		//remove unregistered-actors
        	unregisteredActors.forEach(urActor->{
        		//forcedAdd=false
        		removeActor(urActor,false);
        	});
        	unregisteredActors.clear();
    	}	
    	
    	//add interactables to enter
    	if (!interactablesToEnter.isEmpty()) {
    		for(Entry<Interactable, List<DiscreteCoordinates>> pair: interactablesToEnter.entrySet()) {
    			areaBehavior.enter(pair.getKey(), pair.getValue());
    		}
        	interactablesToEnter.clear();
    	}
    	
    	//remove interactables to leave
    	if (!interactablesToLeave.isEmpty()) {
    		for(Entry<Interactable, List<DiscreteCoordinates>> pair: interactablesToEnter.entrySet()) {
    			areaBehavior.leave(pair.getKey(), pair.getValue());
    		}
        	interactablesToLeave.clear();
    	}
    }
    
    /**
     * Getter for the area width
     * @return (int) : the width in number of cols
     */
    public final int getWidth(){
        return areaBehavior.getWidth();
    }

    /**
     * Getter for the area height
     * @return (int) : the height in number of rows
     */
    public final int getHeight(){
        return areaBehavior.getHeight();
    }

    /** @return the Window Keyboard for inputs */
    public final Keyboard getKeyboard () {
        return window.getKeyboard();
    }
    
    /** @return the Window Keyboard for inputs */
    public final void setViewCandidate (Actor a) {
        this.viewCandidate = a;
    }
    
    /** @return the Window Keyboard for inputs */
    protected final void setBehavior(AreaBehavior ab) {
        areaBehavior = ab;
    }   
    
    public abstract boolean canLeave (Interactable entity,List<DiscreteCoordinates> coord);
    public abstract boolean canEnter (Interactable entity,List<DiscreteCoordinates> coord);
      
    public final boolean leaveAreaCells(Interactable entity, List<DiscreteCoordinates> coordinates) {
    	if (this.canLeave(entity, coordinates)) {
    		interactablesToLeave.put(entity, coordinates);
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    
    public final boolean enterAreaCells(Interactable entity, List<DiscreteCoordinates> coordinates) {
    	if(this.canEnter(entity, coordinates)) {
    		interactablesToEnter.put(entity, coordinates);
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    
    public boolean getHasStarted() {
    	return hasStarted;
    }
    

    /// Area implements Playable

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
    	//indicator if the game have started sometime
    	hasStarted = true;
		//Define actors in the game
    	actors = new LinkedList<>();
    	registeredActors = new LinkedList<>();
    	unregisteredActors = new LinkedList<>();
    	interactablesToEnter = new HashMap<>();
    	interactablesToLeave = new HashMap<>();
    	interactors = new LinkedList<>();
    	
    	
    	//Define info for camera parameter on principal actor
    	viewCenter = Vector.ZERO;
    	viewCandidate = null;
    	scaleFactor = 0.0f;
    	
		//Initialize window 
		this.window = window;
		this.fileSystem = fileSystem;
		areaBehavior = null;
		
		//Transformation on the window
		//Transform viewTransform = Transform.I.scaled(getCameraScaleFactor()).translated(Vector.ZERO);
		//window.setRelativeTransform(viewTransform);  	
        return true;
    }

    /**
     * Resume method: Can be overridden
     * @param window (Window): display context, not null
     * @param fileSystem (FileSystem): given file system, not null
     * @return (boolean) : if the resume succeed, true by default
     */
    public boolean resume(Window window, FileSystem fileSystem){
    	return true;
    }

    @Override
    public void update(float deltaTime) {
    	//Camera issues
    	updateCamera();
    	
    	//Update actors
    	if (!actors.isEmpty()) {
    		actors.forEach(actor->{
    			actor.update(deltaTime);  		
        	});
    	}
    	//update interactions
    	if (!interactors.isEmpty()) {
    		interactors.forEach(interactor->{
    			if(interactor.wantsCellInteraction()) {
    				areaBehavior.cellInteractionOf(interactor);
    			}
    			if(interactor.wantsViewInteraction()) {
    				areaBehavior.viewInteractionOf(interactor);
    			}
        	});
    	}
    	//update draw
    	if (!actors.isEmpty()) {
    		actors.forEach(actor->{
               	actor.draw(window);    		
        	});
    	}
    	
    	purgeRegistration();
    }


    private void updateCamera() {
        if (viewCandidate!=null) {
        	viewCenter = viewCandidate.getPosition();   	
        }
        else {
        	viewCenter = Vector.ZERO;
        }
        
        //Compute new viewpoint
        Transform viewTransform = Transform.I.scaled(getCameraScaleFactor()).translated(viewCenter);
        
        window.setRelativeTransform(viewTransform);
    }

    /**
     * Suspend method: Can be overridden, called before resume other
     */
    public void suspend(){
    	//[IS IT A GOOD APPROACH? SUSPEND AND PURGE REGISTRATION]
        purgeRegistration();
    }


    @Override
    public void end() {
        // TODO save the AreaState somewhere
    }
    

}
