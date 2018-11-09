package states;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import javax.sound.sampled.FloatControl;

import audio.MinimHelper;
import ddf.minim.AudioPlayer;
import ddf.minim.AudioSample;
import ddf.minim.Minim;
import graphics.Assets;
import input.KeyManager;
import main.Handler;
import ui.ClickListener;
import ui.UIImageButton;
import ui.UIManager;

public class SettingsFromGameState extends State{

	private UIManager settingsFromGameManager;
	public AudioPlayer playerBG, playerGame;
	private AudioSample clickSound;
	private KeyManager keyManager = new KeyManager();
	private long radar = 0;
	
	@SuppressWarnings("deprecation")
	public SettingsFromGameState(Handler handler, GameState gameState) {
		super(handler);
		
		if(gameState.getPlayerGame2().isPlaying())
			gameState.getPlayerGame2().pause();
		
		settingsFromGameManager = new UIManager(handler);
		handler.getMouseManager().setUIManager(settingsFromGameManager);
		
		//Sounds 	
		Minim minimBG = new Minim(new MinimHelper());
		playerBG = minimBG.loadFile("BGSong.mp3");
		FloatControl gainControlBG = (FloatControl) playerBG.getControl(FloatControl.Type.MASTER_GAIN);
		gainControlBG.setValue(-15);
		
		Minim minimClick = new Minim(new MinimHelper());
		clickSound = minimClick.loadSample("Click.mpg");
		FloatControl gainControlGame = (FloatControl) clickSound.getControl(FloatControl.Type.MASTER_GAIN);
		gainControlGame.setValue(-15);
		
		playerBG.loop();
		
		
		settingsFromGameManager.addObject(new UIImageButton(100, 450, 80, 80, Assets.btn_back, new ClickListener(){
			
			@SuppressWarnings("deprecation")
			@Override
			public void onClick() {
				
					handler.getMouseManager().setUIManager(null);
					
					clickSound.trigger();
					
					if(playerBG.isPlaying())
						playerBG.close();
					
					gameState.StartGameMusic();
					State.setState(gameState);
					
					
			}
		}));
		
		settingsFromGameManager.addObject(new UIImageButton(275, 503, 250, 52, Assets.btn_exit, new ClickListener(){

			@Override
			public void onClick() {
				handler.getMouseManager().setUIManager(null);
				
				clickSound.trigger();
				
				System.exit(0);
			}
		}));
	}

	@Override
	public void tick() {
		settingsFromGameManager.tick();
		keyManager.tick();
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.bgMenu[0], 0, 0, 800, 600, null);
		settingsFromGameManager.render(g);
	}

}
