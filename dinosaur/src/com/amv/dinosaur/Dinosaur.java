package com.amv.dinosaur;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Dinosaur {
	Vector2 acceleration = new Vector2();
	Vector2 position = new Vector2();
	Vector2 velocity = new Vector2();
	Rectangle bounds = new Rectangle();

	public Dinosaur(Vector2 position){
		bounds.height = 64;
		bounds.width = 64;
		this.position.x = position.x;
		this.position.y = position.y;
	}
	
	
}
