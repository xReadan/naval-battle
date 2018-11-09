package states;

import java.awt.Graphics;

import javax.sound.sampled.FloatControl;

import audio.MinimHelper;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import main.Handler;
import world.Minimap;
import world.World;

public class GameState extends State{

	private World world;
	private AudioPlayer playerGame, playerBG, playerGame2;
	
	
	@SuppressWarnings("deprecation")
	public GameState(Handler handler){
		super(handler); 
		world=new World(handler, "res/world/world1.txt");
		handler.setWorld(world);
		
		Minim minimGame = new Minim(new MinimHelper());
		playerGame = minimGame.loadFile("GameSong.mp3");
		FloatControl gainControlGame = (FloatControl) playerGame.getControl(FloatControl.Type.MASTER_GAIN);
		gainControlGame.setValue(-10);
		playerGame.loop();
		
		Minim minimGame2 = new Minim(new MinimHelper());
		playerGame2 = minimGame2.loadFile("GameSong.mp3");
		FloatControl gainControlGame2 = (FloatControl) playerGame2.getControl(FloatControl.Type.MASTER_GAIN);
		gainControlGame2.setValue(-10);

	}
	
	@Override
	public void tick() {
		world.tick();
		getInput();
	}

	@Override
	public void render(Graphics g) {
		world.render(g);
	}	
	
	public void getInput(){
		if(handler.getKeyManager().esc){
			if(playerGame.isPlaying())
				playerGame.close();
			
			if(handler.getWorld().getEntityManager().getPlayer().getRadarSound().isPlaying())
				handler.getWorld().getEntityManager().getPlayer().getRadarSound().pause();
			
			if(handler.getWorld().getEntityManager().getPlayer().isRadar()){
				if(handler.getWorld().getEntityManager().getPlayer().getRadarSound().isPlaying())
					handler.getWorld().getEntityManager().getPlayer().getRadarSound().pause();
			}
			
			State.setState(new SettingsFromGameState(handler, this));
		}
	}
	public AudioPlayer getPlayerBG() {
		return playerBG;
	}

	public AudioPlayer getPlayerGame() {
		return playerGame;
	}
	
	public void StartGameMusic(){

		playerGame2.loop();
		
	}

	public AudioPlayer getPlayerGame2() {
		return playerGame2;
	}

	public void setPlayerGame2(AudioPlayer playerGame2) {
		this.playerGame2 = playerGame2;
	}
	
}
