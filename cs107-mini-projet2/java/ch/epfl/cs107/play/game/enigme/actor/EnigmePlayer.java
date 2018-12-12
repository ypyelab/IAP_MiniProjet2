package ch.epfl.cs107.play.game.enigme.actor;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Interactor;
import ch.epfl.cs107.play.game.areagame.actor.MovableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.areagame.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.area.EnigmeArea;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

public class EnigmePlayer extends MovableAreaEntity implements Interactor {
	
	//Set additional unique attributes
	private Sprite spr;
	private boolean passDoor;
	private Door lastDoor;
	///Including the handler of interactions
	private EnigmePlayerHandler handler;
	private boolean wantsViewInteraction;
	private final static int ANIMATION_DURATION = 8;
	
	public EnigmePlayer(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position);
		spr = new Sprite("ghost.1",1,1.f,this);
		passDoor = false;
		lastDoor = null;
		handler = new EnigmePlayerHandler();
		wantsViewInteraction = false;
	}

	public EnigmePlayer(Area area, DiscreteCoordinates position) {
		super(area, Orientation.DOWN, position);
		spr = new Sprite("ghost.1",1,1.f,this);
		passDoor = false;
		lastDoor = null;
		handler = new EnigmePlayerHandler();
		wantsViewInteraction = false;
	}
	
	
	//Handler class
	public class EnigmePlayerHandler implements EnigmeInteractionVisitor{
		@Override
		public void interactWith(Door door) {
			setIsPassingDoor(door);
		}
		
		@Override
		public void interactWith(Apple apple) {
			getOwnerArea().unregisterActor(apple);
		}
		
	}
	
	
	public void setIsPassingDoor(Door door) {
		passDoor = true;
		lastDoor = door;
	}
	
	public Door passedDoor() {
		return lastDoor;
	}
	
	public void setCurrentPosition(DiscreteCoordinates position) {
		super.setCurrentPosition(position.toVector());
	}
	
	public void setCurrentArea(Area area) {
		setOwnerArea(area);
	}

	public Vector getPosition() {
		return super.getPosition();
	}
	
	protected boolean getCanPass() {
		//The entering cells, are now the ones in its field of vision
		if (((EnigmeArea)getOwnerArea()).canEnter(this,getEnteringCells())) {
	    	return true;
	    }
	    else {
	    	return false;  	
	    }	
	}
	
	
	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		//actor is updating with time if keyboard order is given
		Keyboard keyboard = getOwnerArea().getKeyboard();
		Button leftArrow = keyboard.get(keyboard.LEFT);
		if(leftArrow.isDown()) {
			if(this.getOrientation()==Orientation.LEFT) {
				//Animation duration in frame number
				if (getCanPass()) {
					move(ANIMATION_DURATION);
					
				}
				//super.update(deltaTime);
			}	
			else {
				this.setOrientation(Orientation.LEFT);
			}
		}

		Button rightArrow = keyboard.get(keyboard.RIGHT);
		if(rightArrow.isDown()) {
			if(this.getOrientation()==Orientation.RIGHT) {
				//Animation duration in frame number
				
				if(getCanPass()) {
					
					move(ANIMATION_DURATION);
				}
				
				//super.update(deltaTime);
			}
			else {	
				this.setOrientation(Orientation.RIGHT);
			}
		}
			
		Button upArrow = keyboard.get(keyboard.UP);
		if(upArrow.isDown()) {
			if(this.getOrientation()==Orientation.UP) {	
				//Animation duration in frame number
				
				if(getCanPass()) {
					move(ANIMATION_DURATION);
				}
				
				//super.update(deltaTime);
			}
			else {
				this.setOrientation(Orientation.UP);
			}
		}
			
		Button downArrow = keyboard.get(keyboard.DOWN);
		if(downArrow.isDown()) {
			if(this.getOrientation()==Orientation.DOWN) {	
				//Animation duration in frame number
				
				if(getCanPass()) {
					move(ANIMATION_DURATION);
				}
				
				//super.update(deltaTime);
			}
			else {
				this.setOrientation(Orientation.DOWN);
			}
		}
		
		//new possibility
		Button lkey = keyboard.get(keyboard.L);
		if(lkey.isDown()) {
			wantsViewInteraction = true;
			//interactWith(getFieldOfViewCells());
		}	
	}	
	
	
	///specific methods for Demo2Player
	public void enterArea(Area area, DiscreteCoordinates position) {
		//register in the area
		//refresh the position
		setCurrentPosition(position.toVector());
		//stop!
		resetMotion();
	}
	
	public void leaveArea(Area area, DiscreteCoordinates position) {
		//register in the area
		area.unregisterActor(this);
	}
	
	public void setPassDoor(boolean set) {
		passDoor = set;
	}
	
	public boolean getPassDoor() {
		return passDoor;
	}
	
	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		return 
				Collections.singletonList(getCurrentMainCellCoordinates());
	}

	@Override
	public boolean takeCellSpace() {
		//no one can take his space
		return false;
	}

	@Override
	public boolean isViewInteractable() {
		return true;
	}

	@Override
	public boolean isCellInteractable() {
		return true;
	}

	@Override
	public void draw(Canvas canvas) {
		spr.draw(canvas);
	}
	
	//Extra function
	
	///for use of Enigme
	public void setIsPassingDoor(boolean set) {
		passDoor = set;
	}
	
	///for use of enigme
	public Door getLastDoor() {
		return lastDoor;
	}

	
	//Implements Interactor
	@Override
	public List<DiscreteCoordinates> getFieldOfViewCells() {
		if(this.getOrientation() == Orientation.DOWN) {
			return
					Collections.singletonList(super.getCurrentMainCellCoordinates().jump(0, -1));
		}
		else if (this.getOrientation() == Orientation.UP) {
			return
					Collections.singletonList(super.getCurrentMainCellCoordinates().jump(0, 1));
		}
		else if (this.getOrientation() == Orientation.LEFT) {
			return
					Collections.singletonList(super.getCurrentMainCellCoordinates().jump(-1, 0));
		}
		else if (this.getOrientation() == Orientation.RIGHT) {
			return
					Collections.singletonList(super.getCurrentMainCellCoordinates().jump(1, 0));
		}
		else {
			return null;
		}
	}

	@Override
	public boolean wantsCellInteraction() {
		return true;
	}

	@Override
	public boolean wantsViewInteraction() {
		// TODO Auto-generated method stub
		return wantsViewInteraction;
	}

	//because is an interactor
	@Override
	public void interactWith(Interactable other) {
		other.acceptInteraction(handler);		
	}

	//Because is an interactable
	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		//EnigmePlayer is not asking for this interaction
		//((EnigmeInteractionVisitor)v).interactWith((Interactable)this);
	}
	
}