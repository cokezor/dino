package com.amv.dinosaur;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.utils.TimeUtils;

public class DinoController {
	private Dinosaur dino;
	private boolean grounded = true;
	private boolean jumpPressed;
	private long jumpPressTime;
	
	final float JUMPVELOCITY = 100f;
	final float GRAVITY = -250.81f;
	final float WALKSPEED = 40f;
	private static final long LONG_JUMP_PRESS = 250l;
	
	public DinoController(Dinosaur dino){
		this.dino = dino;
	}
	
	enum Keys {
		JUMP, LEFT, RIGHT
	}
	
	static Map<Keys, Boolean> keys = new HashMap<DinoController.Keys, Boolean>();
	static{
		keys.put(Keys.JUMP, false);
		keys.put(Keys.LEFT, false);
		keys.put(Keys.RIGHT, false);
	}
	
	public void jumpPressed(){
		keys.put(Keys.JUMP, true);
	}
	
	public void jumpReleased(){
		keys.put(Keys.JUMP, false);
	}
	
	public void leftPressed(){
		keys.put(Keys.LEFT, true);
	}
	
	public void leftReleased(){
		keys.put(Keys.LEFT, false);
	}
	
	public void rightPressed(){
		keys.put(Keys.RIGHT, true);
	}
	
	public void rightReleased(){
		keys.put(Keys.RIGHT, false);
	}

	public void update(float delta){
		processInput();
		
		//initial vertical acceleration
		dino.acceleration.y = GRAVITY;
		// v = a * t
		dino.acceleration.mul(delta);
		dino.velocity.add(dino.acceleration.x, dino.acceleration.y);
		
		dino.position.add(dino.velocity.tmp().mul(delta));
		dino.bounds.x = dino.position.x;
		dino.bounds.y = dino.position.y;
		
		if (dino.position.y < 0){
			dino.position.y = 0;
			grounded = true;
		}
	}
	
	private void processInput(){
		if (keys.get(Keys.JUMP)){
			if (grounded){
				jumpPressed = true;
				grounded = false;
				jumpPressTime = System.currentTimeMillis();
				dino.velocity.y = JUMPVELOCITY;
			} 
			else {
				if (jumpPressed && ((System.currentTimeMillis() - jumpPressTime) >= LONG_JUMP_PRESS)){
					jumpPressed = false;
				} else {
					if (jumpPressed)
						dino.velocity.y = JUMPVELOCITY;
				}
			}
		}
		if (keys.get(Keys.RIGHT)){
			dino.velocity.x = WALKSPEED;
		}
		else if (keys.get(Keys.LEFT)){
			dino.velocity.x = -WALKSPEED;
		}
		else {
			dino.velocity.x = 0;
		}
	}
}
