package com.myfirstgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class MainMenuScreen implements Screen {

	final DesertBattle game;
	private Sprite Map;
	private Texture MapTexture;
	private boolean Start = true;;
	private boolean Controls = false;

	public MainMenuScreen(final DesertBattle game) {
		this.game = game;
			
			//creates the map
		MapTexture = new Texture("Map_Game_2.PNG");
		Map = new Sprite(MapTexture);
		Map.setPosition(0, 0);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); 

		game.batch.begin();
		
			//draws map
		game.batch.draw(Map, Map.getX(), Map.getY());
		
			//checks if the game is in the start screen(starts by default)
		if (Start) {
			game.font.getData().setScale(1.5f);
			
				//draws title screen
			game.font.draw(game.batch,
					"Welcome to Desert Battle!\n" + "    Press Enter to begin!\n"
							+ "    Press Space to view the Controls!",
					Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 4,
					Gdx.graphics.getHeight() / 2 + Gdx.graphics.getHeight() / 8);
			
				//if ENTER is pressed, starts the game
			if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
				game.setScreen(new GameScreen(game));
				dispose();
			}
				
				//if SPACE is pressed, goes to controls screen
			if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
				Start = false;
				Controls = true;
			}
			
			//checks if the game is in the controls screen
		} else if (Controls) {
			game.font.getData().setScale(1.5f);
			
				//draws controls for both characters and how to switch back to start screen
			game.font.draw(game.batch,
					"Player 1 Controls\n" + "Press W to move UP\n" + "Press A to move LEFT\n" + "Press S to move DOWN\n"
							+ "Press D to move RIGHT\n" + "Press SPACE to SHOOT\n",
					25, Gdx.graphics.getHeight() / 2 + Gdx.graphics.getHeight() / 4 + Gdx.graphics.getHeight() / 8);
			game.font.draw(game.batch, "Player 2 Controls\n" + "Press UP to move UP\n" + "Press LEFT to move LEFT\n"
					+ "Press DOWN to move DOWN\n" + "Press RIGHT to move RIGHT\n" + "Press RIGHT CONTROL to SHOOT\n",
					Gdx.graphics.getWidth() / 2,
					Gdx.graphics.getHeight() / 2 + Gdx.graphics.getHeight() / 4 + Gdx.graphics.getHeight() / 8);
			game.font.draw(game.batch, "To go back to Start, press ESCAPE",
					Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 4, 120);
				
				//if ESCAPE is pressed, goes to start screen
			if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
				Controls = false;
				Start = true;
			}
		}
		game.batch.end();

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resize(int width, int height) {
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
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		MapTexture.dispose();
	}

}