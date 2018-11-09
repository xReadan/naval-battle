package tiles;

import graphics.Assets;

public class CliffRight extends Tile{

	public CliffRight(int id) {
		super(Assets.cliff_right, id);
	}
	
	@Override
	public boolean isSolid(){
		return true;
	}

}
