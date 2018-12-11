package ch.epfl.cs107.play.game.enigme.area;

import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.enigme.EnigmeBehavior;
import ch.epfl.cs107.play.game.enigme.actor.Apple;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

public class Level2 extends EnigmeArea{

	//The begin method should get the title of the "current area"
	@Override
    public boolean begin(Window window, FileSystem fileSystem) {
		super.begin(window,fileSystem);
		//plus Door1
		registerActor(new Door(this, "LevelSelector",  new DiscreteCoordinates(2,6), new DiscreteCoordinates(5,0), new DiscreteCoordinates(5,0)));
		//plus Apple1
		registerActor(new Apple(this, new DiscreteCoordinates(5,6)));
	
		return true;
    }
	
	@Override
	public String getTitle() {
		return "Level2";
	}
	
}