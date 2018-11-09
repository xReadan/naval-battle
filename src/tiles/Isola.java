package tiles;

import graphics.Assets;

public class Isola extends Tile{

	public Isola(int id) {
		super(Assets.isola, id);
	}
	
	@Override
	public boolean isSolid(){
		return true;
	}

}
