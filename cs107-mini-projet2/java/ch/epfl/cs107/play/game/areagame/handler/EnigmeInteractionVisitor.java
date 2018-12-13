package ch.epfl.cs107.play.game.areagame.handler;

import ch.epfl.cs107.play.game.enigme.EnigmeBehavior;
import ch.epfl.cs107.play.game.enigme.actor.Collectable;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.game.enigme.actor.EnigmePlayer;
import ch.epfl.cs107.play.game.enigme.actor.Message;
import ch.epfl.cs107.play.game.enigme.actor.PressurePlate;
import ch.epfl.cs107.play.game.enigme.actor.SignalDoor;
import ch.epfl.cs107.play.game.enigme.actor.SignalRock;
import ch.epfl.cs107.play.game.enigme.actor.Switchable;
import ch.epfl.cs107.play.game.enigme.actor.TorchSp;

public interface EnigmeInteractionVisitor extends AreaInteractionVisitor {

    /**
     * Default interaction between something and an interactable
     * Notice: if this method is used, then you probably forget to cast the AreaInteractionVisitor into its correct child
     * @param other (Interactable): interactable to interact with, not null
     */
    default void interactWith(Collectable collectable){
        //by default the interaction is empty
    }
    
    default void interactWith(Door door){
        //by default the interaction is empty
    }
    
    default void interactWith(EnigmePlayer player){
        //by default the interaction is empty
    }
    
    default void interactWith(EnigmeBehavior.EnigmeCell cell){
        //by default the interaction is empty
    }

	default void interactWith(Switchable switchable) {
		//by default nothing
	}
	
	default void interactWith(PressurePlate presPlate) {
		//by default nothing
	}
	
	default void interactWith(SignalDoor sigDoor) {
		//by default nothing
	}
	
	default void interactWith(SignalRock sigRock) {
		//by default nothing
	}
	
	default void interactWith(TorchSp torchSp) {
		//by default nothing
	}
	
	default void interactWith(Message message) {
		//by default nothing
	}
}