package ch.epfl.cs107.play.game.enigme.actor;

import java.awt.Color;

import ch.epfl.cs107.play.game.actor.GraphicsEntity;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.areagame.io.ResourcePath;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;

public class Message extends GraphicsEntity{
	private final TextGraphics text1;
	private final TextGraphics text2;
	public Message(Vector position, String text1, String text2){
		super(position, new ImageGraphics(ResourcePath.getSprite("rock.3"),0.1f,0.1f,null, Vector.ZERO, 1.0f,-Float.MAX_VALUE));
		this.text1 = new TextGraphics(text1,0.9f, Color.BLACK);		
		this.text1.setParent(this);
		this.text1.setAnchor(new Vector(-9f,-8f));
		
		this.text2 = new TextGraphics(text2,0.9f, Color.BLACK);		
		this.text2.setParent(this);
		this.text2.setAnchor(new Vector(-9f,-9f));
	}

	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);
		text1.draw(canvas);
		text2.draw(canvas);
	}
	
	@Override
	public void update(float deltaTime) {		

	}
}