package tiles;

import graphics.Assets;

public class CliffLeft extends Tile{

	public CliffLeft(int id) {
		super(Assets.cliff_left, id);
	}

	@Override
	public boolean isSolid(){
		return true;
	}
}

