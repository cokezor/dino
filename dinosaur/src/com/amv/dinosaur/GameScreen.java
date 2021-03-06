package com.amv.dinosaur;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;

public class GameScreen implements Screen, InputProcessor {
	Dinosaur dino;
	final DinosaurGame game;
	DinoController controller;
	private OrthographicCamera camera;
	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	
	//make one pixel = 32x32 see 10x7
	private static final float CAMERA_WIDTH = 320f;
	private static final float CAMERA_HEIGHT = 224f;
	
	private float ppuX, ppuY;
	
	public GameScreen(final DinosaurGame game){
		this.game = game;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, CAMERA_WIDTH, CAMERA_HEIGHT);
		//camera.position.set(5, 3.5f, 0);
		//camera.update();
		//set the dinosaur @ middle of the screen
		dino = new Dinosaur(new Vector2(5,5));
		controller = new DinoController(dino);
	}
	@Override
	public void render(float delta) {
		//clear screen
		Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		System.out.println(camera.position.x + " " + camera.position.y);
		System.out.println(dino.position.x + " " + dino.position.y);
		//render the map
		renderer.render();
		renderer.setView(camera);
		
		//camera.position.set(dino.position.x + 32, 160, 0);
		//camera.update();
		
		controller.update(delta);
		dino.update(delta);
		//use projection matrix specified by camera
		//renderer.getSpriteBatch().setProjectionMatrix(camera.combined);
		//record all drawing commands from begin
		renderer.getSpriteBatch().begin();
		renderer.getSpriteBatch().draw(dino.image, dino.position.x, dino.position.y);
		//send all sprites to be rendered
		renderer.getSpriteBatch().end();
		
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(this);
		map = new TmxMapLoader().load("map/map.tmx");
		renderer = new OrthogonalTiledMapRenderer(map);
	}

	@Override
	public void hide() {
		dispose();
		Gdx.input.setInputProcessor(null);
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
		Gdx.input.setInputProcessor(null);
		map.dispose();
		renderer.dispose();
	}
	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Keys.Z){
			controller.jumpPressed();
		}
		else if (keycode == Keys.LEFT){
			controller.leftPressed();
		}
		else if (keycode == Keys.RIGHT){
			controller.rightPressed();
		}
		return true;
	}
	@Override
	public boolean keyUp(int keycode) {
		if (keycode == Keys.Z){
			controller.jumpReleased();
		}
		if (keycode == Keys.LEFT){
			controller.leftReleased();
		}
		if (keycode == Keys.RIGHT){
			controller.rightReleased();
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
