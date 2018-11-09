package tiles;

import graphics.Assets;

public class CliffAngleBotRight extends Tile{

	public CliffAngleBotRight(int id) {
		super(Assets.cliff_angle3, id);
	}

	@Override
	public boolean isSolid(){
		return true;
	}
}

