package ch.epfl.cs107.play.game.enigme;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.AreaBehavior;
import ch.epfl.cs107.play.game.areagame.AreaBehavior.Cell;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Image;
import ch.epfl.cs107.play.window.Window;

public class Demo2Behavior extends AreaBehavior{
	/// The behavior is an Image of size height x width

	/// Conversion of the image into an array of cells
    
	public Demo2Behavior(Window window, String fileName){
		super(window,fileName);
		
		//fill area
		for (int i = 0; i < this.getHeight();i++) {
			for (int j=0; j < this.getWidth(); j++) {
				Demo2CellType cellType = Demo2CellType.toType(this.getBehaviorMap().getRGB(this.getHeight()-1-j,i));
				getCells()[i][j] = new Demo2Cell(i,j,cellType);
			}
		}
	}
	
	public boolean isDoor(DiscreteCoordinates coord) {
		return ((Demo2Cell) getCells()[coord.x][coord.y]).isDoor();
	}
	
	
    public boolean canEnter (Interactable entity, DiscreteCoordinates coord) {
		return ((Demo2Cell) getCells()[coord.x][coord.y]).canEnter(entity);
    }
	
    public class Demo2Cell extends Cell{
    	Demo2CellType type;
    	private Demo2Cell(int x, int y, Demo2CellType type) {
    		super(x,y);
    		this.type=type;
    	}
    	protected boolean isDoor() {
    		return (this.type==Demo2Behavior.Demo2CellType.DOOR );
    	}
    	//...
		@Override
		public boolean takeCellSpace() {
			//don't understand what is happening here
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
		protected boolean canEnter(Interactable entity) {
			
			if(this.type == Demo2CellType.NULL || this.type == Demo2CellType.WALL) {	
				return false;
			}
			else{
				return true;
			}
		}
		@Override
		protected boolean canLeave(Interactable entity) {
			return true;
		}

    }
    
	public enum Demo2CellType{
		NULL(0),
		WALL(-16777216),
		DOOR(-65536),
		WATER(-16776961),
		INDOOR_WALKABLE(-1),
		OUTDOOR_WALKABLE(-14112955);
	
		final int type;
	
		Demo2CellType(int type){
		this.type = type;
		}
		
		static Demo2CellType toType(int type) {	
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
	
}