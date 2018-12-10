package ch.epfl.cs107.play.game.enigme;

import ch.epfl.cs107.play.game.areagame.AreaBehavior;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

public class EnigmeBehavior extends AreaBehavior{

	///Cells inside behavior
	public class EnigmeCell extends Cell{

		public EnigmeCell(int x, int y) {
			super(x, y);
			// TODO Auto-generated constructor stub
		}

		@Override
		public boolean takeCellSpace() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isViewInteractable() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isCellInteractable() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		protected boolean canEnter(Interactable entity) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		protected boolean canLeave(Interactable entity) {
			// TODO Auto-generated method stub
			return false;
		}
		
	}
	
	public EnigmeBehavior(Window window, String fileName) {
		super(window, fileName);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean canLeave(Interactable entity, DiscreteCoordinates coord) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canEnter(Interactable entity, DiscreteCoordinates coord) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
