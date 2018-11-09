package tiles;

import graphics.Assets;

public class CliffAngleBotLeft extends Tile{

	public CliffAngleBotLeft(int id) {
		super(Assets.cliff_angle4, id);
	}

	@Override
	public boolean isSolid(){
		return true;
	}
}
