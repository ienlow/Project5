import java.util.Timer;
import java.util.TimerTask;

public class MainCharacter extends GameObject
{
	Timer timer = new Timer();
	public boolean isPitched;
	int count = 0;
	
	public MainCharacter(int x, int y, String image)
	{
		super();
		this.setX(x);
		this.setY(y);
		this.setImage(image);
	}
}
