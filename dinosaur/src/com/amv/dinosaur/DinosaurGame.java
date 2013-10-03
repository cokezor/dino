package com.amv.dinosaur;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class DinosaurGame extends Game {
	SpriteBatch batch;
	BitmapFont font;
	OrthographicCamera camera;
	
	@Override
	public void create() {
		batch = new SpriteBatch();
		font = new BitmapFont();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		setScreen(new MainMenuScreen(this));	
	}
	
	@Override
	public void render(){
		super.render();
		
	}
	
	public void dispose(){
		batch.dispose();
	}
}
