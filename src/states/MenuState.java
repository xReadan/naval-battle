package states;

import java.awt.Graphics;

import javax.sound.sampled.FloatControl;

import audio.MinimHelper;
import ddf.minim.*;
import graphics.Assets;
import main.Handler;
import ui.ClickListener;
import ui.UIImageButton;
import ui.UIManager;

public class MenuState extends State{
	
	public UIManager uiManager;
	
	private AudioPlayer playerBG;
	private AudioSample clickSound;
	
	@SuppressWarnings("deprecation")
	public MenuState(Handler handler, AudioPlayer playerBG){
		super(handler);
		
		this.playerBG = playerBG;
			
		//Interface
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUIManager(uiManager);
		
		//Sounds
		Minim minimClick = new Minim(new MinimHelper());
		clickSound = minimClick.loadSample("Click.mpg");
		FloatControl gainControlGame = (FloatControl) clickSound.getControl(FloatControl.Type.MASTER_GAIN);
		gainControlGame.setValue(-15);

		
		uiManager.addObject(new UIImageButton(275, 185, 250, 52, Assets.btn_start, new ClickListener(){

			@Override
			public void onClick() {
				handler.getMouseManager().setUIManager(null);
				State.setState(new GameState(handler));
				
				if(playerBG.isPlaying())
					playerBG.close();
			}
		}));
		//10 pixel di spazio
		
		uiManager.addObject(new UIImageButton(275, 247, 250, 52, Assets.btn_records, new ClickListener(){

			@Override
			public void onClick() {
				handler.getMouseManager().setUIManager(null);
				State.setState(new RecordsState(handler));
				
				clickSound.trigger();
				
				if(playerBG.isPlaying())
					playerBG.close();
			}
		}));
		
		uiManager.addObject(new UIImageButton(275, 309, 250, 52, Assets.btn_settings, new ClickListener(){

			@Override
			public void onClick() {
				handler.getMouseManager().setUIManager(null);
				State.setState(new SettingsState(handler, playerBG));
				
				clickSound.trigger();
			}
		}));
		
		uiManager.addObject(new UIImageButton(275, 371, 250, 52, Assets.btn_credits, new ClickListener(){

			@Override
			public void onClick() {
				handler.getMouseManager().setUIManager(null);
				
				clickSound.trigger();
				
			}
		}));	
		
		uiManager.addObject(new UIImageButton(275, 433, 250, 52, Assets.btn_exit, new ClickListener(){

			@Override
			public void onClick() {
				handler.getMouseManager().setUIManager(null);
				
				clickSound.trigger();
				
				System.exit(0);
			}
		}));
		
		//Music Player
		if(playerBG.isPlaying() == false){
			playerBG.loop();
		}
		
	}
	
	@Override
	public void tick() {
		uiManager.tick();
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.bgMenu[0], 0, 0, 800, 600, null);
		uiManager.render(g);
	}
	
}
