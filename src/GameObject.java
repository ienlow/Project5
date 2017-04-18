import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class GameObject 
{
	private float x = 0;
	private float y = 0;
	private float xVal = 0;
	private float yVal = 0;
	private float width = 0;
	private float height = 0;
	private float scale = 1.0f;
	private Image imgObj;
	
	public float getX()
	{
		return x;
	}
	
	public float getY()
	{
		return y;
	}
	
	public float getXVel()
	{
		return xVal;
	}
	
	public float getYVel()
	{
		return yVal;
	}
	
	public float getWidth()
	{
		if (imgObj != null)
			return width;
		else 
			return 0;
	}
	
	public float getHeight()
	{
		if (imgObj != null)
			return height;
		else 
			return 0;
	}
	
	public Image getImage()
	{
		return imgObj;
	}
	
	public void setX(float x)
	{
		this.x = x;
	}
	
	public void setY(float y)
	{
		this.y = y;
	}
	
	public void setXVel(float xVel)
	{
		this.xVal = xVel;
	}
	
	public void setYVel(float yVel)
	{
		this.yVal = yVel;
	}
	
	public void setScale(float scale)
	{
		this.scale = scale;
	}
	
	public float getScale()
	{
		return this.scale;
	}
	
	public void setImage(String fileName)
	{
		try 
		{
			imgObj = new Image("res/" + fileName);
		}
		catch(SlickException e) 
		{
			System.err.print("Error");
		}
	}
}
