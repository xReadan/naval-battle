package entity.enemy;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.Random;

import javax.sound.sampled.FloatControl;

import audio.MinimHelper;
import ddf.minim.AudioSample;
import ddf.minim.Minim;
import entity.gun.Bullet;
import entity.gun.BulletManager;
import entity.gun.EnemyBullet;
import entity.ship.Player;
import graphics.Animation;
import graphics.Assets;
import main.Handler;

public class EnemyBattleship extends Enemy{
	
	private Animation anim;
	private AudioSample bulletSound;
	private float angle = 90f, anglePlayer;
	private int range = 250;
	private long lastTime;
	private BulletManager bulletManager = new BulletManager(handler, range);
	private final long BATTLESHIP_CHARGE_TIME = 4000;
	private final long VISUAL_RANGE = 600;
	
	@SuppressWarnings("deprecation")
	public EnemyBattleship(Handler handler, float x, float y) {
		super(handler, x, y, 70, 12);
		
		this.hp = 50;
		
		bounds.setRect(0, 0, width, height);
		
		//Sounds
		Minim minimBullet = new Minim(new MinimHelper());
		bulletSound = minimBullet.loadSample("BulletFire.mpg");
		FloatControl gainControlBullet = (FloatControl) bulletSound.getControl(FloatControl.Type.MASTER_GAIN);
		gainControlBullet.setValue(-20);
		
		//Animations
		anim = new Animation(500, Assets.enemy1);
		
		lastTime = System.currentTimeMillis();
	}

	@Override
	public void tick() {
		//Animations
		anim.tick();
		
		//Movements
		anglePlayer = handler.getWorld().getEntityManager().getPlayer().getAngle();
		yMove = -speed;
		xMove = -speed;
		rotationBounds();
		
		//moveRandomly();
		
		moveIfSpotted();
		
		moveIfInRange();
		
		//Attacks
		checkAttacks();
		bulletManager.tick();
		
	}
	
	private void rotationBounds(){
		at = AffineTransform.getRotateInstance(Math.toRadians(angle), 41, 5);
		rotatedRect = at.createTransformedShape(bounds);
	}
	
	private void checkAttacks(){
		if(System.currentTimeMillis() - lastTime >= BATTLESHIP_CHARGE_TIME){
			if(x < handler.getWorld().getEntityManager().getPlayer().getX()){
				if(y < handler.getWorld().getEntityManager().getPlayer().getY()){
					if((handler.getWorld().getEntityManager().getPlayer().getY() - y) <= range && (handler.getWorld().getEntityManager().getPlayer().getX()) - x <= range){
						shoot(anglePlayer, angle);
					}
				}else if(y > handler.getWorld().getEntityManager().getPlayer().getY()){
					if((y - handler.getWorld().getEntityManager().getPlayer().getY()) <= range && (handler.getWorld().getEntityManager().getPlayer().getX()) - x <= range){
						shoot(anglePlayer, angle);
					}
		
				}

			}
			else if(x > handler.getWorld().getEntityManager().getPlayer().getX()){
				if(y < handler.getWorld().getEntityManager().getPlayer().getY()){
					if((handler.getWorld().getEntityManager().getPlayer().getY() - y) <= range && (x - handler.getWorld().getEntityManager().getPlayer().getX()) <= range){
						shoot(anglePlayer, angle);
					}
				}else if(y > handler.getWorld().getEntityManager().getPlayer().getY()){
					if((y - handler.getWorld().getEntityManager().getPlayer().getY()) <= range && (x - handler.getWorld().getEntityManager().getPlayer().getX()) <= range){
						shoot(anglePlayer, angle);
					}
		
				}
			}
		}
	}
	
	private void shoot(float playerAngle, float enemyAngle){
				bulletSound.trigger();
				
				bulletManager.addEnemyBullet(new EnemyBullet(handler, (int) (x - handler.getGameCamera().getxOffset()) + 25,(int) (y - handler.getGameCamera().getyOffset()) + 6, 4, 18, enemyAngle, playerAngle));
				//bulletManager.addEnemyBullet(new EnemyBullet(handler, (int) (x - handler.getGameCamera().getxOffset()) + 45,(int) (y - handler.getGameCamera().getyOffset()) + 6, 4, 18, enemyAngle, playerAngle));
				//bulletManager.addEnemyBullet(new EnemyBullet(handler, (int) (x - handler.getGameCamera().getxOffset()) + 65,(int) (y - handler.getGameCamera().getyOffset()) + 6, 4, 18, enemyAngle, playerAngle));
				lastTime = System.currentTimeMillis();
	}
	
	private void moveRandomly(){
			if(anglePlayer - angle < -90)
				angle -= Math.random();
			else if(anglePlayer - angle > -90)
				angle += Math.random();
			else if(anglePlayer - angle > -180)
				angle += Math.random();
		move(angle);	
	}
	
	private void moveIfSpotted(){
		
	}
	
	private void moveIfInRange(){
		
	}

	@Override
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.rotate(Math.toRadians(angle), (int) (x - handler.getGameCamera().getxOffset()) + 35,(int) (y - handler.getGameCamera().getyOffset()) + 6);
		g2d.drawImage(anim.getCurrentFrame(),(int) (x - handler.getGameCamera().getxOffset()), (int)(y - handler.getGameCamera().getyOffset()), 70, 12, null);
		bulletManager.render(g);
		g.setColor(Color.red);
		g.fillRect((int) (x + rotatedRect.getBounds2D().getX() - handler.getGameCamera().getxOffset()), (int) (y + rotatedRect.getBounds2D().getY() - handler.getGameCamera().getyOffset()),(int) rotatedRect.getBounds2D().getWidth(),(int) rotatedRect.getBounds2D().getHeight());
	}

	@Override
	public void die() {
		
		
	}
}
