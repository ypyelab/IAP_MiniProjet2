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

public class TorchSp extends AreaEntity implements Logic{

	private Sprite sOn;
	private Sprite sOff;
	private float durAct;
	private float tempDurAct;
	private boolean switchOn;

	public TorchSp(Area area, DiscreteCoordinates position) {
		super(area, Orientation.DOWN,position);
		sOn = new Sprite("torch.ground.on.1",1,1.f,this);
		sOff = new Sprite("torch.ground.off",1,1.f,this);
		switchOn = true;
	}
	
	
	@Override
	public boolean isOn() {
		if(switchOn == true) {
			return true;
		}
		return false;
	}
	
	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		return 
				Collections.singletonList(getCurrentMainCellCoordinates());
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
		return false;
	}

	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		((EnigmePlayerHandler)v).interactWith(this);		
	}

	@Override
	public void draw(Canvas canvas) {
		if (switchOn) {
			sOn.draw(canvas);
		}
		else {
			sOff.draw(canvas);
		}
	}
	
	
}