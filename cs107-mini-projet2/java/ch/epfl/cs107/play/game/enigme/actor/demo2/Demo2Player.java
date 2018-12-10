package ch.epfl.cs107.play.game.enigme.actor.demo2;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.MovableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.enigme.area.demo2.Demo2Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

public class Demo2Player extends MovableAreaEntity implements Interactable {
	
	private boolean passDoor;
	private Sprite spr;
	public boolean needChangeOfRoom;
	
	private final static int ANIMATION_DURATION = 8;
	
	public Demo2Player(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position);
		spr = new Sprite("ghost.1",1,1.f,this);
		needChangeOfRoom = false;
	}
	
	public Demo2Player(Area area, DiscreteCoordinates position) {
		super(area, Orientation.DOWN, position);
		spr = new Sprite("ghost.1",1,1.f,this);
		needChangeOfRoom = false;
	}
	
	public void setNoNeedOfChange() {
		needChangeOfRoom = false;
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
	
	@Override
    protected  boolean move(int framesForMove){	
		super.move(framesForMove);
		for (DiscreteCoordinates coord: getEnteringCells()) {
    	    if(((Demo2Area)getOwnerArea()).isDoor(coord)) {
    	    	//if at least one of the entering cells is a door
    	    	needChangeOfRoom = true;	
    	    }
    	}	
    	return true;
    }
	
	protected boolean getCanPass() {
			if (((Demo2Area)getOwnerArea()).canEnter(this, getEnteringCells())) {
		    	return true;
		    }
		    else {
		    	return false;  	
		    }		
	}
    
	
	
	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		//setCurrentPosition(getPosition());
		//actor is updating with time if keyboard order is given
		Keyboard keyboard = getOwnerArea().getKeyboard();
		Button leftArrow = keyboard.get(keyboard.LEFT);
		if(leftArrow.isDown()) {
			if(this.getOrientation()==Orientation.LEFT) {
				//Animation duration in frame number
				if (getCanPass()) {
					move(ANIMATION_DURATION);
					setCurrentPosition(getPosition().add(-0.05f, 0.00f));
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
					setCurrentPosition(getPosition().add(0.05f, 0.00f));
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
					setCurrentPosition(getPosition().add(0.00f, 0.05f));
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
					setCurrentPosition(getPosition().add(0.00f, -0.05f));
				}
				
				//super.update(deltaTime);
			}
			else {
				this.setOrientation(Orientation.DOWN);
			}
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isViewInteractable() {
		return false;
	}

	@Override
	public boolean isCellInteractable() {
		return true;
	}

	@Override
	public void draw(Canvas canvas) {
		spr.draw(canvas);// TODO Auto-generated method stub	
	}
	
}