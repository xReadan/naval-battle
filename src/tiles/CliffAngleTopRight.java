package tiles;

import graphics.Assets;

public class CliffAngleTopRight extends Tile{

	public CliffAngleTopRight(int id) {
		super(Assets.cliff_angle2, id);
	}

	@Override
	public boolean isSolid(){
		return true;
	}
}
