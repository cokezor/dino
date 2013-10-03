package com.amv.dinosaur;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Dinosaur {
	Vector2 acceleration = new Vector2();
	Vector2 position = new Vector2();
	Vector2 velocity = new Vector2();
	Rectangle bounds = new Rectangle();
	final float JUMPVELOCITY = 10f;
	final float GRAVITY = -9.81f;

	public Dinosaur(Vector2 position){
		bounds.height = 64;
		bounds.width = 64;
		this.position.x = position.x;
		this.position.y = position.y;
	}
	
	public void update(float delta){
		position.add(velocity.tmp().mul(delta));
		bounds.x = position.x;
		bounds.y = position.y;
		
		//initial vertical acceleration
		acceleration.y = GRAVITY;
		
		acceleration.mul(delta);
		velocity.add(acceleration.x, acceleration.y);
		
		if (position.y < 0){
			position.y = 0;
		}
	}
}
