package ch.epfl.cs107.play.game.enigme.actor;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public class Apple extends AreaEntity{

	private Sprite spr;
	
	public Apple(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position);
		spr = new Sprite("apple.1",1,1.f,this);
	}

	public Apple(Area area, DiscreteCoordinates position) {
		super(area, Orientation.DOWN, position);
		spr = new Sprite("apple.1",1,1.f,this);
	}
	
	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		return 
				Collections.singletonList(getCurrentMainCellCoordinates());
	}

	@Override
	public boolean takeCellSpace() {
		// non-traversable actor
		return true;
	}

	@Override
	public boolean isViewInteractable() {
		//if it is seen is interactable
		return true;
	}

	@Override
	public boolean isCellInteractable() {
		//just non-contact interaction
		return false;
	}

	@Override
	public void draw(Canvas canvas) {
		spr.draw(canvas);
	}
	
}