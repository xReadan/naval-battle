package entity.gun;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import entity.Entity;
import graphics.Assets;
import main.Handler;

public class EnemyBullet extends Entity{
	
	private float enemyAngle, playerAngle, enemyX, enemyY, playerX, playerY;
	private int width, height;
	private double xVelocity, yVelocity, relativeAngle;
	
	public EnemyBullet(Handler handler, float x, float y, int width, int height, float enemyAngle, float playerAngle) {
		super(handler, x, y, width, height);
		this.enemyAngle = enemyAngle;
		this.playerAngle = playerAngle;
		this.enemyX = x;
		this.enemyY = y;
		this.handler = handler;
		this.width = width;
		this.height = height;
		playerX = handler.getWorld().getEntityManager().getPlayer().getX();
		playerY = handler.getWorld().getEntityManager().getPlayer().getY();
		
	}

	@Override
	public void tick() {
		shoot();
		rotationBounds();
		
		System.out.println("Nave: " + handler.getWorld().getEntityManager().getPlayer().getRotatedRect().getBounds2D());
		System.out.println("Posizione player: " + handler.getWorld().getEntityManager().getPlayer().getX() + " | " + handler.getWorld().getEntityManager().getPlayer().getY()+ "\n");
		System.out.println("Proiettile: " + this.getX() + " | " + this.getY() + "Dimensione: " + this.getWidth() + " | " + this.getHeight());
		
		if(this.intersect(x, y, playerX, playerY, this.getRotatedRect().getBounds2D(), handler.getWorld().getEntityManager().getPlayer().getRotatedRect().getBounds2D())){
			System.out.println("hit - ");
			damaged();
			this.active = false;
		}
		/*
		if(this.checkEntityCollisions(0, 0)){
			this.active = false;
			System.out.println("hit - ");
			damaged();
		}*/
	}
	
	private void damaged(){
		handler.getWorld().getEntityManager().getPlayer().hurt((int) (Math.random() * 10));
	}
	
	private void shoot(){
		double bulletVelocity = 8.0;
		relativeAngle = Math.toDegrees((Math.atan2(enemyX - playerX, enemyY - playerY))) - 180;
		xVelocity = (bulletVelocity) * Math.sin(Math.toRadians(relativeAngle));
		yVelocity = (bulletVelocity) * Math.cos(Math.toRadians(relativeAngle));
		y += yVelocity;
		x += xVelocity;
		
	}
	
	private void rotationBounds(){
		at = AffineTransform.getRotateInstance(Math.toRadians(relativeAngle));
		rotatedRect = at.createTransformedShape(bounds);
	}

	@Override
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.rotate(-Math.toRadians(relativeAngle), x, y);
		g2d.drawImage(Assets.bullet, (int) x + 2,(int) y + 9, width, height, null);
		g.setColor(Color.red);
		g.fillRect((int) (x + rotatedRect.getBounds2D().getX()), (int) (y + rotatedRect.getBounds2D().getY()),(int) rotatedRect.getBounds2D().getWidth(),(int) rotatedRect.getBounds2D().getHeight());
	}

	@Override
	public void die() {
	//Qui dobbiamo mettere animazione nel caso arrivi fuori range ma senza beccare nulla (guarda BulletManager tick)
		
	}

	
	//Getters and Setters
	public float getEnemyAngle() {
		return enemyAngle;
	}

	public void setEnemyAngle(float enemyAngle) {
		this.enemyAngle = enemyAngle;
	}

	public float getEnemyX() {
		return enemyX;
	}

	public void setEnemyX(float enemyX) {
		this.enemyX = enemyX;
	}

	public float getEnemyY() {
		return enemyY;
	}

	public void setEnemyY(float enemyY) {
		this.enemyY = enemyY;
	}

	public float getPlayerX() {
		return playerX;
	}

	public void setPlayerX(float playerX) {
		this.playerX = playerX;
	}

	public float getPlayerY() {
		return playerY;
	}

	public void setPlayerY(float playerY) {
		this.playerY = playerY;
	}

	
	
}
