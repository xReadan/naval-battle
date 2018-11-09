package tiles;


import graphics.Assets;

public class CliffTop extends Tile{

	public CliffTop(int id) {
		super(Assets.cliff_top, id);
	}

	@Override
	public boolean isSolid(){
		return true;
	}

}
