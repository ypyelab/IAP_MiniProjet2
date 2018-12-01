package ch.epfl.cs107.play.game.areagame;

import java.util.LinkedList;
import java.util.List;

import ch.epfl.cs107.play.game.Playable;
import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.io.FileSystem;
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
	
	// List of actors
	private List <Actor> actors;
	private List <Actor> registeredActors;
	private List <Actor> unregisteredActors;

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
        	//further treatment
        	boolean errorOccured = !actors.add(a);
    	
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
    	
    		if (errorOccured && !forced) {
    			System.out.println("Actor "+ a + "cannot be "
    					+ "emoved, so create it");
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
    		registeredActors.forEach(rActor->{
    			//forcedAdd=false
        		addActor(rActor,false);
        	});
        	registeredActors.clear();
    	}
    	
    	if (!unregisteredActors.isEmpty()) {
    		//remove unregistered-actors
        	unregisteredActors.forEach(urActor->{
        		//forcedAdd=false
        		removeActor(urActor,false);
        	});
        	unregisteredActors.clear();
    	}	
    }
    
    /**
     * Getter for the area width
     * @return (int) : the width in number of cols
     */
    public final int getWidth(){
        // TODO implements me #PROJECT #TUTO
        return 0;
    }

    /**
     * Getter for the area height
     * @return (int) : the height in number of rows
     */
    public final int getHeight(){
        // TODO implements me #PROJECT #TUTO
        return 0;
    }

    /** @return the Window Keyboard for inputs */
    public final Keyboard getKeyboard () {
        // TODO implements me #PROJECT #TUTO
        return null;
    }
    
    /** @return the Window Keyboard for inputs */
    public final void setViewCandidate (Actor a) {
        this.viewCandidate = a;
    }
       
    /// Area implements Playable

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
		//Define actors in the game
    	actors = new LinkedList<>();
    	registeredActors = new LinkedList<>();
    	unregisteredActors = new LinkedList<>();
    	
    	//Define info for camera parameter on principal actor
    	viewCenter = Vector.ZERO;
    	viewCandidate = null;
    	scaleFactor = 0.0f;
    	
		//Initialize window 
		this.window = window;
		this.fileSystem = fileSystem;
		
		//Transformation on the window
		Transform viewTransform = Transform.I.scaled(1).translated(Vector.ZERO);
		window.setRelativeTransform(viewTransform);  	
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
    	
    	actors.forEach(actor->{
    		actor.update(deltaTime);
    		actor.draw(window);
    	});
    	purgeRegistration();
    }


    private void updateCamera () {
        if (viewCandidate!=null) {
        	viewCenter = viewCandidate.getPosition();
        }
        scaleFactor = getCameraScaleFactor();
        
        //Compute new viewpoint
        Transform viewTransform = Transform.I.scaled(scaleFactor).translated(viewCenter);
        window.setRelativeTransform(viewTransform);
    }

    /**
     * Suspend method: Can be overridden, called before resume other
     */
    public void suspend(){
        purgeRegistration();
    }


    @Override
    public void end() {
        // TODO save the AreaState somewhere
    }

}
