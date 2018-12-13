package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Canvas;

public class Key extends Collectable implements Logic{
	
	//attributes specific to this class
	private Sprite spr;

	public Key(Area area, DiscreteCoordinates position) {
		super(area, position);
		spr = new Sprite("key.1",1,1.f,this);
	}
	
	@Override
	public void draw(Canvas canvas) {
		spr.draw(canvas);
	}

	@Override
	public boolean isOn() {
		if(collected == false) {
			return false;
		}
		return true;
	}
	
}