import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class Driver 
{
	private final static int GAME_WIDTH = 900;
	private final static int GAME_HEIGHT = 900;
	
	public static void main(String[] args)
	{
		SlickGame game = new SlickGame("Project 5");
		AppGameContainer container;
		try
		{
			container = new AppGameContainer(game,GAME_WIDTH,GAME_HEIGHT,false);
			container.start();
			container.setShowFPS(false);
		}
		catch (SlickException e)
		{
		}
	}
}
