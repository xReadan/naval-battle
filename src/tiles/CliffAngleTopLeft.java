package tiles;

import graphics.Assets;

public class CliffAngleTopLeft extends Tile{

	public CliffAngleTopLeft(int id) {
		super(Assets.cliff_angle1, id);
	}

	@Override
	public boolean isSolid(){
		return true;
	}
}
