package entity.gun;

import java.awt.Graphics;
import java.util.LinkedList;

import graphics.Animation;
import graphics.Assets;
import main.Handler;

public class BulletManager {

	private LinkedList<Bullet> bullets = new LinkedList<Bullet>();
	private LinkedList<EnemyBullet> enemyBullets = new LinkedList<EnemyBullet>();
	private Handler handler;
	private int range;
	private long timer = System.currentTimeMillis();
	private Animation splash = new Animation(130, Assets.splash);
	private float tempX, tempY;
	private boolean isdead = false;

	
	public BulletManager(Handler handler,int range){
		this.handler = handler;
		this.range = range;
	}
	
	public void tick(){
		if(isdead){
			if(splash.getCurrentFrame() != Assets.splash[5])
				splash.tick();
		}
		for(int i = 0; i < bullets.size(); i++){
			Bullet b = bullets.get(i);
			b.tick();
			if(b.getStartX() < b.getEndX())
				if(b.x >= b.getStartX() + range){
					b.die();
					isdead = true;
					animation();
					tempX = b.getX();
					tempY = b.getY();
					bullets.remove(b);
				}
			if(b.getStartX() > b.getEndX())
				if(b.x <= b.getStartX() - range){
					b.die();
					isdead = true;
					animation();
					tempX = b.getX();
					tempY = b.getY();
					bullets.remove(b);
				}
			if(b.getStartY() < b.getEndY())
				if(b.y >= b.getStartY() + range){
					b.die();
					isdead = true;
					animation();
					tempX = b.getX();
					tempY = b.getY();
					bullets.remove(b);
				}
			if(b.getStartY() > b.getEndY())
				if(b.y <= b.getStartY() - range){
					b.die();
					isdead = true;
					animation();
					tempX = b.getX();
					tempY = b.getY();
					bullets.remove(b);
				}
			if(b.getX() < 0)
				bullets.remove(b);
			if(b.getY() < 0)
				bullets.remove(b);
			if(!b.isActive())
				bullets.remove(b);
			
		}
		for(int i = 0; i < enemyBullets.size(); i++){
			EnemyBullet b = enemyBullets.get(i);
			b.tick();
			
			if(b.getEnemyX() < b.getPlayerX())
				if(b.getX() >= b.getEnemyX() + range){
					enemyBullets.remove(b);
				}
			if(b.getEnemyX() > b.getPlayerX())
				if(b.getX() <= b.getEnemyX() - range){
					enemyBullets.remove(b);
				}
			if(b.getEnemyY() < b.getPlayerY())
				if(b.getY() >= b.getEnemyY() + range){
					enemyBullets.remove(b);
				}
			if(b.getEnemyY() > b.getPlayerY())
				if(b.getY() <= b.getEnemyY() - range){
					enemyBullets.remove(b);
				}
			if(b.getY() < 0)
				enemyBullets.remove(b);
			if(b.getX() < 0)
				enemyBullets.remove(b);
			if(!b.isActive())
				enemyBullets.remove(b);
		

		}
		
	}
	
	private void animation(){
		splash.tick();
	}
	
	
	public void render (Graphics g){
		if(isdead){
			if(splash.getCurrentFrame() != Assets.splash[5])
				g.drawImage(splash.getCurrentFrame(), (int) tempX - 15, (int) tempY - 8, 30, 16, null);
		}	
		
		for(Bullet b : bullets){
			b.render(g);
		}
		
		for(EnemyBullet b : enemyBullets){
			b.render(g);
		}
	}
	
	public void addBullet(Bullet b){
		bullets.add(b);
	}
	
	public void removeBullet(Bullet b){
		bullets.remove(b);
	}
	
	public void addEnemyBullet(EnemyBullet b){
		enemyBullets.add(b);
	}

	public void	removeEnemyBullet(EnemyBullet b){
		enemyBullets.remove(b);
	}
	
	
	//Getters and Setters
	
	public LinkedList<Bullet> getBullets() {
		return bullets;
	}

	public void setBullets(LinkedList<Bullet> bullets) {
		this.bullets = bullets;
	}

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}
}

