package entity;

import java.awt.Graphics;
import java.util.ArrayList;

import entity.enemy.Enemy;
import entity.ship.Player;
import main.Handler;

public class EntityManager {

	private Handler handler;
	private Player player;
	private ArrayList<Entity> entities;
	
	public EntityManager(Handler handler, Player player){
		this.handler = handler;
		this.player = player;
		entities = new ArrayList<Entity>();
		addEntity(player);
	}
	
	public void tick(){
		for(int i = 0; i < entities.size(); i++){
			Entity e = entities.get(i);
			e.tick();
			if(!e.isActive()){
				entities.remove(e);
			}
		}
	}
	
	public void render (Graphics g){
		for(Entity e : entities){
			e.render(g);
		}
	}

	public void addEntity(Entity e){
		entities.add(e);
	}
	//Getters and Setters
	
	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}
	
	
}
