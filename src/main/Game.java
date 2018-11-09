package main;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.sound.sampled.FloatControl;

import audio.MinimHelper;
import ddf.minim.*;
import display.Display;
import graphics.Assets;
import graphics.GameCamera;
import input.KeyManager;
import input.MouseManager;
import states.MenuState;
import states.State;

public class Game implements Runnable{
	
	//Display
	private Display display;
	private int width, height;
	public String title;
	
	//Thread
	private boolean running=false;
	private Thread thread;
	
	//Graphics
	private BufferStrategy bs;
	private Graphics g;
	
	//State
	public State gameState;
	public State menuState;
	public State quitState;
	public State settingsState;
	
	//Input
	private KeyManager keyManager;
	private MouseManager mouseManager;
	
	//Camera
	private GameCamera gameCamera;
	
	//Handler
	public Handler handler;
	public SoundHandler soundHandler;

	//AudioPlayer
	public AudioPlayer playerBG;
	
	//Guns
	
	
	public Game(String title, int width, int height){
		this.width = width;
		this.height = height;
		this.title = title;
		keyManager = new KeyManager();
		mouseManager = new MouseManager();
		
	}
	
	@SuppressWarnings("deprecation")
	private void init(){
		
		display=new Display(title, width,height);
		display.getFrame().addKeyListener(keyManager);
		display.getFrame().addMouseListener(mouseManager);
		display.getFrame().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);
		Assets.init();
		
		
		handler = new Handler(this);
		soundHandler = new SoundHandler (this, handler);
		gameCamera = new GameCamera(handler,0,0);
		
		Minim minimBG = new Minim(new MinimHelper());
		playerBG = minimBG.loadFile("BGSong.mp3");
		FloatControl gainControlBG = (FloatControl) playerBG.getControl(FloatControl.Type.MASTER_GAIN);
		gainControlBG.setValue(-15);
		
		menuState = new MenuState(handler, playerBG);
		
		State.setState(menuState);

	}

	private void tick(){
		keyManager.tick();
				
		if(State.getState()!=null)
			State.getState().tick();
		
	}
	
	private void render(){
		bs=display.getCanvas().getBufferStrategy();
		if(bs==null){
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		
		g=bs.getDrawGraphics();
		//Clear screen
		
		g.clearRect(0,0,width,height);
		//Draw here
		
		if(State.getState()!=null)
			State.getState().render(g);
		
		//End drawing
		bs.show();
		g.dispose();
		
		
	}
	
	public void run(){
		
		init();
		
		int fps=60;
		double timePerTick=1000000000/fps;
		double delta=0;
		long now;
		long lastTime=System.nanoTime();
		long timer=0;
		int ticks=0;
		
		while(running){
			now=System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer+=now-lastTime;
			lastTime=now;
			
			if(delta >= 1){
				tick();
				render();
				ticks++;
				delta--;
			}
			if(timer >=1000000000){
				//System.out.println("Ticks and Frames: " +ticks);
				ticks=0;
				timer=0;
			}
		}
		stop();
	}
	
	public KeyManager getKeyManager(){
		return keyManager;
	}
	
	public MouseManager getMouseManager(){
		return mouseManager;
	}
	
	public GameCamera getGameCamera(){
		return gameCamera;
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	public synchronized void start(){
		if(running)
			return;
		running=true;
		thread=new Thread(this);
		thread.start();
	}
	
	public synchronized void stop(){
		if(!running)
			return;
		running=false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public AudioPlayer getPlayerBG() {
		return playerBG;
	}

	public void setPlayerBG(AudioPlayer playerBG) {
		this.playerBG = playerBG;
	}

	public Display getDisplay() {
		return display;
	}

	public BufferStrategy getBs() {
		return bs;
	}

}
