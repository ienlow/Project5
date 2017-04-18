import java.util.*;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.TextField;

public class SlickGame extends BasicGame
{
	private MainCharacter batter;
	private MainCharacter batter2;
	private MainCharacter batter3;
	private MainCharacter batter4;
	private MainCharacter pitcher;
	private MainCharacter pitcher2;
	private MainCharacter pitcher3;
	private MainCharacter baseball;
	TextField score;
	private int points;
	Timer timer;
	GameObject background;
	Camera camera;
	GameObject clouds;
	
	public SlickGame(String title)
	{
		super(title);
	}
	/*
	 * Contains camera, background, batters for context, pitchers for context.
	 */
	@Override
	public void init(GameContainer gc) throws SlickException
	{
		camera = new Camera();
		timer = new Timer();
		batter = new MainCharacter(75, 600, "batter1.png");
		batter2 = new MainCharacter(50, 200, "batter2.png");
		batter3 = new MainCharacter(50, 200, "batter3.png");
		batter4 = new MainCharacter(50, 200, "batter4.png");
		baseball = new MainCharacter(700, 860, "baseball.png");
		background = new GameObject();
		pitcher = new MainCharacter(600, 610, "pitcher1.png");
		pitcher2 = new MainCharacter(500, 235, "pitcher2.png");
		pitcher3 = new MainCharacter(500, 235, "pitcher3.png");
		background.setImage("baseballstadium.png");
		baseball.getImage().setAlpha(0.0f);
		score = new TextField(gc,gc.getDefaultFont(),250,200,100,25);
	}
	
	@Override
	public void update(GameContainer gc, int delta) throws SlickException 
	{
		Input i = gc.getInput();
		/*
		 * Pitcher animation begins, sets ball speed and resets animation.
		 */
		if(i.isKeyPressed(Input.KEY_S))
		{
			baseball.getImage().setAlpha(0.0f);
			batter.setImage("batter1.png");
			baseball.setXVel(0.0f);
			baseball.setYVel(0.0f);
			baseball.setX(700);
			baseball.setY(660);
			pitcher.setImage("pitcher1.png");
				
			pitcher.setImage("pitcher2.png");
			timer.schedule(new TimerTask()
			{
				@Override
				public void run()
				{
					pitcher.setImage("pitcher3.png");
					baseball.getImage().setAlpha(1.0f);
					baseball.setXVel(-1.2f);
				}
			}, 900);
			timer.schedule(new TimerTask()
			{
				@Override
				public void run()
				{
					pitcher.setImage("pitcher1.png");
				}
			}, 1500);
			
		}
		/*
		 * Batter animation begins, keeps track of score.
		 */
		if (i.isKeyPressed(Input.KEY_SPACE))
		{
			score.setText("");
			batter.setImage("batter2.png");
			/*
			 * Pop up
			 */
			if (baseball.getX() >= 200 && baseball.getX() <= 230)
			{
				baseball.setXVel(0.2f);
				baseball.setYVel(-0.5f);
				points += 20;
				score.setText("" + points);
			}
			/*
			 * Home run
			 */
			if (baseball.getX() >= 150 && baseball.getX() <= 199)
			{
				baseball.setXVel(1.2f);
				baseball.setYVel(-0.2f);
				points += 100;
				timer.schedule(new TimerTask() {
					@Override
					public void run(){
						score.setText("" + points);
					}
				}, 1000);
				score.setText("HOMERUN!");
			}
			/*
			 * Ground ball
			 */
			else if (baseball.getX() >= 70 && baseball.getX() <= 149)
			{
				baseball.setXVel(1.0f);
				baseball.setYVel(0.1f);
				points += 20;
				score.setText("" + points);
			}
			/*
			 * Foul ball
			 */
			else if (baseball.getX() < 69)
			{
				baseball.setYVel(-0.4f);
			}
			timer.schedule(new TimerTask() {
				@Override
				public void run(){
					batter.setImage("batter3.png");
				}
			}, 170);
			timer.schedule(new TimerTask() {
				@Override
				public void run(){
					batter.setImage("batter4.png");
				}
			}, 300);
			timer.schedule(new TimerTask() {
				@Override
				public void run(){
					batter.setImage("batter1.png");
				}
			}, 1000);
		}
		
		baseball.setY(baseball.getY() + (baseball.getYVel() * delta));
		baseball.setX(baseball.getX() + (baseball.getXVel() * delta));
		if (points >= 1000)
			score.setText("WINNER!");
	}
	/*
	 * Renders background, camera, batter, pitcher, baseball and TextField
	 */
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		g.setBackground(Color.white);
		background.getImage().draw(0,0);
		camera.setCameraX(batter.getX()-50);
		g.translate(camera.getCameraX(),camera.getCameraY());
		pitcher.getImage().draw(pitcher.getX(), pitcher.getY(), pitcher.getScale());
		baseball.getImage().draw(baseball.getX(),baseball.getY(),baseball.getScale());
		batter.getImage().draw(batter.getX(),batter.getY(),batter.getScale());
		score.render(gc, g);
		score.setTextColor(Color.white);
		score.setBackgroundColor(Color.black);
		score.setAcceptingInput(false);
	}
}
