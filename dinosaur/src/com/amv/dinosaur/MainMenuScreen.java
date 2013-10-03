package com.amv.dinosaur;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class MainMenuScreen implements Screen {
	OrthographicCamera camera;
	final DinosaurGame game;

	public MainMenuScreen(final DinosaurGame game){
		this.game = game;
		camera = new OrthographicCamera();
		//set the size of the camera
		camera.setToOrtho(false, DinosaurGame.WIDTH, DinosaurGame.HEIGHT);
		camera.position.set(DinosaurGame.WIDTH/2, DinosaurGame.HEIGHT/2, 0);
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1f, 0, 0, 0);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		camera.update();
		game.batch.setProjectionMatrix(camera.combined);
		game.batch.begin();
		game.font.draw(game.batch, "Welcome to drop!", 100, 150);
		game.font.draw(game.batch, "Tap anywhere to begin", 100, 100);
		game.batch.end();
		if (Gdx.input.isTouched()){
			dispose();
			game.setScreen(new GameScreen(game));
		}
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

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

}
