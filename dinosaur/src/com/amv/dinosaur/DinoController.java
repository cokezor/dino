package com.amv.dinosaur;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.utils.TimeUtils;

public class DinoController {
	private Dinosaur dino;
	private boolean grounded = true;
	private boolean jumpPressed;
	private long jumpPressTime;
	
	final float JUMPVELOCITY = 50f;
	final float GRAVITY = -90.81f;
	private static final long LONG_JUMP_PRESS = 1000l;
	
	public DinoController(Dinosaur dino){
		this.dino = dino;
	}
	
	enum Keys {
		JUMP
	}
	
	static Map<Keys, Boolean> keys = new HashMap<DinoController.Keys, Boolean>();
	static{
		keys.put(Keys.JUMP, false);
	}
	
	public void jumpPressed(){
		keys.put(Keys.JUMP, true);
	}
	public void jumpReleased(){
		keys.put(Keys.JUMP, false);
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
	}
}
