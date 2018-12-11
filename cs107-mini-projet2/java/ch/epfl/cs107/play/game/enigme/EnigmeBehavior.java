package ch.epfl.cs107.play.game.enigme;

import ch.epfl.cs107.play.game.areagame.AreaBehavior;
import ch.epfl.cs107.play.game.areagame.AreaBehavior.Cell;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.areagame.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.Demo2Behavior.Demo2Cell;
import ch.epfl.cs107.play.game.enigme.Demo2Behavior.Demo2CellType;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

public class EnigmeBehavior extends AreaBehavior{

	public EnigmeBehavior(Window window, String fileName){
		super(window,fileName);
		
		//fill area
		for (int i = 0; i < this.getHeight();i++) {
			for (int j = 0; j < this.getWidth(); j++) {
				getCells()[i][j] = new EnigmeCell(i,j);
			}
		}
	}
	
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
			return true;
		}

		@Override
		protected boolean canEnter(Interactable entity) {
    		int sum = 0;
			for(Interactable interactable: this.getInteractables()) {
    			if(interactable.takeCellSpace()) {
    				sum = sum++;
    			}
			}
		
    		if(sum!=0) {
    			return false;
    		}
    		else {
    			return true;
    		}
		}
			
		@Override
		protected boolean canLeave(Interactable entity) {
			return true;
		}

		@Override
		public void acceptInteraction(AreaInteractionVisitor v) {
			//EnigmePlayer is not asking for this interaction
			//((EnigmeInteractionVisitor)v).interactWith(this);
		}
		
		
	}

	
    public boolean canLeave (Interactable entity, DiscreteCoordinates coord) {
    	return ((EnigmeCell) getCells()[coord.x][coord.y]).canLeave(entity);
    }
    
    public boolean canEnter (Interactable entity,DiscreteCoordinates coord) {
    	return ((EnigmeCell) getCells()[coord.x][coord.y]).canEnter(entity);
     }
  
	
}
