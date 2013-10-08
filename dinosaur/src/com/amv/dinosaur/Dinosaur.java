package com.amv.dinosaur;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Dinosaur {
	public static final float SIZE = 32f;
	Vector2 acceleration = new Vector2();
	Vector2 position = new Vector2();
	Vector2 velocity = new Vector2();
	Rectangle bounds = new Rectangle();
	Texture image;

	public Dinosaur(Vector2 position){
		bounds.height = SIZE;
		bounds.width = SIZE;
		this.position.x = position.x;
		this.position.y = position.y;
		image = new Texture(Gdx.files.internal("images/smalldino.png"));
	}
	
	public void update(float delta){
		
	}
}
