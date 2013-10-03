package com.amv.dinosaur;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class DinosaurGame extends Game {
	static final int WIDTH = 480;
	static final int HEIGHT = 320;
	SpriteBatch batch;
	BitmapFont font;
	
	@Override
	public void create() {
		batch = new SpriteBatch();
		font = new BitmapFont();
		setScreen(new GameScreen(this));	
	}
	
	@Override
	public void render(){
		super.render();
		
	}
	
	public void dispose(){
		batch.dispose();
	}
}
