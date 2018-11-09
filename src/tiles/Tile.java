package tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {
	
	public static Tile[] tiles = new Tile[10];
	public static Tile Mare = new Mare(0);
	public static Tile Isola = new Isola(1);
	public static Tile Cliff_top = new CliffTop(2);
	public static Tile Cliff_bottom = new CliffBottom(3);
	public static Tile Cliff_left = new CliffLeft(4);
	public static Tile Cliff_right = new CliffRight(5);
	public static Tile Cliff_angle1 = new CliffAngleTopLeft(6);
	public static Tile Cliff_angle2 = new CliffAngleTopRight(7);
	public static Tile Cliff_angle3 = new CliffAngleBotRight(8);
	public static Tile Cliff_angle4 = new CliffAngleBotLeft(9);
	
	public static final int TILEWIDTH=64, TILEHEIGHT=64;
	
	protected BufferedImage[] texture;
	protected final int id;
	
	public Tile(BufferedImage[] texture, int id){
		this.texture=texture;
		this.id=id;
		
		tiles[id]=this;
	}
	
	public void tick(){
		
	}
	
	public void render(Graphics g, int x, int y){
		g.drawImage(texture[0], x, y, TILEWIDTH, TILEHEIGHT, null);
	}
	
	public boolean isSolid(){
		return false;
	}
	
	public int getId(){
		return id;
	}
	
}
