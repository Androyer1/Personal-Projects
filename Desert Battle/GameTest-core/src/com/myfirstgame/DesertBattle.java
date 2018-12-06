package com.myfirstgame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class DesertBattle extends Game {

	public SpriteBatch batch;
	public BitmapFont font;

	public void create() {
		batch = new SpriteBatch();
		font = new BitmapFont(Gdx.files.internal("TitleScreenFont.fnt"));
		this.setScreen(new MainMenuScreen(this));
	}

	public void render() {
		super.render();    
	}

	public void dispose() {
		batch.dispose();
		font.dispose();
		this.getScreen().dispose();
	}

}