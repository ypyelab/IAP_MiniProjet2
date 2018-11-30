package ch.epfl.cs107.play.game.demo1;
import ch.epfl.cs107.play.game.Game;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.window.Window;

public class Demo1 implements Game {
	public static void main(String [] argv){
		
	}
	
	@Override
	public String getTitle() {
		return "Demo1";
	}
	
	public int getFrameRate() {
		return 24;
	}
	
	@Override
    public boolean begin(Window window, FileSystem fileSystem) {
    	return true;
    }
	
	@Override
	public void update(float deltaTime) {};
	
	@Override
	public void end() {};
}