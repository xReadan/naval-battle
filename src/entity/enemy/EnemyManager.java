package entity.enemy;

import java.awt.Graphics;
import java.util.ArrayList;

import entity.Entity;
import entity.ship.Ship;
import main.Handler;

public class EnemyManager {

	private Handler handler;
	private ArrayList<Enemy> enemies;
	private int numEnemy;
	private int xSpawn = 500, ySpawn = 50;
	
	public EnemyManager(Handler handler, int numEnemy){
		this.handler = handler;
		this.numEnemy = numEnemy;
		
		enemies = new ArrayList<Enemy>();
		for(int i = 0; i < numEnemy; i++){
			if(ySpawn > 500){
				ySpawn = 100;
				xSpawn += 100;
			}
			addEnemyBattleship(new EnemyBattleship(handler, 300 , 400));
			ySpawn += 50;
			//(float)Math.random()*600,(float) Math.random()*500)
		}
		
	}
	
	public void tick(){
		for(int i = 0; i < enemies.size(); i++){
			Enemy e = enemies.get(i);
			e.tick();
			if(!e.isActive()){
				enemies.remove(e);
			}
		}
	}
	
	public void render(Graphics g){
		for(Enemy e : enemies){
			e.render(g);
		}
	}
	public void addEnemyBattleship(EnemyBattleship e){
		enemies.add(e);
	}
	public void removeEnemyBattleship(EnemyBattleship e){
		enemies.remove(e);
	}
	public boolean isEmpty(){
		if(enemies.isEmpty())
			return true;
		else return false;
	}

	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}
	
}
