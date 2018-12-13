package ch.epfl.cs107.play.game.enigme.actor;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.actor.EnigmePlayer.EnigmePlayerHandler;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Canvas;

public class SignalRock extends AreaEntity{
	
	Logic log;
	Sprite spr;
	
	public SignalRock(Area area, DiscreteCoordinates position,Logic log) {
		super(area, Orientation.DOWN, position);
		this.log = log;
		spr = new Sprite("rock.3",1,1.f,this);
	}
	
	@Override
	public boolean takeCellSpace() {
		if(log.isOn()) {
			return false;
		}
		return true;
	}
	
	@Override
	public boolean isCellInteractable() {
		if(log.isOn()) {
			return true;
		}
		return false;
	}
	
	@Override
	public boolean isViewInteractable() {
		return false;
	}
	
	@Override
	public void draw(Canvas canvas) {
		if(log.isOn()) {
			//don't draw anything
		}
		else {
			spr.draw(canvas);
		}
	}

	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		return 
				Collections.singletonList(getCurrentMainCellCoordinates());
	}

	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		((EnigmePlayerHandler)v).interactWith(this);
	}
	
}