package com.amv.dinosaur;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.utils.TimeUtils;

public class DinoController {
	private Dinosaur dino;
	private boolean grounded = true;
	private boolean jumpPressed;
	private long jumpPressTime;

	final float JUMPVELOCITY = 96f;
	final float GRAVITY = -313.6f;
	final float WALKSPEED = 64f;
	private static final long LONG_JUMP_PRESS = 250l;

	private TiledMapTileLayer collisionLayer;
	boolean collideX = false, collideY = false;
	float tileWidth, tileHeight;

	public DinoController(Dinosaur dino, TiledMapTileLayer collisionLayer) {
		this.dino = dino;
		this.collisionLayer = collisionLayer;
		tileWidth = collisionLayer.getWidth();
		tileHeight = collisionLayer.getHeight();
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
		processInput();

		// initial vertical acceleration
		dino.acceleration.y = GRAVITY;
		// v = a * t
		dino.acceleration.scl(delta);
		// v += acceleration
		dino.velocity.add(dino.acceleration.x, dino.acceleration.y);
		// position += v * time
		dino.position.add(dino.velocity.cpy().scl(delta));
		// update bounds
		dino.bounds.x = dino.position.x;
		dino.bounds.y = dino.position.y;

		if (dino.velocity.x < 0) {
			// top left of dino
			collideX = collisionLayer
					.getCell(
							(int) (dino.getX() / tileWidth),
							(int) ((dino.getY() + dino.getBounds().height) / tileHeight))
					.getTile().getProperties().containsKey("blocked");

			// middle left
			if (!collideX)
				collideX = collisionLayer
						.getCell(
								(int) (dino.getX() / tileWidth),
								(int) ((dino.getY() + dino.getBounds().height / 2) / tileHeight))
						.getTile().getProperties().containsKey("blocked");

			// bottom left
			if (!collideX)
				collideX = collisionLayer
						.getCell((int) (dino.getX() / tileWidth),
								(int) (dino.getY() / tileHeight)).getTile()
						.getProperties().containsKey("blocked");
		} else if (dino.velocity.x > 0) {
			// top right
			collideX = collisionLayer
					.getCell(
							(int) ((dino.getX() + dino.getBounds().width) / tileWidth),
							(int) ((dino.getY() + dino.getBounds().height) / tileHeight))
					.getTile().getProperties().containsKey("blocked");
			// middle right
			if (!collideX)
				collideX = collisionLayer
						.getCell(
								(int) ((dino.getX() + dino.getBounds().width) / tileWidth),
								(int) ((dino.getY() + dino.getBounds().height / 2) / tileHeight))
						.getTile().getProperties().containsKey("blocked");
			// bottom right
			if (!collideX)
				collideX = collisionLayer
						.getCell(
								(int) ((dino.getX() + dino.getBounds().width) / tileWidth),
								(int) ((dino.getY()) / tileHeight)).getTile()
						.getProperties().containsKey("blocked");
		}

		if (collideX) {
			dino.position.x -= delta * dino.velocity.x;

		}
		if (dino.velocity.y < 0){
			collisionLayer.getCell(0, 6).getTile().getProperties().containsKey("blocked");
			System.out.println("Blocked");
		}
		
		
		// if dinosaur hits the bottom of the screen
//		if (dino.velocity.y < 0) {
//			// bottom left
//			collideY = collisionLayer
//					.getCell(
//							(int) ((dino.getX() + dino.getBounds().width / 2) / tileWidth),
//							(int) (dino.getY() / tileHeight)).getTile()
//					.getProperties().containsKey("blocked");
//			// bottom middle
//			if (!collideY)
//				collideY = collisionLayer
//						.getCell(
//								(int) ((dino.getX() + dino.getBounds().width / 2) / tileWidth),
//								(int) (dino.getY() / tileHeight)).getTile()
//						.getProperties().containsKey("blocked");
//			// bottom right
//			if (!collideY)
//				collideY = collisionLayer
//						.getCell(
//								(int) ((dino.getX() + dino.getBounds().width) / tileWidth),
//								(int) ((dino.getY()) / tileHeight)).getTile()
//						.getProperties().containsKey("blocked");
//		} else if (dino.velocity.y > 0) {
//			// top left
//			collideY = collisionLayer
//					.getCell(
//							(int) ((dino.getX()) / tileWidth),
//							(int) ((dino.getY() + dino.getBounds().height) / tileHeight))
//					.getTile().getProperties().containsKey("blocked");
//			// top middle
//			if (!collideY)
//				collideY = collisionLayer
//						.getCell(
//								(int) ((dino.getX() + dino.getBounds().width / 2) / tileWidth),
//								(int) ((dino.getY() + dino.getBounds().height) / tileHeight))
//						.getTile().getProperties().containsKey("blocked");
//			// top right
//			if (!collideY)
//				collideY = collisionLayer
//						.getCell(
//								(int) ((dino.getX() + dino.getBounds().width) / tileWidth),
//								(int) ((dino.getY() + dino.getBounds().height) / tileHeight))
//						.getTile().getProperties().containsKey("blocked");
//		}

		if (collideY) {
			dino.position.y -= delta * dino.velocity.y;
		}

	}

	private boolean isCellBlocked(Cell cell) {
		return cell.getTile() != null
				&& cell.getTile().getProperties().containsKey("blocked");
	}

	public TiledMapTileLayer getCollisionLayer() {
		return collisionLayer;
	}

	public void setCollisionLayer(TiledMapTileLayer collisionLayer) {
		this.collisionLayer = collisionLayer;
	}

	private void processInput() {

		if (keys.get(Keys.JUMP)) {
			if (grounded) {
				jumpPressed = true;
				grounded = false;
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
			dino.velocity.x = WALKSPEED;
		}

		else if (keys.get(Keys.LEFT)) {
			dino.velocity.x = -WALKSPEED;
		}

		else {
			dino.velocity.x = 0;
		}
	}
}
