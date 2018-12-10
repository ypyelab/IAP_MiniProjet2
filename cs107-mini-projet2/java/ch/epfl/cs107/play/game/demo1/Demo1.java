package ch.epfl.cs107.play.game.demo1;
import java.awt.Color;

import ch.epfl.cs107.play.game.Game;
import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.GraphicsEntity;
import ch.epfl.cs107.play.game.actor.ShapeGraphics;
import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.demo1.actor.MovingRock;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Circle;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;

public class Demo1 implements Game {
	//Our game has an actor
	private Actor actor1;
	private Actor actor2;
	//We have where to draw, and the information of what to draw
	private Window window;
	private FileSystem fileSystem;
	
	@Override
	public String getTitle() {
		return "Demo1";
	}
	
	public int getFrameRate() {
		return 24;
	}
	
	@Override
    public boolean begin(Window window, FileSystem fileSystem) {
		//We define the actor in the game
		float radius = 0.2f;
		actor1 = new GraphicsEntity(Vector.ZERO, new ShapeGraphics(new Circle(radius),null,Color.RED,0.005f)); 
		actor2 = new MovingRock(new Vector(0.2f,0.2f),"Hello, I'm a moving rock");
		
		//Initialize window 
		this.window = window;
		this.fileSystem = fileSystem;
		
		//Transformation on the window
		Transform viewTransform = Transform.I.scaled(1).translated(Vector.ZERO);
		window.setRelativeTransform(viewTransform);
		
		return true;
    }
	
	@Override
	public void update(float deltaTime) {
		
		//actor2 is updating with time if keyboard order is given
		Keyboard keyboard = window.getKeyboard();
		Button downArrow = keyboard.get(Keyboard.DOWN);
		if(downArrow.isDown()) {
			actor2.update(deltaTime);
		}
		
		actor1.draw(window);
		actor2.draw(window);
		
		//Ready to BOUM! if the two objects shock
		Vector difPos = actor2.getPosition().sub(actor1.getPosition());
		TextGraphics boumText = new TextGraphics("BOUM!!!",0.04f, Color.RED);
		//According to position of actor2 in a given time  
		//select the coordinate reference 
		//that is going to be taken into account for the 
		//calculation of the distance between both actors
		if (actor2.getPosition().x<actor1.getPosition().x) {
			float widthIm= 0.1f;
			float heightIm= 0.1f;
			Vector a2new = new Vector(actor2.getPosition().x+widthIm, actor2.getPosition().y+heightIm);
			difPos = a2new.sub(actor1.getPosition());
		}
		
		float radius = 0.2f;
		if (Math.sqrt(Math.pow(difPos.x,2)+Math.pow(difPos.y,2))<radius) {
			boumText.draw(window);
		}
	}
	
	@Override
	public void end() {};
}