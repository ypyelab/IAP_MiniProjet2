package ch.epfl.cs107.play.game.enigme.area;

import java.util.List;

import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class LevelSelector extends EnigmeArea{

	@Override
	public String getTitle() {
		return "LevelSelector";
	}

	@Override
	public float getCameraScaleFactor() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean canLeave(Interactable entity, List<DiscreteCoordinates> coord) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canEnter(Interactable entity, List<DiscreteCoordinates> coord) {
		// TODO Auto-generated method stub
		return false;
	}
	
}