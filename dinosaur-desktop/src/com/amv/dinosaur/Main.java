package com.amv.dinosaur;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class Main {
	public static void main(String[] args) {
		new LwjglApplication(new DinosaurGame(), "Dinosaur", 480, 320, true);
	}
}