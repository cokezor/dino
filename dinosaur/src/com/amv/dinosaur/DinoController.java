package com.amv.dinosaur;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;

public class DinoController {
	private Dinosaur dino;
	private boolean grounded = true;
	private boolean jumpPressed;
	private long jumpPressTime;

	final float JUMPVELOCITY = 100f;
	final float GRAVITY = -156.8f;
	final float WALKSPEED = 64f;
	private static final long LONG_JUMP_PRESS = 250l;

	private TiledMapTileLayer collisionLayer;
	boolean collideX = false, collideY = false;

	public DinoController(Dinosaur dino, TiledMapTileLayer collisionLayer) {
		this.dino = dino;
		this.collisionLayer = collisionLayer;
	}

	enum Keys {
		JUMP, LEFT, RIGHT
	}

	static Map<Keys, Boolean> keys = new HashMap<DinoController.Keys, Boolean>();
	static {
		keys.put(Keys.JUMP, false);
		keys.put(Keys.LEFT, false);
		keys.put(Keys.RIGHT, false);
	}

	public void jumpPressed() {
		keys.put(Keys.JUMP, true);
	}

	public void jumpReleased() {
		keys.put(Keys.JUMP, false);
	}

	public void leftPressed() {
		keys.put(Keys.LEFT, true);
	}

	public void leftReleased() {
		keys.put(Keys.LEFT, false);
	}

	public void rightPressed() {
		keys.put(Keys.RIGHT, true);
	}

	public void rightReleased() {
		keys.put(Keys.RIGHT, false);
	}

	public void update(float delta) {
		// update bounds
		dino.bounds.x = dino.position.x;
		dino.bounds.y = dino.position.y;
		processInput(delta);

		//apply gravity
		dino.velocity.y += GRAVITY * delta;
		//clamp velocity
		
		if (dino.velocity.y > 300){
			dino.velocity.y = 300;
		} else if (dino.velocity.y < -300){
			dino.velocity.y = -300;
		}
		
		// position += v * time
		dino.position.add(dino.velocity.cpy().scl(delta));
		
		System.out.println(dino.velocity);
		int cellX = (int) ((dino.getX()) / 32);
		int cellY = (int) ((dino.getY()) / 32);
		System.out.println("Cell (" + cellX + "," + cellY + ")" + " Position (" + dino.position.x + "," + dino.position.y + ")");
		
		//if dino is going up
		if (dino.velocity.y > 0) {
			collideY = collisionLayer
					.getCell(
							(int) ((dino.getX() + dino.getBounds().width / 2) / 32),
							(int) ((dino.getY() + dino.getBounds().height) / 32))
					.getTile().getProperties().containsKey("blocked");
			//if dino is going down
		} else if (dino.velocity.y < 0) {
			collideY = collisionLayer
					.getCell(
							(int) ((dino.getX() + dino.getBounds().width / 2) / 32),
							(int) ((dino.getY()) / 32))
					.getTile().getProperties().containsKey("blocked");
		}
		
		//if dino is going down and there is a block
		if (collideY && dino.velocity.y < 0){
			dino.position.y = (cellY + 1) * 32;
			dino.velocity.y = 0;
		}
		
	}

	public TiledMapTileLayer getCollisionLayer() {
		return collisionLayer;
	}

	public void setCollisionLayer(TiledMapTileLayer collisionLayer) {
		this.collisionLayer = collisionLayer;
	}

	private void processInput(float delta) {
		if (keys.get(Keys.JUMP)) {
			if (grounded) {
				jumpPressed = true;
				//grounded = false;
				jumpPressTime = System.currentTimeMillis();
				dino.velocity.y = JUMPVELOCITY;
			}

			else {
				if (jumpPressed
						&& ((System.currentTimeMillis() - jumpPressTime) >= LONG_JUMP_PRESS)) {
					jumpPressed = false;
				}
				// if user is holding on to jump
				else {
					if (jumpPressed)
						dino.velocity.y = JUMPVELOCITY;
				}
			}
		}

		if (keys.get(Keys.RIGHT)) {
			collideX = collisionLayer
					.getCell(
							(int) ((dino.getX() + dino.getBounds().width) / 32),
							(int) ((dino.getY()) / 32)).getTile()
					.getProperties().containsKey("blocked");
			if (collideX){
				dino.velocity.x = 0;
			}
			else{ 
				dino.velocity.x = WALKSPEED;
			}
			
		}

		else if (keys.get(Keys.LEFT)) {
			collideX = collisionLayer
					.getCell((int) ((dino.getX()) / 32),
							(int) ((dino.getY()) / 32)).getTile()
					.getProperties().containsKey("blocked");
			if (collideX){
				dino.velocity.x = 0;
			}
			else{
				dino.velocity.x = -WALKSPEED;
			}
		}
		//if neither left or right are pressed
		else {
			dino.velocity.x = 0;
		}
	}
}
