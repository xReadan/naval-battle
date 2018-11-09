package tiles;


import graphics.Assets;

public class CliffBottom extends Tile{

	public CliffBottom(int id) {
		super(Assets.cliff_bottom, id);
	}

	@Override
	public boolean isSolid(){
		return true;
	}
}
