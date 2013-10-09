package com.amv.dinosaur;

import com.badlogic.gdx.Game;

public class DinosaurGame extends Game {
	@Override
	public void create() {
		setScreen(new GameScreen(this));	
	}
	
	@Override
	public void render(){
		super.render();
		
	}
	
	public void dispose(){
		super.dispose();
	}
	
	public void pause(){
		super.pause();
	}
	
	public void resize(int width, int height){
		super.resize(width, height);
	}
	
	public void resume(){
		super.resume();
	}
}
