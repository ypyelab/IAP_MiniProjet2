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
				EnigmeCellType cellType = EnigmeCellType.toType(this.getBehaviorMap().getRGB(this.getHeight()-1-j, i));
				getCells()[i][j] = new EnigmeCell(i,j,cellType);
				
			}
		}
		
	}
	
	
	///Cells inside behavior
	public class EnigmeCell extends Cell{
		private EnigmeCellType type;
		
		public EnigmeCell(int x, int y, EnigmeCellType type) {
			super(x, y);
			this.type=type;
		}
		
		
		@Override
		public boolean takeCellSpace() {
			return true;
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
		protected boolean canEnter(Interactable entity) {
			if (this.type==EnigmeCellType.NULL || this.type==EnigmeCellType.WALL) {
				return false;
			}
			else {
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

	public enum EnigmeCellType{
		NULL(0),
		WALL(-16777216),
		DOOR(-65536),
		WATER(-16776961),
		INDOOR_WALKABLE(-1),
		OUTDOOR_WALKABLE(-14112955);
	
		final int type;
	
		EnigmeCellType(int type){
		this.type = type;
		}
		
		static EnigmeCellType toType(int type) {	
			switch(type) {
				case 0:
					return NULL;
				case -16777216:
					return WALL;
				case -65536:
					return DOOR;
				case -16776961:
					return WATER;
				case -1:
					return INDOOR_WALKABLE;
				case -14112955:
					return OUTDOOR_WALKABLE;
				default:
					return NULL;
			}	
		
		}
	}	
	
    public boolean canLeave (Interactable entity, DiscreteCoordinates coord) {
    	return ((EnigmeCell) getCells()[coord.x][coord.y]).canLeave(entity);
    }
    
    public boolean canEnter (Interactable entity,DiscreteCoordinates coord) {
    	return ((EnigmeCell) getCells()[coord.x][coord.y]).canEnter(entity);
     }
	
}
