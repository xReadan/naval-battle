package main;

import ddf.minim.AudioPlayer;

public class SoundHandler {
	
	private Handler handler;
	private Game game;
	
	public SoundHandler(Game game, Handler handler){
		this.handler = handler;
		this.game = game;
		
	}
}