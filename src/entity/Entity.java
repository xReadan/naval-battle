package entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

import main.Handler;

public abstract class Entity {

	protected Handler handler;
	public float x,y;
	protected int width, height;
	protected Rectangle2D bounds;
	protected boolean active = true;
	protected int hp;
	protected AffineTransform at = new AffineTransform();
	protected Shape rotatedRect;
	
	public static final int DEFAULT_HEALTH = 10;
	
	//Il costruttore delle entità prende il ingresso le posizioni iniziali
	public Entity(Handler handler, float x, float y, int width, int height){
		this.handler = handler;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		hp = DEFAULT_HEALTH;
		
		bounds = new Rectangle2D.Double(0, 0, width, height);
		rotatedRect = at.createTransformedShape(bounds);
	}
	
	public abstract void tick();
	
	public abstract void render (Graphics g);
	
	public abstract void die();
	
	public void hurt(int amount){
		hp -= amount;
		if(hp <= 0){
			active = false;
			die ();
		}
	}
	
	public boolean checkEntityCollisions(float xOffset, float yOffset) {
		
		for(Entity e : handler.getWorld().getEntityManager().getEntities()){
			if(e.equals(this))
				continue;
			for(Entity en : handler.getWorld().getEnemyManager().getEnemies()){	
			if(e.getCollisionBounds(0f, 0f).intersects(en.getCollisionBounds(xOffset, yOffset)))
				return true;
			}
			
		}
		
		return false;
	}

	public Rectangle2D getBounds() {
		return rotatedRect.getBounds2D();
	}

	public Rectangle2D getCollisionBounds(float xOffset, float yOffset){
		
		return new Rectangle2D.Double((int) (x + rotatedRect.getBounds2D().getX() + xOffset), (int) (y + rotatedRect.getBounds2D().getY() + yOffset), bounds.getWidth(), bounds.getHeight());
		
	}
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public Shape getRotatedRect() {
		return rotatedRect;
	}
	
	public boolean intersect(float x, float y, float xp, float yp, Rectangle2D rect1, Rectangle2D rect2) {
		/*if(x < rect1.getX() && (x + rect2.getWidth()) > xp){
			if((y + rect2.getHeight()) > yp)
				return true;
		}
		if(x < xp && (x + rect2.getWidth()) > xp){
			if(y > (yp + rect1.getHeight()))
				return true;
		}
		if(x < (xp + rect1.getWidth())){
			if((y + rect2.getHeight()) > yp)
				return true;
		}
		if(x < (xp + rect1.getWidth())){
			if(y > (yp + rect1.getHeight()))
				return true;
		}
		return false;
	}*/
		 if (x < xp) {
			    if (y <= yp) {
			        if (x + rect1.getWidth() > xp && y + rect1.getHeight() > yp)
			            return true;
			    }
			    if(y > yp) {
			        if (x + rect1.getWidth() > xp && y < yp + rect2.getHeight())
			            return true;
			    }
		 }
			if(x>xp) {
			    if (y < yp) {
			        if (x < xp + rect2.getWidth() && y + rect1.getHeight()> yp)
			            return true;
			    }
			    if(y > yp) {
			        if (x < xp + rect2.getWidth() && y < yp + rect2.getHeight())
			            return true;
			    }
			}
		 return false;
		}
}