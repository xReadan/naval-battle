package world;

import java.awt.Color;
import java.awt.Graphics;

import main.Handler;
import tiles.Tile;

public class Minimap {

	private Handler handler;
	private int id, xStart, yStart, xEnd, yEnd, xPlayer, yPlayer;
	public Minimap(Handler handler){
		this.handler = handler;
		xStart = 0;
		yStart = 0;
	}
	
	public void tick(){
		xPlayer = (int) ((handler.getWorld().getEntityManager().getPlayer().getX()) / 9.6);
		yPlayer = (int) ((handler.getWorld().getEntityManager().getPlayer().getY()) / 8.53);
	}
	
	public void render(Graphics g){
		g.setColor(new Color(48, 138, 178));
		g.fillRect(580, 430, 200, 150);
		g.setColor(Color.BLACK);
		g.fillRect(580 + xPlayer - 3, 430 + yPlayer - 6, 6, 7);
		for(int x = xStart; x < handler.getWorld().getWidht(); x ++){
			for(int y = yStart; y < handler.getWorld().getHeight(); y ++){
				id = handler.getWorld().getTile(x, y).getId();
				switch(id){
				case 1: g.setColor(Color.YELLOW);
						g.fillRect(580 + (x* 6), 430 + (y * 7), 6, 7);
						break;
				}
			}
		}
	}
	
}
