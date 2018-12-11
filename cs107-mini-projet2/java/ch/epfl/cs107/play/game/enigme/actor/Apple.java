package ch.epfl.cs107.play.game.enigme.actor;


import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public class Apple extends Collectable{

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
	public void draw(Canvas canvas) {
		spr.draw(canvas);
	}
	
}