import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.Serializable;

public class Message implements Serializable{
	private static final long serialVersionUID = 1L;
	public Point2D.Double location = new Point2D.Double(500, 500); //(x, y)
	public double shipAngle = 0; //angle the ship faces
	public Point2D.Double resultant = new Point2D.Double(0,0);
	public String imageType;
	public int ID;
	public String object;
	
	public Message(Entity e){
		
		object = e.getType();
		location = new Point2D.Double(e.getLocation().getX(), e.getLocation().getY());
		resultant = new Point2D.Double(e.getResultant().getX(), e.getResultant().getY());
		shipAngle = e.getEntityAngle();
		imageType = e.getImageType();
		ID = e.ID;
		System.out.println("ID: " + ID);
	}
	
	public static Message getNewMessage(Entity e){
		if(e.getType().equals("Entity")){
			Message m = new Message(e);
			return m;
		}else if(e.getType().equals("Spaceship")){
			return new SpaceshipMessage((Spaceship)e);
		}else if(e.getType().equals("Bullet")){
			return new BulletMessage((Bullet)e);
		}else{
			return null;
		}
	}
}