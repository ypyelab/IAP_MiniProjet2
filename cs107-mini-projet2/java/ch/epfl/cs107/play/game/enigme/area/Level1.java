package ch.epfl.cs107.play.game.enigme.area;

import java.util.List;

import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.enigme.EnigmeBehavior;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

public class Level1 extends EnigmeArea{

	//The begin method should get the title of the "current area"
	@Override
    public boolean begin(Window window, FileSystem fileSystem) {
		super.begin(window,fileSystem);
		//plus Door1
		registerActor(new Door(this, "LevelSelector",  new DiscreteCoordinates(1,6), new DiscreteCoordinates(5,0), new DiscreteCoordinates(5,0)));
		
		return true;
    }
	
	@Override
	public String getTitle() {
		return "Level1";
	}

	
}
