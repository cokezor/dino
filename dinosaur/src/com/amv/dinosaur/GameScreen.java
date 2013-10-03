package com.amv.dinosaur;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class GameScreen implements Screen, InputProcessor {
	Dinosaur dino;
	final DinosaurGame game;
	Texture dinoImage;
	DinoController controller;
	
	public GameScreen(final DinosaurGame game){
		this.game = game;
		dino = new Dinosaur(new Vector2(800/2-64/2, 20));
		controller = new DinoController();
		dinoImage = new Texture(Gdx.files.internal("images/dinosaur.png"));
	}
	@Override
	public void render(float delta) {
		//set color to blue and clear screen
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		game.camera.update();
		
		//use projection matrix specified by camera
		game.batch.setProjectionMatrix(game.camera.combined);
		
		dino.update(delta);
		
		//record all drawing commands from begin
		game.batch.begin();
		game.batch.draw(dinoImage, dino.position.x, dino.position.y);
		//send all sprites to be rendered
		game.batch.end();
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Keys.LEFT){
			
		}
		return true;
	}
	@Override
	public boolean keyUp(int keycode) {
		if (keycode == Keys.LEFT){
			
		}
		return true;
	}
	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
