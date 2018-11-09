package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener{

	private boolean[] keys;
	public boolean up,down,left,right;
	public boolean esc;
	public boolean one, two, three;
	
	public KeyManager(){
		keys=new boolean[256];
	}
	
	public void tick(){
		up = keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_D];
		
		esc = keys[KeyEvent.VK_ESCAPE];
		
		one = keys[KeyEvent.VK_1];
		two = keys[KeyEvent.VK_2];
		three = keys[KeyEvent.VK_3];
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()]=true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()]=false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

}
