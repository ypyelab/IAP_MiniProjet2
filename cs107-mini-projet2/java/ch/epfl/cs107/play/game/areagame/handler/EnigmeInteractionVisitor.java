package ch.epfl.cs107.play.game.areagame.handler;

import ch.epfl.cs107.play.game.enigme.EnigmeBehavior;
import ch.epfl.cs107.play.game.enigme.actor.Apple;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.game.enigme.actor.EnigmePlayer;

public interface EnigmeInteractionVisitor extends AreaInteractionVisitor {

    /**
     * Default interaction between something and an interactable
     * Notice: if this method is used, then you probably forget to cast the AreaInteractionVisitor into its correct child
     * @param other (Interactable): interactable to interact with, not null
     */
    default void interactWith(Apple apple){
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
    
}