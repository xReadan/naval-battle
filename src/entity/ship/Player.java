package entity.ship;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

import javax.sound.sampled.FloatControl;

import audio.MinimHelper;
import ddf.minim.AudioPlayer;
import ddf.minim.AudioSample;
import ddf.minim.Minim;
import entity.gun.Bullet;
import entity.gun.BulletManager;
import graphics.Animation;
import graphics.Assets;
import main.Handler;
import states.State;
import world.Minimap;

public class Player extends Ship{

	private int gear = 0;
	
	//Times
	public float timeW = 0, timeW_zero = 0, timeS = 0, timeD = 0, timeA = 0, timeShoot = 0;
	public long lastTime = 0, temp = 0;
	public static float angle = 0f;
	
	//Finals
	private final int TIME_CHANGE_GEAR = 50, TIME_CHANGE_RUDDER = 13, RANGE = 250;
	protected boolean gearPressed = false;
	
	//Animations
	private Animation animLeft;
	
	//Guns
	private BulletManager bulletManager =  new BulletManager(handler,RANGE);
	private int endX = 0, endY = 0;
	private AudioSample bulletSound;
	private boolean radar = false;
	private long radarTimer = 0, radarCooldown = 0;
	private AudioPlayer radarSound;
	
	//Fonts
	private Font normalFont = new Font("TimesRoman", Font.BOLD, 10);
	private Font gearFont = new Font("TimesRoman", Font.BOLD, 13);
	
	//Minimap
	Minimap minimap = new Minimap(handler);
	
	//Rotating Bounds
	private Rectangle2D rec;

	
	@SuppressWarnings("deprecation")
	public Player(Handler handler, float x, float y) {
		super(handler, x, y, Ship.DEFAULT_SHIP_WIDTH, Ship.DEFAULT_SHIP_HEIGHT);
		
		bounds.setRect(0, 0, width, height);
		
		//Sounds
		Minim minimBullet = new Minim(new MinimHelper());
		bulletSound = minimBullet.loadSample("BulletFire.mpg");
		FloatControl gainControlBullet = (FloatControl) bulletSound.getControl(FloatControl.Type.MASTER_GAIN);
		gainControlBullet.setValue(-20);
		
		Minim minimRadar = new Minim(new MinimHelper());
		radarSound = minimRadar.loadFile("Radar.mpg");
		FloatControl gainControlRadar = (FloatControl) radarSound.getControl(FloatControl.Type.MASTER_GAIN);
		gainControlRadar.setValue(-15);
		
		//Animation
		animLeft = new Animation(500, Assets.ship_left);
		
		lastTime = System.currentTimeMillis();
		
		hp = 100;
	}

	@Override
	public void tick() {
		//Animation
		animLeft.tick();
		minimap.tick();
		
		//Movement
		getInput();
		move(angle);	
		handler.getGameCamera().centerOnEntity(this);
		rotationBounds();
	
		//Attack
		checkAttacks();
		bulletManager.tick();
		
		//Cooldowns
		radarDuration();
		
		

	
	}
	
	private void checkAttacks(){
		
		if(handler.getMouseManager().isLeftPressed()){
			
			if((System.currentTimeMillis() - lastTime) >= 1000){
				bulletSound.trigger();
				
				endX = handler.getMouseManager().getMouseX();
				endY = handler.getMouseManager().getMouseY();
				bulletManager.addBullet(new Bullet(handler, (int) (x - handler.getGameCamera().getxOffset()) + 25,(int) (y - handler.getGameCamera().getyOffset()) + 6, 4, 18, endX, endY));
				bulletManager.addBullet(new Bullet(handler, (int) (x - handler.getGameCamera().getxOffset()) + 45,(int) (y - handler.getGameCamera().getyOffset()) + 6, 4, 18, endX, endY));
				bulletManager.addBullet(new Bullet(handler, (int) (x - handler.getGameCamera().getxOffset()) + 65,(int) (y - handler.getGameCamera().getyOffset()) + 6, 4, 18, endX, endY));	
				lastTime = System.currentTimeMillis();
			}
		}
	}
	
	private void rotationBounds(){
		at = AffineTransform.getRotateInstance(Math.toRadians(angle), 41, 5);
		//rotatedRect = at.createTransformedShape(bounds);
	}
	
	private void radarDuration(){
		if(radar == true){
			if((System.currentTimeMillis() - radarTimer) >= 10000){
				radar = false;
				radarSound.pause();
			}
		}
	}
	
	@Override
	public void die(){
		System.out.print("you died.");
	}
	
	public void getInput(){
		
		//Skills
		if(handler.getKeyManager().one){
			
		}
		
		if(handler.getKeyManager().two){
			if(radar == false && (System.currentTimeMillis() - radarCooldown) >= 30000){
				radarTimer = System.currentTimeMillis();
				radarCooldown = System.currentTimeMillis();
				radar = true;
				radarSound.loop();
			}
		}
		
		if(handler.getKeyManager().three){
			
		}
		
		if(handler.getKeyManager().up) {
			
			timeW ++;
			if(timeW == TIME_CHANGE_GEAR){
				if(gear == -1) {
					gear = 0;
					xMove = 0;
					yMove = 0;
					timeW = 0;
				}
			}
			if(timeW == TIME_CHANGE_GEAR){
				if(gear == 0) {
					gear = 1;
					xMove = - speed;
					yMove = - speed;
					timeW = 0;
				}
			}
			if(timeW == TIME_CHANGE_GEAR){
				if(gear == 1) {
					gear = 2;
					xMove = - speed * 2;
					yMove = - speed * 2;
					timeW = 0;
				}
			}
			if(timeW == TIME_CHANGE_GEAR){
				if(gear == 2) {
					gear = 3;
					xMove = - speed * 3;
					yMove = - speed * 3;
					timeW = 0;
				}
			}
			if(timeW == TIME_CHANGE_GEAR){
				if(gear == 3) {
					timeW = 0;
				}
			}
		}
		
		if(handler.getKeyManager().down) {
			
			timeS ++;
			if(timeS == TIME_CHANGE_GEAR){
				if(gear == -1) {
					xMove = speed;
					yMove = speed;
					timeS = 0;
				}
			}
			if(timeS == TIME_CHANGE_GEAR){
				if(gear == 0) {
					gear = -1;
					xMove = speed;
					yMove = speed;
					timeS = 0;
				}
			}
			if(timeS == TIME_CHANGE_GEAR){
				if(gear == 1) {
					gear = 0;
					xMove = 0;
					yMove = 0;
					timeS = 0;
				}
			}
			if(timeS == TIME_CHANGE_GEAR){
				if(gear == 2) {
					gear = 1;
					xMove = - speed;
					yMove = - speed;
					timeS = 0;
				}
			}
			if(timeS == TIME_CHANGE_GEAR){
				if(gear == 3) {
					gear = 2;
					xMove = - speed * 2;
					yMove = - speed * 2;
					timeS = 0;
				}
			}
		}
		
		if(handler.getKeyManager().left){
			
			timeA ++;
			
			if(timeA >= TIME_CHANGE_RUDDER){
				if(angle == 0)
					angle = 360;
				angle -= 5;
				timeA = 0;
			}
		}
		if(handler.getKeyManager().right){
			timeD ++;
			
			if(timeD >= TIME_CHANGE_RUDDER){
				if(angle == 360)
					angle = 0;
				angle += 5;
				timeD = 0;
			}
			
		}
	}

	@Override
	public void render(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g.create();
		Graphics2D g3d = (Graphics2D) g.create();
		g2d.rotate(Math.toRadians(angle), (int) (x - handler.getGameCamera().getxOffset()) + 35,(int) (y - handler.getGameCamera().getyOffset()) + 6);
		g2d.drawImage(animLeft.getCurrentFrame(), (int) (x - handler.getGameCamera().getxOffset()),(int) (y - handler.getGameCamera().getyOffset()), width, height, null);
		//Guns
		bulletManager.render(g);
		//GUI
		g.setColor(new Color(0,170,0));
		g.fillRect(25, 455, hp, 10);
		g.drawImage(Assets.healthContainer, 10, 450, 130, 20, null);
		g.setColor(Color.BLACK);
		g.setFont(normalFont);
		g.drawString("" + hp + "/ 100", 30, 463);
		g.drawImage(Assets.compass, 12, 470, 125, 125, null);
		g3d.rotate(Math.toRadians(angle), 75, 531);
		g3d.drawImage(Assets.compassStick, 35, 523, 80, 16, null);
		g.drawImage(Assets.keyW, 145, 453, 17, 17, null);
		if(gear == 3){
			g.setColor(Color.DARK_GRAY);
			g.setFont(gearFont);
			g.drawString("3/3", 147, 485);
			g.setFont(normalFont);
			g.setColor(Color.BLACK);
			g.drawString("2/3", 147, 505);
			g.drawString("1/3", 147, 525);
			g.drawString("STOP", 147, 545);
			g.drawString("R", 147, 565);
		}
		if(gear == 2){
			g.drawString("3/3", 147, 485);
			g.setColor(Color.DARK_GRAY);
			g.setFont(gearFont);
			g.drawString("2/3", 147, 505);
			g.setFont(normalFont);
			g.setColor(Color.BLACK);
			g.drawString("1/3", 147, 525);
			g.drawString("STOP", 147, 545);
			g.drawString("R", 147, 565);
		}
		if(gear == 1){
			g.drawString("3/3", 147, 485);
			g.drawString("2/3", 147, 505);
			g.setColor(Color.DARK_GRAY);
			g.setFont(gearFont);
			g.drawString("1/3", 147, 525);
			g.setFont(normalFont);
			g.setColor(Color.BLACK);
			g.drawString("STOP", 147, 545);
			g.drawString("R", 147, 565);
		}
		if(gear == 0){
			g.drawString("3/3", 147, 485);
			g.drawString("2/3", 147, 505);
			g.drawString("1/3", 147, 525);
			g.setColor(Color.DARK_GRAY);
			g.setFont(gearFont);
			g.drawString("STOP", 147, 545);
			g.setFont(normalFont);
			g.setColor(Color.BLACK);
			g.drawString("R", 147, 565);
		}
		if(gear == -1){
			g.drawString("3/3", 147, 485);
			g.drawString("2/3", 147, 505);
			g.drawString("1/3", 147, 525);
			g.drawString("STOP", 147, 545);
			g.setColor(Color.DARK_GRAY);
			g.setFont(gearFont);
			g.drawString("R", 147, 565);
			g.setFont(normalFont);
			g.setColor(Color.BLACK);
		}
		g.drawImage(Assets.keyS, 145, 573, 17, 17, null);
		//Skills
		g.setColor(Color.BLACK);
		g.setFont(gearFont);
		g.drawImage(Assets.skillBar, 320, 540, null);
		g.drawImage(Assets.healIcon, 325, 544, 31, 31, null);
		g.drawImage(Assets.radarIcon, 361, 544, 33, 33, null);
		g.drawImage(Assets.aoeIcon, 399, 545, 30, 30, null);
		g.drawImage(Assets.key1, 333, 582, 17, 17, null);
		g.drawImage(Assets.key2, 368, 582, 17, 17, null);
		g.drawImage(Assets.key3, 405, 582, 17, 17, null);
		
		if((System.currentTimeMillis() - radarCooldown) / 1000 >= 0 && (System.currentTimeMillis() - radarCooldown) / 1000 <= 30) {
			g.drawString(30 - ((System.currentTimeMillis() - radarCooldown) / 1000) + "", 371, 535);
		}
		
		if(radar == true){
			g.drawImage(Assets.minimapFrame, 575, 425, 210, 160, null);
			minimap.render(g);
			g.setColor(Color.BLACK);
			g.setFont(gearFont);
			g.drawString(10 - ((System.currentTimeMillis() - radarTimer) / 1000) + "", 600, 420);
		}
		g.setColor(Color.red);
		g.fillRect((int) (x + rotatedRect.getBounds2D().getX() - handler.getGameCamera().getxOffset()), (int) (y + rotatedRect.getBounds2D().getY() - handler.getGameCamera().getyOffset()),(int) rotatedRect.getBounds2D().getWidth(),(int) rotatedRect.getBounds2D().getHeight());
	}

	public float getAngle() {
		return angle;
	}

	public void setAngle(float angle) {
		this.angle = angle;
	}

	public int getRANGE() {
		return RANGE;
	}

	public AudioPlayer getRadarSound() {
		return radarSound;
	}

	public long getRadarTimer() {
		return radarTimer;
	}

	public void setRadarTimer(long radarTimer) {
		this.radarTimer = radarTimer;
	}

	public boolean isRadar() {
		return radar;
	}

	public void setRadar(boolean radar) {
		this.radar = radar;
	}
	
}
