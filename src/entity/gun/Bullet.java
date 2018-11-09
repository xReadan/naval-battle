package entity.gun;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import entity.Entity;
import graphics.Animation;
import graphics.Assets;
import main.Handler;

public class Bullet extends Entity{

	private int startX, startY, endX, endY;
	private double xVelocity, yVelocity, angle;
	private Animation splash = new Animation(200, Assets.splash);
	private boolean die = false;
	
	public Bullet(Handler handler, float x, float y, int width, int height, int endX, int endY) {
		super(handler, x, y, width, height);
		this.endX = endX;
		this.endY = endY;
		this.startX = (int) x;
		this.startY = (int) y;
		
		bounds.setRect(0, 0, width, height);
		
	}

	public void shootBullet(){
		double bulletVelocity = 8.0;
		angle = Math.atan2(endX - startX, endY - startY);
		xVelocity = (bulletVelocity) * Math.sin(angle);
		yVelocity = (bulletVelocity) * Math.cos(angle);
		y += yVelocity;
		x += xVelocity;
	}
	
	@Override
	public void tick() {
		splash.tick();
			
		shootBullet();
		rotationBounds();
		//collisions();
		
	}
	
	/*private void collisions(){
		 for(int i = 0; i < handler.getWorld().getEnemyManager().getEnemies().size(); i++){
			 
				if(this.checkEntityCollisions(endX, 0)){
					this.active = false;
					System.out.println("hit - ");
					handler.getWorld().getEnemyManager().getEnemies().get(i).hurt((int) (Math.random() * 10));
				}
				if(this.checkEntityCollisions(0, endY)){
					this.active = false;
					System.out.println("hit - ");
					handler.getWorld().getEnemyManager().getEnemies().get(i).hurt((int) (Math.random() * 10));
				}	
		 }
	} */
	
	private void rotationBounds(){
		at = AffineTransform.getRotateInstance(angle);
		rotatedRect = at.createTransformedShape(bounds);
	}
	
	@Override
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.rotate(-angle, x, y);
		g2d.drawImage(Assets.bullet, (int) x + 2,(int) y + 9, width, height, null);
		//g.setColor(Color.red);
		//g.fillRect((int) (x + rotatedRect.getBounds2D().getX()), (int) (y + rotatedRect.getBounds2D().getY()),(int) rotatedRect.getBounds2D().getWidth(),(int) rotatedRect.getBounds2D().getHeight());
		if(die == true)
			g.drawImage(splash.getCurrentFrame(), (int) x,(int) y, null);
	}
	
	@Override
	public void die() {
		die = true;
	}

	
	//Getters and Setters
	public int getEndX() {
		return endX;
	}

	public void setEndX(int endX) {
		this.endX = endX;
	}

	public int getEndY() {
		return endY;
	}

	public void setEndY(int endY) {
		this.endY = endY;
	}

	public int getStartX() {
		return startX;
	}

	public void setStartX(int startX) {
		this.startX = startX;
	}

	public int getStartY() {
		return startY;
	}

	public void setStartY(int startY) {
		this.startY = startY;
	}

	public Animation getSplash() {
		return splash;
	}

}
