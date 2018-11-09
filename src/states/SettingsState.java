package states;

import java.awt.Graphics;

import javax.sound.sampled.FloatControl;

import audio.MinimHelper;
import ddf.minim.AudioPlayer;
import ddf.minim.AudioSample;
import ddf.minim.Minim;
import graphics.Assets;
import main.Handler;
import ui.ClickListener;
import ui.UIImageButton;
import ui.UIManager;

public class SettingsState extends State{
	
	private UIManager settingsManager;
	private AudioPlayer playerBG, playerBGCopy;
	private AudioSample clickSound;

	public SettingsState(Handler handler, AudioPlayer playerBG) {
		super(handler);
		
		this.playerBG = playerBG;
		
		settingsManager = new UIManager(handler);
		handler.getMouseManager().setUIManager(settingsManager);
		
		Minim minimClick = new Minim(new MinimHelper());
		clickSound = minimClick.loadSample("Click.mpg");
		FloatControl gainControlGame = (FloatControl) clickSound.getControl(FloatControl.Type.MASTER_GAIN);
		gainControlGame.setValue(-15);
		
		settingsManager.addObject(new UIImageButton(100, 450, 80, 80, Assets.btn_back, new ClickListener(){
			
			@SuppressWarnings("deprecation")
			@Override
			public void onClick() {
				
					handler.getMouseManager().setUIManager(null);
					
					clickSound.trigger();

					if(playerBG.isPlaying())
					State.setState(new MenuState(handler, playerBG));
					else{
					Minim minimBG = new Minim(new MinimHelper());
					playerBGCopy = minimBG.loadFile("BGSong.mp3");
					FloatControl gainControlBG = (FloatControl) playerBG.getControl(FloatControl.Type.MASTER_GAIN);
					gainControlBG.setValue(-15);
					State.setState(new MenuState(handler, playerBGCopy));
					}
			}
		}));
	}

	@Override
	public void tick() {
		settingsManager.tick();
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.bgState, 0, 0, 800, 600, null);
		settingsManager.render(g);
	}

}
