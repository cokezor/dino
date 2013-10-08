package com.amv.dinosaur;

import java.util.HashMap;
import java.util.Map;

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
		dino.acceleration.scl(delta);
		// v += acceleration
		dino.velocity.add(dino.acceleration.x, dino.acceleration.y);
		//position += v * time
		dino.position.add(dino.velocity.cpy().scl(delta));
		//update bounds
		dino.bounds.x = dino.position.x;
		dino.bounds.y = dino.position.y;
		
		//if dinosaur hits the bottom of the screen
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
				}
				//if user is holding on to jump
				else {
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
